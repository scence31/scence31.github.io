package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.model.view.MemberView;

/*
 * 컨트롤러 흐름
 * 1. 요청받은 값들을 하나의 VO 객체로 가공
 * 2. service로 요청받은 값을 전달, 나중에 결과받기
 * 3. 결과 응답
 * 
 */

public class MemberController {
	
	public void insertMember(String userId, String userPwd, String userName,
							 String gender, int age, String email,
							 String phone, String address) {
		
		// 1. 요청받은 값들 가공: Member 객체로 담자
		Member m = new Member(userId, userPwd, userName, gender, age, email,
							  phone, address);
		
		// 2.
		int result = new MemberService().insertMember(m);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("회원추가 성공");
			
		} else {
			
			new MemberView().displayFail("회원추가 실패");
			
		}
		
	} // insertMember 메소드 끝.
	
	public void selectAll() {
		
		ArrayList<Member> list = new MemberService().selectAll();
		
		if(list.isEmpty()) {
			
			new MemberView().displayNoData("결과 없습니다.");
		
		} else {
			
			new MemberView().displayList(list);
		}
		
	} // selectAll 메소드 끝.
	
	public void selectByUserId(String userId) {
		
		Member m = new MemberService().selectByUserId(userId);
		
		if(m == null) {
			
			new MemberView().displayNoData(userId + " 결과 없음");
			
		} else {
			
			new MemberView().displayOne(m);
			
		}
		
	} // selectByUserId 메소드 끝.
	
	public void selectByUserName(String keyword) {
		
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			
			new MemberView().displayNoData("검색결과 없습니다.");
		} else {
			
			new MemberView().displayList(list);
			
		}
	} // selectByUserName 메소드 끝.
	
	public void updateMember(String userId, String newPwd, String newEmail, 
							 String newPhone, String newAddress) {
		
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		int result = new MemberService().updateMember(m);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("수정 성공");
		
		} else {
			
			new MemberView().displayFail("수정 실패");
		}
				
		
	}
	
	public void deleteMember(String userId) {
		
		int result = new MemberService().deleteMember(userId);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("회원탈퇴 성공");
		} else {
			
			new MemberView().displayFail("회원탈퇴 실패");
		}
		
	}

}



