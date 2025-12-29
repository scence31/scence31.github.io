package com.kh.chap01_oneVSmany.run;

import java.util.Scanner;

import com.kh.chap01_oneVSmany.model.vo.Book;

public class ObjectRun {

	public static void main(String[] args) {
		
		/*
		// 도서관리 프로그램 구현중..
		// 각각의 도서 정보를 나타내는 Book 타입의 객체 생성
		// 1. 기본생성자로 객체 생성 후 setter 메소드로 각 필드에 대입
		Book bk1 = new Book();
		// > null, null, 0, null 각 타입에 기본값으로 필드 초기화
		
		bk1.setTitle("두잇 자바 프로그래밍");
		bk1.setAuthor("박은종");
		bk1.setPrice(25000);
		bk1.setPublisher("이지스 퍼블리싱");
		// > "두잇 자바 프로그래밍", "박은종", 25000, "이지스 퍼블리싱" 원하는 값으로 필드 초기화
		
		// 2. 매개변수생성자로 객체 생성과 동시에 각 필드에 값을 대입
		Book bk2 = new Book("처음만난 리액트", "이인제", 33000, "한빛미디어");
		// > "처음만난리액트", "이인제", 33000, "한빛미디어"
		
		System.out.println(bk1.information());
		System.out.println(bk2.information());
		
		// 3. 사용자가 직접 입력한 값들로 객체 생성
		// Book bk3 = new Book(사용자가입력한제목, ~저자명, ~가격, ~출판사명);
		
		Scanner sc = new Scanner(System.in);
		// > Scanner 클래스를 이용해서 객체를 만든 것!
		//   (Scanner 클래스의 유용한 메소드들을 heap 영역에 올리고 호출해서 쓰려고)
		//   그동안 매개변수 생성자를 이용했던 것!
		
		System.out.print("제목: ");
		String title = sc.nextLine();
		
		System.out.print("저자명: ");
		String author = sc.nextLine();
		
		System.out.print("가격: ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("출판사: ");
		String publisher = sc.nextLine();
		
		Book bk3 = new Book(title, author, price, publisher);
		
		System.out.println(bk3.information());
		*/
		
		// 세 권의 Book 객체가 필요하다는 가정 하에
		// 각각의 Book 객체를 따로따로 관리하는 프로그램(각각의 변수로 별도로 관리하겠다)
		// 단, 사용자로부터 입력받은 책 정보로 채워넣기
		Book bk1 = null;
		Book bk2 = null;
		Book bk3 = null;
		// > 지역변수는 항상 일단 초기화하는 것이 좋음!!(딱히 넣을 값 없다면 타입 별 기본값)
		
		Scanner sc = new Scanner(System.in);
		
		// 3권의 도서정보를 입력받은 후 각 객체에 초기화
		
		for(int i = 0; i < 3; i++) {
		
			System.out.print("제목: ");
			String title = sc.nextLine();
			
			System.out.print("저자명: ");
			String author = sc.nextLine();
			
			System.out.print("가격: ");
			int price = sc.nextInt();
			sc.nextLine();
			
			System.out.print("출판사명: ");
			String publisher = sc.nextLine();
			
			// Book 객체 생성하기
			// 반복 1회차에 입력받은 값(i == 0): bk1
			// 반복 2회차에 입력받은 값(i == 1): bk2
			// 반복 3회차에 입력받은 값(i == 2): bk3
			if(i == 0) {
				
				bk1 = new Book(title, author, price, publisher);
				
			} else if(i == 1) {
				
				bk2 = new Book(title, author, price, publisher);
				
			} else {
				
				bk3 = new Book(title, author, price, publisher);
			}
		
		}
		
		// 전체 도서정보 출력(조회 기능)
	 	System.out.println(bk1.information());
		System.out.println(bk2.information());
		System.out.println(bk3.information());
		
		// 사용자로부터 검색할 도서 제목을 입력받아
		// 각 전체 도서의 제목들과 일치하는지를 비교 후
		// 일치하는 도서가 있다면 그 도서의 가격을 알려주는 기능(검색기능, Read)
		
		System.out.print("검색할 도서 제목: ");
		String searchTitle = sc.nextLine();
		
		if(searchTitle.equals(bk1.getTitle())) {
			
			System.out.println(bk1.getPrice());
		}
		
		if(searchTitle.equals(bk2.getTitle())) {
			
			System.out.println(bk2.getPrice());
		}
		
		if(searchTitle.equals(bk3.getTitle())) {
			
			System.out.println(bk3.getPrice());
		}
	}

}














