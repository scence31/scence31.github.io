package com.kh.shoes.model.controller;

import java.util.ArrayList;

import com.kh.shoes.model.service.ShoesService;
import com.kh.shoes.model.view.ShoesView;
import com.kh.shoes.model.vo.Shoes;

public class ShoesController {
	
	public void insertShoes(String shoesCode, String shoesName, String brand, int price, int stock, String status) {
		
		Shoes s = new Shoes(shoesCode, shoesName, brand, price, stock, status);
		
		int result = new ShoesService().insertShoes(s);
		
		if(result > 0) {
			
			new ShoesView().displaySuccess("신발 추가 성공");
			
		} else {
			
			new ShoesView().displayFail("신발 추가 실패");
		}
		
	} // insertShoes 메소드 끝.
	
	public void selectAll() {
		
		ArrayList<Shoes> list = new ShoesService().selectAll();
		
		if(list.isEmpty()) {
			
			new ShoesView().displayFail("조회 실패");
			
		} else {
			
			new ShoesView().displayList(list);
		}
		
	} // selectAll 메소드 끝.
	
	public void updateShoes(String shoesCode, String newName, String newBrand, int newPrice, int newStock, String newStatus) {
		
		Shoes s = new Shoes(shoesCode, newName, newBrand, newPrice, newStock, newStatus);
		
		int result = new ShoesService().updateShoes(s);
		
		if(result > 0) {
			
			new ShoesView().displaySuccess("신발 수정 성공");
			
		} else {
			
			new ShoesView().displayFail("'" + shoesCode + "'" + " 코드 없음");
		}
		
	} // updateShoes 메소드 끝.
	
	public void selectByShoesName(String keyword) {
		
		ArrayList<Shoes> list = new ShoesService().selectByShoesName(keyword);
		
		if(list.isEmpty()) {
			
			new ShoesView().displayFail("'" + keyword + "'" + " 검색어에 해당하는 신발 없음");
			
		} else {
			
			new ShoesView().displayList(list);
		}
		
	} // selectByShoesName 메소드 끝.
	
	public void selectByShoesCode(String shoesCode) {
		
		Shoes s = new ShoesService().selectByShoesCode(shoesCode);
		
		if(s == null) {	
			new ShoesView().displayFail("'" + shoesCode + "'" + " 코드에 해당하는 검색결과 없음");
		} else {	
			new ShoesView().displayOne(s);
		}		
	} // selectByPrice 메소드 끝.
	
	public void deleteShoes(String shoesCode) {
		
		int result = new ShoesService().deleteShoes(shoesCode);
		
		if(result > 0) {		
			new ShoesView().displaySuccess("신발 삭제 성공");
		} else {	
			new ShoesView().displayFail("'" + shoesCode + "'" + " 코드 없음");
		}
	} // deleteShoes 메소드 끝.

}








