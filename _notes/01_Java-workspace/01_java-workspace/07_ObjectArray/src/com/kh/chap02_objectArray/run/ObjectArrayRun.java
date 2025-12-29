package com.kh.chap02_objectArray.run;

import com.kh.chap02_objectArray.model.vo.Phone;

public class ObjectArrayRun {

	public static void main(String[] args) {
		
		// 휴대폰 3대를 관리하는 프로그램
		// 방법1)
		/*
		Phone p1 = null;
		Phone p2 = null;
		Phone p3 = null;
		*/
		
		// 방법2)
		// 자료형[] 배열명 = new 자료형[크기];
		Phone[] arr = new Phone[3];
		
		// 현재 이 arr 배열에 담긴 휴대폰의 개수를 나타내는 변수
		int count = 0;
		// > 휴대폰이 한 대 생성될 때마다 1씩 증가시킬 것!
		
		// System.out.println(arr); // 배열의 주소값
		// System.out.println(arr.length); // 3, 배열의 크기
		// System.out.println(arr[0]); // null (참조자료형 기본값 JVM이 ~)
		
		for(int i =0; i < arr.length; i++) {
			
			System.out.println(arr[i]);
			// arr 배열의 인덱스 null null null
			// > new로 생성 안해서 아직
		}
		// 1. 기본생성자로 객체 생성 후 setter 메소드로 값 세팅
		arr[0] = new Phone();
		arr[0].setName("아이폰");
		arr[0].setSeries("11pro");
		arr[0].setBrand("애플");
		arr[0].setPrice(1000000);
		// 방금 한 대 생성됐으니깐
		count++;
		// > 1대 / 3칸 (확보)
		
		
		// 2. 매개변수생성자로 객체 생성
		arr[1] = new Phone("플립폰", "1", "삼성", 1200000);
		// 또 한대 생성됐으니깐
		count++;
		// > 2대 / 3칸 (확보)
		
		System.out.println("------------------------------");
		
		// 휴대폰 정보를 모두 출력
		
		/*
		for(int i = 0; i < arr.length; i++) {
			
			System.out.println(arr[i].information());
			
		}
		*/
		
		// > 반복 3회차에 NullPointException 오류 발생
		//   배열의 크기는 총 3칸, 그 중 0, 1번 인덱스에만 제대로된 주소값이 담겨있음
		//   2번 인덱스에는 null이 담겨있기 때문에 찾아갈 공간이 없어서 오류 발생
		
		// 무조건 배열의 크기만큼 모두 반복을 돌리면 오히려 위의 오류가 발생하니까
		// 정확히 실제 갖고 있는 휴대폰 객체의 개수만큼만 반복을 돌려야 함!
		
		// 휴대폰의 총 가격을 담을 수 있는 변수
		int sum = 0;
		
		for(int i =0; i < count; i++) {
			
			System.out.println(arr[i].information());
			
			sum += arr[i].getPrice();
		}
		
		System.out.println("---------------");
		
		//이왕이면 총 가격과 평균가도 아래에 같이 출력하고 싶음!
		System.out.println("총가격: " + sum + "원");
		System.out.println("평균가: " + (sum / count) + "원");
		
		
	}

}


















