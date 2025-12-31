---
title: "[Project] SNS 서비스 - React & STS 활용"
date: 2025-12-31 10:00:00 +0900
categories: [Project]
tags: [project, react, STS]
---

## 목차
1. [프로젝트 개요](#project-summary)
2. [로그인/회원가입 구현](#auth-implementation)
3. [카카오 지도 API 연동](#kakao-map-api)
4. [스토리 기능 구현](#story-feature)
5. [트러블슈팅 및 회고](#troubleshooting)

---

<h2 id="project-summary">프로젝트 개요</h2>


* **주제**: SNS 서비스
* **벤치마킹**: 인스타그램, 싸이월드, 페이스북
* **특징**: 인스타그램의 빠른 소통 방식에 싸이월드의 개인 공간(방명록, 다이어리, BGM) 감성을 더해 차별화된 사용자 경험을 제공하고자 했습니다
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

* **Frontend(React)**: 클라이언트에게 보이는 화면으로, Axios 라이브러리를 통해 백엔드와 비동기 통신을 하여 부드러운 UI 사용 경험을 준다
* **Backend(Spring Boot)**: 비즈니스 로직을 담당하는 서버로, DB와 외부 API로부터 데이터를 가공하고 전달함
* **Data Base(Oracle)**: 모든 데이터를 관리 및 저장
* **API(Kakao Map)**: 서버로부터 지도 데이터를 요청받아 사용자(클라이언트)에게 시각화함

<h2 id="auth-implementation">로그인/회원가입 구현</h2>
(여기에 내용 작성...)

<h2 id="kakao-map-api">카카오 지도 API 연동</h2>
(여기에 내용 작성...)

<h2 id="story-feature">스토리 기능 구현</h2>
(여기에 내용 작성...)

<h2 id="troubleshooting">트러블슈팅 및 회고</h2>
(여기에 내용 작성...)