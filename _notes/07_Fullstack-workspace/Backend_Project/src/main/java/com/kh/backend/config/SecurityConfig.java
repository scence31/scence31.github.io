package com.kh.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

	// 스프링 시큐리티 모듈에서 제공하는 BCryptPasswordEncoder 빈 등록
	// 내가 필요할 때마다 해당 객체를 받아서 쓰기 위함
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
