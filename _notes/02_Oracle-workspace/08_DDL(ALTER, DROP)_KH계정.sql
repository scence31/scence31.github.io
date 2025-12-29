/*
    <DDL - DATA DEFINITION LANGUAGE>
    
    - 데이터 정의 언어
    - 데이터베이스를 이루는 
    구조물들을(객체 OBJECT) 새로 생성(CREATE), 수정(ALTER), 삭제(DROP) 하는 구문
    
    * UPDATE, DELETE // ALTER, DROP 헷갈리지 않기
    => 데이터 내용물 // 테이블 구조 수정, 삭제
*/

/*
    1. ALTER
    
    - 객체 구조를 수정하는 구문
    <테이블 수정 - ALTER TABLE>
    
    [표현법]
    
    ALTER TABLE 테이블명 수정할내용;
    
    - 수정할내용
    1) 컬럼 추가/수정/삭제
    2) 제약조건 추가/삭제 (수정은 불가능, 삭제 후 추가해야됨)
    3) 테이블명/컬럼명/제약조건명 변경
    
*/

-- 1) 컬럼 추가/수정/삭제
-- 1-1) 컬럼 추가 ADD
-- ADD 추가할컬럼명 자료형 DEFAULT 기본값
--=> DEFAULT 기본값은 생략가능

SELECT * FROM DEPT_COPY;

-- Q. CNAME 컬럼 추가
ALTER TABLE DEPT_COPY ADD CNAME VARCHAR(20);
--> 새로운 컬럼이 만들어지고 기본적으로 NULL 값으로 채워짐

-- Q. LNAME 컬럼 추가(DEFAAULT 값 지정 후)
ALTER TABLE DEPT_COPY ADD LNAME VARCHAR(20) DEFAULT '한국';
--> 디폴트값으로 채워짐

-- 1-2) 컬럼 수정 MODIFY
-- 자료형 수정: MODIFY 수정할컬럼명 바꿀자료형
-- 주의사항) 컬럼의 값타입과 다른타입으로 변경 불가능(문자->숫자, 사이즈 축소)

-- DEFAULT 값 수정: MODIFY 수정할컬럼명 DEFAULT 바꿀기본값

-- Q. DEPT_COPY 테이블의 DEPT_ID 컬럼의 데이터타입을 CHAR(3)으로 변경
ALTER TABLE DEPT_COPY MODIFY DEPT_ID CHAR(3);

ALTER TABLE DPT_COPY MODIFY DEPT_TITLE VARCHAR2(8);

-- Q. DEPT_COPY 테이블의 DEPT_TITLE 컬럼 자료형을 VARCHAR2(40)으로,
-- LOCATION_ID 컬럼의 자료형을 VARCHAR2(2)로 변경

ALTER TABLE DEPT_COPY MODIFY DEPT_TITLE VARCHAR2(40) 
                      MODIFY LOCATION_ID VARCHAR2(2);
-- 이어서 작성 가능

-- Q. LNAME 컬럼 기본값을 '미국'으로 변경
ALTER TABLE DEPT_COPY MODIFY LNAME DEFAULT '미국';
--> 디폴트 설정값이 수정되더라도 실제 들어있는 데이터는 변경되지 않음.
SELECT * FROM DEPT_COPY;

-- 1-3) 컬럼삭제 DROP COLIMN
-- DROP COLUMN 삭제할컬럼명
CREATE TABLE DEPT_COPY2
AS (
        SELECT *
          FROM DEPT_COPY
          );

ALTER TABLE DEPT_COPY2 DROP COLUMN CNAME;
ALTER TABLE DEPT_COPY2 DROP COLUMN LNAME;
ALTER TABLE DEPT_COPY2 DROP COLUMN DEPT_TITLE;
ALTER TABLE DEPT_COPY2 DROP COLUMN DEPT_ID;
ALTER TABLE DEPT_COPY2 DROP COLUMN LOCATION_ID;
-- 하나는 있어야댐
SELECT * FROM DEPT_COPY2;

/*
    2-1) 제약조건 추가 ADD
    
    - PRIMARY KEY: ADD PRIMARY KEY(컬럼명)
    
    - FOREIGN KEY: ADD FOREIGN KEY(컬럼명) REFERENCES 참조할부모테이블명(컬럼명)
    
    - UNIQUE: ADD UNIQUE(컬럼명)
    
    - CHECK: ADD CHECK(컬럼에대한조건식)
    
    - NOT NULL: MODIFY 컬럼명 NOT NULL
    
    => 제약조건 추가시 나만의 제약조건명을 한번에 부여하고자 한다면
    제약조건 앞에 CONSTRAINT 제약조건명을 기술하면 됨
    단, 한 "계정" 안에 제약조건명이 중복될 수 없음(현재 계정 내에 없는 이름으로 기술해야함)
    => 이미 해당 컬럼에 들어있는 값에 대해서 알맞은 제약조건 종류로 추가해야함
    
*/

-- Q. DEPT_COPY 테이블에, DEPT_ID 컬럼에 기본키 제약조건, DEPT_TITLE에 유니크 제약조건,
-- LNAME 컬럼에 낫널 제약조건 추가
ALTER TABLE DEPT_COPY ADD CONSTRAINT DCOPY_PK PRIMARY KEY(DEPT_ID)
                      ADD CONSTRAINT DCOPY_UQ UNIQUE(DEPT_TITLE)
                      MODIFY LNAME CONSTRAINT DCOPY_NN NOT NULL;

-- 한 계정 내에서만 가능, 다른 테이블이라도 안됨.
-- 이미 해당 컬럼에 들어있는 값 기준으로 제약조건을 추가해야됨
--> 예를들어 CNAME 컬럼에 NULL 값이 있는데, NOT NULL 제약조건 못함.

/*
    2-2) 제약조건 삭제(DROP CONSTRAINT)
    
    DROP CONSTRAINT 제약조건명
    
    NOT NULL일 경우는 MODIFY 컬럼명 NULL
    
    한번에도 삭제 가능
*/

-- Q. DEPT_COPY 테이블로부터 DROP_PK 제약조건 삭제
ALTER TABLE DEPT_COPY DROP CONSTRAINT DCOPY_PK;

-- Q. NOT NULL 삭제
ALTER TABLE DEPT_COPY MODIFY LNAME NULL;
--> MODIFY 말고 제약조건명 제시해서도 삭제 가능함!!!!
ALTER TABLE DEPT_COPY DROP CONSTRAINT SYS_C007106;

-- 3) 컬럼명/제약조건명/테이블명 변경 RENAME
-- 3-1) 컬럼명 변경
-- RENAME COLUMN 기존컬럼명 TO 바꿀컬럼명
ALTER TABLE DEPT_COPY RENAME COLUMN DEPT_TITLE TO DEPT_NAME;
-- 주의사항) 한 테이블 내 중복 컬럼명 당연히 안돼

-- 3-2) wpdirwhrjsaud qusrud
-- RENAME CONSTRAINT 기존제약조건명 TO 바꿀제약조건명
ALTER TABLE EMPLOYEE_COPY4 RENAME CONSTRAINT SYS_C007095 TO ECOPY_ID_NN;
-- 주의사항) 한 계정 내 변경 불가! 계정!!!

-- 3-3) 테이블명 변경
-- RENAME TO 바꿀테이블명
ALTER TABLE DEPT_COPY RENAME TO DEPT_TEST;

SELECT * FROM DEPT_TEST;

-- 오라클 객체를 삭제하는 구문
-- 테이블 삭제: DROP TABLE 테이블명

-- 계정 삭제: DROP USER 계정명;
-- DELETE FROM 테이블명 vs DROP FROM 테이블명 => 데이터 삭제 / 구조 삭제 SELECT 조회 가능 여부

-- DROP TABLE 할 때 참조되고 있는 부모테이블 있으면 삭제 안됨
-- 하고싶으면 먼저 자식 삭제, 그다음 부모테이블 삭제

