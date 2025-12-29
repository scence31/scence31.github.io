package com.kh.exception.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Checked Exception 다루는 방법
public class B_CheckedException {
	
	/*
	 * * Checked Exception은 반드시 예외처리구문을 작성해야하는 예외들
	 * - 즉, 예측불가한 곳에서 언제 어디서 발생할지 모르기 때문에 미리 반드시 작성해야함
	 * 	 주로 외부매체와 어떤 입출력 시 발생
	 * 
	 */
	
	public void method1() throws IOException {
		
		method2();
	}
						// thorws 떠넘길예외클래스명
	public void method2() throws IOException {
		
		// Checked Exception이 발생할 법한 구문

		// Scanner 말고 또 비슷한 객체 있음
		// 단, 문자열로만 입력받을 수 있음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("아무 문자열 입력해보세요: ");
		// String str = br.readLine();
		// 이 메소드 호출시 IOException 발생할 수 있음(컴파일에러)
		// 즉, 문법 상 예외처리구문을 무조건 써라
		
		// 1. try ~ catch문 이용
		
		/*
		try {
			
			String str = br.readLine();
			
			System.out.println("문자열의 길이:" + str.length());
		
		} catch(IOException e) {
			
			System.out.println("예외가 발생됨");
			// 언제 예외가 발생할지 모름
		}
		*/
		
		// 2. throws 키워드 이용
		/*
		 * * throew 키워드
		 * - 지금 당장 예외를 처리하지는 안겠다.
		 * - 다른 곳에서 나 대신 예외를 처리하도록 떠넘기겠다.(위임)
		 * 
		 * > method2에서 throws 키워드를 이용해서 예외처리할 예정
		 *   즉, method2에서 try~catch문을 직접 쓰지는 않겠다.
		 *   나(method2)를 호출했던 곳에서 나 대신 try~catch문을 이용해서 나 대신에
		 *   예외처리를 할 수 있도록 떠넘기겠다(위임하겠다)
		 *   
		 * * Checked 계열은 메소드 정의부에 throws로 이미 예외 떠넘기고 있음

		 */
		
		String str = br.readLine();
		System.out.println("문자열의 길이: " + str.length());
		
		System.out.println("프로그램 종료");
		
	}

}












