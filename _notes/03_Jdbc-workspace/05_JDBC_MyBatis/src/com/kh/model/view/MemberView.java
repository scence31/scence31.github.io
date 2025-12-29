package com.kh.model.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberView {
	
	// Scanner 객체는 전역변수로 빼놓기
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	/**
	 * 메인화면담당 메소드
	 */
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("****회원관리프로그램*****");
			
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체조회");
			System.out.println("3. 회원 검색(아이디로)");
			
			System.out.println("4. 회원 이름검색(키워드로)");
			System.out.println("5. 회원정보 변경");
			System.out.println("6. 회원탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("------------------------");
			
			System.out.print("메뉴 선택: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				selectByUserId();
				break;
			case 4:
				selectByUserName();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("다시 입력하세요.");
			
			}
			
		}
		
	} // mainMenu 끝.
	
	/**
	 * 회원 추가용 화면
	 */
	public void insertMember() {
		
		System.out.println("---회원추가---\n");
		
		System.out.print("*아이디: ");
		String userId = sc.nextLine();
		
		System.out.print("*비밀번호: ");
		String userPwd = sc.nextLine();
		
		System.out.print("*이름: ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F): ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이: ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(숫자만): ");
		String phone = sc.nextLine();
		
		System.out.print("주소: ");
		String address = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age,
						email, phone, address);
		
	} // insertMember 끝.
	
	/**
	 * 
	 */
	public void selectAll() {
		
		System.out.println("---전체 회원 조회---");
		
		mc.selectAll();
		
	} // selectAll 끝.
	
	/**
	 * 
	 */
	public void selectByUserId() {
		
		System.out.println("---회원아이디 검색---\n");
		
		System.out.print("검색할 회원의 아이디: ");
		String userId = sc.nextLine();
		
		mc.selectByUserId(userId);
		
	} // selectByUserId 끝.
	
	/**
	 * 
	 */
	public void selectByUserName() {
		
		System.out.println("---회원 이름키워드 검색---\n");
		
		System.out.print("회원 이름키워드: ");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
		
	} // selectByUserName 끝.
	
	public void updateMember() {
		
		System.out.println("---회원정보 변경---\n");
		
		System.out.print("변경할 회원 아이디: ");
		String userId = sc.nextLine();
		
		System.out.print("*변경할 비번: ");
		String newPwd = sc.nextLine();
			
		System.out.print("변경할 이메일: ");
		String newEmail = sc.nextLine();
		
		System.out.print("변경할 전화번호(숫자만): ");
		String newPhone = sc.nextLine();
		
		System.out.print("변경할 주소: ");
		String newAddress = sc.nextLine();
		
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
		
	} // updateMember 끝.
	
	public void deleteMember() {
		
		System.out.println("---회원 탈퇴---\n");
		
		System.out.print("탈퇴할 회원 아이디: ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	} // deleteMember 끝.
	
	
	//---- 화면메소드 ----
	/**
	 * 요청 성공시 보일 화면
	 * @param message => 성공메시지
	 */
	public void displaySuccess(String message) {
		
		System.out.println(message);
		
	}
	
	/**
	 * 요청 실패시 보일 화면
	 * @param message => 실패메시지
	 */
	public void displayFail(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * @param message
	 */
	public void displayNoData(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * @param list
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.printf("조회된 데이터는 총 %d건 입니다.\n", list.size());
		
		for(Member m : list) {
			
			System.out.println(m);
		}
		
	}
	
	/**
	 * @param m
	 */
	public void displayOne(Member m) {
		
		System.out.println("조회 결과 1건: ");
		
		System.out.println(m);
		
	}

}

