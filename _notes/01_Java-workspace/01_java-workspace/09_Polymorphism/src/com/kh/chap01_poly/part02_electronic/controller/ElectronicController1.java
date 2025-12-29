package com.kh.chap01_poly.part02_electronic.controller;

import com.kh.chap01_poly.part02_electronic.model.vo.Desktop;
import com.kh.chap01_poly.part02_electronic.model.vo.NoteBook;
import com.kh.chap01_poly.part02_electronic.model.vo.Tablet;

// 다형성을 적용하기 전
public class ElectronicController1 {
	
	// 용산 전자 상가에 새로 차린 가게
	// 각 전자제품을 담을 상자를 "필드"로 구현
	// 접근제한자 자료형 필드명;
	private Desktop desk; // 데스크탑 제품을 담을 상자
	private NoteBook note; // 노트북 제품을 담을 상자
	private Tablet tab; // 테블릿 제품 담을 상자
	
	// 제품을 납품받았을 때 해당 제품을 알맞은 상자에 넣는 기능
	// - Desktop 제품 포장용 메소드 (오버로딩 적용)
	public void insert(Desktop d) {
		
		desk = d;
	}

	// - 노트북 제품 포장용 메소드 (오버로딩 적용)
	public void insert(NoteBook n) {
		
		note = n;
	}
	
	// - 테블릿 제품 포장용 메소드 (오버로딩 적용)
	public void insert(Tablet t) {
		
		tab = t;
	}
	
	// 해당 제품을 상자로부터 꺼내서 내어주는 기능
	// - 데스크탑 제품 꺼내주는 메소드
	public Desktop selectDesktop() {
		
		return desk;
	}
	
	// - 노트북 제품 꺼내주는 메소드
	// > 이번에는 매개변수가 동일하기 때문에 오버로딩 적용 불가
	//   (메소드명을 각각 지어줘야함)
	public NoteBook selectNoteBook() {
		
		return note;
	}
	public Tablet selectTablet() {
		
		return tab;
	}
	
}















