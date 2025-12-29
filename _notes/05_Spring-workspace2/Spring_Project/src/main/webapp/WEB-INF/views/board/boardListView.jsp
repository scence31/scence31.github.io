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
	}

	.list-area>tbody>tr:hover {
		cursor : pointer;
	}

	#search-area table {
		height : 35px;
	}

	#search-area select, #search-area input, #search-area button {
		height : 100%;
		box-sizing : border-box;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
	
		<br>
		<h2 align="center">일반게시판</h2>
		<br>

		<br><br>

		<!-- 검색창이 들어갈 자리 -->
		<div id="search-area" align="center">
			
			<!-- 
				검색 요청 시
				http://localhost:8006/spring/board/search 로 요청
			-->
			<form action="/spring/board/search" method="get">
			
			<!-- 검색 결과의 첫 화면은 1번 페이지가 되게끔 -->
			<input type="hidden" name="cpage" value="1">

			<table>
				<tr>
					<td>
						<select name="condition">
							<option value="writer">작성자</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
					</td>
					<td>
						<input type="text" name="keyword" value="${ requestScope.keyword }">
						<!-- 
							검색 결과 창에 검색어가 노출되지 않고 있음!!
							이왕이면 검색어가 유지되서 그대로 input 안에 보여졌으면 함!!
						-->
					</td>
					<td>
						<button type="submit" class="btn btn-secondary btn-sm">검색</button>
					</td>
				</tr>
			</table>
				
			</form>
			
			<!-- 검색 조건을 유지시켜볼 것!! - 자바스크립트 (jQuery) 구문 이용 -->
			<c:if test="${ not empty requestScope.condition }">
				<!-- 응답데이터로 condition 이 있을 경우 -->
				<script>
					$(function() {
						$("#search-area option[value=${ requestScope.condition }]").attr("selected", true);
					});
				</script>
			</c:if>

		</div>

		<br><br>
		
		<!-- 로그인한 회원만 보여지는 글작성 버튼을 배치 -->
		<c:if test="${ not empty sessionScope.loginUser }">
		
			<div align="right" style="width : 950px;">
				<a href="/spring/board/enrollForm" class="btn btn-secondary btn-sm">글작성</a>
				
				<br><br>
			</div>
			
		</c:if>

		<table class="table table-hover list-area" align="center" style="width : 90%;">
			<thead>
				<tr>
					<th>글번호</th>
					<th>카테고리</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			
			<c:choose>
				<c:when test="${ empty requestScope.list }">
					<tr>
						<th colspan="6">
							조회된 게시글이 없습니다.
						</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="b" items="${ requestScope.list }">
						<tr>
							<td>${ b.boardNo }</td>
							<td>${ b.category }</td>
							<td>${ b.boardTitle }</td>
							<td>${ b.boardWriter }</td>
							<td>${ b.count }</td>
							<td>${ b.createDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<!--  
				<tr>
					<td>1</td>
					<td>기타</td>
					<td>글 제목이 들어갈 자리</td>
					<td>user01</td>
					<td>23</td>
					<td>2025-10-17</td>
				</tr>
			-->
			</tbody>
		</table>
		
		<script>
			$(function() {
				
				$(".list-area>tbody>tr").click(function() {
					
					// console.log("클릭됨");
					
					let bno = $(this).children().eq(0).text();
					// console.log(bno);
					
					// 일반게시글 상세조회 요청 보내기 (bno 를 가지고)
					// 1) 쿼리스트링 방식으로 보내기 ?bno=x
					// 2) Path Variable 방식으로 보내기 /x
					
					// Path Variable 방식으로 보내보자!!
					location.href = "/spring/board/detail/" + bno;
				});
			});
		</script>

		<br><br>

		<!-- 페이징바가 들어갈 부분 - 목록 조회시의 페이징바, 검색 조회시의 페이징바 다르게 처리 -->
		<div class="paging-area" align="center">
		
			<ul class="pagination justify-content-center">
			
				<c:choose>
					<c:when test="${ requestScope.pi.currentPage eq 1 }">
						<li class="page-item disabled"><a class="page-link">Previous</a></li>
					</c:when>
					<c:otherwise>
						
						<c:choose>
							<c:when test="${ empty requestScope.condition }">
								<!-- condition 이라는 응답데이터가 없을 경우 : 일반 목록 조회 -->
								<li class="page-item"><a class="page-link" href="/spring/board/list?cpage=${ requestScope.pi.currentPage - 1 }">Previous</a></li>
							</c:when>
							<c:otherwise>
								<!-- condition 이라는 응답데이터가 있을 경우 : 검색 조회 -->
								<li class="page-item"><a class="page-link" href="/spring/board/search?cpage=${ requestScope.pi.currentPage - 1 }&condition=${ requestScope.condition }&keyword=${ requestScope.keyword }">Previous</a></li>
							</c:otherwise>
						</c:choose>
					
					</c:otherwise>
				</c:choose>
				
				<!-- for(int p = startPage; p <= endPage; p++) -->
				<c:forEach var="p" begin="${ requestScope.pi.startPage }"
								   end="${ requestScope.pi.endPage }"
								   step="1">
					
					<c:choose>
						<c:when test="${ requestScope.pi.currentPage eq p }">
							<li class="page-item active"><a class="page-link">${ p }</a></li>
						</c:when>
						<c:otherwise>
						
							<c:choose>
								<c:when test="${ empty requestScope.condition }">
									<li class="page-item"><a class="page-link" href="/spring/board/list?cpage=${ p }">${ p }</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/spring/board/search?cpage=${ p }&condition=${ requestScope.condition }&keyword=${ requestScope.keyword }">${ p }</a></li>
								</c:otherwise>
							</c:choose>
						
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
				
				<c:choose>
					<c:when test="${ requestScope.pi.currentPage eq requestScope.pi.maxPage }">
						<li class="page-item disabled"><a class="page-link">Next</a></li>
					</c:when>
					<c:otherwise>
						
						<c:choose>
							<c:when test="${ empty requestScope.condition }">
								<li class="page-item"><a class="page-link" href="/spring/board/list?cpage=${ requestScope.pi.currentPage + 1 }">Next</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="/spring/board/search?cpage=${ requestScope.pi.currentPage + 1 }&condition=${ requestScope.condition }&keyword=${ requestScope.keyword }">Next</a></li>
							</c:otherwise>
						</c:choose>

					</c:otherwise>
				</c:choose>
			
			</ul>

		</div>

		<br><br>

	</div>
	
	<br><br>

</body>
</html>