package com.kh.variable;

// printf 구문(출력메소드)
public class D_Printf { // D_Printf 클래스 영역 시작

	/*
	 * * 출력메소드(출력문)
	 * System.out.print(출력하고자하는값);
	 * - 단순히 값을 출력만 해줌
	 * 
	 * System.out.println(출력하고자하는값);
	 * - 해당 값을 출력 후 줄바꿈(개행)해줌
	 * 
	 * System.out.printf("출력하고자하는형식(d% 등) \n", 출력하고자하는값1, 출력하고자하는값2 ...);
	 * - 형식에 맞춰서 값들을 출력해주는 역할, 출력 후 줄바꿈 x
	 *   (f: format의 약자)
	 * - 이 때, 문자열 안에 그 값이 들어갈 자리를 아래와 같은 형식으로 잡아줘야 함
	 * %d: 정수
	 * %f: 실수
	 * %c: 문자
	 * %s: 문자열
	 */
	
	public void printfTest() { // printfTest 클래스 영역 시작
		
		// 정수 테스트
		int iNum1 = 10;
		int iNum2 = 20;
		
		// "iNum1: xx, iNum2: xx" 형식으로 출력하기
		// 1. println 사용
		System.out.println("iNum1: " + iNum1 + ", iNum2: " + iNum2);
		
		// 2. printf 사용
		System.out.printf("iNum1: %d, iNum2: %d \n", iNum1, iNum2);
		
		// "10+20=30" 형식으로 출력하기
		// 1. println 사용
		System.out.println(iNum1 + " + " + iNum2 + " = " + (iNum1+iNum2));
		
		// 2. printf 사용
		System.out.printf("%d + %d = %d \n",iNum1, iNum2, iNum1+iNum2);
		
		// %d에 대한 옵션
		System.out.printf("%d \n", iNum1);
		
		System.out.printf("%5d \n", iNum1);
		// > %5d: 5칸의 공간 중 오른쪽에 정렬하겠다.
		System.out.printf("%-5d \n", iNum1);
		// > %-5d: 5칸의 공간 중 왼쪽에 정렬하겠다.
		
		// 실수 테스트
		double dNum = 4.27546789;
		
		// "dNum: xx.xxxxxx" 형식으로 출력하기
		// 1. println 사용
		System.out.println("dNum: " + dNum); // 4.27546789
		
		// 2. printf 사용
		System.out.printf("dNum: %f \n", dNum); // 4.275468
		// > %f: 소수점 아래 7번째 자리에서 반올림하고 소수점 6자리까지만 출력됨

		// %f에 대한 옵션
		System.out.printf("dNum: %.1f \n", dNum); // 4.3
		// > %.1f: 소수점 아래 2번째 자리에서 반올림하고 소수점 1자리까지만 출력됨
		// .자릿수로 제어 가능
		
		// 문자와 문자열 테스트
		char ch = 'a';
		String str = "Hello";
		
		System.out.printf("%c %s \n", ch, str);
		
		// %c, %s에 대한 옵션
		System.out.printf("%C %S \n", ch, str);
		// >%C, %S: 알파벳인 경우 모두 대문자로 변경해서 출력해줌
		
		/*
		 * * printf
		 * - 포맷(형식) 단 한 번 제시로 간편하게 여러 값들을 출력 가능하다.
		 * - 단, printlin처럼 출력 후 줄바꿈이 발생하지 않는다.
		 * - 또한 지정한 포맷의 구멍 개수, 종류, 순서를 모두 맞춰서 나열해야 한다.
		 */
		
		
		
	} // printfTest 클래스 영역 끝
	
	
} // D_Printf 클래스 영역 끝
