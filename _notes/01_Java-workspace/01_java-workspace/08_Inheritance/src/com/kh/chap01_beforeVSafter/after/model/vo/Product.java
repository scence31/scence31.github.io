package com.kh.chap01_beforeVSafter.after.model.vo;

// Desktop, Tv, SmartPhone 세 클래스 모두
// 공통적으로 기술했었던 요소들만 추출해서 단 한 번 벼 ㄹ도로 정의해둔 클래스
// 즉, 공통코드를 갖고 있는 클래스 == 부모클래스
public class Product {
	
	//필드부
	
	private String brand;
	private String pCode; 
	private String pName;
	private int price;
	
	/*
	protected String brand;
	protected String pCode; 
	protected String pName;
	protected int price;
	*/
	
	//생성자부
	public Product() { }
	
	public Product(String brand, String pCode, String pName, int price) {
		
		this.brand = brand;
		this.pCode = pCode;
		this.pName = pName;
		this.price = price;		
	}
	
	//메소드부
	public void setBrand(String brand) {
		
		this.brand = brand;
	}
	
	public void setpCode(String pCode) {
		
		this.pCode = pCode;
	}
	
	public void setpName(String pName) {
		
		this.pName = pName;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public String getBrand() {
		
		return brand;
	}
	
	public String getpCode() {
		
		return pCode;
	}
	
	public String getpName() {
		
		return pName;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public String information() {
		
		return brand + " " + pCode + " " + pName + " " + price;
		
		// Product 클래스의 information 메소드에 allInOne 필드출력구문 못씀
		// 1. allInOne 전역변수 자체가 product 내에 존재하지 않기 때문
		// 2. 다른 자식클래스들에게 쓸모없는 코드를 물려주게 되기 때문
		// 3. 부모클래스는 자식클래스 코드를 갖다쓸 수 없음. ***
	}
}
