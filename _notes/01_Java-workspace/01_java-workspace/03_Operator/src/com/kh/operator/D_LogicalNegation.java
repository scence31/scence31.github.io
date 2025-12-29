package com.kh.operator;

//논리부정연산자
public class D_LogicalNegation { // D_LogicalNegation 클래스 영역 시작
	
	/*
	 * * 논리부정연산자
	 * - 단항연산자로서, 논리값(true or false)을 반대로 부정시켜주는 역할을 하는 연산자
	 * - 즉, true --> false, false --> true로 바꿔줌
	 * 
	 * [표현법]
	 * !논리값; --> 반대 논리값으로 변환
	 */
	
	public void method1() {
		
		System.out.println("true의 부정: " + !true);
		System.out.println("false의 부정: " + !false);
		
		boolean b1 = true;
		boolean b2 = !b1;
		
		System.out.println("b1: " + b1); // true
		System.out.println("b2: " + b2); // false
		// 즉, 논리부정연산자는 해당 논리값을 반대로만 변환함
		// > 증감연산자처럼 자신한테 다시 "대입" 안함!!
		
	}
	
	
} // D_LogicalNegation 클래스 영역 끝
