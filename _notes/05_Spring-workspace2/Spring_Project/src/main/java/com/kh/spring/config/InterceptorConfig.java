package com.kh.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.spring.common.interceptor.LoginInterceptor;


/*
 * * 인터셉터를 빈 등록
 * WebMvcConfigurer 인터페이스를 상속받아서 구현해야 함
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;

	// 빈 등록 과정에서
	// 어느 요청이 어느 인터셉터를 거칠지 등록하는 부분
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 매개변수
		// registry : 인터셉터들의 정보를 보관하는 객체로, 내가 필요로 하는 인터셉터 정보들을 차곡차곡 담기
		// 인터셉터 종류와 가로챌 요청의 url 주소를 같이 기술함.
		registry.addInterceptor(loginInterceptor).addPathPatterns("/member/myPage").addPathPatterns("/notice/enrollForm")
				.addPathPatterns("/notice/updateForm").addPathPatterns("/board/updateForm").addPathPatterns("board/delete");
		// 지금부터 loginInterceptor는 /member/myPage 요청할 때만 거쳐가게 함.
		// 막고싶은 페이지 다 메소드체이닝으로 하면 됨
		
		// 만약 admin 계정에 대한 권한체크 하고싶다면
		// com.kh.spring.common.interceptor 패키지에 AdminCheckInterceptor 클래스 구현 후
		// 여기서 registry.addInterceptor(AdminCheckInterceptor객체).addPathPatterns(~~)...
		// 한 번 더 등록하면 됨.
	}
	
	

}
