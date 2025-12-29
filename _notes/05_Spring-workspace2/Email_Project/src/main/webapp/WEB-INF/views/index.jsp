<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 온라인 방식 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<h1>이메일 인증기능 구현</h1>
	
	<!-- 이 페이지를 회원가입페이지라고 가정하고 들어가기 -->
	<br>
	
	이메일: <input type="email" id="email">
	<button type="button" id="cert" onclick="cert();">인증메일 보내기</button>
	
	<br><br>
	
	인증번호: <input type="text" id="checkNo" disabled>
	<button type="button" id="validate" disabled onclick="validate();">인증</button>
	
	<script>
		
		// 이메일 주소 넘기면서 인증번호 요청
		function cert() {
			
			$.ajax({
				url : "/email/cert",
				type :"post",
				data : {email : $("#email").val()},
				success : function(result) {
					
					alert(result);
					
					// 인증 관련 요소들 활성화
					$("#checkNo").attr("disabled", false);
					$("#validate").attr("disabled", false);
					
					// 사용자가 악의적인 목적으로 인증번호버튼 클릭 계속 하는 것 막기 => 인증버튼 비활성화
					
					$("#email").attr("readonly", true);
					$("#cert").attr("disabled", true);
					
				},
				error : function() {
					
					console.log("인증번호 발급 ajax 통신실패");
				}
				
			});
		}
		
		// 이메일주소와 인증번호를 둘 다 넘기면서 검증 요청
		function validate() {
			
			$.ajax({
				url : "/email/validate",
				type : "post",
				data : {email : $("#email").val(), checkNo : $("#checkNo").val()},
				success : function(result) {
					
					alert(result);
					
					// 인증번호 검증 후 실패했따면?
					// 위 이메일 입력관련 요소들 활성화
					// 아래 인증번호 입력관련 요소들 비활성화, 모든 입력값 초기화
					if(result == "인증 성공") {
						
						$("#checkNo").attr("readonly", true);
						$("#validate").attr("disabled", true);
						
					} else {
						
						$("#email").attr("readonly", false);
						$("#cert").attr("disabled", false);
						
						$("#checkNo").attr("disabled", true);
						$("#validate").attr("disabled", true);
						
						$("#email").val("");
						$("#checkNo").val("");
						
						
					}
				},
				error : function() {
					
					console.log("인증번호 검증용 ajax 통신실패");
				}
			})
		}
		
	</script>

</body>
</html>












