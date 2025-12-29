package com.kh.chap04_date.run;

import java.text.SimpleDateFormat;
import java.util.Date;

// 날짜 관련 클래스들
public class DateRun {

	/*
	 * * Date 클래스(java.util.Date)
	 * - 날짜와 시간에 대한 정보를 담을 수 있는 클래스
	 * - 자발 개발 초창기에 급하게 만들어진 다소 완성도가 떨어지는 클래스
	 * 
	 * * SimpleDateFormat 클래스(java.text.SimpleDateFormat)
	 * - Date 객체의 정보를 내 입맛에 맞는 문자열 형식으로 변환할 때 사용
	 *
	 */
	
	public static void main(String[] args) {
		
		// 기본생성자를 통해 Date 객체 생성
		Date today = new Date();
		System.out.println("기본생성자: " + today);
		// 현재 날짜 및 시간
		
		// 내가 원하는 날짜 세팅 ex) 2020.6.19.
		// 방법1) 매개변수생성자 이용
		// Date date1 = new Date(2020, 6, 19);
		// deprecated: 권장하지 않음(Date에 커서 대면)
		// System.out.println("매개변수생성자: " + date1);
		// 매개변수 쓰면 연도 + 1900, 월 + 1 이렇게 세팅됨
		
		Date date1 = new Date(2020 - 1900, 6 - 1, 19, 19, 15, 50);
		System.out.println("매개변수생성자: " + date1);
		
		// 방법2) 기본생성장로 객체생성 후 setter 메소드를 호출하는 방법
		Date date2 = new Date(); // 현재날짜 및 시간
		date2.setYear(2020 - 1900);
		date2.setMonth(6 - 1);
		date2.setDate(19);
		date2.setHours(19);
		date2.setMinutes(15);
		date2.setSeconds(50);
		System.out.println("setter메소드: " + date2);
		
		System.out.println("-----------------------------------------");
		
		// SimpleDateFormat 객체 이용해서 형식 바꾸기
		
		// 1. 매개변수로 형식을 지정하면서 SimpleDateFormat 객체 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss (E)");
		
		/*
		 * * SimpleDateFormat 형식
		 * 연도: yyyy
		 * 월: MM
		 * 일: dd
		 * 시: hh
		 * 분: mm
		 * 초: ss
		 * 요일: E
		 * 
		 * 
		 */
		
		// 2. SimpleDateFormat 객체에서 제공하는 format 메소드 호출
		// [표현법]
		// sdf.format(Date객체): String 
		String formatDate = sdf.format(today);
		System.out.println(formatDate);
		
	}

}






















