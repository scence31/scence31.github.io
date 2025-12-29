package com.kh.model.vo;

import java.sql.Date;

/*
 * VO(Value Object)
 * 
 * - 현실세계의 객체를 표현하기 위한 틀
 * - DB 테이블의 한 행에 대한 데이터를 담을 수 있는 저장용 클래스
 * - 매칭할 DB 테이븝르이 각 컬럼을 필드로써 유사하게 구성!!
 * 
 * > 유사용어
 * DTO(Data Transfer Object)
 * 
 * VO 작성 순서
 * 1) 캡슐화
 * 2) 기본생성자/매개변수생성자
 * 3) setter/getter
 * 
 */

public class Member {
	
	//필드부. DB 테이블의 컬럼 정보와 유사하게 작업
	
	private int userNo;			//	USERNO	NUMBER
	private String userId;		//	USERTID	VARCHAR2(15 BYTE)
	private String userPwd;		//	USERPWD	VARCHAR2(20 BYTE)
	private String userName;	//	USERNAME	VARCHAR2(20 BYTE)
	private String gender;		//	GENDER	CHAR(1 BYTE) ==> char 대신 String 사용 이젠
	private int age;			//	AGE	NUMBER
	private String email;		//	EMAIL	VARCHAR2(30 BYTE)
	private String phone;		//	PHONE	CHAR(11 BYTE)
	private String address;		//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollDate;	//	ENROLLDATE	DATE ==> java.sql.Date 이거로 import 오라클
	
	// 생성자부
	public Member() {}

	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
	}	
	
	// 매개변수 생성자(필요한 것만 선택해서) -- 회원가입
	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	
	// 메소드부
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", enrollDate=" + enrollDate + "]";
	}
	
	
	
	
}
























