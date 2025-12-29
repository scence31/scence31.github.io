package com.kh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller // 요청을 받아주는 컨트롤러 역할을 하겠다.
public class BasicController {
	
	/*
	 * 요청시 전달된 값을 뽑는 방법
	 * HttpServletRequerst 객체로부터 뽑기
	 * 전달값들은 위 객체의 parameter 영역 안에 키+밸류 세트로 담겨있음
	 * key(name 속성값) + value(value 속성값) 세트
	 * 
	 * Http~ 데이터 뽑는 메소드
	 * 1. request.getParameter("키값") : String 리턴해줌. (그에 해당하는 밸류값을)
	 * 2. request.getParameterValues("키값") : String[] 리턴. ex) 체크박스
	 */
	
	/*
	 * * 응답페이지
	 * 
	 * - 자바코드 내에 html코드 기술해서 전달하는 방법 (Servlet 방법)
	 * HttpServletResponse 객체 필요 / 근데 잘 안쓰임
	 * 
	 * ====================
	 * 그래서 나온게 JSP 방법.
	 * html 코드 내에 java코드를 기술함. Servlet 방법과 반대 느낌
	 * HttpServletResponse 객체 필요 없음. 
	 * 
	 * 응답페이지 정보: /WEB-INF/views/responsePage.jsp
	 * 
	 * 1단계)
	 * .jsp 파일에 데이터 꺼낼 수 있게 코딩작성하기.
	 * request 객체에 담아서 보내주면 댐(attribute 영역에 키+밸류 세트로 담아서)
	 * request.setAttribute("키", 밸류);
	 * 키는 무조건 String 타입이어야 함. 밸류는 Object 타입임 (다형성 적용)
	 * 
	 *  
	 * 2단계)
	 * JSP 방법으로는, void를 String으로 바꿔야 함 리턴때매.
	 * 마지막에 리턴할 때 클래스명만? 리턴해주면 댐
	 * return "responsePage";
	 * => 요청 들어올 때마다 메소드 호출해주는 누군가 있음.
	 * 걔한테 이걸 리턴해준거임. 그럼 내부적으로
	 * application.properties 설정파일에서 View Resolver 설정으로 인해
	 * prefix + 리턴값 + suffix 이렇게 문자열로 연이어주고
	 * 최종파일 경로를 완성하여 해당 화면을 보여주는 원리임.
	 * 
	 * 
	 */
	
	// GET 방식으로 test1에 요청했을 경우 이 메소드를 호출하고 싶음!
	@RequestMapping(value="test1", method=RequestMethod.GET) // POST도 동일
	// teset1 url 주소로 GET 방식 요청이 오면 이 메소드를 호출해주겠다.
	// method 요청방식 get post 틀리면 405 에러 뜸
	public void requestGetController(HttpServletRequest request) {
		// post면 public String
		// throw IOException
		
		String name = request.getParameter("name");
		// post에서는 request.setAttribute("name", name);
		
		// String age = request.getParameter("age");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String[] foods = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(age);
		
		if(foods == null) {
			
			System.out.println("foods: 없음");
		} else {
			
			System.out.println(String.join(", ", foods));
			// String.join(구분자, 배열명);
		}
		
		
	}

}
