/*
    <함수 FUNCTION>
    하나의 기능단위. 자바의 메소드와 같은 존재
    매개변수로 전달된 값들을 읽어서 처리한 결과를 반환 RETURN
    
    - 단일행함수: N개의 값을 읽어서 N개의 결과를 리턴
                 매 행마다 함수를 실행한 후 매 행의 결과를 모두 리턴
    
    - 그룹함수(집계함수): n개의 값을 읽어서 1개의 결과로 리턴
                         매 행을 하나의 그룹으로 묶어서 실행한 후 결과를 리턴
                         
    
   ★ 단일행함수와 그룹함수는 동시에 쓸 수 없음!
    결과(RESULT SET) 행의 개수가 서로 다르기 때문임.
    데이터베이스의 테이블, RESULT SET과 같은 데이터를 나타내는 표 형식은
    항상 정사각형과 같은 모양이어야 하기 때문임.
    DISTINCT를 두 번 이상 쓰지 못하는 것과 같은 원리임
*/
------------------------------------------------------------------------

-----------<단일행함수>-----------------
/*
    <문자열 관련된 함수 - 자바의 String 클래스에서 제공한 메소드들과 유사>
    
    1. LENGTH / LENGTHB
    
    LENGTH(STR): 해당 전달된 문자열의 글자 수 반환
    LENGTHB(STR): 해당 전달된 문자열의 바이트 수 반환(B: BYTE)
    
    STR: '문자열' / 문자열 타입의 컬럼명
    결과값은 NUMBER 타입으로 반환
    
    숫자, 영문, 특수문자: '!', '~', 'A', 'a', '1' 등은 한 글자 당 1BYTE로 취급
    한글: 'ㄱ', 'ㅑ', 김' 등은 한 글자 당 3BYTE로 취급
*/

SELECT LENGTH('오라클!'), LENGTHB('오라클!')
  FROM DUAL;
-- DUAL(가상테이블 DUMMY TABLE)
-- 리터럴을 가지고 단순 연산식 또는 함수식을 처리한 결과를 바로 출력하고 싶을 때
-- FROM절에 적어주는 임의의 테이블명(흰 도화지 느낌)

SELECT 1 + 4
  FROM DUAL;

-- EMPLOYEE 테이블 컬럼에 적용
SELECT EMAIL, EMP_NAME
     , LENGTH(EMAIL)
     , LENGTHB(EMAIL)
     , LENGTH(EMP_NAME)
     , LENGTHB(EMP_NAME)
  FROM EMPLOYEE;

/*
    2. INSTR
    
    INSTR(STR): 전달된 문자열로부터 특정 문자의 위치값을 반환
    
    INSTR(STR, '특정문자', 찾을위치의시작값, 순번)
    
    STR: '문자열' / 문자열 타입의 컬럼명
    결과값은 NUMBER 타입으로 반환
    
    찾을위치의시작값, 순번은 생략 가능!
    
    찾을위치의시작값
    1: 앞에서부터 찾음(생략 기본값)
    -1: 뒤에서부터 찾음
    
    순번 생략시 첫 번째부터
*/
  
SELECT INSTR('AABAACAABBAA', 'B')
  FROM DUAL; -- 3
--> 찾을 위치, 순번 생략 시 기본적으로 앞에서부터 첫 번째 특정 문자의 위치를 검색(1부터)

SELECT INSTR('AABAACAABBAA', 'B', 1)
  FROM DUAL; -- 3
--> 찾을위치의 시작값을 1로(생략 기본값) 설정해두면 앞에서부터 찾아줌!

SELECT INSTR('AABAACAABBAA', 'B', -1)
  FROM DUAL; -- 10
--> 찾을위치의시작값을 -1로 설정하면 뒤에서부터 찾아줌

SELECT INSTR('AABAACAABBAA', 'B', 1, 2)
  FROM DUAL; -- 9
--> 순번을 2로 설정하면 앞에서부터 두 번째 문자의 위치를 찾아줌

SELECT INSTR('AABAACAABBAA', 'B', -1, 2)
  FROM DUAL; -- 9
--> 뒤에서부터 두 번째 위치하는 B의 위치값을 1부터 세서 알려준 것

-- EMPLOYEE 테이블에 적용
-- EMAIL 컬럼으로부터 @의 위치를 알아내기
SELECT EMAIL, INSTR(EMAIL, '@')
  FROM EMPLOYEE;

-- 참고) 오라클은 문자열의 문자를 1부터 카운트함

---------------------------------------------------------------------------
/*
    3. SUBSTR
    SUBSTR(STR, POSITION, LENGTH)
    : 전달된 문자열로부터 특정 문자열을 추출해서 반환, 즉 부분문자열 추출 후 반환
    (자바 .substring() 메소드와 비슷)
    
    - STR:  '문자열' / 문자열타입의컬럼명
    - POSITION: 문자열 추출을 시작할 위치값(음수 가능, -N: 뒤에서부터 N번째)
    - LENGTH: 추출할 문자 개수(생략시 문자열 끝까지 추출)
    
    결과값은 CHARACTER(문자열) 타입으로 반환

*/

SELECT SUBSTR('SHOWMETHEMONEY', 7)
  FROM DUAL; -- THEMONEY
-- 7번째 글자부터 끝까지 추출(LENGTH 생략시 끝까지)

SELECT SUBSTR('SHOWMETHEMONEY', 5, 2)
  FROM DUAL; -- ME

SELECT SUBSTR('SHOWMETHEMONEY', 1, 6)
  FROM DUAL; -- SHOWME

SELECT SUBSTR('SHOWMETHEMONEY', -8, 3)
  FROM DUAL; -- THE

-- EMPLOYEE 테이블에서 적용
-- 주민등록번호에서 성별부분만 추출해서 남자(1) / 여자(2) 체크
SELECT EMP_NAME, EMP_NO, SUBSTR(EMP_NO, 8, 1) "성별"
  FROM EMPLOYEE
  
-- 남자사원만 조회(사원명, 급여)
SELECT EMP_NAME, SALARY
  FROM EMPLOYEE
 -- WHERE SUBSTR(EMP_NO, 8, 1) = '1' OR SUNSTR(EMP_NUM, 8, 1) = '3';
 WHERE SUBSTR(EMP_NO, 8, 1) IN ('1', '3');
 
 
 -- 이메일로부터 ID부분만 추출해서 조회(@ 전까지 문자열 보고싶음)
 SELECT EMP_NAME, EMAIL, SUBSTR(EMAIL, 1, INSTR(EMAIL, '@') -1 ) AS "아이디"
   FROM EMPLOYEE;

-- 주민번호로부터 생년월일까지만 출력
SELECT EMP_NAME, EMP_NO, SUBSTR(EMP_NO, 1, INSTR(EMP_NO, '-') -1) "생년월일"
  FROM EMPLOYEE;

--------------------------------------------------------------------------

/*
    4. LPAD(문자열이 왼쪽에 붙음) / RPAD(오른쪾에)
    
    LAPD/RPAD(STR, 최종적으로반환할문자열의바이트길이, 덧붙일문자)
    : 제시한 문자열에 임의의 문자를 왼쪽 또는 오른쪽에 붙여 푀정 N길이만큼의 문자열 만들어서 반환
    
    STR: '문자열' / 문자열 타입의 컬럼명
    
    결과값은 CHRACTER(문자열) 타입으로 반환
    
*/
SELECT LPAD(EMAIL, 16)
  FROM EMPLOYEE;
--> EMAIL 왼쪽에 공백문자를 추가하여 총 16바이트짜리 문자열로 만들어서 반환
-- 덧붙일문자를 생략하면 공백문자가 붙음

SELECT LPAD(EMAIL, 16, '#')
  FROM EMPLOYEE;

SELECT RPAD(EMAIL, 16, '#')
  FROM EMPLOYEE;

-- EMPLOYEE 테이블 주민등록번호 뒷자리 마스킹처리
SELECT EMP_NAME, RPAD(SUBSTR(EMP_NO, 1, 8), 14, '*') AS 주민번호
  FROM EMPLOYEE;
--> 성별자리까지 추출 후 RPAD 이용해서 14글자 만들기

/*
    6. LRTIM / RTRIM
    
    LTRIM / RTRIM(STR, 제거할문자)
    : 문자열의 왼쪽 또는 오른쪽에서 제거할 문자들을 찾아서 제거한 나머지 문자열을 반환
    
    STR: 문자열 or 문자열타입컬럼명
    제거할문자: 생략하면 공백 제거
               문자열 통으로 제거도 가능, 근데 하나라도 겹치는 문자 모두 제거
    
    결과값음 CHARACTER 문자열 타입으로 반환
    
*/
SELECT LTRIM('              K          H')
  FROM DUAL;

SELECT LTRIM('000033505000', '0')
  FROM DUAL;
--> LTRIM은 왼쪽 제거

SELECT RTRIM(LTRIM('000033505000', '0'), '0')
  FROM DUAL;
--> 중복으로 양쪽 제거

SELECT LTRIM('ABABACCKH', 'ABC')
  FROM DUAL; -- KH

---------------------------------------------------------------------------

/*
    6. TRIM
    TRIM(BOTH/LEADING/TRAILING '제거할문자' FROM STR)
    : 문자열의 양쪽/앞/뒤에 있는 특정 문자를 제거한 나머지의 문자열을 반환
    
    STR: 문~
    결과값은 CHRAC~
    
    BOTH/LEADING/TRAILING 생략 가능
    기본생략: 양쪽 공백 제거 == BOTH
*/

SELECT TRIM('         K       H   ')
  FROM DUAL; --KH

SELECT TRIM('Z' FROM 'ZZZKHZZZ')
  FROM DUAL; --KH
  -- 생략시 BOTH가 기본값(양쪽 제거)

SELECT TRIM(BOTH 'Z' FROM 'ZZZKHZZZ')
  FROM DUAL; --KH

SELECT TRIM(LEADING 'Z' FROM 'ZZZKHZZZ')
  FROM DUAL; -- KHZZZ (== LTRIM) OR  ZZZKH (== RTRIM)

----------------------------------------------------------------------------

/*
    7. LOWER / UPPER / INITCAP
    
    LOWER(STR): 소문자로 변환
    UPPER(STR): 대문자로 변환
    INITCAP(STR): 단어 앞글자만 대문자로 변환(단어의 기준은 띄어쓰기)
    
    STR: 문~
    결과값: CHARC~

*/

SELECT LOWER('Welcome To My World')
  FROM DUAL;

SELECT UPPER('Welcome To My World')
  FROM DUAL;
  
SELECT INITCAP('welcome to my world')
  FROM DUAL;

-----------------------------------------------------------------------

/*
    8. CONCAT
    
    CONCAT(STR1, STR2)
    문자열을 하나로
*/

SELECT CONCAT('가나다', 'ABC')
  FROM DUAL;

-- 3개 하고싶으면 중첩해야됨 하나에서 3개하면 오류남
SELECT CONCAT(CONCAT('가나다', 'ABC'), '123')
  FROM DUAL;
--------------------------------------------------------------------------

/*
    9. REPLACE
    REPLACE(STR, 찾을문자, 바꿀문자)
    문자열에서 문자를 바꾸고, 바뀐 문자열 반환
*/

SELECT REPLACE('서울시 강남구 역삼동', '역삼동', '삼성동')
  FROM DUAL;

-- 이메일 값 중 kh.or.kr을 iei.or.kr로
SELECT EMP_NAME, EMAIL, REPLACE(EMAIL, 'kh.or.kr', 'iei.or.kr')
  FROM EMPLOYEE;

-----------------------------------------------------------------------------

/*
    <숫자와 관련된 함수 (MATH클래스와 유사함)>
    
    1. ABS(NUMBER): 절대값
    정수, 실수 다 NUMBER타입임
    
*/
SELECT ABS(-10)
  FROM DUAl;
-----------------------------------------------------------------------------
/*
    2. MOD(NUMBER1, NUMBER2)
    두 수를 나눈 나머지값을 반환해주는 함수
    (오라클에서는 모듈러 연산자 %가 없어서)
    
*/
SELECT MOD(10, 3)
  FROM DUAL;

SELECT MOD(-10, 3)
  FROM DUAL;
-----------------------------------------------------------------------------

/*
    3. ROUND
    
    ROUND(NUMBER, 위치)
    반올림 처리해주는 함수
    
    위치: 소숫점 아래 N번째 수에서 반올림(반올림할 위치를 옵션으로 지정 가능, 자바는 불가)
         생략 가능, 
         음수로 위치제시하면 역으로 정수 방향으로 반올림
*/
SELECT ROUND(123.456)
  FROM DUAL; -- 123

SELECT ROUND(123.456, 0)
  FROM DUAL; -- 123

SELECT ROUND(123.456, 1)
  FROM DUAL; -- 123.5
  
SELECT ROUND(123.456, 2)
  FROM DUAL; -- 123.46

SELECT ROUND(123.456, -1)
  FROM DUAL; -- 120

----------------------------------------------------------------------------
/*
    4. CEIL
    CEIL(NUMBER): 소숫점 아래의 수를 무조건 올림(위치조정 X)
*/
SELECT CEIL(123.156)
  FROM DUAL;
  -----------------------------------------------------------------------

/*
    5. FLOOR
    FLOOR(NUMBER): 소숫접 아래 수를 무조건 버림처리(자릿수 지정X)
*/

SELECT FLOOR(123.789)
  FROM DUAL;

-- 직원별로 고용일로부터 오늘까지 근무일수를 조회
SELECT EMP_NAME, HIRE_DATE, CONCAT(FLOOR(SYSDATE - HIRE_DATE), '일') AS "근무일수"
  FROM EMPLOYEE;

-----------------------------------------------------------------------------

/*
    6. TRUNC
    TRUNC(NUMBER, 위치): 위치지정 가능한 버림처리, 위치값 = 소수점자리 나머지를 버림
    위치 생략시: ==FLOOR
*/
SELECT TRUNC(123.756, 2)
  FROM DUAL;
----------------------------------------------------------------------------
/*
    <날짜관련 함수>
    DATE 타입: 연, 월, 일, 시, 분, 초 다 포함한 자료형
    
    SYSDATE: 현재 이 컴퓨터의 시스템 날짜를 반환
    
*/
SELECT SYSDATE
  FROM DUAL;
  


-- 1. MONTHS_BETWEEN(DATE1, DATE2)
-- 두 날짜 사이의 개월 수 반환
-- 단, DATE1이 더 미래의 날짜여야 양수로 계산됨
-- DATE2가 더 미래면 음수로 반환

-- EMPLOYEE 테이블로부터 각 지원별로 고용일에서부터 오늘까지의 근무일수와 근무개월수 조회
SELECT EMP_NAME
     , FLOOR(SYSDATE-HIRE_DATE) 근무일수
     , MONTHS_BETWEEN(SYSDATE, HIRE_DATE) 근무개월수
  FROM EMPLOYEE;

--> 일, 시, 분, 초에 대한 연산도 들어가서 소숫점이 나옴

-- 2. ADD_MONTHS(DATE, NUMBER)
-- 특정 날짜에 해당 숫자만큼의 개월수를 더한 날짜를 변환(DATE 타입 변환)

-- 오늘 날짜로부터 5개월 후
SELECT ADD_MONTHS(SYSDATE, 5)
  FROM DUAL;
  
  -- EMPLOYEE 테이블 적용
  -- 전체 사원들의 직원명, 입사일, 입사 후 3개월이 흘렀을 때의 날짜(수습시간 종료) 조회
SELECT EMP_NAME, HIRE_DATE, ADD_MONTHS(HIRE_DATE, 3) "수습종료일"
  FROM EMPLOYEE;

-- 3. NEXT_DAY(DATE, 요일을나타내는값)
-- 특정 날짜에서 가장 가까운 해당 요일을 찾아 그 날짜를 반환
-- 미래의 날짜중 하나임(과거가 가까워도 다음주로 이월)
-- 숫자로도 요일 표현 가능(일요일 == 1)

SELECT NEXT_DAY(SYSDATE, '일') -- '일요일' == '일' == 1
  FROM DUAL;
--> 오늘날짜: 25/08/21(목) ~> 25/08/24(일) ==> 가장 가까운 일요일

-- 시스템언어 변경: AMERICAN(영어)
ALTER SESSION SET NLS_LANGUAGE = 'AMERICAN';

SELECT NEXT_DAY(SYSDATE, 'FRI')
  FROM DUAL;

-- 시스템언어 변경: KOREAN
ALTER SESSION SET NLS_LANGUAGE = 'KOREAN';

-- 4. LAST_DAY(DATE)
-- 해당 날짜 달의 마지막 날짜를 구해서 반환(DATE 타입으로 반환)

SELECT LAST_DAY(SYSDATE)
  FROM DUAL;

-- EMPLOYEE 테이블에서
-- 전체 사원 이름, 입사일, 입사한 달의 마지막날 조회
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE)
  FROM EMPLOYEE;

/*
    5. EXTRACT: 날짜로부터 연도 또는 월 또는 일 정보만 추출해서 반환(NUMBER 타입 반환)
    
    [표현법]
    EXTRACT(YEAR FROM DATE): 특정 날짜로부터 연도만 추출
    EXTRACT(MONTH FROM DATE): 월수만 추출
    EXTRACT(DAY FROM DATE): 일수만 추출
*/

-- 오늘 날짜로부터 각각 연도, 월 ,일을 추출해서 출력
SELECT EXTRACT(YEAR FROM SYSDATE) -- 2025
     , EXTRACT(MONTH FROM SYSDATE) -- 8
     , EXTRACT(DAY FROM SYSDATE) -- 21
  FROM DUAL;

SELECT EMP_NAME
     , HIRE_DATE
     , EXTRACT(YEAR FROM HIRE_DATE) 입사년도
     , EXTRACT(MONTH FROM HIRE_DATE) 입사월
     , EXTRACT(DAY FROM HIRE_DATE) 입사일
     , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 근무연차
  FROM EMPLOYEE
 ORDER BY 입사년도 ASC, 입사월 ASC, 입사일 ASC;

--------------------------------------------------------------------------

/*
    <형변환 함수>
    1. NUMBER / DATE => CHARACTER
    
    TO_CHAR(NUMBER/DATE, '포멧')
    : 숫자형 또는 날짜형 데이터를 문자형 타입으로 변환(CHARACTER 타입 반환)
    0계열 / 9계열
*/

-- NUMBER => CHARACTER
SELECT TO_CHAR(1234)
  FROM DUAL; -- 1234 => '1234'
--> 포맷을 지정하지 않으면 단순히 문자열 타입으로 변환

SELECT TO_CHAR(1234, '00000')
  FROM DUAL; -- 1234 => '01234' : 빈칸을 0으로 채움
  
SELECT TO_CHAR(1234, '99999')
  FROM DUAL; -- 1234 => ' 1234' : 빈칸을 공백으로 채움

-- 자주 쓰이는 예시
SELECT TO_CHAR(1234, 'L00000')
  FROM DUAL; -- 1234 => '\01234'
--> L: Lodcal, 현재 시스템적으로 설정된 나라의 화폐단위 기호로 바꿔줌

SELECT TO_CHAR(1234, 'L99999')
  FROM DUAL; -- 1234 => '\1234'

-- 달러 기호로 보고싶다면?
SELECT TO_CHAR(1234, '$99999')
  FROM DUAL;

-- , 찍어서 금액 표현
SELECT TO_CHAR(1234, 'L9,999')
  FROM DUAL;
  
-- EMPLOYEE 테이블
-- 전체 직원 급여정보를 , 포함하여 출력
SELECT EMP_NAME, SALARY, TO_CHAR(SALARY, 'L99,999,999')
  FROM EMPLOYEE;

-- DATE => CHARACTER
SELECT TO_CHAR(SYSDATE, 'YY/MM/DD')
  FROM DUAL;
--> 포맷 생략시 기본값은 YY/MM/DD

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')
  FROM DUAL;

SELECT TO_CHAR(SYSDATE, 'AM HH:MI:SS')
  FROM DUAL;
--> AM/PM : 오전/오후 ==> AM을 썼다고 무조건 오전이 아님

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS')
  FROM DUAL;
  --> HH24: 24시간 형태
  
SELECT TO_CHAR(SYSDATE, 'MON DY, YYYY')
  FROM DUAL; -- 8월 목, 2025
  --> MON: N월
  --> DY: ex) 월요일에서 요일을 뺀 형태
  
--> 포맷이 다양하며 조합은 내 마음, 자바의 SimpleDateFormat 객체와 비슷한 역할

-- 연도로써 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'YYYY')
     , TO_CHAR(SYSDATE, 'RRRR')
     , TO_CHAR(SYSDATE, 'YY')
     , TO_CHAR(SYSDATE, 'RR')
     , TO_CHAR(SYSDATE, 'YEAR') -- 영어로 출력
  FROM DUAL;

-- 월로 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'MM')
     , TO_CHAR(SYSDATE, 'MON')
     , TO_CHAR(SYSDATE, 'MONTH')
     , TO_CHAR(SYSDATE, 'RM') -- 로마숫자로 출력
  FROM DUAL;

-- 일로 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'D') -- 1주일 기준으로 오늘 며칠째(일요일부터 1)
     , TO_CHAR(SYSDATE, 'DD') -- 1달 기준으로 오늘 며칠째(1일부터 셈)
     , TO_CHAR(SYSDATE, 'DDD') -- 1년기준(1월1일부터)
  FROM DUAL;

SELECT TO_CHAR(SYSDATE, 'DY') -- 요일 불포함
     , TO_CHAR(SYSDATE, 'DAY') -- 요일 포함
  FROM DUAL;
  
-- 2025년 08월 21일 (목) 포맷으로 출력
SELECT TO_CHAR(SYSDATE, 'YYYY"년" MM"월" DD"일" (DY)')
  FROM DUAL;
--> 포맷관련 문자와 특수기호를 제외한 나머지 문자는 큰따옴표로 감싸야함

-- EMPLO
-- 사원명, 입사일 조회 포맷 적용해서
SELECT EMP_NAME, TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일" (DY)')
  FROM EMPLOYEE;


SELECT EMP_NAME, TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일" (DY)')
  FROM EMPLOYEE
 WHERE EXTRACT(YEAR FROM HIRE_DATE) > 2010;

----------------------------------------------------------------------------
/*
    2. NUMBER / CHARACTER => DATE
    
    TO_DATE(NUMBER/CHARACTER, '포맷')
    : 숫자형 또는 문자형 데이터를 날짜형으로 변환(기본 포맷의 DATE 타입 변환)
    
    주의사항: 0으로 시작하거나, 98년 이런거로 하거나, 없는 날짜로하면 오류발생
*/

SELECT TO_DATE(20210101)
  FROM DUAL;

SELECT TO_DATE('20210101')
  FROM DUAL;
  
-- 주의사항
SELECT TO_DATE(000101)
  FROM DUAL; -- 0으로 시작하면 오류 >> '000101' 문자열로 바꿔야함
  
SELECT TO_DATE('20100101', 'YYYYMMDD')
  FROM DUAL;
--> RESULT SET 상으로는 YY/MM/DD로 보이지만 값보기 클릭하면 제대로 날짜 형식 나옴

SELECT TO_DATE('20100101 143021', 'YYYYMMDD HH24MISS')
  FROM DUAL;

SELECT TO_DATE('140630', 'YYMMDD')
  FROM DUAL; -- 2014년 6월 30일
  
SELECT TO_DATE('980630', 'YYMMDD')
  FROM DUAL; -- 2098년으로 나옴
--> TO_DATE로 두 자리 YY하면 현재 세기(21세기) 기준으로 나옴

SELECT TO_DATE('980630', 'RRMMDD')
  FROM DUAL; -- 1998 나옴
--> R: ROUND의 약자(반올림), 50 이상이면 이전세기, 50 미만이면 현재세기로 표현

-----------------------------------------------------------------------------

/*
    3. CHARACTER => NUMBER
    
    TO_NUMBER(CHARACTER, '포맷')
    : 문자형 데이터를 숫자형으로 변환(NUMBER타입으로 변환)
    
*/

SELECT '123' + '123'
  fROM DUAL; -- 246
--> 문자열이 숫자타입으로 자동형변환 후 덧셈 연산

SELECT *
  FROM EMPLOYEE
 WHERE SUBSTR(EMP_NO, 8, 1) IN(1, 3);
--> 문자열과 숫자 타입 간 동등비교 연산 가능(자동형변환 되어서)

-- 자동형변한 안 되는 특이케이스
SELECT '10,000,000' + '550,000'
  FROM DUAL; -- 문자 ,가 포함되어있어서 자동형변환 불가능!
  
SELECT TO_NUMBER('10,000,000', '99,999,999') + TO_NUMBER('550,000', '999,999')
  FROM DUAL;

-----------------------------------------------------------------------------

/*
    ★<NULL 처리 함수>
    
    NULL 값이 매개변수로 들어갔을 때 처리해주는 함수
*/

--1. ★ NVL(컬럼명, 컬럼값이NULL일경우반환할값)
-- 해당 컬럼값이 존재할 경우(NULL 아님) 그대로 반환,
-- NULL일 경우 내가 제시한 특정값을 반환

-- EMPLOYEE 테이블로부터 사원명, 보너스 조회
-- 단 보너스를 받지 않는 경우 NULL이 아닌 0으로 출력

SELECT EMP_NAME, BONUS, NVL(BONUS, 0)
  FROM EMPLOYEE;

-- 보너스 포함 연봉을 제대로 조회 가능함!
SELECT EMP_NAME, SALARY, BONUS, NVL(BONUS, 0), (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "보너스 포함 연봉"
  FROM EMPLOYEE;

-- 사원명, 부서코드 조회, NULL이면 부서배치미정으로 출력
SELECT EMP_NAME, DEPT_CODE, NVL(DEPT_CODE, '부서배치 미정')
  FROM EMPLOYEE;


-- 2. NVL2(컬럼명, 결과값1, 결과값2)
-- 해당 컬럼값이 존재할 경우 결과값1 반환
-- NULL일 경우 결과값2 반환

SELECT EMP_NAME, BONUS, NVL2(BONUS, '보너스 받음', '보너스 받지 않음')
  FROM EMPLOYEE;
  

-- 3. NULLIF(비교대상1, 비교대상2)
-- 비교대상1과 비교대상2가 일치하면 NULL or 비교대상1 반환

SELECT NULLIF('123', '123')
  FROM DUAL; -- 일치해서 NULL 반환

SELECT NULLIF('123', '456')
  FROM DUAL; -- 일치하지 않아서 '123' 반환

----------------------------------------------------------------------------
/*
    <선택함수>
    
    DECODE(비교대상컬럼명, 조건값1, 결과값1, ... 조건값N, 결과값N, 최종결과값)
    
    자바 switch문과 유사한 구조임.
*/

-- 사번, 사원명, 주번, 성별(남/여) 조회
SELECT EMP_ID
     , EMP_NAME
     , EMP_NO
     , SUBSTR(EMP_NO, 8, 1) "성별(숫자)"
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남'
                                  , '2', '여'
                                  , '3', '남'
                                  , '4', '여') "성별(문자)"
  FROM EMPLOYEE;

-- 직원들 급여 인상해서 조회
-- 직급코드가 J7이면 급여 10%, J6이면 15%, J5면 10%, 그 외는 5% 인상해서 조회

SELECT EMP_NAME
     , JOB_CODE
     , SALARY "인상 전"
     , DECODE(JOB_CODE, 'J7', SALARY*1.1
                      , 'J6', SALARY*1.15
                      , 'J5', SALARY*1.2
                      ,       SALARY*1.05) "인상 후"
  FROM EMPLOYEE;

/*
    DECODE 친구(함수 아님)
    CASE WHEN THEN 구문
    
    DECODE는 내부적으로 동등비교만 수행 == switch문
    CASE WHEN THEN 구문은 조건을 디테일하게 가능 == if문
    
    [표현법]
    
    CASE WHEN 조건식1 THEN 결과값1
         WHEN 조건식n THEN 결과값n
         ...
                     ELSE 최종결과값
     END
    
*/

-- 사번, 사원명, 성별조회

-- 사번, 사원명, 성별 조회 DECODE 버전
SELECT EMP_ID
     , EMP_NAME
     , EMP_NO
     , SUBSTR(EMP_NO, 8, 1) "성별(숫자)"
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남'
                                  , '2', '여'
                                  , '3', '남'
                                  , '4', '여') "성별(문자)"
  FROM EMPLOYEE;

-- CASE WHEN THEN 버전
SELECT EMP_ID
     , EMP_NAME
     , EMP_NO
     , SUBSTR(EMP_NO, 8, 1) "성별(숫자)"
     , CASE WHEN SUBSTR(EMP_NO, 8, 1) = '1' THEN '남'
            WHEN SUBSTR(EMP_NO, 8, 1) = '2' THEN '여'
            WHEN SUBSTR(EMP_NO, 8, 1) = '3' THEN '남'
            WHEN SUBSTR(EMP_NO, 8, 1) = '4' THEN '여'
            END "성별(문자)"
  FROM EMPLOYEE;

SELECT EMP_ID
     , EMP_NAME
     , EMP_NO
     , SUBSTR(EMP_NO, 8, 1) "성별(숫자)"
     , CASE WHEN SUBSTR(EMP_NO, 8, 1) IN('1', '3') THEN '남' ELSE '여'
            END "성별(문자)"
  FROM EMPLOYEE; -- 축약 가능
  
-- 직급코드에 따른 급여 인상분 조회
SELECT EMP_NAME
     , JOB_CODE
     , SALARY "인상 전"
     , DECODE(JOB_CODE, 'J7', SALARY*1.1
                      , 'J6', SALARY*1.15
                      , 'J5', SALARY*1.2
                      ,       SALARY*1.05) "인상 후"
  FROM EMPLOYEE;
  
-- CASE WHEN THEN 버전
SELECT EMP_NAME
     , JOB_CODE
     , SALARY "인상 전"
     , CASE WHEN JOB_CODE = 'J7' THEN SALARY*1.1
            WHEN JOB_CODE = 'J6' THEN SALARY*1.15
            WHEN JOB_CODE = 'J5' THEN SALARY*1.15
                                 ELSE SALARY*1.05
     
       END "인상 후"
  FROM EMPLOYEE;

-- 사원명, 급여, 급여등급(고급, 중급, 초급)
-- SALARY 값이 500만원 초과 -> 고급, 350~500 -> 중급, 350 이하 -> 초급
SELECT EMP_NAME
     , SALARY
     , CASE WHEN SALARY > 5000000 THEN '고급'
            WHEN SALARY > 3500000 AND SALARY <= 5000000 THEN '중급'
                                                        ELSE '초급'  
       END "급여 등급"
  FROM EMPLOYEE;
-- 위와 같이 동등비교가 아닌 조건식일 경우에는 DECODE 사용 불가
-- DECODE는 CASE WHEN THEN으로 언제든지 변경 가능

----------------------------------------------------------------------------

-------<그룹함수(==집계함수)>-----
/*
    N개의 값을 읽고, 하나의 그룹으로 묶어서 1개의 결과로 반환해주는 함수
    
*/
            
-- 1. SUM(숫자타입컬럼명): 컬럼값들의 총합계 반환

SELECT SUM(SALARY)
  FROM EMPLOYEE;
  
-- 부서코드가 'D5'인 사원들의 급여 총합계
SELECT SUM(SALARY)
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5';
            
-- 2. AVG(숫자타입컬럼명): 평균값

-- 평균 급여
SELECT ROUND(AVG(SALARY))
  FROM EMPlOYEE;
  
SELECT ROUND(SALARY), AVG(SALARY)
  FROM EMPLOYEE;
--> 위와 같이 그룹함수와 단일행함수를 같이 쓸 수는 없음
-- 각 함수 결과 행의 개수가 다르기 떄문

-- 3. MIN(ANY타입컬럼명) / MX(ANY타입컬럼명)

-- 테이블로부터 급여, 이름, 이메일, 입사일 기준 가장 최솟값 구하기

SELECT MIN(SALARY), MIN(EMP_NAME), MIN(EMAIL), MIN(HIRE_DATE)
  FROM EMPLOYEE; -- 오름차순으로 설정 후 가장 위의 값을 반환

SELECT MAX(SALARY), MAX(EMP_NAME), MAX(EMAIL), MAX(HIRE_DATE)
  FROM EMPLOYEE; -- 내림차순 ~
  
-- 5. COUNT(* / 컬럼명 / DISTINCT 컬럼명)
-- 조회된 행의 개수를 세서 반환

-- COUNT(*)
-- 조회 결과에 해당하는 모든 행의 개수를 다 세서 반환

-- COUNT(컬럼명)
-- 제시한 해당컬럼값이 NULL이 아닌 것들의 행의 개수를 세서 반환(NULL 포함 안함)

-- COUNT(DISTINCT 컬럼명)
-- 중복값이 있을 경우 개수를 하나로 세서 반환(NULL 포함 안함)

-- 참고) COUNT(*)의 결과와 COUNT(컬럼명) 결과는 NULL이 하나도 없다면 항상 일치!
SELECT COUNT(*)
  FROM EMPLOYEE;

-- 여자 사원수
SELECT COUNT(*)
  FROM EMPLOYEE
 WHERE SUBSTR(EMP_NO, 8, 1) IN('2', '4');
            
-- 부서 배치가 된 사원들 수
SELECT COUNT(DEPT_CODE)
  FROM EMPLOYEE;
  -- IS NOT NULL 제외해도 됨(어차피 카운트 안함)

-- 현재 사원들이 속해있는 부서 개수
SELECT COUNT(DISTINCT DEPT_CODE)
  FROM EMPLOYEE;
  













