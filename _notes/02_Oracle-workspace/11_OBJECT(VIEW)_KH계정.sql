/*
        <VIEW> 는 논리적인 가상테이블이다.
        
        - SELECT문(쿼리문)을 저장해둘 수 있는 객체
        (자주 쓰이는 긴 SELECT문을 저장해두면 매번 다시 기술할 필요 없음)
        - 즉 조회용 임시테이블 같은 존재임
        => 실제 데이터가 담겨있는 것은 아님(데이터 저장용도가 아님)
        
        단순히 SELECT문만, TEXT로 저장되어있음(데이터 아님!)
        참고) USER_VIEWS: VIEW에 대해 해당 계정이 갖고 있는 데이터 딕셔너리
        SELECT * FROM USER_VIEWS;

*/

-- 뷰 사용하는 이유
-- Q. 한국에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명, 직급명 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
  FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L, NATIONAL N
 WHERE E.DEPT_CODE = D.DEPT_ID(+)
   AND D.LOCATION_ID = L.LOCAL_CODE(+)
   AND L.NATIONAL_CODE = N.NATIONAL_CODE(+)
   AND E.JOB_CODE = J.JOB_CODE
   AND N.NATIONAL_NAME = '한국';
   
--> 매번 쓰기 귀찮음, 실수가능
--> VIEW 사용해서 저장 후 갖다 쓰자.

/*
    1. VIEW 생성방법 CREATE
    
    [표현법]
    
    1) CREATE VIEW 뷰명
    AS (서브쿼리);
    
    2) CREATE OR REPLACE VIEW 뷰명
    AS (서브쿼리);
    => 뷰 생성시 기존 중복된 이름의 뷰가 없다면 새로 추가, 있다면 해당 뷰 갱신
    
*/

-- VIEW에 저장하기

CREATE VIEW VW_EMPLOYEE
AS (

        SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
          FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L, NATIONAL N
         WHERE E.DEPT_CODE = D.DEPT_ID(+)
           AND D.LOCATION_ID = L.LOCAL_CODE(+)
           AND L.NATIONAL_CODE = N.NATIONAL_CODE(+)
           AND E.JOB_CODE = J.JOB_CODE
);
--> KH계정에 VIEW를 생성할 수 있는 권한이 아직 없음
--> 관리자계정에서 GRANT CREATE VIEW TO KH; 구문으로 권한 부여하기
GRANT CREATE VIEW TO KH;

SELECT * FROM VW_EMPLOYEE;

-- Q. 뷰로부터 한국에서 근무하는 사원들만 보기
SELECT *
  FROM VW_EMPLOYEE
 WHERE NATIONAL_NAME = '한국';

-- Q. 러시아에서 근무하는 사원들만 보기
SELECT *
  FROM VW_EMPLOYEE
 WHERE NATIONAL_NAME = '러시아';

-- 일본에서 근무하는 사원들의 사번, 이름, 직급명, 보너스 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, BONUS
  FROM VW_EMPLOYEE
 WHERE NATIONAL_NAME = '일본';
--> BONUS 컬럼이 없어서 오류 발생

-- BONUS 포함하고싶으면?
-- 방법1) 기존 뷰 삭제 후 다시만들기

-- 방법2) CREATE OR REPLACE VIEW 명령어 사용
CREATE OR REPLACE VIEW VW_EMPLOYEE
AS (
        SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME, BONUS
          FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L, NATIONAL N
         WHERE E.DEPT_CODE = D.DEPT_ID(+)
           AND D.LOCATION_ID = L.LOCAL_CODE(+)
           AND L.NATIONAL_CODE = N.NATIONAL_CODE(+)
           AND E.JOB_CODE = J.JOB_CODE
);

-----------------------------------------------------------------------------

/*
    * 뷰 생성시 컬럼에 별칭 부여하기
    -> 추후에 조회 편리, 서브쿼리 계열이라 함수식 사용시 무조건 별칭 필요.
    
    
*/

-- 사번, 이름, 직급명, 성별, 근무년수를 조회할 수 있는 SELECT문을 뷰로 정의
SELECT EMP_ID, EMP_NAME, JOB_NAME
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여') 성별
     , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 근무년수
  FROM EMPLOYEE
  JOIN JOB USING (JOB_CODE);

CREATE OR REPLACE VIEW VW_EMP_JOB
AS (
        SELECT EMP_ID, EMP_NAME, JOB_NAME
             , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여') 성별
             , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 근무년수
          FROM EMPLOYEE
          JOIN JOB USING (JOB_CODE)
);

SELECT * FROM VW_EMP_JOB;

-- Q. 여자 사원만 조회 view에서

SELECT *
  FROM VW_EMP_JOB
 WHERE 성별 = '여';


-- 참고) 사실.. 인라인 뷰도 뷰였음

-- 다른방법으로 별칭 부여 가능
-- 단, 모ㅗ든 컬럼을 빠짐없이 기술해야 함
CREATE OR REPLACE VIEW VW_EMP_JOB (사번, 사원명, 직급명, 성별, 근무년수)
AS (
        SELECT EMP_ID, EMP_NAME, JOB_NAME
             , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여')
             , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE)
          FROM EMPLOYEE
          JOIN JOB USING (JOB_CODE)
);

-- 근무년수가 20년 이상인 사원들의 모든 컬럼 조회, WHERE절에서도 별칠 가능(인라인뷰 특징)

DROP VIEW VW_EMP_JOB;

SELECT * FROM VW_EMP_JOB;
--> table or view does not exist 오류

-------------------------------------------------------------------------------

/*
   * 생성된 뷰를 통해서 DML문(INSERT, UPDATE, DELETE) 사용 가능
   단, 
   
*/

-- 테스트용 뷰 생성
CREATE OR REPLACE VIEW VW_JOB
AS (
        SELECT *
          FROM JOB
          );

SELECT * FROM VW_JOB; -- 뷰 조회
SELECT * FROM JOB; -- 원본테이블 조회

-- VW_JOB 뷰에 JOB_CODE가 J8이고 JOB_NAME이 인턴인 직급 추가

INSERT INTO VW_JOB VALUES('J8', '인턴');
--> 뷰에 INSERT 하더라도 뷰에 데이터가 실제로 추가된 것이 아니라
-- 뷰의 원본테이블에 INSERT가 된 것

-- J8의 인턴을 알바로 --> INSERT와 마찬가지.
UPDATE VW_JOB
   SET JOB_NAME = '알바'
 WHERE JOB_CODE = 'J8';
 
SELECT * FROM VW_JOB;
SELECT * FROM JOB;

-- DELETE도 마찬가지
DELETE
  FROM VW_JOB
 WHERE JOB_CODE = 'J8';


/*
    뷰를 통해 DML이 불가능한 경우가 더 많음
    
    1) 뷰에 정의되지 않은 컬럼을 조작하는 경우
    2) 뷰에 정의되어 있지 않은 컬럼 중에 베이스테이블에 NOT NULL 제약조건이 지정된 경우
    3) 산술연산식 또는 함수식을 통해 뷰를 정의한 경우
    4) 그룹함수나 GROUP BY절이 포함된 경우
    5) DISTINCT 구문이 포함된 경우
    6) JOIN 이용해서 여러 테이블을 매칭한 경우
    
*/

ROLLBACK;

/*
    * VIEW 생성시 사용가능한 옵션들
    
    [최종 상세 표현법]
    
    CREATE OR REPALCE FORCE/NOFORCE VIEW 뷰명
    AS (서브쿼리)
    WITH CHECK OPTION
    WITH READ ONLY;
    
    1) OR REPLACE
    -> 해당 뷰 이름이 이미 존재하면 갱신 또는 존재하지 않으면 생성해주는 옵션
    
    2) FORCE/NOFORCE
    -> FORCE: 서브쿼리에 기술된 테이블이 실제로 존재하지 않아도 뷰가 생성
    -> NOFORCE: 서브쿼리~ 반드시 존재해야 뷰가 생성. 기본 생략
    
    3) WITH CHECK OPTION
    -> 서브쿼리의 조건절에(WHERE) 기술된 내용에 만족하는 값으로만 뷰에 DML이 가능하게
    해주는 옵션. 조건 미부합시 DML 실행하는 순간 오류 발생
    
    4) WITH READ ONLY
    뷰에 대해서 조회만 가능하게 옵션, DML문 실행 불가
    
*/

-- 2) FORCE/NOFORCE
CREATE OR REPLACE FORCE VIEW VW_TEST
AS (
        SELECT TCODE, TNAME, TCONTENT
          FROM TT
          );
--> "경고: 컴파일 오류와 함께 뷰가 생성되었습니다."

SELECT * FROM VW_TEST;
--> 조회는 안돼, 동작은 불가, 급한 대로 강제로 SELECT문만 TEXT로 저장해둔 상태

-- 추후에 TT 테이블 만들었을 것 같은 경우에 생성.
CREATE TABLE TT(
    TCODE NUMBER,
    TNAME VARCHAR2(30),
    TCONTENT VARCHAR2(50)
    );

SELECT * FROM VW_TEST;

-- 3) WITH CHECK OPTION
CREATE OR REPLACE VIEW VW_EMP
AS(
        SELECT *
          FROM EMPLOYEE
         WHERE SALARY >= 3000000
         )
WITH CHECK OPTION;

SELECT * FROM VW_EMP;

-- VW_EMP 뷰에 UPDATE문 적용해보기
UPDATE VW_EMP
   SET SALARY = 10000
 WHERE EMP_ID = 200;
-- 오류발생: view WITH CHECK OPTION where-clause violation
-- 서브쿼리에 기술한 WHERE절 조건에 부합하지 않는 조건으로 DML 실행해서 오류 발생한 것.
-- UPDATE 구문에서 WHERE절 조건을 맞추면 가능함

-- 4) WITH READ ONLY
CREATE OR REPLACE VIEW VW_EMP_BONUS
AS (

        SELECT EMP_ID, EMP_NAME, BONUS
          FROM EMPLOYEE
         WHERE BONUS IS NOT NULL
         )
WITH READ ONLY;

SELECT * FROM VW_EMP_BONUS;
--> DML처리 따로 불가능한 뷰
