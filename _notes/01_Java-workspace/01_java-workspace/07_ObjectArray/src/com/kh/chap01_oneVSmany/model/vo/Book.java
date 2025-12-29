package com.kh.chap01_oneVSmany.model.vo;

public class Book {
	
	// 필드부
	private String title;
	private String author;
	private int price;
	private String publisher;
	
	// 생성자부
	// 기본생성자
	public Book() { }
	
	// 모든 필드에 대한 매개변수생성자
	public Book(String title, String author, int price, String publisher) {
		
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
	}
	
	
	// 메소드부
	// setter method
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public void setAuthor(String author) {
		
		this.author = author;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public void setPublisher(String publisher) {
		
		this.publisher = publisher;
	}
	
	// getter method
	public String getTitle() {
		
		return title;
	}
	
	public String getAuthor() {
		
		return author;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public String getPublisher() {
		
		return publisher;
	}
	
	// information method
	public String information() {
		
		return "title: " + title + ", author: " + author + ", price: " + price + ", publisher: " + publisher;
		
	}

}























