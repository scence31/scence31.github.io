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
		<h2 align="center">사진게시글 상세 조회</h2>
		<br>

		<table class="detail-area table" align="center" style="width : 90%;">
			<tr>
				<th width="100">제목</th>
				<td colspan="3" width="500">
					${ requestScope.b.boardTitle }
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ requestScope.b.boardWriter }</td>
				<th width="100">작성일</th>
				<td>${ requestScope.b.createDate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height : 100px;">
						${ requestScope.b.boardContent }
					</p>
				</td>
			</tr>
			<tr>
				<th>대표이미지</th>
				<td colspan="3" align="center">
					<div>
						<!-- EL 구문에서 ArrayList 키값 뒤에 [인덱스] 를 쓰면 알아서 그 인덱스번째 데이터를 뽑아줌 -->
						<img src="/spring/${ requestScope.list[0].filePath }${ requestScope.list[0].changeName }" width="500">
					</div>
				</td>
			</tr>
			<tr>
				<th>상세이미지</th>
				<td colspan="3" align="center">
					
					<c:choose>
						<c:when test="${ requestScope.list.size() eq 1 }">
							<!-- 대표이미지만 있을 경우 (상세이미지가 없을 경우) -->
							상세이미지가 없습니다.
						</c:when>
						<c:otherwise>
							<c:forEach var="i" begin="1" end="${ requestScope.list.size() - 1 }" step="1">
								<img src="/spring/${ requestScope.list[i].filePath }${ requestScope.list[i].changeName }" width="200" height="160">
							</c:forEach>
						</c:otherwise>
					</c:choose>
				
				</td>
			</tr>
		</table>

		<br><br>
	
	</div>
	
	<br><br>

</body>
</html>






