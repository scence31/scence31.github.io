package com.kh.chap02_inherit.model.vo;

public class Car extends Vehicle {
	
	private int tire;
	
	
	public Car() {}
	
	public Car(String name, double mileage, String kind, int tire) {
		
		super(name, mileage, kind);
		
		this.tire = tire;
		
	}
	
	public void setTire(int tire) {
		
		this.tire = tire;
	}
	
	public int getTire(int tire) {
		
		return tire;
	}
	
	public String information() {
		
		return super.information() + "\t" + tire;
	}

}
