/*
    <GROUP BY 절>
    
    조회 시 그룹을 묶어줄 기준을 제시할 수 있는 구문
    해당 제시된 기준별로 그룹을 묶어서 조회할 수 있다.
    
    주로 그룹함수와 함께 사용하며 GROUP BY 절을 사용하지 않으면
    그룹함수 결과가 무조건 1개로만 나옴(N개의 데이터가 1개의 그룹으로 묶임)
    GROUP BY 절을 쓸 경우 내가 제시한 기준에 대한 개수별로 그룹이 각각 묶임
    그룹 개수만큼 분할된 후 묶임
    
    실행순서: FROM -> GROUP BY -> SELECT -> ORDER BY
    
*/

-- 전체사원의 총 급여 합
SELECT SUM(SALARY)
  FROM EMPLOYEE; -- 70096240
--> 단순히 SELECT문에서 그룹함수를 호출하면
-- 23명 사원들을 하나의 그룹으로 묶어서 급여의 총 합을 구한 결과.

-- 각 부서별 총 급여의 합
SELECT DEPT_CODE, SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;
 --> GROYP BY 절을 이용해서 그룹함수를 호출하면
 -- 전체 23명의 사원들을 부서별 (DEPT_CODE)로 그룹을 묶어서 (총 7개 그룹)
 -- 부서별 그룹별로 급여의 총합을 각각 구한 결과임
 
 -- 전체 사원의 인원수
SELECT DEPT_CODE, COUNT(*)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;
 -- 총 23명을 부서별로 그룹 묶어서, 그룹 별로 인원수를 각각 카운트한 결과
 
 
SELECT JOB_CODE 직급, COUNT(*)
  FROM EMPLOYEE 
 GROUP BY JOB_CODE
 ORDER BY JOB_CODE ASC;

-- 각 직급별(JOB_CODE) 직급코드, 총급여 합, 사원수, 보너스를 받는 사원수, 평균급여, 최고급여, 최소급여
SELECT JOB_CODE 직업코드
     , SUM(SALARY) "급여 합"
     , COUNT(*) 사원수
     , COUNT(BONUS) "보너스 받는 사원 수"
     , ROUND(AVG(SALARY)) 평균급여
     , MAX(SALARY) 최고급여
     , MIN(SALARY) 최소급여
  FROM EMPLOYEE
 GROUP BY JOB_CODE
 ORDER BY JOB_CODE;
 
 -- 각 부서별 부서코드, 사원수, 보너스받는 사원수, 사수있는사원수, 평균급여
 SELECT NVL(DEPT_CODE, '미정') 부서코드
      , COUNT(*) 사원수
      , COUNT(BONUS) 보너스받는사원수
      , COUNT(MANAGER_ID) 사수있는사원수
      , ROUND(AVG(SALARY)) 평균급여
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
 ORDER BY 부서코드;

-- 성별 별 사원수
SELECT SUBSTR(EMP_NO, 8, 1) 성별
     , COUNT(*) 사원수
  FROM EMPLOYEE
 GROUP BY SUBSTR(EMP_NO, 8, 1);
--> GROUP BY 절에는 함수식도 들어갈 수 있음

-- DECODE로 성별 문자로 표기
SELECT DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남자', '2', '여자') 성별
     , COUNT(*) 사원수
  FROM EMPLOYEE
 GROUP BY SUBSTR(EMP_NO, 8, 1);
--> 총 23명을 성별 기준에 따라 2개의 그룹으로 나눈 뒤 각각 카운트

---------------------------------------------------------------------------

/*
    <HAVING 절>
    
    그룹에 대한 조건식을 제시하고 싶을 때 사용하는 구문
    즉 그룹함수식이 포함된 조건식을 제시하는 용도
    
*/
-- 각 부서별 평균 급여가 300만원 이상인 부서들만 조회
SELECT DEPT_CODE, ROUND(AVG(SALARY))
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
 HAVING ROUND(AVG(SALARY)) >= 3000000
 ORDER BY DEPT_CODE;
--> WHERE절에는 그룹함수 호출식 사용 불가능

-- 각 부서별 보너스를 받는 사원이 없는 부서만 조회
SELECT DEPT_CODE, COUNT(BONUS)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
HAVING COUNT(BONUS) = 0;

-----------------------------------------------------------------------------

/*
    <SELECT 문의 표현법 및 각 절의 실행순서>
    
    SELECT 조회하고자하는컬럼명 or * or 리터럴 or 산술연산식 or 함수식 
      FROM 조회하고자하는테이블명 or DUAL(흰 도화지 가상테이블)
     WHERE 조건식(그룹함수는 안됨)
     GROUP BY 그룹기준이되는컬럼명 or 함수식
    HAVING 조건식(WHERE로 못할때 그룹함수 포함된 것)
     ORDER BY [정렬기준해당컬럼명 or 별칭 or 컬럼순번] [ASC or DESC]
              [NULLS FIRST or NULLS LAST];
              
    FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY
*/

-----------------------------------------------------------------------------

/*
    <집합연산자 SET OPERATOR>
    두 개 이상의 SELECT문으로 하나의 쿼리문을만드는 연산자
    
    주의사항: SELECT문들의 절이 항상 일치해야함. 
             SELECT 절이 다르면 RESULT SET을 합치는 개념이므로 열의 개수가 달라지기 때문
    
    1. UNION
    합집합, 두 쿼리문을 수행한 결과값 합하고 중복값 제거.(OR 의미)
    
    2. INTERSECT
    교집합, 두 쿼리문을 수행한 결과값의 중복된 값.(AND 의미)
    
    3. UNION ALL
    합집합+교집합, 중복값 제거 안함
    
    4. MINUS
    차집합(집합-교집합), 선행 쿼리문 결과값에서 후행 쿼리문의 결과값을 제거
    연산 순서에 따라 결과가 다르게 나오니까 주의
    
*/

-- 1. UNION: 두 쿼리문을 수행한 결과값을 더하지만 중복된 결과는 한 번만 조회

-- 부서코드가 D5거나 급여가 300만원 초과인 사원들 조회(사번, 사원명, 부서코드, 급여)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5';
--> 부서코드가 D5인 사원들만 조회
--> 6명 조회(박세진, 박주현, 서가영, 선종범, 이정민, 최경환)

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000;
--> 급여가 300만원 초과 사원만 조회
--> 8명 조회(김가현, 강호형, 김동규, 김유림, 박성진, 선종범, 최경환, 전지연)

-- 부서코드가 D5거나 급여가 300만원 초과인 사원들 조회(사번, 사원명, 부서코드, 급여)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
 UNION
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY EMP_NAME;
--> 12명 조회(6명+8명-2명)


-- 2. UNION ALL: 여러개의 쿼리 결과를 더함(중복값 여러 개 나올 수 있음)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
 UNION ALL
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY EMP_NAME;
--> OR 연산자로도 가능, 14명 조회

-- 참고)
-- UNION ALL 연산속도가 UNION보다 빠름

-- 3. INTERSECT: 여러 쿼리문 결과 중복부분을 한 번만 조회

-- 부서코드가 D5, 급여가 300만원 초과인 사원 사번, 이름, 부서코드, 급여
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
INTERSECT
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY EMP_NAME;
--> AND 연산자로도 가능


-- 4. MINUS: 선행 쿼리결과에 후행 쿼리결과를 뺀 나머지
-- 부서코드가 D5인 사람들 중에서 급여가 300만원 초과인 사원들 제외하고 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
 MINUS
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY EMP_NAME;
--> 4명 조회.
--> 순서 바뀌면? - 6명 조회


-- 

















