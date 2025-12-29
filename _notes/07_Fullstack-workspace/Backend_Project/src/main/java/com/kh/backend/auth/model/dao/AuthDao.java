package com.kh.backend.auth.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.backend.member.model.vo.Member;

@Repository
public class AuthDao {

	public Member loginAdmin(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.selectOne("authMapper.loginAdmin", userId);
	}

}
