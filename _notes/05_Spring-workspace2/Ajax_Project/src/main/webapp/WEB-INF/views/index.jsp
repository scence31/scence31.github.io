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

	<h1>AJAX 개요</h1>

	<p>
		- Asynchronous Javascript And Xml 의 약자로
		  서버로부터 요청 후 "응답 데이터" 를 가져와 전체 페이지를 고치지 않고
		  일부만 로드할 수 있게끔 해주는 기법 <br>
		- 우리가 기존에 a 태그 또는 location.href 또는 form 태그를 통해 요청했던 방식은
		  "동기식 요청 방식" 이였음!! <br>
		- 동기식 요청 방식은 "응답 페이지" 가 통째로 돌아오는 방법이라고 보면 됨!! (포워딩 위주) <br>
		- 그 응답 페이지가 클라이언트로 돌아와야지만 그 다음 화면을 볼 수 있음!!
		  (그 응답 페이지가 보여지는 과정에서 브라우저 창이 **깜빡거림** - 화면을 갈아 엎어야 하므로) <br>
		- 웹사이트 기능에 따라 화면이 깜빡거리지 않고 요청을 보냈다가 응답을 받아야 하는 기능을
		  구현하려면 "비동기식 요청" 을 보내야 하고, 그러기 위해서는 AJAX 라는 기술이 필요하다.
		  
		<br><br>
		
		* 동기식 VS 비동기식 <br>
		
		1. 동기식 <br> <!-- 그동안 우리가 써왔던 방식 -->
		요청 처리 후 그에 해당하는 응답페이지가 돌아오는 방식 <br>
		사용자 입장에서 요청 처리 후 그에 해당하는 응답페이지가 돌아와야만 다음 작업이 가능해짐 <br>
		만약, 요청 후 서버에서 호출된 결과로 응답페이지를 띄워주기 까지 시간이 지연된다면...
		사용자는 그 다음 페이지가 보여질 때 까지 계속 흰 화면만 보면서 무작정 기다려야 한다.. <br>
		응답 페이지가 보여지는 과정에서 그 페이지가 브라우저 화면에 그림이 다시 그려져야 하므로
		전체 페이지가 "리로드" 된다. (새로고침, 페이지가 기본적으로 깜빡거림) <br>
		예) 거의 대부분의 모든 기능 구현 시 기본적으로 쓰임
		
		<br>
		
		2. 비동기식 <br>
		요청을 보내더라도 현재 사용자가 보는 페이지를 그대로 유지하면서 몰래 요청이 들어가는 느낌 <br>
		사용자 입장에서는 응답이 돌아오기 전까지 현재 페이지를 유지하면서 
		중간 중간 마다 다른 추가적인 요청 또한 보낼 수 있게 됨 <br>
		요청을 보내놓고 그에 해당하는 응답이 올 때 까지 다른 작업을 계속 할 수 있게 된다. <br>
		현재 보고있는 페이지가 계속 유지되기 때문에 응답이 돌아오더라도 페이지가 안깜빡거리게됨!! <br>
		예) 회원가입 시 아이디 중복체크 기능, 
		    댓글 목록 조회 기능,
		    댓글 작성 기능 => 수업시간에 직접 만들어 볼 것!! <br>
		    실시간 검색어 랭크 기능, 
		    검색어 자동완성 기능,
		    메인페이지에서 TOP-N 분석 기능,
		    좋아요 기능 등
		    
		<br><br>
		
		* 비동기식 요청의 단점 <br>
		
		- 현재 페이지에 지속적으로 리소스가 쌓임 => 페이지가 현저히 느려질 수 있음 <br>
		- 코드 상의 복잡도가 기하급수적으로 증가 => 에러 발생 시 디버깅이 어려워짐 <br>
		- 요청 후 돌아온 응답데이터를 가지고 현재 페이지에서 새로운 요소를 동적으로 만들어서 뿌려줘야함
		  => DOM 요소를 새로이 만들어서 찍어내는 구문을 정확히 숙지하고 있어야 함 <br>
		
		<br><br>
		
		* AJAX 구현 방식 <br>
		
		1. 순수 JavaScript 언어를 이용한 방식 : 코드가 복잡하고 어려움 <br>
		2. jQuery 라이브러리를 이용하는 방식 : 간결하고 사용하기가 쉬움 <br>
		3. axios 라이브러리를 이용하는 방식 : react 수업 때 사용할 예정 (jQuery 방식과 문법이 유사)
	</p>
	
	<pre>
		- jQuery 라이브러리를 이용해서 AJAX 요청 보내기
		
		[ 표현법 ]
		
		$.ajax({
			속성명 : 속성값,
			속성명 : 속성값,
			...
		});
		
		- 주요 속성 (반드시 암기할것!!)
		url : 요청할 url 주소 (필수 입력 속성)
			  기존 form 태그 내의 action 속성의 역할, a 태그 내의 href 속성의 역할
		type 또는 method : 요청 전송 방식 (get / post)
						  생략 가능, 생략 시 기본값은 "get" 
						  기존 form 태그 내의 method 속성의 역할
		data : 요청 시 전달값이 있다면 키 + 밸류 형식으로 적어주는 부분
			   키 + 밸류 세트로 "객체" 형식으로 나열함!!
			   요청 시 전달값이 딱히 없다면 data 속성 자체를 생략한다.
			   기존 form 태그 내의 입력양식 관련 요소들의 역할, url 주소의 쿼리스트링을 나타내는 역할
		----- 여기까지는 요청과 관련된 속성들 -----
		
		success : ajax 요청 성공 시 실행할 코드를 정의하는 "메소드 속성"
		error : ajax 요청 실패 시 실행할 코드를 정의하는 "메소드 속성"
		complete : ajax 요청 성공이든 실패든 간에 무조건 실행할 코드를 정의하는 "메소드 속성"
				   (성공이든 실패든 간에 공통 코드가 있으면 활용 가능)
		----- 여기까지는 응답과 관련된 속성들 -----

		* 부수적인 속성 (참고만 할 것)
		- async : 서버와의 비동기 처리 방식 설정 여부 (기본값 true)
		- contentType : request 의 데이터 인코딩 방식 정의 (보내는 측의 데이터 인코딩)
		- dataType : 서버에서 response 로 오는 데이터의 데이터 형 설정, 값이 없다면 스마트하게 판단함
						xml : 트리 형태의 구조
						json : 맵 형태의 데이터 구조 (일반적인 데이터 구조)
						script : javascript 및 일반 String 형태의 데이터
						html : html 태그 자체를 return 하는 방식
						text : String 데이터
		- accept : 파라미터의 타입을 설정 (사용자 특화 된 파라미터 타입 설정 가능)
		- beforeSend : ajax 요청을 하기 전 실행되는 이벤트 callback 함수 (데이터 가공 및 header 관련 설정)
		- cache : 요청 및 결과값을 scope 에서 갖고 있지 않도록 하는 것 (기본값 true)
		- contents : jQuery 에서 response 의 데이터를 파싱하는 방식 정의
		- context : ajax 메소드 내 모든 영역에서 파싱 방식 정의
		- crossDomain : 타 도메인 호출 가능 여부 설정 (기본값 false)
		- dataFilter : response 를 받았을 때 정상적인 값을 return 할 수 있도록 데이터와 데이터 타입 설정
		- global : 기본 이벤트 사용 여부 (ajaxStart, ajaxStop) (버퍼링 같이 시작과 끝을 나타낼 때, 선처리 작업)
		- password : 서버에 접속 권한 (비밀번호) 가 필요한 경우
		- processData : 서버로 보내는 값에 대한 형태 설정 여부 (기본 데이터를 원하는 경우 false 설정)
		- timeout : 서버 요청 시 응답 대기 시간 (milisecond)
	</pre>
	
	<br>
	
	<hr>
	
	<h1>jQuery 라이브러리를 이용한 AJAX 테스트</h1>
	
	<!-- 
		명심할 사항 : jQuery 라이브러리 연동 필수!!
				    이 때, slim 버전에는 $.ajax() 함수 구문이 정의되어있지 않음!!
				    (slim 버전은 쓰지 말것!!)
	-->
	
	<h3>1. 버튼 클릭 시 get 방식으로 서버에 데이터 전송 (요청) 및 응답</h3>
	
	<!-- 기존의 동기식 요청 방식 -->
	<%-- 
	<form action="/ajax/test1" method="get">
		입력 : <input type="text" name="test1">
		<button type="submit">전송</button>
	</form>
	--%>
	
	<!-- ajax 를 이용한 비동기식 요청 방식 -->
	입력 : <input type="text" id="input1">
	<button type="button" id="btn1">전송</button>
	
	<br>
	
	응답 : <label id="output1">현재 응답 없음</label>
	
	<!-- 
		두 방식의 요청 화면 차이점 
		1. form 태그 여부 - form 태그를 쓰는 순간 "동기식" 요청이므로!!
		2. name 속성 여부
		3. submit 버튼 여부
	-->
	
	<!-- ajax 화면에서 전송 버튼 클릭 시 아무일도 안일어남!! - 클릭이벤트 부여!! -->
	<script>
		$(function() {
			
			// 아이디가 btn1 인 요소에 클릭이벤트 부여
			// > 기능 흐름 상, btn1 이 클릭되는 순간 ajax 요청을 보내볼것!!
			$("#btn1").click(function() {
				
				// 굳이 form 태그를 안쓰더라도 동기식 요청을 보낼 수는 있음
				// location.href = "/ajax/test1?input=" + $("#input1").val();
				
				// 비동기식 요청
				$.ajax({
					url : "/ajax/jqAjax1",
					type : "get",
					data : { input : $("#input1").val() },
					success : function(result) {
						
						// console.log("ajax 통신 성공!");
						
						// console.log(result);
						// > 응답데이터는 항상 success function 의
						//   "매개변수" 로 정의하면 받아낼 수 있다!!
						
						// 이렇게 받아온 응답데이터를
						// DOM 요소로써 화면에 출력해보고 싶음!!
						$("#output1").text(result);
						
					},
					error : function() {
						
						console.log("ajax 통신 실패!");
					},
					complete : function() {
						
						console.log("ajax 통신 완료!");
					}
				});
				// > 페이지가 전환되지 않음!! (브라우저 url 주소창도 그대로임)
				//   위의 속성들을 조합하여 url 주소를 만들고 몰래 요청을 다녀오는 개념!!
				//   단, 해당 요청을 받아줄 Controller 가 없기 때문에 404 에러 발생
				
			});
			
		});
	</script>
	
	<br>
	
	<hr>
	
	<h3>2. 버튼 클릭 시 post 방식으로 서버에 데이터 전송 (요청) 및 응답</h3>
	
	이름 : <input type="text" id="input2_1"> <br>
	나이 : <input type="number" id="input2_2"> <br>
	<button type="button" onclick="test2();">전송</button>
	
	<br>
	
	응답 : <label id="output2">현재 응답 없음</label>
	
	<script>
		function test2() {
			
			// 사용자가 입력한 값들을 전달값으로 넘기면서 AJAX 요청 처리
			
			// 사용자가 입력한 값들을 먼저 변수에 담기
			let name = document.getElementById("input2_1").value;
			let age = document.getElementById("input2_2").value;
			
			// console.log(name, age);
			
			$.ajax({
				url : "/ajax/jqAjax2",
				type : "post",
				data : { name : name, age : age },
				success : function(result) {
					
					console.log("ajax 통신 성공!");
					console.log(result);
					
					/*
					// JSONArray 로 넘겨주면 자바스크립트의 배열로 넘어오므로 - 반복문 활용 가능
					for(let i = 0; i < result.length; i++) {
						console.log(result[i]);
					}
					*/
					
					/*
					// JSONObject 로 넘겨주면 자바스크립트의 객체로 넘어오므로 - for in문 활용 가능
					// console.log(result.name, result.age);
					
					for(let key in result) {
						console.log(result[key]);
					}
					*/
					
					$("#output2").text("이름 : " + result.name + ", 나이 : " + result.age);
					
					// 입력요소에 초기화 되는 효과 추가
					$("#input2_1").val("");
					$("#input2_2").val("");
					
				},
				error : function() {
					
					console.log("ajax 통신 실패!");
				}
			});
			
		}
	</script>
	
	<br><hr>
	
	<h3>3. 서버로 데이터 전송 후 조회된 객체를 응답데이터로 받기 </h3>
	
	<!-- 즉, 단일행 조회한 결과를 응답데이터로 받겠다. 
		
		- DAO 단
		Member m = sqlSession.selectOne("~~~~,~~~", XXX);
		// 이 단일행 조회 결과인 m 객체를 응답데이터로 넘기는 연습
	-->
	
	<!-- 관리자페이지를 만든다는 가정 하에 회원번호로 검색하는 기능 구현중 -->
	회원번호 입력 : <input type="number" id="input3">
	<button onclick="test3();">조회</button>
	
	<br>
	
	<div id="output3">
	</div>
	
	<script>
		function test3() {
			
			// 입력받은 회원번호를 뽑아서 ajax 요청 보내기
			let userNo = document.getElementById("input3").value;
			
			$.ajax({
				url : "/ajax/jqAjax3",
				type : "get",
				data : {userNo : userNo},
				success : function(result) {

					let resultStr = result.userNo + result.userName + result.age + result.gender;
					$("#output3").html(resultStr);
				},
				error : function() {
					
					console.log("ajax 통신실패");
				}
			});
		}
	</script>
	
	<br><hr>
	
	<h3>4. 응답데이터로 여러개의 VO 객체들이 담겨있는 ArrayList로 받기</h3>
	<!-- 여러 행 조회인 경우 ArrayList<Member> list = memberService.selectMemberList(); -->
	
	<!-- 회원전체조회 기능 만든다고 가정 -->
	<button type="button" onclick="test4();">회원전체조회</button>
	
	<table id="output4" boarder="1" style="text-align : center;">
	
		<thead>
			<tr>
				<th>회원번호</th>
				<th>회원명</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
		</thead>
		<tbody></tbody>
	
	</table>
	
	<script>
		function test4() {
			
			// 버튼 클릭시 회원전체조회 요청을 ajax로 보내기
			
			$.ajax({
				url : "/ajax/jqAjax4",
				type : "post",
				success : function(result) {
					
					let resultStr = "";
					
					for(let i = 0; i < result.length; i++) {
						
						resultStr += "<tr>" 
											+ "<td>" + result[i].userNo + "</td>"
											+ "<td>" + result[i].userName + "</td>"
											+ "<td>" + result[i].age + "</td>"
											+ "<td>" + result[i].gender + "</td>"
									+ "</tr>"
					}
					
					$("#output4 tbody").html(resultStr);
					// on 동적으로 할 떄는 on구문 사용
					
				},
				error : function() {
					
					console.log("ahax 통신실패");
				}
				
			});
		}
	</script>

</body>
</html>













