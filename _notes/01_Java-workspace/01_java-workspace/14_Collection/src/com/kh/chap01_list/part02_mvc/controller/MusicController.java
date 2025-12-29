package com.kh.chap01_list.part02_mvc.controller;

import java.util.ArrayList;

import com.kh.chap01_list.part02_mvc.model.vo.Music;

// Controller 단: 사용자의 요청을 받아서 해당 기능을 처리해주는 코드
// > 출력문, 입력문 사용 안함
public class MusicController {
	
	private ArrayList<Music> list = new ArrayList<>(); // 0.
	
	{  // 5.
		list.add(new Music("Golden", "헌트릭스"));
		list.add(new Music("Sdoa Pop", "사자보이즈"));
		list.add(new Music("뛰어(JUMP)", "블랙핑크"));
		list.add(new Music("Dirty Work", "에스파"));
		
	}
	
	public int insertMusic(String title, String artist) { // 1, 2
		
		int before = list.size();
		list.add(new Music(title, artist));
		int after = list.size();
				
		return after - before;
		// 수정했음 마지막에 모름 원래 list. void
		
	}
	
	public ArrayList<Music> selectMusicList() {
		
		
		return list;
	}
	
	public ArrayList<Music> searchMusic(String Keyword) { // 1, 2
		
		ArrayList<Music> searchedList = new ArrayList<>(); // 4-1.
		
		for(int i = 0; i < list.size(); i++) { // 4-1
			
			if(list.get(i).getTitle().contains(Keyword)) { // 4-1
				
				searchedList.add(list.get(i)); // 4-2.
			}
			
			
		}
		
		return searchedList;
		
	}
	
	public int deleteMusic(String title) { // 1, 2
		
		int result = 0; // 7.
		
		for(int i = 0; i < list.size(); i++) { // 6-1.
			
			if(list.get(i).getTitle().equals(title)) {
				
				list.remove(i);
				i--; // 6-2.
				result++; // 7.
			}
			
		}
		
		return result; // 7.
		
	}
	
	public int updateMusic(String title, String upTitle, String upArtist) {
		
		
		int result = 0; // 수정된 곡 개수를 담아낼 변수
		
		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i).getTitle().equals(title)) {
				
				// list.set(i, new Music(upTitle, upArtist)); // 9. 1
				
				list.get(i).setTitle(upTitle); // 9. 2
				list.get(i).setArtist(upArtist);
				
				result++;
			}
			
		}
		
		// 수정 완료, 한 곡 이상이라도 수정되었다면 result > 0, 양수 --> 반환형 int로 수정
		return result;
		
	}

	
	
	// 0. 전역변수로 음악 정보를 담을 수 있는 저장소 만들기 ArrayList 활용
	// 1. 저장소에 새로운 곡을 추가시키는 기능담당 메소드 만들기
	// 2. insertMusic이 view의 5번 부분과 호환됨 흐름 정리..?
	// 3. 화면과 관련된 출력문은 배제하고 코드 작성, view에서 출력함
	
	// 4-1. 검색기준? == 제목에 keyword 포함? --> 검색결과를 담아둘 ArrayList 생성, for로 전체 다 찾아보기
	// 4-2. searchedList에는 searchedList의 new 생성, for문과 if문을 통해 검색 결과가 담겨있음
	
	// 5. 초기화블록, 해당 저장소에 들어갈 Music 객체를 add 할 수 있음.
	
	// 6-1. 곡 제목이 정확히 일치할 경우에만 삭제, for로 전체 다 찾아보기
	// 6-2. remove는 i--; 해줘야댐 데이터 한 칸씩 땡겨와서
	// 7. 만약 title 기준으로 일치하는 곡 제목을 찾지 못했을 경우 result == 0, 찾으면 result > 0
	//    반환형 int로 수정

	// 8. 곡 제목이 정확히 일치할 경우에만 수정
	// 9. 수정방법 1, 2
}



