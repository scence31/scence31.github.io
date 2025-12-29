package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Member;

/*
 * * JDBC용 객체
 * Connection: DB 연결정보를 담고있는 객체
 * 생성과 동시에 DB에 접속됨(연결통로 오픈)
 * 
 * (Prepared)Statement: DB에 접속되면, SQL문 전달 및 실행 후 결과를 받아내는 객체
 * 
 * ResultSet: SQL문 종류가 SELECT일 경우, 조회된 결과들이 담겨올 객체
 * 
 * * Statement(부모)와 PreparedStatement(자식)의 차이점 - 상속관계
 * Statement는 완성된 SQL문을 바로 실행하는 객체임. 사용자가 입력한 값들이 문자열로 다 채워진 상태
 * Connection 객체를 가지고 Statement 객체 생성
 * stmt = conn.createStatement();
 * 결과 = stmt.executeXxx(sql);
 * 
 * PreparedStatement는 미완성된 SQL문을 실행 전 잠시 보관해둘 수 있음. 완성시키고 실행
 * (미완성된 SQL문은 쿼리문 중간중간에 데이터가 들어갈 구멍이 뚫려있음)
 * 쿼리문에 구멍을 뚫을 때 ?(위치홀더) 사용.
 * 위치홀더는 쿼리문 실행 직전까지는 완성해야함.
 * 
 * Connection 객체를 가지고 PreparedStatement 객체 생성
 * pstmt = conn.prepareStatement(sql);
 * 결과 = pstmt.executeXxx(); => sql 안넘김
 * 
 * ==> sql의 시점 차이를 보자.
 * 
 * 
 * 
 * ~~기존 단계 동일.
 * 1-1단계) PreparedStatement 객체 생성
 * Connection 객체 활용. 이 때 SQL문을 담은 채로 생성
 * 
 * 1-2단계) 미완성된 SQL문을 완성형태로 채우기(완성된 경우는 생략)
 * 
 * 2단계) PreparedStatement 객체를 사용해서 SQL문 실행(sql 안넘김. 1-1단계에서 이미 넘김)
 * 
 * 3단계~) 기존과 동일. execute ~, ResultSet or ~, finally ~ ...
 * 
 * * 실무에서는 PreparedStatement를 씀. (시큐어 코딩: 해킹을 막는 방향으로 코딩하는 것)
 * -> SQL injection Attack 때문임. 쿼리문 사이에 값을 주입하여 DB 구조 변경 등 악의적인 의도 방지를 위해.
 */

public class MemberDao {
	
	/**
	 * 회원 추가 요청시 MEMBER 테이블에 INSERT 해주는 메소드
	 * @param m => 추가할 회원의 정보
	 * @return => insert 된(처리된) 행의 개수
	 */
	public int insertMember(Member m) {
		
		int result = 0; // 처리된 행의 개수를 담을 변수
		Connection conn = null; // 접속할 DB 정보를 담을 변수
		PreparedStatement pstmt = null; // SQL문 보관 및 실행 후 결과를 받기 위한 변수
		
		/*
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ", DEFAULT)";
		*/
		// --> 귀찮음
		String sql = """
						INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL
												, ?
												, ?
												, ?
												, ?
												, ?
												, ?
												, ?
												, ?
												, DEFAULT)
				""";
		// 자바의 Text blocks 문법: 개행 포함 긴 문자열을 """ 사이에 적어줄 수 있음
		// JDK 15버전부터 지원
		

		try {
			
			// 1) JDBC Driver 등록			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 트랜잭션관련 설정(자동커밋 해제)
			conn.setAutoCommit(false);
			
			// 3-1) SQL문을 넘기면서 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql); // 쿼리문좀 미리 보관하고 있어줘
			
			// 3-2) 미완성된 SQL문일 경우 완성시키기
			// [표현법]
			// pstmt.setXxx(홀더순번, 메꿀값);
			// pstmt.setInt(홀더순번, 정수);
			// pstmt.setString(홀더순번, 문자열);
			// setString / setInt 등 자료형에 맞게 작은따옴표 붙거나 안붙거나 알아서 됨
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			// 실행 전까지 구멍 메꿔두기
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				conn.commit();
				
			} else {
				
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	} // insertMember 끝.
	
	/**
	 * @return
	 */
	public ArrayList<Member> selectAll() {
		
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null; // 완성된 쿼리문도 상관 없음.
		ResultSet rset = null;
		
		String sql = """
						SELECT *
						   FROM MEMBER
						  ORDER BY USERNO ASC
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setSting(1, getUserId()); ... ==> 이미 완성된 쿼리문이라서 생략하면 됨
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Member(rset.getInt("USERNO"),
									rset.getString("USERID"),
						 			rset.getString("USERPWD"),
						 			rset.getString("USERNAME"),
						 			rset.getString("GENDER"),
						 			rset.getInt("AGE"),
						 			rset.getString("EMAIL"),
						 			rset.getString("PHONE"),
						 			rset.getString("ADDRESS"),
						 			rset.getDate("ENROLLDATE")));
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				rset.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	} // selectAll 끝.
	
	/**
	 * @param userId
	 * @return
	 */
	public Member selectByUserId(String userId) {
		
		Member m = null; // 조회된 한 명의 회원 정보
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
							SELECT *
							  FROM MEMBER
							 WHERE USERID = ?
				
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				m = new Member(rset.getInt("USERNO"),
							   rset.getString("USERID"),
							   rset.getString("USERPWD"),
							   rset.getString("USERNAME"),
							   rset.getString("GENDER"),
							   rset.getInt("AGE"),
							   rset.getString("EMAIL"),
							   rset.getString("PHONE"),
							   rset.getString("ADDRESS"),
							   rset.getDate("ENROLLDATE"));
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				rset.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
		
	} // selectByUserId 끝.
	
	public ArrayList<Member> selectByUserName(String keyword) {
		
		ArrayList<Member> list = new ArrayList<>(); // 조회된 회원정보 담을 리스트
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
							SELECT *
							  FROM MEMBER
							 WHERE USERNAME LIKE ?
				
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {

				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				list.add(m);
			}
			

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				rset.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	} // selectByUserName 끝.
	
	public int updateMember(Member m) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
						UPDATE MEMBER
						   SET USERPWD = ?
						     , EMAIL = ?
						     , PHONE = ?
						     , ADDRESS = ?
						 WHERE USERID = ?
				
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				conn.commit();
				
			} else {
				
				conn.rollback();
			}

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
		
	} // updateMember 끝.
	
	public int deleteMember(String userId) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
							DELETE
							  FROM MEMBER
							 WHERE USERID = ?
				
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				conn.commit();
				
			} else {
				
				conn.rollback();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}










