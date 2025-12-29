<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/*
    .outer {
        width : 1000px;
        border : 1px dotted gray;
        margin : auto;
        margin-top : 50px;
    }
	*/

    #mypage-form table {
        margin : auto;
    }

    #mypage-form input {
        margin : 5px;
        padding : 5px;
    }   
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
    <div class="outer">

        <br>
        <h2 align="center">마이페이지</h2>
        <br>

        <!--
            내 정보 조회 겸 수정도 가능한 페이지 이기 때문에
            form, 입력양식 태그 안쪽에 내 정보를 출력해줄것임!!
        -->
        
        <!-- 
        	회원 정보 수정 요청 시
        	http://localhost:8006/spring/member/update 로 요청을 보내고 싶음
        -->
        <form id="mypage-form" action="/spring/member/update" method="post">

            <!--
                * 마이페이지에서 보여질, 수정될 데이터들
                - 보여져야할 데이터 : 아이디, 이름, 전화번호, 이메일, 주소
                - 수정될 데이터 : 이름, 전화번호, 이메일, 주소
                (아이디는 변경을 허용 X - 식별 용도의 데이터는 "불변성" 을 갖음)
            -->

            <table>
				<tr>
					<th>* 아이디</th>
					<td>
						<input type="text" name="userId" maxlength="12" readonly value="${ sessionScope.loginUser.userId }">
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td>
						<input type="text" name="userName" maxlength="6" required value="${ sessionScope.loginUser.userName }">
					</td>
					<td></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" name="phone" placeholder="- 포함해서 입력" value="${ sessionScope.loginUser.phone }">
					</td>
					<td></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" name="email" value="${ sessionScope.loginUser.email }">
					</td>
					<td></td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="address" value="${ sessionScope.loginUser.address }">
					</td>
					<td></td>
				</tr>
			</table>

            <br><br>

            <div align="center">
                <button type="submit" class="btn btn-primary btn-sm">정보변경</button>
                <button type="button" class="btn btn-warning btn-sm" 
                		data-toggle="modal" data-target="#updatePwdForm">비밀번호변경</button>
                <button type="button" class="btn btn-danger btn-sm"
                		data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
            </div>

        </form>

        <br><br>

    </div>
	
    <br><br>
    
    <!-- 
    	비밀번호변경, 회원탈퇴 버튼 클릭 시 페이지 이동으로 기능을 구현할 수도 있겠지만, 
    	복습할 겸 모달창을 활용해볼 예정!!	
    -->
    
    <!-- 비밀번호변경용 모달창 -->
	<div class="modal" id="updatePwdForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호 변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	      
	      	<!-- 
	      		비밀번호 변경 요청 시
	      		http://localhost:8006/spring/member/updatePwd 으로 요청하고 싶음
	      	-->
	        <form action="/spring/member/updatePwd" method="post">
	        
	        	<!-- 
	        		* 비밀번호 변경 시 입력받아야 하는 것들
	        		- 현재 비밀번호, 변경할 비밀번호, 변경할 비밀번호 재입력
	        		  (+ 누구의 비번을 변경할건지 아이디도 필요함!!)
	        		- 변경할 회원의 아이디도 필요하긴 한데, 굳이 입력받지는 않음!!
	        		  눈에 그 아이디값을 보여주지도 않음!!
	        	-->
	        	
	        	<!-- input type="hidden" 으로 아이디값만 몰래 같이 넘길 것 -->
	        	<input type="hidden" name="userId" value="${ sessionScope.loginUser.userId }">
	        	
	        	<table>
	        		<tr>
	        			<th>현재 비밀번호</th>
	        			<td>
	        				<input type="password" name="userPwd" required>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>변경할 비밀번호</th>
	        			<td>
	        				<input type="password" name="updatePwd" required>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>변경할 비밀번호 재입력</th>
	        			<td>
	        				<input type="password" name="checkPwd" required>
	        			</td>
	        		</tr>
	        	</table>
	        	
	        	<br>
	        	
	        	<button type="submit"
	        			class="btn btn-secondary btn-sm"
	        			onclick="return validatePwd();">비밀번호 변경</button>
	        	
	        	<br>
	        	
	        </form>
	        
	        <script>
	        	// 비밀번호 유효성 검사용 함수
	        	function validatePwd() {
	        		
	        		// 변경할 비밀번호와 변경할 비밀번호 재입력이 일치할 경우에만 submit 되도록
	        		
	        		// 입력받은 변경할 비밀번호, 재입력을 변수로 갖고오기
	        		let updatePwd = $("input[name=updatePwd]").val();
	        		let checkPwd = $("input[name=checkPwd]").val();
	        		
	        		if(updatePwd != checkPwd) {
	        			
	        			alert("비밀번호가 일치하지 않습니다.");
	        			
	        			return false;
	        		}
	        		
	        		return true;	
	        	}
	        </script>
	        
	      </div>
	
	    </div>
	  </div>
	</div>

	<!-- 회원탈퇴용 모달창 -->
	<div class="modal" id="deleteForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">회원 탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	      
	      	<b>
	      		탈퇴 후 복구가 불가능합니다. <br>
	      		정말로 탈퇴하시겠습니까? <br><br>
	      	</b>
	      	
	      	<!-- 
	      		회원 탈퇴 요청 시
	      		http://localhost:8006/spring/member/delete 으로 요청하고 싶음
	      	-->
	      	<form action="/spring/member/delete" method="post">
	      		
	      		<!-- 탈퇴 시 다시 한번 확인을 위해 기존 비밀번호를 입력받아서 넘기기 -->
	      		<table>
	      			<tr>
	      				<th>비밀번호</th>
	      				<td>
	      					<input type="password" name="userPwd" required>
	      				</td>
	      			</tr>
	      		</table>
	      		
	      		<br>
	      		
	      		<button type="submit" class="btn btn-danger btn-sm">탈퇴하기</button>
	      		
	      		<br>
	      		
	      	</form>
	      
	      </div>
	
	    </div>
	  </div>
	</div>
	
</body>
</html>





