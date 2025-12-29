package com.kh.chap02_abstractANDinterface.part02_family.model.vo;

public class Mother extends Person implements Basic {
	
	// 필드부
	private String babyBirth; // 아기 탄생여부: 출산, 입양, 없음
	
	//
	public Mother() {}

	public Mother(String name, double weight, int health, String babyBirth) {
		super(name, weight, health);
		this.babyBirth = babyBirth;
	}

	public String getBabyBirth() {
		return babyBirth;
	}

	public void setBabyBirth(String babyBirth) {
		this.babyBirth = babyBirth;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + babyBirth;
	}
	
	// 엄마가 밥을 먹을 때
	@Override
	public void eat() {
	
		// 몸무게가 기존 몸무게에 10 증가
		// 몸무게 = 몸무게 + 10;
		// super.weight = super.weight + 10;
		super.setWeight(super.getWeight() + 10);
		
		// 건강도는 기존의 건강도에서 10 감소
		// 건강도 = 건강도 - 10;
		super.setHealth(super.getHealth() - 10);
	}
	
	// 엄마가 잠 잘 때
	@Override
	public void sleep() {
		
		// 건강도 기존보다 10 증가
		super.setHealth(getHealth() + 10);
		
		
	}
	

}
