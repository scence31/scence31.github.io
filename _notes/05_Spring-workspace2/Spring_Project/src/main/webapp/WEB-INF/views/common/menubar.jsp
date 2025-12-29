<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* 모든 페이지마다 들어가는 공통 스타일 */
	.outer {
		width : 1000px;
		border : 1px dotted gray;
		margin : auto;
		margin-top : 50px;
	}

	/* 로그인 관련 영역 스타일 - 오른쪽으로 (오른손잡이들이 많기 때문에 관례상 배치) */
	#login-form, #user-info {
		float : right;
	}

	#login-form input {
		padding : 5px;
	}

	#user-info a {
		text-decoration : none;
		color : black;
		font-size : 12px;
	}

	/* 메뉴바 스타일 - 좌우로 배치되게끔 */
	.nav-area {
		background-color : black;
	}

	.menu {
		display : table-cell;
		/* 블록요소를 옆으로 배치해주는 속성 (가로길이가 짧아지더라도 옆으로 배치해줌) */
		height : 50px;
		width : 150px;
	}

	.menu>a {
		text-decoration : none;
		color : white;
		font-size : 18px;
		font-weight : 550;
		width : 100%;
		height : 100%;
		display : block;
		line-height : 50px;
	}

	.menu>a:hover {
		background-color : gray;
		color : white;
		text-decoration : none;
	}
</style>

<!--
	내가 짠 스타일 + 부트스트랩 스타일 혼합해서 사용 가능!!
	부트스트랩 연동하기 - menubar.jsp 에 작성하면 include 할 때 마다 가져와짐!!
-->
	<!-- alertify js 연동 구문 -->
	<!-- JavaScript -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/alertify.min.js"></script>
	
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/alertify.min.css"/>
	<!-- Default theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/themes/default.min.css"/>
	<!-- Semantic UI theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/themes/semantic.min.css"/>

    <!-- 부트스트랩 연동 구문 (CDN 방식) -->
    <!-- 예쁘게 정의된 스타일들이 담겨있는 CSS 파일 연동 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- 간단한 동작들을 정의해둔 js 파일 연동 -->
    <!-- jQuery 연동 구문이 부트스트랩 연동 구문 안에 포함 (부트스트랩은 jQuery 로 만들어졌기 때문) -->
    <!-- jQuery library -->
    <!-- 온라인 방식 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<!-- 
		공통 코드 작업 : 1회성 alert 띄우기 기능
		- session 에 alertMsg 가 있을 경우 alert 로 띄워주고, alertMsg 지우기 (1회성)
		  (JSP Action 태그는 script 태그 내에 기술 불가!! script 태그 내에는 오직 자바스크립트만!!)
	-->
	<c:if test="${ not empty sessionScope.alertMsg }">
		<script>
			let alertMsg = "${ sessionScope.alertMsg }";
			// alert(alertMsg);
			alertify.alert('Alert', 
						   alertMsg, 
						   function(){ alertify.success('Ok'); });
		</script>
		<c:remove var="alertMsg" scope="session" />
	</c:if>
	
	<!-- 로고 -->
	<br>
	<h1 align="center">Welcome to D Class</h1>
	
	<!-- 로그인 관련 영역 -->
	<div class="login-area">

	<c:choose>
	<c:when test="${ empty sessionScope.loginUser }">
		<!-- case 1. 로그인 전 - 로그인 폼 -->
		<!-- 
			로그인 요청 시
			http://localhost:8006/spring/member/login 으로 요청하고 싶음!!
			
			절대경로방식 action="/spring/member/login"
			상대경로방식 action="member/login" (추후 이슈 발생 가능)
		-->
		<form id="login-form" action="/spring/member/login" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" required value="${ cookie.saveId.value }">
						<!-- 쿠키에 저장된 키+밸류 세트의 데이터들은 Cookie[] 안에 저장됨.
						근데 EL구문 이용하면 배열반복로직 안해도 됨. cookie.키값.value 호출 -->
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="userPwd" required>
					</td>
				</tr>
				<!-- 아이디 저장 쿠키 -->
				<tr>
					<th clospan="2">
						<input type="checkbox" id="saveId" name="saveId" value="y">
						<label for="saveId">아이디 저장</label>
						<!-- 아이디저장 체크박스도 체크 유지되어야 함 -->
						
						<script>
							$(function() {
								
								// 모든 화면이 로딩된 후 체크박스 체크상태 유지
								let saveId = "${cookie.saveId.value}";
								
								if(saveId != "") { // 아이디젖아 상태, 자바스크립트는 문자열 동등비교 가능함!!!
									
									$("#saveId").attr("checked", true); // 기타속성은 attr로, checked상태의 true
									
									
								}
							})
						</script>
					</th>
				</tr>
				<tr align="center">
					<th colspan="2">
						<button type="submit" class="btn btn-secondary btn-sm">로그인</button>
						<button type="button" class="btn btn-secondary btn-sm" 
											  onclick="enrollPage();">회원가입</button>
					</th>
				</tr>
			</table>
		</form>
		
		<script>
			// 회원가입 버튼 클릭 시 실행할 함수
			function enrollPage() {
				
				// 회원가입 페이지로 이동시키기!!
				location.href = "/spring/member/enrollForm";
				// > 이 때, location.href 로 url 주소를 이동하면 GET 방식임!!
			}
		</script>
	</c:when>
	<c:otherwise>
		<!-- case 2. 로그인 후 -->
		<div id="user-info">
			<b>${ sessionScope.loginUser.userName }</b> 님 환영합니다. <br><br>
			<div align="center">
				<a href="/spring/member/myPage">마이페이지</a>
				<a href="/spring/member/logout">로그아웃</a>
			</div>
		</div>
	</c:otherwise>
	</c:choose>	
	
	</div>

	<br clear="both"> <!-- float 속성 해제 -->
	<br>
	
	<!-- 메뉴바 (navigator) 영역 -->
	<div class="nav-area" align="center">

		<div class="menu"><a href="/spring">HOME</a></div>
		<div class="menu"><a href="/spring/notice/list">NOTICE</a></div>
		<!-- 보통 게시판은 메뉴 클릭 후 첫 게시판 화면 진입 시 "목록 조회" 화면이 나옴 -->
		<div class="menu"><a href="/spring/board/list">BOARD</a></div>
		<!-- 
			마찬가지로 BOARD 메뉴 클릭 시 일반게시판 목록 조회 화면으로 넘길 것!! 
			페이징처리가 들어갈 경우 1번 페이지가 보여져야 하는 것이 국룰!!
			요청 시 전달값으로 cpage 라는 키값으로 1 이라는 밸류값을 같이 보내고 있음!!	
			> 어차피 목록 최초 접근 시 1번 페이지가 보여져야 하는게 국룰이기 때문에 
			  굳이 쿼리스트링을 생략할 수도 있다!! (학원 홈페이지 게시판처럼)
		-->
		<div class="menu"><a href="/spring/thumbnail/list">PHOTO</a></div>
		<!-- 
			마찬가지로 PHOTO 메뉴 클릭 시 사진게시판 목록 조회 화면으로 넘김!!
			단, 페이징 처리가 들어가면 1번 페이지가 보여져야 함!!
			(cpage 키값이 없으면 자동으로 1번 페이지를 요청하게끔 유도)
		-->

	</div>

</body>
</html>