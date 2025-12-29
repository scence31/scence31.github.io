package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductView {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	
	public void mainMenu() {
		
		while(true) {
		System.out.println("!!메뉴 구성!!");
		
		System.out.println("1. 상품 전체 조회하기: ");
		System.out.println("2. 상품 추가하기");
		System.out.println("3. 상품명 검색하기");
		System.out.println("4. 상품정보 수정하기");
		System.out.println("5. 상품 삭제하기");
		System.out.println("0. 프로그램 종료");
		
		System.out.print("메뉴 선택: ");
		int mainMenu = sc.nextInt();
		sc.nextLine();
		
		switch(mainMenu) {
		case 1:
			selectAll();
			break;
		case 2:
			insertProduct();
			break;
		case 3:
			selectByProductName();
			break;
		case 4:
			updateProduct();
			break;
		case 5:
			deleteProduct();
			break;
		case 0:
			System.out.println("프로그램 종료합니다.");
			return;
			
		default:
			System.out.println("다시 선택해주세요.");
		
			}
		
		}
		
	} // mainMenu 메소드 끝.
	
	public void selectAll() {
		
		System.out.println("----상품 전체 조회----");
		
		pc.selectAll();
	} // selectAll 메소드 끝.
	
	public void insertProduct() {
		
		System.out.println("---상품 추가---");
		
		System.out.print("상품아이디: ");
		String productId = sc.nextLine();
		
		System.out.print("상품명: ");
		String productName = sc.nextLine();
		
		System.out.print("상품가격: ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("상품상세정보: ");
		String description = sc.nextLine();
		
		System.out.print("재고 수: ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		pc.insertProduct(productId, productName, price, description, stock);
		
		
		
	} // insertProduct 메소드 끝.
	
	public void selectByProductName() {
		
		System.out.println("----상품명 검색----\n");
		
		System.out.print("상품명 검색: ");
		String keyword = sc.nextLine();
		
		pc.selectByProductName(keyword);
		
	} // selectByProductName 메소드 끝.
	
	public void updateProduct() {
		
		System.out.println("---상품정보 수정---\n");
		
		System.out.print("수정할 상품의 아이디: ");
		String productId = sc.nextLine();
		
		System.out.print("품명 수정: ");
		String newProductName = sc.nextLine();
		
		System.out.print("가격 수정: ");
		int newPrice = sc.nextInt();
		sc.nextLine();
		
		System.out.print("상세정보 수정: ");
		String newDescription = sc.nextLine();
		
		System.out.print("재고량 수정: ");
		int newStock = sc.nextInt();
		sc.nextLine();
		
		pc.updateProduct(productId, newProductName, newPrice, newDescription, newStock);
		
	} // updateProduct 메소드 끝.
	
	public void deleteProduct() {
		
		System.out.println("---상품 삭제---\n");
		
		System.out.print("삭제할 상품 아이디: ");
		String productId = sc.nextLine();
		
		pc.deleteProduct(productId);
	} // deleteProduct 메소드 끝.
	
	// --- 출력용 ---
	
	public void displayList(ArrayList<Product> list) {
		
		System.out.println("조회된 데이터는 총: " + list.size() + "건 입니다.");
		
		for(Product p : list) {
			
			System.out.println(p);
		}
	}
	
	public void displayNoData(String message) {
		
		System.out.println(message);
	}
	
	public void displaySuccess(String message) {
		
		System.out.println(message);
	}
	
	public void displayFail(String message) {
		
		System.out.println(message);
	}

}
