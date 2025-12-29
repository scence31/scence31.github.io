package com.kh.product.controller;

import java.util.ArrayList;

import com.kh.product.service.ProductService;
import com.kh.product.view.ProductView;
import com.kh.product.vo.Product;

public class ProductController {
		
		public void selectAll() {
		
			ArrayList<Product> list = new ProductService().selectAll();
			
			if(list.isEmpty()) {
				
				new ProductView().displayNoData("전체 조회 결과가 없습니다.");
		
			} else {
				
				new ProductView().displayList(list);
			}
		} // selectAll 끝
		
		public void insertProduct(String prCode,
							   	  String prBrand,
								  String prName,
								  int prPrice,
								  int prStock) {
			Product p = new Product(prCode, prBrand, prName, prPrice, prStock);
			
			int result = new ProductService().insertProduct(p);
			
			if(result > 0) {
				
				new ProductView().displaySuccess("제품 추가 성공");
				
			}else {
				
				new ProductView().displayFail("제품 추가 실패");
				
			}
			
			
		} // insertProduct 끝
		
		
		public void updateProduct(String prCode, 
							      String newBrand,
							      String newName,
							      int newPrice,
							      int newStock) {
			
			Product p = new Product();
			p.setPrCode(prCode);
			p.setPrBrand(newBrand);
			p.setPrName(newName);
			p.setPrPrice(newPrice);
			p.setPrStock(newStock);
			
			int result = new ProductService().updateProduct(p);
			
			if(result > 0) {
				
				new ProductView().displaySuccess("수정 성공");
				
			} else {
				
				new ProductView().displayFail("'" + prCode + "'" + "잘못된 입력입니다.");
			}
			
		}
		

	
	
	/**
	 * View에서 제품 삭제 요청시 중간단계
	 * @param prCode => 삭제할 제품 코드
	 */
	public void deleteProduct(String prCode) {
		
		int result = new ProductService().deleteProduct(prCode);
		// 조회가 아니고 DML인 경우 result
		
		if(result > 0) {
			// 삭제할 코드명을 제대로 검색한 경우
			
			new ProductView().displaySuccess("'" + prCode + "'" + "상품 삭제완료");
			
		} else {
			// 아닌 경우
			
			new ProductView().displayFail("'" + prCode + "'" + "상품 삭제실패");
		}
		
	} // deleteProduct 메소드 끝.
	
	/**
	 * View에서 품명 검색 요청시 중간단계
	 * @param nameKeyword => 검색시 조회될 품명키워드
	 */
	public void selectByName(String nameKeyword) {
		
		ArrayList<Product> list = new ProductService().selectByName(nameKeyword);
		// 조회인, 여러행인 경우 ArrayList
		
		if(list.isEmpty()) {
			// 검색한 품명 키워드가 있는 상품이 없는 경우
			
			new ProductView().displayNoData("'" + nameKeyword + "'" + " 품명에 해당하는 상품이 없습니다.");
		
		} else {
			// 없는경우
			
			new ProductView().displayList(list);
		}
		
	} // selectByName 메소드 끝.
	
	/**
	 * View에서 브랜드명 검색 요청시 중간단계
	 * @param brandKeyword => 검색시 조회될 브랜드명키워드
	 */
	public void selectByBrand(String brandKeyword) {
		
		ArrayList<Product> list = new ProductService().selectByBrand(brandKeyword);
		
		if(list.isEmpty()) {
			// 검색한 브랜드명 키워드가 있는 상품이 없는 경우
			
			new ProductView().displayNoData("'" + brandKeyword + "'" + " 브랜드에 해당하는 상품이 없습니다.");
		
		} else {
			// 없는 경우
			
			new ProductView().displayList(list);
			
		}
		
	}

}









