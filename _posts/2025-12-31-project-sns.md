---
title: "[Project] SNS 서비스 - React & STS 활용"
date: 2025-12-31 10:00:00 +0900
categories: [Project]
tags: [project, react, STS]
---

## 목차
1. [프로젝트 개요](#project-summary)
2. [로그인/회원가입 기능 구현](#auth-implementation)
3. [카카오 지도 API 연동](#kakao-map-api)
4. [트러블슈팅](#troubleshooting)
5. [느낀점](#conclusion)

---

<h2 id="project-summary">프로젝트 개요</h2>


* **주제**: SNS 서비스
* **벤치마킹**: 인스타그램, 싸이월드, 페이스북
* **특징**: 인스타그램의 빠른 소통 방식에 싸이월드의 개인 공간(방명록, 다이어리, BGM) 감성을 더해 차별화된 사용자 경험을 제공합니다.
* **참여자 수**: 4명 (기여도 25%)
* **기간**: 2025.12.11. ~ 2026.01.06. (약 4주)

* **담당기능**
1. 로그인 및 회원가입 - JWT, LocalStorage 활용한 보안 중심
2. 지도 API 연동 - 카카오지도를 활용한 위치 지정
3. 스토리 - React Framework 활용한 게시물

| 분류 | 기술 스택 (Tech Stack) |
| :--- | :--- |
| **Frontend** | `React`, `JavaScript (ES6+)`, `CSS3`, `Axios` |
| **Backend** | `Java 11`, `Spring Boot`, `Spring Security`, `MyBatis`, `Lombok` |
| **Database** | `Oracle 11g XE` |
| **API / Lib** | `Kakao Maps API`, `JWT (JSON Web Token)` |
| **Tools** | `GitHub`, `VS Code`, `STS` |

* **시스템 아키텍처**


<img src="/assets/img/project_intro_architecture_draw.io.png" width="50%" alt="시스템 아키텍처 다이어그램">

* **Frontend(React)**: 클라이언트에게 보이는 화면으로, Axios 라이브러리를 통해 백엔드와 비동기 통신을 하여 부드러운 UI 사용 경험을 줍니다.
* **Backend(Spring Boot)**: 비즈니스 로직을 담당하는 서버로, DB와 외부 API로부터 데이터를 가공하고 전달합니다.
* **Data Base(Oracle)**: 모든 데이터를 관리 및 저장합니다.
* **API(Kakao Map)**: 서버로부터 지도 데이터를 요청받아 사용자(클라이언트)에게 시각화합니다.

### ERD Cloud

<img src="/assets/img/project_sns_erd.png" width="50%" alt="erd cloud">


### Use Case Diagram

<img src="/assets/img/project_sns_useCase.png" width="50%" alt="useCase">


---

<h2 id="auth-implementation">로그인/회원가입</h2>

<img src="/assets/img/project_sns_login.png" width="50%" alt="login">
<img src="/assets/img/project_sns_signup.png" width="50%" alt="signup">
<img src="/assets/img/project_sns_findPwd.png" width="50%" alt="findPwd">

**JWT(JSON Web Token) 인증 방식**
1. 프론트엔드와 백엔드가 분리된, 도메인이 다른 구조에서도 인증을 효율적으로 처리할 수 있습니다.
2. LocalStorage에 저장하고, 모든 요청에 대해 토큰을 header에 담아 전송함으로 보안성을 강화했습니다.

### 주요 설정
1. **BCryptPasswordEncoder**: 비밀번호를 단방향 해시 암호화해서 DB에 저장
```java
@Bean
public static BCryptPasswordEncoder bCryptPasswordEncoder() {

	return new BCryptPasswordEncoder();
}
```

2. **이메일 SMTP**: 회원가입 및 계정 찾기 시 인증

```ini
# SMTP 서버 설정
spring.mail.host=smtp.gmail.com
spring.mail.port=587

# gmail 계정 설정
spring.mail.username=abcd@gmail.com
spring.mail.password=abcd

# 프로토콜(통신 규약) 및 인코딩 설정
spring.mail.protocol=smtp
spring.main.default-encoding=UTF-8

# 보안 설정
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

3. **JWT(JSON Web Token)**: 사용자 정보를 담은 토큰을 발급해 세션 없이도 인증 상태 유지

```ini
# 로그인 jwt 설정
jwt.secret=Hello123KHAcademy456Dangsan789WelcomeToDClass

# 만료(1시간 - 1 * 60 * 60 * 1000)
jwt.expiration=3600000
```

```java
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    // application.properties 파일에서 가져오기
    @Value("${jwt.secret}")
    private String secretKey;

    //필터 제외 경로 설정: 로그인, 회원가입, 정적 리소스 등은 토큰 검사 없이 통과
    private boolean isSkipPath(String path) {
        return path.startsWith("/login") || path.startsWith("/signup") || 
               path.startsWith("/find") || path.startsWith("/ws") || 
               path.startsWith("/images") || path.startsWith("/css") || 
               path.startsWith("/js") || path.startsWith("/feed_upfiles");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI().substring(request.getContextPath().length());

        // 1. OPTIONS(Preflight) 요청은 인증 없이 통과 (CORS 대응)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Authorization 헤더에서 Bearer 토큰 추출
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            // 3. JWT 서명 검증 및 데이터(Claims) 파싱
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();

            // 4. 사용자 정보 추출 및 Request 객체에 저장
            String memberId = claims.getSubject();
            Integer memberNo = claims.get("memberNo", Integer.class);
            request.setAttribute("memberNo", memberNo);

            // 5. SecurityContext에 인증 객체 등록 (정상 토큰인 경우)
            if (StringUtils.hasText(memberId) && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        memberId, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            // 토큰 만료/위조 시 별도의 예외 처리 없이 통과 
            // -> SecurityConfig에서 최종적으로 접근이 차단됨
        }

        filterChain.doFilter(request, response);
    }
}
```

4. **CORS(Cross Origin Resource Sharing)**: Reqct(5173)과 Spring(8080) 간 다른 포트번호임에도 통신 허용
```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration config = new CorsConfiguration();

    // 1. 통신을 허용할 프론트엔드 주소 지정
    config.addAllowedOrigin("http://localhost:5173");
    config.addAllowedOriginPattern("http://192.168.*.*:5173");

    // 2. 모든 Header와 HTTP Method(GET, POST 등) 허용
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    // 3. 인증 정보(Credentials) 포함 허용
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}
```

5. **Validation**: 아이디, 비밀번호, 닉네임의 유효성을 검증
```java
if(!memberId.matches("^[a-z0-9]{4,12}$")
	|| !memberPwd.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,16}$")
	|| !memberNick.matches("^[A-Za-z0-9가-힣_.]{2,10}$")) {
	
	return 0;
}
```

### 로그인 요청 처리 - MVC(Service)
```java
@Override
public String loginMember(Login login) {
    
    // 회원 조회
    Login loginUser = loginDao.loginMember(login, sqlSession);

    // 아이디 없는 경우
    if(loginUser == null) {
        
        // 로그인 실패
        return null;
    }
    
    // 비밀번호 비교 (입력한 값 vs DB에 암호화된 값)
    if(!bCryptPasswordEncoder.matches(login.getMemberPwd(), loginUser.getMemberPwd())) {
        
        // 로그인 실패
        return null;
    }
    // JWT 서명(Signature)에 사용할 비밀 키 생성
    // properties 파일에 정의한 jwt.secret 값 기반임
    // HMAC-SHA256(HS256) 알고리즘에 맞는 Key 객체로 변환
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    
    String jwt = Jwts.builder()
            .setSubject(loginUser.getMemberId()) // JWT subject 값 설정(로그인 식별자)
            .claim("memberNo", loginUser.getMemberNo()) // 회원번호 pk를 claim에 저장
            .claim("memberName", loginUser.getMemberName()) // 회원이름을 claim에 저장
            .setIssuedAt(new Date()) // 토큰 발급시간 설정
            .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 토큰 만료시간 설정
            .signWith(key, SignatureAlgorithm.HS256) // 위에서 생성한 Key와 HS256 알고리즘으로 서명
            .compact(); // JWT 문자열 생성

    // 로그인 성공
    return jwt;
	}
```

### 추가 기능
사용자 편의성과 서비스 완결성을 위해 아래 기능을 추가로 구현했습니다.

* **아이디 찾기**: 가입 시 등록한 이름과 이메일 정보를 DB와 대조하여 일치할 경우 제공합니다.
* **비밀번호 재설정**: 본인 인증(이메일 등) 후 BCryptPasswordEncoder를 통해 안전하게 재설정할 수 있습니다.


### JWT 인증 고찰

`JwtAuthFilter`를 구현하며 스프링 시큐리티의 동작 원리와 보안 흐름을 이해할 수 있었습니다.

#### 1. 코드 사용 이유
* **OncePerRequestFilter**: 사용자의 한 번의 요청에 대해 딱 한 번만 실행되도록 보장하여, 불필요한 중복 인증을 방지하고 자원 효율성을 높였습니다.
* **Bearer**: HTTP 인증 표준인 `Bearer` 스키마를 준수하여 토큰의 타입을 명확히 구분하고, 헤더를 통해 안전하게 인증 정보를 전달했습니다.


#### 2. 인증 처리의 4단계 메커니즘

1. **필터링**: 로그인, 회원가입, 정적 리소스 등 토큰 검사가 필요 없는 공용 경로를 사전에 구분하기
2. **추출**: 클라이언트가 보낸 HTTP 헤더에서 JWT 토큰을 안전하게 꺼내기
3. **검증**: 서버만 알고 있는 Secret Key를 이용해 토큰의 위변조 여부 및 만료 시간을 체크하기
4. **등록**: 검증이 완료된 사용자를 `SecurityContext`에 인증된 사용자로 등록하여, 서비스 전체에서 로그인 정보를 활용하기


<h2 id="kakao-map-api">카카오 지도 API 연동</h2>

<img src="/assets/img/project_sns_map_api.png" width="50%" alt="kakaoMap">

### 준비(App Key 발급)
1. **[카카오 디벨로퍼스](https://developers.kakao.com/)** 접속
2. **내 애플리케이션** 생성
3. **플랫폼 > Web** 에 내 사이트 도메인 등록 (테스트 시 `http://localhost` 등)
4. **앱 키 > JavaScript 키** 복사

### 기본 세팅
지도를 표시하기 위해 라이브러리를 로드하고, 지도가 담길 `div`를 생성

```html
<script type="text/javascript"
         src="//[dapi.kakao.com/v2/maps/sdk.js?appkey=YOUR_JS_KEY](https://dapi.kakao.com/v2/maps/sdk.js?appkey=YOUR_JS_KEY)">
</script>
<div id="map" style="width:100%; height:400px;"></div>
```

### 주요 코드
1. **로딩 지연 해결(재귀함수 사용)**: `setTimeout`으로 스크립트 로드 후 `kakao`객체 접근하기

```javascript
// 로딩 타이밍 안전 처리
const wait = () => {

  // kakao 객체와 필요한 라이브러리가 모두 로드되었는지 체크
  if (window.kakao && window.kakao.maps && window.kakao.maps.services) {

    init(); // 로드 완료 시 지도 초기화 실행

  } else {

    setTimeout(wait, 50); // 로드 안 됐으면 50ms 후 다시 시도
  }
};

wait();
```

2. **외부 객체 관리**: `useRef`는 값이 변해도 렌더링에 영향을 주지 않으면서 객체 유지함.(State와 달리 값이 변해도 재렌더링하지 않음)

```javascript
// 지도, 마커, 검색 서비스 객체를 담을 참조 변수
const mapRef = useRef(null);
const markerRef = useRef(null);
const placesRef = useRef(null);

// 초기화 시 ref에 객체 할당 (재렌더링 시에도 지도 객체 유지)
mapRef.current = new window.kakao.maps.Map(container, options);
markerRef.current = new window.kakao.maps.Marker({ position: center });
placesRef.current = new window.kakao.maps.services.Places();
```

3. **장소 선택 및 데이터 처리**: 사용자가 검색 결과를 골랐을 때 지도 이동과 데이터 처리를 동시에 수행

```javascript
const handlePickPlace = (p) => {
  const pos = new window.kakao.maps.LatLng(Number(p.y), Number(p.x));

  // 지도 중심 이동 및 마커 위치 변경 - 시각적 처리
  mapRef.current.setCenter(pos);
  markerRef.current.setPosition(pos);

  // 부모 컴포넌트로 선택된 장소의 상세 정보 전달 - 데이터 처리
  if (onSelect) {
    onSelect({
      latitude: Number(p.y),
      longitude: Number(p.x),
      placeName: p.place_name,
      addressName: p.address_name,
      // 필요한 정보만 선별해서 전달
    });
  }
};
```

### 카카오지도 API 연동 고찰
`KakaoLocationPicker` 컴포넌트를 구현하여 외부 라이브러리를 React 인터페이스와 통합하는 최적의 방법을 익힐 수 있었습니다.

#### 1. 주요 기술 적용 이유
* **재귀함수**: 외부 SDK 로드 시점과 컴포넌트 마운트 시점의 차이로 발생하는 에러를 방지하기 위해 사용했습니다.

* **useRef**: 지도 인스턴스와 마커 객체는 리렌더링 시마다 다시 생성될 필요가 없는 참조값입니다.(상태 아님) 이 것을 `useRef`로 관리해 메모리 낭비를 줄였습니다.

#### 2. 장소 선택 처리의 4단계 메커니즘
1. **초기화**: `useEffect` 내에서 지도를 생성하고 검색과 마커 객체를 메모리에 할당

2. **검색**: 키워드 바탕으로 카카오 로컬 서비스를 호출하여 장소 리스트 확보

3. **상호작용**: 검색 결과로 지도 좌표 이동, 마커를 배치하여 시각적인 피드백 제공

4. **데이터 전달**: 좌표, 징소명, 고유ID 등 로직에 필요한 정보를 부모 컴포넌트로 전달




<h2 id="troubleshooting">트러블슈팅</h2>

## 로그인/회원가입 관련

* **JWT 필터 적용 후 CORS 에러 발생**

1. **현상**: Spring Security와 JWT 필터를 적용한 후 분명히 `@CrossOrigin`을 통해 CORS 설정을 완료하였음에도 CORS 에러가 발생하며 로그인이 막힘
2. **원인**: 브라우저는 본 요청을 보내기 전에 먼저 `OPTIONS` 메소드로 프리플라이트 요청을 먼저 보내는데, `JwtAuthFilter`는 모든 요청에 대해 토큰을 검사하도록 설정되어있음. 따라서 토큰이 없는 `OPTIONS` 요청을 유효하지 않다고 판단하여 401 에러를 반환함. 결과적으로 본 로그인 요청을 보내지 않아서 오류 발생
3. **해결**: `JwtAuthFilter`에 `OPTIONS` 메소드를 통과시키도록 `filterChain.doFilter` 코드 수정

* **`requestMatchers` 설정 및 권환 오류**

1. **현상**: 분명히 허용한 경로임에도 불구하고 403 에러가 발생
2. **원인**: `SecurityConfig`에서는 `permitAll`로 허용했으나, `JwtAuthFilter` 내의 `isSkipPath`에는 해당 경로를 제외하지 않아 필터 단계에서 토큰 검증이 실패되어 차단됨
3. **해결**: `SecurityConfig`, `JwtAuthFilter`를 항상 일관성 있게 관리함


## 카카오 지도 API 관련

* **SDK 로딩 시점 차이로 인한 `kakao is not defined` 에러**

1. **현상**: 페이지 접속 시 지도가 로드되지 않고 콘솔에 에러 발생하였음
2. **원인**: React 컴포넌트 마운트 속도가 지도 로드 속도보다 빨랐던 것이 문제였음
3. **해결**: 재귀 함수 사용, `window.kakao` 객체가 존재하는지 체크 후 초기화 로직을 실행하여 안정성 확복

* **모달 창 내 지도 깨짐 현상**

1. **현상**: 지도 화면이 잘려서 보임
2. **원인**: 지도가 로드되는 시점에 부모 컨테이너 크기가 0이었음
3. **해결**: `useEffect`를 사용하여 호출


<h2 id="conclusion">느낀점</h2>

* 오류의 유형을 파악하는 것의 중요성 - 유형을 알아야 빠른 해결이 가능하다는 것을 깨달았습니다.
* 파일 세분화의 중요성 - 오류 수정, 가독성, 탐색에 효율적임을 깨달았습니다.
* 기본 지식의 중요성 - 개념을 안 상태로 자료를 찾는 것과, 모르는 상태로 찾는 것에 대한 이해 및 코드 작성에 차이가 크다는 것을 느꼈습니다.
* 팀원 간 배려와 맡은 역할에 대한 책임감
