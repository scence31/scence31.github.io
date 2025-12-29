<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#enroll-form input, #enroll-form textarea {
		width : 100%;
		padding : 5px;
		margin : 5px;
		box-sizing : border-box;
	}

	#enroll-form select {
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
		<h2 align="center">게시글 작성</h2>
		<br>

		<!-- 
			게시글 작성 요청 시
			http://localhost:8006/spring/board/insert 로 요청
			
			요청 시 전달값 중 첨부파일이 있을 경우에는
			form 태그 내에 enctype="multipart/form-data" 를 반드시 적는다.
		-->
		<form id="enroll-form" action="/spring/board/insert" 
							   method="post"
							   enctype="multipart/form-data">

			<!--
				* 일반게시글 작성 시

				- 카테고리 번호, 제목, 내용 은 입력받아야함!!
				  + 첨부파일 한개의 정보 또한 입력받아야함!!
				  + 작성자의 회원번호 (hidden 으로 몰래 넘기기)
			-->
			<input type="hidden" name="boardWriter" 
				   value="${ sessionScope.loginUser.userNo }">
			
			<table class="table" align="center" style="width : 90%;">
				
				<tr>
					<th width="100">카테고리</th>
					<td width="500">
						<select name="category">
						<!--  
							<option value="10">공통</option>
							<option value="20">운동</option>
							<option value="30">등산</option>
							<option value="40">게임</option>
							<option value="50">낚시</option>
							<option value="60">요리</option>
							<option value="70">기타</option>
						-->
						
						<!-- 
							- 게시글 작성 시 카테고리 정보들을 
							  DB 카테고리 정보와 똑같이 하드코딩 해도 상관 없지만
							  카테고리가 빈번하게 추가, 수정, 삭제가 될 것 같다?
							- 그래서 우리는 DB 카테고리 정보를 조회해서
							  응답데이터로 반복적으로 option 태그로 출력할것!!
						-->
						
							<c:forEach var="c" items="${ requestScope.list }">
							
								<option value="${ c.categoryNo }">${ c.categoryName }</option>
							
							</c:forEach>
						
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="boardTitle" required>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="boardContent" 
								  rows="10" required></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="upfile">
					</td>
				</tr>

			</table>

			<br><br>

			<div align="center">
				<button type="submit" class="btn btn-primary btn-sm">작성하기</button>
				<button type="reset" class="btn btn-secondary btn-sm">초기화</button>
			</div>

		</form>

		<br><br>

	</div>

	<br><br>

</body>
</html>