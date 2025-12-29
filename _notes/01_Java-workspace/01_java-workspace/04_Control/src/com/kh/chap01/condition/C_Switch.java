package com.kh.chap01.condition;

import java.util.Scanner;

// switch문
public class C_Switch {
	
	/*
	 * * switch문
	 * - if문 계열은 대놓고 "조건식"을 직접 기술해서 선택적인 흐름을 만들어내는 조건문이었다면,
	 * - switch문의 경우에는 직접 "조건식"을 기술하지 않고도 선택적인 흐름을 만들어낼 수 있는 조건문임
	 *   (문법에 따라 switch문을 기술하면 내부적으로 알아서 "동등비교" 연산을 수행)
	 * 
	 * * switch문과 if문 계열의 차이점
	 * - if(조건식): 조건식을 내가 직접 제시
	 *				내가 원하는대로, 복잡하게 조건식을 기술할 수 있다.(특히 범위 검사)
	 * 
	 * - swtich: 조건식을 내가 직접 제시 안함
	 *           내부적으로 알아서 조건검사가 발생함
	 *           (확실한 값의 조건일 경우에만, 즉 "동등비교"가 발생하는 원리)
	 * 
	 * [표현법]
	 * switch(동등비교할대상자) {
	 * case 값1: //내부적으로 동등비교할대상자 == 값1
	 * 		실행할코드1; // 해당 동등비교 결과가 true일 경우 실행됨
	 * 		break; // 이 switch문 블록 {} 자체를 빠져나가겠다.
	 * case 값2: //내부적으로 동등비교할대상자 == 값2
	 * 		실행할코드2;
	 * 		break;
	 * case 값3: //내부적으로 동등비교할대상자 == 값3
	 * 		실행할코드2;
	 * 		break;
	 * ... (원하는 case 값을 찾을 때까지)
	 * case 값n :
	 * 		실행할코드n;
	 * 		break;
	 * default: 
	 *  	위의 값1 ~ 값n까지 일치하는 값을 찾지 못한 경우 실행할 코드
	 *   	(if문 계열의 else와 같은 역할!)
	 *   	//break; 안써도됨
	 *   	// > default는 어차피 switch 블럭{} 마지막 코드기 때문에
	 *   	//   굳이 break;는 적지 않아도 된다.
	 * }
	 * 
	 */
	
	public void method1() {
		
		// 1 ~ 3 사이의 정수값을 입력받아
		// 1인 경우 "빨간불입니다"
		// 2인 경우 "파란불~"
		// 3	   "초록불~" 출력
		// 잘못 입력 "잘못입력했습니다
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1 ~ 3 사이의 정수 입력: ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// if문 계열 버전
		/*
		 if(num == 1) {
			
			System.out.println("빨간불입니다.");
			
		} else if(num == 2) {
			
			System.out.println("파란불입니다.");
			
		} else if(num == 3) {
			
			System.out.println("초록불입니다.");
			
		} else {
			
			System.out.println("잘못입력했습니다.");
		}
		*/
		
		// switch문 버전
		/*
		switch(num) {
		case 1: // num == 1
			System.out.println("빨간불입니다.");
			break;
		case 2:
			System.out.println("파란불입니다.");
			break;
		case 3:
			System.out.println("초록불입니다.");
			break;
		default:
			System.out.println("잘못입력했습니다.");
		
		}
		*/
		
		// 판별 결과를 담아둘 변수 먼저 세팅(비슷한 값 출력시)
		String result = ""; // String은 빈 문자열
		
		switch(num) {
		
		case 1:
			result = "빨간불";
			break;
		case 2:
			result = "파란불";
			break;
		case 3:
			result = "초록불";
			break;
		default:
			System.out.println("잘못입력했습니다.");
			return;
			// return문은 현재 실행중인 메소드 영역 벗어나도록 만듦
			// 즉, 이 메소드에 남은 코드가 100줄이 있더라도
			// 당장 흐름을 멈추고 메소드를 호출했던 곳(태초마을 메인메소드)으로 돌아감
			
		}

		// 이 시점에서 판별 결과를 가지고 단 한 번 출력문을 작성
		System.out.println(result + "입니다.");
		// > num 값에 의해 default 구문을 타고 넘어온 경우
		//   "입니다"만 출력됨,
		// > 안 출력됐으면 좋겠음
		
		
		
	} // 메소드1 영역 끝
	
	public void method2() {
		
		// 구매할 과일명 입력받아
		// 과일마다 가격 출력
		// 사과 1000원
		// 바나나 2000원
		// 복숭아 5000원
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-----과일가게에 온 것을 환영합니다 -----");
		System.out.print("구매할 과일 (사과, 바나나, 복숭아) 입력: ");
		
		String fruit = sc.nextLine();
		
		// if문 계열 버전
		// if(fruit == "사과")
		
		/*
		if(fruit.equals("사과")) {
			
			System.out.println("1,000원입니다.");
			
		} else if(fruit.equals("바나나")) {
			
			System.out.println("2,000원입니다.");
			
		} else if(fruit.equals("복숭아")) {
			
			System.out.println("5,000원입니다.");
			
		} else {
			
			System.out.println("안팝니다.");
		}
		*/
		
		
		// > 과일명이 제대로 동등비교가 되고 있지 않다!
		// > 문자열 String의 경우 "참조자료형"이기 때문에
		//   ==으로 동등비교를 할 경우 문자열의 "내용물"이 아닌 "주소값"을 기준으로
		//   동등비교가 일어난다!
		// > 문자열의 경우 "내용물"을 기준으로 동등비교해주는 별도의 구문이 있다.
		//   equals 메소드
		// [표현법]
		// 문자열.equals(동등비교할문자열);
		// > 문자열 내용이 일치하면 true, 일치하지 않으면 false
		
		//switch문 버전
		switch(fruit) {
		case "사과":
			System.out.println("1,000원입니다.");
			break;
		case "바나나":
			System.out.println("2,000원입니다.");
			break;
		case "복숭아":
			System.out.println("5,000원입니다.");
			break;
		default:
			System.out.println("안팔아요.");
		
		}
		
		// switch문은 문자열도 내용물 알아서 잘 동등비교해줌!
		
		
	} // 메소드2 영역 끝
	
	// 특이케이스 - break 생략하는 경우
	public void method3() {
		
		// 사용자로부터 키보드로 등급을 입력받아
		// 해당 등급별로 권한을 출력하기
		// 1등급: 관리권한, 쓰기권한, 읽기권한
		// 2등급: 쓰기권한, 읽기권한
		// 3등급: 읽기권한
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("등급 입력: ");
		int grade = sc.nextInt();
		sc.nextLine();
		
		switch(grade) {
		case 1:
			System.out.println("관리권한");
			// System.out.println("쓰기권한");
			// System.out.println("읽기권한");
			// break;
		case 2:
			System.out.println("쓰기권한");
			// System.out.println("읽기권한");
			// break;
		case 3:
			System.out.println("읽기권한");
			break;
		default:
			System.out.println("권한없음");
			
		}
		
		// switch문은 case 중 동등비교대상자와 일치하는 값을 찾아서
		// 그 시점부터 코드가 실행되기 시작한다!
		// break;를 만날 때까지만 실행된다!
		// switch문에서 break;를 생략하는 순간
		// 다음 break;를 만날 때까지 또는 switch문이 종료될 때까지
		// 아래로 계속 흐름
		
		
		
	} // 메소드3 영역 끝

}
