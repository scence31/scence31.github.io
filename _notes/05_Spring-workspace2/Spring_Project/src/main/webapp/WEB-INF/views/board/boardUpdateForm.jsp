<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#update-form input, #update-form textarea {
		width : 100%;
		box-sizing : border-box;
		padding : 5px;
		margin : 5px;
	}

	#update-form select {
		padding : 5px;
		margin : 5px;
	}

	#update-form textarea {
		resize : none;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
	
		<br>
		<h2 align="center">게시글 수정</h2>
		<br>

		<!-- 
			게시글 수정 요청 시
			http://localhost:8006/spring/board/update 로 요청
			
			또한, 수정 요청 시에도
			첨부파일이 넘어갈 수 있다!! (enctype 속성)
		-->
		<form id="update-form" action="/spring/board/update" 
							   method="post"
							   enctype="multipart/form-data">
							   
			<!-- 글번호도 함께 넘기기 -->
			<input type="hidden" name="boardNo" value="${ requestScope.b.boardNo }">

			<table align="center" class="table" style="width : 90%;">

				<tr>
					<th width="100">카테고리</th>
					<td width="500">
						<select name="category">
							<c:forEach var="c" items="${ requestScope.list }">
								<option value="${ c.categoryNo }">${ c.categoryName }</option>
							</c:forEach>
						</select>
						
						<script>
							// 기존 카테고리명이 선택되서 보이게끔
							$(function() {
								
								$("#update-form option").each(function() {
									
									if($(this).text() == "${ requestScope.b.category }") {
										
										$(this).attr("selected", true);
									}
								});
							});
						</script>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="boardTitle" required
										   value="${ requestScope.b.boardTitle }">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="boardContent" required
								  rows="20">${ requestScope.b.boardContent }</textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:if test="${ not empty requestScope.at }">
							<!-- 첨부파일이 있을 경우 -->
							<a download="${ requestScope.at.originName }" 
							   href="/spring/${ requestScope.at.filePath }${ requestScope.at.changeName }">
								${ requestScope.at.originName }
							</a>
							
							<!-- ATTACHMENT 테이블에 UPDATE 문 실행 시 필요한 파일번호 같이 넘기기 -->
							<input type="hidden" name="originalFileNo" 
												 value="${ requestScope.at.fileNo }">
							<!-- 
								기존 첨부파일이 있는데 수정할 첨부파일도 있으면 
								기존 첨부파일은 서버로부터 지울 것임!! 
								기존 첨부파일의 수정파일명도 같이 넘겨줄것!!
							-->
							<input type="hidden" name="originalFileChangeName" value="${ requestScope.at.changeName }">
						</c:if>
					
						<input type="file" name="reUpfile">
						<!--
							혹시 이 게시글 수정 시 
							첨부파일의 수정도 일어날 수 있기 때문에!!
						-->
					</td>
				</tr>

			</table>

			<br><br>

			<div align="center">
				<button type="submit" class="btn btn-primary btn-sm">수정하기</button>
				<button type="button" class="btn btn-secondary btn-sm"
						onclick="history.back();">뒤로가기</button>
			</div>

			<br><br>

		</form>

	</div>
	
	<br><br>

</body>
</html>