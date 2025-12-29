package com.kh.spring.notice.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.notice.model.dao.NoticeDao;
import com.kh.spring.notice.model.vo.Notice;

@Service
public class NoticeService {

	// NoticeDao 객체 셋팅
	@Autowired
	private NoticeDao noticeDao;
	
	// SqlSessionTemplate 객체 셋팅
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 공지사항 리스트 조회 트랜잭션을 처리하는 메소드
	public ArrayList<Notice> selectNoticeList() {
		
		return noticeDao.selectNoticeList(sqlSession);
	}
	
	// 공지사항 작성 트랜잭션을 처리하는 메소드
	@Transactional
	public int insertNotice(Notice n) {
		
		return noticeDao.insertNotice(sqlSession, n);
	}
	
	// 공지사항 조회수 증가 트랜잭션을 처리하는 메소드
	@Transactional
	public int increaseCount(int nno) {
		
		return noticeDao.increaseCount(sqlSession, nno);
	}
	
	// 공지사항 상세 조회 트랜잭션을 처리하는 메소드
	public Notice selectNotice(int nno) {
		
		return noticeDao.selectNotice(sqlSession, nno);
	}
	
	// 공지사항 수정 트랜잭션을 처리하는 메소드
	@Transactional
	public int updateNotice(Notice n) {
		
		return noticeDao.updateNotice(sqlSession, n);
	}
	
	// 공지사항 삭제 트랜잭션을 처리하는 메소드
	@Transactional
	public int deleteNotice(int nno) {
		
		return noticeDao.deleteNotice(sqlSession, nno);
	}
	
}


