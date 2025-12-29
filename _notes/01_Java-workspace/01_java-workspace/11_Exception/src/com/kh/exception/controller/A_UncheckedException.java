package com.kh.exception.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

// Unchecked Exception 다루는 방법
public class A_UncheckedException {
	
	/*
	 * * 예외의 두 가지 분류
	 * 1. Unchecked Exception: 충분히 예측 가능한 상황에 대한 오류
	 * 						  (RuntimeException클래스의 자식 클래스들로 구성)
	 * 
	 * 2. Checked Exception: 예측 불가능한 상황에 대한 오류
	 * 
	 * * Unchecked Exception 종류(즉 RuntimeException클래스의 자식클래스들)
	 * 
	 * - ArrayIndexOutOfBoundsException: 배열의 부적절한 인덱스로 접근할 때 발생하는 예외
	 * - NegativeArraySizeExcecption: 배열의 크기를 음수로 지정할 경우 발생하는 예외
	 * - ClassCastException: 허용할 수 없는 형변환이 진행될 경우 발생~
	 * - NullPointerException: 참조변수가 null임에도 불구하고 접근하려고 할 때 발생~
	 * - ArithmeticException: 나누기 연산에서 0으로 나눌 때 발생~
	 * - ...
	 * 
	 * > 이러한 RuntimeException 관련된 예외들은 개발자 입장에서 충분히 예측 가능한 상황이기에
	 *   굳이 예외처리구문 쓰지 않고 조건문 사용해서 충분히 처리 가능
	 *   - 조건문: 예외 애초에 예방
	 *   - 예외처리: 예외 수습
	 */
	
	// Scanner 객체를 이 클래스의 모든 메소드에서 이용하게끔 전역변수로 생성
	private Scanner sc = new Scanner(System.in);
	
	
	public void method1() {
		
		// ArithmeticException(0 나눌 때 ~)
		// 사용자에게 두 개의 정수값 입력받아 나눗셈한 연산결과를 출력
		
		System.out.print("첫번째 정수: ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("두번째 정수(0 제외): ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		// System.out.println("나눗셈 연산 결과: " + (num1 / num2));
		// 사용자가 num2에 0을 입력한 경우 예외 발생, 비정상 종료
		
		// 해결방법1)
		// 조건문을 이용해서 치러기능 if(num2 =! 0) { ~~
		
		// 해결방법2)
		// 예외처리구문 사용하는 방법
		// 대놓고 예외처리구문을 사용하는 방법(예외발생 대비 실행할 내용 정의)
		
		/*
		 * * try ~ catch문
		 * [표현법]
		 * try{
		 * 		
		 * 		예외가 발생될 법한 구문;
		 * } catch(발생될예외클래스명 변수명) {
		 *	 
		 * 		해당 예외가 발생했을 때 실행할 구문;	
		 * }
		 * 
		 */
		
		try { 
			
			System.out.println("나눗셈 연산 결과: " + (num1 / num2));
			
			
		} catch(ArithmeticException e) {
			
			System.out.println("0으로 나눌 수는 없습니다..");
			// e.printStackTrace();
			// > 오류가 어디서 발생했는지 추적하여 어디서 발생한 것인지 정보를 디테일하게 볼 수 있음
			
		}
		
		System.out.println("프로그램 종료");
			
	}
	
	public void method2() {
		
		// method1 보완
		System.out.print("정수입력(0제외): ");
		
		try {
		
		int num = sc.nextInt();
		// 정수 이외의 값: INputMismatchException 발생(런타임익셉션의 자식클래스)
		// > Unchecked Exception으로 분류한다.
		// 단, 사용자가 키보드로 무엇을 입력할지 몰라서 if문으로 막기 힘듦 좀 그럼(특이케이스)
		
		sc.nextLine();
		
		System.out.println("나눗셈 연산 결과: " + (10 / num));
		// 0 입력하면 아리스메틱 오류
		
		} catch(InputMismatchException e) {
			
			System.out.println("정수로 제대로 입력해야합니다.");
			
		} catch(ArithmeticException e) {
			
			System.out.println("0으로 나눌 수 없음");
		}
		// 예외가 한번에 여러개 발생할 가능성 있을 경우
		// try 블록으로 한번에 묶고 catch로 블록을 종류별로 여러 개 작성 가능(다중catch블록)
		
		System.out.println("프로그램 종료");
		
	}
	
	public void method3() {
		
		// ArrayIndexOutOfBoundsException
		// > 배열의 부적절한 인덱스로 직접접근하려고 할 때
		
		// NegativeArraySsizeException
		// > 배열의 크기를 음수로 지정할 때 발생
		
		System.out.print("배열의 크기: ");
		
		int size = sc.nextInt();
		sc.nextLine();
		
		/*
		// 정수가 아닌 다른 값 입력시 InputMismatchException 발생
		
		int[] arr = new int[size];
		// > 배열의 크기를 음수로 입력했을 때 NegativeArraySizeException 발생
		
		System.out.println("100번 인덱스 값: " + arr[100]);
		// > 배열의 크기를 101칸 미만으로 지정했을 때 ArrayIndexoutOfBoundsException 발생
		
		System.out.println("프로그램 종료");
		*/
		

		
		/*
		if(size > 0) { // 배열 크기가 양수일 경우 -배열생성가능
			
			int[] arr = new int[size];
			
			if(size >= 101) { // 배열크기 101 이상이면 100번 인덱스 접근 가능
				
				System.out.println("100번 인덱스 값: " + arr[100]);
				
			} else {  // 101 미만이면 100번 인덱스 접근 불가능
				
				System.out.println("부적절한 인덱스로 접근할 수 없습니다.");
				
			}
			
		} else { // 양수x, 배열생성 불가능
			
			System.out.println("배열의 크기로 음수 불가함");
		}
		*/
		
		
		
		/*
		try {
			
			int[] arr = new int[size];
			
			System.out.println("100번 인덱스의 값: " + arr[100]);
			
		} catch(NegativeArraySizeException e) {
			
			System.out.println("배열의 크기로는 음수를 제시할 수 없습니다.");
			
		} catch(ArrayIndexOutOfBoundsException e) {
			
			System.out.println("부적절한 인덱스로 접근할 수 없습니다.");
			
		}
		*/
		
		
		
		// 처리해야하는 catch 블록의 개수가 늘어난다면?
		// > 일일이 쓰는게 원칙이지만 귀찮음
		
		/*
		try {
			
			int[] arr = new int[size];
			
			System.out.println("100번 인덱스읭 값: " + arr[100]);
			
		} catch(RuntimeException e) {
			// 다중 catch 블록의 경우, 부모타입(상위)의 클래스 catch 블록 하나 만으로 다형성 적용 가능
			// 근데 무슨 예외인지는 모름
			
			System.out.println("예외 발생했지만 정확한 원인은 모름");
		}
		*/
		
		
		try {
			
			int[] arr = new int[size];
			
			System.out.println("100번 인덱스값: " + arr[100]);
			
		} catch(NegativeArraySizeException e) {
			
			System.out.println("배열의 크기로 음수는 불가합니다.");
			
		} catch(RuntimeException e) {
			
			System.out.println("예외가 발생하긴 했지만 원인은 모름");
		}
		// 다중 catch 블록 및 다형성 활용시 작성 순서가 중요함.
		// > 넓은 개념이 나중에 출력되도록 하자.(Unreachable 오류)
		
		System.out.println("프로그램 종료");
	}
	
	public void method4() {
		
		System.out.print("숫자로 변환할 문자열(정수형식): ");
		String str = sc.nextLine();
		
		// "123" --> 123 (파싱이용)
		// 힌트)
		// int num = Integer.parseInt("123"); // 됨
		// int num = Integer.parseInt("1abc.1"); // 안됨
		// > NumberFormatException 오류 발생
		
		// 이 점을 참고해서 해당 문자열을 정수로 변환할 수 있는지 검사하는 프로그램(try ~ catch문)
		
		
		try {
			
			// 입력받은 값을 정수로 변환(파싱)하려는 시도
			int num = Integer.parseInt(str);
			
			System.out.println("변환할 수 있습니다.");
			System.out.println("결과: " + num);
			
		} catch(NumberFormatException e) {
			
			// 파싱 제대로 안되어 오류발생한경우 - 문자열을 정수형으로 변환할 수 없음
			System.out.println("변환할 수 없음");
		}
		
		
		System.out.println("프로그램 종료");
	}
	
	/*
	 * * RuntimeException 관련 예외들은
	 * - 충분히 조건문으로 해결가능. 예방개념. 직접 조건문 구문으로 핸들링(처리) 가능함.
	 * - 예외처리구문으로도 해결가능. 예외 발생 대비하여 실행할 내용을 정의해 두는, 수습하는 개념
	 * 
	 * 예측이 가능한상황: Unchecked Exception
	 * > 조건문으로 최대한 해결하는 것을 권장
	 * 
	 * 예측 불가능한상황: Checked Exception
	 * > 예외처리구문 쓸 수밖에 없음
	 * 
	 */
	

}



























