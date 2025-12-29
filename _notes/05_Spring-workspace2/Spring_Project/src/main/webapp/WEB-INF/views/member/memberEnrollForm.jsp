<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* menubar.jsp 로 공통 스타일 이관 */
	/*
	.outer {
		width : 1000px;
		border : 1px dotted gray;
		margin : auto;
		margin-top : 50px;
	}
	*/

	#enroll-form table {
		margin : auto;
	}

	#enroll-form input {
		margin : 5px;
		padding : 5px;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	<!-- ../ : 현재 이 폴더로부터 한겹 빠져나가겠다. -->

	<div class="outer">

		<br>
		<h2 align="center">회원가입</h2>
		<br>

		<!-- 
			회원가입 요청 시 
			http://localhost:8006/spring/member/insert 로 요청하고 싶음
		-->
		<form id="enroll-form" action="/spring/member/insert" method="post">

			<!--
				* 회원가입 시 입력받아야 하는 것들
				- 아이디, 비번, 이름, 전화번호, 이메일, 주소
				- 이 중, 아이디, 비번, 이름은 "필수 입력 사항"
			-->

			<table>
				<tr>
					<th>* 아이디</th>
					<td>
						<input type="text" name="userId" maxlength="12" required>
					</td>
					<td>
						<button type="button" class="btn btn-secondary btn-sm" onclick="idCheck();">중복확인</button>
						<!-- 중복확인은 나중에 AJAX 배우고 나서 구현할 것!! -->
					</td>
				</tr>
				<tr>
					<th>* 비밀번호</th>
					<td>
						<input type="password" name="userPwd" maxlength="15" required>
					</td>
					<td></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td>
						<input type="password" maxlength="15" required>
						<!-- 단순히 일치 여부만 보는 값이기 때문에 서버로 넘기지 않음!! -->
					</td>
					<td></td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td>
						<input type="text" name="userName" maxlength="6" required>
					</td>
					<td></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" name="phone" placeholder="- 포함해서 입력">
					</td>
					<td></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" name="email">
					</td>
					<td></td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="address">
					</td>
					<td></td>
				</tr>
			</table>

			<br><br>

			<div align="center">
				<button type="submit" class="btn btn-primary btn-sm" disabled>회원가입</button>
				<!-- 아이디 중복확인 거치기 전까지 버튼 비활성화 -->
				
				<button type="reset" class="btn btn-secondary btn-sm">초기화</button>
			</div>

		</form>

		<br><br>

	</div>
	
	<br><br>
	
	<script>
		function idCheck() {
			
			// 중복확인 클릭할 때마다 입력받은 아이디값 뽑기
			// 아이다값에 Ajax 형식 적용
			
			// 입력받는 input 요소 객체 자체를 변수에 담기
			let $userId = $("#enroll-form input[name=userId]");
			// 변수 담을 때 중복 주의, 제이쿼리 명명규칙 $ 주의
			
			// 중복체크시 8006/spring/member/idCheck?checkId=xxx 주소로 비동기식 요청
			$.ajax({
				url : "/spring/member/idCheck",
				type : "get",
				data : {checkId : $userId.val(result)},
				success : function(result) {
					
					if(result == "NNNNN") {
						
						alertify.alert("이미 사용중, 탈퇴한 회원 아이디임")
						
						// 재입력 유도
						$userId.focus();
						
					} else {
						
						if(confirm("사용 가능, 사용할거니?")) {
							
							// 아이다값 확정(수정못하게)
							$userId.attr("readonly", true);
							
							// 회원가입버튼 활성화
							$("#enroll-form button[type=submit]").removeAttr("disabled");
							
						} else {
							
							// 재입력 유도
							$userId.focus();
							
							
						}
					}
				},
				error : function() {}
			})
		}
	
	</script>

</body>
</html>
























