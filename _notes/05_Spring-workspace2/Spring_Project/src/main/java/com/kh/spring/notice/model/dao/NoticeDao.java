package com.kh.spring.notice.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.notice.model.vo.Notice;

@Repository
public class NoticeDao {
	
	// 공지사항 목록 조회용 쿼리문 실행 메소드
	public ArrayList<Notice> selectNoticeList(SqlSessionTemplate sqlSession) {
		
		// 공지사항 목록 조회 : 여러행 조회 (selectList 메소드)
		return (ArrayList)sqlSession.selectList("noticeMapper.selectNoticeList");
	}
	
	// 공지사항 작성용 쿼리문 실행 메소드
	public int insertNotice(SqlSessionTemplate sqlSession, Notice n) {
		
		return sqlSession.insert("noticeMapper.insertNotice", n);
	}
	
	// 공지사항 조회수 증가용 쿼리문 실행 메소드
	public int increaseCount(SqlSessionTemplate sqlSession, int nno) {
		
		return sqlSession.update("noticeMapper.increaseCount", nno);
	}

	// 공지사항 상세조회용 쿼리문 실행 메소드
	public Notice selectNotice(SqlSessionTemplate sqlSession, int nno) {

		// 공지사항 상세 조회 : 단일행 조회 (selectOne 메소드)
		return sqlSession.selectOne("noticeMapper.selectNotice", nno);
	}
	
	// 공지사항 수정용 쿼리문 실행 메소드
	public int updateNotice(SqlSessionTemplate sqlSession, Notice n) {
		
		return sqlSession.update("noticeMapper.updateNotice", n);
	}
	
	// 공지사항 삭제용 쿼리문 실행 메소드
	public int deleteNotice(SqlSessionTemplate sqlSession, int nno) {
		
		return sqlSession.update("noticeMapper.deleteNotice", nno);
	}
	
}


