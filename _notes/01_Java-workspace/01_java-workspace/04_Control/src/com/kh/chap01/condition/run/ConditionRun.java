package com.kh.chap01.condition.run;

import com.kh.chap01.condition.A_If;
import com.kh.chap01.condition.B_Else;
import com.kh.chap01.condition.C_Switch;

public class ConditionRun {

	/*
	 * * 프로그램의 기본 흐름
	 * - "위에서 아래로" 코드가 순차적으로 실행됨
	 * - 하지만 이 순차적인 흐름을 바꾸고자 할 때 "제어문" 이라는 것을 통해서 직접 제어 가능
	 * 
	 * [종류]
	 * 1. "선택적"으로 실행하는 선택문
	 * > 조건문(단독 if문, if-else문, if-else if문, switch문)
	 *  
	 * 2. "반복적"으로 실행하는 반복문
	 * > 반복문(for문, while문, do-while문)
	 * 
	 * 3. 이 외의 흐름을 제어하는 구문들
	 * > 분기문(break문, continue문)
	 */
	
	/* * 조건문
	 * - "조건식"을 통해 참이냐 거짓이냐를 판단햇서 해당 조건식 결과가 참(true)일경우
	 *   그에 해당하는 구문만 "선택적"으로 실행하도록 해주는 구문
	 * - 조건식의 결과는 항상 논리값(true or false)이 나올 수 있게끔 제시해야함!!
	 * - 보통 조건식에는 비교연산자(대소, 동등), 논리연산자(&&, ||), 논리부정연산자(!) 등
	 *   연산의 결과값이 boolean 타입으로 나오는 연산자를 주로 사용한다!!
	 * - 조건문은 크게 if문 계열과, switch문으로 나뉘게 됨
	 * 
	 * [종류]
	 * 1. if문 계열: 조건식을 내가 직접 기술 가능한 구문
	 * - 단독 if문
	 * - if-else문
	 * - if-else if문 
	 * 
	 * 2. switch문 계열: 조건식을 내가 직접 기술하지 않는 구문(내부적으로 비교연산 같은 것이 알아서 발생함)
	 * 
	 */
	
	
	
	public static void main(String[] args) {
		
		A_If a = new A_If();
		
		//a.method1();
		//a.method2();
		//a.method3();
		//a.method4();
		//a.method5();
		
		B_Else b = new B_Else();
		
		//b.method1();
		//b.method2();
		//b.method3();
		//b.method4();
		//b.method5();
		//b.method6();
		//b.method7();
		//b.method8();
		//b.method9();
		//b.method10();
		//b.method11();
		//b.method12();
		b.method13();
		
		
		
		C_Switch c = new C_Switch();
		
		//c.method1();
		//c.method2();
		//c.method3();
		
		
		
		
		
	}

}
