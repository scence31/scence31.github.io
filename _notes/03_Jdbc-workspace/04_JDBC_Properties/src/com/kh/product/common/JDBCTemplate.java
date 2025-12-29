package com.kh.product.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private JDBCTemplate() {}
	
	/**
	 * 공용코드 중 Connection 관련한 것들 모아둔 메소드, .properties 파일 연동
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
			try {
				
				prop.load(new FileInputStream("resources/driver.properties"));
				// resources의 dirver.properties 파일에서 DB 연결정보 읽어들이기
				
				Class.forName(prop.getProperty("driver"));
				// JDBC의 driver 클래스 등록
				
				conn = DriverManager.getConnection(prop.getProperty("url"),
						   prop.getProperty("username"),
						   prop.getProperty("password"));
				
				conn.setAutoCommit(false);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			return conn;

	} // getConnection static 메소드 끝.
	
	/**
	 * 공용코드 중 close 관련 메소드. conn / 예외처리 포함
	 * @param conn
	 */
	public static void close(Connection conn) {
		
		try {
			
			if((conn != null) && (!conn.isClosed())) {
				
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close static 메소드(conn) 끝
	
	/**
	 * 공용코드 중 close 관련 메소드. rset / 예외처리 포함
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		
		try {
			
			if((stmt != null) && (!stmt.isClosed())) {
				
				stmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // close static 메소드(stmt) 끝
	
	/**
	 * 공용코드 중 close 관련 메소드. rset / 예외처리 포함
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		
		try {
			
			if((rset != null) && (!rset.isClosed())) {
			
				rset.close();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // close static 메소드(rset) 끝
	
	/**
	 * 공용코드 중 commit 관련 메소드. 예외처리 포함
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			
			if((conn != null) && (!conn.isClosed())) {
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // commit static 메소드 끝.
	
	/**
	 * 공용코드 중 rollback 관련 메소드. 예외처리 포함
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
		try {
			
			if((conn != null) && (!conn.isClosed())) {
			
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // rollback static 메소드 끝

}





