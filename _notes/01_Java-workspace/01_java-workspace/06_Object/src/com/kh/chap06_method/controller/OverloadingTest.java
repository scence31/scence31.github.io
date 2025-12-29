package com.kh.chap06_method.controller;

// 메소드 "오버로딩" 개념
public class OverloadingTest {
	
	/*
	 * * 메소드 오버로딩(Overloading)
	 * - 한 클래스 내에 "같은 메소드명"으로 여러 개의 메소드를 정의할 수 있는 규칙
	 * - 원칙적으로 한 클래스 내에 동일한 이름의 메소드 여러 개를 작성할 수 없다!
	 *   (단, 어떤 규칙을 지킨다면 가능함!!)
	 *   
	 * * 메소드 오버로딩의 성립조건
	 * 1. 메소드명은 동일해야한다.
	 * 2. 매개변수의 자료형, 개수, 순서가 하나라도 다르게 작성되어야 한다.
	 *    ex) 어제 배운 매개변수생성자가 메소드 오버로딩의 대표적인 예시임!
	 * 3. 단, 매개변수명, 접근제한자 종류, 반환형은 메소드 오버로딩에 영향을 주지 않음
	 * 
	 */
	
	public void test() {
		
		// * 메소드 오버로딩이 잘 되어있는 예시
		// 1. 매개변수 생성자
		// 2. 출력메소드들
		System.out.println("안녕하세요"); // 매개변수 1개(String)
		System.out.println(100); // 매개변수 1개(int)
		System.out.println(true); // 매개변수 1개(boolean)
		System.out.println(); // 매개변수 0개
		
		System.out.printf("%d", 10); // 매개변수 2개(String, int)
		System.out.printf("%f", 0.1); // 매개변수 2개(String, double)
		System.out.printf("%s %d", "안녕", 2); // 매개변수 3개(String, String, int)
		System.out.printf("%f %c %d", 0.12, 'A', 11); // 매개변수 4개(String, double, char, int)
		
	}
	
	public void test(int a) { // 오버로딩 성립
		
		
	}
	
	public void test(int a, String s) { // 오버로딩 성립
		
		
	}
	
	public void tset(String s, int a) { // 오버로딩 성립 
		
		
	}
	
	public void test(int a, int b) { // 오버로딩 성립
		
		
	}
	
	// > 매개변수의 자료형, 종류, 개수, 순서 중 하나라도 다르면 오버로딩 성립됨
	
	/*
	public void test(int c, int d) { // 오버로딩 성립 X
		// 결국 매개변수명만 다르고 바로 위의 메소드와
		// 매개변수의 개수, 종류, 순서가 모두 일치하기 때문에 성립 X
		
	}
	*/
	
	// > 매개변수명은 단순 이름이기 때문에 오버로딩에 영향을 주지 않음!
	//   (이름은 붙이는 사람 마음이기 때문)
	
	public void test(int a, int b, String s) { // 오버로딩 성립
		
	}
	
	/*
	public int test(int a, int b, String s) { // 오버로딩 성립 X
		// 반환형(void부분)이 다르지만
		// 매개변수의 종류, 개수, 순서가 모두 일치하기 때문
	}
	*/
	
	// > 반환형은 호출할 때 반환값이 있든 없든 똑같이 호출할 수 있기 때문에 
	//   오버로드에 영향을 주지 않음
	
	/*
	private void test(int a, int b, String s) { // 오버로딩 성립 X
		// 접근제한자가 달라도 성립 x
		// 매개변수의 종류, 개수, 순서가 모두 일치하기 때문
	}
	*/
	
	// > 호출할 수 있는 곳의 범위가 다르더라도
	//   결국 호출구문 자체는 똑같기 때문에 오버로딩에 영향 X

}














