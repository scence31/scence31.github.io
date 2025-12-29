package com.kh.cjap02_set.part01_hashSet.model.vo;

public class Student {
	
	private String name;
	private int age;
	private int score;
	
	public Student() {}

	public Student(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", score=" + score + "]";
	}
	
	// equals, hashCode 메소드 오버라이딩
	// 1. equals 오버라이딩 - 필드값들을 기준으로 동등비교하게끔 재정의
	@Override
	public boolean equals(Object obj) {
		
		// Student객체명.equals(비교할Student객체);
		
		// Student this	 vs  Object obj
		// name, age, score 필드값이 일치하는가? -> 모두 일치하면 true, 하나라도 틀림 false
		
		Student other = (Student)obj;
		// 매개변수로 받은 비교대상 객체
		
		// name 필드값 동등비교
		// this.name.equals(other.name)
		
		// age 필드값 동등비교
		// this.age == other.age
		
		// score 필드값 동등비교
		// this.score == other.score
		
		if((this.name.equals(other.name)) && (this.age == other.age) && (this.score == other.score)) {
			
			return true;
		} else {
			
			return false;
		}
		
		
	}
	
	// 2. hashCode 오버라이딩 - 필드 내용을 기준으로 십진수화
	@Override
	public int hashCode() {
		
		return (name + age + score).hashCode(); // "김갑생2640"
		// 이미 String 클래스의 hashCode 메소드는 내용물 기준으로 십진수화 해줌
	}

}










