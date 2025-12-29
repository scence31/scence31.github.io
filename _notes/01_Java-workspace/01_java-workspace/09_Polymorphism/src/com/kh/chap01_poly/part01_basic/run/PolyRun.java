package com.kh.chap01_poly.part01_basic.run;

import com.kh.chap01_poly.part01_basic.model.vo.Child1;
import com.kh.chap01_poly.part01_basic.model.vo.Child2;
import com.kh.chap01_poly.part01_basic.model.vo.Parent;

public class PolyRun {

	public static void main(String[] args) {
		// 명심할 사항 : "=" 대입연산자를 기준으로 왼쪽과 오른쪽의 자료형은 같아야함!! (컴퓨터의 값 처리 규칙)
		
		// 1. 부모 타입의 참조변수로 부모 객체를 다루는 경우
		System.out.println("1. 부모 타입의 참조변수로 부모 객체를 다루는 경우");
		Parent p1 = new Parent(); 
		// > p1 상자에는 Parent 형식의 주소값만 담길 수 있음!!
		
		p1.printParent();
		// p1.printChild1();
		// p1.printChild2();
		// > p1 참조변수로 Parent 에만 접근 가능 (Child1, Child2 꺼는 건들 수 없음)
		//   (부모 : 내꺼만 내꺼..흑ㅠ)
		
		// 2. 자식 타입의 참조변수로 자식 객체를 다루는 경우
		System.out.println("2. 자식 타입의 참조변수로 자식 객체를 다루는 경우");
		Child1 c1 = new Child1();
		// > c1 상자에는 Child1 형식의 주소값만 담길 수 있음!!
		
		c1.printChild1(); // 내꺼는 내꺼
		c1.printParent(); // 부모님거도 내꺼
		// > c1 참조변수로 Child1, Parent 둘 다 접근 가능
		//   (자식 : 내꺼도 내꺼, 부모님꺼도 내꺼 ^O^)
		
		// 3. 부모 타입의 참조변수로 자식 객체를 다루는 경우
		System.out.println("3. 부모 타입의 참조변수로 자식 객체를 다루는 경우");
		Parent p2 = /* (Parent) */ new Child1();
		// > 대입연산자 기준으로 양 쪽의 자료형이 다름에도 불구하고 오류 발생 X
		//   Child1 형식의 주소값이 Parent 형식의 주소값으로 "자동형변환" 이 되고 있다!!
		// > 즉, "상속" 구조에서는 클래스 간의 "형변환" 이 가능하다. - "다형성"
		
		// Parent 형식의 주소값이 담길 수 있는 p2 상자가 만들어짐
		// Child1 형식의 객체가 생성되면서 주소값 생성
		// Child1 형식의 주소값이 Parent 형식의 주소값으로 자동형변환
		// 최종적으로 타입이 맞아 떨어지므로 p2 에 주소값이 담김
		
		p2.printParent();
		// p2.printChild1();
		// > 결국 Parent 로 형변환이 되었기 때문에 Parent 에만 접근 가능!!
		
		((Child1)p2).printChild1();
		// > 다시 Child1 타입의 주소값으로 형변환을 함으로써 원상복귀 후 Child1 에 접근 가능!!
		
		((Child1)p2).printParent();
		// > 다시 자식 타입으로 원상복귀가 되었기 때문에 내꺼도 내꺼, 부모님꺼도 내꺼
		
		/*
		 * * 다형성 (Polymorphism)
		 * - 직역하면 다양한 형태를 갖는 성질
		 * - "상속" 관계에서의 각 클래스별 "형변환" 개념
		 *   (즉, 상속이 대전제임!! 상속 없이는 다형성이라는 개념도 없음)
		 *   
		 *   		   자동형변환
		 * 			<------------
		 * 부모타입					자식타입
		 * 			------------>
		 * 			   강제형변환
		 * 
		 * 1. UpCasting
		 * - 자식 타입이 부모 타입으로 형변환 되는 과정
		 * - 자동형변환 (형변환 연산자 생략 가능)
		 * 
		 * 2. DownCasting
		 * - 부모 타입이 자식 타입으로 형변환 되는 과정
		 * - 강제형변환 (형변환 연산자를 명시적으로 작성해야함)
		 */	
		
		
		// 다형성을 쓰는 이유
		
		// Child1 객체 2개, child2 객체 2개 필요함!
		// > 변수만 이용하면 변수 총 4개 필요
		// > 배열 이용하면 Child1 배열 1개, Child2 배열 1개
		
		// - 객체 배열 이용
		
		// Child1[] arr1 = new Child1[2];
		// arr1[0] = new Child1(1, 2, 4);
		// arr1[1] = new Child1(2, 1, 5);
		
		// Child2[] arr2 = new Child2[2];
		// arr2[0] = new Child2(5, 7, 2);
		// arr2[1] = new Child2(2, 3, 5);
		
		// 위의 방법도 충분히 가능하고 괜찮은 방법이지만,
		// 다형성을 적용하면 부모타입 참조변수로 다양한 자식객체들을 다룰 수 있기 때문에
		// 효율적으로 객체 배열을 쓸 수 있게 된다
		// > 부모변수 = 자식객체; --> 부모배열 = 자식객체들;
		
		System.out.println("===========다형성을 접목한 객체배열========");
		Parent[] arr = new Parent[4];
		arr[0] = /*(Parent)*/new Child1(1, 2, 4);
		arr[1] = /*(Parent)*/new Child2(5, 7, 2);
		arr[2] = new Child2(2, 3, 5);
		arr[3] = new Child1(2, 1, 5);
		
		
		((Child1)arr[0]).printChild1();
		((Child2)arr[1]).printChild2();
		((Child2)arr[2]).printChild2();
		((Child1)arr[3]).printChild1();
		
		// 주의할점!! ***
		// ((Child2)arr[0]).printChild2();
		// > ClassCastException 오류발생
		//   클래스간 형변환이 잘못 되었을 경우 발생하는 오류
		//   Child1이 Child2로 형변환이 될 수 없다고 알려줌
		// > 문법상의 오류는 없음! arr[0]에는 Parent 주소값이 담겨있고 자식타입인 Child2로 형변환이 가능하므로
		//   Child1 형태로 객체생성 --> Parent로 취급 --> Child2로 형변환
		//   그래서 오류가 발생함(Child1과 Child2는 남남임, 형변환 안됨)
		
		System.out.println("====반복문 이용해서 해보기====");
		
		for(int i = 0; i < arr.length; i++) {
			// 각 인덱스별로 실제로 참조하고 있는 자식타입으로 다운캐스팅(원상복귀) 후 메소드 호출
		
			// ((Child1)arr[i]).printChild1();
			// > 항상 원본타입에 맞게 형변환을 해줘야 함!
			
			// > 형변환 전 해당 타입이 원래 Child1이었는지 Child2였는지 검사하고 형변한 들어가기
		
			// * instanceof 연산자
			// - 현재 해당 참조변수가 실제로 어떤 자식객체를 참조하고 있는지 확인가능
			// System.out.println(arr[i] instanceof Child1);
			// System.out.println(arr[i] instanceof Child2);
			// > 원래 어느타입으로 생성됐냐에 따라 true or false 변환
			
			// System.out.println(arr[i] instanceof Parent);
			// > Child1, Child2 출신이지만 결국 Parent로 취급되기 대문에 모두 true
			
			/*
			if(arr[i] instanceof Child1) {
				
				((Child1)arr[i]).printChild1();
				
			} else {
				
				((Child2)arr[i]).printChild2();
				
			}
			*/
			// > 제대로 동작하나, 구문이 괜히 길고 복잡해지고 있음!
			
			// 팁)
			arr[i].print();
			// > 단, 오버라이딩을 이용하면 굳이 형변환을 통해 원상복귀를 하지 않아도 됨!
			//   메소드를 호출할 때, 실질적으로 참조하고 있는 원본자식타입의 오버라이딩 된
			//   메소드가 있다면, 그 오버라이딩 된 메소드가 우선적으로 호출되기 때문!
			//   (오버라이딩 된 메소드가 항상 우선순위가 제일 높음!)
			
			
		}
		

	}

}
