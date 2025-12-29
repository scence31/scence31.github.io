package com.kh.third.run;

import com.kh.third.MyName;


public class Run { // 클래스 영역 시작
	
	public static void main(String[] args) { //메인메소드 영역 시작
		
		
		// 1. MyName 클래스를 대변할 이름 생성(new)하기
		// 클래스명 대변할이름 = new 클래스명();
		MyName mn = new MyName();
		
		// 2. 만든 대변할 이름을 통해 해당 메시지 호출
		// 대변할이름.메소드명();
		
		mn.callMyName();
		
		
	} //메인메소드 영역 끝

} // 클래스 영역 끝
