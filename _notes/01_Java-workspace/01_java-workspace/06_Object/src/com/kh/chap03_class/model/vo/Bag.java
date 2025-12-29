package com.kh.chap03_class.model.vo;

public class Bag {
	
	private String name; // 가방 제품명
	private String type; // 가방 제품종류(백팩, 크로스백, 핸드백 등등)
	private char size; // 가방 사이즈(S / M / L)
	private String color; // 가방 색깔
	private int price; // 가격
	// 무게 추가
	private double weight;
	
	public Bag() {
		
		
	}
	
	public Bag(String name, String type, char size, String color, int price) {
		
		this.name = name;
		this.type = type;
		this.size = size;
		this.color = color;
		this.price = price;
		
	}
	// 무게 추가
	public Bag(String name, String type, char size, String color, int price, double weight) {
		
		this(name, type, size, color, price);
		// 무게 추가
		this.weight = weight;
		
		
		
	}

	public void setName(String name) {
		
		this.name = name;
	}
	
	public void setType(String type) {
		
		this.type = type;
	}
	
	public void setSize(char size) {
		
		this.size = size;
	}
	
	public void setColor(String color) {
		
		this.color = color;
	}
	
	public void setPrice(int pirce) {
		
		this.price = price;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getType() {
		
		return type;
	}
	
	public char size() {
		
		return size;
	}
	
	public String color() {
		
		return color;
	}
	
	public int price() {
		
		return price;
	}
	
	public String information() {
		
		return name + "\t" + type + "\t" + size + "\t" + color + "\t" + price + "\t" + weight;
	}
}


