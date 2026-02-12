---
title: "[Java] JDBC 특징과 구성 요소, MyBatis"
date: 2026-01-15 09:00:00 +0900
categories: [Java, Database]
tags: [jdbc, java, oracle, db, mybatis, backend]
---


### JDBC란?
Java 애플리케이션과 데이터베이스를 연결해 주는 표준 인터페이스


#### 특징
* **DB 접속 및 SQL 실행**: Java 애플리케이션이 DB에 접속하고 SQL을 실행할 수 있도록 지원한다.
* **DB 독립적 구조**: Oracle, MySQL, MSSQL 등 DB 종류가 달라도 JDBC API 사용법은 동일하다.
* **SQL 기반 데이터 처리**: 개발자가 직접 SQL을 작성하여 CRUD를 처리하며, 실행 결과를 Java 객체로 변환할 수 있다.
* **범용적 실행 환경**: Java 표준 API이므로 Local 환경 및 WAS 환경에서 동작 가능하다.
* **프레임워크 확장성**: 순수 JDBC의 반복 코드를 줄이기 위해 MyBatis나 Spring JDBC, JPA 등과 함께 사용됩니다.


#### 구성 요소
JDBC를 이용한 데이터 처리 흐름에 필수적인 요소들

| 구성 요소 | 역할 및 특징 |
| :--- | :--- |
| **Driver** | Java와 DB 사이의 통신 규칙을 담당한다. |
| **Connection** | DB와의 실제 연결 통로로, SQL 실행 전 반드시 생성되어야 한다. |
| **PreparedStatement** | SQL을 DB에 전달한다. SQL Injection을 방지하고 캐싱 효과로 성능이 좋다. |
| **ResultSet** | `SELECT` 쿼리 실행 결과를 담는 객체 |
| **Close** | 사용이 끝난 자원을 반납한다. 순서는 `ResultSet` -> `Statement` -> `Connection` |


#### 정리
`Driver` -> `Connection` -> `PreparedStatement` -> `ResultSet` 구조를 통해 SQL 기반의 데이터를 처리하는 미들웨어 역할을 수행한다.

---

### MyBatis란?
JDBC를 편리하게 사용하기 위한 프레임워크. JDBC만 사용하면 코드가 길어지고 유지보수가 어렵기 때문에 사용

* **반복 코드 제거**: `try-catch-finally`, `Connection` 생성 및 `Close` 등 반복되는 코드를 내부적으로 처리
* **SQL 분리**: Java 코드 안에 문자열로 존재하던 SQL을 별도의 XML 파일로 분리
* **자동 매핑**: `ResultSet`에서 데이터를 하나하나 꺼내 객체에 담는 과정을 자동으로 수행

#### PreparedStatement
변수를 바인딩할 때 `#{value}` 사용하여 SQL Injection 방지
