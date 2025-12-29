

-- 1. JOB 테이블의 모든 정보 조회
SELECT *
  FROM JOB;

-- 2. JOB 테이블의 직급이름 조회
SELECT JOB_NAME
  FROM JOB;
  
-- 3. DEPARTMENT 테이블의 모든 정보 조회
SELECT *
  FROM DEPARTMENT;
  
-- 4. EMPLOYEE 테이블의 직원명, 이메일, 전화번호, 고용일 조회
SELECT EMP_NAME, EMAIL, PHONE, HIRE_DATE
  FROM EMPLOYEE;
  
-- 5. 임플로 테이블 고용일, 사원이름, 월급 조회
SELECT HIRE_DATE, EMP_NAME, SALARY
  FROM EMPLOYEE;
  
-- 6. 임플로 이름, 연봉, 총수령액(보너스포함), 실수령액(총수령액-(연봉*세금 3%) 조회
SELECT EMP_NAME, SALARY*12 연봉, (SALARY+(SALARY*NVL(BONUS, 0))) 총수령액
      ,((SALARY+(SALARY*NVL(BONUS, 0))) - ((SALARY*12)*0.03)) 실수령액
  FROM EMPLOYEE
 ORDER BY ((SALARY+(SALARY*NVL(BONUS, 0))) - ((SALARY*12)*0.03));

-- 7. 임플로 SAL_LEVEL이 S1인 사람의 이름, 월급, 고용일, 연락처 조회
SELECT EMP_NAME, SALARY, HIRE_DATE, PHONE
  FROM EMPLOYEE
 WHERE SAL_LEVEL = 'S1';
 
-- 8. 임플로 실수령액이 5천만원 이상인 사람의 이름, 월급, 실수령액, 고용일 조회
SELECT EMP_NAME, SALARY, ((SALARY+(SALARY*NVL(BONUS, 0))) - ((SALARY*12)*0.03)) 실수령액, HIRE_DATE
  FROM EMPLOYEE
 WHERE ((SALARY+(SALARY*NVL(BONUS, 0))) - ((SALARY*12)*0.03)) >= 50000000;
 
-- 10. 임플로, DEPT_CODE가 D9거나 D5인 사원 중 고용일이 02년 1월 1일보다 빠른 사원의 이름, 부서코드, 고용일 조회
SELECT EMP_NAME, DEPT_CODE, HIRE_DATE
  FROM EMPLOYEE
 WHERE DEPT_CODE IN('D9', 'D5') AND HIRE_DATE > '02/01/01';

-- 11. 임플로, 고용일이 90/01/01 ~ 01/01/01 사원 전체내용 조회
SELECT *
  FROM EMPLOYEE
 WHERE HIRE_DATE BETWEEN '90/01/01' AND '02/01/01'; 

-- 12. 임플로, 이름 끝이 '연'으로 끝나는 사원 이름 조회
SELECT EMP_NAME
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '%연';

-- 13. 임플로, 전화번호 처음 3자리가 010이 아닌 사원 이름, 전화번호 조회
SELECT EMP_NAME, PHONE
  FROM EMPLOYEE
 WHERE PHONE NOT LIKE '010%';
 
-- 14. 임플로, 메일주소 '_' 앞이 4자면서 DEPT_CODE가 D9 또는 D6이고
--     고용일이 90/01/01 ~ 00/12/01이고, 급여가 270만원 이상인 사원 전체를 조회
SELECT *
  FROM EMPLOYEE
 WHERE LENGTH(SUBSTR(EMAIL, 1, INSTR(EMAIL, '_') -1)) = 4
   AND DEPT_CODE IN('D9', 'D6')
   AND HIRE_DATE BETWEEN TO_DATE('90/01/01') AND TO_DATE('00/12/01')
   AND SALARY >= 2700000;
   
-- 15. 임플, 사원명과 직원 주민번호 이용해서 생년, 생월, 생일 조회
SELECT 








