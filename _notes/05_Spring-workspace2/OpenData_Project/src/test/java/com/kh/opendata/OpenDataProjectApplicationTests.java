package com.kh.opendata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenDataProjectApplicationTests {
	
	// 인증키 따로 필드로 빼두기
	public static final String SERVICEKEY = "00d2893717c3abcb4d0a95371a23f4cbd8293107f3354028ea277be8baf6e5e0";
	// 인증키는 한 번 발급 받으면 고정임 => 상수필드로

	@Test
	void contextLoads() throws IOException {
		
		// * OpenAPI 방식으로 공공데이터를 요청하고 응답을 받는 테스트코드 작성하기
		
		// 1. 오픈API 서버로 요청할 url 주소 세팅(String)
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		
		// 미리보기하면 url 주소의 쿼리스트링에 요청시전달값들이 나열되어있음 (GET방식)
		url += "?ServiceKey=" + SERVICEKEY;
		url += "&pageNo=1";
		url += "&numOfRows=10";
		url += "&resultType=xml";
		
		System.out.println(url);
		// 특히 서비스키는 제대로 넣어졌는지 확인
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		
		// 3. 응답데이터 받아올 입력스트림 객체 얻어오기
		// 요청한 서버와의 입력스트림을 열어야 함(응답데이터 읽어들이기)
		// 응답데이터에 다량의 한글이 들어가 있으므로 문자스트림 이용 예정
		// 이왕이면 읽어들이는 속도를 빠르게 하기 위해 보조스트림도 붙여서 이용
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line; // null로 초기화
		
		String responseText = ""; // 최종 응답데이터를 담을 변수
		
		// 4. 입력스트림 객체를 이용해서 한 줄 한 줄씩 응답데이터 받기
		while((line = br.readLine()) != null) {
			
			// System.out.println(line);
			responseText += line;
		}
		
		// 5. 응답 다 받으면 입력스트림 닫고, 요청연결 끊기
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		
	}

}
