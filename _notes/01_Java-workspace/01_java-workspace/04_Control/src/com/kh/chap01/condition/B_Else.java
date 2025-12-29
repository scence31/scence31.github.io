package com.kh.chap01.condition;

import java.util.Scanner;

// if-else문, if-else if문
public class B_Else {
	
	/*
	 * * if-else문
	 * - 조건식 하나로 한번에 두 개의 경우의 수를 나타낼 수 있는 선택문
	 * 
	 * 
	 */

	public void method1() {
		
		//사용자에게 정수값을 입력받은 후
		// 짝수인지 홀수인지 판별해서 출력
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		/*
		if(num % 2 == 0) { // 첫번째 경우(짝수)
			
			System.out.println("짝수입니다.");
			
		} else { // 두번째 경우(짝수가 아닐경우, 즉 홀수)
			
			System.out.println("홀수입니다.");
		}
		*/
		
		// 위의 코드를 보완해서
		// 양수이면서 짝수인지, 양수이면서 홀수인지 판별
		// > 중첩 if문 사용
		
		// 우선 양수인지를 먼저 판별
		if(num > 0) { // 양수일 경우
			
			// 양수일 경우에만 이 안에서 짝수 or 홀수를 판별
			if(num % 2 == 0) {
				
				System.out.println("양수이면서 짝수입니다.");
			} else { // 두번째 경우의수: 짝수가 아닐경우
				
				System.out.println("양수이면서 홀수입니다.");
			}
		} else { // 양수가 아닐경우(세번째), 즉 0이거나 음수를 뜻함
			
			System.out.println("양수가 아닙니다.");
		}
		
		// > if-else문 또한 중첩해서 사용할 수 있다.!
		
	} // 메소드1 영역 끝
	
	/*
	 * * if-else if문
	 * - 한 번에 3개 이상의 경우의수를 나타낼 때 사용
	 * - n개의 경우의수를 표현할 경우 n-1개의 조건식을 제시
	 * - 순서: if --> else if --> else
	 * 
	 * [표현법]
	 * if(조건식1) {
	 * 
	 * 		조건식1이 true일 경우 실행할 구문;
	 * 
	 * } else if(조건식2) {
	 * 
	 * 		조건식2가 true일 경우 실행할 구문;
	 * 
	 * } else if(조건식3) {
	 * 
	 * 		조건식3가 true일 경우 실행할 구문;
	 * 
	 * } else {
	 * 
	 * 		앞에 제시한 조건식 1~3 모두 결과가 false일 경우 실행할 구문;
	 * }
	 */
	
	public void method2() {
		
		// 사용자로부터 정수를 입력받아
		// 양수인지, 0인지, 음수인지 판별해서 출력하시요
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		//if-else if문 버전
		if(num > 0) {
			
			System.out.println("양수입니다.");
		} else if(num < 0) {
			
			System.out.println("음수입니다.");
			
		} else {
			
			System.out.println("0입니다.");
		}
		
		
	} // 메소드2 영역 끝
	
	public void method3() {
		
		// 사용자로부터 나이를 입력받고
		// 어린이, 청소년, 성인 판별해서 출력
		// 어린이 13세이하, 청소년 14~19세, 성인 20세이상
		// 단, 음수입력시 "잘못된 나이입니다."
		// if-else if문 사용
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("나이를 입력하세요: ");
		int age = sc.nextInt();
		sc.nextLine();
		
		
		//내가 한거
		/* if(age >= 0 && age <= 13) {
			
			System.out.println("어린이");
		} else if(age > 13 && age <= 19) {
			
			System.out.println("청소년");
			
		} else if(age > 19) {
			
			System.out.println("성인");
		} else {
			
			System.out.println("잘못된 나이입니다.");
		}
		*/
		
		//선생님이 한거
		if(age < 0) { //나이가 음수인 경우
			
			System.out.print("잘못 입력했습니다.");
			
		} else { //나이가 음수가 아닌 경우(제대로 된 나이)
			
			if(age <= 13) {
				
				System.out.println("어린이");
				
			} else if(age > 13 && age <= 19) {
				// (age > 13) && 구문을 지우더라도 제대로 작동함
				// 어차피 결과를 만족하고 진행하기 때문
				// > 하한가 적지 않음으로써 불필요한 구문 생략가능
				
				System.out.println("청소년");
				
			} else {
				
				System.out.println("성인");
			}
		}
		
		
	} //메소드3 영역 끝
	
	public void method4() {
		
		// 사용자에게 점수를 입력받고
		// 90점 이상인 경우 "A등급"
		// 80점 이상인 경우 "B등급"
		// 70점 이상인 경우 "C등급"
		// 70점 미만인 경우 "D등급"
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("점수를 입력하세요: ");
		int score = sc.nextInt();
		sc.nextLine();
		
		// 점수는 0 ~ 100점
		// 제대로 된 점수인지 검사
		// > 등급 판별 전 판별된 등급 값을 담아둘 변수를 하나 세팅
		char grade = ' ';
		// > char 타입의 경우 일반적으로 ' '(공백)으로 초기화해서 쓴다!!
		// > String 타입의 경우 일반적으로 ""(빈문자열)로 초기화해서 쓴다!
		// > 메소드 안쪽에서 선언한 변수: 지역변수(local variable)
		//   주로 지역변수는 초기화 후 사용하는 것을 권장
		// > 또한 이 grade라는 지역변수는 이 if문 블럭 {} 안쪽에서만 쓸 수 있음
		//   (지역변수 개념)
		
		if(score >= 0 && score <= 100) {
			
			if(score >= 90) {
				
				grade = 'A';
				
			} else if(score >= 80) {
				
				grade = 'B';
				
			} else if(score >= 70) {
				
				grade = 'C';
				
			} else {
				
				grade = 'D';
			}
			
			// 모드 비슷한 문구로 출력할 경우
			System.out.println("당신의 점수는" + score + "점이고, 등급은 " + grade + "등급입니다.");
			
		} else {
			
			
			System.out.println("잘못 입력했습니다.");
		}
		
		
		
	} //메소드4 영역 끝
	
	public void method5() {
		
		// 변수 실습문제 2번
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫 번째 정수를 입력하세요: ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("두 번째 정수를 ㄹ입력하세요: ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		int plus = num1 + num2;
		int minus = num1 - num2;
		int multi = num1 * num2;
		int division = num1 / num2;
		
		System.out.println("더하기 결과: " + plus);
		System.out.println("빼기 결과: " + minus);
		System.out.println("곱하기 결과: " + multi);
		System.out.println("나누기 결과: " + division);
		
				
		
		
	} // method 5 end
	
	public void method6() {
		
		//변수 실습문제 3
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("가로: ");
		double width = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("세로: ");
		double height = sc.nextDouble();
		sc.nextLine();
		
		double area = width * height;
		double measurement = (width + height) * 2;
		
		System.out.println("면적: " + area);
		System.out.println("둘레: " + measurement);
		
	} // method 6 end
	
	public void method7() {
		
		// 변수 실습문제 4, 형변환 4
		Scanner sc = new Scanner(System.in);
		
		System.out.print("반지름: ");
		int radius = sc.nextInt();
		sc.nextLine();
		
		double area = ((int)radius * radius * 3.14);
		double measurement = ((int)radius * 3.14 * 2);
		
		
		
		System.out.println();
		
		System.out.println("면적: " + area);
		System.out.printf("둘레: %.1f", measurement);
		
		
		
	} // method 7 end
	
	public void method8() {
		
		//형변환 실습문제 1
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자: ");
		char word = sc.nextLine().charAt(0);
		
		int num = word;
		
		System.out.println("A Unicode: " + num);
		
		
	} //method 8 end
	
	public void method9() {
		
		//실습문제 2 연산자
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if(num > 0) {
			
			System.out.println("양수다");
			
		} else if(num == 0) {
			
			System.out.println("0이다");
			
		} else {
			
			System.out.println("음수다");
		}
		
	} // method 9 end
	
	public void method10() {
		
		//실습문제3 연산자
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수: ");
		
		int num = sc.nextInt();
		sc.nextLine();
		
		if(num % 2 == 0) {
			
			System.out.println("짝수다");
		} else {
			
			System.out.println("홀수다");
		}
		
		
	} // 메소드 10 끝
	
	public void method11() {
		
		//실습문제4 연산자
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("인원수: ");
		int peopleNum = sc.nextInt();
		sc.nextLine();
		
		System.out.print("사탕 개수: ");
		int candyNum = sc.nextInt();
		sc.nextLine();
		
		int candyForOne = candyNum / peopleNum;
		int candyRest = candyNum % peopleNum;
		
		System.out.println("1인당 사탕 개수: " + candyForOne);
		System.out.println("남늠 사탕 개수: " + candyRest);
		
		
	} // method 11 end
	
	public void method12() {
		
		// 실습문제8 연산자
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("주민번호를 입력하세요(-포함): ");
		
		String personNum = sc.nextLine();
		
		char word = personNum.charAt(7);
		
		if(word == '3' || word == '1') {
			
			System.out.println("남자");
		} else if(word == '4' || word == '2') {
			
			System.out.println("여자");
		} else {
			
			System.out.println("잘못입력");
		}
		
		
		
	} // method 12 end
	
	public void method13() {
		
		//실습문제 9 연산자
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수1: ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("정수2: ");
		int num2 = sc.nextInt();
		sc.nextLine();		
		
		System.out.print("입력: ");
		int input = sc.nextInt();
		sc.nextLine();		
		
		if(input <= num1 || input > num2) {
			
			System.out.println("true");
		} else {
			
			System.out.println("false");
		}
		
	}
	
	
	
}
