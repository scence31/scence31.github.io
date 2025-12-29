package com.kh.operator;

//증감연산자
public class B_InDecrease { // B_InDecrease 클래스 영역 시작
	
	/*
	 * * 증감연산자
	 * - 단항연산자로서(이항x), 한 번에 값을 1씩 증가시키거나 또는 1씩 감소시키는 역할의 연산자
	 * - 1씩 증가 or 감소시키는 연산 후 다시 자기 자신한테 "대입" 하는 것까지가 역할임!
	 * 
	 * [종류]
	 * ++: 값을 1씩 "증가" 시키는 연산자
	 *     ++값(전위연산자), 값++(후위연산자)
	 * --: 값을 1씩 "감소" 시키는 연산자
	 *     --값(전위연산자), 값--(후위연산자)
	 * 
	 * <시점 차이>
	 * (증감연산자)값: 전위연산자, 다른 연산을 하기 전에 먼저 증감이 발생
	 * 값(증감연산자): 후위연산자, 다른 연산을 다 하고 마지막에 증감이 발생
	 */
	
	public void method1() {// method1 메소드 영역 시작
		
		int num = 10;
		System.out.println("num: " + num); // 10
		
		num++; // 증가, 후위연산자
		// > num 변수값에 1을 증가시킨 후
		//   그 증가시킨 값을 num 변수에 다시 "대입" 하겠다.
		//   즉, num = num+1; <-- 이 구문과 같은 의미임
		
		System.out.println("변경된 num: " + num); // 11
		
		num--; // 감소, 후위연산자
		// > num 변수값에 1을 감소시킨 후
		//   그 감소시킨 값을 num 변수에 다시 "대입" 하겠다.
		//   즉, num = num-1; <-- 이 구문과 같은 의미임
		
		
		System.out.println("최종 num: " + num); // 10
		
		
	}// method1 메소드 영역 끝
	
	public void method2() { // method2 메소드 영역 시작
		
		//전위 연산
		// > 증감이 제일 먼저 발생하겠다.
		int a = 10;
		int b = ++a;
		// > a 값을 먼저 1 증가시킨 후 a 변수에 다시 대입한 후
		//   그 a 변수값을 b 변수에 대입하겠다.
		
		System.out.printf("a: %d, b: %d \n", a, b); // 11 / 11
		
		// 후위 연산
		// 증감 마지막에 발생
		int c = 10;
		int d = c++; // 후위 연산은 일단 ++ 없는 셈치고 나중에 하면 알아보기 쉽긴 함
		// > d 변수에 c 변수의 값을 먼저 대입
		//   c 변수값을 1 증가시킨 후 c에 다시 대입
		
		System.out.printf("c: %d, d: %d \n", c, d); // 11 / 10
		
		
		
	} // method2 메소드 영역 끝
	
	
	public void method3() { // method3 메소드 영역 시작
		
		int num1 = 20;
		int result1 = num1++ *3;
		
		System.out.println("num1: " + num1); // 21
		System.out.println("restult1: " + result1); // 60
		
		// ------------------
		
		int num2 = 20;
		int result2 = ++num2 *3;
		
		System.out.println("num2: " + num2); // 21
		System.out.println("result2: " + result2); // 63
		
		
	} // method3 메소드 영역 끝
	
	public void method4() { // method4 메소드 영역 시작
		
		
		int a = 5;
		int b = 10;
		int c = (++a) + b;
		int d = c/a;
		int e = c%a;
		int f = e++;
		int g = (--b) + (d--);
		int h = 2;
		int i = a++ +b/(--c/f)*(g-- -d)%(++e +h);
		// 위에서부터 아래로 진행하면 됨(!!후위연산자는 일단 제거 후 마지막에!!)
		// 마지막까지 진행한 후 a, b, c, ... 최종값임
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println("d: " + d);
		System.out.println("e: " + e);
		System.out.println("f: " + f);
		System.out.println("g: " + g);
		System.out.println("h: " + h);
		System.out.println("i: " + i);
		
		
		
		
		
	} // method4 메소드 영역 끝
	
	

} // B_InDecrease 클래스 영역 끝
