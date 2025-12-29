package com.kh.chap01_beforeVSafter.after.model.vo;

// 클래스 다이어그램에서는 자식클래스가 부모클래스를 찌르는 방향 -->
public class SmartPhone extends Product /*  extends Object */{
	
	private String mobileAgency;
	
	public SmartPhone() { }
	
	public SmartPhone(String brand, String pCode, String pName, int price, String mobileAgency) {
		
		super(brand, pCode, pName, price);
		
		this.mobileAgency = mobileAgency;
		
	}
	
	
	public void setMobileAgency(String mobileAgency) {
		
		this.mobileAgency = mobileAgency;
	}
	
	public String getMobileAgency() {
		
		return mobileAgency;
	}
	
	public String information() {
		
		return super.information() + " " + mobileAgency;
	}

}
