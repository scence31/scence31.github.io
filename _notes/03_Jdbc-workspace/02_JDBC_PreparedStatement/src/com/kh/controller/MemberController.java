package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

/*
 * Controller 흐름
 * 1) 요청시 전달값들을 하나의 VO객체로 가공
 * 2) DOA로 전달값 넘기면서
 * 3) 
 */


public class MemberController {
	
	// private MemberDao md = new MemberDao();
	// --> 전역변수로 빼서 사용해도 되긴 하지만, ~~
	/**
	 * 회원 추가 요청을 받아 처리하는 메소드
	 * @param userId
	 * @param userPwd
	 * @param userName
	 * @param gender
	 * @param age
	 * @param email
	 * @param phone
	 * @param address => 추가할 회원의 정보들
	 */
	public void insertMember(String userId, String userPwd, String userName, 
							 String gender, int age, String email, String phone,
							 String address) {
		
		Member m = new Member(userId, userPwd, userName, gender, age,
							  email, phone, address);
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("회원추가 성공");
			
		} else {
			
			new MemberView().displayFail("회원추가 실패");
		}
		
		
	} // insertMember 끝.
	
	/**
	 * 
	 */
	public void selectAll() {
		
		ArrayList<Member> list = new MemberDao().selectAll();
		
		if(list.isEmpty()) {
			
			new MemberView().displayNoData("조회 결과 없음");
			
		} else {
			
			new MemberView().displayList(list);
		}
	} // selectAll 끝.
	
	/**
	 * @param userId
	 */
	public void selectByUserId(String userId) {
		
		
		Member m  = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			
			new MemberView().displayNoData(userId + " 에 대한 검색결과 없음");
		} else {
			
			new MemberView().displayOne(m);
		}
		
	} // selectByUserId 끝.
	
	public void selectByUserName(String keyword) {
		
		ArrayList<Member> list  = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			
			new MemberView().displayNoData("'" + keyword + "'" + "에 해당하는 검색결과 없음");
			
		} else {
			
			new MemberView().displayList(list);
		}
		
	} // selectNyUserName 끝.
	
	public void updateMember(String userId, String newPwd,
							 String newEmail, String newPhone,
							 String newAddress) {
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		int result = new MemberDao().updateMember(m);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("수정 성공");
		} else {
			
			new MemberView().displayFail( "'" + userId + "'" + "잘못 입력, 수정 실패");
		}
		
	} // updateMember 끝.
	
	/**
	 * 회원 탈퇴 요청.
	 * @param userId => 탈퇴하게 할 회원 아이디
	 */
	public void deleteMember(String userId) {
		
		int result = new MemberDao().deleteMember(userId);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("'" + userId + "'" + " 회원탈퇴 성공");
		} else {
			
			new MemberView().displayFail("'" + userId + "'" + " 오류, 탈퇴 실패");
		}
		
	}

}






