package com.kh.chapt02.loop;

import java.util.Scanner;

// for문
public class A_For {
	
	/*
	 * * for문
	 * - "반복횟수가 정해져 있을 때" 사용하는 반복문
	 * - 문법 상으로 반복의 횟수를 지정하는 부분이 있다.
	 * - 초기식, 조건식, 증감식 세 개의 구문을 가지고
	 *   반복의 횟수를 직접 지정해야함(각각 세미콜론으로 구분됨)
	 *   
	 * [표현법]
	 * for(초기식; 조건식; 증감식) {
	 * 
	 *		반복적으로 실행할 구문; 
	 * }
	 * 
	 * > 초기식: 반복문이 시작될 때 "맨 처음에 단 한 번만 실행되는 구문"
	 *          반복문에서 두고두고 사용할 변수를 선언 및 초기화할 때 주로 사용
	 *          ex) int i = 0;
	 *          
	 * > 조건식: "반복문이 수행될 조건을 작성하는 부분"
	 * 			조건식 결과가 true일 경우 반복을 더 실행
	 * 			조건식 결과가 false일 경우 반복을 멈춤
	 *  		(반복을 멈춤 == for문 중괄호 영역을 빠져나가겠다)
	 *  		주로 초기식에서 제시된 변수를 가지고 조건식을 지정함!!
	 *  		ex) i < 10;
	 *  
	 * > 증감식: 반복문을 제어하는 변수값을 "증감시키는 구문"
	 * 			보통 초기식에서 제시된 변수를 가지고 증감식을 지정함!!
	 * 			ex) i++
	 * 
	 * * for문의 수행흐름
	 *  for(초기식; 조건식; 증감식) {
	 * 
	 *		반복적으로 수행할 구문; 
	 * }
	 * for문을 만나는 순간
	 * 초기식 --> 조건식(조건검사) --> true일 경우 실행하고자 하는 구문 실행 --> 증감식
	 *      --> 조건식(조건검사) --> true일 경우 실행하고자 하는 구문 실행 --> 증감식
	 *      ...
	 *      --> 조건식(조건검사) --> false일 경우 실행하고자 하는 구문 실행 X(빠져나감)
	 * 
	 * > 매번 조건식의 조건검사를 통해
	 *   조건이 true일 경우만 반복해서 실행하는 원리!!
	 */
	
	
	
	
	public void method1() {
		
		//"안녕하세요"를 5번 반복해서 출력하고 싶다.
		// 방법1) 출력문 5번 작성 
		// sysout"안녕하세요" * 5
		
		
		// 방법2) for문을 이용한 방법
		// 반복횟수: 5회
		// i 값이 1에서부터 5보다 작거나 같을 때까지 1씩 증가할 동안 반복을 수행
		// i = 1, 2, 3, 4, 5 (반복) / 6 (빠져나감) 마지막 i = 6
		for(int i = 1; i <= 5; i++) {
			
			System.out.println("안녕하세요.");
			// i = 1 --> true --> 출력문 --> i++ -->
			// i = 2 ...
			// i = 6 --> false --> for문 } 빠져나감
		}
		
	} // method 1 영역 끝

	public void method2() {
		
		// "반갑습니다" 5번 출력
		
		// 반복횟수 5회
		// i 값이 1에서부터 6보다 작을 때까지 1씩 증가할 동안 반복 수행
		// i = 1, 2, 3, 4, 5
		for(int i = 1; i < 6; i++) {
			
			System.out.println("반갑습니다.");
		}
	
		System.out.println("----------------");
		
		//"다시만나요" 5번 출력
		
		// 반복횟수 5회
		// i 값이 11에서부터 16보다 작을 때까지 1씩 증가할 동안 반복 수행
		// i = 11, 12, 13, 14, 15
		
		for(int i = 11; i < 16; i++) {
			
			System.out.println("다시만나요");
		}
		
		/*
		 * * for문의 경우 초기식; 조건식; 증감식에 의해 반복횟수가 결정됨!
		 * > 어떻게 작성하냐에 따라 다르게 작성하더라도 반복횟수가 매번 동일할 수 있다!
		 *   (초기식의 시작값은 무조건 1일 필요는 없다)
		 *   
		 * * 증감식에 의해 결정되는 마지막 i 값도 신경쓰기! 
		 * 
		 * * 두 번째 for문의 초기식 변수명이 동일하게 i임에도 오류가 나지 않는 이유
		 * > for문 초기식에서 선언한 변수는 for{ } 안에서만 유효한 지역변수이기 때문임
		 * 
		 */
	} // 메소드2 영역 끝
	
	public void method3() {
		
		// "하이" 5번 출력
		
		// 반복횟수: 5
		// i 값을 1에서부터 10보다 작을 때까지 매번 2씩 증가할 동안 반복 수행
		// i = 1, 3, 5, 7, 9, ... / 11
		
		for(int i = 1; i < 10; i = i+2) {
			
			System.out.println("하이");
		}
		
		// > 증감식이 무조건 1씩 증가하라는 법은 없다!
		
		System.out.println("----------------");
		
		//"헬로우" 5번 출력
		// 반복횟수 5회
		// i 값이 10에서부터 6보다 크거나 같을 때까지 매번 1씩 감소할 동안 반복 수행
		// i = 10, 9, 8, 7, 6, ... / 5
		
		for(int i = 10; i >= 6; i--) {
			
			System.out.println("헬로우");
		}
		// > 감소식도 가능
		
	} // method3 end
	
	//제일 단순하게 반복되는 횟수를 지정하는 표현법(공식!)
	
	public void method4() {
		
		// "굿바이" 5회 출력
		
		// 반복횟수 5회
		// i 값이 0에서부터 5보다 작을 때까지 1씩 증가할 동안 반복 수행
		// i = 0, 1, 2, 3, 4 ... / 5(빠져나감)
		for(int i = 0; i < 5; i++) {
			
			System.out.println("굿바이");
		}
		
	} // 메소드 4 영역 끝
	
	/*
	 * * 초기식, 조건식, 증감식을 어떻게 지정하냐에 따라
	 *   반복횟수가 같아질 수도, 달라질 수도 있음!
	 *   
	 * - 제일 단순하게 반복되는 횟수를 지정하는 표현법
	 * [표현법]
	 * for(int i = 0; i < 반복횟수; i++) {
	 * 0 1 2 3 4 / 5
	 * }
	 * for(int t = 0; i < 3; i++) {
	 * 0 1 2 / 3
	 * }
	 * 
	 * * 이 공식은 저번주에 배운 index 개념 때문에 나온 공식임!
	 * - index는 0부터 시작하기 때문에 시작 수를 0으로 둔 것임!
	 * - index는 1씩 증가하기 때문에 i++로 증감식 형성
	 * (추후 문자열의 index 개념과 접목하여 유용하게 반복문 활용 가능해짐)
	 */
	
	// 반복식 사용시 주의사항
	
	public void method5() {
		
		//"반가워요" 3번 출력
		for(int i = 0; i < 3; i++) {
			
			System.out.println("반가워요");
			// 조건이 안맞으면 처음부터 false > 반복0회
			// 맞아도 false가 안나오는 상황이면 > 무한반복
			// for(int i = 1; i <= 3; i--) {
			// }
			// 이런 상황들은 지양해야됨
			// 단, 의도적으로 무한반복 돌려야 하는 상황도 있음
			// > 귀찮게 일일이 쓰지 말고, 조건식 부분에 대놓고 true 적으면 됨
			// > for(;;) 또는 for(; true;)도 가능
		}
		
		for(;;) {
			
			
			System.out.println("잘 되나?");
		}
		
		/*
		 * 참고) if문에서..
		 *  if(true) {
		 * 
		 *   이 중괄호 안의 실행문;은 무조건 실행됨!
		 * }
		 * > 굳이 이렇게 쓰진 않지만 같은 맥락
		 */
		
	} // 메소드 5 영역 끝
	
	public void method6() {
		
		//고정적인 내용이 아닌 매번 달라지는 출력 결과 보기
		// "i: 1"
		// "i: 2"
		// "i: 3"
		//  ...
		// "i: 10"
		
		// >> 이런 경우에는 항상 "규칙"을 찾아봐야 함!
		// 규칙1. 앞쪽의 "i: "까지는 공통문자열
		// 규칙2. 뒤쪽의 숫자값들은 1부터 10까지(포함) 매번 1씩 증가하면서 출력됨
		
		/*for(int i = 1; i <= 10; i++) {
			
			System.out.println("i: " + i);
		}
		*/
		
		//퀴즈) 아래의 for문을 가지고 위와 같은 결과 출력하기
		for(int i = 0; i < 10; i++) {
			
			
			System.out.println("i: " + (i+1));
		}
		
	} // method 6 end
	
	
	public void method7() {
		
		// "1 2 3 4 5 6 7 8 9 10" 출력
		
		//방법1)
		//System.out.println("1 2 3 4 5 6 7 8 9 10");
		
		//방법2) for
		//규칙: 1씩 증가, 공백, 줄바꿈x
		for(int i = 1; i < 11; i++) {
			
			System.out.print(i + " ");
		}
		
		
	} // 메소드 7 끝
	
	public void method8() {
		
		// 1에서 10까지 홀수만을 출력
		// 1 3 5 7 9
		// 규칙 2씩 증가, 공백o 줄바꿈x
		
		/*
		  for(int i = 1; i < 10; i += 2) {
			
			System.out.print(i+" ");
		}
		*/
		
		// > 반복이 애초에 5번 일어남
		// i 값에 일부러 홀수만 담기게끔 유도하는 방법
		
		//다른 방법
		// 규칙: 시작 1, 끝 9 포함, 매번 1씩 증가
		for(int i = 1; i <= 9; i++) {
			
			if(i %2 != 0) {
				
				System.out.print(i + " ");
				
			}
			
		}
		// 반복은 9번 일어나지만, 출력은 5번 발생
		// 짝수는 아무 일 안 일어남
		
	} // 메소드 8 끝
	
	public void method9() {
		
		// 1에서부터 10까지 숫자를 모두 더한 결과를 출력
		
		// 방법1) int sum = 1+2+3+...+10;
		
		// 방법2)
		// 반복문을 이용한 방법(규칙 먼저 찾기)
		/*
		 * int sum = 0;
		 * sum = sum + 1; // 1까지만 더한 결과 출력됨
		 * sum = sum + 2; // 2까지만 더한 결과 출력됨
		 * sum = sum + 3; // 3까지만 더한 결과 출력됨
		 * ...
		 * sum = sum + 10; // 10까지 모두 더한 결과 출력됨
		 * > 계속 이전 결과에 누적해서 숫자를 더해주고 있음!
		 */
		
		// 최종 덧셈 결과를 담을 변수 먼저 세팅
		int sum = 0;
		// > 누적 합을 구할 변수값은 항상 0으로 초기화 한다!(정확한 결과값을 얻어내기 위함)
		// 규칙: 시작수 1, 끝수 10 포함, 매번 1씩 증가
		for(int i = 1; i <= 10; i++) {
			
			sum += i;
			// >> sum = sum + i;
			
		}
		
		// 이 시점 기준으로 sum에는 총 덧셈 결과가 담겨있음
		System.out.println("sum: " + sum);
		
		System.out.println("-------------");
		
		// 1부터 10까지 모두 곱한 결과값 출력해보기
		
		//누적 곱을 담아둘 변수
		// > 0으로 세팅하면 결과가 0으로 나오기 때문에 1로 세팅함
		int prod = 1;
		for(int i = 1; i <= 10; i++) {
			
			prod *= i;
			
		}
		
		System.out.println("prod: " + prod);
		
	} // 메소드 9 끝
	
	public void method10() {
		
		// 사용자가 1에서부터 입력한 값 모두 더하기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("양수 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// 본격적인 덧셈 전, 사용자가 양수를 제대로 입력했나 먼저 판별하기
		if(num > 0) { // 양수일 경우
			
			int sum = 0;
			
			// 규칙: 시작수 1, 끝수 num 포함, 1씩 증가
			for(int i = 1; i <= num; i++) {
				
				sum += i;
				
			}
			
			System.out.println("sum: " + sum);
		} else { //양수가 아닐 경우
			
			System.out.println("양수를 입력하세요");
		}
		
	} // 메소드 10 영역 끝
	
	public void method11() {
		
		// 1에서부터 매번 달라지는 랜덤값(1~10 사이 정수)까지의 총 합계 출력
		
		/*
		 * * 랜덤값(임의의 값)
		 * - 자바에서 제공하는 Math 클래스에 정의되어있는
		 *   random() 메소드 호출하여 매번 다른 랜덤값을 얻어올 수 있음
		 *   
		 * [표현법]
		 * Math.random();
		 * > Math 클래스를 대변할 이름 생성하지 않고
		 *   클래스명.메소드명(); 사용해서 바로 호출!!
		 *   random 메소드 또한 static 키워드가 붙은 형태이기 때문
		 *   
		 * > Math.random();이 호출되는 순간
		 *   0.0 ~ 0.9999999999... 사이의 랜덤값 하나 발생됨
		 *   즉, 0.0 <= 발생된랜덤값 < 1.0
		 * 
		 */
		
		// System.out.println(Math.random());
		
		/*
		 * - 근데 우리가 원하는 랜덤수의 범위는 1 ~ 10
		 * 
		 * 랜덤값 0.0 ~ 0.999999999...에서
		 * 1) 10을 곱해보자
		 * 0.0 ~ 9.999999999...
		 * 
		 * 2) 1을 더해보자
		 * 1.0 ~ 10.99999999...
		 * 
		 * 3) 소수점을 날려보자
		 * 1 ~ 10
		 * 
		 * * 내가 원하는 구간의 랜덤수를 정수로 봅는 규칙(공식)
		 * [표현법]
		 * (int)(Math.random() * 몇개의랜덤수 + 시작수);
		 * 
		 * ex) 11 ~ 20 사이의 랜덤한 정수를 얻어내고 싶다
		 * (int)(Math.random() * 10 + 11);
		 * 
		 * ex) 1 ~ 45 사이의 랜덤한 정수 ~
		 * (int)(Math.random() * 45 + 1);
		 * 
		 */
		
		// 1 ~ 10 사이의 랜덤수 발생시키기(변수에 담기)
		int random = (int)(Math.random() * 10 + 1);
	
		
		
		System.out.println("현재 발생한 랜덤값: " + random);
		
		// 1 ~ random 까지 더한 결과를 담을 변수
		
	    int sum = 0;
		
		//규칙: 시작수 1, 끝수 random 포함, 1씩 증가
		for(int i = 1; i <= random; i++) {
		
			sum += i;
		}
		
		System.out.println("1에서부터 랜덤값까지의 총 합: " +sum);
	
	} // 메소드 11영역 끝
	
	public void method12() {
		
		// 사용자로부터 2 ~ 9 사이의 정수를 하나 입력받아
		// 해당 구구단을 출력해보기
		
		/*
		 * - 규칙 찾기
		 * 2단
		 * 2 x 1= 2
		 * 2 x 2 = 4
		 * 2 x 3 = 6
		 * 2 x 4 = 8
		 * ...
		 * 
		 * > 규칙: 시작수 1, 끝수 9 포함, 1씩 증가
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("2 ~ 9 사이 정수를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// 알맞은 정수 범위를 입력했는지 먼저 검사
		
		if(num >= 2 && num <= 9) { // 제대로된 범위일 경우
			
			System.out.printf("---%d단---\n", num);
			
			for(int i = 1; i <= 9; i++) {
				
				System.out.printf("%d X %d = %d \n", num, i, (num*i));
			}
			
		} else { // 제대로된 범위가 아닐 경우
			
			System.out.println("2 ~ 9 사이가 아닙니다");
		} 
		
	} // 메소드 12 영역 끝
	
	// 이 상황에서 2단에서 9단까지 모두 다 한꺼번에 출력하고 싶다면?
	// > 위에서 공부할 때 if문 안에서 for문을 중첩해서 썼고, for문 안에서 if문을 중첩해서도 썼었음!
	// > if문끼리도 중첩해서 쓸 수 있음!
	// > 마찬가지로 for문 안에 for문 중첩 가능!
	
	// 중첩 for문의 예시들
	public void method13() {
		
		// "1 2 3 4 5"
		// "1 2 3 4 5"
		// "1 2 3 4 5"
		// 이렇게 출력하고 싶음
		
		// 방법1) sysout 12345 3번 쓰기
		
		// 방법2) for문 사용
		/*
		for(int i = 0; i < 3; i++) {
			
			System.out.println("1 2 3 4 5");
		 	*/
		
		// 방법3) for
		for(int i = 0; i < 3; i++) {
			
			// System.out.println("1 2 3 4 5");
			// > 규칙: 시작수 1, 끝수 5 포함, 1씩 증가
			// 		  줄바꿈 없이 공백 출력
			for(int j = 1; j <= 5; j++) {
				
				System.out.print(j + " "); // "1 2 3 4 5"
			}
			System.out.println();
			// > 다음 증감식으로 넘어가기 전에 개행 넣기
			
		}
		// for문을 2겹 중첩해서 사용한 방법(중첩 for문, 이중 for문)
		// 바깥쪽 반복횟수 3번 * 안쪽 반복횟수 5번 = 총 15번 반복 일어남!!
		
		/*
		 * * 중첩 for문의 반복횟수는
		 *   안쪽 for문 반복횟수 * 바깥쪽 for문 반복횟수
		 * > 반복횟수가 기하급수적으로 늘어나기 때문에 비효율적임
		 *   (세 겹 이상으로 쓰지 말자..) 실무에서도 쓸 일 거의 없음
		 * 
		 */
	} // 메소드 13 영역 끝
	
	public void method14() {
		
		// *****
		// *****
		// *****
		// *****
		// *****
		// 위 모양으로 출력하시오
		
		//방법1) sysout"*****" 5줄
		
		//방법2)
		/*
		for(int i = 0; i < 5; i++) {
			
			System.out.println("*****");
		}
		*/
		// > 반복이 총 5회 진행됨
		
		//방법3)
		/*
		for(int i = 0; i < 5; i++) {
			
			//System.out.println("*****");
			// > 규칙: *이 5개, 개행, 공백문자 없이 출력
			for(int j = 0; j < 5; j++) {
				
				System.out.print("*");
				
			} 
			System.out.println();
		}
		*/
		
		//방법4)
		// > for문을 단 한 겹만 쓰되, 반복이 총 25번 진행되게끔 유도하는 방법
		for(int i = 1; i <= 25; i++) {
			
			System.out.print("*");
			// 별이 5개 찍힐 때마다 줄바꿈 하고 넘어감
			// ***** : i ==5 줄바꿈
			// ***** : i ==10 줄바꿈
			// ...
			// > i가 5의 배수일 때만 줄바꿈 구문 추가
			// 어떤 수가 5의 배수인가? 어떤수 & 5 == 0
			if(i % 5 == 0) {
				
				System.out.println();
			}
			
			
		}
		// > *을 최종적으로 25개 출력해야 하는 상황(반복 25회 진행)
		// 단, * 5개마다 줄바꿈 해야됨
		// 아무리 for문을 한 겹만 쓰더라도 최종반복횟수는 25번 일어남!
		// 중첩 for문을 쓴 것과 다를 바가 없다!
		
		/*
		 * * 알고리즘
		 * - 어떻게 코드를 짜야 컴퓨터가 최대한 일을 적게하면서 같은 실행결과를 뽑아낼 수 있나?
		 * 	 관해 고민하는 학문
		 * - 프로그램의 성능(효율성)은 "시간복잡도"와 "공간복잡도" 개념이 결합되어 측정됨
		 * > 시간복잡도: 컴퓨터가 일하는 횟수(우리가 신경써야할 것)
		 * > 공간복잡도: 메모리 공간이 얼마나 적게쓰이느냐(자바에서는 신경쓰지 않아도 됨)
		 * - 성능이 제일 좋게 나오는 코드: 최상의 경우
		 * - 성능이 제일 안 좋게 나오는 코드: 최악의 경우
		 * 
		 */
	} // 메소드 14 영역 끝
	
	public void method15() {
		
		// 1***		1: 1행 1열 자리에 있음
		// *2**		2: 2행 2열 자리에 있음
		// **3*		3: ~
		// ***4		4: ~
		
		// 총 4줄이 출력되고 있으므로 반복을 4번 진행
		for(int i = 1; i <= 4; i++) { // i는 행
			
			// System.out.println("1***");
			// > 무엇을 출력하든 간에 글자 수가 총 4개이므로 반복을 4번 진행
			for(int j = 1; j <=4; j++) { // j는 열
				
				// 이 for문 안쪽에서는 글자 1개를 출력해야함!
				// *또는 1또는 2또는 3또는 4
				// 어느 경우엔 * 출력? 또 1 출력?(선택적)
				if(i == j ) { // 일치하면 숫자를 출력
					
					System.out.print(i); // j 써도 됨
					
				} else { // 일치하지 않으면 * 출력
					
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
		
	} // 메소드 15 영역 끝
	
	public void method16() {
		
		// *		* 1개
		// **		* 2개
		// ***		~
		// ****		~
		// *****	~
		
		// 총 5줄 --> 반복 5회
		for(int i = 1; i <= 5; i++) {
			
			// *을 행수 i만큼 출력하고 넘어가야함!
			// 즉, i번 반복하겠다...
			for(int j = 1; j <= i; j++) {
				
				System.out.print("*");
			}
			
			System.out.println();
		}
		
	} // method 16 end
	
	public void method17() {
		
		// *****
		// ****
		// ***
		// **
		// *
		
		// 총 5줄을 출력해야 함 반복 5회
		for(int i = 5; i >= 1; i--) {
			
			// 별을 i개 만큼만 출력하고 줄바꿈하기
			for(int j = 0; j < i; j++) {
				
				System.out.print("*");
			}
			
			System.out.println();
		}
	} // 메소드 17 영역 끝
	
	//구구단 모두 출력
	public void method18() {
		
		// 2 ~ 9단까지 모두 출력
		
		/*
		 * --- 2단 ---
		 * 2 x 1 = 2
		 * 2 x 2 = 4
		 * ...
		 * 2 x 9 = 18
		 * 
		 * --- 3단 ---
		 * 3 x 1 = 3
		 * ...
		 * 3 x 9 = 27
		 * 
		 * --- 4단 ---
		 * ...
		 * --- 9단 ---
		 * 9 x 9 = 81
		 */
		
		System.out.println("2 ~ 9단까지 모두 출력");
		
		
		for(int i = 2; i <= 9; i++) {
			
			System.out.printf("--- %d단 --- \n", i);
			
			for(int j = 1; j <= 9; j++) {
				
				System.out.printf("%d X %d = %d \n", i, j, (i*j));
			}
		
		}
		
	} // method 18 끝
	
	
	// for문 활용 예시 - index 응용
	public void method19() {
		
		// 사용자로부터 영문 문자열 입력받아
		// 그 문자열을 각 인덱스 자리의 글자를 출력(반복문 활용)
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("영문 문자열 입력: ");
		String str = sc.nextLine();
		
		/*
		 * ex) "apple" 입력한 경우 - 5번 출력(글자가 5개)
		 * 0번째 글자: a
		 * 1번째 글자: p
		 * ...
		 * 4번째 글자: e
		 * 
		 * "hi" - 2번 출력(글자 2개)
		 * "new" - 3번 출력(글자 3개)
		 * 
		 * > 즉, 반복은 결국 사용자가 입력한 문자열의 글자 개수만큼 반복해야됨!
		 *   index는 0부터 시작, 시작수도 항상 0이야됨!
		 *   
		 */
		
		/*
		 * * 문자열의 글자수를 세는 방법(문자열의 길이 알아내기)
		 * [표현법]
		 * 문자열.length();
		 * 
		 */
		
		// 규칙: 시작수는 0, 글자 수만큼 반복 돌리기!
		for(int i = 0; i < str.length(); i++) {
			
			System.out.println(i + "번째 글자: " + str.charAt(i));
		}
		
		// for문의 초기식 변수명을 늘 i로 쓴 이유: index의 약자
		
	}
	
	public void method20() {
		
		// 1 ~ 45까지 정수중 랜덤한 정수값을 뽑아서
		// 총 6개 출력(단, 중복 가능, 단순 출력만)
		
		
		int random1 = (int)(Math.random() * 45 + 1);
		int random2 = (int)(Math.random() * 45 + 1);
		int random3 = (int)(Math.random() * 45 + 1);
		int random4 = (int)(Math.random() * 45 + 1);
		int random5 = (int)(Math.random() * 45 + 1);
		int random6 = (int)(Math.random() * 45 + 1);
		
		
		
		System.out.printf("%d %d %d %d %d %d", random1, random2, random3, random4, random5, random6);
		
	}
	
	public void method21() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("출력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// num까지의 정수 합
		// num = 5 --> 1 2 3 4 5 합
		
		int sum = 0; // 초기화
		
		for(int i = 1; i <= num; i++) {
			
			sum += i;
			
			if(i == num) {
				
				System.out.print(i); // 시작수 마지막수 같으니 그냥 숫자 하나만
		} else {
			
			System.out.print( i + "+"); // 다르면 다 1+2+ 형식으로 출력
		}
			
		}
		System.out.print("=" + sum); // 마지막 결합 빠져나와서, 합계
	} // 메소드 21 영역 끝
	
	public void practice1() {
		
		//실습문제 1 반복문
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1 이상의 숫자를 입력하세요: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if(num < 1) {
			
			System.out.println("잘못 입력했습니다.");
		} else {
			
			for(int i = 1; i <= num; i++) {
				
				System.out.print(i + " ");
			}
		}
	} // practice 1 영역 끝
	
	public void practice2() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("1 이상의 숫자를 입력: ");
			int num = sc.nextInt();
			sc.nextLine();
			
			if(num > 0) {
				
				for(int i = 1; i <= num; i++) {
					
					System.out.print(i + " ");
					
				}
				break;
				
			} else {
				
				System.out.println("잘못입력 다시");
			}
				
		}
	} // practice 2 메소드 영역 끝
	
	public void practice3() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1 이상의 숫자를 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if(num < 1) {
			
			System.out.println("잘못입력");
			
		} else {
			
			for(int i = num; i >= 1; i--) {
				
				System.out.print(i + " ");
			}
		}
			
	} // practice 3 메소드 영역 끝
	
	public void practice4() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("1 이상의 숫자 입력: ");
			
			int num = sc.nextInt();
			sc.nextLine();
			
			if(num >= 1) {
				
				for(int i = num; i >= 1; i--) {
					
					System.out.print(i + " ");
					
				}
				break;
				
			} else {
				
				System.out.println("다시 입력");
			}
			
		}
		
	} // 프락티스 4 영역 끝
	
	public void practice5() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력하세요 하나: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		int sum = 0;
		
		
		for(int i = 1; i <= num; i++) {
			
			sum += i;
			
		  if(i == num) {
				
				System.out.print(i);
				
			} else {
				
				System.out.print(i + "+");
			}
		  
		}
		System.out.print("=" + sum);

	}
}
