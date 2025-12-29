/*
    * DDL(DATA DEFINITION LANGUAGE)
    
    - 데이터 정의 언어
    - 오라클에서 제공하는 객체(OBJECT)를 새로이 만들고(CREATE), 구조를 변경하고(ALTER),
      구조 자체를 삭제하는(DROP) 명령문
    - 즉, 데이터베이스를 이루는 구조물 자체를 정의하는 언어.
      주로 DB 관리자, 설계자가 사용함
      
      * 오라클의 객체(OBJECT)
      - 오라클 데이터베이스를 이루고 있는 "구조물"
      
      [종류]
      테이블(TABLE), 뷰(VIEW), 시퀀스(SEQUENCE), 사용자(USER),
      인덱스(INDEX), 패키지(PACKAGE), 트리거(TRIGGER),
      프로시저(PROCEDURE), 함수(FUNCTION),
      동의어(SYNONYM)
      
*/


/*
    <CREAT TABLE>
    
    * 테이블(TABLE)
    - 행(ROW), 열(COLUMN)로 구성되는 가장 기본적인 데이터베이스의 객체(구조물)
    - 모든 데이터는 항상 테이블을 통해서 저장됨(데이터 저장에 테이블 생성 필수)
    
    - 한 테이블 안에서 컬럼명 중복 불가능
    - 테이블명은 한 계정 내에서 중복 불가능
    
    [표현법]
    
    CREATE TABLE 테이블명 (
        컬럼명 CHAR(),
        컬럼명 DATE,
        ...,
        컬럼명 자료형
    );
    
    <오라클의 자료형>
    
    - 문자 CHAR(크기) / VARCHAR2(크기) / CROB
    : 크기는 BYTE 단위로 지정함(문자와 문자열 차이 없음)
      숫자, 영문자, 특수문자 => 한 글자당 1BYTE 취급
      한글                 => 3BYTE 취급
    
      1. CHAR(2000): 최대 2000BYTE까지 지정 가능, 고정길이
      고정길이: 아무리 적은 값이 들어와도 공백으로 채워 처음 할당한 크기 유지하겠다.
      ex) CHAR(10) --> '김치' => '김치    ' 원래 6바이트, 공백 10바이트.
      ==> 들어올 값의 글자수가 딱 정해져 있을 경우 쓰는게 유리함
      ex) 성별(남/여 CHAR(3) ), 전화번호, 주민등록번호
      
      
      2. VARCHAR2(4000): 최대 4000BYTE까지 지정 가능, 가변길이
      가변길이: 적은 값이 들어오면 그 값에 맞춰 저장공간 크기가 줄어듦
      ex) VARCHAR2(10) --> '김치' => 원래 10BYTE지만 6BYTE로. 근데 10 오버하면 오류
      ==> 글자수, 데이터 용량이 가늠되지 않는 경우 유리함
      ex) 아이디/비밀번호, 게시글 제목/내용 등
      
      VARCHAR2 - VARIABLE(가변) + 2(두 배)
      
      
      3. CROB: 최대 128TB까지 저장 가능(대용량 문자 자료형), 대체로 문자열 관련 함수 불가
      
      
      - 숫자(NUMBER)
      : 정수, 실수 상관없이 모두 다 보관 가능
      
      - 날짜(DATE)
      :연, 월, 일, 시, 분, 초 모두 다 포함한 자료형
*/

--> 회원들의 데이터 (아이디, 비번, 이름, 회원가입일) 담기 위한 MEMBER 테이블 생성
CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(20),
    MEMBER_PWD VARCHAR2(20),
    MEMBER_NAME VARCHAR2(20),
    MEMBER_DATE DATE
);
--> 대소문자 구분x 언더바로 구분. 문자열 사이즈는 넉넉하게 하는 것이 좋음

-- 테이블이 잘 만들어졌나 확인하는법
-- 1) 접속탭 이용해서 직접 확인
-- 2) SELECT문 활용
SELECT * FROM MEMBER;
-- 3) 데이터 딕셔너리 활용
-- 데이터 딕셔너리: 다양한 객체들의 정보를 저장하고 있는 시스템 테이블(오직 조회 용도)
--> USER_TABLES: 현재 이 계정이 갖고 있는 테이블들의 전반적인 정보를 담고 있는 시스템 테이블(데이터 딕셔너리)
SELECT * FROM USER_TABLES;


-- USER_TAB_COLUMNS: 현재 이 계정이 가지고 있는 테이블들의 모든 컬럼 정보를 담고 있는 시스템 테이블(데이터 딕셔너리) 조회
SELECT * FROM USER_TAB_COLUMNS

/*
    컬럼에 주석달기(권장사항)
    COMMENT ON COLUMN 테이블명.컬럼명 IS '주석내용';
*/

COMMENT ON COLUMN MEMBER.MEMBER_ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.MEMBER_PWD IS '회원비밀번호';
COMMENT ON COLUMN MEMBER.MEMBER_NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.MEMBER_DATE IS '회원가입일';

-- MEMBER 테이블에 데이터 넣어보기~
-- 테이블에 데이터를 추가할 수 있는 구문: INSERT문(DML의 한 종류)
-- [표현법]
-- INSERT INTO 테이블명 VALUES (첫번째컬럼값, 두번째컬럼값, ... 마지막);
-- 한 행 단위로 데이터 추가, 값의 기술순서가 매우 중요
INSERT INTO MEMBER VALUES('user01', 'pass01', '홍길동', '2021-02-01');
INSERT INTO MEMBER VALUES('user02', 'pass02', '김말똥', '2021-02-02');

INSERT INTO MEMBER VALUES('user03', 'pass03', '김갑생', SYSDATE);

INSERT INTO MEMBER VALUES(NULL, NULL, NULL, SYSDATE);
--> 회원가입 시 아이디, 비번, 이름은 보통 필수입력사항임
--> 즉 NULL 값이 존재하면 안돼

INSERT INTO MEMBER VALUES('user03', 'pass04', '박말순', SYSDATE);
--> 회원가입 시 아이디는 보통 중복확인 절차를 마쳐야됨
-- 위의 NULL값이나 중복된 아이디값은 "유효하지 않은 값들"이라고 함
-- 항상 테이블에 유효한 데이터값들을 유지하기 위해서는 "제약조건"을 걸어줘야 함

------------------------------------------------------------------------------

/*
    <제약조건 CONSTRAINTS>
    
    - 원하는 데이터값만 항상 유지하기 위해 특정 컬럼마다 설정하는 제약
    - 데이터 무결성 보장 목적으로 사용(유효하지 않은 데이터 거부)
    => 제약조건이 부여된 컬럼에 들어올 데이터에 문제가 있나 없나 자동으로 검사해줄 목적
      (애초에 데이터에 문제 없다면 해당 컬럼에 값이 잘 들어감)
      
      [종류]
      NOT NULL, UNIQUE, CHECK, PRIMARY KEY, FOREIGN KEY
      
      - 컬럼에 제약조건을 부여하는 방식: 컬럼레벨방식/테이블레벨방식
      
      1) 컬럼레벨방식: 제약조건을 부여하고자 하는 컬럼 뒤에 곧바로 기술하는 방법
      [표현법]
      컬럼명 자료형 제약조건
      
      2) 테이블레벨방식: 컬럼명들을 모두 나열 후 마지막에 어느 컬럼에 어떤 제약조건을 할지 연이어 나열하는 방식
      
      
*/

/*
    1. NOT NULL 제약조건
    
    - 해당 컬럼에 반드시 값이 존재해아하는 경우에 사용(NULL 없어야됨)
    - NOT NULL은 컬럼레벨방식으로만 부여 가능
*/

--> MEMBER 테이블을 보완할 MEM_NOTNULL 테이블 생성
-- 아이디, 비번, 이름 컬럼에 NOTNULL 추가한 형태로 새로 생성
CREATE TABLE MEM_NOTNULL (
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(20) NOT NULL,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30)
);

-- 테이블 만들어졌는지 확인. 둘 중 하나 하셈
SELECT * FROM MEM_NOTNULL;
SELECT * FROM USER_TABLES;

-- 회원정보 INSERT 해보기
INSERT INTO MEM_NOTNULL VALUES(1
                             , 'user01'
                             , 'pass01'
                             , '홍길동'
                             , '남'
                             , '010-1234-1234'
                             , 'user01@naver.com');
                             
INSERT INTO MEM_NOTNULL VALUES(2
                             , NULL
                             , NULL
                             , NULL
                             , NULL
                             , NULL
                             , NULL);
--> NOT NULL 제약조건에 위배되어 오류 발생
-- CANNOT INSERT NULL INTO 계정명.테이블명.컬럼명

INSERT INTO MEM_NOTNULL VALUES(2
                             , 'user02'
                             , 'pass02'
                             , '김갑생'
                             , NULL
                             , NULL
                             , NULL);

INSERT INTO MEM_NOTNULL VALUES(3
                             , 'user01'
                             , 'pass03'
                             , '박말순'
                             , '여'
                             , NULL
                             , NULL);
--> NOT NULL 제약조건 성공. 근데 중복값은 아직 못막음

------------------------------------------------------------------------------

/*
    2. UNIQUE 제약조건
    
    - 컬럼에 중복값을 제한하는 제약조건
    - 데이터 삽입, 수정시 기존의 컬럼값 중에 중복값이 있을 경우에,
    추가 또는 수정되지 않게 제약함
    - 컬럼레벨방식/테이블레벨방식 모두 사용 가능
*/

--> 위의 MEM_NOTNULL 테이블을 보완할 MEM_UNIQUE 테이블 생성
-- 아이디 중복값 막기 위한 UNIQUE 제약조건 추가
CREATE TABLE MEM_UNIQUE (
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,  -- 컬럼레벨방식
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30)
);
--> 한 컬럼에 여러 개의 제약조건을 걸 수 있다.
--> 컬럼레벨방식의 경우 띄어쓰기로 구분해서 나열한다

-- MEM_UNIQUE 테이블 삭제(DROP) 후 테이블레벨방식으로 재생성
-- 삭제(DROP): DROP TABLE 테이블명;
DROP TABLE MEM_UNIQUE;

-- 테이블레벨방식으로 생성
CREATE TABLE MEM_UNIQUE (
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(20) NOT NULL,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL, -- NOT NULL은 컬럼레벨방식만 가능
    GENDER CHAR(3),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30), -- 여기까지가 컬럼들을 나열한 부분
    UNIQUE (MEM_ID)     -- 제약조건을 나열한 부분(테이블레벨방식)
);

INSERT INTO MEM_UNIQUE VALUES(1
                            , 'user01'
                            , 'pass01'
                            , '홍길동'
                            , '남'
                            , '010-1234-1234'
                            , 'user01@naver.com');
                            
INSERT INTO MEM_UNIQUE VALUES(2
                            , 'user02'
                            , 'pass02'
                            , '김갑생'
                            , NULL
                            , NULL
                            , NULL);
                            


INSERT INTO MEM_UNIQUE VALUES(3
                            , 'user02'
                            , 'pass03'
                            , '박말순'
                            , NULL
                            , NULL
                            , NULL);

--> UNIQYE 제약조건에 위배되어 INSERT 실패
--> 오류: UNIQUE CONSTRAINT (계정명.제약조건명) VIOLATED
--> 그냥 제약조건이 위배되었다고만 알려주지만 제약조건 명을 통해 어디가 오류인지 알려줌
-- DDL.SYS.C007030 => 제약조건 부여시 직접 지정 안하면 내부적으로 임의로 명 부여해줌

/*
    * 제약조건 부여 시 제약조건명도 지정하는 표현법
    
    1) 컬럼레벨방식
    CREATE TABLE 테이블명 (
        컬럼명 자료형 CONSTRAINT 제약조건명 제약조건,
        컬럼명 자료형,
        ...
    );
    
    2_ 테이블레벨방식
    CREATE TABLE 테이블명 (
        컬럼명 자료형,
        컬럼명 자료형,
        ...,
        CONSTRAINT 제약조건명 제약조건(컬럼명),
        CONSTRAINT 제약조건명 제약조건(컬럼명),
        ...
    );
    
    두 방식 모두 CONSTRAINT 제약조건명을 생략 가능
    단, 생략시 제약조건명이 임의로 SYS_C~~~로 됨
    
    - 제약조건명 지을 때 팁
    테이블명_컬럼명_제약조건약자 -> 키워드 조합해서 짓기
    
    EX) MEMBER 테이블의 UNIQUE 제약조건을 MEM_ID 컬럼에 부여했을 경우
    제약조건명: MEM_ID_UQ
    
    EX) MEMBER 테이블의 NOT NULL 제약조건을 MEM_PWD 컬럼에 부여했을 경우
    제약조건명: MEM_PWD_NN
    
*/

--> MEM_UNIQUE 테이블 보완해보기. 구조는 그대로, 제약조건에 이름만 붙일 것
CREATE TABLE MEM_CON_NM (
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(20) NOT NULL,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) CONSTRAINT MEM_NAME_NN NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30),
    CONSTRAINT MEM_ID_UQ UNIQUE (MEM_ID)
);

-- 제약조건명, 코멘트, ...

INSERT INTO MEM_CON_NM VALUES(1
                            , 'user01'
                            , 'pass01'
                            , '홍길동'
                            , NULL
                            , NULL
                            , NULL);
                            
INSERT INTO MEM_CON_NM VALUES(2
                            , 'user01'
                            , 'pass02'
                            , '박말순'
                            , NULL
                            , NULL
                            , NULL);

--> 제약조건명 지정 후 오류구문 발생시 어디서 발생한 것인지 알 수 있음

SELECT * FROM MEM_CON_NM;

INSERT INTO MEM_CON_NM VALUES(2
                            , 'user02'
                            , 'pass02'
                            , '김말똥'
                            , '가'
                            , NULL
                            , NULL);

--> GENDER 컬럼에 '남' 또는 '여'만 들어가게 하고싶음
-- 아래 3. CHECK 제약조건 확인
-----------------------------------------------------------------------------
/*
    3. CHECK 제약조건
    
    - 컬럼에 기록될 수 있는 값에 대한 '조건식'을 설정해줄 수 있는 제약조건
    - 즉, 조건식을 직접 제시해서 그 조건에 맞는 값만 해당 컬럼에 넣어주겠다.
    
    [표현법]
    CHECK(해당컬럼에대한조건식)
*/

--> 위의 테이블 보완 GENDER에 CHECK 제약조건 부여한 후 생성
CREATE TABLE MEM_CHECK (
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')), --컬럼레벨방식
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30),
    MEM_DATE DATE NOT NULL
);
INSERT INTO MEM_CHECK VALUES(1
                           , 'user01'
                           , 'pass01'
                           , '홍길동'
                           , '남'
                           , '010-1234-1234'
                           , 'user01@naver.com'
                           , SYSDATE);
                           
INSERT INTO MEM_CHECK VALUES(1
                           , 'user02'
                           , 'pass02'
                           , '김갑생'
                           , NULL
                           , NULL
                           , NULL
                           , SYSDATE);

--> CHECK 제약조건에 NULL 값도 존재 가능함, 막고싶으면 NOT NULL도 해주면 돼

INSERT INTO MEM_CHECK VALUES(1
                           , 'user02'
                           , 'pass02'
                           , '김갑생'
                           , '가'
                           , NULL
                           , NULL
                           , SYSDATE);

--> CHECK 제약조건 또한 위배되었을 때 제약조건명으로만 오류 알려줌.
--> CONSTRAINT 하자, MEM_GENDER_CK 이런식으로


/*
    * 특정 컬럼에 들어갈 값에 대한 '기본값' 지정 가능
    ★ DEFAULT 설정(제약조건은 아님, 단순 설정임)
*/

-- MEM_CHECK 테이블 삭제 후 재생성
DROP TABLE MEM_CHECK;
SELECT * FROM MEM_CHECK;

-- 회원가입일을 항상 INSERT 되는 시점의 SYSDATE 값으로 보완한 테이블 생성
CREATE TABLE MEM_CHECK (
    MEM_NO NUMBER NOT NULL,
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR(20) NOT NULL,
    GENDER CHAR(3) NOT NULL CHECK(GENDER IN ('남', '여')),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30),
    MEM_DATE DATE DEFAULT SYSDATE NOT NULL
);

/*
    * 데이터 삽입시 일부 데이터만 지정해서 넣는 방법
    
    INSERT INTO 테이블명(컬럼명1, 컬럼명2, 컬럼명3)
                 VALUES(컬럼값1, 컬럼값2, 컬럼값3);
*/

INSERT INTO MEM_CHECK(MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GENDER)
               VALUES(1, 'user01', 'pass01', '고길동', '남');
--> 디폴트가 지정되지 않은 컬럼은 기본적으로 NNULL값 삽입, 설정한 것은 디폴트값 삽입

INSERT INTO MEM_CHECK(MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GENDER, MEM_DATE)
               VALUES(1, 'user02', 'pass02', '박말순', '여', '19/11/09');

SELECT * FROM MEM_CHECK;
--> DEFAULT가 제약설정이 아닌 이유, 디폴트 설정했다고 무조건 디폴트값이 들어가는 건 아님

------------------------------------------------------------------------------
/*
    4. PRIMARY KEY(기본키, 주키) 제약조건
    
    - 테이블에서 각 행들의 정보를 유일하게 식별할 수 있는 컬럼에 부여하는 제약조건
    - 즉, 각 행들을 구분할 수 있는 식별자 역할
    (HASHMAP의 KEY 같은 존재)
    - 값이 중복되지 않고 무조건 존재해야만하는 컬럼에 PRIMARY KEY 제약조건을 부여함
    (NOT NULL + UNIQUE 느낌)
    
    - 한 테이블 당 한 개만 지정 가능. (최소성)
    
    EX) 회원번호, 주문번호, 사번, 학번, 예약번호, ...
    => 각 건수별로 하나씩 식별/구분하기 위해 NULL이 아닌 중복되지 않은 값을 하나씩 부여
      (추후 한 건만 정확하게 조회/수정/삭제하기 위해서)
    
*/

--> 또 테이블 보완, PRIMARY KEY 제약조건 추가 MEM_PRIMARYKEY1 테이블 생성
-- MEM_NO 컬럼에 PRIMARY KEY 제약조건 부여
CREATE TABLE MEM_PRIMARYKEY1 (
    MEM_NO NUMBER CONSTRAINT MEM_PK PRIMARY KEY, --컬럼레벨방식
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN('남', '여')),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30)
    -- CONSTRAINT MEM_PK PRIMARY KEY(MEM_ID) -- 테이블레벨방식
);
--> 사실 MEM_ID도 PRIMARY KEY가 될 수 있는 컬럼임
-- 회원들을 유일하게 식별 가능한 컬럼들(후보키 CANDIDATE KEY): MEM_NO, MEM_ID
--> 후보키들 중 적절한 것 선택해서 주면 됨.

INSERT INTO MEM_PRIMARYKEY1 VALUES(1
                                 , 'user01'
                                 , 'pass01'
                                 , '홍길동'
                                 , '남'
                                 , NULL
                                 , NULL);

INSERT INTO MEM_PRIMARYKEY1 VALUES(1
                                 , 'user02'
                                 , 'pass02'
                                 , '박말순'
                                 , NULL
                                 , NULL
                                 , NULL);

--> 오류발생: unique constraint violated

INSERT INTO MEM_PRIMARYKEY1 VALUES(NULL
                                 , 'user02'
                                 , 'pass02'
                                 , '김갑생'
                                 , NULL
                                 , NULL
                                 , NULL);

--> 오류발생: cannot insert NULL into "DDL"."MEM_PRIMARYKEY1"."MEM_NO"

SELECT * FROM MEM_PRIMARYKEY1;

INSERT INTO MEM_PRIMARYKEY1 VALUES(2
                                 , 'user02'
                                 , 'pass02'
                                 , '김갑생'
                                 , '여'
                                 , '010-1111-2222'
                                 , 'user02@gamil.com');

-->> MEM_PRIMARYKEY2 테이블 생성해보기
CREATE TABLE MEM_PRIMARYKEY2 (
    MEM_NO NUMBER PRIMARY KEY,
    MEM_ID VARCHAR2(20) PRIMARY KEY,
    MEM_PWD VARCHAR2(20) NOT NULl,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN('남', '여')),
    PHONE VARCHAR(15),
    EMAIL VARCHAR(30)
);
--> 오류 발생: table can have only one primary key
--> 단, 두 개 이상의 컬럼을 하나로 묶어 PK 하나로 설정은 가능함
-- 두 개 이상의 컬럼을 하나로 묶어서 PRIMARY KEY로 지정하는 경우: 복합키

CREATE TABLE MEM_PRIMARYKEY2 (
    MEM_NO NUMBER,
    MEM_ID VARCHAR2(20),
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN('남', '여')),
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30),
    PRIMARY KEY(MEM_NO, MEM_ID) -- 컬럼을 하나로 묶어서 PKEY 하나로 설정 가능(복합키)
    );
-- 복합키는 테이블레벨방식밖에 안됨.

INSERT INTO MEM_PRIMARYKEY2 VALUES(1
                                 , 'user01'
                                 , 'pass01'
                                 , '홍길동'
                                 , NULL
                                 , NULL
                                 , NULL);

INSERT INTO MEM_PRIMARYKEY2 VALUES(1
                                 , 'user02'
                                 , 'pass02'
                                 , '김갑생'
                                 , NULL
                                 , NULL
                                 , NULL);
--> 회원번호가 중복됨에도 불구하고 제대로 INSERT 됨

INSERT INTO MEM_PRIMARYKEY2 VALUES(2
                                 , 'user02'
                                 , 'pass03'
                                 , '고영희'
                                 , NULL
                                 , NULL
                                 , NULL);
--> ID 중복됨에도 INSERT 됨
SELECT * FROM MEM_PRIMARYKEY2;

INSERT INTO MEM_PRIMARYKEY2 VALUES(2
                                 , 'user02'
                                 , 'pass04'
                                 , '박말순'
                                 , NULL
                                 , NULL
                                 , NULL);
                                 
--> 복합키로 설정되어있는 컬럼은 여러개로 묶인 값이 모두 동일해야 중복으로 판별됨

INSERT INTO MEM_PRIMARYKEY2 VALUES(3
                                 , NULL
                                 , 'pass05'
                                 , '이순신'
                                 , NULL
                                 , NULL
                                 , NULL);

--> 복합기로 설정되어있는 컬럼에는 NULL값이 하나일지라도 들어갈 수 없음
-- 위의 중복이랑 좀 다름(모두 vs 하나)

--> 물론 여러개의 컬럼을 묶어서 하나의 PRIMARY KEY로 둘 수 있지만(복합키)
-- PRIMARY KEY를 위한 컬럼을 임의로 하나 만들어서 그걸로 PK 두는 것이 편함
-- PRIMARY KEY를 위한 컬럼: 인위적 식별자

----------------------------------------------------------------------------
/*
    5. FOREIGN KEY 제약조건(외래키)
    
    - 해당 컬럼에 다른 테이블에 존재하는 값이 들어와야 할 경우 그 컬럼에 부여하는 제약조건
    - 즉, 다른테이블로부터 데이터를 땡겨 쓰겠다.
    - 다른 테이블을(부모테이블) 참조한다고 표현함
    - 즉 참조된 다른 테이블(부모테이블)이 제공하는 컬럼값만 들어갈 수 있는 제약조건
    - FOREIGN KEY 제약조건으로 다른 테이블 간 관계를 형성할 수 있음(JOIN 가능해짐, 연결고리로 쓰겠다.)
    
    ex) KH계정의 EMPLOYEE 테이블의 DEPT_CODE 컬럼에는 D1 ~ D9까지 값만 들어갈 수 있음
    D1 ~ D9 값은 사실 DEPARTMENT 테이블의 DEPT_ID 컬럼에 들어있음.
    => EMPLOYEE의 DEPT_CODE 컬럼에는 DEPARTMENT의 DEPT_ID 컬럼값만 들어갈 수 있음
    
    ex) EMPLOYEE 테이블의 JOB_CODE 컬럼에는 J1 ~ J7 값만 들어갈 수 있음.
    J1 ~ J7 값은 사실 JOB 테이블의 JOB_CDOE 컬럼에 들어있음
    => EMPLOYEE의 JOB_CDOE 커럼에는 JOB의 JOB_CODE 컬럼값만 들어갈 수 있음
    
    [표현법]
    1) 컬럼레벨방식
    컬럼명 자료형 CONSTRAINT 제약조건명 REFERENCES 참조할부모테이블명(참조할컬럼명)
    
    2) 테이블레벨방식
    CONSTRAINT 제약조건명 FOREIGN KEY(컬럼명) REFERENCES 참조할부모테이블명(참조할컬럼명)
    
    두 방식 모두, (참조할컬럼명) 생략은 가능하지만 생략하면 기본적으로
    참조할부모테이블의 PRIMARY KEY 제약조건 컬럼명으로 자동으로 잡히게 됨
*/

-- 명심할 사항: 항상 부모테이블을 먼저 생성해야됨
--> 부모테이블: 회원등급에 대한 데이터(등급코드, 등급명) 보관하는 테이블 MEM_GRADE 생성
CREATE TABLE MEM_GRADE(
    GRADE_CODE CHAR(2) PRIMARY KEY,
    GRADE_NAME VARCHAR2(20) NOT NULL
);
-- 회원등급정보 담기
INSERT INTO MEM_GRADE VALUES('G1', '일반회원');
INSERT INTO MEM_GRADE VALUES('G2', '우수회원');
INSERT INTO MEM_GRADE VALUES('G3', '특별회원');

--> 자식테이블: 기존회원정보를 담는 테이블에 등급 정보까지 포함한 테이블 MEM 생성
CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY,
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GRADE_ID CHAR(2) REFERENCES MEM_GRADE(GRADE_CODE), -- 컬럼레벨방식
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30)
    -- FOREIGN KEY(GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) -- 테이블레벨방식
    );

INSERT INTO MEM VALUES(1, 'user01', 'pass01', '홍길동', 'G1', NULL, NULL);

SELECT * FROM MEM;

INSERT INTO MEM VALUES(2, 'user02', 'pass02', '김갑생', 'G2', NULL, NULL);

INSERT INTO MEM VALUES(3, 'user03', 'pass03', '박말순', 'G1', NULL, NULL);

SELECT * FROM MEM;

INSERT INTO MEM VALUES(4, 'user04', 'pass04', '김말똥', NULL, NULL, NULL);

SELECT * FROM MEM;
--> 외래키는 제약조건이 걸린 컬럼에는 기본적으로 NULL 값이 들어갈 수 있음
-- 만약 NULL 값이 못들어오게 막고싶다면 NOT NULL 제약조건 추가하면 됨

INSERT INTO MEM VALUES(5, 'user05', 'pass05', 'G4', NULL, NULL);
--> 오류발생: parent key not found
-- G4는 부모테이블인 MEM_GRADE에 없는데 외래키 제약조건인 GRADE_ID 컬럼에 값을 넣으려함

SELECT * FROM MEM;

--> 반대상황은?: 부모테이블에 있는 G1 데이터를 지운다면? DELETE문(DML의 한 종류)
--[표현법]
-- DELETE FROM 테이블명 WHERE 조건식;
DELETE
  FROM MEM_GRADE
 WHERE GRADE_CODE = 'G1'
--> 오류발생: child record found
-- 자식테이블 MEM에서 G1을 사용하고 있어서 부모테이블 MEM_GRADE에 있는 데이터 삭제 불가

/*
    * 외래키 관계의 부모데이터를 함부로 지울 수 없음
    - 자식테이블 생성시(외래키 제약조건 설정시) 부모데이터가 삭제되었을 경우
    남은 자식데이터를 어떻게 처리할건지 옵션으로 지정할 수 있음
    
    * FOREIGN KEY 삭제옵션
   
    0) ON DELETE RESTRICTED: 기본적으로 지정됨, 가장 좋음 그냥 상황 자체를.
    -> 삭제제한
    
    1) ON DELETE SET NULL: 차선책임, 2)는 무분별 데이터 삭제가능성 있음.
    -> 부모데이터를 삭제했을 때 해당 데이터를 사용하는 자식데이터를 모두 NULL 처리하겠다.
    
    2) ON DELETE CASCADE
    -> 부모데이터를 삭제~ 자식데이터도 모두 삭제하겠다.

*/

-- 1) ON DELETE SET NULL
DROP TABLE MEM;

SELECT * FROM MEM_GRADE;

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY,
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GRADE_ID CHAR(2) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL,
    -- 컬럼레벨방식
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30)
    -- FOREIGN KEY(GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL
    -- 테이블레벨방식
    );
    
 --   2) 테이블레벨방식
 --   CONSTRAINT 제약조건명 FOREIGN KEY(컬럼명) REFERENCES 참조할부모테이블명(참조할컬럼명)

INSERT INTO MEM VALUES(1, 'user01', 'pass01', '홍길동', 'G1', NULL, NULL);

INSERT INTO MEM VALUES(2, 'user02', 'pass02', '김갑생', 'G2', NULL, NULL);

INSERT INTO MEM VALUES(3, 'user03', 'pass03', '박말순', 'G1', NULL, NULL);

INSERT INTO MEM VALUES(4, 'user04', 'pass04', '김말똥', NULL, NULL, NULL);

SELECT * FROM MEM;

DELETE
  FROM MEM_GRADE
 WHERE GRADE_CODE = 'G1';
 
SELECT * FROM MEM_GRADE;
SELECT * FROM MEM;
-- NULL로 대체됨

ROLLBACK;

SELECT * FROM MEM_GRADE;

SELECT * FROM MEM;

DROP TABLE MEM;

-- 2) ON DELETE CASCADE

CREATE TABLE MEM(
    MEM_NO NUMBER PRIMARY KEY,
    MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR2(20) NOT NULL,
    MEM_NAME VARCHAR2(20) NOT NULL,
    GRADE_ID CHAR(2), -- REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL,
    -- 컬럼레벨방식
    PHONE VARCHAR2(15),
    EMAIL VARCHAR2(30),
    FOREIGN KEY(GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE CASCADE
    -- 테이블레벨방식
    );
INSERT INTO MEM VALUES(1, 'user01', 'pass01', '홍길동', 'G1', NULL, NULL);
INSERT INTO MEM VALUES(2, 'user02', 'pass02', '김갑생', 'G2', NULL, NULL);
INSERT INTO MEM VALUES(3, 'user03', 'pass03', '박말순', 'G1', NULL, NULL);
INSERT INTO MEM VALUES(4, 'user04', 'pass04', '김말똥', NULL, NULL, NULL);

SELECT * FROM MEM;
SELECT * FROM MEM_GRADE;

DELETE
  FROM MEM_GRADE
 WHERE GRADE_CODE = 'G1';

SELECT * FROM MEM_GRADE;
SELECT * FROM MEM;
--> 부모데이터 삭제됨(자식데이터 행 기준으로 모두 삭제)

-- MEN 테이블로부터 전체회원 아이디, 비번, 이름, 등급명 조회
--> 오라클전용 구문
SELECT MEM_ID, MEM_PWD, MEM_NAME, NVL(GRADE_NAME, '등급미정')
  FROM MEM, MEM_GRADE
 WHERE GRADE_ID = GRADE_CODE(+);

--> ANSI
SELECT MEM_ID, MEM_PWD, MEM_NAME, NVL(GRADE_NAME, '등급미정')
  FROM MEM
  LEFT JOIN MEM_GRADE ON (GRADE_ID = GRADE_CODE);

-- 사실 외래키 제약조건이 걸리지 않아도 JOIN 가능함.
-- 단, 두 컬럼에 동일한 의미 데이터들이 서로 담겨있어야 함
-- ex) 사실 KH 계정의 EMPLOYEE 테이블의 DEPT_CODE, JOB_CODE 컬럼에는 외래 제약 X

ROLLBACK;

DROP TABLE MEM;
-----------------------------------------------------------------------------

/*
    ---- 여기서부터 실행은 KH계정을 전환해서 실행 -----
    
    * SUBQUERY 이용한 테이블 생성
    
    - 메인 SQL문의 보조역할을 해주는 SELECT문을 서브쿼리라고 함
    - 메인 SQL문의 종류는 SELECT문 뿐만아니라 CREATE문도 될 수 있음
    
    [표현법]
    CREATE TABLE 테이블명
    AS (서브쿼리);
    
    - 즉, 해당 서브쿼리를 수행한 RESULT SET을 통해서 새로 테이블을 만들겠다.
    - 테이블을 복사하는 개념(깊은복사 느낌)
    
*/

-- EMPLOYEE 테이블을 복제한 새로운 테이블 생성 -> EMPLOYEE_COPY
CREATE TABLE EMPLOYEE_COPY
AS (
        SELECT *
          FROM EMPLOYEE
    );

SELECT * FROM EMPLOYEE_COPY;
-->  컬럼들, 조회결과의 데이터들이 복사됨
--> 제약조건은 NOT NULL만 복사됨



-- EMPLOYEE 테이블에 있는 컬럼 구조만 복사하고 싶을 경우는?
-- 데이터값은 필요없는 경우
CREATE TABLE EMPLOYEE_COPY2
AS (
        SELECT *
          FROM EMPLOYEE
         WHERE 1 = 0 -- FALSE 의미
         );



-- 전체 사원 중 급여 300만원 이상인 사원의 사번, 이름, 부서코드, 급여 컬럼만 복제
CREATE TABLE EMPLOYEE_COPY3
AS (
        SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
          FROM EMPLOYEE
         WHERE SALARY >= 3000000
);

-- 전체 사원의 사번, 사원명, 급여, 연봉조회 결과를 테이블로 생성
CREATE TABLE EMPLOYEE_COPY4
AS (
        SELECT EMP_ID, EMP_NAME, SALARY, SALARY*12 "연봉"
          FROM EMPLOYEE
);
--> 서브쿼리의 SELECT 절에 함수식이나 연산식이 포함되면 별칭 필요.
-- 그 별칭이 컬럼명이 됨. 참고로 한글 컬럼명 가능

/*
    * 테이블이 다 생성된 후 뒤늦게 제약조건을 추가하고자 한다면?
    
    ALTER TABLE 테이블명 xxxx(변경할내용);
    
    - PRIMARY KEY: ADD PRIMARY KEY(컬럼명);
    - FOREIGN KEY: ADD FOREIGN KEY(컬럼명) REFERENCES 부모테이블명(컬럼명);
    - UNIQUE: ADD UNIQUE(컬럼명);
    - CHECK: ADD CHECK(컬럼에대한조건식);
    - NOT NULL: MODIFY 컬럼명 NOT NULL;

*/

-- EMPLOYEE_COPY 테이블에 없는 PRIMARY KEY 제약조건 추가, 
-- EMP_ID 컬럼에 부여
ALTER TABLE EMPLOYEE_COPY ADD PRIMARY KEY(EMP_ID);

-- DEPT_CODE 컬럼에 외래키 추가. DEPARTMENT 테이블의 DEPT_ID 컬럼 참조
ALTER TABLE EMPLOYEE_COPY ADD FOREIGN KEY(DEPT_CODE) REFERENCES DEPARTMENT(DEPT_ID);

-- DEPARTMENT 테이블의 LOCATION_ID 컬럼에 외래키 추가
ALTER TABLE DEPARTMENT ADD FOREIGN KEY(LOCATION_ID) REFERENCES LOCATION(LOCAL_CODE);








