package com.kh.chap02_encapsulation.model.vo;


// 캡슐화 과정까지 완료한 클래스
public class Student {
	
	// [필드부]
	// 접근제한자 (예약어) 자료형 필드명;
	private String name;
	private int age;
	private double height;
	
	
	// [생성부자부]
	
	// [메소드부]
	// - "메소드"들이 모여있는 "부"분
	
	/*
	 * * 메소드
	 * - 하나의 기능단위
	 * - 각각의 기능을 담당하는 개념
	 * 
	 * [표현법]
	 * 접근제한자 반환형 메소드명(매개변수(생략가능)) {
	 *  public  void  method1()
	 * 		
	 * 		실행할 코드;
	 * }
	 * 
	 * > 반환형: 메소드 종료 후 메소드를 호출했던 곳(태초마을)으로 돌아갈 때
	 * 			갖고 갈 값의 "자료형"을 지정하는 부분
	 * 			(갖고 갈 값이 없다면 void 적용)
	 * > 매개변수: 메소드 실행시 필요한 값이 있다면 호출해주는 곳(태초마을)로부터
	 * 			 값을 넘겨받는 부분(넘겨받을 값 없다면 생략)
	 * 
	 */
	
	// 간접적으로나마 데이터를 필드에 기록 및 수정하는 용도의 메소드 ( = 연산 이용)
	// >> setter메소드
	
	/*
	 * * setter메소드 작성 공식
	 * 
	 * [표현법]
	 * public void set필드명(해당필드의자료형 해당필드명) {
	 * 
	 * 		this.필드명 = 해당필드명;
	 * }
	 * 
	 * > "set필드명" 메소드명은 반드시 낙타표기법 지켜야함
	 * > 매개변수의 이름은 해당 필드명과 "동일"하게 맞춰준다!
	 * > 매개변수명과 필드명이 일치하면 매개변수명이 우선순위 더 높음
	 * 	 "필드로서의" 의미를 부여하려면 필드명 앞에 this. 붙여야됨
	 * > setter메소드는 반드시 public으로 풀어줌!
	 */
	
	// name필드에 대한 setter메소드
	public void setName(String name) {
		
		this.name = name;
	}
	
	// age 필드에 대한 setter메소드
	public void setAge(int age) {
		
		this.age = age;
	}
	
	// height 필드에 대한 setter메소드
	public void setHeight(double height) {
		
		this.height = height;
	}
	
	// setter메소드는 필드마다 무조건 한 개씩 다 만들어줘야 한다!
	
	// 간접적으로나마 데이터를 꺼내올 수 있게 해주는 메소드
	// > getter메소드
	
	/*
	 * * getter메소드 작성공식
	 * 
	 * [표현법]
	 * public 해당필드의자료형 get필드명() {
	 * 
	 * 		return 필드명;
	 * }
	 * 
	 * > get필드명 낙타표기법 지키기
	 * > 반환형은 해당 필드값의 자료형, 반환값은 해당필드에 담겨있는 값이 된다!
	 * > getter메소드 또한 public으로 풀어줘야함
	 * 
	 */
	
	// name필드에 대한 getter메소드
	public String getName() {
		
		return name;
	}
	
	public int getAge() {
		
		return age;
	}
	
	public double getHeight() {
		
		return height;
	}
	
	// getter메소드 또한 필드마다 하나씩 다 만들어야댐
	
	// ---- 여기까지가 캡슐화 관련 메소드 ----
	
	// 필요하다면 이 자리에 추가적으로 더 메소드를 정의해줘도 됨
	// VO클래스의 메소드부에는 반드시 세터/게터 메소드만 존재하란 법은 없음
	
	// 모든 필드값을 하나의 문자열로 연이어서 돌려주는 용도의 메소드
	public String information() {
		
		// return name, age, height;
		// return(태초마을로 돌려낼) 값은 최대 1개만 가능
		// return name + age + height; // "홍길동20168.7"
		return name + "님의 나이는 " + age + "살이고, 키는" + height + "cm 입니다.";
		
	}
	
}



















