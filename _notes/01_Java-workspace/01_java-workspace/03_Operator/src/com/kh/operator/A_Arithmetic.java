package com.kh.operator;

import java.util.Scanner;

// 산술연산자
public class A_Arithmetic { // A_Arithmetic 클래스 영역 시작
	
	/*
	 * * 산술연산자
	 * - 이항연산자로서 연산 방법과 우선순위가 일반 수학과 완전 동일
	 * 
	 * [종류]
	 * +, -, *, /(몫), %(Modular, 나머지)
	 */

	public void method1() { //method1 메소드 영역 시작
		
		int num1 = 10;
		int num2 = 3;
		
		System.out.println("num1 + num2 = " + (num1+num2));
		// 괄호로 묶어줘야 됨!
		// num1 + num2를 그냥 출력하면 String처럼 하나의 문자열 103으로 출력됨(10 3)
		
		System.out.println("num1 - num2 = " + (num1-num2));
		// > 뻴셈의 경우 괄호로 묶어주지 않으면
		//   String으로 앞쪽의 문자열과 10을 연이어줌 (연이은 결과가 String으로 나옴)
		//   이 엮인 문자열과 뒤의 숫자 3을 빼는 연산이 그 이후에 일어나서
		//   오류가 발생하는 것!(문자열에서 숫자를 뻴 수 없음)
		//   (num1-num2) 괄호 묶어야됨
		
		System.out.println("num1 * num2 = " + (num1*num2));
		System.out.println("num1 / num2 = " + (num1/num2));
		System.out.println("num1 % num2 = " + (num1%num2));
		// > 곱셈, 나눗셈, 모듈러의 경우
		//	 덧셈보다 우선순위가 높기 때문에 괄호없이도 결과 잘 출력
		//   다만 가독성을 위해 그냥 괄호로 묶자
		
		
		// * 산술 연산시 주의할 점
		// 아래 출력메소드 실행시 오류 발생
		// System.out.println(10/0);
		// System.out.println(10%0);
		// 오류명: ArithmeticException: / by zero
		// 수학과 마찬가지로 0으로 나눌 수 없음
		
		// 원칙상 항상 같은 타입의 값들끼리만 연산 가능
		// 연산결과 또한 항상 같은 타입으로 나옴
		
		double a = /*(double)*/35; // 35.0
		double b = /*(double)*/10; // 10.0
		// > 자동형변환된 상태
		
		System.out.println("a + b = " + (a+b)); // 45.0
		System.out.println("a - b = " + (int)(a-b)); // 25
		System.out.println("a * b = " + (a*b)); // 350.0
		System.out.println("a / b = " + (a/b)); // 3.5
		System.out.println("a % b = " + (a%b)); // 5.0
		
		
	} // method1 메소드 영역 끝
	
	// 연습문제
	public void method2() {// method2 메소드 영역 시작
		
		// 사용자에게 키보드로 인원수와 사탕의 개수를 각각 정수로 입렫 받아
		// 1인당 동일하게 나눠가진 사탕 개수와 남은 사탕의 개수를 출력
		
		/*
		 * 실행 예시)
		 * 인원수: 5
		 * 사탕개수: 164
		 * ---------------
		 * 1인당 사탕 개수: 32
		 * 남는 사탕 개수: 4
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("인원수: ");
		int person = sc.nextInt();
		sc.nextLine();
		
		System.out.print("사탕개수: ");
		int candy = sc.nextInt();
		sc.nextLine();
		
		
		System.out.println("---------------");
	
		System.out.println("1인당 사탕개수: " + (candy/person));
		System.out.println("남는 사탕개수: " + (candy%person));
		
		
		
		
		
	}// method2 메소드 영역 끝
	
	
} // A_Arithmetic 클래스 영역 끝
