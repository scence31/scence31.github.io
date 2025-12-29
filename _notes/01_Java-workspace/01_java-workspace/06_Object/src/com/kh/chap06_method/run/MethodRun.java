package com.kh.chap06_method.run;

import com.kh.chap06_method.controller.MethodTest1;
import com.kh.chap06_method.controller.MethodTest2;

public class MethodRun {

	public static void main(String[] args) {
		
		// 1. MethodTest1
		MethodTest1 m1 = new MethodTest1();
		
		m1.method1();
		
		System.out.println("--------------------------");
		
		// int a = m1.method2(); // 변수(a)에 random값 넣고 호출하면 가능
		// System.out.println("랜덤값: " + a);
		// 그냥 호출구문만 써도 메소드가 잘 돌아가짐나
		// 돌려받은 반환값을 가지고 후처리같은 것을 하고싶다면
		// 항상 메소드 호출 구문 앞에 변수 선언문과 같이 써야한다!
		// ex) sc.nextLine();
		//     String str = sc.nextLine();
		
		System.out.println("랜덤값: " + m1.method2());
		// > 굳이 위의 int a = m1.method2(); 구문처럼 하지 않고
		// 이렇게 출력문 안에서 바로 호출해서 돌려받은 반환값을 곧바로 출력 가능
		
		System.out.println("-----------------------------");
		
		m1.method3(10, 20);
		// 매개변수가 있는 메소드를 호출할 경우에는
		// 항상 순서, 종류, 개수를 다 맞춰서 값으로 전달해준다!
		// 호출시 넘겨주는 값 == 인자
		// 그 인자값을 받아주는 변수 == 매개변수
		
		System.out.println("------------------------------------");
		
		// m1.method4(42, 2);
		
		// int result = m1.method4(42, 2);
		// System.out.println(result);
		
		System.out.println("곱은: " + m1.method4(42, 2));
		// > 반환값이 없는 메소드는 보통 해당 메소드 내에서
		//   처리 후 처리결과를 출력하는 것까지 역할로 잡아서 작성
		// > 반환값이 있는 메소드는 물론 출력하는 역할로 쓸 수 있지만
		//   이 결과를 반환값으로 돌려줘서 그 후에 유의미한 처리를 이어가게끔 유도해서 작성
		
		System.out.println("=================================");
		
		// 2. MethodTest2
		
		// MethodTest2 m2 =new MethodTest2();
		// > static 메소드는 굳이 객체 생성할 필요 없음.
		//   바로 클래스명.메소드명(); 호출하면 됨
		MethodTest2.method1();
		
		System.out.println("------------------------------------");
		
		System.out.println(MethodTest2.method2());
		
		System.out.println("------------------------------------");
		
		MethodTest2.method3("홍길동", 20);
		MethodTest2.method3("김말똥", 30);
		
		System.out.println("------------------------------------");
		
		System.out.println(MethodTest2.method4(10, 9));
		System.out.println(MethodTest2.method4(20, 35));

		
		
		

	}

}
