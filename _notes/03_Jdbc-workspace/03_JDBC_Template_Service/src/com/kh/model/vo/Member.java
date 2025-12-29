package com.kh.model.vo;

import java.sql.Date;

public class Member {
	
	// 필드부
	private int userNo;				//	USERNO	NUMBER
	private String userId;			//	USERID	VARCHAR2(15 BYTE)
	private String userPwd;			//	USERPWD	VARCHAR2(20 BYTE)
	private String userName;		//	USERNAME	VARCHAR2(20 BYTE)
	private String gender;			//	GENDER	CHAR(1 BYTE)
	private int age;				//	AGE	NUMBER
	private String email;			//	EMAIL	VARCHAR2(30 BYTE)
	private String phone;			//	PHONE	CHAR(11 BYTE)
	private String address;			//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollDate;		//	ENROLLDATE	DATE
	
	
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
