package com.kh.chap02_abstractANDinterface.part02_family.model.vo;

// Baby extends Person: Person 클래스의 코드 갖고오고 + 코드 덧붙여 확장
// Baby implements Basic: Basic 인터페이스의 추상메소드 받아와서 단순히 코드 안쪽에 채워서 구현하겟다.
public class Baby extends Person implements Basic {

	// 필드부
	
	// 생성자부
	public Baby() {}
	
	public Baby(String name, double weight, int health) {
		
		super(name, weight, health);
	}
	
	// 메소드부
	@Override
	public String toString() {
		
		return super.toString();
		
	}
	
	// 애기가 밥 먹을 때
	@Override
	public void eat() {
		
		// 몸무게 3 증가
		super.setWeight(super.getWeight() + 3);
		
		// 건강도 1 증가
		super.setHealth(super.getHealth() + 1);
	}
	
	// 애기가 잠을 잘 때
	@Override
	public void sleep() {

		// 건강도 3 증가
		super.setHealth(super.getHealth() + 3);
		
	}
	

}
