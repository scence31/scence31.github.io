-- 1. 회원 목록 조회용 쿼리문
SELECT USER_ID
        , USER_NAME
        , EMAIL
        , GENDER
        , AGE
        , ENROLL_DATE
        , STATUS
  FROM MEMBER
 WHERE USER_ROLE = 'USER'
 ORDER BY USER_ID ASC;
 
 -- 2. 회원상세조회용 쿼리문
SELECT USER_ID
        , USER_NAME
        , EMAIL
        , GENDER
        , AGE
        , PHONE
        , ADDRESS
        , ENROLL_DATE
        , STATUS
  FROM MEMBER
 WHERE USER_ID = ?
    AND USER_ROLE = 'USER';
    
-- 3. 회원정보수정용 쿼리문
UPDATE MEMBER
    SET USER_NAME = ?
       ,  EMAIL = ?
      , GENDER = ?
       ,  AGE = ?
        , PHONE = ?
        , ADDRESS = ?
 WHERE USER_ID = ?
 
 -- 4. 회원탈퇴용 쿼리문
 UPDATE MEMBER
    SET STATUS = 'N'
        , MODIFY_DATE = SYSDATE
    WHERE USER_ID = ?
    
-- 5. 공지사항목록조회 쿼리문
SELECT NOTICE_NO
         , NOTICE_TITLE
         , NOTICE_WRITER
         , CREATE_DTAE
  FROM NOTICE
 WHERE STATUS = 'Y'
 ORDER BY NOTICE_NO DESC;
 
 
-- 6. 공지사항 상세조회 쿼리문
SELECT NOTICE_NO
         , NOTICE_TITLE
         , NOTICE_WRITER
         , NOTICE_CONTENT
         , CREATE_DATE
  FROM NOTICE
 WHERE STATUS = 'Y'
    AND NOTICE_NO = ?

-- 7. 공지사항 작성용 쿼리문
INSERT INTO NOTICE(NOTICE_NO, NOTICE_TITLE, NOTICE_WRITER, NOTICE_CONTENT)
        VALUES(SEQ_NNO.NEXTVAL, ?, ?, ?)
 
-- 8. 공지사항수정 쿼리문
UPDATE NOTICE
   SET NOTICE_TITLE = ?, NOTICE_CONTENT = ?
 WHERE NOTICE_NO = ?
   AND STATUS = 'Y'
 
--9. 공지사항삭제 쿼리문
UPDATE NOTICE
   SET STATUS = 'N'
 WHERE NOTICE_NO = ?
   AND STATUS = 'Y'
 
-- 10. 일반게시글 개수 카운트하는 쿼리문 (삭제된 게시글 빼야됨)
SELECT COUNT(*)
  FROM BOARD
 WHERE STATUS = 'Y'
 
-- 11. 페이징바 3단 인라인
SELECT *
  FROM (
                SELECT ROWNUM AS RSUM, A.*
                  FROM (
                                SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, ORIGIN_NAME, COUNT, CREATE_DATE
                                  FROM BOARD
                                 WHERE STATUS = 'Y'
                                 ORDER BY BOARD_NO DESC
                  ) A
                  WHERE ROWNUM <= ?
)
WHERE RNUM >= ?

-- 12. 게시글 검색 개수 쿼리문
SELECT COUNT(*)
  FROM BOARD
 WHERE BOARD_TITLE LIKE '%' || ? || '%'
   AND STATUS = 'Y' 
 
-- 13. 검색결과 쿼리문
SELECT *
  FROM (
        SELECT ROWNUM AS RNUM, A.*
          FROM (
                 SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, ORIGIN_NAME, COUNT, CREATE_DATE
                   FROM BOARD
                  WHERE STATUS = 'Y'
                    AND BOARD_TITLE LIKE '%' || #{keyword} || '%'
                  ORDER BY BOARD_NO DESC
          ) A
          WHERE ROWNUM &lt;= #{endRow}
)
WHERE RNUM &gt;= #{startRow}		
 
-- 14. 게시물 작성용 쿼리문
INSERT INTO BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, ORIGIN_NAME, CHANGE_NAME)
                    VALUES(SEQ_BNO.NEXTVAL, ?, ?, ?, ?, ?)
 
-- 15. 게시글 조회수 증가
UPDATE BOARD
   SET COUNT = COUNT + 1
 WHERE BOARD_NO = ?
   AND STATUS = 'Y'
   
-- 16. 게시글 상세조회
SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, ORIGIN_NAME, CHANGE_NAME, CREATE_DATE
  FROM BOARD
 WHERE BOARD_NO = ?
   AND STATUS= 'Y'
   
-- 17. 원본파일명 조회
SELECT ORGIN_NAME
  FROM BOARD
  WHERE CHANGE_NAME = ?
    AND STATUS = 'Y'
   
   
-- 18. 게시글 수정
UPDATE BOARD
   SET BOARD_TITLE = ?, BOARD_CONTENT = ?, ORIGIN_NAME = ?, CHANGE_NAME = ?
 WHERE BOARD__NO = ?
   AND STATUS = 'Y'
   

-- 20. 로그인(인증용) 쿼리문
SELECT *
  FROM MEMBER
 WHERE USER_ID = ?
    AND USER_PWD = ?
    AND STATUS = 'Y'
    AND USER_ROLE = 'ADMIN'
   
   
   
   
   
   
   
   
   
   
   
   
 
 
 
 