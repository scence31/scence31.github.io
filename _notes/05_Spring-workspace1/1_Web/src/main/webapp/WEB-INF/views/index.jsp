<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>* JSP(Java Server Page)</h1>
	
	<p>
		- JSP란 HTML 코드 형식 내에서 자바코드를 쓸 수 있는 자바언어
		기존 자바코드 내에서 응답화면 구현하면 복잡해서 간단하게 함 <br>
	</p>
	
	<br><hr>
	
	<h2>JSP Element 표현법</h2>
	
	<h3>1. JSP 스크립팅 원소(Scripting Element)</h3>
	
	<p>- JSP 페이제에서 자바코드를 기술할 수 있게 해주는 기능</p>
	
	<ol>
		<li>
			선언문 : &lt;%! %&gt; <br>
			이 페이지에서 사용될 멤버변수와 메소드를 선언하기 위해 사용
		</li>
		<li>
			스크립틀릿 : &lt;% %&gt; <br>
			JSP에서 자바코드를 기술하기 위한 제일 기본적인 표현법
		</li>
		<li>
			표현식(출력식) : &lt;%=%&gt; <br>
			자바언어로 작성한 값 또는 메소드 호출결과값 또는 변수값 등을
			브라우저 화면 상에 출력하기 위해 사용
		</li>
	</ol>
	
	<!-- 다른 페이지에서 연습해볼 것! -->
	<!--
	<a href="WEB-INF/views/01_ScriptingElement.jsp">View detail &raquo;</a>
	 -->
	<!-- WEN-INF 폴더 안쪽은 WAS에 의해 관리되기 때문에 보안상 url 주소 등으로 직접접근이 불가함 -->
	 
	<a href="/jsp/scripting">View Detail &raquo;</a>
	<!-- 특정 jsp 파일 경로를 댄 것이 아니라 url 주소를 댄 것. -->
	
	<br> <hr>
	
	<h3>
		2. 지시어, 지시자(Directive)
		&lt;%@ page/include/taglib 속성명="속성값" 속성명="속성값" ... %&gt;
	</h3>
	
	<p>
		- JSP 페이지 전체에 영향을 미치는 정보를 기술할 때 쓰임
	</p>
	
	<ol>
		<li>
			page 지시어: 현재 이 jsp 페이지를 처리하는데 필요한 각종 환경설정 관련 속성을 기술하는 부분
			<ul>
				<li>language : 사용할 스크립트 언어 유형을 지정</li>
				<li>contentType : 웹브라우저가 받아볼 해당 페이지의 형식, 페이지에 출력할 텍스트의 인코딩 방식</li>
				<li>pageEncoding : jsp 파일에 기록된 자바코드의 인코딩 방식을 지정</li>
				<li>import : 자바의 import와 같음</li>
				<li>errorPage : jsp 페이지 상에 자바코드 실행에 오류가 발생한 경우, 대체해서 에러페이지 경로 보여줌</li>
			</ul>
			
			<a href="/jsp/directive/page">View Detail &raquo;</a>
	
		</li>
		<li>include 지시어 : jsp 페이지 내 또다른 jsp 페이지를 포함하고자할 때 사용하는 지시어 <br>
			<a href="/jsp/directive/include">View Detail &raquo;</a>
		</li>
		<li>taglib 지시어 : jsp 기능을 더 확장할 수 있는 인자임. 추가 라이브러리를 등록하는 구문 
		</li>
	</ol>
	
	<br><hr>
	
	<%-- JSP 예제 --%>
	
	<h2>
		피자 주문 페이지 (jsp) : 주문요청 --> 주문요청을 받는 Controller --> 결제페이지(응답 페이지)
	</h2>
	
	<h3>
		<a href="/jsp/pizza/orderForm">피자 주문페이지로 &raquo;</a> <!-- get/post 방식 구분하는법도 -->
	</h3>
	
	<br><hr>
	
	<%-- 
		숙제)
	 	아래 링크를 클릭할 경우 com.kh.jsp.controller 패키지의 MemberController 클래스 작성 후
	 	그 안의 /jsp/member/loginForm 요청이 get 방식으로 들어왔을 때, 로그인 페이지가 보이게 코딩
	 	로그인 페
	 --%>
	
	<h2>로그인 페이지 (jsp) : 로그인 요청 --> 로그인 요청을 받는 Controller --> 성공/실패 여부 응답페이지</h2>
	
	<h3>
		<a href="/jsp/member/loginForm">로그인 페이지로 &raquo;</a>
	</h3>

</body>
</html>