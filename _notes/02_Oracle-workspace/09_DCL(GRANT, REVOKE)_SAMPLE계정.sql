-- 현재 CREATE SESSION 권한만 받음

CREATE TABLE TEST (
    TEST_ID NUMBER
    );
    
-- 3-1. INSUFFICIENT PRIVILEGES 오류로 테이블 생성 불가

CREATE TABLE TEST (
    TEST_ID NUMBER
    );

-- 3-2. TABLE SPACE라는 공간이 아직 할당되지 않아서 오류 발생
-- TABLE SPACE: CREATE TABLE 구문으로 테이블 생성시 테이블은 TABLE SPACE라는 공간에 만들어짐
-- 모든 테이블은 TABLE SPACE에 만들어져야 함
-- 필요하다면 직접 만들어서 써도 되지만, 오라클에서 기본적으로 제공하는 테이블스페이스 CREATE공간이 있음
-- 오라클 기본제공 TABLE SPACE: SYSTEM
-- NO PRIVILEGES ON TABLESPACE 'SYSTEM'

CREATE TABLE TEST (
    TEST_ID NUMBER
    );
--> 드디어 테이블 생성 완료
--> 해당 계정이 소유하는 테이블을 조작하는 것도 가능해짐

SELECT * FROM TEST;
INSERT INTO TEST VALUES(1);

-- 뷰 만들어보기
CREATE VIEW V_TEST
AS (
        SELECT *
          FROM TEST
    );
    
-- 4. 뷰를 생성할 수 있는 CREAT VIEW 권한이 부여되지 않아서 오류 발생
-- insufficient privileges 오류 발생. 불충분한 권한

CREATE VIEW V_TEST
AS (
        SELECT *
          FROM TEST
    );  
--> 뷰 생성 완료!

--> 해당 계정에 권한이 어디까지 들어있느냐에 따라서 할 수 있는 작업범위가 날라짐

-----------------------------------------------------------------------------
    
-- SAMPLE 계정에서 KH 계정의 테이블에 접근해서 조회해보기
SELECT * FROM KH.EMPLOYEE;
    
-- 5. KH 계정의 EMPLOYEE 테이블에 접근해서 SELECT 할 수 있는 권한이 없음
-- table or view does not exist 오류 발생
SELECT * FROM KH.EMPLOYEE;
    
-- 6. KH 계정의 DEPARTMENT 테이블 조회도 역시 불가해서 권한 부여 후 접근.
SELECT * FROM KH.DEPARTMENT;

INSERT INTO KH.DEPARTMENT VALUES('D0', '회계부', 'L2');

COMMIT;
--> 모두 KH계정에서 실행

----------------------------------------------------------------------------

-- 테이블 만들어보기
CREATE TABLE TEST2(
    TEST_ID NUMBER
    
    );


-- 7. SAMPLE 계정 회수당함
-- CREAT TABLE 하면 처음처럼 똑같이 오류뜸










    