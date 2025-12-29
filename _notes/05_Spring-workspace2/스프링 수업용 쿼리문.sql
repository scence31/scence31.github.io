-- 1. 로그인용 쿼리문
-- 아이디, 비번 일치 여부, 탈퇴 여부까지 체크!!
SELECT *
  FROM MEMBER
 WHERE USER_ID = ?
   AND USER_PWD = ?
   AND STATUS = 'Y'
   
-- 2. 회원가입용 쿼리문
-- 아이디 ~ 주소까지 6개의 값을 INSERT
INSERT INTO MEMBER(USER_NO
                 , USER_ID
                 , USER_PWD
                 , USER_NAME
                 , PHONE
                 , EMAIL
                 , ADDRESS)
            VALUES(SEQ_UNO.NEXTVAL
                 , ?
                 , ?
                 , ?
                 , ?
                 , ?
                 , ?)
            
-- 3. 회원정보 수정용 쿼리문
-- 뭐를 어떻게 수정할거냐
UPDATE MEMBER
   SET USER_NAME = ?
     , PHONE = ?
     , EMAIL = ?
     , ADDRESS = ?
     , MODIFY_DATE = SYSDATE
 WHERE USER_ID = ?
   AND STATUS = 'Y'
            
-- 4. 비밀번호 변경용 쿼리문
UPDATE MEMBER
   SET USER_PWD = ?
     , MODIFY_DATE = SYSDATE
 WHERE USER_ID = ?
   AND STATUS = 'Y'
            
-- 5. 회원 탈퇴용 쿼리문
-- 회원 탈퇴는 의미상 DELETE 이지만, UPDATE 로 구현할 것
UPDATE MEMBER
   SET STATUS = 'N'
     , MODIFY_DATE = SYSDATE
 WHERE USER_ID = ?
   AND STATUS = 'Y'
            
-- 6. 공지사항 목록 조회용 쿼리문
SELECT NOTICE_NO
     , NOTICE_TITLE
     , USER_ID
     , COUNT
     , CREATE_DATE
  FROM NOTICE N
  JOIN MEMBER ON (NOTICE_WRITER = USER_NO)
 WHERE N.STATUS = 'Y'
 ORDER BY NOTICE_NO DESC

-- 7. 공지사항 작성용 쿼리문
INSERT INTO NOTICE(NOTICE_NO
                 , NOTICE_TITLE
                 , NOTICE_CONTENT
                 , NOTICE_WRITER)
            VALUES(SEQ_NNO.NEXTVAL
                 , ?
                 , ?
                 , ?)

-- 8. 공지사항 조회수 증가용 쿼리문
UPDATE NOTICE
   SET COUNT = COUNT + 1
 WHERE NOTICE_NO = ?
   AND STATUS = 'Y'

-- 9. 공지사항 상세 조회용 쿼리문
-- 화면 상, 제목, 작성자의 아이디, 내용, 작성일 4개는 조회해야함!!
-- + 항상 상세조회 기능 구현 시에는 눈에 보이지는 않지만 PRIMARY KEY 제약조건에 해당하는 컬럼값도 같이 조회!!
SELECT NOTICE_NO
     , NOTICE_TITLE
     , NOTICE_CONTENT
     , USER_ID
     , CREATE_DATE
  FROM NOTICE N
  JOIN MEMBER ON (NOTICE_WRITER = USER_NO)
 WHERE NOTICE_NO = ?
   AND N.STATUS = 'Y'

-- 10. 공지사항 수정용 쿼리문
UPDATE NOTICE
   SET NOTICE_TITLE = ?
     , NOTICE_CONTENT = ?
 WHERE NOTICE_NO = ?
   AND STATUS = 'Y'

-- 11. 공지사항 삭제용 쿼리문
UPDATE NOTICE
   SET STATUS = 'N'
 WHERE NOTICE_NO = ?
   AND STATUS = 'Y'

-- 12. 일반게시글 총 갯수 세는 용도의 쿼리문
-- 목록 조회 시 필요 : 삭제되지 않고, 게시글 타입이 1인 경우에만
SELECT COUNT(*)
  FROM BOARD
 WHERE STATUS = 'Y'
   AND BOARD_TYPE = 1

-- 13. 일반게시글 목록 조회용 쿼리문
-- 게시글 목록 조회 시 "최신순" 으로 조회한다!!
-- 페이징 처리가 들어가야 하기 때문에 한번에 boardLimit 갯수 만큼만 조회해야함
--> 우선 게시글들을 최신순으로 정렬 후 위에서부터 10 개만 조회 == 1페이지
--                          그다음 위에서부터 10 개만 조회 == 2페이지
--                          ...
--> 조건식이 실행되기 전에 정렬이 먼저 일어나야하므로
--  (WHERE 절 보다 ORDER BY 절이 먼저 실행되야하므로 인라인 뷰 응용하기)
SELECT *
  FROM
    (
        SELECT ROWNUM RNUM, A.*
          FROM
            (
                SELECT BOARD_NO
                     , CATEGORY_NAME AS "CATEGORY"
                     , BOARD_TITLE
                     , USER_ID
                     , COUNT
                     , CREATE_DATE
                  FROM BOARD B
                  JOIN CATEGORY USING (CATEGORY_NO)
                  JOIN MEMBER ON (BOARD_WRITER = USER_NO)
                 WHERE B.STATUS = 'Y'
                   AND BOARD_TYPE = 1
                 ORDER BY BOARD_NO DESC
            ) A
    )
 WHERE RNUM BETWEEN ? AND ?
--> ROWNUM 컬럼은 원칙상 조건식이 1 부터 시작되게끔만 작성해야 제대로 조회됨

-- 14. 게시글 검색 시 검색된 게시글의 총 갯수를 구하는 쿼리문
-- CASE1 : 검색 조건이 writer 인 경우 (USER_ID 컬럼 기준으로 LIKE 연산)
-- CASE2 : 검색 조건이 title 인 경우 (BOARD_TITLE 컬럼 기준으로 LIKE 연산)
-- CASE3 : 검색 조건이 content 인 경우 (BOARD_CONTENT 컬럼 기준으로 LIKE 연산)
SELECT COUNT(*)
  FROM BOARD B
  JOIN MEMBER ON (BOARD_WRITER = USER_NO)
 WHERE BOARD_TYPE = 1
   AND B.STATUS = 'Y'
--   AND USER_ID LIKE '%ad%' -- 작성자의 아이디 기준 'ad' 로 검색 결과 65 개
--   AND BOARD_TITLE LIKE '%입니다1%' -- 게시글 제목 기준 '입니다1' 로 검색 결과 19 개
   AND BOARD_CONTENT LIKE '%2%' -- 게시글 내용 기준 '2' 로 검색 결과 20 개

-- 15. 게시글 검색 결과를 보여주는 쿼리문
SELECT BOARD_NO
     , CATEGORY_NAME AS "CATEGORY"
     , BOARD_TITLE
     , USER_ID
     , COUNT
     , CREATE_DATE
  FROM BOARD B
  JOIN CATEGORY USING (CATEGORY_NO)
  JOIN MEMBER ON (BOARD_WRITER = USER_NO)
 WHERE BOARD_TYPE = 1
   AND B.STATUS = 'Y'
--   AND USER_ID LIKE '%ad%'
--   AND BOARD_TITLE LIKE '%입니다1%'
   AND BOARD_CONTENT LIKE '%2%'
 ORDER BY BOARD_NO DESC
--> 검색 기능 구현 시 RowBounds 객체를 사용하기 싫다면 인라인뷰를 두 겹 걸쳐서 쓰면 됨!!

-- 16. 일반게시글 작성용 쿼리문
INSERT INTO BOARD(BOARD_NO
                , BOARD_TYPE
                , CATEGORY_NO
                , BOARD_TITLE
                , BOARD_CONTENT
                , BOARD_WRITER)
           VALUES(SEQ_BNO.NEXTVAL
                , 1
                , ?
                , ?
                , ?
                , ?)

-- 16_2. 첨부파일 등록용 쿼리문
INSERT INTO ATTACHMENT(FILE_NO
                     , REF_BNO
                     , ORIGIN_NAME
                     , CHANGE_NAME
                     , FILE_PATH)
                VALUES(SEQ_FNO.NEXTVAL
                     , SEQ_BNO.CURRVAL
                     , ?
                     , ?
                     , ?)

-- 17. 카테고리 전체 조회용 쿼리문
SELECT * 
  FROM CATEGORY
 ORDER BY CATEGORY_NO ASC

-- 18. 일반게시글 조회수 증가용 쿼리문
UPDATE BOARD
   SET COUNT = COUNT + 1
 WHERE BOARD_NO = ?
   AND STATUS = 'Y'

-- 19. 일반게시글 상세조회용 쿼리문
SELECT BOARD_NO
     , CATEGORY_NAME AS "CATEGORY"
     , BOARD_TITLE
     , BOARD_CONTENT
     , USER_ID
     , CREATE_DATE
  FROM BOARD B 
  LEFT JOIN CATEGORY USING (CATEGORY_NO)
  JOIN MEMBER ON (BOARD_WRITER = USER_NO)
 WHERE BOARD_NO = ?
   AND B.STATUS = 'Y'
--> 이 쿼리문을 사진게시글 상세조회 때에도 재활용 할 것!!
--  글번호 부분에 일반게시글 번호를 넣고 실행하면 제대로 실행됨!! (조회가 됨)
--              사진게시글 번호를 넣고 실행하면 제대로 실행이 안됨!! (조회가 안됨)
--  그런데 WHERE 절에 BOARD_TYPE 에 대한 조건이 없음 (즉, 잘하면 사진게시글 번호를 제시해도 조회가 되겠다)

-- 20. 첨부파일 상세조회용 쿼리문
-- 첨부파일이 있는 경우 a 요소로 첨부파일을 나타낼 것!!
-- <a href="파일경로/수정파일명">원본파일명</a>
SELECT FILE_NO
     , ORIGIN_NAME
     , CHANGE_NAME
     , FILE_PATH
  FROM ATTACHMENT
 WHERE REF_BNO = ?
--> 이 쿼리문은 글번호를 어떻게 넣어주냐에 따라
--  단일행 조회도 가능하고, 여러행 조회도 가능함!!
--  일반게시글에 딸린 첨부파일 정보를 조회하는 용도로도 쓸 수 있고
--  사진게시글에 딸린 첨부파일들의 정보들을 조회하는 용도로도 쓸 수 있다!!

-- 21. 일반게시글 수정용 쿼리문
-- 게시글 수정 요청 시.. Board b 는 넘겨받았고
UPDATE BOARD
   SET CATEGORY_NO = ?
     , BOARD_TITLE = ?
     , BOARD_CONTENT = ?
 WHERE BOARD_NO = ?
   AND STATUS = 'Y'
-- CASE1 : 새로 넘어온 첨부파일 (reUpfile) X
--> 첨부파일 정보는 일절 건들이지 않겠다!! 즉, ATTACHMENT 테이블에 DML 수행 X
-- CASE2 : 새로 넘어온 첨부파일 (reUpfile) O
--         CASE2_1 : 이 게시글에 기존 첨부파일이 있던 경우
--> 기존의 첨부파일 정보를 UPDATE 시키겠다. 즉, ATTACHMENT 테이블에 UPDATE 문 수행
UPDATE ATTACHMENT
   SET ORIGIN_NAME = ?
     , CHANGE_NAME = ?
     , UPLOAD_DATE = SYSDATE
 WHERE FILE_NO = ?
--         CASE2_2 : 이 게시글에 기존 첨부파일이 없던 경우
--> 새로운 첨부파일 정보를 INSERT 시키겠다. 즉, ATTACHMENT 테이블에 INSERT 문 수행
INSERT INTO ATTACHMENT(FILE_NO
                     , REF_BNO
                     , ORIGIN_NAME
                     , CHANGE_NAME
                     , FILE_PATH)
                VALUES(SEQ_FNO.NEXTVAL
                     , ?
                     , ?
                     , ?
                     , ?)

-- 22. 일반게시글 삭제용 쿼리문
UPDATE BOARD
   SET STATUS = 'N'
 WHERE BOARD_NO = ?
   AND STATUS = 'Y'
            
-- 23. 사진게시글 작성용 쿼리문
INSERT INTO BOARD(BOARD_NO
                , BOARD_TYPE
                , BOARD_TITLE
                , BOARD_CONTENT
                , BOARD_WRITER)
           VALUES(SEQ_BNO.NEXTVAL
                , 2
                , ?
                , ?
                , ?)

-- 24. 사진게시글 첨부파일 등록용 쿼리문
INSERT INTO ATTACHMENT(FILE_NO
                     , REF_BNO
                     , ORIGIN_NAME
                     , CHANGE_NAME
                     , FILE_PATH
                     , FILE_LEVEL)
                VALUES(SEQ_FNO.NEXTVAL
                     , SEQ_BNO.CURRVAL
                     , ?
                     , ?
                     , ?
                     , ?)
           
-- 25. 사진게시글 목록 조회용 쿼리문
-- 글번호, 제목, 조회수, 썸네일이미지의경로 및 수정파일명 조회
SELECT BOARD_NO
     , BOARD_TITLE
     , COUNT
     , FILE_PATH || CHANGE_NAME "TITLEIMG"
  FROM BOARD B
  JOIN ATTACHMENT ON (BOARD_NO = REF_BNO)
 WHERE BOARD_TYPE = 2
   AND B.STATUS = 'Y'
   AND FILE_LEVEL = 1
 ORDER BY BOARD_NO DESC

-- 26. 아이디 중복확인용 쿼리문
SELECT COUNT(*)
  FROM MEMBER
 WHERE USER_ID = ?

-- 27. 댓글목록조회용 쿼리문
-- REPLY 테이블에 더미데이터 넣기
INSERT INTO REPLY(REPLY_NO, REPLY_CONTENT, REF_BNO, REPLY_WRITER)
                 VALUES(SEQ_RNO.NEXTVAL, 'ㅋㅋㅋ', 21, 1)
                 
INSERT INTO REPLY(REPLY_NO, REPLY_CONTENT, REF_BNO, REPLY_WRITER)
                 VALUES(SEQ_RNO.NEXTVAL, 'ㅎㅎㅎ', 21, 4)
                 
INSERT INTO REPLY(REPLY_NO, REPLY_CONTENT, REF_BNO, REPLY_WRITER)
                 VALUES(SEQ_RNO.NEXTVAL, 'ㄴㄴㄴ', 21, 5)

COMMIT;

SELECT * FROM REPLY

SELECT REPLY_NO, REPLY_CONTENT, USER_ID, TO_CHAR(CREATE_DATE, 'YY/MM/DD HH:MI:SS') "CREATE_DATE"
  FROM REPLY R
  JOIN MEMBER ON (REPLY_WRITER = USER_NO)
 WHERE REF_BNO = 21
    AND R. STATUS='Y'
 ORDER BY REPLY_NO DESC
 
-- 28. 댓글작성용 쿼리문
INSERT INTO REPLY(REPLY_NO, REPLY_CONTENT, REF_BNO, REPLY_WRITER)
                    VALUE(SEQ_RNO.NEXTVAL, ?, ?, ?)


CREATE USER FS IDENTIFIED BY FS;

GRANT CONNECT, RESOURCE TO FS;

           
           
           
           
           
            
            
            
            
            
            
            
            
            