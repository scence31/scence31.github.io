<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#update-form input, #update-form textarea {
		width : 100%;
		box-sizing : border-box;
		margin : 5px;
		padding : 5px;
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
        <h2 align="center">공지사항 수정</h2>
        <br>

		<!-- 
			공지사항 수정 요청 시
			http://localhost:8006/spring/notice/update 로 요청을 하고 싶음
		-->
        <form id="update-form" action="/spring/notice/update" method="post">

			<!-- 이 공지사항 게시글 번호도 눈에 안보이게 같이 넘겨야 함!! -->
			<input type="hidden" name="noticeNo" value="${ requestScope.n.noticeNo }">

            <table align="center" class="table" style="width : 90%;">

				<tr>
					<th width="60">제목</th>
					<td width="500">
						<input type="text" name="noticeTitle" 
							   value="${ requestScope.n.noticeTitle }" required>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="noticeContent" required rows="10">${ requestScope.n.noticeContent }</textarea>
					</td>
				</tr>

			</table>

            <br><br>

            <div align="center">
                <button type="submit" class="btn btn-primary btn-sm">수정하기</button>
                <button type="button" class="btn btn-secondary btn-sm"
                        onclick="history.back();">뒤로가기</button>
            </div>

        </form>

        <br><br>

    </div>
	
    <br><br>

</body>
</html>