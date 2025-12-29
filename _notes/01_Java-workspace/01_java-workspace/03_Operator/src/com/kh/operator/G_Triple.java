package com.kh.operator;

import java.util.Scanner;

// 삼항연산자
public class G_Triple { // G_Triple 클래스 영역 시작

	/*
	 * * 삼항연산자
	 * - 3개의 항목을 가지고 연산하는 연산자
	 * - 조건식을 통해 연산 결과가 true로 나올 경우, false로 나올 경우
	 *   처리하고 싶은 각각의 경우의 수를 표현하는 연산자
	 * 
	 * [표현법]
	 * (조건식) ? (조건식결과가true일경우의결과값) : (조건식결과가false일경우의결과값)
	 * 
	 * > 조건식: 연산 결과가 true 아니면 false로 나오는 식
	 * 
	 */
	
	public void method1() {// method1 영역 시작
		
		// 지금까지 출력한 결과
		// > 사용자가 입력한 값이 양수입니까?: true
		// > 사용자가 입력한 값이 양수입니까?: false 
		
		// 이번에 하려는 출력 결과
		// > 양수일 경우: "양수입니다."
		// > 아닐 경우: "양수가 아닙니다."
		// > 결과를 String으로 친절하게 표현하고자 함!
		
		
		// 사용자가 입력한 정수값이 양수인지 아닌지를 판별 후
		// 그에 맞는 결과 출력
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값을 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// boolean result = (num > 0);
		// String result = ((num > 0) ? ("양수입니다.") : ("양수가 아닙니다."));
		// System.out.println(num + "의 값은: " + result);
		
		// 간단하게 쓰기
		// System.out.println(num + "은(는)" + ((num > 0) ? "양수입니다" : "양수가 아닙니다."));
		// 삼항연산자가 산술연산보다 우선순위가 낮아서 오류가 발생함!!
		// 삼항연산 부분을 괄호로 묶어준다!!
		
		// 또 다른 방법
		String result = (num <= 0) ? "양수가 아닙니다." : "양수입니다.";
		System.out.printf("%d은(는) %s \n", num, result);
		
		// > 같은 결과를 출력하지만, 조건에 따라 뒤의 참/거짓 내용 다를 수 있음(말장난 주의)
		
		
	} // method1 영역 끝
	
	// 연습문제
	public void method2() { // method2() 메소드 영역 시작
		
		//사용자로부터 키보드로 정수값 하나를 입력받은 후
		// 그 정수값이 짝수인지 홀수인지 판별 후 출력
		// 짝수일 경우, "짝수입니다"
		// 홀수일 경우, "홀수입니다" 출력하시오
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값 하나를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.println((num % 2 == 0) ? ("짝수입니다.") : ("홀수입니다."));
		
		// 다른 방법
		// String result = ((num % 2 == 0) ? "짝수입니다." : "홀수입니다.");
		// System.out.printf("%d은(는) %s \n", num, result);
		
		
	} // method2() 메소드 영역 끝
	
	public void method3() { // method3 메소드 영역 시작
		
		// 사용자로부터 키보드로 영문자 하나를 입력받아
		// 대문자인지 대문자가 아닌지 판별한 후
		// 대문자일 경우, "대문자입니다."
		// 아닐 경우, "대문자가 아닙니다." 출력
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("영문자 한 개 입력: ");
		char ch = sc.nextLine().charAt(0);
		
		// 아스키코드 개념 접목
		String result = (('A' <= ch) && (ch <= 'Z') ? ("대문자입니다") : ("대문자가 아닙니다."));
		// > 조건식 부분에  ((65 <= ch) && (ch <= 90) 해도 무방하긴 함!
		System.out.printf("%c은(는) %s \n", ch, result);
		
		// * 도전문제!!
		// 위의 삼항연산자에서 대문자가 아닐 경우,
		// 소문자인지, 영문자 자체가 아닌지 한 번 더 판별해보기
		
		
	} // method3 메소드 영역 끝
	
	// method1 내용 보완
	public void method4() { // method4 메소드 영역 시작
		
		// 사용자로부터 입력받은 정수값이
		// 양수인지, 0인지, 음수인지 판별 후 출력
		// "양수입니다." / "음수입니다."/ "0입니다."
		// > 경우의 수 세개 이상으로 늘어남(기존과 달리)
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값을 입력하시오: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		String result = (num > 0) ? ("양수입니다.") : ((num == 0) ? "0입니다." : "음수입니다.");
		System.out.println(result);
		
		// 삼항연산자는 기본적으로 2가지 경우의 수만 표현 가능하지만(true or false)
		// 삼항연산자 안에 삼항연산자를 잘 중첩해서 표현하면 3가지 이상 표현 가능하다!!!
		// false 부분에 다시 삼항연산자 넣기 반복 반복
		
	} // method4 메소드 영역 끝
	
} // G_Triple 클래스 영역 끝
