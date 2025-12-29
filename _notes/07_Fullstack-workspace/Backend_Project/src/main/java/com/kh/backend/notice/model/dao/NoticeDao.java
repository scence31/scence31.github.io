package com.kh.backend.notice.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.backend.notice.model.vo.Notice;

@Repository
public class NoticeDao {

	public ArrayList<Notice> selectNoticeList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("noticeMapper.selectNoticeList");
	}

	public Notice selectNoticeList(SqlSessionTemplate sqlSession, int noticeNo) {
		
		return sqlSession.selectOne("noticeMapper.selectNotice", noticeNo);
	}

	public int insertNotice(SqlSessionTemplate sqlSession, Notice n) {
		
		return sqlSession.insert("noticeMapper.insertNotice", n);
	}

	public int updateNotice(SqlSessionTemplate sqlSession, Notice n) {
		
		return sqlSession.update("noticeMapper.updateNotice", n);
	}

	public int deleteNotice(SqlSessionTemplate sqlSession, int noticeNo) {
		
		return sqlSession.update("noticeMapper.deleteNotice", noticeNo);
	}

	
}
