package com.kh.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/*
 * * Logging
 * 
 * 서버에서 필요에 의해 기록을 남기는 행위
 * - 파일, 콘솔, 이메일, DB 등 다양한 곳에 기록을 남길 수 있음
 * 로그인, 게시글작성, 에러 기록, ... 실무 필수 : 디버깅, 이슈트래킹, 보안감사 기록제출용 등
 * 
 * Logger
 * - 로깅을 하기 위한 객체로 log4j, logback 종류가 있음(기능지원 프레임워크) 각 레거시/부트 기본값
 * 스프링 프로젝트에서 Logger 쓰려면 암거난 써도 됨 위 종류 중
 * 
 * Slf4j (log4j, logback 기술 통합해서 관리해줌 - 부모 인터페이스)
 * -> 중간다리 라이브러리
 * 
 * 스프링 PSA 특징 - 중간다리 필요
 * 스프링 + 보조해주는라이브러리 + 마이바티스
 * 스프링 + Slf4j + logback
 * 
 * 사용방법
 * 1. Logger 객체 생성 - 직접 생성구문 / Lombok이 만들어준거 객체 받아쓰기
 * 2. 위 객체에서 제공하는 메소드 호출 
 * 
 */

// 1. Logger 객체 얻어내기 - Lombok꺼 사용
@Slf4j // Logger 객체 자동생성 어노테이션 - 필드부에 Logger log; 객체 생성됨(전역변수)
@SpringBootTest
class BackendProjectApplicationTests {

	@Test
	void contextLoads() {
		
		// 2. Logger 객체에서 제공하는 로깅 수행시 필요한 구문들 호출 log.메소드명()
		log.debug("난 debug");
		log.info("난 info");
		log.warn("난 warn");
		log.error("난 error");
		// 콘솔창에 메시지의 종류, 시간, 내용, 위치가 sysout처럼 출력됨.
		// debug는 출력에서 제외됨
		
		// logback 프레임워크 관련 환경설정 파일을 작성 후
		// 어떻게 작성하냐에 따라 로깅이 되는지 확인
		
		// * logback 환경설정 파일 - logback-spring.xml
		// 이 프로젝트의 외부 프레임워크 또는 라이브러리 연동 파일을 작성하는 폴더 내부에 생성 - src/main/resources 안에
		
		
	}

}























