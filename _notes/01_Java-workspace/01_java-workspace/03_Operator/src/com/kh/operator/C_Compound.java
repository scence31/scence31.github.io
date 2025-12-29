package com.kh.operator;

// 복합대입연산자
public class C_Compound { // C_Compound 클래스 영역 시작

	/*
	 * * 복합대입연산자(산술대입)
	 * - 이항연산자로서, 산술연산자와 대입연산자를 함께 사용하는 연산자
	 * - 즉, 산술연산 후에 그 결과를 자신한테 다시 "대입" 까지 해주겠다.
	 * 
	 * [종류]
	 * +=, -=, *=, /=, %=
	 * 
	 * [표현법]
	 * a += 3; // a = a+3;
	 * > 즉, 기존의 a 값에 3을 더해서 a에 그 결과를 다시 대입해주겠다.
	 */
	
	public void method1( ) { // method1 메소드 영역 시작
		
		int num = 12;
		System.out.println("현재 num: " + num); //12
		
		// num을 3 증가시키기
		num = num+3;
		System.out.println("3 증가시킨 num: " + num); //15
		
		// 또 num 3 증가시키기
		num += 3;
		System.out.println("또 3증가시킨 num: " +num); //18
		
		// num 5 감소시키기
		num -= 5;
		System.out.println("3 감소시킨 num: " + num); //13
		
		// num 6배 증가시키기
		num *= 6;
		System.out.println("6배 증가시킨 num: " + num); //78
		
		// num 2배 감소시키기
		num /= 2;
		System.out.println("2배 감소시킨 num: " + num); //39
		
		// num을 4로 나눴을 때의 나머지를 num에 대입
		num %= 4;
		System.out.println("최종 num: " + num); //3
		
		// +=의 경우 "문자열 연결" 역할도 해줌!!
		String str = "Hello";
		str += "World";
		//str = str + "World";
		
		System.out.println(str);
		
	} //method1 메소드 영역 끝
	
} // C_Compound 클래스 영역 끝
