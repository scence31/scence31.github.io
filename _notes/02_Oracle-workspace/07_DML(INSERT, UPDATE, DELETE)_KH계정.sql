/*
    <DML - DATA MANIPULATION LANGUAGE>
    
    - 데이터 조작 언어
    - 테이블에 새로운 데이터를 삽입하거나 INSERT
    - 기존 데이터를 수정/삭제하는 UPDATE DELETE 구문. (SELECT도 DML로 분류하긴 함)
    
    * SELECT 구문과 INSERT UPDATE DELETE 구문 차이점
    
    SELECT 구문은 10000번 실행해도 테이블 내용 변화 없음(단순 조회)
    INSERT UPDATE DELETE문은 1번만 실행해도 테이블 내용 변화함
*/

/*
    1. INSERT
    - 테이블에 새로운 행을 추가하는 구문
    
    [표현법]
    1) INSERT INTO 테이블명 VALUES(값1, 값2, ...);
    => 해당 테이블의 모든 컬럼에 대해 한 행 단위로 값을 하나하나 추가하는 것
    
    주의사항) 컬럼 순번대로, 개수만큼 값 나열
    
    오류: not enough values 부족한 값 제시 / too many values 많은 값 제시
    
*/

-- EMPLOYEE 테이블에 사원 정보 추가하기
-- Q. 홍길동 사원 정보 추가
INSERT INTO EMPLOYEE VALUES(900, '홍길동', '911231-1234567', 'user01@kh.or.kr'
                          , '01012345678', 'D3', 'J5', 'S4', 3500000, 0.1, NULL
                          , SYSDATE, NULL, DEFAULT);
--> "1행이 삽입되었습니다."
---> DEFAULT 값일 경우, DEFAULT 키워드를 사용하면 알아서 기본값이 INSERT 됨

SELECT * FROM EMPLOYEE;

-- Q. 박말순 사원 정보 추가하기
INSERT INTO EMPLOYEE VALUES(901, '박말순', '980723-2345678', 'user02@kh.or.kr'
                          , '01044445555', 'D7', 'J7', 'S4', 3100000, NULL, NULL
                          , SYSDATE, NULL, 'N');

SELECT * FROM EMPLOYEE;

-------------------------------------------------------------------------------

/*
    2) INSERT INTO 테이블명(컬럼명1, 컬럼명2, ...) VALUES(값1, 값2, ...);
    - 해당 테이블에 특정 컬럼만 선택, 그 컬럼에 추가할 값만 선택적으로 제시할 때 사용
    - 한 행 단위로 추가되며, 선택되지 않은 나머지 컬럼 기본값은 NULL
    
    주의사항) NOT NULL 제약조건 있는 컬럼은 선택해서 직접 값을 제시야되지만,
    DEFAULT 설정이 된 컬럼은 선택하지 않아도 됨(DEFAULT 값이 들어감)
    
    오류: not enough values 부족한 값 제시 / too many values 많은 값 제시
*/
                          
-- Q. 902 김말똥 사원정보 추가
INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, JOB_CODE, SAL_LEVEL, HIRE_DATE)
              VALUES(902, '김말똥', '881122-2211334', 'D7', 'J3', 'S2', SYSDATE);
                          
SELECT * FROM EMPLOYEE;
--> 선택하지 않은 컬럼값은 NULL or DEFAULT값으로 채워 만들어짐

-- Q. 고영희 사원 정보 추가
INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, JOB_CODE, SAL_LEVEL, HIRE_DATE)
              VALUES(903, '고영희', '851212-1122334', 'D4', 'J3', 'S2', SYSDATE);

------------------------------------------------
                          
                          
-- 연습용 새로운 테이블 만들기
-- Q. 첫번째 테이블, 급여 300만원 이상 사번, 이름, 직급명에 대해 보관할 테이블
CREATE TABLE EMP_JOB(
    EMP_ID NUMBER,
    EMP_NAME VARCHAR2(20),
    JOB_NAME VARCHAR2(20)
    );

-- Q. 두 번째 테이블, 급여 300만 이상 사번, 이름, 부서명 보관할 테이블
CREATE TABLE EMP_DEPT(
    EMP_ID NUMBER,
    EMP_NAME VARCHAR2(20),
    DEPT_TITLE VARCHAR2(20)
    );

-- 급여가 300만 이상인 사원들의 사번, 이름, 직급명, 부서명 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE
  FROM EMPLOYEE E
  JOIN JOB J USING (JOB_CODE)
  JOIN DEPARTMENT D ON (DEPT_CODE = DEPT_ID)
 WHERE SALARY >= 3000000;
----------------------------------------------------------------------------
/*
    * INSERT INTO 구문에서도 서브쿼리 이용하면 INSERT ALL 구문처럼 한번에 여러 행 ISNERT 할 수 있음
    
    3)
    INSERT INTO 테이블명
    (서브쿼리);
    => VALUES로 값을 직접대입하는 것이 아닌, 서브쿼리로 조회한 결과를 통째로 INSERT하는 구문
*/

-- 연습용 테이블 생성
CREATE TABLE EMP_01(
    EMP_ID NUMBER,
    EMP_NAME VARCHAR(20),
    DEPT_TITLE VARCHAR(20)
    );
    
SELECT * FROM EMP_01;

-- 전체 사원의 사번, 이름, 부서명 조회
SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '부서배치미정')
  FROM EMPLOYEE, DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID(+);

INSERT INTO EMP_01
(
    SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '부서배치미정')
      FROM EMPLOYEE, DEPARTMENT
     WHERE DEPT_CODE = DEPT_ID(+)
);


----------------------------------------------------------------------------
/*
    1) INSERT ALL
    [표현법]
    INSERT ALL
    INTO 테이블명1 VALUES(컬럼명1, 컬럼명2, ...)
    INTO 테이블명2 VALUES(컬럼명1, 컬럼명2, ...)
    ...
    (서브쿼리);
*/

-- 바로 위의 SELECT문으로부터
-- EMP_ID, EMP_NAME, JOB_NAME 컬럼에 들은 조회값들은 EMP_JOB 테이블에
-- EMP_ID, EMP_NAME, DEPT_TITLE 컬럼에 들은 조회값들은 EMP_DEPT 테이블에
-- 각각 INSERT 가능함
INSERT ALL
INTO EMP_JOB VALUES(EMP_ID, EMP_NAME, JOB_NAME) -- 10개 행 INSERT
INTO EMP_DEPT VALUES(EMP_ID, EMP_NAME, DEPT_TITLE) -- 10개 행 INSERT
(
    SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE
      FROM EMPLOYEE E
      JOIN JOB J USING (JOB_CODE)
      JOIN DEPARTMENT D ON (DEPT_CODE = DEPT_ID)
     WHERE SALARY >= 3000000 -- 10개 행 조회
);
--> "20개 행이 삽입되었습니다."
-- 서브쿼리 실행결과는 총 10개 행 조회
-- 각각 EMP_JOB, EMP_DEPT 텡이블에 10개 행씩 INSERT, 총 20개 행이 INSERT 됨

SELECT * FROM EMP_JOB;
SELECT * FROM EMP_DEPT;
                    
-- INSERT ALL시 조건을 사용해서도 각 테이블에 INSERT 가능
-- 연습용 테이블 만들기
-- 사번, 이름, 입사일, 급여 저장하는 테이블 생성
-- 첫번째 테이블: 2010년 이전에 입사한 사원들 정보 저장
CREATE TABLE EMP_OLD
AS (
        SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
          FROM EMPLOYEE
         WHERE 1= 0
         );
         
-- 두번쨰 테이블:2010년 이후에 입사한 사원들 정보만 저장
CREATE TABLE EMP_NEW
AS (
        SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
          FROM EMPLOYEE
         WHERE 1 = 0
         );
         
SELECT * FROM EMP_OLD;
SELECT * FROM EMP_NEW;

-- 2010년도 이전 입사자들 정보만 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
  FROM EMPLOYEE
 -- WHERE HIRE_DATE < '2010/01/01'; -- 9명
 WHERE HIRE_DATE >= '2010/01/01'; -- 18명


/*
    2)
    INSERT ALL
    WHEN 조건식1 THEN INTO 테이블1 VALUES(컬럼명1, 컬럼명2, ...)
    WHEN 조건식2 THEN INTO 테이블2 VALUES(컬럼명1, 컬럼명2, ...)
    ...
    (서브쿼리);
    
*/

INSERT ALL
WHEN (HIRE_DATE < '2010/01/01') THEN INTO EMP_OLD VALUES(EMP_ID, EMP_NAME, HIRE_DATE, SALARY) -- 9개 행 INSERT
WHEN (HIRE_DATE >= '2010/01/01') THEN INTO EMP_NEW VALUES(EMP_ID, EMP_NAME, HIRE_DATE, SALARY) -- 18 INSERT
(
    SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
      FROM EMPLOYEE -- 27개 행 조회
  );
--> "27개의 행이 삽입되었습니다."
-- 서브쿼리로 실행된 결과로 26개 행 조회됨
-- 이걸 각 2010년 기준으로 이전/이후 입사자들을 9명/17명씩 나눠서 각각 INSERT하는 것. 26개 행 INSERT

-->> INSERT 구문 성공시, "N개 행이 삽입되었습니다." 문구가 나옴..!

-----------------------------------------------------------------------------

/*
    3. UPDATE
    
    - 테이블에 이미 기록된 기존 데이터를 수정하는 구문
    - 테이블 내용은 변하지만 행의 개수는 변하지 않음
    
    [표현법]
    
    UPDATE 테이블명
       SET 컬럼명 = 바꿀값=> 
         , ...
     WHERE 조건식;
     => SET절 변경할 값(변경후), =은 대입연산자임 ~> ,로 나열(AND 아님)
     => WHERE절 기존 데이터(변경전), 생략가능(TRUE로 판별됨, 해당 테이블 전체 행이 변경)
    
*/

-- 테스트용 복사본 테이블 생성
CREATE TABLE DEPT_COPY
AS (
        SELECT *
          FROM DEPARTMENT
        );
SELECT * FROM DEPT_COPY;

-- Q. DEPT_COPY 테이블 D9 부서의 부서명을 '총무부'에서 '전략기획팀'으로 변경
UPDATE DEPT_COPY
   SET DEPT_TITLE = '전략기획팀' --> 대입연산자
 WHERE DEPT_ID = 'D9'; --> 동등비교연산자
--> 1행이 업데이트되었습니다.

-- 데이터 변경사항 픽스
COMMIT;

-- DEPT_COPY 테이블의 D7 부서의 부서명을 해외영업3부 --> 교육팀 변경
UPDATE DEPT_COPY
   SET DEPT_TITLE = '교육팀'
--> WHERE 생략했더니 "9개 행이 업데이트되었습니다." 전체 행이 변경됨. 실수 조심

SELECT * FROM DEPT_COPY;

-- 전체 행 변경되기 전으로 데이터 복구
ROLLBACK; --> COMMIT;으로 픽스 시킨 시점으로 롤백

-- 복사본 테이블
CREATE TABLE EMP_SALARY
AS(
        SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY, BONUS
          FROM EMPLOYEE
          );

SELECT * FROM EMP_SALARY;

-- Q. 강호형 사원의 급여를 550만원으로 업데이트 -> WHERE 절에 PRIMARY KEY 활용해 업뎃
UPDATE EMP_SALARY
   SET SALARY = 5500000
 WHERE EMP_ID = 201; -- PRIMARY KEY 해당하는 EMP_ID 통해 식별(중복X)


-- Q. EMP_SALARY 테이블의 선종범 사원 급여를 700만원, 보너스를 0.2로 변경
UPDATE EMP_SALARY
   SET SALARY = 7000000, BONUS = 0.2 --> ,로 연결
 WHERE EMP_ID = 209; --> PRIMARY KEY 해당하는 EMP_ID 통해 식별(중복X)


-- Q. EMP_SALARY 테이블의 전체사원 급여를 기존 급여에서 20% 인상하여 변경
UPDATE EMP_SALARY
   SET SALARY = SALARY * 1.2
 WHERE 1 = 1;
-- > WHERE절을 생략하는 대신 항상 TRUE가 나오도록 1=1 해도 됨

/*
    * UPDATE 문 안에서도 서브쿼리 사용 가능함
    - WHERE절 뿐만 아니라 SET절에서도 가능
*/

-- Q. EMP_SALARY 테이블의 김말똥 사원의 부서코드를 김가현 사원의 부서코드로 변경
-- 1) 김가현 사원 부서코드 알아내기
SELECT DEPT_CODE FROM EMP_SALARY WHERE EMP_NAME = '김가현'; -- D9

-- 2) 김말똥 사원의 부서코드를 D9로 변경
UPDATE EMP_SALARY
   SET DEPT_CODE = (
                        SELECT DEPT_CODE
                          FROM EMP_SALARY
                         WHERE EMP_NAME = '김가현'
                         ) -- 결과값 단일행 서브쿼리
 WHERE EMP_ID = 902;

SELECT * FROM EMP_SALARY;

-- Q. 지영재 사원의 급여와 보너스를 임은우 사원의 급여와 보너스값으로 변경
-- 1) 임은우 사원꺼 구하기
SELECT SALARY, BONUS
  FROM EMP_SALARY
 WHERE EMP_NAME = '임은우';

-- 2) 지영재 사원꺼 구하기
UPDATE EMP_SALARY
   SET SALARY = (
                    SELECT SALARY
                      FROM EMP_SALARY
                     WHERE EMP_NAME = '임은우'
                     )
     , BONUS = (
                    SELECT BINUS
                      FROM EMP_SALARY
                     WHERE EMP_NAME = '임은우'
                     )
 WHERE EMP_NAME = '지영재';
--> 단일행 서브쿼리를 두 번써서 표현 가능

UPDATE EMP_SALARY
   SET (SALARY, BONUS) = (
                            SELECT SALARY, BONUS
                              FROM EMP_SALARY
                             WHERE EMP_NAME = '임은우'
                             ) --> 결과 다중열 서브쿼리 (1행 2열)
 WHERE EMP_NAME = '지영재';

--> UPDATE문 사용시 주의사항: 변경할 값에 있어서도 해당 컬럼에 대한 제약조건은 반드시 위해하면 안됨

-- 이정민 사원의 사번을 200번으로 변경
UPDATE EMPLOYEE
   SET EMP_ID = 200
 WHERE EMP_NAME = '이정민';
--> PRIMARY KEY 제약조건에 위배, 오류발생

-- 사번이 200번인 사원의 이름을 NULL로 변경
UPDATE EMPLOYEE
   SET EMP_NAME = NULL
 WHERE EMP_ID = 200;
--> NOT NULL 제약조건에 위배, 오류발생


-- 모든 변경사항 확정(픽스)
COMMIT;

-------------------------------------------------------------------------------

/*
    4. DELETE
    
    - 테이블에 기록된 기존 데이터를 삭제하는 구문
    
    [표현법]
    DELETE
      FROM 테이블명
     WHERE 조건식;
     => WHERE절 생략가능(모든 행 삭제)
    
*/
SELECT * FROM EMPLOYEE;

-- Q. EMPLOYEE 테이블로부터 김말똥, 박말순, 고영희 사원 데이터 지우기
DELETE
  FROM EMPLOYEE
 WHERE EMP_NAME IN('김말똥', '박말순', '고영희');

COMMIT;

-- Q. DEPARTMENT 테이블로부터 DEPT_ID가 D1인 부서 삭제
DELETE
  FROM DEPARTMENT
 WHERE DEPT_ID = 'D1';
--> 오류발생: child record found (외래키 제약조건, EMPLOYEE 데이터에서 갖다쓰는중)

-- Q. DEPARTMENT 테이블로부터 DEPT_ID가 D3인 부서 삭제
DELETE
  FROM DEPARTMENT
 WHERE DEPT_ID = 'D3';
--> D3 갖다쓰는 테이블 없음

SELECT * FROM DEPARTMENT;

ROLLBACK;

-- Q. 김가현 사원과 같은 부서의 사원들 정보 지우기 (서브쿼리 사용)
SELECT DEPT_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME = '김가현';

DELETE
  FROM EMPLOYEE
 WHERE DEPT_CODE = (
                        SELECT DEPT_CODE
                          FROM EMPLOYEE
                         WHERE EMP_NAME = '김가현'
             );
 
/*
    INSERT/UPDATE/DELETE => 정수 N으로 나옴
    "N행이 삽입/업데이트/삭제되었습니다." 멘트 발생
    
    SELECT 구문 실행 성공시 항상 => "RESULT SET" 형식
    
*/
-------------------------------------------------------------------------------

/*
    * TRUNCATE
    
    - 테이블의 전체 행을 삭제할 때 쓸 수 있는 구문(절삭, 잘라내다)
    - WHERE절을 통해 별도 조건식을 제시할 수 없음
    - DELETE 보다 수행속도가 빠름
    - ROLLBACK 불가능(DML이 아니라 DDL로 분류)
    
    [표현법]
    TRUNCATE TABLE 테이블명;    |   DELETE FROM 테이블명;
    - 별도조건 제시 불가                가능
    - 수행속도 빠름                       느림
    - 롤백 불가                         가능
    - DDL 분류                              DML 분류
*/


SELECT * FROM EMP_SALARY;

DELETE
  FROM EMP_SALARY;

ROLLBACK;

TRUNCATE TABLE EMP_SALARY;
--> "테이블이 잘렸습니다."










