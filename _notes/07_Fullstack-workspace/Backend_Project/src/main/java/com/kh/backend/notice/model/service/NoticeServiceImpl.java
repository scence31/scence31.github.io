package com.kh.backend.notice.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.backend.notice.model.dao.NoticeDao;
import com.kh.backend.notice.model.vo.Notice;

// 서비스단을 나타내는 설계도면인 인터페이스를 상속받아서 구현할 부분
// 물력받은 것들이 죄다 추상메소드들 뿐임(완성하기 전까진 호출 불가능)
// 추상메소드를 쓰고 싶으면 직접 코드를 안 쪽에 채워야 함(구현의 강제성)
@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public ArrayList<Notice> selectNoticeList() {
		return noticeDao.selectNoticeList(sqlSession);
	}

	@Override
	public Notice selectNotice(int noticeNo) {
		return noticeDao.selectNoticeList(sqlSession, noticeNo);
	}

	@Override
	@Transactional
	public int insertNotice(Notice n) {
		
		return noticeDao.insertNotice(sqlSession, n);
	}

	@Override
	@Transactional
	public int updateNotice(Notice n) {
		
		return noticeDao.updateNotice(sqlSession, n);
	}

	@Override
	@Transactional
	public int deleteNotice(int noticeNo) {
		return noticeDao.deleteNotice(sqlSession, noticeNo);
	}
	
	
	

}
