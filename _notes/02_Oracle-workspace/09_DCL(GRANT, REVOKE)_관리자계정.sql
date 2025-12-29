/*
    <DCL - DATA CONROL LANGUAGE>
    
    - 데이터 제어 언어
    - 계정에게 시스템권한 또는 객체접근권한을 부여(GRANT)하거나 회수하는(REVOKE) 언어
    - 데이터 변경내용을 픽스해주는 COMMIT, 데이터 변경 내용을 복구해주는 ROLLBACK 또한 DCL로 분류됨
    하지만 TCL(TRANSACTION CONTROL LANGUAGE)로 분류하기도 함
    
    * 권한 부여(GRANT)
    - 시스템권한: 특정 DB에 접근하는 권한, 객체들을 생성할 수 있는 권한
    - 객체접근권한: 특정 객체들에 접근해서 조작(DML)할 수 있는 권한
    
    [표현법]
    GRANT 권한명1, 권한명2, ... TO 계정명;
    
    - 시스템권한 종류
    1. CREATE SESSION: 계정에 접근할 수 있는 권한
    2. CREATE TABLE: 테이블을 생성할 수 있는 권한
    3. CREAKE VIEW: 뷰를 생성할 수 있는 권한
    4. CREATE SEQUENCE: 시퀀스~
    5. CREATE USER: 계정~
    ...
*/

-- 1. SAMPLE 일반 계정 생성
CREATE USER SAMPLE IDENTIFIED BY SAMPLE;
--> 접속할 수 있는 권한 CREATE SESSION 권한을 부여하지 않았더니 접속시 LOGON DENIED 오류 발생

-- 2. SAMPLE 계정에 접속하기 위한 CREATE SESSION 권한을 부여
GRANT CREATE SESSION TO SAMPLE;

-- 3-1. SALMPLE 계정에 테이블을 생성할 수 있는 권한 부여
GRANT CREATE TABLE TO SAMPLE;

-- 3-2. SAMPLE 계정에 테이블스페이스 공간 할당해주기
ALTER USER SAMPLE QUOTA 2M ON SYSTEM;
--> QUOTA: 할당하다.
--> 2M: 2 MEGA BYTE

-- 4. SAMPLE 계정에 CREATE VIEW 권한 부여
GRANT CREATE VIEW TO SAMPLE;

---------------------------------------------------------------------------

/*
    * 객체접근권한(객체권한)
    
    - 특정 객체들을 조작할 수 있는 권한
    -즉, DML 구문들을 실행할 수 있는 권한(SELECT, INSERT, UPDATE, DELETE 가능)
    
    [표현법]
    GRANT 권한종류 ON 특정객체 TO 계정명;
    
    권한종류    | 특정객체
    ------------------------------------------
    SELECT          TABLE, VIEW, SEQUENCE
    INSERT          TABLE, VIEW
    UPDATE          TABLE, VIEW
    DELETE          TABLE, VIEW
    
    ex) 특정 XXX 테이블에 SELECT 할 수 있는 권한 부여
    GRANT SELECT ON 테이블명 TO 계정명;
    
*/

-- 5. SAMPLE 계정에 KH.EMPLOYEE 테이블을 조회할 수 있는 권한 부여
GRANT SELECT ON KH.EMPLOYEE TO SAMPLE;
--> KH.EMPLOYEE 테이블 조회 성공

GRANT SELECT ON KH.DEPARTMENT TO SAMPLE;

GRANT INSERT ON KH.DEPARTMENT TO SAMPLE;

-----------------------------------------------------------------------------
/*
    - 우리는 그동안 최소한 권한 부여할 때는
    GRANT CONNECT, RESOURCE TO 계정명;  했었음
    
    <롤 - ROLE>
    
    - 특정 권한들을 하나의 집합으로 묶어놓은 것
    
    > CONNECT: CREATE SESSION 권한이 하나의 집합으로 묶여있음
    (데이터베이스에 접속할 수 있는 권한)
    
    > RESOURCE: CREATE TABLE, CREATE SEQUENCE, ...
    (특정 객체들을 생성 및 관리할 수 있는 권한)
    근데 CREATE VIEW 권한은 불포함임
    
*/

-- 데이터 딕셔너리를 통해 어떤 롤이 있고, 어떤 롤에 어떤 권한들이 묶여있는지 확인 가능
-- (ROLE_SYS_PRIVS)
SELECT *
  FROM ROLE_SYS_PRIVS
 WHERE ROLE IN('CONNECT', 'RESOURCE');

-------------------------------------------------------------------------

/*
    * 권한 회수 (REVOKE)
    
    - 권한을 회수할 때 사용하는 명령어
    
    [표현법]
    
    REVOKE 권한명1, 권한명2, ... FROM 계정명;
*/
-- 7. SAMPLE 계정에서 테이블 만들 수 없도록 CREATE TABLE을 REVOKE 하기
REVOKE CREATE TABLE FROM SAMPLE;
















