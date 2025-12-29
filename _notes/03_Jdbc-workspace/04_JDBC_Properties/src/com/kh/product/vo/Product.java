package com.kh.product.vo;

public class Product {
	
	// 필드부
	private String prCode; // 제품코드
	private String prBrand; // 제품브랜드
	private String prName; // 제품이름
	private int prPrice; // 제품가격
	private int prStock; // 재고량
	
	public Product() {}

	public Product(String prCode, String prBrand, String prName, int prPrice, int prStock) {
		super();
		this.prCode = prCode;
		this.prBrand = prBrand;
		this.prName = prName;
		this.prPrice = prPrice;
		this.prStock = prStock;
	}

	public String getPrCode() {
		return prCode;
	}

	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}

	public String getPrBrand() {
		return prBrand;
	}

	public void setPrBrand(String prBrand) {
		this.prBrand = prBrand;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public int getPrPrice() {
		return prPrice;
	}

	public void setPrPrice(int prPrice) {
		this.prPrice = prPrice;
	}

	public int getPrStock() {
		return prStock;
	}

	public void setPrStock(int prStock) {
		this.prStock = prStock;
	}

	@Override
	public String toString() {
		return "Product [prCode=" + prCode + ", prBrand=" + prBrand + ", prName=" + prName + ", prPrice=" + prPrice
				+ ", prStock=" + prStock + "]";
	}
	
	

}
