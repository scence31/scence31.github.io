/*
    * SQL(Structured Query Language)
    - 구조화된 질의 언어
    - DB에서 사용되는 명령문들을 SQL이라고 함(==쿼리문)
    
    [종류]
    DQL(Data Query Language): 데이터 조회 용도
        SELECT: 데이터 조회    
    
    DML(Data Manipulation Language): 데이터 조작 용도
        INSERT: 데이터 삽입, UPDATE: 데이터 수정, DELETE: 데이터 삭제
        *SELECT(보통 DML로 간주함)
    
    DDL(Data Definition Language): 데이터 정의
        CREAT: 데이터베이스 구조물 생성, ALTER: 데이터베이스 구조물 수정, DROP: 데이터베이스 구조물 삭제
    
    DCL(Data Control Language): 데이터 제어 용도
        DQL, DML, DDL을 제외한 모든 명령문들을 묶어서 DCL이라고 함
        GRANT: 권한부여, REVOKE: 권한뺏기
    
    TCL(Transaction Control Language): 트랜잭션 제어 용도
        데이터의 조작 흐름(DML문의 흐름)을 컨트롤해줌
        COMIT: 데이터 조작 결과를 픽스, ROLLBACK: 데이터 조작 결과를 복구, SAVEPOINT: 데이터 조작 흐름의 중간저장점 잡기
        *TCL 또한 DCL의 하위분류임
*/

/*
    <SELECT>
    DQL 또는 DML의 한 종류로, 데이터를 조회하거나 검색할 때 사용되는 명령어
    
    - RESULT SET: SELECT문을 통해 조회된 데이터들의 결과물
    
    [표현법]
    SELECT 조회하고자하는컬럼명, 컬럼명, 컬럼명, ... (여러개면 나열하기)
      FROM 테이블명;
*/

-- EMPLOYEE 테이블의 전체 사원들의 사번, 이름, 급여 컬럼만 조회
SELECT EMP_ID, EMP_NAME, SALARY
  FROM EMPLOYEE;
  
select emp_id, emp_name, salary
  from employee;
-- SQL 명령문들은 대소문자를 가리지 않지만 가독성을 위해 대문자 사용
-- 자바 이름지을 때: empId, empName
-- 오라클 이름지을 때: EMP_ID, EMP_NAME ~> 스네이크표기법(Snake Case)

-- EMPLOYEE 테이블의 전체 사원들의 모든컬럼을 다 조회
SELECT EMP_ID
     , EMP_NAME
     , EMP_NO
     , EMAIL
     , PHONE
     , DEPT_CODE
     , JOB_CODE
     , SAL_LEVEL
     , SALARY
     , BONUS
     , MANAGER_ID
     , HIRE_DATE
     , ENT_DATE
     , ENT_YN
  FROM EMPLOYEE;
  
SELECT *
  FROM EMPLOYEE;
-- 해당 테이블의 모든 컬럼을 조회하고싶다면 SELECT* 사용해도 됨

-- 연습문제
-- 1. JOB 테이블의 모든 컬럼 조회
SELECT JOB_CODE, JOB_NAME
  FROM JOB;
  
-- 2. JOB 테이블의 직급명 컬럼만 조회
SELECT JOB_NAME
  FROM JOB;
  
-- 3. DEPARTMENT 테이블의 모든 컬럼 조회
SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID
  FROM DEPARTMENT;

-- 4. EMPLOYEE 테이블의 직원명, 이메일, 전화번호, 입사일 컬럼 조회
SELECT EMP_NAME, EMAIL, EMP_NO, HIRE_DATE
  FROM EMPLOYEE;

-- 5. EMPLOYEE 테이블의 입사일, 직원명, 급여 컬럼 조회
SELECT HIRE_DATE, EMP_NAME, SALARY
  FROM EMPLOYEE;

---------------------------------------------------------------------

/*
    <컬럼값을 통한 산술연산>
    조회하고자 하는 컬럼값을 나열하는 SELECT 절에
    산술연산자 +-/* 기술해서 결과 조회할 수 있음(% 모듈러 연산은 없음)
*/

-- EMPLOYEE 테이블로부터 직원명, 월급, 연봉(= 월급*12) 조회
SELECT EMP_NAME, SALARY, SALARY * 12
  FROM EMPLOYEE;
  
-- 직원명, 월급, 보너스, 보너스가포함된연봉 조회(NULL값이 포함되면 연살결과 NULL로 나옴)
SELECT EMP_NAME, SALARY, BONUS, (SALARY + (SALARY*BONUS))*12
  FROM EMPLOYEE;
  
-- 직원명, 입사일, 근무일수 조회
-- 오라클에서 날짜를 다루는 DATE 타입끼리도(연, 월, 일, 시, 분, 초) 산술연산 가능!
-- 오늘날짜: SYSDATE
-- DATE 타입끼리 산술연산 시 결과값은 일 단위로 나옴
-- 결과에 소수점이 나오는 이유는 시/분/초 포함되기 때문
SELECT EMP_NAME, HIRE_DATE, SYSDATE - HIRE_DATE
  FROM EMPLOYEE;
  
-----------------------------------------------------------------------------

/*
    <컬럼명에 별칭 지정하기>
    조회시 컬럼명에 별칭을(ALIAS) 붙일 수 있음
    
    [표현법]
    SELECT 컬렴명 AS 별칭
         , 컬렴명 AS "별칭"
         , 컬렴명 별칭
         , 컬럼명 "별칭" ...
      FROM 테이블명;
      
      AS 여부는 상관 없음, 별칭에 특수문자 or 띄어쓰기 포함된 경우는 쌍따옴표 필요
      별칭이 너무 길면 오류발생
*/

-- EMPLOYEE 테이블의 이름, 급여, 보너스, 총소득(보너스 포함 연봉) 조회
SELECT EMP_NAME AS 이름
     , SALARY AS "급여(월)"
     , BONUS 보너스
     , (SALARY + (SALARY*BONUS)) * 12 "총 소득"
  FROM EMPLOYEE;
  
----------------------------------------------------------------------------

/*
    <리터럴>
    == 값
    SELECT절에 일반 리터럴을 제시하여 마치 그 테이블에 원래부터 존재한 데이터처럼 함께 조회 가능
    제시한 리터럴 값은 조회결과인 RESULT SET의 모든 행에 반복적으로 출력됨
    오라클에서 문자열은 작은따옴표로 감싸서 표현한다
*/

-- EMPLOYEE 테이블로부터 사번, 사원명, 급여, 단위 조회
SELECT EMP_ID, EMP_NAME, SALARY, '원' 단위
  FROM EMPLOYEE;

---------------------------------------------------------------------------

/*
    <DISTINCT>
    조회하고자 하는 컬럼의 중복된 값을 딱 한 번씩만 출력하고 싶을 때 사용
    즉, 중복 제거
    
    [표현법]
    SELECT DISTINCT 컬럼명
      FROM 테이블명;
      
    단, SELECT절에서는 DINTINCT 구문을 단 한 번만 사용할 수 있음
*/

-- EMPLOYEE 테이블로부터 부서코드들을 조회
SELECT DEPT_CODE
  FROM EMPLOYEE;
-- 한 부서에 여러명의 직원이 소속될 수 있어서 중복값이 나옴
-- 부서코드를 중복값 없이 보고싶다면?
SELECT DISTINCT DEPT_CODE
  FROM EMPLOYEE;
-- > 사원이 1명이라도 있는 부서들의 부서코드를 조회한 것

-- EMPLOYEE 테이블로부터 중복 제거 후 직급코드 조회
SELECT DISTINCT JOB_CODE
  FROM EMPLOYEE;

SELECT DISTINCT DEPT_CODE, JOB_CODE
  FROM EMPLOYEE;
--> DEPT_CODE화 JOB_CODE 컬럼값들을 세트로 묶어서 한번에 중복판별

----------------------------------------------------------------------------

/*
    <WHERE절>
    SELECT문 내부에서 조회하고자 하는 테이블에 특정 조건을 제시해서
    그 조건에 만족되는 행들만 조회할 수 있게 도와줌
    
    [표현법]
    SELECT 조회하고자하는컬럼명, 컬럼명, ... ==> SELECT절
      FROM 테이블명 ==> FROM절
    WHERE 조건식; ==> WHERE절
      
    SELECT문의 필수구성 절: SELECT절, FROM절
    WHERE절이 없다고 해서 SELECT문 오류발생하는 것은 아님, 데이터 디테일 향상 가능.
    
    *SELECT문의 절 실행순서
    FROM절 --> WHERE절 --> SELECT절      
*/

/*
    WHERE절의 조건식에는 다양한 연산자 사용 가능함
    
    <비교연산자>
    크고 작음을 비교: > < >= <=
    일치 비교: =
    일치하지 않음을 비교: !=, ^=, <>
    
*/

-- EMPLOYEE 테이블로부터 급여가 400만원 이상인 사원들의 모든 컬럼을 조회
SELECT *
  FROM EMPLOYEE
 WHERE SALARY >= 4000000;

-- EMPLOYEE 테이블로부터 부서코드가 D9인 사람들의 사원명, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
  WHERE DEPT_CODE = 'D9';
-- 3명 조회
  
  -- EMPLOYEE 테이블로부터 부서코드가 D9가 아닌 사원들의 사원명, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE != 'D9';
-- 18명 조회
-- 총 23명인데 2명 누락됨
--> 비교연산시 NULL값은 제외하고 연산됨


-- 연습문제 --
-- 1. 임플로이 테이블로부터 급여가 300만원 이상인 사원들의 이름, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
  FROM EMPLOYEE
 WHERE SALARY >= 3000000;
 
-- 2. 임플로이로부터 직급코드가 J2인 사람들의 이름, 급여, 보너스 조회
SELECT EMP_NAME, SALARY, BONUS
  FROM EMPLOYEE
 WHERE JOB_CODE = 'J2';
 
-- 3. 임플~ 현재 재직중인 사원들의 사번, 이름, 입사일 조회
-- 힌트) ENT_YN 컬럼값 기준 N이 재직중
SELECT EMP_ID, EMP_NAME, HIRE_DATE
  FROM EMPLOYEE
 WHERE ENT_YN = 'N';
 -- WHERE ENT_DATE = NULL; -> NULL은 비교연산 불가능

-- 4. 임플~ 연봉이 5천만원 이상인 사람달의 이름, 급여, 연봉, 입사일 조회
SELECT EMP_NAME, SALARY, SALARY*12 연봉, HIRE_DATE
  FROM EMPLOYEE
 WHERE SALARY*12 >= 50000000; -- WHERE 절에 별칭 불가(실행순서 때문에!!)

----------------------------------------------------------------------------

/*
    <논리연산자>
    여러개의 조건식을 하나로 엮을 때 사용
    AND / OR
    자바에서는 && / ||
    
    AND가 OR보다 우선순위 더 높음(다른 언어도 마찬가지임)
    EX) 부서가 D9 또는 D8이고, 급여가 200만원 이상인
       (DEPT_CODE = 'D9' OR DEPT_CODE = 'D8') AND SALARY >= 2000000
    
*/

-- 임플~ 부서코드가 D9이면서 급여가 500만원 이상인 사원 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE (DEPT_CODE = 'D9') AND (SALARY >= 5000000);
 
-- 임플테이블~ 부서코드가 D6이거나 급여가 300만원 이상인 사원 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE (DEPT_CODE = 'D6') OR (SALARY >= 3000000);

-- 임플테이블~ 급여 350만 이상, 600만 이하인 사원 이름, 사번, 급여, 직급코드 조회
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
  FROM EMPLOYEE
 WHERE (SALARY >= 3500000) AND (SALARY <= 6000000);

----------------------------------------------------------------------------

/*
    <BETWEEN AND>
    몇 이상 몇 이하인 범위에 대한 조건을 제시할 때 사용
    
    [표현법]
    비교대상컬럼명 BETWEEN 하한값 AND 상한값
    
*/
-- 임플테이블~ 급여 350만 이상, 600만 이하인 사원 이름, 사번, 급여, 직급코드 조회
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
  FROM EMPLOYEE
 WHERE SALARY BETWEEN 3500000 AND 6000000;
 
 -- 임플테이블로부터 입사일 90/01/01 ~ 03/01/01 사이인 사람들 모든 컬럼 조회
 SELECT *
   FROM EMPLOYEE
 WHERE HIRE_DATE BETWEEN '90/01/01' AND '03/01/01';
 
  -- 임플테이블로부터 입사일 90/01/01 ~ 03/01/01 사이가 아닌 사람들 모든 컬럼 조회
  SELECT *
   FROM EMPLOYEE
 WHERE /*NOT*/ HIRE_DATE NOT BETWEEN '90/01/01' AND '03/01/01';
  -- NOT은 오라클의 논리부정연산자 (자바는 !)
  
----------------------------------------------------------------------------

/*
    <LIKE '특정패턴'>
    비교하려는 컬럼값이 내가 지정한 특정패턴에 만족할 경우 조회
    
    [표현법]
    비교대상컬럼명 LIKE 특정패턴
    
    특정패턴 작성하는법: 와일드카드인 '%', '_'를 가지고 제시함
    '%': 0글자 이상
         비교대상컬럼명 LIKE '문자%': 컬럼값 중에 '문자'로 시작되는 값들 모두 조회
         ex) A, ADFS, Avxzc, AAASDF ..
         
         비교대상컬럼명 LIKE '%문자': '문자'로 끝나는 값들 모두 조회
         ex) B, asfdaB, ...
         
         ★ 비교대상컬럼명 LIKE '&문자%': 문자가 포함된 값들 모두 조회
         (특히 '%문자%' 패턴은 검색기능 구현시 쓰임)
         
    '_': 딱 1글자
         비교대상컬럼명 LIKE '문자_': 컬럼값 중에 '문자' 뒤에 무조건 1글자가 있을 경우
         ex) 'AA', 'AB', 'AC' ...
         
         비교대상컬럼명 LIKE '_문자': 앞에 무조건 1글자
         비교대상컬럼명 LIKE '__문자': 문자 앞에 무조건 2글자
*/

-- 임플테이블로부터 성이 김씨인 사람들의 이름, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '김%';

-- 임플테이블로부터 이름 '림'이 포함된 사원 이름, 주민번호, 부서코드 조회
SELECT EMP_NAME, EMP_NO, DEPT_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '%림%';

-- 임플~ 전화번호 4번째 자리가 9로 시작하는 사원들의 사번, 사원명, 전화번호, 이메일 조회
SELECT EMP_ID, EMP_NAME, PHONE, EMAIL
  FROM EMPLOYEE
 WHERE PHONE LIKE '___9%';

-- 임플~ 이름 가운데 글자가 '가'인 사람들의 모든 컬럼 조회 / 그 외 사람 조회
SELECT *
  FROM EMPLOYEE
 WHERE /*NOT*/ EMP_NAME /*NOT*/ LIKE '_가%';


--- 연습문제 ---
-- 1. EMPLOYEE 테이블로부터 이름이 '진'으로 끝나는 사원들의 이름, 입사일 조회
SELECT EMP_NAME, HIRE_DATE
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '%진';
 
-- 2. 임플테이블로부터 전화번호 처음 3글자가 010이 아닌 사원들의 이름 전화번호 조회
SELECT EMP_NAME, PHONE
  FROM EMPLOYEE
 WHERE PHONE NOT LIKE '010%';

-- 3. DEPARTMENT 테이블로부터 해외영업과 관련된 부서들의 모든 컬럼 조회
SELECT *
  FROM DEPARTMENT
 WHERE DEPT_TITLE LIKE '%해외영업%';

-----------------------------------------------------------------------------

/*
    <IS NULL>
    NULL과 일치하는지 비교해주는 연산자
    =은 NULL과 일치함을 비교할 수 없음
    
    [표현법]
    비교대상컬럼명 IS NULL: 컬럼값이 NULL인 경우 조회
    비교대상컬럼명 IS NOT NULL: 컬럼값이 NULL이 아닌 경우 조회
*/

-- 임플~ 보너스를 받지 않는(보너스 = NULL) 사원들의 사번, 이름, 급여, 보너스 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
  FROM EMPLOYEE
 WHERE BONUS IS NOT NULL;

-- 임플~ 사수가 없는 사원들의 사원명, 사수의 사번, 부서코드 조회
SELECT EMP_NAME, MANAGER_ID, DEPT_CODE
  FROM EMPLOYEE
 WHERE MANAGER_ID Is NULL;

-- 임플~ 사수도 없고, 부서배치도 받지 않은 사원 모든 컬럼 조회
SELECT *
  FROM EMPLOYEE
 WHERE (MANAGER_ID IS NULL) AND (DEPT_CODE IS NULL);
 
-- 부서배치 받지는 않았지만 보너스는 받는 사원들의 사원명, 보너스, 부서코드 조회
SELECT EMP_NAME, BONUS, DEPT_CODE
  FROM EMPLOYEE
 WHERE (DEPT_CODE IS NULL) AND (BONUS IS NOT NULL);

-- 위의 연습문제 3번
-- 3. 임플~ 현재 재직중인 사원들의 사번, 이름, 입사일 조회
-- 힌트) ENT_YN 컬럼값 기준 N이 재직중
SELECT EMP_ID, EMP_NAME, HIRE_DATE
  FROM EMPLOYEE
 WHERE ENT_DATE IS NULL;

-------------------------------------------------------------------------

/*
    <IN>
    비교대상컬럼값에 내가 제시한 목록들 중에서 일치하는 값이 있는지 동등비교해주는 연산자
    
    [표현법]
    비교대상컬럼명 IN (값, 값, 값, ...)
    >> OR 연산자와 비슷
    
    보통 동일한 비교대상컬럼명 기준으로 동등비교하는 조건이 OR로 여러 개 엮여있을 경우
    조건식을 줄이는 용도로 많이 씀
*/

-- 임플~ 부서코드가 D6 또는 D8 또는 D5인 사람의 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE IN ('D6', 'D8', 'D5');
--> 내부적으로 동등비교가 수행됨(NULL 제외하고 연산됨)

-- 그 외의 사원들
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE NOT DEPT_CODE IN ('D6', 'D8', 'D5');

-----------------------------------------------------------------------

/*
    <연결 연산자 (||)>
    여러 컬럼값들을, 마치 하나의 컬럼값인 것처럼, 연결시켜주는 연산자(하나의 문자열로)
    컬럼과 리터럴 또한 연결 가능함
    
    - 자바로 따지면 sysout("num: " + num);

*/

SELECT EMP_ID || EMP_NAME || SALARY AS 연결됨
  FROM EMPLOYEE;

-- XXX번 XXX사원의 월급은 XXXXXXXX원 입니다.
SELECT EMP_ID || '번 ' || EMP_NAME || '사원의 월급은 ' || SALARY || '원 입니다.'
  FROM EMPLOYEE;

------------------------------------------------------------------------
/*
    - 연결자 우선순위
    
    0. ()
    1. 산술연살자
    2. 연결연산자 ||
    3. 비교연산자
    4. IS NULL, LIKE, IN
    5. BETWEEN AND
    6. NOT
    7. AND
    8. OR
*/

-------------------------------------------------------------------------
/*
    <ORDER BY 절>
    SELECT문 가장 마지막에 기입하는 구문일 뿐만 아니라 실행순서 또한 가장 마지막인 구문.
    조회된 결과를 최종적으로 오름차순 또는 내림차순으로 정렬해주는 역할.
    
    타입별 정렬값: 숫자, 이름, 이메일(영문), 날짜
    
    [표현법]
    SELECT 조회할컬럼명, ..
      FROM 테이블명
     WHERE 조건식(생략가능)
     ORDER BY [정렬기준컬럼명 or 별칭 or 컬럼순번] [ASC or DESC or 생략=ASC] [NULLS FIRST or LAST or 생략=]];
                            
     ASC: 오름차순 정렬
     DESC: 내림차순 정렬
     NULLS FIRST: 정렬하고자 하는 컬럼값에 NULL이 포함된 경우 NULL값들을 위에 배치하겠다.
     NULLS LAST: 정렬하고자~ NULL값들을 아래에 배치하겠다.
     
     ASC의 기본값은 NULLS LAST --> 둘다 생략시 나오는 것
     DESC의 기본값은 NULLS FIRST
     (오라클에서는 NULL값이 제일 큰 값으로 취급되기 때문임)
     
     실행순서: FROM -> WHERE -> SELECT -> ORDER BY 절
*/

-- 사원들의 급여가 높은(/**/)/낮은 순서대로 모든 컬럼 조회
SELECT *
  FROM EMPLOYEE
 ORDER BY SALARY /*DESC*/ASC;
 
SELECT EMP_ID 사번, EMP_NAME 사원명, SALARY 급여
  FROM EMPLOYEE
 ORDER BY 급여 DESC;
 --> ORDER BY 절이 SELECT 절보다 순서가 늦게 실행되어서 별칭 사용 가능

--        1       2         3       4         5
SELECT EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, SALARY
  FROM EMPLOYEE
-- ORDER BY 2 ASC; -- EMP_NAME 기준으로 정렬
 ORDER BY 5 DESC; -- SALARY 기준으로 정렬
 --> SELECT 절에 기술한 컬럼의 순번(1부터 셈) 통해서 정렬 기준 잡을 수 있음
 -- 가독성 측면에서 권장하는 방법은 아님
 
SELECT *
  FROM EMPLOYEE
 ORDER BY BONUS ASC NULLS FIRST;
 
 SELECT EMP_NAME, SALARY*12 연봉
   FROM EMPLOYEE
 ORDER BY 연봉 DESC;
 
SELECT *
  FROM EMPLOYEE
 ORDER BY BONUS DESC, SALARY ASC;
-- 정렬기준 콤마로 나열해서 여러 개 가능, 정렬기준 컬럼값이 일치할 경우 순서대로..
  
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 