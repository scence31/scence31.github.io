package com.kh.chap02_encapsulation.model.vo;

// 현실세계의 책 한권의 정보를 담을 수 있는 VO클래스(추상화, 캡슐화된 상태로)
public class Book {
	
	// 필드부
	// > 추상화과정을 거쳤다는 가정 하에 필드 먼저 제시
	private String title; // 책 제목
	private String publisher; // 출판사명
	private String author; // 저자명
	private int price; // 책 가격
	private double discountRate; // 책의 할인율
	
	// 생성자부
	
	// 메소드부
	// 위에 제시한 필드별 세터/게터 메소드 작성
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public void setPublisher(String publisher) {
		
		this.publisher = publisher;
	}
	
	public void setAuthor(String author) {
		
		this.author = author;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public void setDiscountRate(double discountRate) {
		
		this.discountRate = discountRate;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public String getPublisher() {
		
		return publisher;
	}
	
	public String getAuthor() {
		
		return author;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public double getDiscountRate() {
		
		return discountRate;
	}
	// 여기까지 캡슐화 완료
	
	// 모든 필드값들을 하나의 문자열로 연이어 내보내주는 인포메이션 메소드
	public String information() {
		
		return "책 제목은 " + title + "이고, 출판사명은 " + publisher + "이고, 저자명은 " + author + "이고, 가격은 " + price + "이고, 할인율은 " + discountRate + "%입니다.";
	}
	
	// 앞으로 캡슐화 뿐만 아니라 인포메이션 메소드도 만들기
}
