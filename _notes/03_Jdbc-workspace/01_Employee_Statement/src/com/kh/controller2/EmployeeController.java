package com.kh.controller2;

import com.kh.model.dao2.EmployeeDao;
import com.kh.model.vo2.Employee;
import com.kh.view2.EmployeeView;

public class EmployeeController {
	
	public void insertEmployee(String empName, String empNo, String email, String phone, 
							   String deptCode, String jobCode, String salLevel,
							   int salary, double bonus, String managerId) {
		
		Employee e = new Employee(empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId);
		
		int result = new EmployeeDao().insertEmployee(e);
		
		if(result > 0) {
			
			new EmployeeView().displaySuccess("회원 추가 성공");
			
		} else {
			
			new EmployeeView().displiayFail("회원 추가 실패");
			
		}
	} // insertEmployee 메소드 끝.

}
