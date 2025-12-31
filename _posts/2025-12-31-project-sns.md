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
4. [스토리 기능 구현](#story-feature)
5. [트러블슈팅 및 회고](#troubleshooting)

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

<h2 id="auth-implementation">로그인/회원가입</h2>

**JWT(JSON Web Token) 인증 방식**
1. 프론트엔드와 백엔드가 분리된, 도메인이 다른 구조에서도 인증을 효율적으로 처리할 수 있습니다.
2. LocalStorage에 저장하고, 모든 요청에 대해 토큰을 header에 담아 전송함으로 보안성을 강화했습니다.

### 주요 설정
1. BCryptPasswordEncoder 암호화
```java
// 단방향 해시 암호화 후 보관(DB에 그대로 저장 X)
@Bean
public static BCryptPasswordEncoder bCryptPasswordEncoder() {

	return new BCryptPasswordEncoder();
}
```

2. 이메일(계정 찾기 및 인증)
```properties
# SMTP 서버 설정
spring.mail.host=smtp.gmail.com
spring.mail.port=587

spring.mail.username=abcd@gmail.com
spring.mail.password=abcd

spring.mail.protocol=smtp
spring.main.default-encoding=UTF-8

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```


### 추가 기능
사용자 편의성과 서비스 완결성을 위해 아래 기능을 추가로 구현했습니다.

* **아이디 찾기**: 가입 시 등록한 이름과 이메일 정보를 DB와 대조하여 일치할 경우 제공합니다.
* **비밀번호 재설정**: 본인 인증(이메일 등) 후 BCryptPasswordEncoder를 통해 안전하게 재설정할 수 있습니다.

<h2 id="kakao-map-api">카카오 지도 API 연동</h2>
(여기에 내용 작성...)

<h2 id="story-feature">스토리 기능 구현</h2>
(여기에 내용 작성...)

<h2 id="troubleshooting">트러블슈팅 및 회고</h2>
(여기에 내용 작성...)