package com.kh.shoes.model.vo;

public class Shoes {
	
	// 필드부: SQL의 테이블 컬럼명과 비슷하게 값들 지정
	private String shoesCode;		
	private String shoesName;					
	private String brand;						
	private int price;
	private int stock;
	private String status; // 판매여부 'Y' or 'N'
	// status는 원래 char 타입이지만, 통일을 위해 String 타입으로 설정
	
	//생성자부
	// 기본생성자
	public Shoes() {}
	
	// 매개변수생성자
	public Shoes(String shoesCode, String shoesName,
				 String brand, int price, int stock,
				 String status) {
		super();
		this.shoesCode = shoesCode;
		this.shoesName = shoesName;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.status = status;
	}
	
	// 메소드부(sestter/getter/toString)
	public String getShoesCode() {
		return shoesCode;
	}

	public void setShoesCode(String shoesCode) {
		this.shoesCode = shoesCode;
	}

	public String getShoesName() {
		return shoesName;
	}

	public void setShoesName(String shoesName) {
		this.shoesName = shoesName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Shoes [shoesCode=" + shoesCode + ", shoesName="
				+ shoesName + ", brand=" + brand + ", price=" + price
				+ ", stock=" + stock + ", status=" + status + "]";
	}
	
	

}
