package com.kh.field.example2.model.vo;

public class Protuct {
	
	// field
	private String productId;
	private String productName;
	private String productArea;
	private int price;
	private double tax;
	
	// 생성
	
	public Protuct() {
		
		
	}
	
	public Protuct(String productId, String productName, String productArea, int price, double tax) {
		
		this.productId = productId;
		this.productName = productName;
		this.productArea = productArea;
		this.price = price;
		this.tax = tax;
		
		
	}
	
	// 메소드
	public void setProductId(String productId) {
		
		this.productId = productId;
	}
	
	public void setProductName(String productName) {
		
		this.productName = productName;
	}
	
	public void setProductArea(String productArea) {
		
		this.productArea = productArea;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public void setTax(double tax) {
		
		this.tax = tax;
	}
	
	public String getProductId() {
		
		return productId;
	}
	
	public String getProductName() {
		
		return productName;
	}
	
	public String getProuctArea() {
		
		return productArea;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public double getTax() {
		
		return tax;
	}
	
	public String information() {
		
		return productId + " " + productName + " " + productArea + " " + price + " " + tax;
		
	}

}







