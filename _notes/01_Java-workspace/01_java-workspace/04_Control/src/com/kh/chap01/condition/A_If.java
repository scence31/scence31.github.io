package com.kh.chap01.condition;

import java.util.Scanner;

// 단독 if문
public class A_If {
	
	/*
	 * * 단독 if문
	 * - 단 하나의 선택적인 경우를 나타낼 때 사용하는 구문
	 * 
	 * [표현법]
	 * if(조건식) {
	 * 
	 * 		조건식의 결과가 true일 경우 실행할 코드;
	 * }
	 * > 조건식의 결과가 참(true)이라면 중괄호 블록(영역) 안의 코드가 실행됨
	 * > 조건식의 결과가 거짓(false)이라면 중괄호 코드 무시하고 다음 코드로 넘어감
	 * 
	 */
	
	// 기존 삼항연산자 문 --> 단독 if문 버전으로
	
	public void method1() {
		
		// 사용자에게 정수값을 입력받은 후
		// 양수인지, 0인지, 음수인지 판별 후 결과 출력
		
		Scanner sc = new Scanner(System.in);
		// 생성 단축기: ctrl+shift+o
		
		System.out.print("정수를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// 삼항연산자를 이용한 경우
		// String result = (num > 0) ? "양수입니다." : ((num = 0) ? : "0입니다." : "음수입니다.");
		
		// 단독 if문을 이용한 경우
		// > 표현해야 하는 경우의 수가 3개이므로 단독 if문 또한 3개를 적는다.
		
		// 첫번째 경우의 수: 양수일 경우(num > 0)
		if(num > 0) {
			
			System.out.println("양수입니다.");
		}
		
		// 두번째 경우의 수: 0일 경우(num == 0)
		if(num == 0) {
			
			System.out.println("0입니다.");
		}
		
		// 세번째 경우의 수: 음수일 경우(num < 0)
		if(num < 0) {
			
			System.out.println("음수입니다.");
		}
		
		
		
		
		
	} // 메소드1 영역 끝
	
	public void method2() {
		
		//사용자가 입력한 정수값이 짝수인지 홀수인지 판별 후 출력
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값을 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		//삼항연산자 버전
		//String result = (num %2 == 0) ? "짝수입니다." : "홀수입니다.";
		
		// 단독 if문 버전
		// > 표현할 경우의 수가 2개이므로 단독 if문 또한 2번 쓴다.
		
		// 첫번째 경우의 수: 짝수일 경우
		if(num % 2 == 0) {
			
			System.out.println("짝수입니다.");
		}
		
		// 두번째 경우의 수: 홀수일 경우
		if(num % 2 != 0) {
					
			System.out.println("홀수입니다.");
		}
		
		
	} // 메소드2 영역 끝

	public void method3() {
		
		// 사용자가 입력한 나이값을 가지고
		// 어린이? 청소년? 성인 판별 후 출력
		// 어린이: 13세 이하
		// 청소년: 13세 초과, 19세 이하
		// 성인: 19세 초과
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("나이를 입력하세요: ");
		int age = sc.nextInt();
		sc.nextLine();
		
		// 삼항연산자 버전
		//String result = (age <= 13) ?  "어린이" : ((age > 13 && age <= 19) ? "청소년" : "성인");
		
		// 단독 if문 버전
		// > 경우의 수가 3개이므로 단독 if문 또한 3번 사용
		
		// 첫번째 경우의 수: 어린이
		if(age <= 13) {
			
			System.out.println("어린이");
		}
		
		// 두번째 경우의 수: 청소년 
		if(age > 13 && age <= 19) {
			
			System.out.println("청소년");
		}
				
		// 세번째 경우의 수: 성인
		if(age > 19) {
			
			System.out.println("성인");
		}
				
		
	} // 메소드3 영역 끝
	
	public void method4() {
		
		//사용자가 입력한 주민번호를 가지고
		//남자인지 여자인지 구분하여 출력하세요
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("주민번호를 입력하세요(-포함): ");
		String personId = sc.nextLine();
		
		//입력받은 주민번호로부터 성별에 해당하는 문자 추출해서 변수에 담기
		char gender = personId.charAt(7);
		// [주의!]
		// gender에는 '1' 또는 '2' ... 등 문자로서의 값이 담겨있음!!
		
		if(gender == '1' || gender == '3') {
			
			System.out.println("남자");
		}
		
		if(gender == '2' || gender == '4') {
			
			System.out.println("여자");
		}	
		
		if(gender != '1' && gender != '2' && gender != '3' && gender != '4') {
			
			System.out.println("잘못입력");
		}
		
		
	} // 메소드4 영역 끝
	
	//method2 보완
	public void method5() {
		
		// 사용자 입력한 정수값이
		// 양수이면서 짝수인지, 양수이면서 홀수인지 판별
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		//단독 if문 버전
		
		/*
		//첫번째 경우의수: 양수이면서 짝수인 경우
		if((num > 0) && (num % 2 == 0)) {
			
			System.out.println("양수이면서 짝수입니다.");
		}
		
		if((num > 0) && (num % 2 != 0)) {
			
			System.out.println("양수이면서 홀수입니다.");
		}
		*/
		
		// > 첫번째, 두번째 경우의 수 모두 짝수인지 홀수인지 검사하기 전에
		//   우선적으로 양수인지 먼저 체크하고 있음
		// > if문 안에 if문을 "중첩"하여 사용 가능(중첩 if문)
		
		// 우선 양수인지만 먼저 체크하는 단독 if문 제시
		if(num > 0) {
			
			// num이 양수인지만 판별 --> 흐름 통과되거나 걸러짐
			
			// 이 안에서만 짝수/홀수 판별하면 됨
			if(num % 2 == 0) {
				
				System.out.println("양수이면서 짝수입니다.");
			}
			if(num % 2 != 0) {
				
				System.out.println("양수이면서 홀수입니다.");
			}
		}
		
		if(num <= 0) {
			
			System.out.println("양수가 아닙니다.");
		}
		
		// > 삼항연산자처럼 if문 안에 if문을 중첩하여 작성할 수 있다.
		
	} // 메소드5 영역 끝
	
} // A_If 클래스 영역 끝
