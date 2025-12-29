package com.kh.chap03_class.model.vo;

//클래스에서 사용 가능한 접근제한자: public, default(기술하지 않을 때)
//커피의 정보를 담을 수 있는 VO 클래스
public class Coffee {
	
	
	//필드부
	//추상화 과정: 음료명(String), 가격(int), 핫/아이스(char), 사이즈(char)
	private String coffeeName;
	private int price;
	private char option; // 'H' or 'I'
	private char size; // 'T' or 'G' or 'V'
	
	//생성자부
	
	//메소드부
	public void setCoffeeName(String coffeeName) {
		
		this.coffeeName = coffeeName;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public void setOption(char option) {
		
		this.option = option;
	}
	
	public void setSize(char size) {
		
		this.size = size;
	}
	
	public String getCoffeeName() {
		
		return coffeeName;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public char getOption() {
		
		return option;
	}
	
	public char getSize() {
		
		return size;
		
	}
	
	public String information() {
		
		return "커피명: " + coffeeName + ", 가격: " + price + ", 옵션: " + option + ", 사이즈: " + size;
		
	}
	
	

}
