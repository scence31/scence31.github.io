package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OpenDataController {

	// 인증키 따로 필드로 빼두기
	public static final String SERVICEKEY = "00d2893717c3abcb4d0a95371a23f4cbd8293107f3354028ea277be8baf6e5e0";
	// 인증키는 한 번 발급 받으면 고정임 => 상수필드로	
	
	// 응답데이터를 JSON으로 받았을 경우
	/*
	@ResponseBody
	@GetMapping(value="busan", produces="application/json; charset=UTF-8")
	public String busanList(int numOfRows) throws IOException {
		
		// 1. 요청한 url주소 만들기
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		
		url += "?ServiceKey=" + SERVICEKEY;
		url += "&pageNo=1";
		url += "&numOfRows=" + numOfRows;
		url += "&resultType=json";
		// 근데 요청시 전달값을 받을 때 한글을 넘겨야 할 경우엔
		// 그냥 넘기지 말고 URLEncoder.encode("한글값", "UTF-8");
		// 예시
		// url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		
		// 2. 자바구문을 통해 오픈API 요청
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		
		// 3. 요청한 서버와의 입력스트링 객체 만들기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		// 4. 한 줄씩 응답데이터 읽어들이기
		String line;
		String responseText = "";
		
		
		while((line = br.readLine()) != null) {
			
			responseText += line;
		}
		
		// 5. 통로닫기 및 연결끊기
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		// JSON 형식의 문자열이 담김
		
		return responseText;
		
	}
	*/
	
	// 응답데이터를 XML로 받아올 경우
	@ResponseBody
	@GetMapping(value="busan", produces="text/xml; charset=UTF-8")
	public String busanList(int numOfRows) throws IOException {

		// 1. 요청한 url주소 만들기
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		
		url += "?ServiceKey=" + SERVICEKEY;
		url += "&pageNo=1";
		url += "&numOfRows=" + numOfRows;
		url += "&resultType=xml";
		// 근데 요청시 전달값을 받을 때 한글을 넘겨야 할 경우엔
		// 그냥 넘기지 말고 URLEncoder.encode("한글값", "UTF-8");
		// 예시
		// url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		
		// 2. 자바구문을 통해 오픈API 요청
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		
		// 3. 요청한 서버와의 입력스트링 객체 만들기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		// 4. 한 줄씩 응답데이터 읽어들이기
		String line;
		String responseText = "";
		
		while((line = br.readLine()) != null) {
			
			responseText += line;
		}
		
		// 5. 통로닫기 및 연결끊기
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		// -> XML 형식의 문자열
		
		return responseText;
	}

}
