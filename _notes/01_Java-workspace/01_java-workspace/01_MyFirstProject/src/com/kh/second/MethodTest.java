package com.kh.second;

// 풀클래스명: com.kh.second.MethodTest
public class MethodTest { // MethodTest 클래스 영역 시작
	
	// 엔터 -> 자동탭키(들여쓰기): 가독성을 위해 자동 완성 습관화
	
	// 일반메소드들을 만들어 보자
	// > 메인메소드든 일반메소드든 한 클래스 내부에는 여러개의 메소드를 작성할 수 있다!!
	// 한 클래스 안에 무조건 메인메소드를 포함할 필요도 없다!!(프로젝트당 하나만 있으면 됨. 한 클래스 X)
	// 이 클래스 안에 메인메소드가 없기 때문에 실행이 불가능하다!!
	
	public void testPrintA() { // testPrintA 일반메소드 영역 시작
		
		// 들여쓰기(TAP키)
		
		// 이 메소드가 호출 될 순간 실행할 내용 작성!
		
		System.out.println("첫번째 메소드 실행");
		
		testPrintB();
		// testPrintC();
		// 같은 클래스의 메소드를 호출할 경우에는 그냥 메소드명();으로 호출 가능
		
	} // testPrintA 일반메소드 영역 끝
	
	public void testPrintB() { // testPrintB 일반메소드 영역 시작
		
		System.out.println("두번째 메소드 실행");
		
		testPrintC();
		
	} // testPrintB 일반메소드 영역 끝
	
	public void testPrintC() { // testPrintC 일반메소드 영역 시작
		
		System.out.println("세번째 메소드 실행");
		
		//testPrintA();
		// > 만약 testPrintA 메소드를 호출하게 되면
		// A -> B -> C -> A -> B -> C ...
		// 위와 같이 무한반복으로 호출됨!
		// > 오류 문구와 함께 강제종료 되는 상황!
		//   (프로그램은 항상 사용자가 종료하겠다고 하기 전까지 종료되면 안됨)
		
		
		//실행 결과
		//프로그램 시작
		//첫번째 메소드 실행
		//두번째 메소드 실행
		//세번째 메소드 실행
		//무한반복...
		//StackOverflowError 발생하면서
		//프로그램이 비정상적으로 강제종료됨!
		// > Stack은 메모리 공간 유형 중 하나인데 Stack의 크기를 벗어난 경우 에러 발생함
		//   (프로그램은 항상 메모리 공간에서 작동한다! -> 이 때는 프로세스라고 부름)
		
		
	} // testPrintC 일반메소드 영역 끝
	
	
} // MethodTest 클래스 영역 끝
