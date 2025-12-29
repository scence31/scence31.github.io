package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

/*
 * * Service단
 * - JDBC과정에서 필요한 커넥션 객체와 관련된 구문들을 작성하는 부분
 * - 커넥션 객체는 하나의 트랜잭션 단위를 나타냄(통로 열기 -> 기능관련쿼리문들 실행 -> 통로닫기)
 * 
 * - DAO단 메소드 한 개 == SQL문 한 개
 * - Service단 메소드 한 개 == 트랜잭션 한 개
 * 
 * 원래 컨트롤러에서 모든 코드작성 => DAO로 이관 => 서비스로 이관
 * low controller, fat service
 * 
 * * 최종 MVC패턴 구조
 * Run --> MemberView --> MemberController --> MemberSerivce --> MemberDao --> DB
 * 	  <-----------------------------------------------------------------------
 * 
 * * Service 코드 흐름
 * 1) 커넥션 객체 생성(DB와의 연결통로를 여는 작업)
 * 2) 커넥션 객체와 컨트롤러로부터 받은 전달값을 DAO로 넘기면서 메소드 호출 후 결과받기
 * 3) DML문의 경우 트랜잭션 처리하기(커넥션 객체에서 제공하는 메소드로)
 * 4) 커넥션 객체 반납
 * 5) 컨트롤러로 결과 반환
 * 
 * 
 */

public class MemberService {
	
	/**
	 * 회원추가기능을(트랜잭션) 담당하는 메소드
	 * @param m => 추가할 회원 정보
	 * @return => 처리된 행 개수
	 */
	public int insertMember(Member m) {
		
		// 1) 커넥션 객체 생성(템플릿에 공통코드 만든거 메소드 호출)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) 커넥션 객체 conn과 전달값 m을 DAO로 넘기면서 메소드 호출 후 결과받기
		int result = new MemberDao().insertMember(conn, m);
		
		// 3) DML문이면 트랜잭션 처리
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		// 컨트롤러로 결과반납
		return result;
	} // insertMember 끝.
	
	/**
	 * 회원전체조회기능을(트랜잭션) 담당하는 메소드
	 * @return => 조회된 전체회원의 정보들
	 */
	public ArrayList<Member> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		// 컨트롤러 객체 반납
		JDBCTemplate.close(conn);
		
		// 컨트롤러 반환
		return list;
		
	} // selectAll 끝.
	
	/**
	 * 회원 아이디로 검색기능을(트랜잭션) 담당하는 메소드
	 * @param userId => 검색할 아이디
	 * @return => 검색된 회원 한 명의 정보
	 */
	public Member selectByUserId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return m;
		
		
	} // selectByUserId 끝.
	
	public ArrayList<Member> selectByUserName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	} // selectByUserName 끝.
	
	public int updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	} // updateMember 끝.
	
	public int deleteMember(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId);
		
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
			
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	} // deleteMember 끝.

}














