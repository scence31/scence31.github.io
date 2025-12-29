package com.kh.spring.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Category;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@Repository
public class BoardDao {
	
	// 게시글의 총 갯수를 구하는 쿼리문 실행 메소드
	public int selectListCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
	}
	
	// 게시글 목록 조회용 쿼리문 실행 메소드
	public ArrayList<Board> selectBoardList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		// 그냥 정석으로 하기 -> vo에 생성해놔서 걍 pi로
		
		/*
		 * * RowBounds 객체 생성 - 마이바티스 페이징처리
		 * [ 표현법 ]
		 * RowBounds rowBounds = new RowBounds(offset, limit);
		 * 
		 * - offset : 앞에서부터 몇개를 "건너뛸건지" 에 대한 갯수
		 * - limit : "몇개" 를 조회해올건지에 대한 갯수
		 * 
		 * * RowBounds 객체의 동작 원리
		 * 인라인 뷰 없이 테이블의 총 조회 결과를 구하고
		 * 그 구한 ResultSet 의 결과로 부터 
		 * "앞에서부터 n 개를 건너 뛰고" "m 개만 조회하겠다" 컨셉으로 동작함!!
		 * > 결국, 페이징 처리 구현 시 굳이 쿼리문에 복잡한 인라인뷰를 안써도 됨!!
		 * > 기존 인라인뷰를 이용하는 방식 보다 RowBounds 를 이용하는 방식이 성능이 매우 안좋다!!
		 *   특히 게시글의 갯수가 더 많아질수록 성능 차이는 훨씬 더 커짐!! (실무에서는 그닥 권장사항은 아님..)
		 */
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// RowBounds 객체를 적용해서 selectList 메소드를 호출하면 됨!!
		// > 이 경우에는 selectList 메소드의 오버로딩된 형태 중 매개변수 3개짜리를 호출하면 됨
		// 첫번째 매개변수 : "어느매퍼.어느아이디"
		// 두번째 매개변수 : 미완성된 쿼리문일 경우 완성시킬 데이터
		// 세번째 매개변수 : RowBounds 객체
		// > 매개변수 3개짜리 오버로딩된 형태는 오직 selectList 메소드에서만 제공해줌!!
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", null, rowBounds);
		// > 만약 해당 쿼리문이 완성된 형태라 두번째 매개변수 (완성시킬 데이터) 가 필요 없다면
		//   두번째 매개변수 자리에는 null 값으로 자리를 메꿔주면 된다!!
	}
	
	// 게시글 검색 요청 시, 검색된 게시글의 갯수를 구하는 쿼리문 실행 메소드
	public int selectSearchCount(SqlSessionTemplate sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
	}

	// 게시글 검색용 쿼리문 실행 메소드
	public ArrayList<Board> searchBoardList(SqlSessionTemplate sqlSession,
											HashMap<String, String> map,
											PageInfo pi) {
		
		// 오늘도 인라인뷰 대신 RowBounds 객체를 이용해볼 것!!
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.searchBoardList", map, rowBounds);
	}
	
	// 일반게시글 추가용 쿼리문 실행 메소드
	public int insertBoard(SqlSessionTemplate sqlSession, Board b) {
		
		return sqlSession.insert("boardMapper.insertBoard", b);
	}
	
	// 첨부파일 등록용 쿼리문 실행 메소드
	public int insertAttachment(SqlSessionTemplate sqlSession, Attachment at) {
		
		return sqlSession.insert("boardMapper.insertAttachment", at);
	}
	
	// 카테고리 정보 조회용 쿼리문 실행 메소드
	public ArrayList<Category> selectCategoryList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectCategoryList");
	}
	
	// 일반게시글 조회수 증가용 쿼리문 실행 메소드
	public int increaseCount(SqlSessionTemplate sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.increaseCount", bno);
	}
	
	// 일반게시글 상세조회용 쿼리문 실행 메소드
	public Board selectBoard(SqlSessionTemplate sqlSession, int bno) {
		
		return sqlSession.selectOne("boardMapper.selectBoard", bno);
	}
	
	// 첨부파일 상세조회용 쿼리문 실행 메소드
	public Attachment selectAttachment(SqlSessionTemplate sqlSession, int bno) {
		
		return sqlSession.selectOne("boardMapper.selectAttachment", bno);
	}
	
	// 일반게시글 수정용 쿼리문 실행 메소드
	public int updateBoard(SqlSessionTemplate sqlSession, Board b) {
		
		return sqlSession.update("boardMapper.updateBoard", b);
	}
	
	// 일반게시글 첨부파일 수정용 쿼리문 실행 메소드 1
	public int updateAttachment(SqlSessionTemplate sqlSession, Attachment at) {
		
		return sqlSession.update("boardMapper.updateAttachment", at);
	}
	
	// 일반게시글 첨부파일 수정용 쿼리문 실행 메소드2 
	public int insertNewAttachment(SqlSessionTemplate sqlSession, Attachment at) {
		
		return sqlSession.insert("boardMapper.insertNewAttachment", at);
	}
	
	// 일반게시글 삭제용 쿼리문 실행 메소드
	public int deleteBoard(SqlSessionTemplate sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.deleteBoard", bno);
	}
	
	// 사진게시글 작성용 쿼리문 실행 메소드
	public int insertThumbnailBoard(SqlSessionTemplate sqlSession, Board b) {
		
		return sqlSession.insert("boardMapper.insertThumbnailBoard", b);
	}
	
	// 사진게시글 첨부파일 등록용 쿼리문 실행 메소드
	public int insertAttachmentList(SqlSessionTemplate sqlSession, ArrayList<Attachment> list) {
		
		// 쿼리문은 한번에 한개만 작성해야 한다!!
		// > 한번만 잘 작성해두고 나서 반복문을 이용해서 여러번 실행시킬 예정!!
		
		int result = 1;
		// > INSERT 구문을 여러번 실행 후 모두 성공 / 하나라도 실패 여부를 가려야함!!
		//   초기값을 1로 셋팅해둔 후 누적 곱을 구함!!
		
		for(Attachment at : list) {
			
			result *= sqlSession.insert("boardMapper.insertAttachmentList", at);
			// > 누적 곱을 구하는 과정에서
			//   하나라도 INSERT 에 실패하면 누적 곱의 내용이 계속 0 으로 나오게 될 것!!
		}
		
		return result;
	}
	
	// 사진게시글 목록 조회용 쿼리문 실행 메소드
	public ArrayList<Board> selectThumbnailList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectThumbnailList");
	}
	
	// 사진게시글 첨부파일 목록 조회용 쿼리문 실행 메소드
	public ArrayList<Attachment> selectAttachmentList(SqlSessionTemplate sqlSession, int bno) {
		
		// 일반게시글 상세조회시 썼던 첨부파일 조회용 쿼리문을 재활용
		return (ArrayList)sqlSession.selectList("boardMapper.selectAttachment", bno);
	}

	// 댓글목록조회용 쿼리문 실행 메소드
	public ArrayList<Reply> ajaxSelectReplyList(SqlSessionTemplate sqlSession, int bno) {
		return (ArrayList)sqlSession.selectList("boardMapper.ajaxSelectReplyList", bno);
	}

	// 댓글작성용 쿼리문 실행 메소드
	public int ajaxInsertReply(SqlSessionTemplate sqlSession, Reply r) {
		return sqlSession.insert("boardMapper.ajaxInsertReply", r);
	}
	
}








