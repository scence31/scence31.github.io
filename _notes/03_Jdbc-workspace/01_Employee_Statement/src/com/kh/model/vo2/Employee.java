package com.kh.model.vo2;

import java.sql.Date;

public class Employee {
	
		private String empId;		//	EMP_ID 사원번호
		private String empName;		//	EMP_NAME 이름
		private String empNo;		//	EMP_NO 주민등록번호
		private String email;		//	EMAIL
		private String phone;		//	PHONE
		private String deptCode;	//	DEPT_CODE 부서코드
		private String jobCode;		//	JOB_CODE 직급코드
		private String salLevel;	//	SAL_LEVEL 급여등급
		private int salary;			//	SALARY
		private double bonus;		//	BONUS
		private String managerId;	//	MANAGER_ID 관리자사번
		private Date hireDate;		//	HIRE_DATE
		private Date entDate;		//	ENT_DATE 퇴사일
		private String entYn;		//	ENT_YN 재직여부
	
		public Employee() {}

		public Employee(String empId, String empName, String empNo, String email, String phone, String deptCode,
				String jobCode, String salLevel, int salary, double bonus, String managerId, Date hireDate,
				Date entDate, String entYn) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.empNo = empNo;
			this.email = email;
			this.phone = phone;
			this.deptCode = deptCode;
			this.jobCode = jobCode;
			this.salLevel = salLevel;
			this.salary = salary;
			this.bonus = bonus;
			this.managerId = managerId;
			this.hireDate = hireDate;
			this.entDate = entDate;
			this.entYn = entYn;
			
			
		}

		public Employee(String empName, String empNo, String email, String phone, String deptCode, String jobCode,
				String salLevel, int salary, double bonus, String managerId) {
			super();
			this.empName = empName;
			this.empNo = empNo;
			this.email = email;
			this.phone = phone;
			this.deptCode = deptCode;
			this.jobCode = jobCode;
			this.salLevel = salLevel;
			this.salary = salary;
			this.bonus = bonus;
			this.managerId = managerId;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public String getEmpNo() {
			return empNo;
		}

		public void setEmpNo(String empNo) {
			this.empNo = empNo;
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

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

		public String getJobCode() {
			return jobCode;
		}

		public void setJobCode(String jobCode) {
			this.jobCode = jobCode;
		}

		public String getSalLevel() {
			return salLevel;
		}

		public void setSalLevel(String salLevel) {
			this.salLevel = salLevel;
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public double getBonus() {
			return bonus;
		}

		public void setBonus(double bonus) {
			this.bonus = bonus;
		}

		public String getManagerId() {
			return managerId;
		}

		public void setManagerId(String managerId) {
			this.managerId = managerId;
		}

		public Date getHireDate() {
			return hireDate;
		}

		public void setHireDate(Date hireDate) {
			this.hireDate = hireDate;
		}

		public Date getEntDate() {
			return entDate;
		}

		public void setEntDate(Date entDate) {
			this.entDate = entDate;
		}

		public String getEntYn() {
			return entYn;
		}

		public void setEntYn(String entYn) {
			this.entYn = entYn;
		}

		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
					+ ", phone=" + phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel="
					+ salLevel + ", salary=" + salary + ", bonus=" + bonus + ", managerId=" + managerId + ", hireDate="
					+ hireDate + ", entDate=" + entDate + ", entYn=" + entYn + "]";
		}
		
		
		
	

}
