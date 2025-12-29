package com.kh.chap03_class.model.vo;

// 클래스 실습예제 2번
public class Product {
	
	//필드부
	private String pName/* = null */; //제품명
	// 외자인 경우 명명규칙 없이 setter getter 메소드 생성
	private int price/* = 0 */; //제품가격
	private String brand = "애플"; //브랜드명
	// 필드를 선언만 한 경우에는
	// 항상 해당 타입에 대한 기본값으로 자동으로 초기화됨(heap영역 특징)
	// 내가 원하는 기본값이 따로 있다면,
	// 그 값으로 해당필드의 선언 및 초기화 구문까지 한번에 작성하면 됨
	
	
	//생성자부
	
	//메소드부
	
	public void setpName(String pName) {
		
		this.pName = pName;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public void setBrand(String brand) {
		
		this.brand = brand;
	}

	public String getpName() {
		
		return pName;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public String getBrand() {
		
		return brand;
	}

	public String information() {
		
		return "제품명: " + pName + ", 제품가격: " + price + ", 브랜드명: " + brand;
	}


}
