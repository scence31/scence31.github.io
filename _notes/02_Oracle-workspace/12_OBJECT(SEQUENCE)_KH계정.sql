/*
    <SEQUENCE 시퀀스>
    
    - 자동으로 번호를 순차적으로 발생하게 하는 역할의 객체
    - 은행 번호표 발행기 역할
    
    ex) 회원번호, 사번, 게시글번호 등등
    PRIMARY KEY 역할을 할 정수값을 채번할 때 많이 쓰임
    
    1. 시퀀스 객체생성 구문
    
    [표현법]
    
    CREATE SEQUENCE 시퀀스명
    START WITH 시작수
    => 처음 발생시킬 시작값 지정
    
    INCREMENT BY 증가값
    => 몇 씩 증가시킬 것인지 결정
    
    MAXVALUE 최댓값
    => 최댓값 지정
    
    MINVALUES 최솟값
    => 최솟값 지정
    
    CYCLE / NOCYCLE
    => 값 순환 여부 지정
    
    CACHE 바이트크기 / NOCACHE
    => 캐시메모리 사용 여부 지정. CACHE_SIZE 기본값은 20 BYTE
    
    위의 옵션들은 생략 가능함
    
    * 캐시메모리
    - 앞으로 발생될 정수값들을 미리 생성해서 저장해두는 공간
    - 매번 호출할 때마다 새로 번호를 생성하는 것보다 캐시메모리 공간에 미리 생성된 번호를
    저장해두고 바로 갖다 쓰게하면 훨씬 속도가 빠름
    - 단 해당 계정 접속이 끊기고 재접속하면 기존 캐시메모리에 담겨있던 정수값들이 없어짐

*/

/*
    참고) 접두사(명명규칙)
    
    - 테이블명: TB_XXX
    - 뷰명: VW_XXX
    - 시퀀스명: SEQ_XXX
    
*/

CREATE SEQUENCE SEQ_TEST;

-- 시퀀스는 접속탭 또는 USER_SEQUENCE 데이터 딕셔너리로 확인 가능
-- USER_SEQUENCE: 현재 접속한 이 계정이 갖고 있는 시퀀스 정보
SELECT * FROM USER_SEQUENCES;

-- 옵션 조합해서 시퀀스 생성
CREATE SEQUENCE SEQ_EMPNO
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;

SELECT * FROM USER_SEQUENCES;

------------------------------------------------------------------------------

/*
    ★ 2. 시퀀스 사용 구문
    
    - 시퀀스명.CURRVAL
    : 현재 시퀀스의 값
    마지막으로 성공적으로 발생된 NEXTVAL 값(값을 담는 변수같은 존재)
    
    - 시퀀스명.NEXTVAL
    : 다음 번호를 발생시키는 구문
    기존 시퀀스 값에서 INCREMENT BY 값만큼 증가된 값을 리턴
    즉, 시퀀스명.CURRVAL + INCREMENT BY 값
*/

-- Q. 발생된 시퀀스의 번호값을 단순히 출력해보기. DUAL 테이블 사용
SELECT SEQ_EMPNO.CURRVAL
  FROM DUAL;
--> SEQ_EMPNO 시퀀스 생성 후 한 번이라도 NEXTVAL 수행한 적 없어서 CURVRVAL 구문 수행불가

SELECT SEQ_EMPNO.NEXTVAL
  FROM DUAL; -- 300
--> 시작수인 300부터 하나씩 번호를 뽑아줌
--> NEXTVAL 수행시 내부적으로 값 300이 CURRVAL 변수에 담기는 것까지 수행됨

SELECT SEQ_EMPNO.CURRVAL
  FROM DUAL;

SELECT SEQ_EMPNO.NEXTVAL
  FROM DUAL; -- 305
--> NEXTVAL = CURRVAL + INCREMENT BY 값
--> 내부적으로 305가 CURRVAL에 담김

SELECT SEQ_EMPNO.CURRVAL
  FROM DUAL; -- 305

SELECT SEQ_EMPNO.CURRVAL
  FROM DUAL; -- 305
  
-- 데이터 딕셔너리를 통해 시퀀스 정보 재확인
SELECT * FROM USER_SEQUENCES;
--> USER_SEQUENCES 데이터 딕셔너리의 LAST_NUMBER 컬럼에 310 담김
-- LAST_NUMBER: 현재 시점에서 NEXTVAL을 수행할 경우의 예상값

SELECT SEQ_EMPNO.NEXTVAL
  FROM DUAL; -- 310
--> 이제 CURRVAL은 310, LAST_NUMBER은 315

SELECT SEQ_EMPNO.NEXTVAL
  FROM DUAL; -- 오류발생, MAXVALUE까지.
---------------------------------------------------------------------------

/*
    3. 시퀀스 변경
    
    [표현법]
    
    ALTER SEQUENCE 시퀀스명
    INCREMENT BY 증가값
    MAXVALUE 최댓값
    MINVALUE 최솟값
    CYCLE / NOCYCLE
    CACHE 바이트크기 / NOCACHE;
    
    모든 옵션은 조합 가능
    START WITH 이거만 변경 불가능. 변경하고싶으면 삭제후 재생성
*/

-- Q. SEQ_EMPNO 변경
ALTER SEQUENCE SEQ_EMPNO
INCREMENT BY 10
MAXVALUE 400;

SELECT SEQ_EMPNO.NEXTVAL
  FROM DUAL; -- 320

DROP SEQUENCE SEQ_EMPNO;

-----시퀀스가 사용되는 예시-----------

-- 사원관리 프로그램 구현중...
--EMPLOYEE 테이블에 사원 정보를 추가하는 기능 구현중

-- 사원이 매번 추가될 때마다 사번(EMP_ID) 값을 매번 새롭게 발생시키는 시퀀스 생성
CREATE SEQUENCE SEQ_EID
START WITH 300;

INSERT INTO EMPLOYEE VALUES(SEQ_EID.NEXTVAL, '이순신', '991111-1111111'
                          , 'user11@kh.or.kr', '01098785432', NULL, 'J7', 'S5'
                          , 2000000, NULL, 201, SYSDATE, NULL, DEFAULT);

INSERT INTO EMPLOYEE VALUES(SEQ_EID.NEXTVAL, '강감찬', '991311-1111111'
                          , 'user12@kh.or.kr', '01098785434', NULL, 'J7', 'S5'
                          , 2200000, NULL, 201, SYSDATE, NULL, DEFAULT);

-- INSERT문 중에서 사원이 추가될 때마다 실행되는 구문의 규칙

INSERT INTO EMPLOYEE VALUES(SEQ_EID.NEXTVAL, ?, ?
                          , ?, ?, ?, ?, ?
                          , ?, ?, ?, SYSDATE, NULL, DEFAULT);

--> ? 부분은 키보드로 입력받아서 채워야함. JAVA SCANNER 활용
--> JDBC














