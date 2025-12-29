package com.kh.chap01_oneVSmany.run;

import java.util.Scanner;

import com.kh.chap01_oneVSmany.model.vo.Book;

// 객체배열 사용 후
public class ObjectArrayRun {

	public static void main(String[] args) {
		
		// 3권의 책 정보를 관리(객체 배열을 이용해서)
		
		// 3개의 Book 객체를 담아 보관할 배열 생성
		// 배열선언: 자료형[] 배열명;
		// 배열할당(생성): 배열명 = new 자료형[배열크기];
		// 배열 선언 동시에 할당: 자료형[] 배열명 = new 자료형[배열크기];

		Book[] arr = new Book[3];
		// > arr[0], arr[1], arr[2]
		//   null    null    null
		// > 각 인덱스 자리에는 Book 타입의 주소값만 들어갈 수 있다!!
		
		// 내 메모, 배열 선언 할당 순서
		// 1. Stack에 arr 상자 1개 생기고
		// 2. Heap에 대충 0123 주소값, [0] [1] [2] 방번호 붙은 상자 3칸 생성
		//    > new니까
		// 3. Heap에 각 방번호 상자에 null 들어감
		// 4. 0123 주소값이 Stack arr 상자에 들어가면서 Heap 상자에 매칭됨
		
		Scanner sc = new Scanner(System.in);
		
		// 3권의 전체 도서의 정보를 입력받은 후 각 인덱스에 Book 객체 생성 후 담아주기
		for(int i = 0; i < arr.length; i++) {
			
			System.out.print("제목: ");
			String title = sc.nextLine();
			
			System.out.print("저자명: ");
			String author = sc.nextLine();
			
			System.out.print("가격: ");
			int price = sc.nextInt();
			sc.nextLine();
			
			System.out.print("출판사명: ");
			String publisher = sc.nextLine();
			
			arr[i] = new Book(title, author, price, publisher);
			// 5. new니까 또 상자(title, author ~) 생김 4개
			// 6. arr[i]랑 또 생긴 상자랑 매칭됨 0번째, 1번째, 3번째 반복.. 또 생긴 상자..
			// 생긴 상자는 주소지는 다 다름 arr상자, Book상자 다
			// 주소값 두 번 타고 들어감
			
		}
		
		// > 이 시점 기준으로 arr 배열의 각 인덱스에는 Book 객체가 차곡차곡 담겨있을 것
		
		// 전체 도서 정보들 출력하기(조회)
		for(int i = 0; i < arr.length; i++) {
			
			// System.out.println(arr[i]); // 주소값
			System.out.println(arr[i].information());
			// > arr[i]: arr 배열의 주소값을 타고 들어가서, i 번째 방에 "직접접근" 하겠다.
			//   .information(): i 번째 방의 주소값을 또 타고 들어가서
			//					 information 메소드를 "직접접근" 하겠다.
			// > 최종적으로 주소값을 2번 타고 들어가겠다!!
			
			
		}
		
		// 사용자에게 검색할 도서 제목을 입력받아
		// 각 전체 도서들의 제목과 일일이 비교하여 일치하는 도서의 가격을 알려주기(검색)
		
		System.out.print("검색할 책 제목: ");
		String searchTitle = sc.nextLine();
		
		/*
		for(int i = 0; i < arr.length; i++) {
			
			if(searchTitle.equals(arr[i].getTitle())) {
				
				System.out.println(arr[i].getPrice());
				
			}
		}
		*/
		
		// + 기능 보안
		// 만약 일치하는 도서가 없다면 "검색된 도서가 없습니다." 출력
		
		// 보조해주는 변수 활용
		int count = 0;
		// > 일치하는 도서를 찾을 때마다 1씩 증가시킬 변수
		//   즉, 일치하는 도서의 개수를 셀 변수
		
		for(int i = 0; i < arr.length; i++) {
			
			if(searchTitle.equals(arr[i].getTitle())) {
				
				System.out.println(arr[i].getPrice());
				count++;
				
				// "책의 제목들이 모두 다 중복되지 않는다는 가정 하에"
				// 첫 번째 검색 결과부터 일치한다면? 그 이후의 검사는 안해도 됨
				// >이 검사해주는 for문 자체를 빠져나가게끔 구문 추가
				break;
			}
		}
		
		// > 이 시점 기준으로 count에는
		//   일치하는 도서가 단 한 권도 없을 경우에는 count == 0
		//   일치하는 도서가 n권이면 count == n
		if(count == 0) {
			
			System.out.println("검색된 도서가 없습니다.");
		}
		
		
	}

}













