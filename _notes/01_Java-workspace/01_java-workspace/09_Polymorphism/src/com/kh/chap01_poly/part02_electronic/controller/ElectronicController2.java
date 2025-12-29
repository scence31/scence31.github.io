package com.kh.chap01_poly.part02_electronic.controller;

import com.kh.chap01_poly.part02_electronic.model.vo.Electronic;

public class ElectronicController2 {
	
	
	// 용산 전자상가에 새로 차린 가게
	// 어떤 종류의 전자제품이든 다 보관할 수 있는 창고를 "필드"로써 구현
	// 접근제한자 자료형[] 필드명 = 값;
	private Electronic[] elec = new Electronic[3]; // 자료형에 참조자료형도 가능! 당연
	// [0] [1] [2] : 세 칸짜리 창고가 만들어짐
	
	// 제품을 납품받아서 창고의 각 칸에 집어넣는 기능
	public void insert(Electronic any, int index) {
		
		elec[index] = any;
	}
	
	// 창고의 x번 칸에 들은 제품을 꺼내서 내어주는 기능
	public Electronic select(int index) {
		
		return elec[index];
	}
	
	// 창고에 있는 모든 제품을 한번에 다 꺼내서 내어주는 기능 (오버로딩)
	public Electronic[] select() {
		
		return elec;
	}
}
