package com.kh.chap03_class.model.vo;

public class Dog {
	
	private String name;
	private int age;
	private String type;
	private String color;
	private double weight;
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public void setAge(int age) {
		
		this.age = age;	
	}
	
	public void setType(String type) {
		
		this.type = type;
	}
	
	public void setColor(String color) {
		
		this.color = color;
	}
	
	public void setWeight(double weight) {
		
		this.weight = weight;
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getAge() {
		
		return age;
	}
	
	public String getType() {
		
		return type;
	}
	
	public String getColor() {
		
		return color;
	}
	
	public double getWeight() {
		
		return weight;
	}
	
	public String information() {
		
		return "이름 : " + name + ", 나이: " + age + ", 종류: " + type + ", 색깔: " + color + ", 몸무게: " + weight + "kg";
		
		
	}
	

}
