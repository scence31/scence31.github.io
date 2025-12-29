package com.kh.chap01_beforeVSafter.after.run;

import com.kh.chap01_beforeVSafter.after.model.vo.Desktop;
import com.kh.chap01_beforeVSafter.after.model.vo.SmartPhone;
import com.kh.chap01_beforeVSafter.after.model.vo.Tv;

public class AfterRun /* extends Object */ {

	public static void main(String[] args) {
		
		// Desktop 객체 생성
		/*
		Desktop d = new Desktop();
		d.setBrand("삼성");
		d.setpCode("d-01");
		d.setpName("게이밍데스크탑");
		d.setPrice(2300000);
		// > Desktop 클래스에 brand ~ price 필드까지 setter 메소드가
		//   코드로 직접 구현되어있지 않지만, 내가 가진 메소드인 것마냥 잘 호출됨!
		
		d.setAllInOne(true);
		*/
		
		Desktop d = new Desktop("삼성", "d-01", "게이밍데스크탑", 2300000, true);
		
		System.out.println(d.getBrand());
		System.out.println(d.getpCode());
		System.out.println(d.getpName());
		System.out.println(d.getPrice());
		System.out.println(d.isAllInOne());
		// > getter 메소드 또한 마찬가지로 내꺼인 것마냥 잘 호출됨!
		
		System.out.println(d.information());
		// > information 메소드 또한 마찬가지로 내꺼인 것마냥 잘 호출됨!
		//   allInOne 필드는 출력이 안됐음
		// > Desktop 클래스 내에 information 재정의했더니 잘 됨(오버라이딩)
		
		// Tv객체 생성
		Tv t = new Tv("엘지", "t-01", "고오급벽걸이티비", 3500000, 100);
		
		System.out.println(t.information());
		
		// SmartPhone 객체 생성
		SmartPhone s = new SmartPhone("애플", "ap-1", "핸폰", 3000000, "SKT");
		
		System.out.println(s.information());
		// 실행하고자 하는 메소드가 자식클래스의 재정의 되어있다면
		// > 그 재정의된 메소드가 우선출력됨(동적바인딩)
		// 단, 재정의된 자식메소드가 없다면
		// > 부모클래스의 원본메소드가 호출됨
		
		/*
		 * * 상속
		 * - 매 클래스마다 중복된 코드(필드, 메소드)들을
		 *   "부모클래스"에 공통 코드로 묶어두고(단 한 번만 정의해두고)
		 *   해당 부모클래스의 코드를 갖다 씀, 즉 코드를 물려받겠다. 상속받겠다.
		 * 
		 * * 상속 장점
		 * 1. 보다 적은 양의 코드로 새로운 클래스들을 매 번 작성 가능함
		 * 2. 중복된 코드를 공통적으로 관리하기 때문에 새로운 코드를 추가하거나 수정할 때 용이
		 * > 프로그램의 "생산성"과 "유지보수"에 크게 기여함
		 * 
		 * * 상속 특징
		 * 1. 자식클래스는 내코드 + 부모클래스의 코드 모두 다 내꺼처럼 쓸 수 있다.
		 *    자식클래스는 부모꺼 내꺼, 내꺼도 내꺼 ㅋ
		 *    부모클래스는 내꺼만 내꺼 ㅠ
		 * 2. 클래스간의 상속은 "다중 상속" 불가능함 ***
		 *    즉, "단일 상속"만 가능(부모클래스는 최대 한 개만 둘 수 있음)
		 *    왜? 부모클래스가 여러 개일 경우, 필드명 또는 메소드명이 중복된다면
		 *    자식 입장에서 어느 부모의 것을 갖다 써야할지 헷갈리기 때문임
		 * 3. 명시되어있지는 않지만, 모든 클래스는 Object 클래스의 후손이다.***
		 * 	  내가 만든 클래스든, 남이 만들어준 클래스든 간에 자바의 모든 클래스에 해당됨
		 *    > Object: 자바의 "최상위 클래스", 모든 클래스의 부모 역할
		 *     			모든 자바 클래스들은 즉, Object 클래스에서 제공하는 유용한 메소드들을
		 *              언제든지 가져다 쓸 수 있다!
		 *              또한, 그 Object 클래스로부터 물려받은 유용한 메소드 중
		 *              내 마음에 안드는 것이 있다면 내 입맛대로 재정의(오버라이딩)해서 쓸 수 있음
		 *              
		 * * Object 클래스에서 제공하고 있는 대표적인 유용한 메소드들
		 * - hashCode() : 주소값을 10진수로 리턴.
		 *    			  사실 hashCode는 Object 클래스에 있는 메소드임
		 *    			  그동안 그냥 호출 가능했던 이유는
		 *    			  Object 클래스는 모든 클래스의 부모클래스기 때문
		 * - equals() : 두 주소값을 가지고 동등비교를 해주는 메소드
		 *    
		 */
		
		System.out.println(d);
		System.out.println(t);
		System.out.println(s);
		
		System.out.println(d.equals(s)); // d == s 주소값 일치? false
		
		
		
		
		
		
		

	}

}
