<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.list-area {
		width : 780px;
		margin : auto;
	}

	.thumbnail {
		border : 1px solid lightgray;
		width : 220px;
		display : inline-block;
		margin : 16px;
		padding-top : 10px;
	}

	.thumbnail:hover {
		cursor : pointer;
		opacity : 0.75;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
		
		<br>
		<h2 align="center">사진게시판</h2>
		<br>
		
		<!-- 로그인한 회원만 보여지는 글작성 버튼 -->
		<c:if test="${ not empty sessionScope.loginUser }">
			<div align="right" style="width : 855px;">
				<a class="btn btn-secondary btn-sm"
				   href="/spring/thumbnail/enrollForm">
					글작성
				</a>
				
				<br><br>
			</div>
		</c:if>

		<div class="list-area">

			<!--  
				게시글 1개를 "컴포넌트" 단위로 만들어서
				게시글의 갯수 만큼 반복적으로 컴포넌트 찍어내기!!
			-->
			<!--  
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="160px">
				<p>
					No.112 제목입니다. <br>
					조회수 : 12
				</p>
			</div>
			-->
			
			<c:choose>
				<c:when test="${ not empty requestScope.list }">
					<!-- 조회된 결과가 있을 경우 -->
					
					<c:forEach var="b" items="${ requestScope.list }">
						<div class="thumbnail" align="center">
							<!-- 
								글번호를 상세조회 요청 시 뽑아오기 쉽도록
								일부러 .thumbnail 요소의 첫번째 자식으로 hidden 으로 
								값을 숨겨서 넣어준것!!
							-->
							<input type="hidden" value="${ b.boardNo }">
							<img src="/spring/${ b.titleImg }" width="200px" height="160px">
							<p>
								<c:choose>
									<c:when test="${ b.boardTitle.length() >= 9 }">
										No.${ b.boardNo } ${ b.boardTitle.substring(0, 8) }... <br>
										조회수 : ${ b.count }
									</c:when>
									<c:otherwise>
										No.${ b.boardNo } ${ b.boardTitle } <br>
										조회수 : ${ b.count }
									</c:otherwise>
								</c:choose>
							</p>
						</div>
					</c:forEach>
					
				</c:when>
				<c:otherwise>
					<!-- 조회된 결과가 없을 경우 -->
					등록된 게시글이 없습니다.
				</c:otherwise>
			</c:choose>

		</div>

		<br><br>

	</div>
	
	<br><br>
	
	<script>
		$(function() {
			
			$(".thumbnail").click(function() {
				
				let bno = $(this).children().eq(0).val();
				// console.log(bno);
				
				location.href = "/spring/thumbnail/detail/" + bno;
			});
			
		});
	</script>

</body>
</html>



