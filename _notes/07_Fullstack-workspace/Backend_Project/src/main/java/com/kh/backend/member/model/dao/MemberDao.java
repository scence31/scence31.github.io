package com.kh.backend.member.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.backend.member.model.vo.Member;

@Repository
public class MemberDao {
	
	public ArrayList<Member> selectMemberList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectMemberList");
	}
	
	public Member selectMember(SqlSessionTemplate sqlSession, String userId) {
		
		return sqlSession.selectOne("memberMapper.selectMember", userId);
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.update("memberMapper.updateMember", m);
	}

	public int deleteMember(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.deleteMember", userId);
	}

}
