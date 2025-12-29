package com.kh.shoes.model.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.shoes.model.controller.ShoesController;
import com.kh.shoes.model.vo.Shoes;

public class ShoesView {
	
	private Scanner sc = new Scanner(System.in);
	private ShoesController shc = new ShoesController();
	
	
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("=======신발조회=======\n");
			System.out.println("1. 신발 추가");
			System.out.println("2. 신발 조회");
			System.out.println("3. 신발 수정(코드입력)");
			System.out.println("4. 신발 검색(품명입력)");
			System.out.println("5. 신발 검색(코드입력)");
			System.out.println("6. 신발 삭제(코드로)");
			System.out.println("0. 프로그램 종료");
			System.out.println("======================");
			
			System.out.print("메뉴 선택: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			
			case 1:
				insertShoes();
				break;
				
			case 2:
				selectAll();
				break;
				
			case 3:
				updateShoes();
				break;
				
			case 4:
				selectByShoesName();
				break;
				
			case 5:
				selectByShoesCode();
				break;
				
			case 6:
				deleteShoes();
				break;
				
			case 0:
				System.out.println("프로그램 종료");
				return;
				
			default :
					System.out.println("다시 선택하세요.");
			}
		}
	} // 메인메뉴 끝
	
	public void insertShoes() {
		
		System.out.println("===신발 추가===\n");
		
		System.out.print("코드: ");
		String shoesCode = sc.nextLine();
		
		System.out.print("품명: ");
		String shoesName = sc.nextLine();
		
		System.out.print("브랜드명: ");
		String brand = sc.nextLine();
		
		System.out.print("가격: ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("재고량: ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		System.out.print("판매진행여부(Y/N): ");
		String status = sc.nextLine().toUpperCase();
		
		shc.insertShoes(shoesCode, shoesName, brand, price, stock, status);
		
	} // 신발추가 메소드 끝
	
	public void selectAll() {
		
		System.out.println("====전체 조회====");
		
		shc.selectAll();
		
	} // 신발 조회 메소드 끝.
	
	public void updateShoes() {
		
		System.out.println("===신발 수정===\n");
		
		System.out.print("수정할 신발코드: ");
		String shoesCode = sc.nextLine();
		
		System.out.print("품명 수정: ");
		String newName = sc.nextLine();
		
		System.out.print("브랜드명 수정: ");
		String newBrand = sc.nextLine();
		
		System.out.print("가격 수정: ");
		int newPrice = sc.nextInt();
		sc.nextLine();
		
		System.out.print("재고량 수정: ");
		int newStock = sc.nextInt();
		sc.nextLine();
		
		System.out.print("판매진행여부 수정(Y/N): ");
		String newStatus = sc.nextLine().toUpperCase();
		
		shc.updateShoes(shoesCode, newName, newBrand, newPrice, newStock, newStatus);
		
	} // 수정 메소드 끝.
	
	public void selectByShoesName() {
		
		System.out.println("===신발 검색(품명)===\n");	
		System.out.print("품명 검색: ");
		String keyword = sc.nextLine();
		
		shc.selectByShoesName(keyword);		
	} // 품명 검색 메소드 끝
	
	public void selectByShoesCode() {
		
		System.out.println("===신발 검색(코드)===\n");	
		System.out.print("코드 검색: ");
		String shoesCode = sc.nextLine();
		
		shc.selectByShoesCode(shoesCode);		
	} // 가격검색 메소드 끝
	
	public void deleteShoes() {
		
		System.out.println("===신발 삭제===\n");	
		System.out.print("삭제할 신발(코드입력): ");
		String shoesCode = sc.nextLine();
		
		shc.deleteShoes(shoesCode);	
	} // 삭제 메소드 끝.
	
	
	// 화면 출력용 메소드 시작(성공/실패/list/1개만)
	public void displaySuccess(String message) {
		
		System.out.println(message);
	}
	
	public void displayFail(String message) {
		
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Shoes> list) {
		
		System.out.println("총 " + list.size() + "건 조회");
		
		for(Shoes s : list) {
			
			System.out.println(s);
		}

	}
	
	public void displayOne(Shoes s) {
		
		System.out.println("총 1건입니다.");
		System.out.println(s);
	}

}
