package com.kh.chap02_abstractANDinterface.part02_family.model.vo;

// 엄마든 애기든 간에 사람이라면 가져야할 것들을 공통코드화하기

public abstract class Person {
	
	// 필드부
	private String name;
	private double weight;
	private int health; // 건강도
	
	public Person() {}
	
	public Person(String name, double weight, int health) {
		this.name = name;
		this.weight = weight;
		this.health = health;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	@Override
	public String toString() {
		
		return name + weight + health;
	}
	// 사람이라면 응당 무조건 해야하는 행위: 밥먹기, 잠자기
	// public abstract void eat();
	
	// public abstract void sleep();
	// > eat, sleep 두 추상메소드를 인터페이스라는 곳으로 이관
}













