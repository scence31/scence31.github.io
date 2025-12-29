package com.kh.myweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

	// war로 배포하겠다는 코드 작성
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(SpringProjectApplication.class);
	}
	
	

}
