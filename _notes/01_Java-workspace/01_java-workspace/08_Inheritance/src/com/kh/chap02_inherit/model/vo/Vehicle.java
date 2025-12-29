package com.kh.chap02_inherit.model.vo;

public class Vehicle {
	
	private String name;
	private double mileage;
	private String kind;
	
	public Vehicle() {}
	
	public Vehicle(String name, double mileage, String kind) {
		
		this.name = name;
		this.mileage = mileage;
		this.kind = kind;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public void setMileage(double mileage) {
		
		this.mileage = mileage;
	}
	
	public void setKind(String kind) {
		
		this.kind = kind;
	}
	
	public String information() {
		
		return name + "\t" + mileage + "\t" + kind;
	}

	
	
}
