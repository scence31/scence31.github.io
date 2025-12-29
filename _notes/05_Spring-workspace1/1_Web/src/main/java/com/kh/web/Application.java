package com.kh.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	/*
	 * Spring 프로젝트의 메인메소드
	 * 
	 * 시작점임, 프로젝트 구동시 제일먼저 실행됨
	 * 부트 대시보드탭에서 구동하면 댐
	 * 웹사이트의 메인페이지를 제일 먼저 띄워줌
	 * 
	 * src/main 폴더 내에 화면 프론트엔드 관련 코드 작업
	 * src 폴더 내부에는 전반적 코드 작업함. 백엔드 자바 + 프론트엔드 html
	 * 
	 * src/main/java : 자바코드 백엔드
	 * src/main/resources : 스프링 관련 환경설정 파일
	 * +
	 * webapp 폴더 : 나중에 배포되는 폴더 (웹사이트 구동시 필요한 모든 파일이 포함되어야 함)
	 * src/main/webapp : 이 폴더 내부에 메인화면 파일 만들 예정
	 * 					(src/main/webapp/WEB-INF/views 폴더)
	 * 
	 * 메인페이지는 index.jsp로 만들어야함(웹 규칙)
	 */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		// System.out.println("?");
	}

}
