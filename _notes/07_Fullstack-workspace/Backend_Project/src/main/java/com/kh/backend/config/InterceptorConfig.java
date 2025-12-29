package com.kh.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.backend.common.interceptor.AuthInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	// AuthInterceptor 객체 받아서 요청 전에 거치도록 연결
	@Autowired
	private AuthInterceptor authInterceptor;

	// 연결 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(authInterceptor)
				.addPathPatterns("/member/**")
				.addPathPatterns("/notice/**")
				.addPathPatterns("/board/**")
				.excludePathPatterns("/auth/login");
	}
	
	
	

}
