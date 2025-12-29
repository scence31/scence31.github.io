<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#enroll-form input, #enroll-form textarea {
		width : 100%;
		box-sizing : border-box;
		padding : 5px;
		margin : 5px;
	}

	#enroll-form textarea {
		resize : none;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
		
		<br>
		<h2 align="center">사진게시글 작성</h2>
		<br>

		<!-- 
			사진게시글 작성 요청 시
			http://localhost:8006/spring/thumbnail/insert 로 요청
		-->
		<form id="enroll-form" action="/spring/thumbnail/insert" 
							   method="post"
							   enctype="multipart/form-data">

			<!-- 현재 로그인한 사용자 (작성자) 의 회원번호 -->
			<input type="hidden" name="boardWriter"
								 value="${ sessionScope.loginUser.userNo }">

			<table align="center" class="table" style="width : 90%;">

				<tr>
					<th width="100">제목</th>
					<td colspan="3" width="500">
						<input type="text" name="boardTitle" required>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="boardContent" required
								  rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<th>대표이미지</th>
					<td colspan="3" align="center">
						<!--
						<input type="file">
						-->
						<!--
							대표이미지를 입력받으려면 이미지 파일을 입력받아야 하니깐
							input type="file" 을 쓰는게 맞음!!
							우리는 사진 이미지 파일을 입력 받되,
							이미지 미리보기 형식으로 입력을 받아보고 싶음!!
						-->
						<img id="titleImg" width="250" height="180">
					</td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<td>
						<img id="contentImg1" width="200" height="150">
					</td>
					<td>
						<img id="contentImg2" width="200" height="150">
					</td>
					<td>
						<img id="contentImg3" width="200" height="150">
					</td>
				</tr>

			</table>

			<div id="file-area">
				<!-- 
					위의 img 들은 미리보기용 요소들!!
					실제 파일 입력은 input type="file" 로 할 것!!
					(게시글 한개당 첨부파일 최대 4개 - 썸네일 1, 일반 3)
				-->
				
				<!-- 대표이미지 (썸네일) 입력용 - 필수 입력!! -->
				<input type="file" id="file1" name="files" onchange="loadImg(this, 1);" required>

				<!-- 상세이미지 입력용 -->
				<input type="file" id="file2" name="files" onchange="loadImg(this, 2);">
				<input type="file" id="file3" name="files" onchange="loadImg(this, 3);">
				<input type="file" id="file4" name="files" onchange="loadImg(this, 4);">

				<!-- 
					일부러 모든 요소의 name 속성값을 통일시켜줬음!! (files 로 통일)
					모든 요소에 change 이벤트를 걸어 볼 것!! 
					(이벤트 속성명 : onchange, 이벤트명 : change)
					
					- change 이벤트 : input 요소의 입력 내용물이 변경될 때 발생하는 이벤트
				-->
				
			</div>

			<script>
				$(function() {

					// 우선 input type="file" 들을 숨김처리 (자리도 안차지하게)
					// > display : none;
					$("#file-area").hide();

					// 각각의 미리보기 영역을 나타내는 img 요소에 클릭 이벤트 걸기
					$("#titleImg").click(function() {

						// 아이디가 file1 인 요소를 클릭해줄 것!!
						$("#file1").click();
						// > click 메소드 호출 시 매개변수 (이벤트 핸들러) 없이 호출하면
						//   그대로 클릭되는 효과를 준다!!
					});

					$("#contentImg1").click(function() {

						$("#file2").click();
					});

					$("#contentImg2").click(function() {

						$("#file3").click();
					});

					$("#contentImg3").click(function() {

						$("#file4").click();
					});
				});

				function loadImg(inputFile, num) {

					// console.log(inputFile);
					// > 현재 내용물 변화가 생긴 input type="file" 요소 객체
					//   (this 를 전달했으므로)

					// console.log(num);
					// > 몇번째 input type="file" 인지 구분하기 위한 숫자값

					// * input type="file" 요소 핸들링 방법
					// > input type="file" 요소 "객체" 의 files 라는 속성에 접근해보자!!
					// console.log(inputFile.files);
					// files 라는 속성은 업로드된 파일의 정보들을 배열 형식으로 여러개 묶어서 반환
					// > 만약 input type="file" 로 하나의 파일 정보만 입력 했다면 1개의 정보만 담겨있음
					//   (0번 방에만 파일 정보가 담겨있음, 이 때 length == 1)
					// > 만약 input type="file" multiple (한번에 여러 파일을 입력받게끔 해주는 속성)
					//   로 한번에 여러개의 파일 정보를 입력하면 여러개의 정보가 담겨있을 것임!!
					//   (0번 방에서부터 차곡차곡 파일 정보가 담겨있음, 이 때 length == 파일갯수)

					// console.log(inputFile.files.length);
					// > length 가 1 이면 현재 선택된 파일이 있는거고
					//   length 가 0 이면 현재 선택된 파일이 없는거임!! (즉, 파일의 존재 유무를 알 수 있음)

					if(inputFile.files.length == 1) {
						// 현재 선택된 파일이 존재할 경우
						// > 선택된 파일을 읽어들여서 그 영역에 맞는 img 요소에 미리보기 효과 부여!!

						// 선택된 파일을 읽어들이기
						// 1. 파일을 읽어들일 FileReader 자바스크립트 객체 생성 (생성자 함수)
						let reader = new FileReader();

						// 2. FileReader 객체에서 제공하는 파일을 읽어들이는 메소드 속성을 호출
						// > readAsDataURL 메소드 속성 호출
						// > 단, 어느 파일을 읽어들일 건지 반드시 매개변수로 제시해야함!!
						//   (input type="file" 로 multiple 속성을 제시하면 한번에 여러 파일이 선택되기 때문)
						// > 우리는 한번에 한개의 파일만 읽어들이기로 했기 때문에 [0] 인덱스로 매개변수를 제시함
						reader.readAsDataURL(inputFile.files[0]);
						// > 메소드가 호출되고 해당 파일을 읽어들이는 순간
						//   내부적으로 해당 그 파일만의 고유한 임의의 url 주소같은걸 만들어준다!!
						// > 그 만들어진 url 주소를 가지고 자리에 맞는 img 요소의 src 속성에 대입해줄 것!!

						// 3. 파일 읽기가 완료되었을 때 실행할 함수를 정의
						// > onload 이벤트 속성이 reader 객체에 부여된 것!!
						//   reader 객체에 의해 "파일 로딩이 다 성공한 뒤 단 한번" 실행할 함수 정의
						reader.onload = function(e) {
							// > 이 onload 속성에 대입된 이름이 없는 function 은 "이벤트 핸들러" 임
							//   익명함수 형식의 이벤트 핸들러 작성 시 매개변수를 하나 받을 수 있다!!
							// > 관례 상 매개변수명을 e 로 작성해주고,
							//   이 e 에는 매번 이벤트가 발생해서 이 함수가 실행될 때 마다
							//   해당 방금 일어난 이벤트에 대한 정보가 객체 형식으로 넘어오게됨!!
							//   e : 이벤트 정보 (객체 형식)
							//	 e.target : 방금 이벤트를 당한 요소 객체
							//	 e.target.result : 타겟 (FileReader 객체) 의 result 속성
							//   > 파일이 읽어들여질 때 만들어지는 그 파일의 고유한 임의의 url 주소가 담김!!

							// 자리에 맞는 img 요소의 src 속성에 대입해줄 것!!
							switch(num) {
							case 1 : 
								$("#titleImg").attr("src", e.target.result);
								break;
							case 2 : 
								$("#contentImg1").attr("src", e.target.result);
								break;
							case 3 : 
								$("#contentImg2").attr("src", e.target.result);
								break;
							case 4 : 
								$("#contentImg3").attr("src", e.target.result);
							}

						};

					} else {
						// 현재 선택된 파일이 존재하지 않을 경우
						// > 그 영역에 맞는 img 요소에 미리보기 효과를 빼기!!

						switch(num) {
						case 1 : 
							$("#titleImg").attr("src", null);
							break;
						case 2 : 
							$("#contentImg1").attr("src", null);
							break;
						case 3 :
							$("#contentImg2").attr("src", null);
							break;
						case 4 : 
							$("#contentImg3").attr("src", null);
						}

					}

				}
			</script>

			<br><br>

			<div align="center">
				<button type="submit" 
						class="btn btn-primary btn-sm">등록하기</button>
			</div>

		</form>
		
		<br><br>

	</div>

	<br><br>

</body>
</html>