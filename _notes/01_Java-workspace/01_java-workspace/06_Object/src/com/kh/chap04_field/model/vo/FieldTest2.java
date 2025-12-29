package com.kh.chap04_field.model.vo;

// 필드에서 사용가능한 접근제한자 종류
public class FieldTest2 {
	
	/*
	 * 접근제한자 자료형 필드명;
	 * 
	 * * 접근제한자 종류
	 * (+)public
	 * - 어디서든(같은패키지든 다른패키지든 모두) 모두 접근 가능하게 하고싶다.
	 * 
	 * (#)protected
	 * - 같은 패키지면 무조건 접근 가능
	 *   다른 패키지면 원칙상 접근 불가능하나,
	 *   단 "상속" 구조를 가진 클래스에서는 다른 패키지라도 접근 가능(조건부)
	 *   (상속은 다음 단원에서 다룰 것)
	 * 
	 * (~)default
	 * - 오로지 같은 패키지에서만 접근가능하게 하고싶다.
	 * 
	 * (-)private
	 * - 오직 해당 클래스 영역 안에서만 접근가능하게 하고싶다.
	 * 
	 * public --> private 쪽으로 갈수록 접근 범위가 좁아짐
	 * + # ~ -는 클래스다이어그램 상의 표기임
	 */
	
	//필드부
	// > 접근제한자 종류별로 필드 구성
	public String pub = "public";
	protected String pro = "protected";
	/*default*/ String df = "default";
	private String pri = "private";
	
	public static String sta = "FieldTest2";
	
	// 생성자부
	
	// 메소드부
	public static void test() {
		
		System.out.println("하이?");
	}

	
	

}
