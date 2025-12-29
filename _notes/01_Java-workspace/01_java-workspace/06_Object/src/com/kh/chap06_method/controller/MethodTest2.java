package com.kh.chap06_method.controller;

// static 메소드
public class MethodTest2 {
	
	// 1.
	public static void method1() {
		
		System.out.println("매개변수와 반환값이 모두 없는 메소드");
	}
	
	// 2.
	public static String method2() {
		
		return "매개변수는 없지만 반환값이 있는 메소드";
		
	}
	
	// 3.
	public static void method3(String name, int age) {
		
		System.out.println(age + "살의 " + name + "님 환영합니다^^");
	}
	
	// 4.
	public static int method4(int num1, int num2) {
		
		return num1 + num2;
	}
}
