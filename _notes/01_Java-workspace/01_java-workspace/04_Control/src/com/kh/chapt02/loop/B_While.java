package com.kh.chapt02.loop;

// while문, do-while문
public class B_While {
	
	/*
	 * * while문
	 * - 반복횟수가 다소 명확하지 않을 때 사용하는 반복문
	 * - 조건식을 제시하고, 그 조건검사 결과가 매번 true인지 false인지에 따라
	 *   반복을 더 진행할지 말지를 판별하는 수행 원리
	 *   
	 * * while문을 이왕이면 for문과 유사한 형태로 써보자!!
	 * [표현법]
	 * 초기식; // 필수 X, for문과 비슷해 보이려고
	 * while(조건식) { // 조건검사 결과가 true일 경우 해당 구문 반복실행
	 * 				 // 조건검사 결과가 false일 경우 해당 구문 빠져나감
	 * 
	 * 		반복적으로 실행할 구문;
	 * 		증감식; // 필수 X, for문과 비슷해 보이려고
	 * }
	 * > while문은 초기식과 증감식이 원래는 없음
	 * > 조건식 부분에 대놓고 true를 쓰면 무한반복 출력
	 * > 즉, while(true)는 for(;;)과 같음(while true를 관례상 더 많이씀)
	 */
	
	public void method1() {
		
		// "안녕하세요" 5번 출력
		// for문)
		//for(int i = 0; i < 5; i++) {
		
			//System.out.println("안녕하세요");
		
		//}
		// for문의 경우 for문 바깥 마지막 i 값은 확인 불가능함
		// for문 초기식에서 선언한 변수는 for문 안 쪽에서만 쓸 수 있고. 끝나면 사라짐
		
		// while문)
		int i = 0; //초기식
		while(i < 5) { //조건식
			
			System.out.println("안녕하세요"); //반복적으로 실행할 구문
			i++; // 증감식
		} // i = 0, 1, 2, 3, 4 (반복) / 5(빠져나간 값)
		
		System.out.println("마지막 i값은: "+ i);
		// while문으로 반복할 경우는 while문 밖에서 마지막 i 값 확인 가능하다!!
		// 초기식 자체가 while문 바깥에서 선언되었기 때문임
		// 지역변수 관련 사항
		
	} // 메소드1 영역 끝
	
	public void method2() {
		
		// "i: 1"
		// "i: 2"
		// ...
		// "i: 10"
		// 규칙) 시작수 1, 끝수 10, 1씩 증가
		
		//for(int i = 1; i <= 10; i++) {
			
		//	System.out.println("i: " + i);
		//}
		
		// sysout 마지막 i: 진행시 i == 11이지만, 지역변수 때문에 확인 불가할 뿐임
		
		int i = 1;
		while(i <= 10) {
			
			System.out.println("i: " + i);
			i++;
		}
		
		System.out.println("마지막 i: " + i);
		// 반복은 10. 마지막 i는 11(증감식에 의해)
		
	} // 메소드 2 영역 끝
	
	public void method3() {
		
		// "1 2 3 4 5"
		// 규칙: 시작수 1, 끝수 5, 1씩 증가
		
		//for(int i = 1; i <= 5; i++)
		
		int i = 1;
		while(i <= 5) {
			
			System.out.print(i++ + " ");
			// i++;
			// 증감식을 생략하는 대신,
			// i 출력 후 1 증가될 수 있게끔 "후위연산자"를 이용할 수 있다!!
		}
		
		System.out.println();
		System.out.println("마지막 i: " + i); // 6
		
		// "5 4 3 2 1" 출력
		// > 시작수 6(마지막 i 변수값 재활용), 끝수 1, 1씩 감소
		
		// i = 6; // 현재 i 변수에는 6이 담겨있음
		while(i > 1) {
			
			// i--;
			// 시작수가 내가 출력하고자 하는 값보다 1 크기 때문에
			// 증감식을 오히려 위쪽에 작성한 것
			System.out.print(--i + " ");
			// "전위연산자"를 이용한 증감식 작성
			// i--;
		}
		System.out.println();
		System.out.println("마지막 i: " + i); // 1
		
	} // 메소드 3 영역 끝
	
	public void method4() {
		
		// "1 3 5 7 9"
		// 홀수만 출력
		// 시작수 1, 끝수 9, 매번 2씩 증가
		// for(int i = 2; i <= 9; i += 2)
		
		//방법2)
		//int i = 1;
		//while(i <= 9) {
			
		//	System.out.print(i + " ");
		//	i += 2;
		//}
		
		// 방법3)
		// 마지막 i: 11
		// 홀수일 경우에만 출력 (for문 안에 if문)
		
		int i = 1;
		while(i <= 9) {
			
			if(i % 2 != 0) {
				
				System.out.print(i + " ");
			}
			i++;
		}
		
	} // 메소드 4 영역 끝
	
	public void method5() {
		
		// 1 ~ 10까지의 합계
		// 시작수 1, 끝수 10, 1씩 증가
		
		int sum = 0; // 총합을 담아둘 변수
		int i = 1;
		while(i <= 10) {
			
			sum += i;
			i++;
		}
		// 마지막 i: 11
		System.out.println(sum);
		
	} // 메소드 5 영역 끝
	
	// 무한반복 예시
	public void method6() {
		
		int i = 1;
		while(true) {
			
			System.out.println("i: " + i);
			i++;
		}
		// 의도적으로 무한반복을 돌리고 싶다면
		// 조건식 부분에 대놓고 true 고정값을 기술하면 된다
		
	} // 메소드 6 영역 끝
	
	/*
	 * * do-while문
	 * - 별도의 조건검사 없이 반복 시작 전에 무조건 해당 구문을
	 *   한 번 미리 실행(do - 그냥 해) 하고 나서 반복이 진행(while)되는 구문
	 *   
	 * [표현법]
	 * do {
	 * 
	 * 		반복적으로 실행할 구문;
	 * 
	 * } while(조건식);
	 * 
	 * > 별도의 조건검사 없이 무조건 실행할 코드를 한 번 먼저 실행
	 *   (즉, 조건검사 결과가 처음부터 false라도 한 번은 적어도 실행됨)
	 * > 그 이후 조건식을 통해 조건검사 후
	 *   해당 조건검사 결과가 true면 실행할 코드를 다시 실행
	 *   				 false면 반복문을 빠져나감
	 */
	
	public void method7() {
		
		// while문
		/*
		int num =1;
		while(num == 0) {
			
			System.out.println("안녕");
		}
		*/
		// 애초에 처음부터 조건 틀림
		// while문 반복 실행 0회
		
		// do-while문
		int num = 1;
		do {
			
			System.out.println("안녕");
			
		} while(num == 0);
		// 애초에 조건 맞지 않아도
		// do-while문은 적어도 단 한 번은 일어나게 되어있음
		
		// do-while문은 개발자가 원하는 반복횟수 + 1회
		// 특이해서 실무로는 잘 쓰이지 않음
		
	} // 메소드 7 영역 끝
	
	public void method8() {
		
		// "1 2 3 4 5"
		// while문 버전
		/*
		int i = 1;
		while(i <= 5) {
			
			System.out.print(i + " ");
			i++;
		}
		*/
		
		// i = 1, 2, 3, 4, 5, 반복 / 6(빠져나감)
		// 조건검사 6번, 5번이 true, 반복 5회
		
		// do-while 버전
		int i = 1;
		do {
			
			System.out.print(i + " ");
			i++;
			
		} while(i <= 5);
		// i = 1, 2, 3, 4, 5, 반복 / 6(빠져나감)
		// 조건검사 5번, 4번이 true, 반복 4회
		// 조건검사 전 한 번은 그냥 서비스로 실행했기 때문에 조건검사 5번임
		
	}
	
}
