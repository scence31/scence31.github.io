package com.kh.chap02_abstractANDinterface.part01_basic.model.vo;

// 스포츠 응당 가져야할
public abstract class Sports {
	
	private int people;
	
	public Sports() {}
	
	public Sports(int people) {
		
		this.people = people;
		
	}
	
	public void setPeople(int people) {
		
		this.people = people;
	}
	
	public int getPeople() {
		
		return people;
	}
	
	@Override
	public String toString() {
		return "people: " + people; 
	}
	
	// 경기 규칙은 메소드로 표현
	public abstract void rule();
	
	/*
	 * * 부모클래스 입장에서 자식클래스들이 모두 물려준 메소드를 오버라이딩해서 쓴다면
	 *   굳이 힘들게 코드를 채워서 작성할 필요가 없을 것 같음
	 *   (알아서 재정의해서 쓰게끔 메소드의 틀만 제공해줘도 될 것 같은 느낌)
	 * > 메소드의 몸통부를 지우기, 이 때 abstract 예약어를 붙여주자!
	 * 
	 * * 추상메소드
	 * - 몸통부 { }가 존재하지 않는 미완성된 메소드
	 * - 추상메소드를 정의하고자 한다면, "abstract" 예약어도 써야 한다!
	 * - 어떤 클래스 내부에 미완성된 추상메소드가 하나라도 포함되는 순간
	 *   그 클래스 또한 추상클래스로 바뀐다
	 *   (마찬가지로 클래스 쪽에도 abstract 예약어 써야함)
	 * 
	 */

}









