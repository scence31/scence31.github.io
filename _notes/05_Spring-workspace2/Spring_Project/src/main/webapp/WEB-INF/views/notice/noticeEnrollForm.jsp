<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#enroll-form input, #enroll-form textarea {
		width : 100%;
		box-sizing : border-box;
		margin : 5px;
		padding : 5px;
	}

	#enroll-form textarea {
		resize : none;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
	<div class="outer">
		
		<br>
		<h2 align="center">공지사항 작성</h2>
		<br>

		<!-- 
			공지사항 작성 요청 시
			http://localhost:8006/spring/notice/insert 로 요청하고 싶음
		-->
		<form id="enroll-form" 
			  action="/spring/notice/insert" method="post">

			<!--
				* 공지사항 작성 기능 구현 시 필요한 데이터들

				- 제목, 내용을 직접 입력받아야함
				  (+ 직접 입력받지 않고, 눈에 노출되지도 않지만, 작성자의 회원번호 또한 넘겨야함)
			-->
			
			<!-- 현재 로그인한 회원 == 작성자 (회원번호를 hidden 으로 넘기기) -->
			<input type="hidden" name="noticeWriter" 
								 value="${ sessionScope.loginUser.userNo }">
				
			<table align="center" class="table" style="width : 90%;">

				<tr>
					<th width="60">제목</th>
					<td width="500">
						<input type="text" name="noticeTitle" required>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="noticeContent" required rows="10"></textarea>
					</td>
				</tr>

			</table>

			<br><br>

			<div align="center">
				<button type="submit" class="btn btn-primary btn-sm">등록하기</button>
				<button type="reset" class="btn btn-secondary btn-sm">초기화</button>
				<button type="button" class="btn btn-secondary btn-sm"
						onclick="history.back();">뒤로가기</button>
						<!-- history.back() : 이전 페이지로 돌아가게 해주는 메소드 -->
			</div>

		</form>

		<br><br>

	</div>
	
	<br><br>

</body>
</html>