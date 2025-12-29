/*
    <SUBQUERY - 서브쿼리)
    
    주된 SQL문(SELECT, INSERT, UPDATE, DELETE, CREAT, ...) 안에 포함된 또 하나의 SELECT문
    즉, SELECT문을 중첩해서 쓰겠다.
    - 메인 SQL문을 위해 안 쪽에서 보조역할을 하는 쿼리문
    - 소괄호로 묶어서 표현함

    하나의 주된 SQL문: 메인쿼리 -> 서브쿼리 실행 결과를 가진 후 실행됨
    포함된 SELECT문: 서브쿼리 -> 먼저 실행됨
*/

-- 간단한 서브쿼리 예제 1

-- 이태훈 사원과 같은 부서인 사원들
-- 1) 먼저 이태훈 사원의 부서코드를 조회
SELECT DEPT_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME = '이태훈'; --> D8 부서코드임을 확인함

--2) 부서코드가 D8인 사람들 조회
SELECT EMP_NAME
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D8';

-- 1), 2)를 하나의 쿼리문으로 합치기
SELECT EMP_NAME -- 메인쿼리
  FROM EMPLOYEE
 WHERE DEPT_CODE = (SELECT DEPT_CODE -- 서브쿼리
                      FROM EMPLOYEE
                     WHERE EMP_NAME = '이태훈');

-- 간단 서브쿼리 예제 2

-- 전체 사원의 평균급여보다 많은 급여를 받고 있는 사원의 사번, 이름, 직급코드, 급여 조회
-- 1) 사원들의 평균급여 구하기
SELECT ROUND(AVG(SALARY))
  FROM EMPLOYEE; --> 3,047,663원

-- 2) 급여가 3,047,663 이상인 사원들 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 3047663;

-- 1), 2) 합치기
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT ROUND(AVG(SALARY))
                    FROM EMPLOYEE);

-----------------------------------------------------------------------------

/*
    * 서브쿼리의 구분(종류)
    
    - 서브쿼리 부분만 실행했을 때 그 결과가 몇 행, 몇 열이냐에 따라 구분됨.
    
    1. 단일행 (단일열) 서브쿼리: 서브쿼리의 RESULT SET이 오직 1개일 때
    2. 다중행 (단일열) 서브~: 서브쿼리~ 여러 행일 때(1열)
    3. (단일행) 다중열 서브~: 서브~ 여러 열일 때(1행)
    4. 다중행 다중열 서브~: 여러 행, 열일 때
    => 위 4종류는 모두 조건식에서 기술(WHERE or HAVING절)
    => 서브쿼리는 SELECT절 내부에서도 쓸 수 있음: 스칼라 서브쿼리(성능상 사용X 권장)
    
    5. 서브쿼리 FROM절에도 사용 가능
    => INLINE VIEW(인라인 뷰)
    
    => 서브쿼리를 수행한 결과가 몇행 몇열인지에 따라 메인쿼리에서 사용하는 연산자 종류 달라짐
    
*/

/*
    1. 단일행 서브쿼리(SINGLE ROW SUBQUERY): 서브쿼리의 조회 결과값이 오직 1개
    
    - 메인쿼리에서 일반적인 연산자는 사용 가능 =, !=, <=,...
    
*/

-- Q. 전직원 평균 급여보다 더 적게받는 사원명, 직급코드. 급여 조회
-- 1) 먼저 평균급여 구하기
SELECT ROUND(AVG(SALARY))
  FROM EMPLOYEE;

-- 2) 평균보다 적게받는 사원
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY < (SELECT ROUND(AVG(SALARY))
                   FROM EMPLOYEE); -- 결과값 단일행

-- Q. 최저급여를 받는 사원 사번, 사원명, 직급코드, 급여, 입사일 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY, HIRE_DATE
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MIN(SALARY)
                  FROM EMPLOYEE); -- 결과값 단일행

-- Q. 박세진 사원 급여보다 더 많이 받는 사번, 이름, 부서코드, 급여 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT SALARY
                    FROM EMPLOYEE
                   WHERE EMP_NAME = '박세진'); -- 결과값 단일행

-- Q. 박세진 사원 급여보다 더 많이 받는 사원들의 사번, 이름, 부서명, 급여 조회
--> 오라클전용 구문
SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '미정'), SALARY
  FROM EMPLOYEE, DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID(+)
   AND SALARY > (SELECT SALARY
                   FROM EMPLOYEE
                  WHERE EMP_NAME = '박세진'); -- 결과값 단일행

--> ANSI
SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '미정'), SALARY
  FROM EMPLOYEE
  LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
 WHERE SALARY > (SELECT SALARY
                   FROM EMPLOYEE
                  WHERE EMP_NAME = '박세진'); 

-- Q. 김동윤 사원과 같은 부서 사원들의 사번, 이름, 전화번호, 직급명 조회(김동윤 제외)
--> 오라클전용 구문
SELECT EMP_ID, EMP_NAME, PHONE, JOB_NAME
  FROM EMPLOYEE, JOB
 WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE
   AND DEPT_CODE = (SELECT DEPT_CODE
                      FROM EMPLOYEE
                     WHERE EMP_NAME = '김동윤')
   AND NOT EMP_NAME = '김동윤';

--> ANSI
SELECT EMP_ID, EMP_NAME, PHONE, JOB_NAME
  FROM EMPLOYEE
  JOIN JOB USING (JOB_CODE)
 WHERE DEPT_CODE = (SELECT DEPT_CODE
                      FROM EMPLOYEE
                     WHERE EMP_NAME = '김동윤')
   AND NOT EMP_NAME = '김동윤';

-- Q. 급여합이 가장 큰 부서 조회(부서코드, 부서명, 급여합 조회)
-- 1) 부서 별 급여 합 조회
SELECT DEPT_CODE, SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE; --> 총 23개 급여값을 총 7개의 그룹으로 나눠 각각의 sum을 구함
 
-- 2) 그 중 급여 합이 가장 큰 부서 보기
SELECT MAX(SUM(SALARY))
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;

-- 3) 총 급여액의 부서 정보를 구하기
SELECT DEPT_CODE, DEPT_TITLE, SUM(SALARY)
  FROM EMPLOYEE, DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID
 GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                        FROM EMPLOYEE
                       GROUP BY DEPT_CODE); -- 결과값 단일행

--------------------------------------------------------------------------

/*
    2. 다중행 (단일열) 서브쿼리(MULTI ROW SUBQUERY): 리절트 값이 여러행 1열
    
    * 사용할 수 있는 연산자 종류
    - 컬럼명 IN (서브쿼리): 비교대상컬럼명 IN (값, 값, 값, ...)
    -> 조회된 여러 개의 결과값 중에서 하나라도 일치할 경우 / NOT IN: "없으면" 의미
    
    컬럼명 >= =< > < ANY/ALL (서브쿼리)
    
    컬럼명 > ANY (서브쿼리)
    -> 조회된 여러개의 결과값 중에서 하나라도 클 경우(여러 결과값 중 가장 작은 값보다 무조건 클 경우)
    컬럼명 < ANY (서브쿼리)
    -> 반대
    
    컬럼명 > ALL (서브쿼리)
    -> 조회된 여러개의 결과값 중 모든값보다 클 경우(가장 큰 값보다 무조건 클 경우)
    컬렴명 < ALL (서브쿼리)
    -> 반대
    

*/

-- Q. 각 부서별 최고급여를 받는 사원의 이름, 직급코드, 급여 조회
-- 1) 각 부서별 최고급여 먼저 구하기
SELECT MAX(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;
--> (2,890,000 3,660,000 8,000,000 , 3,760,000 3,900,000 2,490,000 2,550,000)

-- 2) 위 급여액 받는 사원들 조회
SELECT EMP_NAME, JOB_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY IN (2890000, 3660000, 8000000, 3760000, 3900000, 2490000, 2550000);

-- 1), 2) 쿼리 합치기
SELECT EMP_NAME, JOB_CODE, SALARY
  FROM EMPLOYEE
 WHERE SALARY IN (SELECT MAX(SALARY)
                    FROM EMPLOYEE
                   GROUP BY DEPT_CODE); -- 결과값 다중행 - 7행 1열

-- Q. 강호형 또는 최경환 사원과 같은 부서인 사원들의 사원명, 부서코드, 급여 조회
-- 1) 강호형, 최경환 부서코드 구하기
SELECT DEPT_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME IN ('강호형', '최경환'); -- D9, D5

-- 2) 위 부서에 소속된 사원 구하기
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE IN ('D9', 'D5');

-- 1), 2) 합치기
SELECT EMP_NAME, DEPT_CODE, SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE IN (SELECT DEPT_CODE
                       FROM EMPLOYEE
                      WHERE EMP_NAME IN ('강호형', '최경환')); -- 결과값 다중행
                      
-- Q. 사원 < 대리 < 과장 < 차장 < 부장
-- 대리임에도 불구하고 과장의 급여보다 더 많이 받는 직원 사번, 이름, 직급명, 급여 조회

-- 1) 과장 급여 조회
SELECT SALARY
  FROM EMPLOYEE E, JOB J
 WHERE E.JOB_CODE = J.JOB_CODE -- 연결고리 조건
   AND J.JOB_NAME = '과장'; -- 추가적인 조건
--> 2,200,000 2,500,000 3,760,000

-- 2) 위 급여보다 높은 급여를 받는 직원 조회 == 과장보다 급여 높으면 끝
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
  FROM EMPLOYEE E, JOB J
 WHERE E.JOB_CODE = J.JOB_CODE
   AND SALARY > ANY (SELECT SALARY
                       FROM EMPLOYEE E, JOB J
                      WHERE E.JOB_CODE = J.JOB_CODE
                        AND J.JOB_NAME = '과장') -- 결과값 다중행
   AND J.JOB_NAME = '대리';
   
-- Q. 과장임에도 불구하고 모든 차장의 급여보다 많이 받는 직원 조회. 사번, 이름, 직급명, 급여
-- 1) 차장 급여 조회
SELECT SALARY
  FROM EMPLOYEE
  JOIN JOB USING (JOB_CODE)
 WHERE JOB_NAME = '차장';
--> 2,800,000 1,550,000 2,490,000 2,480,000

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
  FROM EMPLOYEE
  JOIN JOB USING (JOB_CODE)
 WHERE SALARY > ALL (SELECT SALARY
                       FROM EMPLOYEE
                       JOIN JOB USING (JOB_CODE)
                      WHERE JOB_NAME = '차장') -- 결과값 다중행
   AND JOB_NAME = '과장';

------------------------------------------------------------------------------

/*
    3. (단일행) 다중열 서브쿼리
    
    상황에 따라 단일행 서브쿼리로도 대체 가능함
*/

-- Q. 서가영 사원과 같은 부서코드, 직급코드에 해당하는 사원의 이름, 부코, 직코, 입사일 조회
-- 1) 서가영 사원의 부서코드, 직급코드 조회
SELECT DEPT_CODE, JOB_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME = '서가영';
--> D5, J5

SELECT EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
   AND JOB_CODE = 'J5';

-- 단일행 단일열 서브쿼리 버전
SELECT EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
  FROM EMPLOYEE
 WHERE DEPT_CODE = (SELECT DEPT_CODE
                      FROM EMPLOYEE
                     WHERE EMP_NAME = '서가영') -- 결과값 단일행
   AND JOB_CODE = (SELECT JOB_CODE
                     FROM EMPLOYEE
                    WHERE EMP_NAME = '서가영'); -- 결과값 단일행
--> 메인쿼리 안에 서브쿼리 두 개 이상 들어가도 됨

-- 단일행 다중열 서브쿼리 버전
SELECT EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
  FROM EMPLOYEE
 WHERE (DEPT_CODE, JOB_CODE) = (SELECT DEPT_CODE, JOB_CODE
                                  FROM EMPLOYEE
                                 WHERE EMP_NAME = '서가영'); -- 결과값 다중열

-- Q. 이주원 사원과 같은 부서,직급코드 사원들의 사번, 이름, 직급코드, 사수사번 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, MANAGER_ID
  FROM EMPLOYEE
 WHERE (DEPT_CODE, JOB_CODE) = (SELECT DEPT_CODE, JOB_CODE
                                  FROM EMPLOYEE
                                 WHERE EMP_NAME = '이주원')

-- Q. 최윤호 사원과 같은 직급코드, 사수사번의 사원 사번, 이름, 직급코드. 사수사번 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, MANAGER_ID
  FROM EMPLOYEE
 WHERE (JOB_CODE, MANAGER_ID) = (SELECT JOB_CODE, MANAGER_ID
                                  FROM EMPLOYEE
                                 WHERE EMP_NAME = '최윤호'); -- 결과값 다중열
--> 단일행 다중열 서브쿼리는 내부적으로 여러개를 동등비교하는 케이스일 경우
--> 단일행 단일열 서브쿼리로 대체 가능
-- 한번에 동등비교 가능하지만 동등비교할컬럼명 순서 유의

-------------------------------------------------------------------------------

/*
    4. 다중행 다중열 서브쿼리
    
    서브쿼리 조회결과가 여러행 여러컬럼일 경우 => IN 사용
*/

-- 각 직급별 최소급여를 받는 사원들 조회 사번, 이름, 직급코드, 급여
-- 우선 각 직급별 최소급여 조회
SELECT JOB_CODE, MIN(SALARY)
  FROM EMPLOYEE
 GROUP BY JOB_CODE;

/*
    J2	3700000
    J7	1380000
    J3	3400000
    J6	2000000
    J5	2200000
    J1	8000000
    J4	1550000
*/
-- 위의 목록들 중에 일치하는 사원 고르기
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
  FROM EMPLOYEE
/*
    WHERE (JOB_CODE, SALARY) = ('J2', 3700000)
    OR (JOB_CODE, SALARY) = ('J7', 1380000)
    OR (JOB_CODE, SALARY) = ('J3', 3400000)
    OR (JOB_CODE, SALARY) = ('J6', 2000000)
    OR (JOB_CODE, SALARY) = ('J5', 2200000)
    OR (JOB_CODE, SALARY) = ('J1', 8000000)
    OR (JOB_CODE, SALARY) = ('J4', 1550000);
*/
 WHERE (JOB_CODE, SALARY) IN (('J2', 3700000)
                            , ('J7', 1380000)
                            , ('J3', 3400000)
                            , ('J6', 2000000)
                            , ('J5', 2200000)
                            , ('J1', 8000000)
                            , ('J4', 1550000));
--> 컬럼값 여러개를 한번에 묶어서 리터럴과 동등비교할 수 없음(문법문제)
-- 컬럼값 여러개를 한번에 묶어서 동등비교를 하고싶다면 동등비교 연산자 기준 오른쪽은 서브쿼리만 사용 가능(리터럴 불가)

-- 다중행 다중열 서브쿼리 버전으로 변경
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
  FROM EMPLOYEE
 WHERE (JOB_CODE, SALARY) IN (SELECT JOB_CODE, MIN(SALARY)
                                FROM EMPLOYEE
                               GROUP BY JOB_CODE); -- 결과값 다중행 다중열

-- Q. 각 부서별 최고급여를 받는 사원들의 사번, 이름, 부서코드, 급여 조회
SELECT DEPT_CODE, MAX(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;

/*
    NULL 2890000
    D1 	 3660000
    D9	 8000000
    D5	 3760000
    D6	 3900000
    D2	 2490000
    D8	 2550000
*/

-- 위 부서별 급여받는 사원 조회
SELECT EMP_ID, EMP_NAME, NVL(DEPT_CODE, '없음'), SALARY
  FROM EMPLOYEE
 WHERE (NVL(DEPT_CODE, '없음'), SALARY) IN (SELECT NVL(DEPT_CODE, '없음'), MAX(SALARY)
                                FROM EMPLOYEE
                               GROUP BY DEPT_CODE) -- 결과값 다중행 다중열
 ORDER BY DEPT_CODE ASC;

------------------------------------------------------------------------------

/*
    5. 인라인 뷰
    
    FROM절에 서브쿼리를 제시하는 것(테이블명 대신)
    FROM 테이블명
    FROM (서브쿼리)
    => 서브쿼리를 수행한 RESULT를 테이블 대신 이용하겠다.
    즉, RESULT SET으로부터 조회를 하겠다.
    
*/

-- Q. 보너스 포함 연봉이 3000만원 이상인 사원들의 사원, 이름, 보너스포함연봉, 부서코드 조회
--> 인라인뷰 쓰지 않는 경우
SELECT EMP_ID
     , EMP_NAME
     , (SALARY + (SALARY*NVL(BONUS, 0))) * 12  AS "보너스 포함 연봉"
     , NVL(DEPT_CODE, '미정')
  FROM EMPLOYEE
 WHERE (SALARY + (SALARY*NVL(BONUS, 0))) * 12 >= 30000000;

--> 인라인뷰 쓴 경우
SELECT EMP_ID, EMP_NAME, "보너스 포함 연봉", DEPT_CODE
  FROM (
        SELECT EMP_ID
             , EMP_NAME
             , (SALARY + (SALARY*NVL(BONUS, 0))) * 12  AS "보너스 포함 연봉"
             , DEPT_CODE
          FROM EMPLOYEE -- 전체 사원들의 보너스 포함 연봉까지 조회 RESULT SET
       )
 WHERE "보너스 포함 연봉" >= 30000000;
--> FROM절 서브쿼리(인라인뷰)에서 이미 컬럼에 별칭 부여하고 조회해서
-- 바깥쪽 메인쿼리 WHERE절 조건식에 별칭사용 가능(FROM이 맨처음 실행되니깐)
-- 인라인뷰를 사용하지 않으면 WHERE절에 별칭사용 불가(WHERE이 SELECT보다 먼저 실행되어서

--> 인라인뷰는 주로 SELECT문의 각 절의 실행순서를 바꾸고 싶을 경우 사용
-- 별칭, 정렬(ORDER BY) 등

-- 인라인뷰를 많이 사용하는 예시
-- TOP-N 분석: 데이터베이스 테이블 상 데이터들 중 최상위 몇 자료를 보기 위해 사용하는 개념
-- ex) 급여가 가장 높은 N명, 입사일이 가장 오래된/최근인 N명

-- 전 직원 중 급여가 가장 높은 상위 5명 조회
-- ROWNUM: 오라클에서 기본적으로 제공해주는 컬럼(조회된 순서대로 1에서부터 순번 부여)
SELECT ROWNUM, EMP_NAME, SALARY
  FROM EMPLOYEE
 WHERE ROWNUM <= 5
 ORDER BY SALARY DESC;
-- EMPLOYEE 테이블로부터(FROM) 위에서 5개 데이터를 가져오고(WHERE),
-- 조회된 컬럼 제시하고(SELECT), 급여 높은 순서대로 정렬(ORDER BY)
--> ORDER BY가 마지막으로 실행되어서 제대로된 TOP-N 분석결과가 나오지 않음.

-- TOP-N 분석의 핵심은 일단 ORDER_BY로 정렬하고나서 정렬된 데이터를 기준으로 조회
SELECT ROWNUM "순번", EMP_NAME "사원명", SALARY "급여"
  FROM (
            SELECT *
              FROM EMPLOYEE
             ORDER BY SALARY DESC
       )
 WHERE ROWNUM <= 5;
--> 큰 FROM절 1빠(FROM - SELECT - ORDER BY) - WHERE절 2빠 - SELECT절
-- ORDER BY가 WHERE보다 먼저 실행되게 유도함

-- 인라인뷰 사용 팁
-- SELECT ROWNUM, * -- SELECT *은 그 자체로만 사용 가능(다른 컬럼명들이랑 같이 못씀)
-- 근데 별칭 붙이면 가능

SELECT ROWNUM, E.*
  FROM (
          SELECT *
            FROM EMPLOYEE
           ORDER BY SALARY DESC
       ) E
 WHERE ROWNUM <= 5;
-- 인라인뷰도 별칭 사용 가능, 별칭.컬럼명으로 제시 가능

-- Q. 부서별 평균급여 높은 3개 부서의 부서코드, 평균급여 조회

SELECT ROWNUM, DEPT_CODE, "평균 급여"
  FROM (
            SELECT DEPT_CODE, AVG(SALARY) "평균 급여"
              FROM EMPLOYEE
             GROUP BY DEPT_CODE
             ORDER BY AVG(SALARY) DESC
       )
 WHERE ROWNUM <= 3;
--> 인라인뷰 내부에 함수식이 있으면 컬럼에 반드시 별칭 있어야함.
-- 없으면 메인쿼리에서 해당 컬럼을 제대로 찾을 수 없음

-- Q. 가장 최근 입사한 사원 5명의 사원명, 급여, 입사일 조회
SELECT ROWNUM, E.*
  FROM (
            SELECT EMP_NAME, SALARY, HIRE_DATE
              FROM EMPLOYEE
             ORDER BY HIRE_DATE DESC
             
        ) E
 WHERE ROWNUM < 5;
--> 인라인뷰: 탑엔 분석, 게세판 목록 조회 시 사용

-------------------------------------------------------------------------------

/*
    <TOP-N 분석 관련 내용(서브쿼리 관련 아님)>
    
    순위를 매기는 함수
    
    1. RAMK() OVER(정렬기준)
    : 공동 1위가 3명이라고 한다면 그 다음 순위가 4위
    
    2. DENSE_RANK() OVER(정렬기준)
    : 공동 1위가 3명이면 다음 순위는 2위(촘촘)
    
    => 순위를 매기는 함수들을 WINDOW FUNCTION이라고 부른다.
    WINDOW FUNCTION은 SELECT절에서만 사용 가능
    
*/

-- Q. 사원들의 급여 높은 순서대로 매겨서 사원명, 급여, 순위 조회
SELECT ROWNUM, EMP_NAME, SALARY
  FROM EMPLOYEE
 ORDER BY SALARY DESC;
--> ORDER BY가 마지막으로 실행되기 때문에 SALARY 기준으로는 정렬되는데, ROWNUM은 뒤죽박죽

SELECT ROWNUM, EMP_NAME, SALARY
  FROM (
          SELECT EMP_NAME, SALARY
            FROM EMPLOYEE
          ORDER BY SALARY DESC
        );
--> 공동값처리 순위 애매함
        
-- 순위매기는 함수 사용시: ORDER BY를 위로 올려서 먼저 해주는 느낌??
SELECT /*DENSE_*/RANK() OVER(ORDER BY SALARY DESC) " 순위", EMP_NAME, SALARY
  FROM EMPLOYEE
--> DENS 사용시 ㅇㅇ

-- 순위를 매기는 함수 사용해서도 TOP-N 분석 가능
-- 급여가 가장 높은 5명만 조회
SELECT EMP_NAME, SALARY, RANK() OVER(ORDER BY SALARY DESC) "순위"
  FROM EMPLOYEE
 WHERE RANK() OVER(ORDER BY SALARY DESC) "순위" <= 5;
 --> 오류: WINDOW 함수는 WHERE절에서 사용 불가능함. SELECT절에서만 사용 가능
 
-- 해결방법) 인라인뷰 이용하면 가능
SELECT *
  FROM (
          SELECT EMP_NAME, SALARY, RANK() OVER(ORDER BY SALARY DESC) "순위"
            FROM EMPLOYEE
        )
 WHERE "순위" <= 5;
--> ROWNUM을 사용하든 RANK 사용하든 TOP-N에서는 인라인뷰 사용 필수임
        















