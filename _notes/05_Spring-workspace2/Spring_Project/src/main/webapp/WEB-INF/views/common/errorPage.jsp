<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 에러페이지 또한 상단에 메뉴바가 보이게끔 -->
	<jsp:include page="menubar.jsp" />
	
	<br><br>
	
	<h1 align="center" style="color : red;">
		${ requestScope.errorMsg }
	</h1>

</body>
</html>





