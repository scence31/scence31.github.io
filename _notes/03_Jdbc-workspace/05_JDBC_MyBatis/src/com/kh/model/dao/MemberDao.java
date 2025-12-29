package com.kh.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.model.vo.Member;

public class MemberDao {
	
	public int insertMember(SqlSession sqlSession, Member m) {
		
		return sqlSession.insert("memberMapper.insertMember", m);
		
	}
	
	public ArrayList<Member> selectAll(SqlSession sqlSession) {
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectAll");
	}
	
	public Member selectByUserId(SqlSession sqlSession, String userId) {
		
		return sqlSession.selectOne("memberMapper.selectByUserId", userId);
	}
	
	public ArrayList<Member> selectByUserName(SqlSession sqlSession, String keyword) {
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectByUserName", keyword);
	}
	
	public int updateMember(SqlSession sqlSession, Member m) {
		
		return sqlSession.update("memberMapper.updateMember", m);
	}
	
	public int deleteMember(SqlSession sqlSession, String userId) {
		
		return sqlSession.delete("memberMapper.deleteMember", userId);
	}

}
