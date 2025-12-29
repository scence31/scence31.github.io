/*
    * DB 구축을 위한 스크립트 작성
    
    - 위에서부터 아래로 명령문들이 한 줄씩 읽히며 실행되는 구조
    - 처음부터 신중하게 끝까지 쓰고 한 번만 실행하면 계정 내 테이블, 시퀀스 등 구축됨
    
    
    * 스크립트 작성시 주의사항
    
    1) DROP TABLE, DROP SEQUENCE 등 DROP 구문을 스크립트 파일 상단에 먼저 작성하기.
    테이블 또는 시퀀스 등 객체명이 중복되어 스크립트 실행 중간 오류발생 가능성을 줄이기 위함.
    자식테이블 DROP문 --> 부모테이블 DROP문 순서로 적는다.
    
    2) 테이블 CREATE 구문은 부모테이블 --> 자식테이블 순서로 적는다.(외래키 제약조건 때문)
    
*/

DROP TABLE MEMBER;
DROP SEQUENCE SEQ_USERNO;

CREATE TABLE MEMBER(

    USERNO NUMBER CONSTRAINT MEM_NO_PK PRIMARY KEY,
    USERID VARCHAR2(15) NOT NULL UNIQUE,
    USERPWD VARCHAR2(20) NOT NULL,
    USERNAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(1) CHECK(GENDER IN('M', 'F')),
    AGE NUMBER,
    EMAIL VARCHAR2(30),
    PHONE CHAR(11),
    ADDRESS VARCHAR2(100),
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
    
);
DROP TABLE MEMBER;
CREATE SEQUENCE SEQ_USERNO
NOCACHE;
DROP SEQUENCE SEQ_USERNO;

-- 개발 단계에서는 만들어진 테이블에 샘플데이터를 몇 개정도 넣고들어가는게 좋음
--> 샘플데이터 == 더미데이터

INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL
                        , 'admin'
                        , '1234'
                        , '관리자'
                        , 'M'
                        , 45
                        , 'admin@naver.com'
                        , '01012341111'
                        , '서울시 마포구'
                        , '2021/01/25');

INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL
                        , 'user01'
                        , 'pass01'
                        , '홍길녀'
                        , 'F'
                        , 23
                        , NULL
                        , '01067895555'
                        , NULL
                        , '2021/07/12');

-- 항상 스크립트 구문 안에 DML문이 포함되었다면 끝에는 COMMIT; 마무리하기.
COMMIT;

SELECT * FROM MEMBER;

---------------------------------------------------------------------------

-- 여기서부터는 실행하지 않을 것.
-- 1. 회원추가용 쿼리문
INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL
                        , 'XXX'
                        , 'XXXX'
                        , 'XXX'
                        , 'X'
                        , XX
                        , 'XXXXXXXX'
                        , 'XXXXXX'
                        , 'XXXXXXXXXXX'
                        , DEFAULT);

-- 2. 회원 조회용 쿼리문
SELECT * FROM MEMBER;

-- 3. 회원 아이디 검색용 쿼리문

SELECT *
  FROM MEMBER
 WHERE USERID = 'XXX';

-- 4. 회원 이름 키워드 검색용 쿼리문
SELECT *
  FROM MEMBER
 WHERE USERNAME LIKE '%XXX%';

-- 5. 회원정보 수정용(UPDATE)쿼리문
UPDATE MEMBER
   SET USERPWD = 'XXXX'
     , EMAIL = 'XXXXX'
     , PHONE = 'XXXXXX"
     , ADDRESS = 'XXXXX'
 WHERE USERID = 'XXX';

-- 6. 회원탈퇴용 쿼리문
DELETE 
  FROM MEMBER
 WHERE USERID = 'XXX';

-- 상품관리 프로그램 --

CREATE TABLE PRODUCT (
    PRODUCT_ID VARCHAR2(30) CONSTRAINT PR_ID_PH PRIMARY KEY,
    PRODUCT_NAME VARCHAR2(50) CONSTRAINT PR_NAME_NN NOT NULL,
    PRODUCT_PRICE NUMBER CONSTRAINT PR_PR_NN NOT NULL,
    DESCRIPTION VARCHAR2(300),
    STOCK NUMBER CONSTRAINT PR_ST_NN NOT NULL
    
    );


SELECT * FROM PRODUCT;

INSERT INTO PRODUCT VALUES('nb_ss7', '삼성노트북', 1570000, '시리즈7', 10);

COMMIT;

DROP TABLE TB_PRODUCT;

CREATE TABLE TB_PRODUCT (
    PR_CODE VARCHAR2(20) CONSTRAINT P_CO_PK PRIMARY KEY,
    PR_BRAND VARCHAR2(20) CONSTRAINT P_BR_NN NOT NULL,
    PR_NAME VARCHAR2(30) CONSTRAINT P_NA_NN NOT NULL,
    PR_PRICE NUMBER CONSTRAINT P_PR_NN NOT NULL,
    PR_STOCK NUMBER CONSTRAINT P_ST_NN NOT NULL
    );

COMMENT ON COLUMN TB_PRODUCT.PR_CODE IS '코드';
COMMENT ON COLUMN TB_PRODUCT.PR_BRAND IS '브랜드';
COMMENT ON COLUMN TB_PRODUCT.PR_NAME IS '품명';
COMMENT ON COLUMN TB_PRODUCT.PR_PRICE IS '가격';
COMMENT ON COLUMN TB_PRODUCT.PR_STOCK IS '재고량';

INSERT INTO TB_PRODUCT VALUES('a1324', '애플', '맥북프로', 2500000, 100)

COMMIT;

SELECT * FROM TB_PRODUCT;

DROP TABLE TB_SHOES

CREATE TABLE TB_SHOES (
    SH_CODE VARCHAR2(30) CONSTRAINT SH_CO_PK PRIMARY KEY,
    SH_NAME VARCHAR2(30) CONSTRAINT SH_NA_NN NOT NULL,
    BRAND VARCHAR2(30) CONSTRAINT SH_BR_NN NOT NULL,
    PRICE NUMBER CONSTRAINT SH_PR_NN NOT NULL,
    STOCK NUMBER CONSTRAINT SH_STO_NN NOT NULL,
    STATUS CHAR(1) CONSTRAINT SH_STA_CH CHECK(STATUS IN('Y', 'N'))
    );

COMMENT ON COLUMN TB_SHOES.SH_CODE IS '코드';
COMMENT ON COLUMN TB_SHOES.SH_NAME IS '품명';
COMMENT ON COLUMN TB_SHOES.BRAND IS '브랜드';
COMMENT ON COLUMN TB_SHOES.PRICE IS '가격';
COMMENT ON COLUMN TB_SHOES.STOCK IS '재고량';
COMMENT ON COLUMN TB_SHOES.STATUS IS '판매여부';

INSERT INTO TB_SHOES VALUES('p123', '에어포스', '나이키', 110000, 10, 'Y');
INSERT INTO TB_SHOES VALUES('n1234', '뉴발503', '뉴발란스', 130000, 20, 'N');


SELECT * FROM TB_SHOES;

COMMIT;


