package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * * JDBCTemplate 클래스
 * - JDBC 과정 중 반복적으로 쓰이는 구문들을 각각의 메소드 단위로 정의해두는 곳임.
 * - 해당 과정이 필요할 때마다 알맞은 메소드를 계속 호출해서 갖다 쓸 것(재사용 목적으로 공통코드 진행)
 * 
 * => 재사용, 공유, 공통의 개념의 코드를 언제든지 갖다 써야함.
 * 이 클래스의 모든 메소드는 static 메소드로 작성(싱글톤 패턴 적용)
 * 
 * 싱글톤 패턴: 메모리 영역에 단 한 번 올라간 것을 계속 재사용하는 개념으로 객체생성 필요없이 사용함!!
 * 
 * - 공통적으로 필요한 코드들
 * (1) Connection 객체 생성 및 트랜잭션 설정하는 코드 Class.forName / conn = Driver~
 * (2) JDBC용 객체를 반납하는 코드 close()
 * (3) Connection 객체를 가지고 트랜잭션 처리해주는 코드
 */

public class JDBCTemplate {
	
	// 접근제한자 private 사용: 생성자 호출구문 막기(객체생성 필요없음)
	private JDBCTemplate() {}
	
	// 모든 메소드들을 static으로 구현
	
	// (1), DB접속정보 전달하면서 Connection 객체를 생성해서 반환해주는 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			// 커넥션 객체 생성을 위한 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 커넥션 객체 생성하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 트랜잭션 설정하기(자동커밋 해제)
			conn.setAutoCommit(false);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 만들어진 커넥션 객체 반환
		return conn;
		
	} // getConnection 메소드 끝.
	
	// (2), 매개변수로 전달받은 JDBC용 객체를 반납해주는 메소드
	// 종류별로 만들기 오버로딩 적용! 커넥션, (프리페어드)스테이트먼트, 리저트셋
	public static void close(Connection conn) {
		
		try {
			
			if((conn != null) && (!conn.isClosed())) {
				// 전달된 conn이 null이 아니고, 아직 close된 상태가 아니라면!
				
				conn.close(); // 이 때 반납해주겠음.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // close 끝.
	
	// (2) (프리페어드)스테이트먼트 => 이거로만 만들면 프리페어드의 부모라서 다형성에 의해 자동으로 됨
	// 호출할 때 자식타입 객체를 인자로 넘겨도. 다형성/오버라이딩 적용
	public static void close(Statement stmt) {
		
		try {
			
			if((stmt != null) && (!stmt.isClosed())) {
			
				stmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // close 끝.
	
	//(2)
	public static void close(ResultSet rset) {
		
		try {
			
			if((rset != null) && (!rset.isClosed())) {
				
				rset.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // close 끝.
	
	// (3) 커밋. 매개변수로 전달받은 커넥션 객체를 가지고 커밋/롤백해주는 메소드
	public static void commit(Connection conn) {
		
		try {
			
			if((conn != null) && (!conn.isClosed())) {
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // commit 끝.
	
	//(3) 롤백.
	public static void rollback(Connection conn) {
		
			
		try {
				
			if((conn != null) && (!conn.isClosed())) {
					
					conn.rollback();
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
	} // rollback 끝.
	
	
	

}





























