/*
    <JOIN>
    
    - 두 개 이상의 테이블로부터 데이터를 함께 조회하고자 할 때 사용하는 구문(SELECT문).
    조회 결과는 하나의 결과물로 나옴(RESULT SET)
    
    - 관계형 데이터베이스에서는 최소한의 데이터를 각각의 테이블에 데이터를 쪼개서 보관함.
    -> 데이터의 중복을 최소화하기 위해 최대한 쪼개는 것(정규화 작업)
    
    - 즉 최대한 테이블을 쪼갠 데이터를 한 개의의 테이블로 조회하는 것은 우리가 원하는
    디테일한 데이터를 온전하게 하기 어려움.
    
    - 그래서 우리는 JOIN 구문을 통해 테이블 간 관계를 맺어 같이 조회해야 함.
    무작정 하는 것은 아니고, 연결고리에 해당하는 컬럼을 매칭해서 JOIN 해야 함(외래키).
    
    - JOIN은 크게 "오라클 전용 구문"과 "ANSI(미국국립표준협회)구문"으로 문법이 나뉜다.
    
    | 오라클 전용 구문                  | ANSI 구문
    ===========================================================================
    등가조인(EQUAL JOIN)               | 내부조인(INNER JOIN)
    
    포괄조인(LEFT/RIGHT JOIN)          | 외부조인(LEFT/RIGHT/FULL OUTER JOIN)
                                        FULL OUTER JOIN -> 오라클 전용 구문에서는 불가
    
    카테시안 곱(CARTESIAN PRODUCT)      | 교차조인(CROSS JOIN)
    ---------------------------------------------------------------------------
                    자체조인(SELF JOIN), 비등가조인(NON EQUAL JOIN)
    
     
*/

-- JOIN을 배워야 하는 이유
-- EMPLOYEE 테이블로부터 사원의 사번, 사원명, 부서코드 // 부서명까지 알아내고 싶다면?
SELECT EMP_ID, EMP_NAME, DEPT_CODE
  FROM EMPLOYEE;
--> DEPT_CODE
  
SELECT DEPT_ID, DEPT_TITLE
  FROM DEPARTMENT;
--> DEPT_TITLE

-- EMPLOYEE 테이블로부터 전체 사원들의 사번, 사원명, 직급코드 // 직급명까지 알고싶다면?
SELECT EMP_ID, EMP_NAME, HOB_CODE
  FROM EMPLOYEE;
  --> JOB_CODE
  
SELECT JOB_CODE, JOB_NAME
  FROM JOB;
--> JOB_CDOE

--> 매 번 여러 개의 SELECT문 조회 결과를 왔다갔다 확인하기 귀찮, 한번에 조회하고픔.
--> 연결고리 컬람 찾아서 매칭하면 조회할 수 있음

----------------------------------------------------------------------------
/*
    1. 등가조인(EQUAL JOIN) / 내부조인 (INNER JOIN)
    
    연결고리 컬럼값이 일치하는 행들만 조인돼서 조회하겠다.
    즉, 일치하지 않는 값들은 조회에서 제외하겠다.
*/

-->> 오라클전용 구문
-- FROM절에 조회하고자 하는 테이블명들을 ,로 나열
-- WHERE절에 매칭할 연결고리 컬럼에 대한 컬럼명 조건 기술

-- 전체사원들의 사번, 사원명, 부서코드, 부서명 조회
-- 1) 연결고리 컬럼명이 서로 다른 경우
-- EMPLOYEE 테이블의 DEPT_CODE / DEPARTMONT 테이블의 DEPT_ID
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
  FROM EMPLOYEE, DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID;
--> 2명이 조회 안됨 => DEPT_CODE와 DEPT_TITLE 서로 NULL과 일치하는 값이 없어서 제외.

-- 전체 사원들의 사번, 사원명, 직급코드 조회
-- 2) 연결고리 컬럼명이 서로 같을 경우
-- 테이블명.컬렴명
SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
  FROM EMPLOYEE E, JOB J
 WHERE E.JOB_CODE = J.JOB_CODE;
--> AMNIBUOUSLY(애매모호한) 오류 발생
-- 테
--이블명에도 붙있 수 있음. 별칭.컬럼명

----------------------------------------------------------------------------

--> ANSI 구문
-- fROM 절에 기준이 되는 테이블명 하나만 기술한 뒤
-- 스 뒤에 JOIN절에서 같이 조회하고자 하는 테이블명을 마저 기술
-- 또한 매칭할 연결고리 컬럼에 대한 조건도 JOIN절에 같이 기술
-- USING 구문 / ON 구문

-- 사번, 사원명, 부서코드, 부서명
-- 1) 연결고리 컬럼명이 다른 경우 ==> 오직 ON 구문 사용
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
  FROM EMPLOYEE
  INNER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
  --> INNER JOIN에서 INNER는 생략 가능

-- 사번, 사원명, 직급코드, 직급명
-- 2) 연결고리 컬럼명이 같은 경우 ==> USING, ON 구문 둘다 사용 가능

-- 2-1) ON 구문 이용
-- AMBIGUOUSLY 오류 발생, 마찬가지로 테이블명 or 별칭 명시해야됨
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB_NAME
  FROM EMPLOYEE
 INNER JOIN JOB ON (EMPLOYEE.JOB_CODE = JOB.JOB_CODE);
 
-- 2-2) USING 구문 이용
-- ON 구문은 연결고리에 대한 조건식을 내가 직접 기술하는 방법임
-- USING 구문은 연결고리 컬럼명만 제시하면 알아서 동등비교를 수행하는 구문
-- 연결코리 컬럼명 하나만 USING 옆에 스면 알아서 매칭해줌(오류발생X)

SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
  FROM EMPLOYEE
  JOIN JOB USING (JOB_CODE); -- INNER 생략
  
--참고) 특이케이스
-- 연결고리 컬럼명이 동일한 경우(DEPT_ 동일) => "NATURAL JOIN"(자연조인)
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
  FROM EMPLOYEE
  NATURAL JOIN JOB;
--> 두 개의 테이블명만 제시하고, 연결고리에 대한 기술 X
-- 운 좋게도 두 개의 테이블에 일치하는 컬럼이 유일하게 한 개씩 존재
--> 그 유일 일치한 컬럼이 알아서 조인 발생

-- JOIN시 연결고리에 대한 조건 뿐만 아니라 추가적인 조건도 제시 가능

-- 직급이 대리인 사원들의 사번, 이름, 급여, 직급명 조회
-->> 오라클전용 구문
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
  FROM EMPLOYEE, JOB
 WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE -- 연결고리 조건
   AND JOB_NAME = '대리'; -- 추가적인 조건

--> ANSI 구문
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
  FROM EMPLOYEE E
  JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE);
  
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
  FROM EMPLOYEE
  JOIN JOB USING (JOB_CODE);

---- <연습문제> ----
-- 1. 부서가 인사관리부인 사원들의 사번, 사원명, 보너스를 조회
--> 오라클 구문
SELECT EMP_ID, EMP_NAME, NVL(BONUS, 0), DEPT_TITLE
  FROM EMPLOYEE, DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID
   AND DEPT_TITLE = '인사관리부';

--> ANSI 구문
SELECT EMP_ID, EMP_NAME, NVL(BONUS, 0), DEPT_TITLE
  FROM EMPLOYEE
  JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
   AND DEPT_TITLE = '인사관리부';

-- 2. 부서가 총무부가 아닌 사원들의 사원명, 급여, 입사일을 조회
--> 오라클 구문
SELECT EMP_NAME, SALARY, HIRE_DATE
  FROM EMPLOYEE, DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID
   AND NOT DEPT_TITLE = '총무부'
 UNION
SELECT EMP_NAME, SALARY, HIRE_DATE
  FORM EMPLOYEE
 WHERE DEPT_CODE IS NULL;


--> ANSI 구문
SELECT EMP_NAME, SALARY, HIRE_DATE, DEPT_TITLE
  FROM EMPLOYEE
  JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
   AND NOT DEPT_TITLE = '총무부';

-- 3. 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회
--> 오라클 구문
SELECT EMP_ID, EMP_NAME, NVL(BONUS, 0), DEPT_TITLE
  FROM EMPLOYEE,DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID
   AND BONUS IS NOT NULL;


--> ANSI 구문
SELECT EMP_ID, EMP_NAME, NVL(BONUS, 0), DEPT_TITLE
  FROM EMPLOYEE
  JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
   AND BONUS IS NOT NULL;


-- 4. 아래의 두 테이블을 참고해서 부서코드, 부서명, 지역코드, 지역명 조사
SELECT * FROM DEPARTMENT; -- DEPT_ID, DEPT_TITLE, LOCATION_ID(이거)
SELECT * FROM LOCATION; -- LOCAL_CODE(이거), NATIONAL_CODE, LOCAL_NAME
--> 오라클 구문
SELECT DEPT_ID, DEPT_TITLE, LOCAL_CODE, LOCAL_NAME
  FROM DEPARTMENT, LOCATION
 WHERE LOCATION_ID = LOCAL_CODE;



--> ANSI 구문
SELECT DEPT_ID, DEPT_TITLE, LOCAL_CODE, LOCAL_NAME
  FROM DEPARTMENT
  JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
-- 4번 문제에서, 아시아 지역에 위치한 부서만을 보고싶음
 WHERE LOCAL_NAME LIKE '%ASIA%';

------------------------------------------------------------------------------

/*
    2. 포괄조인 / 외부조인 (OUTER JOIN) OUTER 생략 가능
    
    테이블 간의 JOIN 시 일치하지 않는 행도 포함해서 조회 가능
    단, 반드시 LEFT / RIGHT 지정해야함 => 기준이 되는 테이블의 방향
*/

-- EMPLOYEE 테이블로부터 전체사원들의 사원명, 급여, 부서명 조회
-- 기존
SELECT EMP_NAME, SALARY, DEPT_TITLE
  FROM EMPLOYEE
  JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
--> INNER JOIN 적용시 연결고리 NULL 값 때문에 2명이 조회 안댐

-- 1) LEFT OUTER JOIN
-- 두 테이블 중 왼편!에 기술된 테이블을 기준!으로 하여 JOIN
-- 무조건, NULL이라도, 왼편에 기술된 테이블의 데이터 조회함.

-- ANSI 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
  FROM EMPLOYEE
  LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
--> FROM EMPLOYEE 했으니까, LEFT가 EMPLOYEE로 설정됨.
-- OUTER 생략 가능

-- 오라클전용 구문
SELECT EMP_NAME, SALARY, DEPT_TITLe
  FROM EMPLOYEE, DEPARTMENT -- 순서상관없음
 WHERE DEPT_CODE = DEPT_ID(+); -- 순서는 상관없음
--> LEFT 반대로 (+)

-- 2) LIGHT OUTER JOIN
-- ANSI 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
  FROM DEPARTMENT
 RIGHT JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID);

-- 오라클전용 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
  FROM EMPLOYEE, DEPARTMENT -- 순서상관없음
 WHERE DEPT_ID = DEPT_CODE(+); -- 순서는 상관없음

-- 3) FULL OUTER JOIN
-- 두 테이블이 가진 모든 행을 조회할 수 있도록 JOIN: LEFT + RIGHT
-- ANSI 구문만 가능!

-- ANSI 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
  FROM EMPLOYEE
  FULL JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- 오라클전용 구문으로하면 ONLY ONE OUTER-JOINED TABLE 오류 발생

----------------------------------------------------------------------------

/*
    3. 카테시안 곱(CARTESTIAN PRODUCT) / 교차조인(CROSS JOIN)
    
    모든 테이블의 각 행들이 서로서로 맵핑된 데이터가 조회됨(곱집합)
    곱해진 모든 행들의 조합이 출력됨 => 필요없는 데이터까지 출력됨
    
    실무에서 사용될 일은 드묾, 실수하면 발생
*/

-- 사원명, 부서명 조회
-- 오라클전용 구문
SELECT EMP_NAME, DEPT_TITLE
  FROM EMPLOYEE, DEPARTMENT;

-- ANSI
SELECT EMP_NAME, DEPT_TITLE
  FROM EMPLOYEE
 CROSS JOIN DEPARTMENT;

---------------------------------------------------------------------------

/*
    4. 비등가조인(NON EQUAL JOIN)
    
    연결고리에 대한 조건식에 '='를 사용하지 않는 JOIN문.
    주로, 지정한 컬럼값이 일치하는 경우가 아닌, 범위에 포함되는 경우를 매칭할 때 쓰임
*/

-- EMPLOYEE 테이블로부터 사원명, 급여, 급여등급
SELECT EMP_NAME, SALARY 
  FROM EMPLOYEE;

-- SAL_GRADE 급여구간에 SAL_LEVEL 컬럼값 맞게 EMPL 테이블에 넣어뒀지만,
-- 비등가조인 공부를 위해 테이블에 SAL_LEVEL 없다고 가정.
-- 애초에 근데 SAL_LEVEL은 파생컬럼이기 때문에 테이블 안에 없는게 맞음
-- (데이터 불일치 현상 == 데이터의 무결성이 깨짐)
SELECT * FROM SAL_GRADE;
--> 우리 회사 내규에 따른 연봉테이블

-- NON EQUAL JOIN
-- 사원명, 급여, 급여등급(SAL_GRADE 테이블의 것으로)
-- 오라클전용 구문
SELECT EMP_NAME, SALARY, SAL_GRADE.SAL_LEVEL
  FROM EMPLOYEE, SAL_GRADE
 WHERE SALARY
BETWEEN MIN_SAL
   AND MAX_SAL;

-- ANSI
SELECT EMP_NAME, SALARY, SAL_GRADE.SAL_LEVEL
  FROM EMPLOYEE
  JOIN SAL_GRADE ON (SALARY >= MIN_SAL AND SALARY <= MAX_SAL);
-- 주의사항: ANSI로 비등가조인 => ON 구문만 사용가능(USING 못함. USING 자체가 내부조인)

-- 비등가조인 예시)
-- 인터넷 쇼핑몰
-- 회원정보 테이블 / 구매금액량에 따른 구간별 회원등급 테이블(VIP)

-----------------------------------------------------------------------------

/*
    5. 자체조인(SELF JOIN)
    
    같은 테이블끼리 JOIN하는 경우
    즉, 자기자신의 테이블과 다시 조인을 맺는 경우
    - AMBIGUOUSLY 오류 주의!
*/

-- 사원의 정보 조회
SELECT EMP_ID "사원 사번"
     , EMP_NAME "사원 이름"
     , SALARY " 사원 급여"
     , MANAGER_ID "사수 사번"
  FROM EMPLOYEE;
--> 사수사번을 통해 사수의 정보를 알아내보자

SELECT * FROM EMPLOYEE E; -- 사원에 대한 정보도출용(MANAGER_ID 연결고리)
SELECT * FROM EMPLOYEE M; -- 사수에 대한 정보도출용(EMP_ID 연결고리)

-- 사원 사번, 사원명, 사원 부서코드, 사원 급여 조회
-- 사수 ~ 조회
-- 오라클 전용 구문
SELECT E.EMP_ID "사원 사번"
     , E.EMP_NAME "사원명"
     , E.DEPT_CODE "사원의 부서코드"
     , E.SALARY "사원의 급여"
     , M.EMP_ID "사수 사번"
     , M. EMP_NAME "사수명"
     , M.DEPT_CODE "사수의 부서코드"
     , M.SALARY "사수의 급여"
  FROM EMPLOYEE E, EMPLOYEE M
 WHERE E.MANAGER_ID = M.EMP_ID; -- 연결고리 조건
 --> 자체조인도 NULL값 반영 안됨 => 누락

SELECT E.EMP_ID "사원 사번"
     , E.EMP_NAME "사원명"
     , E.DEPT_CODE "사원의 부서코드"
     , E.SALARY "사원의 급여"
     , M.EMP_ID "사수 사번"
     , M. EMP_NAME "사수명"
     , M.DEPT_CODE "사수의 부서코드"
     , M.SALARY "사수의 급여"
  FROM EMPLOYEE E, EMPLOYEE M
 WHERE E.MANAGER_ID = M.EMP_ID(+);
 --> 자체조인해도 포괄조인 하면 됨
 
-- ANSI
SELECT E.EMP_ID "사원 사번"
     , E.EMP_NAME "사원명"
     , E.DEPT_CODE "사원의 부서코드"
     , E.SALARY "사원의 급여"
     , M.EMP_ID "사수 사번"
     , M. EMP_NAME "사수명"
     , M.DEPT_CODE "사수의 부서코드"
     , M.SALARY "사수의 급여"
  FROM EMPLOYEE E
  LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);

-- 사원의 부서코드 뿐만 아니라 부서명도 보고싶다면?
-- 사수의 ~보고싶다면?

-- 사원사번, 사원명, 사원부서코드, 사원부서명, 사원급여 조회
-- 사수사번, ~ 조회
-- 오라클전용 버전
SELECT E.EMP_ID "사원 사번"
     , E.EMP_NAME"사원명"
     , E.DEPT_CODE "사원 부서코드"
     , D1.DEPT_TITLE "사원 부서명"
     , E.SALARY "사원 급여"
     , M.EMP_ID "사수 사번"
     , M.EMP_NAME "사수명"
     , M.DEPT_CODE "사수 부서코드"
     , D2.DEPT_TITLE "사수 부서명"
     , M.SALARY "사수 급여"
  FROM EMPLOYEE E, EMPLOYEE M, DEPARTMENT D1, DEPARTMENT D2
 WHERE E.MANAGER_ID = M.EMP_ID(+)
   AND E.DEPT_CODE = D1.DEPT_ID(+)
   AND M.DEPT_CODE = D2.DEPT_ID(+);

-- ANSI
SELECT E.EMP_ID "사원 사번"
     , E.EMP_NAME"사원명"
     , E.DEPT_CODE "사원 부서코드"
     , D1.DEPT_TITLE "사원 부서명"
     , E.SALARY "사원 급여"
     , M.EMP_ID "사수 사번"
     , M.EMP_NAME "사수명"
     , M.DEPT_CODE "사수 부서코드"
     , D2.DEPT_TITLE "사수 부서명"
     , M.SALARY "사수 급여"
  FROM EMPLOYEE E
  LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID)
  LEFT JOIN DEPARTMENT D1 ON (E.DEPT_CODE = D1.DEPT_ID)
  LEFT JOIN DEPARTMENT D2 ON (M.DEPT_CODE = D2.DEPT_ID);
--> 3개 이상의 테이블 또한 JOIN 가능.

------------------------------------------------------------------------------
/*
    <다중 JOIN>
    
    3개 이상의 테이블을 조인하는 것
    ANSI 구문의 경우 JOIN절 작성순서가 중요함
    
    N개의 테이블을 조인한다면 연결고리 조건은 N-1개
    
*/

-- 사번, 사원명, 부서명, 직급명, 근무지역명 조회
SELECT * FROM EMPLOYEE; -- DEPT_CODE / JOB_CODE
SELECT * FROM DEPARTMENT; -- DEPT_ID /               LOCATON_ID
SELECT * FROM JOB; --                    JOB_CODE
SELECT * FROM LOCATION; --                           LOCAL_CODE

-- 오라클전용
SELECT E.EMP_ID "사번"
     , E.EMP_NAME "사원명"
     , D.DEPT_TITLE "부서명"
     , J.JOB_NAME "직급명"
     , L.LOCAL_NAME "근무지역명"
  FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L
 WHERE E.DEPT_CODE = D.DEPT_ID(+)
   AND E.JOB_CODE = J.JOB_CODE(+)
   AND D.LOCATION_ID = L.LOCAL_CODE(+);

-- ANSI

SELECT E.EMP_ID "사번"
     , E.EMP_NAME "사원명"
     , D.DEPT_TITLE "부서명"
     , J.JOB_NAME "직급명"
     , L.LOCAL_NAME "근무지역명"
  FROM EMPLOYEE E
  LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
  LEFT JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE)
  LEFT JOIN LOCATION L ON (D.LOCATON_ID = L.LOCAL_CODE);
  

-- 연습문제 --
-- 사원명, 부서명, 직급명, 근무지역명, 근무국가명, 급여등급(SAL_GRADE로)
SELECT * FROM EMPLOYEE; --   DEPT_CODE       JOB_CODE                                   SALARY
SELECT * FROM DEPARTMENT; -- DEPT_ID                    LOCATION_ID
SELECT * FROM JOB;        --                 JOB_CODE
SELECT * FROM LOCATION;   --                            LOCAL_CODE      NATIONAL_CODE
SELECT * FROM NATIONAL;   --                                            NATIONAL_CODE
SELECT * FROM SAL_GRADE;  --                                                            MIN_SAL / MAX_SAL
  
  
-- 오라클
SELECT E.EMP_NAME "사원명"
     , D.DEPT_TITLE "부서명"
     , J.JOB_NAME "직급명"
     , L.LOCAL_NAME "근무지역명"
     , N.NATIONAL_NAME "근무국가명"
     , S.SAL_LEVEL "급여등급"
  FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L, NATIONAL N, SAL_GRADE S
 WHERE E.DEPT_CODE = D.DEPT_ID(+)
   AND E.JOB_CODE = J.JOB_CODE(+)
   AND D.LOCATION_ID = L.LOCAL_CODE(+)
   AND L.NATIONAL_CODE = N.NATIONAL_CODE(+)
   AND E.SALARY BETWEEN S.MIN_SAL AND S.MAX_SAL(+);

-- ANSI
SELECT E.EMP_NAME "사원명"
     , NVL(D.DEPT_TITLE, '미정') "부서명"
     , J.JOB_NAME "직급명"
     , NVL(L.LOCAL_NAME, '미정') "근무지역명"
     , NVL(N.NATIONAL_NAME, '미정') "근무국가명"
     , S.SAL_LEVEL "급여등급"
  FROM EMPLOYEE E
  LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
  JOIN JOB J USING (JOB_CODE)
  LEFT JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE)
  LEFT JOIN NATIONAL N USING (NATIONAL_CODE)
  JOIN SAL_GRADE S ON (E.SALARY BETWEEN S.MIN_SAL AND S.MAX_SAL)
 WHERE N.NATIONAL_NAME = '한국';
 
 
















