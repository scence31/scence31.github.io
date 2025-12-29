package com.hw1.model.vo;

public class Employee {
	
	private int empNo;
	private String empName;
	private String dept;
	private int age;
	private char gender;
	private int salary;
	private double bonusPoint;
	private String phone;
	private String address;
	
	public Employee() {}
	
	public Employee(int empNo, String empName, String dept, int age, char gender, int salary, double bonusPoint,
			String phone, String address) {

		this.empNo = empNo;
		this.empName = empName;
		this.dept = dept;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
		this.bonusPoint = bonusPoint;
		this.phone = phone;
		this.address = address;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(double bonusPoint) {
		this.bonusPoint = bonusPoint;
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

	@Override
	public String toString() {
		return empNo + ", " + empName + ", " + dept + ", " + age + ", " + gender + ", " + 
				salary + ", " + bonusPoint + ", " + phone
				+ ", " + address;
	}
	
	
}
