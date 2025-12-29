package com.kh.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/*
 * * 애플리케이션 테스트 수행
 * - 소프트웨어가 제대로 동작하는지 미리 검증하는 과정
 * - 개발단계 별 크게 네 가지 종류가 있음
 * 
 * * 테스트 종류
 * 1. 단위테스트: 개발 과정에서 이루어지는 메소드단위의 코드가 잘 동작하는지 테스트하는 개념
 * 2. 통합테스트: 모든 코드들을 취합 후 기능이 잘 동작하는지 테스트
 * 3. 시스템테스트: 모든 코드들을 취합 후 비기능적 요소들이 동작하는지 테스트(기능이 아닌! 사용자 편의, 보안, 고객 요구사항 반영여부 등)
 * 4. 인수테스트: 실제 사용자 입장에서 테스트 수행하는 개념. 주로 테스트를 전문적으로 하는 직무의 사람들이 수행(QA). 베타서비스도 해당됨
 * 
 * * TDD(Test Driven Development 테스트 주도 개발)
 * - 본 기능 구현 전에 테스트코드를 먼저 작성하고 잘 동작하면 실제 기능구현에 코드를 적용하는 방식(즉, 단위테스트를 기능구현마다 미리 해본다)
 * - 자바(스프링) 대표 테스트용 프레임워크 JUnit 사용(스프링부트에 기본세팅 되어있음)
 * 
 */

@SpringBootTest // 테스트용 클래스임을 알려주는 어노테이션
class EmailProjectApplicationTests {
	
	// JavaMailSender 객체 의존성 주입으로 받기
	@Autowired
	private JavaMailSender mailSender;

	@Test // 이 메소드를 테스트 용도로 사용 어노테이션
	void contextLoads() throws MessagingException {
		
		// 이 안에서 테스트할 자바코드 작성 후 실행 ctrl + f11
		// System.out.println("?");
		
		// * 이메일 전송 테스트코드 작성 TDD
		// 1. 단순 텍스트만 이메일로 보내는 경우
		// - SimpleMailMessage 객체를 이용하는 방법
		// SimpleMailMessage 객체에 보낼 메일에 대한 정보를 담아주고
		// JavaMailSender 객체를 통해 메일 발송
		
		// 심플메일메시지 객체 생성(메시지 정보를 담는 용도 - 제목, 내용, 첨부파일, 받는사람, 참조, 숨은참조)
		/*
		SimpleMailMessage message = new SimpleMailMessage();
		
		// setter 메소드를 통해 위 항목들을 담아주기
		message.setSubject("제목입니다.");
		message.setText("내용");
		// message.setTo("scence31@naver.com"); // 수신인 1명일 경우
		String[] to = {"scence31@naver.com", "605045@naver.com", "gdhdhd@naver.com"};
		message.setTo(to); // 여러 명이면 배열로 넘기기
		
		String[] cc = {"scence31@naver.com", "khh001224@gmail.com"};
		message.setCc(cc);
		
		// 숨은참조
		message.setBcc("leo.im@kakao.com");
		
		// 마지막으로 JavaMailSender 객체를 이용해서 메시지 전송
		// 내가 직접 생성하는 것이 아닌 스프링의 의존성 주입 방식으로 얻어내기
		mailSender.send(message);
		*/
		
		
		// 2. 첨부파일 또는 메일 내용 안에 html코드를 삽입하여 같이 보내는 경우
		// MimeMessage 객체 이용
		// MimeMessage 객체에 보낼 메일에 대한 정보를 담아주고
		// 최종적으로 JavaMailSender 객체를 통해 해당 메일을 전송(같음)
		
		// MimeMessage 객체생성
		MimeMessage message = mailSender.createMimeMessage();
		// JavaMailSender 객체로부터 얻어내기
		
		// MimeMessage 객체에 메일내용을 직접 담는 것이 아닌
		// Helper 역할을 할 객체를 만들어서 거기에 메일에 대한 정보를 담는다.
		// MimeMessageHelper 객체 생성
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
		// 매개변수: MiemMessage 객체, 첨부파일여부, 인코딩값
		
		// MimeMessageHelper 객체에 메일정보 담기
		
		mimeMessageHelper.setSubject("제목");
	
		// 내용(html 코드 포함해서 넘기기)
		String text = """
					<html>
						<head></head>
						<body>
							<div>
								<h1>마임메시지 테스트 메일 본문 내용입니다.</h1>
								
								<br><hr><br>
								
								<div style="border : 1px solid gray;">
									<h3 align="center">잘되니</h3>
								</div>
							</div>
						</body>
					
					</html>
				
				""";
		// 텍스트블록 형식으로 지정
		
		mimeMessageHelper.setText(text, true);
		// true일 경우 html 태그가 태그로서 해석되어 보임
		// false일 경우 그냥 텍스트로
		
		// 받는사람
		String[] to = {"scence31@naver.com", "605045@naver.com", "gdhdhd@naver.com"};
		mimeMessageHelper.setTo(to);
		
		// 참조
		String[] cc = {"scence31@naver.com", "khh001224@gmail.com"};
		mimeMessageHelper.setCc(cc);
		
		// mimeMessage는 첨부파일도 보낼 수 있음
		// DataSource 객체를 통해 첨부파일 정보 담기
		DataSource dataSource = new FileDataSource("C:\\04_Frontend-workspace\\2_css-workspace\\resources\\Marten-snow-winter-wildlife_2880x1800.jpg");
		// 첨부파일 경로 정확히
		// 여러 개 첨부파일 보내고 싶으면 DataSource 객체 계속 생성
		
		// 메시지 내용에 첨부하기
		mimeMessageHelper.addAttachment(dataSource.getName(), dataSource);
		// 이 때 메일 서비스에서 제공하는 파일업로드 정책에 맞추기
		// 최대개수, 용량, 확장자종류 등
		
		// JavaMailSender 객체를 이용해서 메시지 전송
		mailSender.send(message);
		
	}

}
















