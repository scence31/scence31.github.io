/*
    * JDBC 과정을 진행하기 위해 우선 DB를 구축해야한다.
    - 반드시 한 계정 안에 한 프로그램에서 쓸 DB 테이블만 구축해서 쓰자
*/

-- JEBC 수업용 계정 생성
CREATE USER JDBC IDENTIFIED BY JDBC;

-- 권한 부여
GRANT CONNECT, RESOURCE TO JDBC;


-- SPRING 수업용 계정 생성
CREATE USER SPRING IDENTIFIED BY SPRING;
GRANT CONNECT, RESOURCE TO SPRING;