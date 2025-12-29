package com.kh.chap02_encapsulation.run;

import com.kh.chap02_encapsulation.model.vo.Book;
import com.kh.chap02_encapsulation.model.vo.Person;
import com.kh.chap02_encapsulation.model.vo.Student;

public class Run {
	
	/*
	 * 7. 캡슐화 과정을 통해 더 완벽한 형태의 클래스를 만들자!
	 * - 캡슐화를 하지 않으면: 외부로부터 직접접근이 가능하기 때문에
	 * 					   값이 변질되거나 함부로 조회가 가능하게 됨
	 * 
	 * 캡슐화: 데이터의 접근제한을 원칙으로 하여 외부로부터 직접접근을 막자는 컨셉
	 * 		  대신에 "간접적으로나마 값을 처리할 수 있게끔" 조치를 취해야 함
	 * 
	 * 1) 정보은닉 단계
	 * - 필드들을 외부로부터 직접접근을 막기 위해 public 대신 private으로 변경
	 * 2) setter / getter 메소드 작성
	 * - 간접적으로나마 접근해서 값을 대입하거나 변경하거나
	 *   그 값을 가져올 수 있게 처리하는 메소드들을 작성하는 단계
	 *   값을 담는 용도의 메소드: setter 메소드
	 *   값을 가져오는 용도의 메소드: getter 메소드
	 * 
	 */

	public static void main(String[] args) {
		
		// 캡슐화 과정 중 1단계만 완료한 후의 클래스로 객체 생성해보기
		Student hong = new Student();
		
		// 홍길동 학생 정보 담기
		//hong.name = "홍길동";
		// is not visible 오류 발생
		// 각 필드의 접근제한자를 private로 하면 직접접근이 불가능해짐!
		// 따라서 setter 메소드를 통해 간접적으로나마 접근해서 값을 대입하자
		
		// setter메소드를 통해 간접적으로 값 대입해보기
		// 객체명.메소드명();
		// 객체명.setter메소드명(필드에대입할값);
		// >> setter메소드 호출공식
		hong.setName("홍길동");
		hong.setAge(20);
		hong.setHeight(168.7);
		// 메소드 호출시 넘기는 값을 "인자"라고 함
		
		// 잘 됐나 출력해보기
		/*
		System.out.println(hong.name);
		System.out.println(hong.age);
		System.out.println(hong.height);
		*/
		// 마찬가지로 is not visible 오류 발생
		// 값을 조회할 경우에도 직접접근은 불가능함
		// getter메소드를 만들어서 간접적으로 값을 꺼내올 수 있게 하기
		
		// getter메소드 호출해서 각 필드값 얻어내기
		// 객체명.getter메소드명();
		
		
		System.out.println(hong.getName());
		System.out.println(hong.getAge());
		System.out.println(hong.getHeight());
		
		/*
		String name = hong.getName();
		int age = hong.getAge();
		double height = hong.getHeight();
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(height);
		*/
		
		System.out.println();
		
		// xxx님의 나이는 xx살이고 키는 xx이다 출력
		System.out.printf("%s님의 나이는 %d살이고 키는 %.1fcm이다. \n", hong.getName(), hong.getAge(), hong.getHeight());
		
		
		// println이든 printf든 간에
		// 모든 필드값 불러와서 출력하기 귀찮음
		System.out.println(hong.information());
		
		//퀴즈) 고길동, 24, 180.3 객체 만들고 정보 출력
		
		Student ko = new Student();
		// > null, 0, 0.0
		
		ko.setName("고길동");
		ko.setAge(24);
		ko.setHeight(180.3);
		
		
		System.out.println(ko.information());
		
		System.out.println("----------------");
		
		Book bo = new Book();
		
		bo.setTitle("객체");
		bo.setPublisher("kh");
		bo.setAuthor("이정민");
		bo.setPrice(15000);
		bo.setDiscountRate(10.5);
		
		System.out.println(bo.information());
		
		System.out.println("-----------------");
		
		Person pe = new Person();
		
		pe.setId("user01");
		pe.setPwd("pass01");
		pe.setName("김가현");
		pe.setAge(20);
		pe.setGender('F');
		pe.setPhone("010-1111-2222");
		pe.setEmail("gahyun@kh.com");
		
		System.out.println(pe.information());
		
		
		
		
	}

}













