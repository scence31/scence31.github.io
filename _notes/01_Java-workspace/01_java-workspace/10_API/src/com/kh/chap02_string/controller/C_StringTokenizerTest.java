package com.kh.chap02_string.controller;

import java.util.StringTokenizer;

// 문자열을 분리하는 방법
public class C_StringTokenizerTest {
	
	public void method1() {
		
		String str = "Java,Oracle,JDBC,HTML,Server,Spring";
		
		// 방법1)
		// 문자열.split(String 구분자): String[]
		String[] arr = str.split(",");
		
		/*
		for(int i = 0; i < arr.length; i++) {
			
			System.out.println(arr[i]);
		}
		*/
		
		// 배열의 경우 for문을 다음과 같이도 쓸 수 있다.
		// > 향상된 for문 또는 foreach문
		// [표현법]
		// for(변수선언문: 배열명) { }
		for(String s: arr) { // 매 반복회차마다 String s = arr[i];
			
			System.out.println(s);
		}
		
		System.out.println("----------------------------");
		
		// 방법2)
		// java.util 패키지의 StringToknizer 클래스를 이용하는 방법
		// [표현법]
		// StringTokenizer stn = new StringTokenizer(분리할문자열, 구분자);
		
		StringTokenizer stn = new StringTokenizer(str, ",");
		/*
		System.out.println("분리된 문자열 개수: " + stn.countTokens()); // 6개
		
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		
		System.out.println(stn.nextToken());
		*/
		// > NoSuchElementException 오류 발생
		// 현재 stn 공간에 토큰이 더이상 남아있지 않음(개수 이상 출력시) 아예 뽑혀나감
		
		// 반복문 활용
		/*
		for(int i = 0; i < stn.countTokens(); i++) {
			
			System.out.println(stn.nextToken());
		}
		*/
		// > stn.countTokens() 메소드는
		//   현재 실제로 남아있는 토큰의 개수만 세서 반환해줌
		//   매 반복회차마다 counTokens() 결과가 1씩 감소하기 때문에 반복이 3번만 돎
		
		// 해결방법1)
		// 보조해주는 변수 이용하기
		/*
		int count = stn.countTokens(); // 6
		for(int i = 0; i < count; i++) {
			
			System.out.println(stn.nextToken());
		}
		*/
		
		// 해결방법2)
		// 반복하되 stn 공간에 토큰이 남아있을 동안만 돌리기
		while(stn.hasMoreTokens()) {
			// boolean, true or false
			
			System.out.println(stn.nextToken());
		}
	}

}


