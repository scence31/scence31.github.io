package com.kh.spring.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * * Filter(필터) - 개념만, 인터셉터와 차이점
 * - 요청이 컨트롤러로 최종 도달하기 전에 걸러주는 역할(공통코드로 작성) - view랑 디스패처서블릿 사이
 * - 스프링에서 나온 개념은 아님
 * - 이미 필터 하나 쓰고있음 (인코딩필터) => post방식이 들어오면 get방식과 다른 인코딩방식으로 전달값이 넘겨져서 한글 깨짐
 * 근데 필터때매 안 깨지는 것임.
 * 
 * 
 * * Interceptor
 * - 요청이 디스패처서블릿을 거쳐 컨트롤러 클래스로 도달하기 전/후에 요청을 가로채서
 *   선처리/후처리할 구문을 작성 (스프링에서 제공하는 개념)
 *  View --> DispatcherServlet --> Interceptor --> Controller
 *  							<--				<--
 *   
 * - 요청이 컨트롤러로 도달하기 전 로그인 권한체크시 많이 씀(선처리)
 * - 로깅 등을 남길 때도 많이 씀(후처리)
 * 공통적으로 선/후처리 하는 구문을 작성할 때 씀 -> 공통코드 작업을 도와주는 개념(AOP 개념 - 공통코드로 재사용성 높임)
 * 
 * * Interceptor 클래스 구현방법
 * - 스프링에서 제공하는 HandlerInterceptor 인터페이스를 상속받아서 구현
 * - 인터페이스: 모든 필드가 상속필드, 메소드가 추상메소드로 구상된 일종의 추상클래스
 * => 물려받은 코드가 모두 껍데기만 있는 추상메소드라서 코드는 모두 오버라이딩 해야함
 * 
 * 근데!! HandlerInterceptor는 다 dafault 메소드라서 일부 메소드만 오버라이딩하면 됨
 * 
 * * Interceptor 간섭시점
 * 1. 요청 전 : preHandle - 요청이 컨트롤러로 도달하기 전에 실행할 공통코드를 작성(선처리 코드)
 * 2. 요청 후 : postHandle - 요청처리 결과가 디스패처서블릿으로 도달하기 전에 실행할 공통코드를 작성(후처리 코드)
 * 
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

	// 1. 요청 전에 가로채는 메소드 (선처리용)
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// * 매개변수
		// request : 사용자 요청, 요청시 전달값이 담겨있음 (parameter 영역에)
		// response : 요청에 대한 응답, 응답시 필요한 유용한 메소드들이 담겨있음
		// handler : 이 요청은 누가 처리하기로 했는가에 대한 정보가 담겨있음
		/*
		System.out.println("preHandler 작동 하나");
		System.out.println(handler);
		
		// * 리턴값 boolean
		// true : 요청에 최종으로 도달하겠다
		// false : 요청 반려
		return true;
		*/
		
		// preHandle에서 로그인 여부 확인 로직을 구현할 것임
		// 요청이 들어가기 전에 로그인이 되었나부터 검사해야 해서
		
		// 로그인한 회원 정보는 session에 담김
		
		// 1. session 객체부터 얻어내야 함
		// 매개변수로 정의된 request 객체를 통해 얻어낼 수 있음
		HttpSession session = request.getSession();
		
		// 2. session으로부터 로그인한 회원의 정보를 꺼내오기
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		// 3. 로그인 여부 판별
		if(loginUser != null) { // 로그인상태
			
			return true;
			
		} else {
			
			// 로그인 후 이용가능함을 알려주기 위한 일회성 알람문구 session 담기
			session.setAttribute("alertMsg", "로그인 먼저");
			
			// 메인페이지(로그인페이지) url 재요청
			// response 객체에서 제공하는 sendRedirect 메소드를 통해 url 재요청 가능 포트번호 다음부분부터
			response.sendRedirect("/spring");
			
			return false;
		}
		
	}
	
	
	// 2. 요청 후에 가로채는 메소드 (후처리용)
	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// * 매개변수
		// request : 선처리용 + 응답데이터 attribute
		// response : 동일
		// handler : 누가 처리했는가에 대한 정보
		// modleAndView : model (응답데이터) + view (응답페이지) 정보
		
		System.out.println("postHandle 작동되나?");
		
		System.out.println(handler);
		System.out.println(modelAndView);
		
		// * 리턴값(void) - 없음
		// 이미 postHandle은 Controller에서 요청처리가 다 끝나고 돌아오는 방향이기 때문에 처리된 내용을 되돌릴 수 없음
		
	}
	*/
	
	
	

}
