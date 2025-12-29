package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.view.ProductView;

public class ProductController {
	
	public void selectAll() {
		

		ArrayList<Product> list = new ProductDao().selectAll();
		
		if(list.isEmpty()) {
			
			new ProductView().displayNoData("조회된 데이터 없음");
		} else {
			
			new ProductView().displayList(list);
		}
		
	} // selectAll 메소드 끝.
	
	public void insertProduct(String productId, String productName, int price,
							  String description, int stock) {
		
		Product p = new Product(productId, productName, price, description, stock);
		
		int result = new ProductDao().insertProduct(p);
		
		if(result > 0) {
			
			new ProductView().displaySuccess("회원 추가 성공");
			
			
		} else {
			
			new ProductView().displayFail("회원 추가 실패");
			
		}
		
	} // insertProduct 메소드 끝.
	
	public void selectByProductName(String keyword) {
		
		ArrayList<Product> list = new ProductDao().selectByProductName(keyword);
		
		if(list.isEmpty()) {
			
			new ProductView().displayNoData(keyword + "에 대한 검색 결과 없습니다.");
			
		} else {
			
			new ProductView().displayList(list);
		}
		
	} // selectByProductName 메소드 끝.
	
	public void updateProduct(String productId, String newProductName, int newPrice,
							  String newDescription, int newStock) {
		
		Product p = new Product(productId, newProductName, newPrice,
				  newDescription, newStock);
		
		int result = new ProductDao().updateProduct(p);
		
		if(result > 0) {
			
			new ProductView().displaySuccess("수정 성공");
		} else {
			
			new ProductView().displayFail(productId + " 오류로 수정 실패, 아이디 재입력하십시오.");
		}
		
	} // updateProduct 메소드 끝.
	
	public void deleteProduct(String productId) {
		
		int result = new ProductDao().deleteProduct(productId);
		
		if(result > 0) {
			
			new ProductView().displaySuccess("삭제 성공");
		} else {
			
			new ProductView().displayFail(productId + " 오류로 삭제 실패, 아이디 재입력하십시오.");
		}
		
	}

}
