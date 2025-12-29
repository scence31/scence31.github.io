package com.kh.chap03_wrapper.run;

// Wrapper 클래스
public class WrapperRun {
	
	/*
	 * * Wrapper 클래스
	 * - 기본자료형을 참조자료형으로 포장해주는 클래스들
	 * - 각 기본자료형에 맞는 Wrapper 클래스 타입이 별도 있음
	 * 
	 * [종류] 기본자료형 / Wrapper 클래스(참조자료형)
	 * boolean / Boolean
	 * char / Character
	 * byte / Byte
	 * short / Short
	 * int / Integer
	 * long / Long
	 * float / Float
	 * double / Double
	 * 
	 * 
	 */
	
	

	public static void main(String[] args) {
		
		// 1. 객체생성구문을 이용하는 방법
		
		int num1 = 10;
		int num2 = 15;
		
		Integer i1 = new Integer(num1); // num1 --> i1 
		Integer i2 = new Integer(num2); // num2 --> i2
		
		System.out.println(i1.toString()); // 10
		System.out.println(i2); // 15
		
		System.out.println(i1 == i2); // false 주소값비교
		
		System.out.println(i1.equals(i2)); // false 내용물 비교
		
		System.out.println(i1.hashCode()); // 10
		System.out.println(i2.hashCode()); // 15
		// 내용물 기준으로 해시코드 리턴
		
		// 대소비교
		System.out.println(i1.compareTo(i2)); // -1
		// compartTo: Object 클래스에서 제공되는 메소드(두 갑을 비교)
		// a.compaerTo(b) a>b == 1, or -1, a=b == 0 리턴
		
		
		// 2. 객채생성구문 없이 이용하는 법(AutoBoxing)
		Integer i3 = num1; // 10
		Integer i4 = num2; // 15
		
		// AutoBoxing 안되는 경우
		// Integer i5 = "123"; 다형성 적용 안되어서
		
		Integer i5 = new Integer("123"); // 문자열을 Integer로 바꾸고 싶을 때

		// 반대로 Wrapper에서 기본으로 변환하는 법 ==> UnBoxing
		
		// 1. 해당 Wrapper 클래스에 정의된 xxxValue() 메소드 활용하는 방법
		int num3 = i3.intValue(); // i3 --> num3
		int num4 = i4.intValue(); // i4 --> num4
		
		// 2. 메소드 없이 그냥 대입 AutoUnBoxing
		int num5 = i1;
		int num6 = i2;
		
		System.out.println("-----------------------");
		
		// 기본자료형 <---> String
		
		String str1 = "10";
		String str2 = "15.3";
		
		System.out.println(str1 + str2); // "1015.3"
		
		// 1. String --> 기본자료형 ***
		// > "파싱한다." (parsing)
		//[표현법]
		// 해당Wrapper클래스명.parseXxx(변환할문자열);
		int i = Integer.parseInt(str1); // "10" -- > 10
		double d =Double.parseDouble(str2); // "15.3" --> 15.3
		
		System.out.println(i + d); // 25.3
		
		//파싱 주의사항
		// int i2 = Integer.parseInt("12.3");
		// NumberFormatException 오류발생(형식에 맞지 않음)
		
		// 2. 기본자료형 --> String
		// 2-1. String 클래스에 정의된 valueOf 메소드 활용하는 법
		//[표현법]
		// String.valueOf(변환할기본자료형값): String - 오버로딩된 메소드
		String strI = String.valueOf(i); // 10 --> "10"
		String strD = String.valueOf(d); // 15.3 --> "15.3"
		
		System.out.println(strI + strD); // "1015.3"
		
		// 2-2. " "(빈문자열) 접하는 법
		String result = 10 + "";
		
	}

}




















