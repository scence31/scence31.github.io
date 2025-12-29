package com.kh.chap01_list.part02_mvc.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.chap01_list.part02_mvc.controller.MusicController;
import com.kh.chap01_list.part02_mvc.model.vo.Music;

// View 단: 화면을 담당하는 코드만 작성할 것
// > 출력문, 입력문, 
public class MusicView {
	
	private Scanner sc = new Scanner(System.in); // 전역변수로 생성
	private MusicController mc = new MusicController(); // 5
	
	
	// 메인화면: 프로그램 실행시 제일 처음으로 보여지는 화면
	public void mainMenu() {
		
		while(true) { // 1.
			System.out.println("~~~멜론 음원~~~");
			System.out.println("1.새로운 곡 추가");
			System.out.println("2. 곡 전체 조회");
			System.out.println("3. 특정 곡 검색");
			System.out.println("4. 특정 곡 삭제");
			System.out.println("5. 특정 곡 수정");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("-------------------");
			System.out.print("메뉴 입력: "); // 2.
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			
			case 1:
				insertMusic(); // 4-1.
				break;
			case 2:
				selectMusic(); // 4-1. 비슷 ~
				break;
			case 3:
				searchMusic();
				break;
			case 4:
				deleteMusic();
				break;
			case 5:
				updateMusic();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다. 안녕히 가세요~!");
				return; // 3.
			default:
				System.out.println("없는 메뉴입니다. 다시 입력하세요");
			
			}
			
		}
	}		
		public void insertMusic() { // 4-2.
			
			System.out.println("---곡 추가---");
			
			System.out.print("곡명 입력: ");
			String title = sc.nextLine();
			
			System.out.print("가수명 입력: ");
			String artist = sc.nextLine();
			
			int result = mc.insertMusic(title, artist);
			
			if(result > 0) {
				
				System.out.println("성공적으로 추가되었습니다.");
				
			} else {
				
				System.out.println("곡 추가 실패");
			}
			
		
	} // insertMusic 메소드 끝
		
		public void selectMusic() { // 6.
			
			System.out.println("--- 전체 곡 조회 ---");
			
			ArrayList<Music> list = mc.selectMusicList(); // 7. list 넘김 Controller로
			
			if(list.isEmpty()) { // 8.
				
				System.out.println("현재 존재하는 곡이 없습니다.");
			} else {
				
				for(int i = 0; i < list.size(); i++) {
					
					System.out.println(list.get(i).toString());
				}
			}
		} // selectMusicList 메소드 끝
		
		public void searchMusic() { // 6.
			
			System.out.println("----특정 곡 검색----");
			
			System.out.print("검색할 곡명 키워드: "); // 7. keyword 넘김 Controller로 
			String keyword = sc.nextLine();
			
			ArrayList<Music> searchedList = mc.searchMusic(keyword);
			
			if(searchedList.size() == 0) { // 8.과 비슷
				
				System.out.println("검색결과가 없습니다.");
				
			} else {
				
				System.out.println("검색결과는 총 " + searchedList.size() + "건 입니다.");
				
				for(Music m : searchedList) {
					
					System.out.println(m);
					
				}
			}
			
		} // searchMusic 메소드 끝
		
		public void deleteMusic() { // 6.
			
			System.out.println("----곡 삭제----");
			
			System.out.print("삭제할 곡명: "); // 7. title 넘김 Controller로
			String title = sc.nextLine();
			
			int result = mc.deleteMusic(title); // 9.
			
			if(result > 0) {
				
				System.out.println("성공적으로 삭제되었습니다.");
				
			} else {
				
				System.out.println("삭제할 곡을 찾지 못했습니다.");
				
			}
			
		} // deleteMusic 메소드 끝
		
		public void updateMusic() { // 6.
			
			System.out.println("---곡 수정---");
			
			System.out.print("기존 곡명: ");
			String title = sc.nextLine();
			
			System.out.print("수정할 내용(곡명): ");
			String upTitle = sc.nextLine();
			
			System.out.print("수정할 내용(가수명): ");
			String upArtist = sc.nextLine();
			
			int result = mc.updateMusic(title, upTitle, upArtist);
			
			if(result > 0) {
				
				System.out.println("성공적으로 수정되었습니다.");
				
			} else {
				
				System.out.println("수정할 곡을 찾지 못했습니다.");
			}
		}
		
		
		
			
	
		// 1. 사용자가 종료하기 전까지 끝나지 않도록 무한반복
		// 2. Scanner 있으면 거기서 잠깐 무한반복 멈추고 입력하면 다시 무한반복됨
		// 3. return 만나면 와일트루 반복구문 빠져나감, break는 스위치만
		// 4-1. while문 곡 추가부분을 새로운 메소드를 switch에서 insultMusic 메소드 생성. 로직
		// 4-2. 4-1에 해당하는 메소드 작성(5번과 호환?)
		// 5. Controller로 기능 요청, Controller의 메소드 호출구문(insertMusic)도 view랑 이름 똑같이 맞추기
		//    (MusicController new 생성자 --> 전역변수로 빼주기 어차피 다 써서)
		// 6. selectMusic도 반복.. 또 반복
		// 7. 조회 결과를 Controller 메소드에 요청해서 다시 View에서 받아 View가 출력
		// 8. 컬렉션 사용 isEmpty(), list.size()
		
		// 9. controller에서 result 관련 처리
		
		/*
		 * 각 기능별 생각해볼 것
		 * 1) 새로운 곡 추가: 
		 * 2) 조회: 
		 * 3) 검색: 
		 * 4) 삭제: 
		 * 5) 수정: 
		 * 
		 */
}	
