package com.kh.ajax.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ajax.model.vo.Member;

@Controller
public class AjaxController {
	
	// Ajax 요청 시 전달값 뽑기
	// 1. HttpServletRequest 객체 이용
	// 2. @RequestParam 어노테이션 이용
	// 3. @RequestParam 어노테이션 생략
	// 4. 커맨드 객체 방식 또한 가능하다!!
	
	// Ajax 요청 시 응답 데이터 전달하기
	// 1. HttpServletResponse 객체 이용
	// 2. 응답할 데이터를 "문자열" 로 리턴해주기
	// (Spring 프레임워크에서 제공하는 방식)
	// > 단, 스프링 Controller 메소드에서 문자열 타입을 리턴하면
	//   기본적으로 DispatcherServlet (호출한 부분) 이 그 문자열을 리턴받을것임!!
	//   내부적으로 내가 리턴한 문자열 앞뒤로 prefix, suffix 가 붙으면서
	//   그 jsp 페이지를 찾아서 포워딩 하려고 함!!
	// > 그래서 지금부터 내가 리턴하는 문자열은 응답페이지 정보가 아니라 응답 데이터임을 알려주는
	//   어노테이션을 해당 메소드 상단에 같이 적어줘야함!!
	@ResponseBody
	@GetMapping("jqAjax1")
	public String ajaxMethod1(String input) {
		
		// 응답 문자열
		String responseData = "입력된 값 : " + input + ", 길이 : " + input.length();
		
		return responseData;
	}
	
	@ResponseBody
	@PostMapping(value="jqAjax2", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(String name, int age) {
		
		// 1. 응답데이터들을 하나의 문자열로 연이어서 내보내기		
		// 2. response 객체를 이용해보기
		// 3. JSON
		/*
		 * - JSON (JavaScript Object Notation)
		 * : 자바스크립트 객체 표기법
		 *   ajax 응답 시 데이터 전송에 사용되는 포맷 중 하나
		 *   다량의 데이터를 형식적으로 구조화 해서 묶을 때 많이 쓰임!! 
		 *   (공공데이터, 빅데이터 분야에서도 많이 쓰임)
		 *   기본적으로 자바에서는 JSON 관련 코드를 제공해주지 않기 때문에
		 *   JSON 을 이용하려면 관련 라이브러리 (.jar) 를 연동해서 써야 함!!
		 *   
		 * - JSON 처리 시 사용되는 객체 종류 (json-simple-1.1.1.jar 에서 제공)
		 * 1. JSONArray
		 * : [value, value, value, ...]
		 *   즉, 자바스크립트의 배열 형식으로 데이터 여러개를 묶어서 한번에 보낼 수 있음!!
		 * 
		 * 2. JSONObject
		 * : {key : value, key : value, ...}
		 *   즉, 자바스크립트의 객체 형식으로 데이터 여러개를 묶어서 한번에 보낼 수 있음!!
		 */
		
		// JSONArray 이용
		/*
		JSONArray jArr = new JSONArray(); // []
		jArr.add(name); // ["홍길동"]
		jArr.add(age); // ["홍길동", 35]
		*/
		// > JSONArray 는 자바의 ArrayList 와 같은 구조임!!
		//				  자바스크립트의 배열과 같은 구조임!!
		
		// JSON 객체를 response 객체로 넘겨보기
		// response.setContentType("application/json; charset=UTF-8");
		// response.getWriter().print(jArr);
		// > "['홍길동', 35]" 로 응답데이터가 넘어감!!
		//   배열인척 하는 문자열 타입임
		// > setContentType 메소드 호출 시 "text/html;" --> "application/json;"
		
		// JSON 객체를 문자열 리턴 방식으로 넘겨보기
		// return jArr.toJSONString();
		// > JSON 객체를 객체인척 하는 문자열 타입으로 바꿔주는 메소드
		//   그냥 리턴을 하면 문자열로 넘어가므로 설정을 하나만 추가해주면 됨!!
		
		// JSONObject 써보기
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("name", name); // {name : "홍길동"}
		jObj.put("age", age); // {name : "홍길동", age : 35}
		// > JSONObject 는 자바의 HashMap 와 같은 구조임!!
		//				   자바스크립트의 객체와 같은 구조임!!
		
		return jObj.toJSONString();
		
	}
	
	// 객체형식의 응답데이터를 넘겨야 할 경우
	@ResponseBody
	@GetMapping(value="jqAjax3")
	public Member ajaxMethod3(int userNo) {
		
		// DB로부터 회원 한 명의 정보를 조회했다는 가정 하에 응답할 데이터
		Member m = new Member(userNo, "고길동", 50, "남");
		
		// 1. response 객체를 통해 응답데이터 넘기기
		// 2. JSON
		// 자바 VO 객체형식을 JSON 형식으로 바꾸고 싶으면 JSONObject 사용
		// {속성명 : 속성값, ...}
		// 속성명 자리에 필드명을 적고 속성값 자리에 해당 필드값을 적을 예정
		// JSONObject jObj = new JSONObject(); // {}
		// jObj.put("userNo", m.getUserNo());
		// ...
		// 문자열 리턴방식으로 넘길 것
		// return jObj.toJSONString();
		
		// VO 필드 개수만큼 put 메소드를 이용해서 일일이 JSON 객체로 옮겨담는다.
		// 이 때, 필드명이 속성명 부분에 오도록 정확하게 쓰면 됨. (정석)
		// 근데 필드 많으면 귀찮 => jackson databind 라이브러리 사용(스프링부트에 자동연동 되어있음)
		return m;
		// 해당 VO 타입으로 바로 리턴해줌 => jackson이 JSONObject 타입으로 가공해서 응답데이터 넘김
		// 객체의 필드명을 자바스크립트 객체의 속성명으로 바꿈
		
	}
	
	// ArrayList를 응답데이터로 넘길 경우
	@ResponseBody
	@PostMapping("jqAjax4")
	public ArrayList<Member> ajaxMethod4() {
		
		// 회원전체조회 요청을 DB로부터 처리했다는 가정 하에 회원 정보가 담긴 ArrayList 세팅
		ArrayList<Member> list = new ArrayList<>();
		
		list.add(new Member(1, "고길동", 50, "남"));
		list.add(new Member(2, "김해린", 30, "여"));
		
		// JSONArray로 바꾸면 됨. jackson 활용
		return list;
		// 알아서 JSON으로 가공됨
	}

}


























