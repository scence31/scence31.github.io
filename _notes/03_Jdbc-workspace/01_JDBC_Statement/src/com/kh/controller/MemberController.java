package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

import co.kh.view.MemberView;

/*
	 * * Controller
	 * 
	 * - View에서 요청한 기능을 처리함
	 * - View의 해당 메소드로 전달된 데이터를 가공처리 한 후 DAO 메소드 호출시 전달
	 * - DAO로부터 반환받은 결과에 따라 사용자가 보게될 응답화면 view 결정
	 * 
	 * * Controller 메소드 코드 흐름
	 * 1) 매개변수로 전달받은 값들을 하나의 VO 객체에 담기(가공처리)
	 * 2) DAO단의 메소드를 호출하면서 VO 객체를 전달 후 결과 받기
	 * 3) DAO로부터 전달받은 결과에 따라서 응답화면을 지정
	 * 
	 */

public class MemberController {
	
	/**
	 * 사용자의 회원추가 요청을 처리하는 메소드
	 * @param userId
	 * @param userPwd
	 * @param userName
	 * @param gender
	 * @param age
	 * @param email
	 * @param phone
	 * @param address => 사용자가 회원 추가 요청시 입력했던 값들
	 */
	public void insertMember(String userId, String userPwd,
							 String userName, String gender,
							 int age, String email,
							 String phone, String address) {
		
		// 1) 전달된 데이터들을(매개변수) vo의 Member 객체로 가공하기
		Member m = new Member(userId, userPwd, userName, gender, age,
								email, phone, address);
		
		// 2) 가공된 Member 객체를 DAO의 메소드로 넘기면서 호출 및 그 결과값을 DAO로부터 받기
		int result = new MemberDao().insertMember(m);
		
		// 3) 결과에 따른 응답화면 지정
		if(result > 0) { // 성공, 성공화면 --> View에서 메소드 정의 후 호출
			
			new MemberView().displaySuccess("회원 추가 성공");
			
		} else { // 실패, 실패화면 --> View에서 메소드 정의 후 호출
			
			new MemberView().displayFail("회원 추가 실패");
			
		}
		
	} // insertMember 메소드 끝.
	
	/**
	 * 사용자의 회원전체조회 요청을 처리해주는 메소드
	 */
	public void selectAll() {
		
		// 1) 전달된 데이터들을 Member 객체로 가공 ==> 없으면 패스
		
		// 2) DAO로 메소드 호출 후 결과받기
		// 요청시 전달값이 없어서 매개변수 없이 그냥 호출
		// SELECT문을 이용한 조회 기능은 크게 2가지로 나뉨
		// - 여러행 조회: 2개 이상의 데이터가 조회될 경우(ArrayList)
		// - 단일행 조회: 많아봤자 최대 1개 데이터가 조회될 경우(VO)
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 3) 결과에 따른 응답화면 지정
		// > 조회결과 유무 판별 후 사용자가 보게 될 화면을 각각 지정
		if(list.isEmpty()) {
			
			new MemberView().displayNodata("전체 조회된 결과가 없습니다.");
			
		} else {
			
			new MemberView().displayList(list);
			
			
		}
		
	} // selectAll 메소드 끝.
	
	/**
	 * 사용자의 아이디로 검색요청을 처리해주는 메소드
	 * @param userId => 검색할 회원 아이디(검색어)
	 */
	public void selectByUserId(String userId) {
		
		// 1) 요청시 전달값을 하나의 VO객체로(Member) 가공
		// 어차피 전달값이 userId 하나라서 가공 패스(전달값 둘 이상부터 가공하면 됨)
		
		// 2) DAO로 전달값을 넘기면서 호출 후 결과받기
		Member m = new MemberDao().selectByUserId(userId);
		// 아이디로 검색할 예정임(UNIQUE 제약조건 걸림) --> 단일행 조회
		// 일치하는 아이디로 검색하면 많아봤자 최대 1행만 나옴
		
		// 3) DAO로부터 전달받은 결과를 가지고 응답화면 지정
		if(m == null) { // 조회결과 없음
			
			new MemberView().displayNodata(userId + "에 해당하는 결과가 없습니다.");
			
			
		} else { // 조회결과 있음
			
			new MemberView().displayOne(m);
			
			
		}
		
	} // selectByUserId 메소드 끝.
	
	
	/**
	 * 사용자 회원명 키워드로 검색 요청시 처리해주는 메소드
	 * @param keyword => 사용자가 입력한 회원명 검색어 키워드
	 */
	public void selectByUserName(String keyword) {
		
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		// 여러행 조회(동명이인 등)
		
		
		
		if(list.isEmpty()) {
			
			new MemberView().displayNodata(keyword + "에 대한 검색결과 없음");
			
			
		} else {
			
			new MemberView().displayList(list);
			
		}
	} // selectByUserName 메소드 끝.
	
	/**
	 * 회원정보 수정 요청을 받아 처리해주는 메소드
	 * @param userId => 변경할 회원 아이디
	 * @param userPwd
	 * @param newEmail
	 * @param newPhone
	 * @param newAddress => 변경할 내용들
	 */
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
			
			new MemberView().displaySuccess("회원정보 변경 성공");
		} else {
			
			new MemberView().displayFail("회원정보 변경 실패");
		}
		
		
	} // updateMember 메소드 끝
	
	/**
	 * 사용자 회원탈퇴 요청 받아서 처리해주는 메소드
	 * @param userId => 탈락시킬 회원 아이디
	 */
	public void deleteMember(String userId) {
		
		int result = new MemberDao().deleteMember(userId);
		
		if(result > 0) {
			
			new MemberView().displaySuccess("회원탈퇴 성공");
		} else {
			
			new MemberView().displayFail("회원탈퇴 실패");
		}
		
	} // deleteMember 메소드 끝.

}

















