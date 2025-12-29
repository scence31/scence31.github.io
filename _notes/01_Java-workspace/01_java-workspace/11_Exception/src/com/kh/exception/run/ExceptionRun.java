package com.kh.exception.run;

import java.io.IOException;

import com.kh.exception.controller.A_UncheckedException;
import com.kh.exception.controller.B_CheckedException;

// 예외처리
public class ExceptionRun {
	
	/*
	 * * 명심할 사항: 프로그램은 항상 사용자가 종료를 원할 때만 종료되어야 한다
	 * 
	 * * 에러(오류) 종류
	 * - 시스템에러: 컴퓨터 하드웨어 자체 오류, 개발자가 소스코드로 해결 불가능(심각 에러)
	 * - 컴파일에러: 소스코드의 문법 문제로 발생하는 에러(컴파일 자체가 불가능), 코드로 수정 가능, 빨간 밑줄 -> 해결쉬움
	 * - 런타임에러: 문법 문제가 아닌 프로그램 실행 중에 발생하는 에러, 개발자 or 사용자 잘못
	 * - 논리에러: 문법도 맞고 실행도 잘 되지만 프로그램 의도 상 반대로 작동함(예를 들어 조건식 반대로 작성 등)
	 * 
	 * 예외(Exception): 시스템 에러를 제외한 에러로, 발생시 대부분 프로그램이 비정상적으로 종료됨
	 * 예외처리: 예외가 발생했을 때 이 것을 대비하여 처리하는 방법(프로그램의 비정상적인 종료를 막기 위해)
	 * 
	 * * 예외처리 방법
	 * 1. try ~ catch문 이용 **
	 * 2. throws 키워드 이용
	 * 
	 * 
	 */
	
	// 메인메소드 또한 throws 떠넘길 수잇음 JVM이. 하지마
	public static void main(String[] args) throws IOException {
		
		A_UncheckedException a = new A_UncheckedException();
		
		//a.method1();
		//a.method2();
		//a.method3();
		//a.method4();
		
		B_CheckedException b = new B_CheckedException();
		
		try {
			
			b.method1();
			
		} catch(IOException e) {
			
			System.out.println("예외발생");
		}
		
		System.out.println("프로그램 종료");
		
		

	}

}
