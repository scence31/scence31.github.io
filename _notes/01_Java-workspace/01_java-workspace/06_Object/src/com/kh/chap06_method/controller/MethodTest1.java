package com.kh.chap06_method.controller;

// 일반메소드 사용법
public class MethodTest1 {
	
	/*
	 * * 메소드(Method)
	 * - 하나의 기능 단위를 나타내는 코드블록{}
	 * - 기능별 메소드를 정의 후 항상 언제든지 호출해서 쓸 수 있다.
	 * 
	 * 1. 메인메소드: 프로그램의 시작점(Entry Point)를 나타내는 메소드
	 * 			    프로그램 시작시 JVM이 호출해줌
	 * 				프로그램당 한 개 이상은 무조건 갖고 있어야 함!(여러 개여도 됨)
	 * 
	 * 2. 일반메소드: 메인메소드가 아닌 다른 모든 메소드
	 * 				한 번 잘 만들어 두고 메소드의 이름을 갖고 호출해서 코드를 실행함!
	 * 
	 * [표현법]
	 * 접근제한자 (예약어) 반환할값의자료형 메소드명(매개변수(생략가능)) {
	 * 
	 * 
	 * 		실행할코드;
	 * 
	 * 		return 반환값; // 반환할값의자료형이 void면 생략
	 * }
	 */
	
	// 1. 매개변수, 반환값 둘 다 없는 메소드 - 우리가평소에 쓰던거
	public void method1() {
		
		System.out.println("매개변수와 반환값이 둘 다 없는 메소드입니다.");
		
		// 1 ~ 10까지의 총합계를 구하여 출력하는 메소드
		int sum = 0;
		
		for(int i = 1; i <= 10; i++) {
			
			sum += i;
		}
		
		System.out.println("총 합계: " + sum);
		
		// return;
		// > void 메소드인 경우 return; 구문이 생략되어있음
		//   JVM이 return; 구문을 자동으로 작성해주는 꼴
	}
	
	// 2. 매개변수가 없고, 반환값은 있는 메소드(return 필요) - getter, information
	public int method2() {
		
		System.out.println("매개변수는 없고, 반환값은 있는 메소드입니다.");
		
		// 1 ~ 100까지 랜덤한 정수값을 하나 발생시켜 돌려주는 메소드
		int random = (int)(Math.random()*100 + 1);
		return random;
	}
	
	// 3. 매개변수가 있고 반환값은 없는 메소드 - setter
	public void method3(int num1, int num2) {
		
		System.out.println("매개변수가 있고, 반환값이 없는 메소드입니다.");
		
		// num1과 num2의 값을 비교하여 결과를 출력하는 메소드
		// 최솟값: xx
		// 최댓값: xx
		int min = 0; // 둘 중 작은값 보관할 변수
		int max = 0; // 큰값 보관할 변수
		
		// 비교
		if(num1 > num2) {
			
			max = num1;
			min = num2;
		} else {
			
			max = num2;
			min = num1;
		}
		
		System.out.println("최솟값: " + min + "\n최댓값: " + max);
	}
	
	// 4. 매개변수도 있고 반환값도 있는 메소드
	public int method4(int a, int b) {
		
		System.out.println("매개변수도 있고, 반환값도 있는 메소드입니다.");
		
		// 두 매개변수 정수값을 곱한 결과를 리턴
		// int result = a*b;
		// return result; 이렇게 하거나
		
		return a*b; // 이렇게 하거나
	}

}


























