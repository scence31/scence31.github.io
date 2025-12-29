package com.kh.operator;

import java.util.Scanner;

//논리연산자
public class F_Logical { // F_Logical 클래스 영역 생성

	/*
	 * * 논리연산자
	 * - 이항연산자로서, 두 개의 "논리값"을 연산하는 연산자
	 * 
	 * [표현법]
	 * 논리값 (논리연산자) 논리값 --> 결과 또한 논리값으로 나옴!!
	 * 
	 * [종류]
	 * AND 연산자: 논리값 && 논리값
	 *           그리고, 이면서, 이고
	 * 
	 * OR 연산자: 논리값 || 논리값
	 *          또는, 이거나
	 */
	
	public void method1() { // method1 메소드 영역 시작
		
		// 사용자가 키보드로 입력한 정수값이
		// 양수이면서 짝수인지 확인하기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값 한 개를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// 판별할 결과를 담아둘 변수 설정
		boolean result = ((num > 0) && (num % 2 == 0));
		// bollean 한 이유: true or false
		// result 한 이유: 너무 길어서 치환해줌
		
		// > AND의 경우 && 연산자 기준 양쪽이 모두 true여야만 결과도 true
		
		System.out.println("사용자가 입력한 값이 양수면서 짝수입니까?: " + result);
		
		
	} // method1 메소드 영역 끝
	
	public void method2() { // method2 메소드 영역 시작
		
		// 사용자가 키보드로 입력한 정수값이
		// 1 이상 100 이하의 숫자인지 판별하기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값 한 개를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// 1 <= num <= 100
		// 프로그래밍에서는 이렇게 한 번에 표현 불가능
		// 우선순위 때문임. 1 <= num이 먼저 발생하고 다음 연산에 만족하지 않음
		
		boolean result = ((num >= 1) && (num <= 100));
		System.out.println("사용자가 입력한 값이 1 이상 100 이하입니까?: " + result);
		
		
	} // method2 메소드 영역 끝
	
	public void method3() { // method3 메소드 영역 시작
		
		// 사용자가 키보드로 입력한 값이 'y' 이거나 'Y' 인지 판별하기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("계속하시려면 소문자 y 또는 Y를 입력하세요: ");
		char answer = sc.nextLine().charAt(0);
		
		boolean result = ((answer == 'y') || (answer == 'Y'));
		// > OR 연산자의 경우 || 기준으로 하나만 true면 무조건 true임!
		
		System.out.println("입력한 값이 y 또는 Y 인가요?: " + result);
		
		// > 같은 논리연산자지만 &&가 ||보다 우선순위가 더 높다!!
		
	} // method3 메소드 영역 끝
	
	public void method4() { // method4 메소드 영역 시작
		
		/*
		 * * 논리연산자의 특징
		 * 1. &&: 둘 다 true여야 결과도 true
		 * true && true == true
		 * true && false == false
		 * false && false == false
		 * 
		 * 2. ||: 둘 중에 하나라도 true면 결과도 true
		 * true || true == true
		 * true || false == true
		 * false || false == false
		 */
		
		// - 논리연산 시 주의사항(위에 특징 참고)
		int num = 10;
		boolean result1 = false && (num > 0); //false
		// > 뒤의 비교연산 구문이 Dead code라고 경고가 뜬다!
		//   경고표시(노란밑줄): 실행시 오류는 없지만 추후 문제가 생길 여지 있음
		//   false &&까지만 보더라도 어차피 결과는 false인데
		//   굳이 왜 뒤에 코드를 작성하고 실행하려고 하는지 물어보는 것
		System.out.println(result1);
		
		boolean result2 = true || (num > 0); //true
		// 마찬가지 경고
		System.out.println(result2);
		
		/*
		 * * Dead code
		 * - 불필요한 코드, 실행되지 않아도 되는 코드
		 * - 불필요한 코드 실행은 컴퓨터 입장에서 시간과 메모리 공간 낭비를 초래
		 * > Dead code는 최소화하자!
		 *
		 */
		
	} // method4 메소드 영역 끝
	
	
} // F_Logical 클래스 영역 끝
