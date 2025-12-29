package com.kh.backend.auth.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.backend.auth.model.dao.AuthDao;
import com.kh.backend.member.model.vo.Member;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member loginAdmin(String userId) {
		return authDao.loginAdmin(sqlSession, userId);
	}

}
