package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Member;

public class MemberDao {
	
	/*
	 * * 기존의 방식
	 * 
	 * - DAO 클래스의 메소드에서 사용자가 요청할 때마다 실행해야하는 SQL문을
	 * 자바 소스코드 내에 직접 String 타입으로 명시적으로 작성함 => 정적코딩방식(하드코딩)
	 * 
	 * - 문제점: SQL 구문을 수정할 일이 있을 경우 자바 소스코드를 수정하는 셈임.
	 * 
	 * - 해결방법: 외부파일 .xml 만들어서 실시간으로 SQL문을 동적으로 읽어들이고 실행하기 => 동적코딩방식
	 * 
	 */
	
	// 생성자부로, 서비스단에서 DAO 메소드를 호출할 때마다
	// new MemberDao().XXX();와 같이 메소드 체이닝을 하는데,
	// 해당 메소드가 호출되기 전에 기본생성자가 먼저 호출됨을 이용하여
	// 메소드마다 상단에 먼저 실행되어야 하는 공통코드를 기본생성자에 작성할 것임!
	
	// 쿼리문을 키+밸류 형태로 담아둘 전역변수
	private Properties prop = new Properties();
	
	public MemberDao() {
		
		try {
			
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param conn => DB 접속정보가 담긴 커넥션 객체
	 * @param m => 추가할 회원 정보
	 * @return => 처리된 행 개수
	 */
	public int insertMember(Connection conn, Member m) {
		
		int result = 0; // 처리된 행의 개수를 담을 변수
		PreparedStatement pstmt = null; // SQL문 보관 및 실행 후 결과를 받기 위한 변수
		
		String sql = prop.getProperty("insertMember");
		
		try {
						
			pstmt = conn.prepareStatement(sql); // 쿼리문좀 미리 보관하고 있어줘
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			
			result = pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		// 서비스로 결과반환
		return result;
		
	} // insertMember 끝.
	
	public ArrayList<Member> selectAll(Connection conn) {
		
		ArrayList<Member> list = new ArrayList<>();

		PreparedStatement pstmt = null; // 완성된 쿼리문도 상관 없음.
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAll");
		
		try {
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		// 서비스로 반납
		return list;
		
	} // selectAll 끝.
	
	public Member selectByUserId(Connection conn, String userId) {
		
		Member m = null; // 조회된 한 명의 회원 정보
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
		try {
			
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			}
		
		return m;
		
	} // selectByUserId 끝.
	
	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		
		ArrayList<Member> list = new ArrayList<>(); // 조회된 회원정보 담을 리스트
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserName");
		
		try {
			
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		return list;
	} // selectByUserName 끝.
	
	public int updateMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
			
		}
		
		return result;
	}
		
	 // updateMember 끝.
	
	public int deleteMember(Connection conn, String userId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
			
		}
		
		return result;
		
	} // deleteMember 끝.

}










