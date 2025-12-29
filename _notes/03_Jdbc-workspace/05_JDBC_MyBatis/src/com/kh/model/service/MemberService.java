package com.kh.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {

		SqlSession sqlSession = Template.getSqlSession();

		int result = new MemberDao().insertMember(sqlSession, m);

		if (result > 0) {

			sqlSession.commit();

		} else {

			sqlSession.rollback();

		}

		sqlSession.close();

		return result;
	}

	public ArrayList<Member> selectAll() {

		SqlSession sqlSession = Template.getSqlSession();

		ArrayList<Member> list = new MemberDao().selectAll(sqlSession);

		sqlSession.close();

		return list;

	}

	public Member selectByUserId(String userId) {

		SqlSession sqlSession = Template.getSqlSession();

		Member m = new MemberDao().selectByUserId(sqlSession, userId);

		sqlSession.close();

		return m;

	}

	public ArrayList<Member> selectByUserName(String keyword) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Member> list = new MemberDao().selectByUserName(sqlSession, keyword);
		
		sqlSession.close();
		
		return list;
	}

	// (트랜잭션) 회원 정보 수정
	public int updateMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new MemberDao().updateMember(sqlSession, m);
		
		if (result > 0) {
			
			sqlSession.commit();
			
		} else {
			
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	// (트랜잭션) 회원 삭제
	public int deleteMember(String userId) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new MemberDao().deleteMember(sqlSession, userId);
		
		if (result > 0) {
		
			sqlSession.commit();
		
		} else {
		
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

}
