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
		<h2 align="center">공지사항 상세조회</h2>
		<br>

		<table id="detail-area" align="center" class="table" style="width : 90%;">

			<tr>
				<th width="80">제목</th>
				<td width="400" colspan="3">
					${ requestScope.n.noticeTitle }
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ requestScope.n.noticeWriter }</td>
				<th width="80">작성일</th>
				<td>${ requestScope.n.createDate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height : 200px;">
						${ requestScope.n.noticeContent }
					</p>
				</td>
			</tr>

		</table>

		<br><br>

		<div align="center">
			<a href="/spring/notice/list" class="btn btn-secondary btn-sm">목록가기</a>

			<c:if test="${ (not empty sessionScope.loginUser) and (sessionScope.loginUser.userId eq n.noticeWriter) }">
				<!-- 현재 로그인한 사용자가 이 글의 작성자일 경우에만 보여지게끔 -->
				<a class="btn btn-warning btn-sm" onclick="postFormSubmit(1);">
					수정하기
				</a>
				<a class="btn btn-danger btn-sm" onclick="postFormSubmit(2);">
					삭제하기
				</a>
				<!-- 
					수정하기, 삭제하기 요청 시 모두 글번호를 넘겨줘야 한다!!
					어제 배운 대로 get 방식을 통해 쿼리스트링으로 직접 글번호를 넘겨주고 있음!!
					get 방식은 url 주소 자체를 북마크 등으로 복사해 뒀다가
					다른 브라우저 또는 다른 컴퓨터로 그냥 url 요청 시 그대로 요청이 들어가게 됨!!
					그래서 우리는 둘 다 post 방식으로 요청할 수 있게끔 코드를 보완할 것임!!
					(시큐어 코딩의 연장선)
				-->
				
				<form id="postForm" action="" method="post">
					<input type="hidden" name="nno" value="${ requestScope.n.noticeNo }">
				</form>
				
				<script>
					function postFormSubmit(num) {
						
						if(num == 1) {
							// 수정하기 버튼 클릭 시
							// /spring/notice/updateForm 으로 요청 보내기
							// 이 때 이 공지사항의 글번호를 post 로 같이 넘길 것
							
							$("#postForm").attr("action", "/spring/notice/updateForm").submit();
							// > 위의 form 요소에 action 속성을 추가한 후 submit 요청 보내기
							
						} else {
							// 삭제하기 버튼 클릭 시
							// /spring/notice/delete 으로 요청 보내기
							// 이 때 이 공지사항의 글번호를 post 로 같이 넘길 것
							
							$("#postForm").attr("action", "/spring/notice/delete").submit();
							// > 위의 form 요소에 action 속성을 추가한 후 submit 요청 보내기
							
						}
					}
				</script>
				
			</c:if>
		</div>

		<br><br>

	</div>
	
	<br><br>

</body>
</html>






