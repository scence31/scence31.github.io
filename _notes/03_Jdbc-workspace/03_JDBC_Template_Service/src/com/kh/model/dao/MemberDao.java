package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Member;

public class MemberDao {
	
	/**
	 * @param conn => DB 접속정보가 담긴 커넥션 객체
	 * @param m => 추가할 회원 정보
	 * @return => 처리된 행 개수
	 */
	public int insertMember(Connection conn, Member m) {
		
		int result = 0; // 처리된 행의 개수를 담을 변수
		PreparedStatement pstmt = null; // SQL문 보관 및 실행 후 결과를 받기 위한 변수
		
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
		
		String sql = """
						SELECT *
						   FROM MEMBER
						  ORDER BY USERNO ASC
				""";
		
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
		
		String sql = """
							SELECT *
							  FROM MEMBER
							 WHERE USERID = ?
				
				""";
		
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
		
		String sql = """
							SELECT *
							  FROM MEMBER
							 WHERE USERNAME LIKE ?
				
				""";
		
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
		
		String sql = """
						UPDATE MEMBER
						   SET USERPWD = ?
						     , EMAIL = ?
						     , PHONE = ?
						     , ADDRESS = ?
						 WHERE USERID = ?
				
				""";
		
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
		
		String sql = """
							DELETE
							  FROM MEMBER
							 WHERE USERID = ?
				
				""";
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










