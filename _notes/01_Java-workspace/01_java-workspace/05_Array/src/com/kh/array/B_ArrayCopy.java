package com.kh.array;

import java.util.Arrays;

// 배열복사(주소값, 참조자료형 관련)
public class B_ArrayCopy {
	
	/*
	 * * 배열복사
	 * - 배열을 복사하는 과정
	 * 
	 * [종류]
	 * 1. 얕은복사: 복사하는 척만 하는 것
	 * 2. 깊은복사: 제대로 복사하는 것
	 */
	
	public void method1() {
		
		// 원본배열
		int[] origin = {1, 2, 3, 4, 5}; // 초기화블록
		
		System.out.println("== 원본배열 출력 ==");
		
		for(int i = 0; i < origin.length; i++) {
			
			System.out.print(origin[i] + " ");
			
		}
		
		// 복사본 배열
		int[] copy = origin;
		
		System.out.println();
		System.out.println("== 복사본배열 출력 ==");
		
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}
		// > 콘솔창에 출력한 결과로는 복사가 잘 된 것 같음!
		
		// copy 배열 수정해보기
		copy[2] = 99;
		
		System.out.println();
		System.out.println("=== 복사본배열 수정 후 ===");
		
		System.out.println("== 원본배열 출력 ==");
		
		for(int i = 0; i < origin.length; i++) {
			
			System.out.print(origin[i] + " ");
			
		}		
		
		System.out.println();
		
		System.out.println("== 복사본배열 출력 ==");
		
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}		
		// 복사본배열만을 가지고 수정을 했는데
		// 원본배열도 같이 수정된 것 같다(즉, 원본 배열 데이터가 훼손되었다)
		// > 복사가 제대로 되지 않음
		
		/*
		 * * 얕은복사: 배열의 "주소값"이 복사되어
		 * 			 원본과 주소본의 "주소값"이 같은 상태(같은 배열을 가리킴)
		 * > 원본과 복사본의 "주소값"이 같은 상태를 얕은복사라고 함
		 * 
		 */
		
		// 주소값들 출력
		System.out.println();
		System.out.println("원본주소값: " + origin); // [I@2f2c9b19
		System.out.println("복사본주소값: " + copy); // [I@2f2c9b19
		
		// 위와 같이 주소값이 같음
		// 해시코드 출력
		System.out.println("원본해시코드: " + origin.hashCode()); // 791452441
		System.out.println("복사본해시코드: " + copy.hashCode()); // 791452441
		
		// 원본과 복사본 동등비교 결과
		System.out.println(origin == copy); // true
		
		/* > 배열복사가 제대로 되었는지 확인하고 싶다면
		 *   for문을 이용해서 단순히 출력만 하면 안되고,
		 *   배열의 내용을 수정해서 전후로 비교해봐도 되고,
		 *   단순히 원본과 복사본의 주소값을 동등비교해봐도 됨 (sysout origin == copy)
		 * 
		 */
		
	} // 메소드 1 영역 끝
	
	// 깊은복사 - 총 4가지 방법 있음
	public void method2() {
		
		// 방법 1. for문을 이용하는 방법
		// > 새로운 배열을 생성한 후 각 인덱스별 내부값을
		//   일일이 for문으로 대입한느 방법
		
		// 원본배열
		int[] origin = {1, 2, 3, 4, 5};
		
		// 복사본배열
		int[] copy = new int[origin.length];
		// new 구문 때문에 깊은복사된 것( Heap 영역 새로 생성)
		
		// copy[0] = origin[0];
		// copy[1] = origin[1];
		// ...
		
		for(int i = 0; i < copy.length; i++) {
			
			copy[i] = origin[i];
			
		}
		// 각각 출력
		System.out.println("==원본배열 출력==");
		
		for(int i = 0; i < origin.length; i++) {
			
			System.out.print(origin[i] + " ");
			
		}
		System.out.println();
		System.out.println("==복사본배열 출력==");
		
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}
		
		//copy배열 수정
		copy[2] = 99;
		
		System.out.println();
		System.out.println("===복사본배열 수정 후===");
		
		System.out.println("==원본배열 출력==");
		
		for(int i = 0; i < origin.length; i++) {
			
			System.out.print(origin[i] + " ");
			
		}
		System.out.println();
		System.out.println("==복사본배열 출력==");
		
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}
		// > 복사본배열의 내용을 수정하더라도
		//   원본배열의 내용은 여전히 보존되는 것을 볼 수 있다!!
		// > 복사가 제대로 됐다.
		
		/*
		 * * 깊은복사
		 * - 단순히 주소값만 복사하는 개념이 아닌
		 * 	 실제 heap 영역에 만들어지는 배열의 실물 자체를 복사하는 개념
		 * 
		 */
		
		
		System.out.println();
		// 주소값, 해시코드 출력
		System.out.println("원본 주소값: " + origin); // [I@2f2c9b19
		System.out.println("복사본 주소값: " + copy); // [I@1c20c684
		System.out.println("원본 해시코드: " + origin.hashCode()); // 79~
		System.out.println("복사본 해시코드: " + copy.hashCode()); // 47~
		
		// 동등비교
		System.out.println(origin == copy); // false
		
	} // 메소드 2 영역 끝
	
	public void method3() {
		
		// 깊은복사
		// 방법 2. 새로운 배열을 생성 후
		// 		  System 클래스에서 제공하는 arraycopy 메소드 이용하는 방법
		
		// 원본배열
		int[] origin = {1, 2, 3, 4, 5};
		
		// 복사본배열
		int[] copy = new int[10]; // [0] ~ [9] 방
		// > 일부러 원본배열 크기보다 넉넉하게 만들었음
		// 0이 10개 담겨있음(초기화를 하지 않아서)
		
		/*
		 *  * System 클래스의 arraycopy 메소드
		 *  - 배열의 깊은복사를 위해 정의된 메소드
		 *  
		 * [표현법]
		 * System.arraycopy(원본배열명, 원본배열의복사를시작할인덱스, 
		 *                  복사본배열명, 복사본배열의복사될시작인덱스, 복사할개수);
		 * 
		 * > 표현법이 복잡한 만큼 복사시 옵션을 디테일하게 설정 가능!
		 */
		
		// System.arraycopy(origin, 0, copy, 0, 5);
		// origin: 1 2 3 4 5
		// copy:   0 0 0 0 0 0 0 0 0 0 0
		// 복사 결과 1 2 3 4 5 0 0 0 0 0 0
		
		// System.arraycopy(origin, 0, copy, 2, 5);
		// origin: 1 2 3 4 5
		// copy:   0 0 0 0 0 0 0 0 0 0
		// 복사 결과 0 0 1 2 3 4 5 0 0 0
		
		// System.arraycopy(origin, 0, copy, 1, 3);
		// origin: 1 2 3 4 5
		// copy:   0 0 0 0 0 0 0 0 0 0 0
		// 복사 결과 0 1 2 3 0 0 0 0 0 0 0
		
		 System.arraycopy(origin, 2, copy, 1, 3);
		// origin: 1 2 3 4 5
		// copy:   0 0 0 0 0 0 0 0 0 0 0
		// 복사 결과 0 3 4 5 0 0 0 0 0 0 0
		
		// System.arraycopy(origin, 2, copy, 9, 2);
		// origin: 1 2 3 4 5
		// copy:   0 0 0 0 0 0 0 0 0 0 0		
		// 복사 결과 0 0 0 0 0 0 0 0 0 0 3 / 4 (인덱스 범위 벗어남)
		// ArrayIndexOutOfBoundsException 오류 발생
		// 붙여넣기 과정에서 인덱스 범위를 벗어나서 발생
		
		// 복사 후 남는 부분은 원래의 값으로 계속 유지된다.
		 
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}
		
		// 깊은복사가 잘 됐는지 확인
		System.out.println();
		System.out.println("원본주소값: " + origin);
		System.out.println("복사본주소값: " + copy);
		// 깊은복사가 잘 됐음
		
	} // method 3 영역 끝
	
	public void method4() {
		
		// 깊은복사
		// 방법 3. Arrays 클래스에서 제공하는 copyOf 메소드 이용
		
		// 원본배열
		int[] origin = {1, 2, 3, 4, 5};
		
		/*
		 * * Arrays 클래스의 copyOf 메소드
		 * - 깊은복사를 해주는 메소드
		 * 
		 * [표현법]
		 * 복사본배열명 = Arrays.copyOf(원본배열명, 복사할개수);
		 * 
		 * 복사할개수가 의미하는 것은 곧 복사본배열의 크기!!
		 * 초과된 부분은(아래 copyOf, 7 참고) 기본값(0)으로 채워진 후 복사본배열 만들어짐
		 * 
		 */
		
		// 복사본배열
		// int[] copy = Arrays.copyOf(origin, 5);
		// 1 2 3 4 5
		
		// int[] copy = Arrays.copyOf(origin, 2);
		// 1 2
		
		int[] copy = Arrays.copyOf(origin, 7);
		// 1 2 3 4 5 0 0
		
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}
		
		// 깊은복사가 잘 됐는지 확인
		System.out.println();
		System.out.println("원본해시코드: " + origin.hashCode());
		System.out.println("복사본주소값: " + copy.hashCode());
		
	} // 메소드 4 영역 끝
	
	public void method5() {
		
		// 깊은복사
		// 방법 4. clone이라는 메소드 이용
		
		/*
		 * * clone 메소드
		 * - 배열의 깊은복사를 해주는 메소드
		 * 
		 * [표현법]
		 * 복사본배열명 = 원본배열명.clone();
		 * 
		 */
		
		// 원본배열
		int[] origin = {1, 2, 3, 4, 5};
		
		// 복사본배열
		int[] copy = origin.clone();
		// 인덱스 지정X, 복사할개수 지정X
		// 즉 원본배월과 완전 똑같이 복제하겠다(클론)
		
		for(int i = 0; i < copy.length; i++) {
			
			System.out.print(copy[i] + " ");
		}
		
		// 복사가 잘 됐는지 확인
		System.out.println();
		System.out.println(origin == copy); // false (깊은복사 완료됨)
		
	}

}


















