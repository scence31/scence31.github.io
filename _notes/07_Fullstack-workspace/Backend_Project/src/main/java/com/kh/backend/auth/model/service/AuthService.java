package com.kh.backend.auth.model.service;

import com.kh.backend.member.model.vo.Member;

public interface AuthService {
	
	// 인증용 서비스(로그인용)
	Member loginAdmin(String userId);
	
	

}
