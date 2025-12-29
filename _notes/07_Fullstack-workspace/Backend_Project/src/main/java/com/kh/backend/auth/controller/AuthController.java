package com.kh.backend.auth.controller;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.backend.auth.model.service.AuthService;
import com.kh.backend.member.model.vo.Member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("auth")
public class AuthController {
	
	// 인가 과정에서도 써야하기 때문에 일부러 상수필드로 빼둠
	public static final String secretKey = "Hello123KHAcademy456Dangsan789WelcomeToEClass";
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/*
	 * 기존 로그인 구현 방법
	 * 회원 정보를 HttpSession 객체에 담아서 전역으로 사용
	 * - JSP는 스프링 내부라서 꺼내쓸 수 있음
	 * 
	 * - 리액트는 못 써서,
	 * 안전한 형태로 응답데이터 넘기고(JWT - Json Web Token 사용) 브라우저 상 저장소에 저장(HttpSession 객체 역할 대체)
	 * JWT - JSON 형태: 제대로 된 데이터인지, 위변조되었는지 검증 및 판별해줌
	 * JWT 구조
	 * 1. Header : 데이터를 담는 그릇의 머리부(전반적인 설정값들이 담김 - 암호화 알고리즘 종류 등)
	 * 2. Payload : ~ 몸통부(실제 내용 담김 - 아이디, 권한, 발급일 등)
	 * 3. Signature : 서명(헤더, 페이로드 암호화한 값 정보를 합침 - 인증, 위변조되었는지 확인)
	 * 
	 * JWT 라이브러리 필요 jjwt
	 */
	
	// Jwts 타입 객체 생성(builder라는 생성자 역할의 메소드로 생성)
	// 회원 구분하는 고유값(아이디) 세팅
	// 회원 이름
	// 회원 권한
	// JWT 발급시간
	// JWT 만료시간(1시간 후)
	// 서명시 필요한 키, 암호화 알고리즘 종류 지정
	// 위 설정한 내용으로 하나의 토큰 만ㄷ름(문자열 타입으로)
	
	// 로그인용
	@PostMapping("login")
	public String loginAdmin(@RequestBody Member m) {
		
		// System.out.println(m);
		
		// 평문비번: m.getUserPwd();
		// System.out.println(m.getUserPwd()); // 평문비번
		// System.out.println(bCryptPasswordEncoder.encode(m.getUserPwd())); // 암호문 비번
		
		// 일단 아이디만 넘겨서 일치하는지 검사 - 쿼리문에 다 넣고
		Member loginUser = authService.loginAdmin(m.getUserId());
		
		// System.out.println(loginUser); // admin만 조회 or null
		
		if(loginUser != null && bCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			
			// 로그인 성공
			
			// loginUser 객체값으로 JWT 생성
			// 1. 비밀 키 생성 - 복잡하게, 32글자 이상 32byte == 256bit 이상, 특수기호 제외
			// String secretKey = "Hello123KHAcademy456Dangsan789WelcomeToEClass";
			
			// 2. 문자열 타입의 비밀키 서명을 위한 Key 객체로 가공 - 본격적으로 jjwt 라이브러리 이용
			Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
			
			// 3. 위 Key로 JWT 생성
			String jwt = Jwts.builder()
					.setSubject(loginUser.getUserId())
					.claim("name", loginUser.getUserName())
					.claim("role", loginUser.getUserRole())
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000))
					.signWith(key, SignatureAlgorithm.HS256)
					.compact();
			
			// System.out.println(jwt);
			// Header.Payload.Signature
			// 토큰은 jwt.io 사이트에서 해석해줌
			
			return jwt;

		} else {
			
			// 로그인 실패
			return null;
		}
		
		
	}
	

}
