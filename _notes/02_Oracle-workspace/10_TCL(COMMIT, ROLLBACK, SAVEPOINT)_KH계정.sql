/*
    <TCL - TRANSACTION CONTROL LANGUAGE>
    
    - 트랜잭션 제어 언어
    - 단, INSERT, UPDATE, DELETE 문에만 해당됨
    (즉, 데이터의 변경사항 (DML)들을 한번에 트랜잭션에 담았다가 처리)
    * 트랜잭션<TRANSACTION>
    - 데이터베이스의 논리적 연산 단위
    - 쉽게 말하면 웹 개발 시 기능 1개의 단위
    ex) 로그인 기능, 회원가입 기능, 게시글 작성기능...
    
    - 하나의 기능을 구현할 때 무조건 쿼리문 1개만 필요하란 법은 없음
    (회원가입: 아이디 중복체크용 SELECT + 회원가입용 INSERT)
    이 경우에는 쿼리문들이 순차적으로 모두 성공해야 해당 기능이 제대로 동작했다고 봄
    
    * TCL 종류
    
    - COMMIT;
    : 하나의 트랜잭션에 담긴 변경사항들을 실제 DB에 반영(픽스)
    실제 DB에 반영 후 트랜잭션은 비워짐
    
    - ROLLBACK;
    : 하나의 트랜잭션에 담긴 변경사항들을 삭제한 후 마지막 COMMIT 시점으로 돌아감
    
    - SAVEPOINT 포인트명;
    : 현재 이 시점에 임시저장점을 정의(여러 개 가능)
    
    - ROLLBACK TO 포인트명;
    : 전체 변경사항 삭제가 아닌, 해당 포인트까지만 롤백
*/

SELECT * FROM EMP_01;
-- 전체사원 총 27명

-- 901, 902 삭제
DELETE
  FROM EMP_01
 WHERE EMP_ID IN(901, 902);

-- 900 삭제
DELETE
  FROM EMP_01
 WHERE EMP_ID = 900;
--> 24명 조회, 우리 눈엔 잘 삭제된 것 같음
--> DB에 실제로 반영된 상태는 아님

ROLLBACK;

SELECT * FROM EMP_01;
--> 27명 복구

----------------------------------------------------------------------------

-- 사번이 200인 사원 삭제
DELETE
  FROM EMP_01
 WHERE EMP_ID = 200;

-- 사번 800, 이름 김말순, 부서는 총무부인 사원 추가
INSERT INTO EMP_01 VALUES(800, '강말순', '총무부');

SELECT * FROM EMP_01;
--> 삭제/추가처럼 보이는 것. TRABSATION에만 반영. 실제 DB는 반영 안댐

COMMIT;

SELECT * FROM EMP_01;
--> 이제 픽스됨. DB 반영

SAVEPOINT SP1;

ROLLBACK TO SP1;

COMMIT;

-- CREAT(DDL) 사용하면 COMMIT이 자동으로 되어서 ROLLBACK이 안댐

/*
    * DDL 구문(CREATE, ALTER, DROP)을 사용하는 순간
    기존에 트랜잭션에 있던 모든 변경사항들을 무조건 실제 DB에 반영 후 내부적으로 DDL 수행됨
    => DDL 수행 전 DML 문에 의해서 변경사항이 있따면 일단 커밋하든 롤백하든 픽스하자.
*/
