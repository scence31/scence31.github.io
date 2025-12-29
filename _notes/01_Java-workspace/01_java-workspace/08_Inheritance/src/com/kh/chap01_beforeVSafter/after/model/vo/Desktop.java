package com.kh.chap01_beforeVSafter.after.model.vo;


//			 자식클래스		 부모클래스
//			 후손클래스		 조상클래스
//			 하위클래스		 상위클래스
//			 서브클래스		 슈퍼클래스
public class Desktop extends Product {
	// > 상속받는 순간 필드 4개, 메소드 9개를 내꺼처럼 쓸 수 있다.
	
	// 필드부
	private boolean allInOne;
	
	//생성자부
	public Desktop() { }
	
	// 자식클래스의 모든 필드에 대해 매개변수를 갖는 생성자를 작성할 때
	// 물려받은 필드에 대해 모두 다 초기화할 것을 염두에 두고 생성자 작성해야한다.
	public Desktop(String brand, String pCode, String pName, int price, boolean allInOne) {
		
		/*
		this.brand = brand;
		this.pCode = pCode;
		this.pName = pName;
		this.price = price;		
		this.allInOne = allInOne;
		*/
		// this. : 자기 자신의 주소값을 담고 있는 개념(내가 가진 필드의 ~)
		
		/*
		super.pName = pName;
		super.pCode = pCode;
		super.pName = pName;
		super.price = price;
		*/
		
		// super. : 해당 부모객체의 주소값을 담고 있는 개념(부모 클래스가 가진 필드의 ~)
		// > 아무리 부모클래스로부터 물려받은 필드라도 private 접근제한자라면 자유롭게 사용 불가능!
		
		// 해결방법1)
		// 부모클래스의 필드를 자식까지는 접근가능하게끔 접근제한을 살짝 풀어주는 방법
		// > 부모클래스 필드의 접근제한자를 private --> protected로 변경
		//   (protected: 같은 패키지 내에서만 접근가능 + 다른 패키지여도 상속관계에서 접근 가능)
		// > 하지만 정보은닉(캡슐화) 제대로 안됨.. private가 깨져서 권장하는 방법 아님.
		
		// 해결방법2)
		// 부모클래스의 setter메소드 활용(간접적으로 접근 값 대입, 심지어 public)
		/*
		super.setBrand(brand);
		super.setpCode(pCode);
		super.setpName(pName);
		super.setPrice(price);
		*/
		// > 필드의 개수가 늘어날수록 setter메소드도 그만큼 더 호출해야돼서 귀찮음
		
		// 해결방법3)
		// 부모클래스의 매개변수생성자 호출
		
		// this() : this 생성자, 내가 갖고있는 생성자를 호출하는 구문
		// super() : super 생성자, 부모클래스가 갖고있는 생성자를 호출하는 구문
		// super(); // 부모클래스의 기본생성자를 호출하겠다.
		super(brand, pCode, pName, price); // 부모클래스의 매개변수생성자를 호출하겠다.
		// > super 생성자 또한 첫줄에 호출구문을 기술해야한다!
		// > 이 시점 기준으로 이미 brand ~ price 필드까지는 초기화 되어있을 것!
		
		this.allInOne = allInOne;
	}
	
	//메소드부
	public void setAllInOne(boolean allInOne) {
		
		this.allInOne = allInOne;
	}
	
	public boolean isAllInOne() {
		
		return allInOne;
	}
	
	// information 메소드
	// > 부모클래스로부터 이미 물려받은 information 메소드가 있음
	//   자식클래스에 똑같이 information 메소드를 만들었다고 해서
	//   이 메소드를 2개 가지고 있는 상황은 아니다.
	// > 부모클래스로부터 메소드를 우선 물려받고,
	//   그 물려받은 메소드의 내용물(코드)이 마음에 들지 않아서
	//   코드를 "재정의"하였다라고 표현함
	//   즉, 코드를 덮어쓰겠다! == "오버라이딩"
	public String information() {
		
		/*
		return "brand: " + super.getBrand() + ", pCode: " + super.getpCode()
		+ ", getpName: " + super.getpName() + ", price: " + super.getPrice()
		+ ", allInOne: " + allInOne;
		*/
		
		return super.information() + " " + allInOne;
	}
	

}

















