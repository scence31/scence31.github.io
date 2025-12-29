<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 스크립틀릿 구문으로, html 문서형식 내에 자바코드를 기술할 수 있는 영역임
	// 아까 데이터를 request의 attribute 영역에 키+밸류로 담아놓음 setAttribute로.
	// request.getAttribue("키값") : Object (밸류값) 이렇게 뽑으면 댐.
	
	String name = (String)request.getAttribute("name"); // 다운캐스팅 형변환
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- <span id="city"><;%= city %></span> 에 사는, -->
<!-- <span id="height"><;%= height %></span> cm 이고, -->

</body>
</html>