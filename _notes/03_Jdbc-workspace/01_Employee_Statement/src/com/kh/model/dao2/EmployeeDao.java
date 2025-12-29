package com.kh.model.dao2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.model.vo2.Employee;

public class EmployeeDao {
	
	
	public int insertEmployee(Employee e) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO EMPLOYEE_COPY1 VALUES(SEQ_EMPID.NEXTVAL, '"
					+ e.getEmpName() + "', '"
					+ e.getEmpNo() + "', '"
					+ e.getEmail() + "', '"
					+ e.getPhone() + "', '"
					+ e.getDeptCode() + "', '"
					+ e.getJobCode() + "', '"
					+ e.getSalLevel() + "', "
					+ e.getSalary() + ", "
					+ e.getBonus() + ", '"
					+ e.getManagerId() + "', '"
					+ e.getHireDate() + "', '"
					+ e.getEntDate() + "', '"
					+ e.getEntYn() + "')";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				
				conn.commit();
			} else {
				
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}

		return result;		
	}

}
