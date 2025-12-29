package com.kh.second.run; //패키지 선언부

// import 선언부
// > 다른 패키지의 클래스를 가져다 쓰고 싶을 때, 가져다 쓰겠다고 선언하는 부분
//   (import: 가져오다, 포함시키다)
//[표현법]
//import 가져오고자하는클래스의풀클래스명;
import com.kh.second.MethodTest;
// > com.kh.second 패키지의 MethodTest 클래스의 코드를 가져다 쓰겠다.
import com.kh.first.HelloEveryone;
// > 

//클래스 선언부
// 풀클래스명: com.kh.second.run.Run
public class Run { // Run 클래스 영역 시작
	
	//메인메소드 만들기
	
	public static void main(String[] args) { //메인메소드 영역 시작
		
		System.out.println("프로그램 시작");
		
		/*
		 * * 메소드 호출
		 * - 메소드의 이름을 불러서 실행시켜주는 행위
		 * 
		 * [표현법](=문법)
		 * 메소드명();
		 * > 같은 클래스에 있는 메소드를 호출할 경우
		 *   단순히 메소드명만 호출해 주면 된다!!
		 * 
		 * > 다른 클래스에 있는 메소드를 호출하고 싶은 경우에는?
		 */
		
		//testPrintA();
		// > 오류 testPrintA() is undefined for the type Run
		//   Run 클래스 안에 testPrintA 메소드가 정의되어있지 않음
		//   (호출불가)
		
		// 1. 호출할 메소드가 있는 클래스를 "생성"(new)이라는 것을 해야한다.
		// [표현법](=문법)
		// 클래스명 대변할이름(임의?) = new 클래스명();
		
		// - 우리가 호출할 testPrintA 메소드는 MethodTest 클래스에 있음
		// MethodTest mt = new MethodTest();
		// > 오류 MethodTest cannot be resolved to a type
		//   MethodTest라는 클래스를 찾지 못하고 있음
		// > 기본적으로 코드 내에서 클래스명을 기술할 경우
		//   현재 같은 패키지 내에서 해당 클래스를 찾음!!(같은 폴더가 아님!!)
		//   (지금은 다른 패키지에 있는 클래스기 때문에 찾지 못하는 것)
		
		// 해결방법1)
		// 해당 클래스가 정확히 어떤 패키지에 있는 클래스인지 풀클래스명으로 명시하기
		// com.kh.second.MethodTest mt = new com.kh.second.MethodTest();
		
		// 해결방법2)
		// 그냥 클래스명만으로 가지고 작성하되,
		// 이 클래스가 어떤 패지키에 속해있는지 선언문(import)을 추가적으로 작성하기
		MethodTest mt = new MethodTest();
		// > 주로 쓰이는 방법!!(중괄호 부분 가독성 높음)

		// 2. 생성 후 이제 해당 클래스에 있는 메소드를 호출(실행)
		// > 1단계에서 생성한 "대변할이름"을 통해서 호출한다고 보면 됨!!
		//[표현법]
		//대변할이름.메소드명();
		/*
		mt.testPrintA();
		mt.testPrintB();
		mt.testPrintC();
		*/
		// > 대변할이름은 한 번 생성해두면 같은 중괄호 영역 안에서는 재활용 가능!
		
		// testPrintA 메소드 하나만 호출하되 A, B, C 모두 출력하는 결과를 보고싶다면?
		mt.testPrintA();
		
		// *메인메소드 또한 개발자인 내가 직접 호출 가능하다.
		// > 메인메소드는 프로그램 시작시 JVM에 의해 자동으로 호출되지만,
		//   개발자인 내가 직접 소스코드를 이용해 호출도 가능하긴 함(권장하지는 않음)
		
		// - 어제 만든 HelloEveryone 클래스의 메인메소드 호출
		// > 메인메소드는 앞에 static 키워드가 붙기 때문에
		//   그 클래스를 "대변할이름"을 만들지 않고 바로 호출 가능
		//[표시법]
		//클래스명.메소드명();
		
		HelloEveryone.main(null);
		// > 가능은 하나, 굳이 이렇게 호출해서 사용하지는 않음!
		
		
		System.out.println("프로그램 종료");
		
		
	} // 메인메소드 영역 끝

} // Run 클래스 영역 끝
