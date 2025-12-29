package com.kh.field.example2.model.vo;

public class Circle {
	
	
	// 원 둘레 2파이r
	// 원 넓이 파이r제곱
	public static final double PI = 3.14;
	private int radius = 1;
	
	public Circle() {
		
		
	}
	
	public Circle(int radius) {
		
		this.radius = radius;
		
		
	}


	
	public void setRadius(int radius) {
		
		this.radius = 1;
		
	}
	
	public int getRadius() {
		
		return radius;
		
	}
	
	public String information() {
		
		return "반지름: " + radius;
	}
}
