package com.kh.chap01_list.part02_mvc.run;

import com.kh.chap01_list.part02_mvc.view.MusicView;

public class MusicRun {

	/*
	 * * MVC(Model, View, Controller) 패턴: 프로그램 작성시 코드를 역할별로 분리해서 짜는 기법
	 * M: 데이터관련코드, V: 화면관련코드, C: 기능관련코드
	 * 
	 */
	
	public static void main(String[] args) {

		MusicView mv = new MusicView(); // 1.
		mv.mainMenu();
		

	}
	
	
	// 1. 메인메뉴 메소드 호출

}
