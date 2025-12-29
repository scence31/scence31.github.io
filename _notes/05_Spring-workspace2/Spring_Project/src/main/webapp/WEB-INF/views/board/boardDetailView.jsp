<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
	
		<br>
		<h2 align="center">게시글 상세 조회</h2>
		<br>

		<table id="detail-area" align="center" class="table" style="width : 90%;">
			<tr>
				<th width="70">카테고리</th>
				<td width="70">${ requestScope.b.category }</td>
				<th width="70">제목</th>
				<td width="350">${ requestScope.b.boardTitle }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ requestScope.b.boardWriter }</td>
				<th>작성일</th>
				<td>${ requestScope.b.createDate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height : 250px;">
						${ requestScope.b.boardContent }
					</p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					
					<c:choose>
						<c:when test="${ empty requestScope.at }">
							<!-- 첨부파일이 없을 경우 -->
							첨부파일이 없습니다.
						</c:when>
						<c:otherwise>
							<!-- 첨부파일이 있을 경우 -->
							<!-- 
								브라우저에서
								http://localhost:8006/spring/resources/board_upfiles/2025102016192662029.jpg
								
								url 주소 상의 context path 는 webapp 폴더를 나타냄!!
								/spring == webapp 폴더
								url 주소 상으로 WEB-INF 폴더 내부만 아니면 이렇게 직접 파일을 접근해볼 수 있다!!
							-->
							<a download="${ requestScope.at.originName }" href="/spring/${ requestScope.at.filePath }${ requestScope.at.changeName }">
								${ requestScope.at.originName }
							</a>
						</c:otherwise>
					</c:choose>
				
				</td>
			</tr>
		</table>

		<br><br>
		
		<div align="center">
			<a href="/spring/board/list" class="btn btn-secondary btn-sm">목록가기</a>
			
			<!-- 로그인한 사용자가 현재 게시글 작성자일 경우에만 보여져야함 -->
			<c:if test="${ (not empty sessionScope.loginUser) and (sessionScope.loginUser.userId eq requestScope.b.boardWriter) }">
				<a class="btn btn-warning btn-sm" onclick="postFormSubmit(1);">수정하기</a>
				<a class="btn btn-danger btn-sm" onclick="postFormSubmit(2);">삭제하기</a>
			
				<!-- 수정하기, 삭제하기 버튼 클릭 시 POST 방식으로 요청 -->
				<form id="postForm" action="" method="post">
					<input type="hidden" name="bno" value="${ requestScope.b.boardNo }">
				</form>
				
				<script>
					function postFormSubmit(num) {
						
						if(num == 1) { // 수정하기 버튼 클릭 시
							
							$("#postForm").attr("action", "/spring/board/updateForm").submit();
							
						} else { // 삭제하기 버튼 클릭 시
							
							$("#postForm").attr("action", "/spring/board/delete").submit();
						}
					}
				</script>
			</c:if>
		</div>
		
		<br><br>
		
		<!-- 댓글 관련 화면 구현 - AJAX 배우고 나서 기능 구현할것!! -->
		<div id="reply-area">
		
			<table align="center" class="table" style="width : 90%;">
				<thead>
				
					<c:choose>
						<c:when test="${ not empty sessionScope.loginUser }">
							<!-- 로그인된경우 -->
							<tr>
								<th>댓글작성</th>
								<td>
									<textarea id="replyContent"
											  cols="80" rows="3"
											  style="resize : none;"></textarea>
								</td>
								<td>
									<button class="btn btn-secondary" onclick="insertReply();">
										댓글등록
									</button>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- 비로그인 -->
							<tr>
								<th>댓글작성</th>
								<td>
									<textarea readonly
											  cols="80" rows="3"
											  style="resize : none;">로그인 후 이용가능한 서비스입니다.</textarea>
								</td>
								<td>
									<button class="btn btn-secondary" disable>댓글등록</button>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>	
				</thead>
				<tbody>
				</tbody>
			</table>
		
		</div>
		
		<br><br>

	</div>

	<br><br>
	
	<script>
	
		// 댓글목록은 상세조회 페이지 띄워질 때 같이 조회되어야 함
		// 상세조회페이지 다 로딩되면 몰래 ajax로 넣기
		// 메인페이지의 TOP-N 분석에 많이 쓰임
		
		$(function() {
			
			// 이 페이지가 로딩된 후 단 한 번 ajax 요청을 보낼 것(댓글목록을 조회해달라는)
			selectReplyList();
			// 페이지 최초 진입시 한 번 조회해서 뿌려주기
			
			// 댓글이 실시간으로 업데이트되는 효과를 주고싶다면
			// 아래 구문으로 주기적으로 실행
			setInterval(selectReplyList, 1000);
			
		});
		
		// 댓글목록조회용 선언적 함수를 하나 만들기 => 코드 재활용성 높임, 새로고침효과, 실시간효과
		function selectReplyList() {
			
			$.ajax({
				url : "/spring/board/rlist",
				type : "get",
				data : { bno : ${requestScope.b.boardNo}},
				success : function(result) {
					
					// console.log(result);
					
					let resultStr = "";
					
					for(let i in result) {
						
						resultStr += "<tr>"
										+ "<td>" + result[i].replyWriter + "</td>"
										+ "<td>" + result[i].replyContent + "</td>"
										+ "<td>" + result[i].createDate + "</td>"
									+ "</tr>";
					}
					
					$("#reply-area tbody").html(resultStr);
					
				},
				error : function() {
					
					console.log("댓글목록조회 ajax 통신실패");
				}
			})
		}
		
		// 댓글등록용 함수
		function insertReply() {
			
			// 사용자가 입력한 댓글내용을 변수에 담기
			let replyContent = $("#replyContent").val();
			
			$.ajax({
				url : "/spring/board/rinsert",
				type : "post",
				data : {
					replyContent : replyContent,
					refBno : ${requestScope.b.boardNo}
				},
				success : function(result) {
					
					if(result == "success") {

						selectReplyList();
						
						// 댓글작성창 초기화
						$("#replyContent").val("");
						
					} else {
						
						alertify.alert("작성 실패");
						
						// 댓글작성창 초기화
						$("#replyContent").val("");
					}
					
				},
				error : function() {
					
					console.log("댓글작성용 ajax 통신실패");
				}
				
			});
		}
	
	</script>

</body>
</html>
















