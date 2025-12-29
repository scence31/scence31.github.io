package com.kh.chap03.branch;

// continue문
public class B_Continue {
	
	/*
	 * * continue문
	 * - 반복문 안에서 사용하는 보조문
	 * - continue;를 만나게 되면 그 뒤에 어떤 구문이 있든 간에
	 *   마저 실행하지 않고, 해당 반복문의 증감식 또는 조건식으로 바로 올라가주는 역할
	 * 
	 * [표현법]
	 * for(초기식; 조건식; 증감식) {
	 * 
	 * 		반복적으로 실행할 구문 1;
	 * 		continue; // 밑의 코드들은 무시하고 다음 증감식으로 넘어감
	 * 		반복적으로 실행할 구문 2;
	 * 		...
	 * }
	 * 
	 * while(조건식) {
	 * 
	 * 		반복적으로 실행할 구문1;
	 * 		continue; // 밑의 코드들은 무시하고 다음 조건식으로 넘어감
	 * 		...
	 * }
	 * 
	 * > for문의 경우 증감식, while문의 경우 조건식으로 올라가서
	 *   다음 반복회차가 진행될 수 있게끔 유도한다!!
	 */
	
	public void method1() {
		
		// 1 ~ 10까지의 정수 중 홀수만을 출력
		
		// 방법3) 반복을 1 ~ 9까지 매번 돌리면서
		//		 조건검사로 홀수인 경우에만 출력문을 실행하는 방법
		/*
		for(int i = 1; i <= 9; i++) {
			
			if(i % 2 != 0) {
				
				System.out.print(i + " ");
			}
			
		}
		*/
		
		// 방법4) continue문 이용하는 방법
		// > 이 방법 또한 반복을 매번 다 진행해야함!
		for(int i = 1; i <= 9; i++) {
			
			if(i % 2 == 0) {
				
				continue; // 아래 코드 무시하고 바로 증감식으로 올라가겠다
			}
			
			System.out.print(i + " ");
			
		}
		
	} // 메소드 1 영역 끝
	
	public void method2() {
		
		// 1 ~ 100까지의 정수 총 합을 구하되.
		// 단 6의 배수값은 제외하고 더해보자
		int sum = 0;
		
		// 6의 배수가 아닌 경우에만 덧셈을 하겠다
		// 방법1) continue;문 사용 x
		/*
		for(int i = 1; i <= 100; i++) {
			
			// 덧셈을 하기 전에, i가 6의 배수가 아닌지를 먼저 판별
			if(i % 6 != 0) { // i가 6의 배수가 아닌 경우
				
				sum += i;
			}
		}
		System.out.println("결과: " + sum);
		*/
		
		// 방법2) continue;문을 사용하는 방법
		// > 6의 배수일 경우에만 덧셈을 하지 않겠다.
		for(int i = 1; i <= 100; i++) {
			
			// 6의 배수인 경우
			if(i % 6 == 0) {
				
				continue; // 아래 코드는 무시하고 다음 증감식으로 넘어감
			}
			sum += i;
		}
		
		System.out.println("결과: " + sum);
		
	} // 메소드 2 영역 끝
	
	public void method3() {
		
		// 2단 ~ 9단 출력하되
		// 4의 배수단은 제외하고 출력하세요
		
		// 1. continue문 없이 풀기 (4의 배수단 아닐 경우에만 출력하겠다)
		
		/*
		for(int i = 2; i <= 9; i++) {
			
			if~ {
			
			}
			
			System.out.printf("---- &d단 ---- \n", i);
			
			
			for(int j = 1; j <= 9; j++) {
				
				System.out.println(i + "X" + j + "=" + (i*j));
				
			}
			
		}
		*/
		// 2. continue문 써서 풀기
		
		
		for(int i = 2; i <= 9; i++) {
		
			if(i % 4 == 0) {
			
				continue;
			}
			
			System.out.printf("--- %d단 --- \n", i);
			
			for(int j = 1; j <= 9; j++) {
				int prod = 1;
				prod *= i*j;
				System.out.printf("%d X %d = %d \n", i, j, (prod));
				
			}
		}
		
		
	} // 메소드 3 영역 종료
	

}
