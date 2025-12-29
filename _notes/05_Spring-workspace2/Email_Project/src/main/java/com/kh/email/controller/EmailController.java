package com.kh.email.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
	
	// 인증번호를 담아둘 HashMap 생성 전역변수로
	// key : 인증할 email 주소, value : 발급한 인증번호 6자리 숫자
	private Map<String, String> certNoList = Collections.synchronizedMap(new HashMap<>());
	// 단, 동기화 문제 때문에 위와 같이 생성해야 함
	// 동시에 여러 사용자가 인증번호 요청을 보낸 경우 인증번호를 동시에 한 Map에 Put이 가능함
	
	/*
	 * * 이슈 - 인증번호 발급 후 결과 반응이 늦음
	 * - 위 certNoList 생성시 동기화된 Map으로 생성해서 쓰고 있어서
	 * 
	 * 해결)
	 * 인증번호 요청시 이메일주소와 랜덤 OPT를 Map에 put하지말고
	 * 인증번호 TABLE을 DB에 구축하고 거기에 INSERT 하는 방식으로 보관한다.
	 * 
	 */
	
	// JavaMailSender 객체 의존성 주입
	@Autowired
	private JavaMailSender mailSender;
	
	@ResponseBody
	@PostMapping("cert")
	public String getCertNo(String email) {
		
		// System.out.println(email);
		
		// 6자리의 [1회성] 랜덤 인증번호 발급(6자리숫자로만 100,000 ~ 999,999)
		int random = (int)(Math.random() * 900000 + 100000);
		
		// 발급한 인증번호를 Controller 저장소에 보관하고 넘길 것(검증용)
		certNoList.put(email, random + "");
		// Service -> Dao -> DB까지 가서
		// INSERT INTO CERT(EMAIL, CERT_NO) VALUE(?, ?)
		
		// System.out.println(certNoList);
		
		// 이메일 주소로 random 번호를 보낼 것
		SimpleMailMessage message = new SimpleMailMessage();
		
		// 간단히 메시지 정보 담기
		message.setSubject("[KH 정보교육원] 이메일 인증 번호");
		message.setText("인증번호: " + random);
		message.setTo(email);

		mailSender.send(message);
		
		return "인증번호 완료";
	}
	
	@ResponseBody
	@PostMapping("validate")
	public String validate(String email, String checkNo) {
		
		String result = "";
		
		// 위 certNoList로부터 email, checkNo 모두 일치하는 놈 찾기
		// Service -> Dao -> DB 다녀오기
		// SELECT * FROM CERT 
		//			WHERE EMAIL = ? 
		//			AND CERT_NO = ? AND SYSDATE <= CREATE_DATE + 3분
		
		// 위의 certNoList로부터 email, checkNo 모두 일치하는 놈 찾기
		if(certNoList.get(email).equals(checkNo)) {
			
			result = "인증 성공";
		} else {
			
			result = "인증 실패";
		}
		
		// 인증 성공이든 실패든 무조건 저장된 1회성 인증번호 삭제하기
		// OTP(One Time Password - 일회성 비밀번호) 형식으로
		certNoList.remove(email);
		// Service -> Dao -> DB 다녀오기
		// DELETE FROM CERT WHERE EMAIL = ?
		
		
		// System.out.println(certNoList);
		
		return result;
		
	}

}








