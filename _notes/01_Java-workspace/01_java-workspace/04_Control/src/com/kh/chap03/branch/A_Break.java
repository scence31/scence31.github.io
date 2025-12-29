package com.kh.chap03.branch;

import java.util.Scanner;

// break문
public class A_Break {
	
	/*
	 * * break문
	 * - break;를 만나는 순간 "가장 가까운 반복문"을 단 한 겹만 빠져나가는 역할
	 * - 즉, 해당 break문이 속해있는 반복문 한 겹만 멈추고 빠져나가겠다.
	 * 	 (반복을 멈춰주는 역할)
	 * 
	 * [표현법]
	 * for() / while() {
	 * 
	 * 		반복적으로 실행할 구문 ~~;
	 * 		break; // 해당 반복문을 바로 빠져나감
	 * 			   // 반복횟수가 몇 번이 남았든 간에!!
	 * 
	 * }
	 * 
	 * * 주의할 점
	 * - switch문의 break;와는 다른 개념임!
	 * - 위의 break;는 해당 switch문을 빠져나가는 역할이고
	 * - 분기문의 break;는 해당 반복문을 빠져나가는 역할이기 때문
	 * - 구문 자체는 똑같이 생겼지만, 결국 근본적인 역할 자체가 다르기 때문에
	 *   애초에 개념 자체가 다른 구문이다!!
	 * 
	 */
	
	public void method1() {
		
		// 매번 반복적으로 새로이 발생되는 랜덤값(1 ~ 100) 출력
		// 단, 그 발생된 랜덤값이 "홀수일 경우" 반복문을 멈춰라
		
		// > "매번 반복적": 계속 진행(무한반복)하겠다
		while(true) {
			
			// 1 ~ 100 사이의 랜덤값을 발생시킨 후 변수에 담기
			int random = (int)(Math.random() * 100 + 1);
			
			// 발생된 random값이 홀수일 경우
			// 해당 random값을 출력하고 "프로그램을 종료합니다" 출력 후 멈추기
					
					System.out.println("random: " + random);
					// random값 출력하기
					
					if(random % 2 != 0) {
						// 홀수인지 판별
						System.out.println("프로그램을 종료합니다.");
						break;
					}
				
		}
		
	} // 메소드1 영역 끝
	
	public void method2() {
		
		// 매번 사용자에게 문자열을 입력받은 후
		// 해당 문자열의 길이를 출력
		// 단, 사용자가 입력한 문자열이 "exit"와 일치할 경우
		// 반복을 종료
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("문자열 입력: ");
			String str = sc.nextLine();
			
			// 출력하기 전 입력받은 문자열이 "exit"인지를 검사하기
			if(str.equals("exit")) {
				//문자열 내용물 동등비교: 변수값.equals("동등비교대상")
				
				System.out.println("프로그램을 종료합니다");
				break; // 가까운 while(true)(반복문) 빠져나감(중괄호 확인, while 안쪽 if?)
			}
			
			System.out.println("입력하신 문자열의 길이: " + str.length());
			
			
		} // break; --> 무한반복 멈추고 이거 빠져나감
		
		
		
	} // 메소드 2 영역 끝
	
	public void method3() {
		
		// 사용자에게 양수를 입력받아 1 ~ 사용자가 입력한 수까지 출력
		// 단, 제대로된 양수를 입력하지 않은 경우에는
		// "양수가 아닙니다. 다시 입력해 주세요" 출력하고 다시 입력받기
		// 제대로된 양수를 입력한 경우에는 출력 후 프로그램 종료
		
		// > 사용자가 언제 양수 입력할지, 양수가 아닌 수를 입력할지 모르기 때문에
		// > while(true) 안쪽에서 입력받자
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("양수를 입력하세요: ");
			int num = sc.nextInt();
			sc.nextLine();
			
			// 입력하세요 수가 양수인지 먼저 검사
			if(num > 0) {
				
				// 양수일 경우: 1부터 그 수(num)까지 출력
				for(int i = 1; i <= num; i++) {
					
					System.out.print(i + " ");
				}
				
				break;
			} else { // 양수가 아닐 경우
				
				System.out.println("잘못입력했습니다. 다시입력하세요");
			}
				
		}
		// break;문은 주로 무한반복문 안에서
		// "특정 조건"과 결합해서 해당 무한반복문의 종료시점을 나타낼 때 주로 쓰인다
		
	} // 메소드 3영역 끝
	
	// break;문 사용시 주의사항
	public void method4() {
		
		int num = 1;
		
		while(true) {
			
			System.out.println("안녕하세요?");
			
			if(num == 1) {
				
				break;
				// break; // while(true) 한 겹만 빠져나감
				//return; // method4 자체를 빠져나감(태초마을로)
			}
			
		}
		
		//System.out.println("프로그램을 종료합니다.");
		// break;를 while 내에서 생략하는 순간
		// 이 출력문까지 흐름이 절대 도달할 수 없다
		// 오류: Unreachable code
		// 무한반복코드를 작성할 때 break;문이 없을 경우
		// 해당 무한반복문 아래의 코드에 프로그램의 흐름이 도달할 수 없음!!
		//
		// 또한 무한반복문 안에서 return; 구문을 사용하면 마찬가지로 반복 종료됨
		// (흐름이 메소드 자체를 종료시키기 때문)
		// 마찬가지로 메소드를 통으로 빠져나가기 때문에
		// 이 출력문이 또한 Unreachable code 오류가 발생함
	}
	

}
