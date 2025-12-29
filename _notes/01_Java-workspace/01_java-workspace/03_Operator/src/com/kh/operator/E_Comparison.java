package com.kh.operator;

import java.util.Scanner;

//비교연산자
public class E_Comparison { // E_Comparison 클래스 영역 시작

	/*
	 * * 비교연산자(관계연산자)
	 * - 이항연산자로서, 두 개의 값을 가지고 비교해주는 역할
	 *   (크고 작음을 비교, 일치함을 비교)
	 * - 비교연산한 결과가 참일 경우 ture, 거짓일 경우 false 값을 변환
	 *   (아무리 숫자끼리 연산하더라도 결과가 숫자가 아닌 논리값으로 출력됨!!)
	 * > 후에 특정 조건을 제시할 수 있는 "조건문" 같은 데에서 많이 쓰일 예정
	 * 
	 * [종류]
	 * - 크고 작음을 비교
	 * a < b: a가 b보다 작습니까?(질문)
	 * a > b: a가 b보다 큽니까?
	 * a <= b: a가 b보다 작거나 같습니까?
	 * a >= b: a가 b보다 크거나 같습니까?
	 * 
	 * - 일치함을 비교
	 * a == b: a와 b가 일치합니까?
	 * a != b: a와 b가 일치하지 않습니까?
	 * 
	 * > 크고 작음을 비교하는 연산자들이 일치함을 비교하는 연산자들보다 우선순위가 높다!!
	 * 
	 */
	
	
	public void method1() { // method1 메소드 영역 시작
		
		int a = 10;
		int b = 25;
		System.out.println("a > b: " + (a > b)); //false
		// > 우선순위 챙기기(소괄호)
		// 산술연산자가 비교연산자보다 우선순위 높음
		System.out.println("a <= b: " + (a <= b)); //true
		
		
		boolean result1 = (a == b);
		// > 비교연산자보다 대입연산자인 =이 우선순위가 낮아 괄호 없이도 작동!
		//   단, 가독성 떄문에 괄호 묶어서 표현 권장
		System.out.println("result1: " + result1); // false
		
		boolean result2 = (a != b);
		System.out.println("result2: " + result2); // true
		
		// 산술연산 비교연산 결합
		System.out.println((a - b) < 0);
		// (a - b) 결과가 0보다 작습니까?
		// (10 - 25) = -15가 0보다 작나요?: true
		// > 산술연산이 우선순위가 더 높아서
		//   산술연산 결과를 가지고 비교연산이 들어간다
		//   단, 가독성을 위해 괄호로 묶자
		
		// 예제)
		// 어떤 숫자를 2로 나누었을 때
		// 나머지값이 0이냐 1이냐를 가지고
		// 짝수인지 홀수인지 판별 가능!!
		System.out.println("a가 짝수입니까?: " + (a % 2 == 0)); // true
		System.out.println("b가 홀수입니까?: " + (b % 2 == 1)); // true
		
		System.out.println("b가 홀수입니까?: " + (b % 2 != 0)); // true
		// > 어떻게 비교연산식을 작성하냐에 따라서
		//   결과가 같게 또는 다르게 나올 수 있다(!= --> 부정=)
		
		
	} // method1 메소드 영역 끝
	
	public void method2() {
		
		// 연습문제
		// 0, 1. 사용자로부터 키보드로 정수값 한 개를 입력받아서
		// 2, 3, 4. 그 정수값이 "양수" 인지 "음수" 인지 "0" 인지 판별
		// 5. 그리고 절취선 생성 후 아래에
		// 6, 7. 그 정수값이 짝수인지 홀수인지 판별
		
		
		// 0. 스캐너 생성
		Scanner sc = new Scanner(System.in);
		// 1. 스캐너에 따라 알맞은 내용 작성
		System.out.print("정수값 한 개 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		// 2. 양수인지 판별
		System.out.println("사용자가 입력한 값은 양수입니까?: " + (num > 0));
		// 3. 0인지 판별
		System.out.println("사용자가 입력한 값은 0입니까?: " + (num == 0));
		// 4. 음수인지 판별
		System.out.println("사용자가 입력한 값은 음수입니까?: " + (num < 0));
		// 5. 절취선 생성
		System.out.println("--------------");
		// 6. 짝수인지 판별
		System.out.println("입력한 값은 짝수입니까?: " + (num % 2 == 0));
		// 7. 홀수인지 판별
		System.out.println("입력한 값은 홀수입니까?: " + (num % 2 != 0));
		
		
	}
	
} // E_Comparison 클래스 영역 끝
