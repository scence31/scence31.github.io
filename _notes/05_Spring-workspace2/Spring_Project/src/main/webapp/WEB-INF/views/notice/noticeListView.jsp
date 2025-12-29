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
		text-align : center;
		/* width : 90%; */
		/*
			CSS 우선순위 때문에 적용되지 않음!!
			(우선순위를 높여줘야 스타일이 적용됨)
		*/
		margin : auto;
	}

	.list-area>tbody>tr:hover {
		cursor : pointer;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
		
		<br>
		<h2 align="center">공지사항</h2>
		<br>
		
		<!-- 관리자만 보여지는 글작성 버튼 배치 -->
		<c:if test="${ (not empty sessionScope.loginUser) and (sessionScope.loginUser.userId eq 'admin') }">
			<div align="right" style="width : 950px;">
				<a href="/spring/notice/enrollForm" class="btn btn-secondary btn-sm">글작성</a>
				
				<br><br>
			</div>
		</c:if>

		<table class="table table-hover list-area"
			   style="width : 90%;">
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			
			<!-- 반복적으로 조회된 list 의 Notice 객체들을 tr 로 찍어내기 -->	
			<c:choose>
				<c:when test="${ empty requestScope.list }">
					<!-- 조회된 데이터가 없을 경우 -->
					<tr>
						<th colspan="5">
							조회된 공지사항이 없습니다.
						</th>
					</tr>
				</c:when>
				<c:otherwise>
					<!-- 조회된 데이터가 있을 경우 -->
					<c:forEach var="n" items="${ requestScope.list }">
						<tr>
							<td>${ n.noticeNo }</td>
							<td>${ n.noticeTitle }</td>
							<td>${ n.noticeWriter }</td>
							<td>${ n.count }</td>
							<td>${ n.createDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
				
			<!--  
				<tr>
					<td>1</td>
					<td>공지사항 제목입니다.</td>
					<td>admin</td>
					<td>24</td>
					<td>2025-10-15</td>
				</tr>
				<tr>
					<td>1</td>
					<td>공지사항 제목입니다.</td>
					<td>admin</td>
					<td>24</td>
					<td>2025-10-15</td>
				</tr>
				<tr>
					<td>1</td>
					<td>공지사항 제목입니다.</td>
					<td>admin</td>
					<td>24</td>
					<td>2025-10-15</td>
				</tr>
			-->
			</tbody>
		</table>

		<br><br>

	</div>

	<br><br>
	
	<script>
		$(function() {
			
			// 게시글 1개를 나타내는 tr 요소에 클릭 이벤트를 부여
			// > 해당 게시글 번호에 해당하는 게시글을 상세조회 요청을 보내기
			$(".list-area>tbody>tr").click(function() {
				
				// console.log("클릭됨");
				
				// 상세보기 요청 시 클릭한 게시글의 글번호를 같이 넘겨버릴 것!!
				// > 공지사항의 글번호가 primary key 제약조건이기 때문!!
				
				// 방금 클릭당한 게시글의 글번호를 먼저 변수에 담기
				let nno = $(this).children().eq(0).text();
				// console.log(nno);
				// > 현재 클릭을 당한 tr 의 자손들 중에서 첫번째 자손의 innerText 값이 글번호임!!
				
				// 위의 nno 를 상세조회 요청 시 전달값으로 같이 보낼 예정
				// location.href = "/spring/notice/detail?nno=" + nno;
				// > href 속성으로 url 요청을 하면 GET 방식으로 요청이 들어가기 때문에
				//   요청 시 전달값이 있다면 직접 문자열로 쿼리스트링을 만들어 주면 된다!!
				// > 요청 시 전달값을 대놓고 쿼리스트링으로 연결해서 보내는 것 : 쿼리스트링 방식
				
				// 위와 같이 직접 쿼리스트링을 통해 요청 시 전달값을 넘길 수 있지만,
				// 아래와 같은 방법도 가능함!!
				location.href = "/spring/notice/detail/" + nno;
				// > 마치 글번호를 url 요청 주소인 것 마냥 / 사이에 은근히 집어넣었음!!
				// > 요청 시 전달값을 url 요청 주소인 것 마냥 같이 보내는 것 : Path Variable 방식
				//   예) 티스토리 블로그 게시글 상세보기 url 주소
				
			});
			
		});
	</script>

</body>
</html>





