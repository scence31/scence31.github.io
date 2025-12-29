package com.kh.chap04_field.run;

import com.kh.chap04_field.model.vo.FieldTest2;
import com.kh.chap04_field.model.vo.FieldTest3;

public class FieldRun {

	public static void main(String[] args) {
		
		// 1. FieldTest1
		/*
		FieldTest1 f1 = new FieldTest1();
		// 객체 생성시 global 멤버변수만 생성(할당)
		
		f1.test(10);
		// 해당 메소드 호출시 매개변수 생성
		
		// System.out.println(local);
		// System.out.println(num);
		// test 메소드는 이미 시점이 끝나고 넘어온 상태라
		// 지역변수인 local, num 이미 소멸되었음
		// 이 시점에서는 사용 불가능
		
		// System.out.println(f1.global);
		// global 변수는 아직 소멸되지 않음
		// 단, private이기 때문에 직접 접근이 안되는 것
		
		// global도 소멸시키고 싶다면
		f1 = null;
		// 사실상 이 시점 기준으로 소멸됨
		
		//System.out.println(f1.global);
		// 접근이 불가능함(NullPointException 오류)
		*/
		
		// 2. FieldTest2
		FieldTest2 f2 = new FieldTest2();
		// > 총 4개의 멤버변수 생성됨
		
		// public: 어디서든 직접접근 가능
		System.out.println(f2.pub);
		
		// protected: 같은패키지에서는 직접접근 가능, 다른패키지 원칙상 불가
		// 			  (조건부로 상속관계에서는 가능)
		// System.out.println(f2.pro); // is not visible
		
		// default: 같은 가능, 다른 불가
		// System.out.println(fc2.df); // is not visible
		
		// private: 해당 클래스 영역 안에서만 접근 가능
		// System.out.println(f2.pri); // is not visible
		
		// 3. FieldTest3
		
		System.out.println(FieldTest3.sta);
		// > static 예약어가 붙은 놈들은 객체생성 필요없이
		// 그냥 갖다쓸 수 있다(프로그램 시작과 동시에 이미 공간 확보되어서)
				
		System.out.println(FieldTest2.sta);
		// > 항상 클래스명.static변수명 형식으로 접근해야함
		// static 변수명이 중복될 수 있기 때문에
		// 정확하게 어느 클래스의 변수를 가져다 쓸건지 풀네임으로 기수해야함
		
		FieldTest3.test();
		FieldTest2.test();
		// > 메소드도 마찬가지
		//   객체 생성할 필요 없음, 항상 클래스명.static메소드명() 호출하면 됨
		
		// static 메소드 대표주자
		System.out.println((int)(Math.random()*10)+9);
		
		System.out.println(FieldTest3.NUM);
		// 상수필드 값 절대 변경 불가능
		
		// 그럼 그냥 static 변수는?
		FieldTest3.sta = "FieldTest3";
		
		System.out.println(FieldTest3.sta);
		// 값 변경 가능
		
		/*
		 * * static 키워드는 너무 남발해도 좋지 않음
		 * - 프로그램 시작과 동시에 다 메모리 Static 영역에 올려두고 갖다쓰는 개념인데
		 *   내가 앞으로 쓸지도 쓰지 않을지도 모르는 놈들을 죄다 메모리에 미리 올려두고 시작하면
		 *   성능적인 이슈가 발생할 수 있어서
		 * - 자주, 어디서든지 두고두고 갖다 써야하는 것들만 static 붙이자
		 * 
		 * * static 많이 사용하는 예시
		 * - 자바에서 제공하는 Math 클래스
		 * 
		 * Math: 수학 계산과 관련된 유용한 메소드들이 모인 클래스(계산용 메소드, 관련 상수값들)
		 * > 수학 계산할 일이 빈번하다 보니 필드, 메소드 모두 static으로 만듦!
		 *   (Math 클래스 타입의 객체를 생성할 필요가 없다)
		 *   즉, Math m = new Math(); // 못 함
		 *   아예 처음부터 객체생성구문 쓰지 못하게 private로 막음
		 *   (어차피 객체 생성을 안해도 그냥 갖다 쓸 수 있어서)
		 *   객체생성구문 또한 못쓰게 막아둔 코드패턴을 "싱글톤 패턴"이라고 함
		 */
		
		// Math 클래스 대표 필드
		System.out.println(Math.PI); // 원주율

	}

}















