package com.kh.view2;

import java.sql.Date;
import java.util.Scanner;

import com.kh.controller2.EmployeeController;

public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	
	public void mainMenu() {
		
		System.out.println("*****사원관리 프로그램*****\n");
		
		while(true) {
			System.out.println("1. 사원정보 추가");
			System.out.println("2. 사원정보 전체 조회");
			System.out.println("3. 사원 이름 키워드 검색");
			System.out.println("4. 사원 연락처 수정");
			System.out.println("5. 사원 퇴사처리");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("---------------------");
			System.out.println("메뉴 선택: ");
			int mainMenu = sc.nextInt();
			sc.nextLine();
			
			switch(mainMenu) {
			case 1:
				insertEmployee();
				break;
		/*	case 2:
				selectEmployee();
				break;
			case 3:
				searchName();
				break;
			case 4:
				updatePhone();
				break;
			case 5:
				updateHire();
				break;
		*/	case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("다시 입력하세요");
			}
		
		}
	} // mainMenu 메소드 끝.
	
	public void insertEmployee() {
		
		System.out.println("-----사원 추가-----\n");
		
		System.out.print("회원이름: ");
		String empName = sc.nextLine();
		
		System.out.print("회원 주민등록번호: ");
		String empNo = sc.nextLine();
		
		System.out.print("회원 이메일: ");
		String email = sc.nextLine();
		
		System.out.print("회원 전화번호: ");
		String phone = sc.nextLine();
		
		System.out.print("회원 부서코드: ");
		String deptCode = sc.nextLine();
		
		System.out.print("회원 직급코드: ");
		String jobCode = sc.nextLine();
		
		System.out.print("회원 급여등급: ");
		String salLevel = sc.nextLine();
		
		System.out.print("회원 급여: ");
		int salary = sc.nextInt();
		sc.nextLine();
		
		System.out.print("회원 보너스: ");
		double bonus = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("회원 관리자의 사번: ");
		String managerId = sc.nextLine();
		
		
		ec.insertEmployee(empName, empNo, email, phone, deptCode, jobCode, salLevel,
						  salary, bonus, managerId);

		
	} // insertEmployee 메소드 끝.
	
	// 서비스 요청 후 응답화면들 ====================================
	
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공: " + message);
	}
	
	public void displiayFail(String message) {
		
		System.out.println("서비스 요청 실패: " + message);
	}

}
