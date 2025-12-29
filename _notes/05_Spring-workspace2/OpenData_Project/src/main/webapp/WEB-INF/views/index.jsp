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

	<!-- 공공데이터 조회해서 이 메인페이지에 뿌리기 -->
	<h1>부산맛집</h1>
	
	<br><br>
	
	개수 :
	<select id="numOfRows" onchange="selectBusanList();">
		<option>10</option>
		<option>20</option>
		<option>30</option>
		<option>40</option>
		<option>50</option>
	</select>
	
	<!-- 이 페이지가 로딩되자마자 처음부터 응답데이터가 화면에 보였으면 함 -->
	<table border="1" id="result" align="center">
		<thead></thead>
		<tbody>
			<tr>
				<th width="200">가게명</th>
				<th width="200">전화번호</th>
				<th width="250">운영시간</th>
				<th>설명</th>
				<th>대표이미지</th>
			</tr>
		</tbody>
	</table>
	
	<script>
		function selectBusanList() {
			
			let numOfRows = $("#numOfRows").val();
			
			
			$.ajax({
				url : "/opendata/busan",
				type : "get",
				data : {numOfRows : numOfRows},
				success : function(result) {

					// console.log(result);
					// 응답데이터 형식이 XML인 경우
					// document 객체는 html 문서에서는 html 문서 1개를 나타내고
					// xml 문서에서는 xml 문서 1개를 나타냄
					// xml 또한 마찬가지로 markup language이기 때문에 태그들의 포함관계(부모자식)를 똑같이 DOM 요소들처럼
					// 가계도로 나타낼 수 있음
					// -> 제이쿼리의 자손탐색메소드 사용 가능 xml 태그에서
					// console.log(result.find("item"));
					// result는 document 객체를 카리키며 순수 자바스크립트 객체임. 근데 find는 제이쿼리라 호환 안맞음
					// console.log($(result).find("item"));
					// [item, item, ...]
					
					let itemArr = $(result).find("item");
					
					let resultStr = "";
					
					itemArr.each(function(index, item) {
						
						// console.log($(item).find("MAIN_TITLE").text());
						
						resultStr += "<tr>"
									+ "<td>" + $(item).find("TITLE").text() + "</td>"
									+ "<td>" + $(item).find("CNTCT_TEL").text() + "</td>"
									+ "<td>" + $(item).find("USAGE_DAY_WEEK_AND_TIME").text() + "</td>"
									+ "<td>" + $(item).find("ITEMCNTNTS").text() + "</td>"
									+ "<td>" + "<img width='200 src='" + $(item).find("MAiN_IMG_NORMAL").text() + "'></td>"
									+ "</tr>"
						
					});
					
					$("#result>tbody").html(resultStr);
					
					// 응답데이터 형식이 JSON인 경우
					/*
					const itemArr = result.getFoodKr.item;
					
					let resultStr = "";
					
					for(let i = 0; i < itemArr.length; i++) {


						resultStr += "<tr>"
									+ "<td>" + itemArr[i].TITLE + "</td>"
									+ "<td>" + itemArr[i].CNTCT_TEL + "</td>"
									+ "<td>" + itemArr[i].USAGE_DAY_WEEK_AND_TIME + "</td>"
									+ "<td>" + itemArr[i].ITEMCNTNTS + "</td>"
									+ "<td>" + "<img width='200' src='" + itemArr[i].MAIN_IMG_NORMAL + "'></td>"
									+ "</tr>"
						
					}
					
					$("#result>tbody").html(resultStr);
					*/
					
					
				},
				error : function() {
					
					console.log("부산맛집조회 ajax 통신실패");
				}
			})
			
		}
		
		$(function() {
			
			selectBusanList();
		})
		
	</script>

</body>
</html>











