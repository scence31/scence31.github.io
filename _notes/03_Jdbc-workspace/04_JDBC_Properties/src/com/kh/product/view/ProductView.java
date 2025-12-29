package com.kh.product.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.product.controller.ProductController;
import com.kh.product.vo.Product;

public class ProductView {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	/**
	 * 메인메뉴 출력화면 
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println("----- 제품 관리 프로그램 ------");
			
			System.out.println("1. 제품 전체 조회");
			System.out.println("2. 제품 추가");
			System.out.println("3. 제품 수정");			
			System.out.println("4. 제품 삭제");
			System.out.println("5. 품명 검색");
			System.out.println("6. 브랜드 검색");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("--------------------------");
			
			System.out.print("메뉴 입력: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				selectAll();
				break;
			case 2:
				insertProduct();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				selectByName();
				break;
			case 6:
				selectByBrand();
				break;
			case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("다시 입력하세요.");	
			
			}
			
		}
		
	} // mainMenu 끝.
	
	public void selectAll() {
		
		System.out.println("\n----- 제품 전체 조회 -----");
		
		pc.selectAll();
	}
	
	public void insertProduct() {
		
		System.out.println("--- 제품 추가 ---\n");
		
		System.out.print("제품 코드: ");
		String prCode = sc.nextLine();
		
		System.out.print("제품 브랜드: ");
		String prBrand = sc.nextLine();
		
		System.out.print("제품 이름: ");
		String prName = sc.nextLine();
		
		System.out.print("제품 가격: ");
		int prPrice = sc.nextInt();
		sc.nextLine();
		
		System.out.print("재고량: ");
		int prStock = sc.nextInt();
		sc.nextLine();
		
		pc.insertProduct(prCode, prBrand, prName, prPrice, prStock);
	}
	
public void updateProduct() {
		
		System.out.println("---제품수정---\n");
		
		System.out.print("수정할 제품(코드입력): ");
		String prCode = sc.nextLine();
		
		System.out.print("품명 수정: ");
		String newName = sc.nextLine();
		
		System.out.print("브랜드 수정: ");
		String newBrand = sc.nextLine();
		
		System.out.print("가격 수정: ");
		int newPrice = sc.nextInt();
		
		System.out.print("재고 수정: ");
		int newStock = sc.nextInt();
		
		pc.updateProduct(prCode, newName, newBrand, newPrice, newStock);
		
	}

	
	
	/**
	 * 제품 삭제시 보일 화면 메소드. 코드로 입력해서 삭제
	 */
	public void deleteProduct() {
		
		System.out.println("---제품삭제---\n");
		
		System.out.print("삭제할 제품(코드입력): ");
		String prCode = sc.nextLine();
		
		pc.deleteProduct(prCode);
		
	} // deleteProduct 메소드 끝.
	
	/**
	 * 품명으로 검색, 제품 검색시 보일 화면 메소드. 키워드로 입력해서 한 글자만 같아도 검색됨
	 */
	public void selectByName() {
		
		System.out.println("---품명검색---\n");
		
		System.out.print("검색할 품명: ");
		String nameKeyword = sc.nextLine();
		
		pc.selectByName(nameKeyword);
		
	} // selectByName 메소드 끝.
	
	/**
	 * 브랜드명으로 검색, 제품 검색시 보일 화면 메소드. 키워드로 입력해서 한 글자만 같아도 검색됨
	 */
	public void selectByBrand() {
		
		System.out.println("---브랜드검색---\n");
		
		System.out.print("검색할 브랜드: ");
		String brandKeyword = sc.nextLine();
		
		pc.selectByBrand(brandKeyword);
		
	} // selectByBrand 메소드 끝.
	
	/**
	 * 요청 성공시 보일 화면 메소드.
	 * @param message => Controller에서 출력해줄 화면
	 */
	public void displaySuccess(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 요청 실패시 보일 화면 메소드.
	 * @param message => Controller에서 출력해줄 화면
	 */
	public void displayFail(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 조회된 결과가 없을 시 보일 화면 메소드
	 * @param message => Controller에서 출력해줄 화면
	 */
	public void displayNoData(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 조회된 결과가 있을 시 보일 화면 메소드
	 * @param list => 검색된 상품 리스트 담겸있음
	 */
	public void displayList(ArrayList<Product> list) {
		
		System.out.println("검색된 건은 총 " + list.size() + "건 입니다.");
	
		for(Product p : list) {
			
			System.out.println(p);
		}
	}
	

}
