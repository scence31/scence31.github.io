-- [BASIC SELECT]

-- 1. 춘 기술대 학과 이름과 계열을 표시하시오, 단 출력 헤더는 "학과 명", "계열" 표시
SELECT DEPARTMENT_NAME "학과 명", CATEGORY 계열
  FROM TB_DEPARTMENT;
  
-- 2. 학과의 학과 정원을 다음과 같은 형태로 화면에 출력
SELECT DEPARTMENT_NAME || '의 정원은' TO_CHAR(CAPACITY) || '명 입니다.' AS 학과별 정원
  FROM TB_DEPARTMENT
