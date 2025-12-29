package com.kh.chap03_class.run;

import com.kh.chap03_class.model.vo.Bag;
import com.kh.chap03_class.model.vo.Coffee;
// > 클래스의 접근제한자가 public이 아닌 default일 경우
//   나와 같은 패키지의 클래스만 찾아 쓸 수 있음!
//   Coffee is not visible 오류 발생
//   즉, 다른 패키지에서는 이 Coffee 클래스를 갖다 쓰지 못함
import com.kh.chap03_class.model.vo.Product;

public class Run {
	
	public static void main(String[] args) {
		
		/*
		Dog a = new Dog();
		
		a.setName("뽀삐");
		a.setAge(4);
		a.setType("포메");
		a.setColor("화이트");
		a.setWeight(4.2);
		
		System.out.print(a.information());
		*/
		
		Product p1 = new Product();
		// > null, o, null 기본값
		
		p1.setpName("아이폰13");
		p1.setPrice(1230000);
		//p1.setBrand("애플");
		
		System.out.println(p1.information());
		
		Product p2 = new Product();
		
		p2.setpName("애플워치5");
		p2.setPrice(500000);
		//p2.setBrand("애플");
		
		System.out.println(p2.information());
		
		/*
		 * > p3, p4, p5, ... 제품 객체를 계속 만들 것임
		 * 	 그런데 전부 애플 브랜드의 제품으로 객체 생성할 것
		 * > brand 필드값을 "애플"로 맞추면 될 것 같음
		 *   (즉, setBrand를 "애플"로 고정해서 호출하고싶음)
		 */
		
		p1.setBrand("APPLE"); // "애플"로 고정한 값을 "APPLE"로 바꾸고싶음
		p2.setBrand("APPLE"); // 그냥 다시 하면 됨
		
		System.out.println(p1.information());
		System.out.println(p2.information());
		
		/*
		 * 필드에 초기화 구문을 이용해서 초기화하더라도
		 * 언제든지 setter 메소드를 통해 필드값을 바꿀 수 있음
		 * (필드는 전역"변수"이기 때문임)
		 * 
		 * 
		 */
		System.out.println("===============================================");
		
		/*
		 * * Coffee라는 클래스는 내가 만든 클래스임
		 * == 커피명(String), 가격(int), 옵션(char), 사이즈(char)
		 * 	  한번에 String, int, char, char를 다 담을 수 있는
		 *    나만의 "배열같은" 자료형(구조체 개념에서 파생된 것이 클래스)
		 * > 클래스 == 사용자 정의 자료형
		 * 
		 */
		
		// - Coffee 클래스를 이용해서 객체 생성
		// 클래스명 객체명 = new 클래스명();
		// 자료형 변수명 = 값;
		// > 클래스 == 참조자료형 / 객체 == 참조자료형 변수
		
		Coffee c1 = new Coffee();
		// > null, 0, ' ', ' '
		// > heap 영역은 비어있을 수 없어서 JVM이 기본값으로 초기화
		
		c1.setCoffeeName("아메리카노");
		c1.setPrice(2000);
		c1.setOption('H');
		c1.setSize('T');
		
		System.out.println(c1.information());
		
		Coffee c2 = new Coffee();
		// 객체 생성 == 인스턴스화
		// 객체 == 인스턴스
		
		c2.setCoffeeName("바닐라라떼");
		c2.setPrice(3000);
		c2.setOption('I');
		c2.setSize('G');
		
		System.out.println(c2.information());
		
		System.out.println(c1);
		System.out.println(c2);
		// > 주소값 출력
		
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());
		// > 해시코드 출력

		Coffee c3 = c1;
		// > "아메리카노", 2000, 'H', 'T'
		System.out.println(c3.information());
		
		c3.setCoffeeName("콜드브루");
		c3.setOption('I');
		
		System.out.println(c3.information());
		System.out.println(c1.information());
		// 얕은복사라서 c1 내용물도 바뀜
		
		/*
		 * * 객체 또한 배열과 마찬가지로
		 *   참조자료형 개념이기 때문에 얕은복사 깊은복사 개념이 적용됨
		 *   
		 * 
		 * 
		 */
		
		System.out.println("========================");
		
		Bag b1 = new Bag("샤넬", "백팩", 'S', "파랑", 150000, 1.3);
		
		System.out.println(b1.information());
		
		
	}

}
















