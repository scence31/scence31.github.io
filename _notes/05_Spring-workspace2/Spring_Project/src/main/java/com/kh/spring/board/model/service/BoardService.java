package com.kh.spring.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Category;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@Service
public class BoardService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BoardDao boardDao;
	
	// 게시글의 총 갯수를 구하는 트랜잭션 처리 메소드
	public int selectListCount() {
		
		return boardDao.selectListCount(sqlSession);
	}
	
	// 게시글을 여러행 조회 해주는 트랜잭션 처리 메소드
	public ArrayList<Board> selectBoardList(PageInfo pi) {
		
		return boardDao.selectBoardList(sqlSession, pi);
	}
	
	// 게시글 검색 시 검색된 게시글의 갯수를 조회해주는 트랜잭션 처리 메소드
	public int selectSearchCount(HashMap<String, String> map) {
		
		return boardDao.selectSearchCount(sqlSession, map);
	}
	
	// 게시글 검색 조회를 해주는 트랜잭션 처리 메소드
	public ArrayList<Board> searchBoardList(HashMap<String, String> map, PageInfo pi) {
		
		return boardDao.searchBoardList(sqlSession, map, pi);
	}
	
	// 게시글 작성 페이지 조회 시 보여져야하는 카테고리 정보 조회를 해주는 트랜잭션 처리 메소드
	public ArrayList<Category> selectCategoryList() {
		
		return boardDao.selectCategoryList(sqlSession);
	}
	
	// 게시글 작성을 해주는 트랜잭션 처리 메소드
	@Transactional
	public int insertBoard(Board b, Attachment at) {
		
		// 그동안 우리는 트랜잭션 처리 메소드 내부에서 쿼리문을 단 한번만 실행했었음!!
		// 트랜잭션 1개 == DML 문 1개 (단일 프로세스)
		// 트랜잭션 1개 == DML 문 2개 이상 (다중 프로세스)
		// > DML 문들 중 하나라도 실패하면 모두 롤백해야함!!
		
		// 1. 우선 BOARD 테이블에 INSERT 를 다녀오기
		int result1 = boardDao.insertBoard(sqlSession, b);
		
		// ATTACHMENT 테이블 INSERT 결과를 담을 result2 변수 미리 선언
		int result2 = 1;
		// > 단, 첨부파일이 없는 경우에는 BOARD INSERT 가 성공했을 때도
		//   result2 는 0 이 될 수 밖에 없기 때문에 무조건 실패처리로 빠지게됨!!
		//   이걸 방지하기 위해서는 result2 값을 애초에 1로 셋팅해두면된다!!
		
		// 첨부파일이 있을 경우
		if(at != null) {
		
			// 2. ATTACHMENT 테이블에 INSERT 를 다녀오기
			result2 = boardDao.insertAttachment(sqlSession, at);
		}
		
		// 트랜잭션 처리는 스프링에게 맡기기 (@Transactional)
		// > 우리는 컨트롤러한테 최종 결과를 리턴해야함!!
		//   result1 도 양수고 result2 도 양수일 경우에 최종 성공 값을 리턴
		//   트랜잭션의 개념 상 하나라도 양수가 아니면 최종 실패 값을 리턴
		return result1 * result2;
		// > 혹시라도 하나라도 실패하면 0 을 반환하게끔
		//   두 result 값의 곱셈 결과를 반환해버리기!!
	}
	
	// 조회수 증가 트랜잭션 처리 메소드
	@Transactional
	public int increaseCount(int bno) {
		
		return boardDao.increaseCount(sqlSession, bno);
	}
	
	// 일반게시글 상세조회 트랜잭션 처리 메소드
	public Board selectBoard(int bno) {
		
		return boardDao.selectBoard(sqlSession, bno);
	}
	
	// 첨부파일 상세조회 트랜잭션 처리 메소드
	public Attachment selectAttachment(int bno) {
		
		return boardDao.selectAttachment(sqlSession, bno);
	}
	
	// 일반게시글 수정 트랜잭션 처리 메소드
	@Transactional
	public int updateBoard(Board b, Attachment at) {
		
		// 1. 우선 어느 경우의 수든지 간에 BOARD 테이블에 UPDATE 는 한번 다녀와야함
		int result1 = boardDao.updateBoard(sqlSession, b);
		
		int result2 = 1;
		
		// 2. 새로 넘어온 첨부파일이 있는 경우
		//    ATTACHMENT 테이블에 UPDATE 또는 INSERT 해야함
		if(at != null) {
			
			// 2_1. 기존 첨부파일이 있을 경우 - UPDATE
			// 2_2. 기존 첨부파일이 없을 경우 - INSERT
			if(at.getFileNo() != 0) { 
				// 기존 첨부파일이 있을 경우
				
				result2 = boardDao.updateAttachment(sqlSession, at);
				
			} else {
				// 기존 첨부파일이 없을 경우
				
				result2 = boardDao.insertNewAttachment(sqlSession, at);
			}
		}
		
		return result1 * result2;
		
	}
	
	// 일반게시글 삭제 트랜잭션 처리 메소드
	@Transactional
	public int deleteBoard(int bno) {
		
		return boardDao.deleteBoard(sqlSession, bno);
	}
	
	// 사진게시글 작성 트랜잭션 처리 메소드
	@Transactional
	public int insertThumbnail(Board b, ArrayList<Attachment> list) {
		
		// 1. 우선 게시글 정보를 먼저 INSERT 하고 넘어오기
		int result1 = boardDao.insertThumbnailBoard(sqlSession, b);
		
		// 2. 첨부파일"들"의 정보를 INSERT 하고 넘어오기
		// > 첨부파일 중 썸네일 이미지는 필수입력사항이므로 무조건 DAO 를 다녀와야함
		int result2 = boardDao.insertAttachmentList(sqlSession, list);
		
		return result1 * result2;
	}
	
	// 사진게시글 목록 조회 트랜잭션 처리 메소드
	public ArrayList<Board> selectThumbnailList() {
		
		return boardDao.selectThumbnailList(sqlSession);
	}
	
	// 사진게시글 첨부파일 목록 조회 트랜잭션 처리 메소드
	public ArrayList<Attachment> selectAttachmentList(int bno) {
		
		return boardDao.selectAttachmentList(sqlSession, bno);
	}

	// 댓글목록조회 트랜잭션처리 메소드
	public ArrayList<Reply> ajaxSelectReplyList(int bno) {
		
		return boardDao.ajaxSelectReplyList(sqlSession, bno);
	}

	// 댓글작성요청 트랜잭션처리 메소드
	@Transactional
	public int ajaxInsertReply(Reply r) {

		return boardDao.ajaxInsertReply(sqlSession, r);
	}
	
}




