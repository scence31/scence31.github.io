package com.kh.chap02_string.controller;

//String 클래스 특징
public class A_StringPoolTest {
	
	/*
	 * * String 클래스(java.lang.String)
	 * - 자바에서 문자열을 다루는 용도의 클래스
	 * - 문자열 리터럴을 곧바로 대입하거나 new 구문을 통해 객체생성이 가능하다.
	 * 
	 * * String은 "불변클래스다."(변하지 않는 클래스)
	 *  > 상수개념은 아니고, 기존 값이 담겨있던 공간에서 수정이 되지 않는다는 뜻임
	 */
	
	// 1. 생성자를 통해 문자열 담기
	public void method1() {
		
		String str1 = new String("hello"); // String str1 = "hello";
		String str2 = new String("hello");
		
		System.out.println(str1 == str2); // false (주소값을 비교한 것이라서)
		
		System.out.println(str1.toString());
		System.out.println(str2);
		// > String 클래스의 toString() 메소드의 경우
		//   실제 담겨있는 문자열을 반환하도록 오버라이딩 되어있음!
		
		System.out.println(str1.equals(str2)); // true (내용물 비교)
		// > 원래 Object의 equals() 메소드는 두 객체 간 주소값을 동등비교하는 것임
		//   그런데 String 클래스의 equals는 실제 담긴 내용을 기준으로 되도록 오버라이딩 됨
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		// 원래 Object의 hashcode 메소드는 주소값 --> 십진수인데
		// String 클래스의 십진수는 실제 담긴 내용을 기준으로 십진수 되도록 오버라이딩 됨
		
		// String은 클래스로 정의돼있지만 변수 성격이 강해서 내용물 기준으로 동작하도록 오버라이딩
		
		// 주소값을 알고싶다면?
		// System.identityHashCode(참조변수명);
		System.out.println(System.identityHashCode(str1)); // 6647~
		System.out.println(System.identityHashCode(str2)); // 8045~
		// 주소값 다름
		
	}
	
	// 2. 문자열 리터럴로 객체생성
	public void method2() {
		
		String str1 = "hello";
		String str2 = "hello";
		System.out.println(str1 == str2); // true?; (주소값 비교)
		
		/*
		 * * 문자열 객체 생성시 리터럴로 한다면
		 *   메모리의 Heap 영역의 String Poll(상수풀) 공간에 문자열 내용물이 올라가게 됨
		 *   (생성자 new를 사용한다면 일반 Heap 용역에 올라감)
		 * > String Pool 특징: 내부에 동일한 내용의 문자열이 존재 불가능
		 * 
		 * 해시코드 동일, 아이덴티티해시코드 동일, equals 동일
		 */
		
	}
	
	// 3. 불변클래스: 변하지 않는 클래스
	public void method3() {
		
		String str = "hello";
		System.out.println(System.identityHashCode(str)); // 664740647
		
		str = "goodbbye";
		System.out.println(System.identityHashCode(str)); // 804564176
		// 주소값 다르게 나옴
		
		str += "abc"; // str = str + "abc";
		System.out.println(System.identityHashCode(str)); // 951007336
		
		// new 구문 사용해도 마찬가지로 불변클래스 적용
		
		/*
		 * * 불변클래스라고 해서 수정이 안되는 것은 아님
		 *   그 값이 담긴 기존의 자리에서 수정이 안되는 개념일 뿐임
		 * > 매 번 새로운 주소값을 참조하게 됨
		 * > 기존의 연결이 끊긴 문자열들은 일정 시간 지나면 가비지컬렉터가 정리해줌 
		 */
		
		/*
		for(int i = 0; i < 50000; i++) {
			
			str += i;
		}
		*/
		
		// 위의 코드처럼 빈번하게 문자열의 내용이 변경된다면
		// 메모리 공간의 할당이 계속 일어나서 성능저하이슈 발생
		
	}
	
	// 4. 불변클래스 성질을 보완하는 클래스
	public void method4() {
		
		// * 기존의 String 객체를 대체하는 StringBuilder, StringBuffer 객체
		/*
		// 1) StringBuilder 사용법
		StringBuilder sb = new StringBuilder(); // 매개변수 비어있으면 --> ""
		System.out.println("sb: " + sb);
		System.out.println(sb.hashCode()); // 468121027
		
		// 문자열을 이어주는 메소드
		// [표현법]
		// sb.append(연이을문자열);
		sb.append("Hello"); // "" + "Hello"
		System.out.println("sb: " + sb);
		System.out.println(sb.hashCode()); // 468121027 위와 동일함
		// 주소값 일치, 기존 공간에서 내용만 바뀜
		*/
		
		// 2) StringBuffer 사용법
		StringBuffer sb = new StringBuffer();
		System.out.println("sb: " + sb);
		System.out.println(sb.hashCode()); // 468121027
		
		sb.append("Hello");
		System.out.println("sb: " + sb); // Hello
		System.out.println(sb.hashCode()); // 468121027 위와 동일함
		// 주소값 일치, 기존 공간에서 내용만 바뀜		
		
		/*
		for(int i = 0; i < 50000; i++) {
			
			sb.append(i);
		}
		*/
		
		// * StringBuilder, StringBuffer 사용하면
		//   기존 그 공간에서 값 수정이 그대로 발생함. 새로운 곳이 할당되지 않음
		// > 즉 불변 아님, 빈번하게 문자열 내용 바뀔경우 사용하면 좋음
		
		// 빌더, 버퍼 차이점
		// 스트링빌더: 속도가 상대적 더 빠르지만/ 두 개 이상 연산이 한번에 발생하면 동시에 처리 불가
		// 스트링버퍼: 속도가 상대적 더 느르지만/ 두 개 이상 동시에 처리 가능( == 멀티쓰레드)
		
		
		
	}
	
	
}
























