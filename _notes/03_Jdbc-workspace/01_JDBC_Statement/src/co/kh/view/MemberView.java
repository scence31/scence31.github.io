package co.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

/*
 * * View
 * 
 * - 사용자가 보게 될 화면(시각적 요소) 담당
 * - 주로 입력문과 출력문을 이용해 콘솔에 화면 구성
 * 
 */

public class MemberView {
	
	// 전역변수로 Scanner 및 MemberController 객체 생성
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	// Alt + Shift + j => 파란색 주석(API주석)
	/**
	 * 사용자가 보게 될 첫화면(메인화면)
	 */
	public void mainMenu() {
		
		while(true) {

			System.out.println("***** 회원관리 프로그램 *****");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.println("-----------------------------");
			
			System.out.print("이용할 메뉴를 선택하세요: ");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
					System.out.println("없는 메뉴입니다. 다시 입력해주십쇼");
			}
			
		}
		
	} // main menu 메소드 끝
	
	
	/**
	 * 회원 추가용 화면
	 * 추가하고자 하는 회원의 정보를 입력받아서 회원 추가 요청을 보낼 것
	 */
	public void insertMember() {
		
		System.out.println("---회원 추가---\n");
		
		// 회원 추가 요청시 필요한 데이터들(MEMBER 테이블 기준으로 생각)
		// 아이디, 비번, 이름, 성별, 나이, 이메일, 전화번호, 주소
		// 아이디, 비번, 이름은 필수 입력사항(NOT NULL)
		
		System.out.print("* 아이디: ");
		String userId = sc.nextLine();
		
		System.out.print("* 비밀번호: ");
		String userPwd = sc.nextLine();

		System.out.print("* 이름: ");
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
		
		// 회원가입처리
		// MemberController의 어떤 메소드를 호출하면서
		// 방금 입력받은 값들을 넘기면서 회원추가요청 보내기
		
		mc.insertMember(userId, userPwd, userName, gender,
						age, email, phone, address);
		
		
	} // insertMember 메소드 끝.
	
	/**
	 * 회원 전체 조회 요청화면을 담당하는 메소드
	 */
	public void selectAll() {
		
		System.out.println("-------회원 전체 조회------\n");
		
		// MemberController의 어떤 메소드를 호출하면서 회원전체조회 요청
		mc.selectAll();
		
		
		
	} // selectAll 메소드 끝.
	
	/**
	 * 검색할 아이디를 사용자에게 입력받은 후 조회요청화면을 담당하는 메소드
	 */
	public void selectByUserId() {
		
		System.out.println("-----회원 아이디로 검색----\n");
		
		System.out.print("검색할 회원의 아이디: ");
		String userId = sc.nextLine();
		
		// 입력받은 아이디를 Controller의 어떤 메소드를 호출하면서 넘기기
		// -> 아이디 검색요청
		mc.selectByUserId(userId);
		
	} // selectByUserId 메소드 끝.
	
	/**
	 * 검색할 이름키워드를 사용자에게 입력받고 검색요청화면을 담당하는 메소드
	 */
	public void selectByUserName() {
		
		System.out.println("----회원 이름 키워드 검색----\n");
		
		System.out.print("회원 이름 키워드 입력: ");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
		
	} // selectByUserName 메소드 끝.
	
	/**
	 * 변경할 회원 아이디를 사용자에게 입력받고, 변경할 정보들을 입력받음(비번, 이메일, 전화번호, 주소)
	 * 그리고 변경 요청 화면을 담당하는 메소드
	 */
	public void updateMember() {
		
		System.out.println("----회원 정보 변경----\n");
		
		// 데이터 갱신(update) 기능 구현시 고려할 사항
		// 어떤 데이터를, 어떻게 수정할 것이냐
		// 회원 아이디를 통해 변경할 회원 한 명만 정확히 골라내기(unique) => where절
		// 비번, 이메일, 전화번호, 주소 변경 => set절
		
		System.out.print("변경할 회원 아이디: ");
		String userId = sc.nextLine();
		
		System.out.print("*변경할 비밀번호: ");
		String newPwd = sc.nextLine();
		
		System.out.print("변경할 이메일: ");
		String newEmail = sc.nextLine();
		
		System.out.print("변경할 전화번호(숫자만): ");
		String newPhone = sc.nextLine();
		
		System.out.println("변경할 주소: ");
		String newAddress = sc.nextLine();
		
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
		
		
	} // updateMember 메소드 끝.
	
	/**
	 * 탈퇴할 아이디 입력받은 후 삭제 요청하는 화면 담당메소드
	 */
	public void deleteMember() {
		
		System.out.println("---회원 탈퇴--- \n");
		
		System.out.print("탈락할 회원의 아이디: ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	} // deleteMember 메소드 끝.
	
	// -----------------------------------
	// 서비스 요청 처리 후 사용자가 보게 될 응답화면들
	
	/**
	 * 서비스 요청 성공시 보게 될 화면
	 * @param message => 성공시 보여줄 메시지
	 */
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공: " + message);
	}
	
	/**
	 * 서비스 요청 실패시 보게 될 화면
	 * @param message => 실패시 보여줄 메시지
	 */
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패: " + message);
	}
	
	/**
	 * 조회 서비스 요청시 조회결과가 없을 경우 보게 될화면
	 * @param message => 사용자에게 출력할 메시지 내용
	 */
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 조회 결과가 여러 개일 경우 반복문을 통해 보여질 응답화면
	 * @param list => 여러 개의 조회결과들이 담겨있는 리스트
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.println("조회된 데이터는 다음과 같습니다. (총 " + list.size() + "건) ");
		
		for(Member m : list) {
			
			System.out.println(m);
			
		}
	}
	
	/**
	 * 조회 결과가 한 개일 경우 보일 응답화면
	 * @param m => 한 개의 조회결과가 담겨있는 객체
	 */
	public void displayOne(Member m) {
		
		System.out.println("조회된 데이터는 다음과 같습니다. (총 1건)" );
		System.out.println(m);
		
	}
	
	
}



































