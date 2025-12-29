package com.kh.array;

import java.util.Arrays;
import java.util.Scanner;

// 배열 개념
public class A_Array {
	
	/*
	 * * 변수
	 * - "해당 자료형의 값 단 하나만" 담을 수 있는 개념
	 * ex)
	 * int a = 10;
	 * a = 20;
	 * > 값이 20으로 바뀌어서 저장되는 것이지, 10과 20 둘 다 동시에 저장되지 않음
	 * 
	 * * 배열
	 * - "해당 자료형의 값 여러개"를 담을 수 있는 개념
	 *   (같은 자료형의 변수가 여러개 한 묶음으로 있는 느낌)
	 * ex)
	 * int[] arr = new int[3]; // [0] [1] [2]
	 * > int 자료형의 값들이 들어갈 수 있는 방 3개를 만들겠다.
	 * > [0], [1], [2] 방을 만든 것
	 *   (이 때, 방 번호를 "인덱스"라고 부름. 0부터 시작함)
	 * arr[0] = 1;
	 * arr[1] = 5;
	 * arr[2] = 10;
	 *   
	 * * 자바의 인덱스 개념
	 * - 문자열, 배열에 모두 존재(똑같이 적용되는 개념)
	 * - 0부터 1씩 증가하는 순번
	 *   for(int i = 0; i < 반복횟수; i++)
	 * - 문자열의 길이 == 5, 시작인덱스 == 0, 마지막인덱스 == 4
	 * - 배열의 크기 ==3, 시작인덱스 == 0, 마지막인덱스 == 2
	 * 
	 * 항상 크기 == n, 시작인덱스 == 0, 마지막인덱스 == n-1
	 * > 그래서 위의 for문 공식이 나온 것!
	 *   for(int i = 0; i < 크기' i++)
	 *   i < 크기: i가 크기 -1까지만 반복을 돌리겠다!
	 * 
	 */
	
	public void method1() {
		
		// 배열을 왜 써야할까?
		// 변수만 가지고 프로그래밍을 한다면..
		// 0, 1, 2, 3, 4 다섯개의 정수를 기록하고 싶음
		
		/*
		int num1 = 0;
		int num2 = 1;
		int num3 = 2;
		int num4 = 3;
		int num5 = 4;
		// > 내가 기록하고 싶은 데이터 개수만큼 변수를 만들어야함
		
		// 출력할 때는?
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
		System.out.println(num4);
		System.out.println(num5);
		
		// 총 합계를 구해야 한다면?
		// int sum = num1 + num2 ...;
		
		int sum =0;
		
		for(int i = 1; i <=5; i++) {
			
			// System.out.println(numi);
			
			// sum += numi;
			
			// numi라고 해서 num1, num2, --- 이렇게 변수명이 완성되는 것이 아닌
			// 이름 그대로 numi라는 변수를 찾게 되는 것
			
		}
		
		// > 변수만을 가지고 프로그래밍을 하면 반복문 사용이 불가하다!
		*/
		
		// 그래서 나온 개념이 배열!
		// 배열을 활용해 프로그래밍 하기
		// 정수 0, 1, 2, 3, 4 다섯개의 값을 기록해야함
		
		/*
		 * * 배열 사용법
		 *  
		 * 1. 배열 선언
		 * 
		 * [표현법] 두가지 방법 있음
		 * - 자료형 배열명[];
		 * - 자료형[] 배열명; // 이 표현법 더 많이 씀
		 */
		
		// 변수 선언
		//int a;
		
		// 배열 선언
		//int arr1[]; / 방법 1)
		//int[] arr2; / 방법 2)
		
		// > 배열의 선언은 변수 선언과 마찬가지로 메모리의 Stack 영역에 빈 사장자가 만들어지는 꼴임
		
		/*
		 * 2. 배열할당
		 * - 이 배열 한 묶음 몇 개의 값들을 보관할 것인지
		 *   이 배열의 실질적인 "크기(사이즈)"를 지정해주는 과정
		 * - 즉, 몇 칸짜리 배열로 만들 것이냐를 지정하는 부분
		 *   내가 지정한 개수만큼 값이 들어갈 방이 만들어짐!!
		 *   (실제 값이 저장될 공간을 확보하는 것을 배열할당이라고 함
		 * 
		 * [표현법]
		 * 배열명 = new 자료형[배열크기];
		 */
		
		// 1. 배열 선언
		//int[] arr;
		
		// 2. 배열 할당
		//arr = new int[5];
		
		// > 여기까지 배열의 선언 후 할당 과정까지 거쳐짐
		//   이제 비로소 배열을 쓸 준비가 완료된 것!
		
		// 변수 때도 선언 따로 대입 따로 했었음!
		// int a; / 변수 선언
		// a = 10; / 변수 대입
		
		// 변수의 선언과 동시에 초기화 또한 가능
		// int a = 10;
		
		// 배열 또한 선언과 동시에 할당 가능함
		/*
		 * 2_2. 배열의 선언과 할당 동시진행
		 * 
		 * [표현법]
		 * 자료형[] 배열명 = new 자료형[배열크기];
		 */
		
		int[] arr = new int[5];
		
		/*
		 * 3. 배열의 각 인덱스 자리에 값 대입(보관)하는 법
		 * 
		 * [표현법]
		 * 배열명[인덱스] = 값;
		 */
		/*
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 3;
		arr[4] = 4;
		*/
		
		// 위의 대입 구문을 반복문을 활용한 버전으로 바꾸기
		for(int i = 0; i < 5; i++) {
			
			arr[i] = i;
			
		}
		
		// 배열 내용물 출력
		/*
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		System.out.println(arr[4]);
		*/
		
		// 출력 또한 반복문 버전으로 바꾸기
		for(int i = 0; i < 5; i++) {
			
			System.out.println(arr[i]);
			
		}
		
		// > 대입용 반복문이든, 출력용 반복문이든 간에
		// for(int i = 0; i < 5; i++)
		// 이 때, for문의 조건식 부분 i < 5는 결국
		// 5가 이 배열의 크기(사이즈)가 되기 때문에 마지막 인덱스까지만 정확히 반복 도는 꼴
		
		// System.out.println(arr[2]); // 2
		// arr[2]는 arr에 들은 주소값을 기준으로 타고 들어가서
		// 그 주소지에 있는 방들 중에서 [2]번 방에 있는 값을 꺼내오겠다라는 뜻!
		
		System.out.println(arr); // [I@279f2327 (주소값)
		// arr이라는 배열명만 출력해봤더니 뭔가 외계어같은 내용이 출력되고 있음
		
	} // 메소드 1 영역 끝
	
	public void method2() {
		
		int i = 10;
		
		int[] iArr = new int[5]; // [0] [1] [2] [3] [4]
		// new: 
		
		System.out.println(i); // 10
		System.out.println(iArr); // [I@279f2327
		
		double d =10.0;
		
		double[] dArr = new double[3]; // [0] [1] [2]
		
		System.out.println(d); // 10.0
		System.out.println(dArr); // [D@2ff4acd0 (주소값)
		
		// > 어느 타입의 배열이든 간에
		//   배열명만 출력했을 경우 외계어 출력
		// 이유: 배열은 사실 "참조자료형 변수"임
		//      따라서 해당 배열명 변수에 들은 값은 주소값임
		
		// - 주소값과 관련된 유용한 메소드
		System.out.println(iArr.hashCode()); // [I@279f2327 --> 664740647
		System.out.println(dArr.hashCode()); // [D@2ff4acd0 --> 804564176
		// > hashCode() : 변수에 담긴 주소값을 십진수의 형태로 나타내줌
		//   (해시코드: 주소값을 십진수 형태로 나타낸 것)
		
		/*
		 * * 기본자료형(8개)
		 * - boolean, char, byte, short, int, long, float, double
		 * > 실제 값을 바로 담을 수 있는 변수(즉, 일반변수)
		 * 
		 * * 참조자료형(셀 수 없음 多)
		 * - String, int[], double[], char[], String[], ..., Scanner, A_Array, ...
		 * > 실제값은 메모리 공간 어딘가에 따로 담겨있고
		 *   그 실제값이 담긴 곳의 "주소값"을 담을 수 있는 변수
		 *   (주소값을 "참조"해서 실제값이 들어있는 곳을 찾아가겠다)
		 *    즉, 참조변수(레퍼런스변수)
		 * > 표면상의 사이즈는 4byte임(주소값이 4byte 형식으로 저장됨)
		 * 
		 */
		
		
	} // 메소드 2 영역 끝.
	
	public void method3() {
		
		// 배열과 반복문
		
		int[] iArr = new int[3]; // [0] [1] 2[]
		double[] dArr = new double[3]; // [0] [1] [2]
		
		// iArr 배열의 각 방에 들은 데이터를 출력해보고 싶다!
		// System.out.println(iArr[0]);
		// System.out.println(iArr[1]);
		// System.out.println(iArr[2]);
		
		for(int i = 0; i < iArr.length; i++) {
			
			System.out.println(iArr[i]);
		}
		
		// dArr 마저 출력
		for(int i = 0; i < dArr.length; i++) {
			
			System.out.println(dArr[i]);
		}
		
		/*
		 * * 배열을 출력할 때 반복문을 쓰면 더 편리하다.
		 * - 0번 인덱스에서부터 마지막 인덱스까지 순차적으로 각 방에 접근하면서 출력할 수 있다.
		 * - 마지막인덱스 == 배열의크기 - 1
		 * - 반복횟수: 배열의 크기만큼
		 * for(int i = 0; i < 배열의크기; i++)
		 * 
		 * > 매 번 배열마다 for문을 적용할 때 배열의 크기를 일일이 세서 조건식을 적기 귀찮
		 * > 배열의 크기를 알아내는 방법을 이용하면 손쉽게 조건식 작성 가능!
		 * 
		 * [표현법]
		 * 배열명.length; // 메소드가 아님! 속성이라고 부름
		 * 
		 * 참고)
		 * 문자열의 길이를 알고싶다면?
		 * 문자열.length(); // 메소드 호출
		 */
		
		// iArr, dArr 배열 크기 출력
		System.out.println(iArr.length); // 3
		System.out.println(dArr.length); // 3
		
		/*
		 * * 각 배열 선언 및 할당까지 하고 각 인덱스에 값을 한 번도 직접 대입한 적 없어도
		 *   iArr 배열의 각 인덱스에는 0, dArr 배열의 각 인덱스에는 0.0 담겨있음
		 *   (잘 출력됨 == 잘 담겨있음)
		 * 
		 * 이유: 배열에서 실제 데이터들이 담겨있는 부분은 메모리의 heap 영역임!
		 *      이 메모리의 heap 영역이라는 공간은 절대 빈공간으로 존재할 수 없다는 특징
		 *      배열이 할당되는 순간(heap영역에 공간이 확보되는 순간)
		 *      heap영역의 특징상 비어있을 수 없기 때문에
		 *      JVM이 자동으로 0 또는 0.0으로 초기화를 해줌(0, 0.0은 각 타입 별 기본값임)
		 *      
		 * * 자바 변수 타입별 기본값
		 * - int, byte, short, long: 0
		 * - double, float: 0.0
		 * - char: ' '(공백)
		 * - boolean: false
		 * - String, 기타 참조자료형들: null(주소값이 없음을 나타내는 "값")
		 * 
		 */
		
	} // 메소드 3 영역 끝
	
	public void method4() {
		
		int[] arr = new int[5];
		// [0] [1] [2] [3] [4]
		//  0   0   0   0   0 (기본값)
		
		// 내가 원하는 값들로 직접 값을 대입
		// 배열명[방번호] = 값;
		// arr[0] = 1;
		// arr[1] = 2;
		// arr[2] = 3;
		// arr[3] = 4;
		// arr[4] = 5;
		
		// 대입구문 또한 규칙만 잘 찾으면 반복문 가능
		for(int i = 0; i < arr.length; i++) {
			
			arr[i] = i + 1;
			
		}
		// > 이 시점 기준으로 배열의 각 인덱스에는 내가 원하는 값으로 모두 대입이 일어났을 것!
		
		// 값 출력을 위한 for문
		for(int i = 0; i < arr.length; i++) {
			
			System.out.println(arr[i]);
			
		}
		
		//System.out.println(arr[10]);
		// > 문법 자체는 알맞아서 빨간 밑줄로 알려주지 않음!
		// 단, 실행 중 ArrayIndexOutOfBoundsException 오류 발생
		// 배열의 크기를 벗어나서 인덱스에 접근한 경우 발생하는 오류
		// (5칸짜리 0 ~ 4 인덱스만 갖고 있는 배열인데 갑자기 10번 인덱스에 접근하려 해서)
		
		// > 배열 이용시 반복문을 쓰든 안쓰든 간에
		//   항상 그 배열의 인덱스 범위에 맞는 방으로 접근해야한다!
		
		// 참고)
		// 문자열의 길이를 벗어나서 인덱스에 접근할 경우
		// StringIndexOutOfBoundsException 발생(같은 맥락)
		
		// arr 배열의 모든 방에 들은 데이터의 총합을 구하기
		// int sum = arr[0] + arr[1] + ... + arr[4];
		/*
		 * int sum = 0;
		 * sum = sum + arr[0];
		 * sum = sum + arr[1];
		 * ...
		 * sum = sum + arr[4];
		 * 
		 */
		
		// 누적합을 구할 for문
		int sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			
			sum += arr[i];
			
		}
		
		System.out.println("sum: " + sum); // 15
		
		System.out.println(arr); // [I@19469ea2 (주소값)
		System.out.println(arr.hashCode()); // 424058530 (주소값을 십진수로)
		System.out.println(arr.length); // 5 (배열크기)
		
	} // 메소드 4 영역 끝
	
	public void method5() {
		
		// [0] ~[9] 까지 51 ~ 100 사이의 랜덤값을 넣어보기
		
		int[] arr = new int[10]; // [0] ~ [9], 0으로 기본값 세팅
		
		
		for(int i = 0; i < arr.length; i++) {
			
			arr[i] = (int)(Math.random()*50 + 51); // int(math~)(랜덤값개수)(시작수);
			
		}
		
		// 총 10개의 랜덤값이 배열에 잘 대입되어있나 출력
		// 출력용 for문
		for(int i = 0; i < arr.length; i++) {
			
			// "arr[x]: xx" 형식으로 출력
			System.out.printf("arr[%d]: %d \n", i, arr[i]);
			
		}
		
	} // 메소드 5 영역 끝
	
	public void method6() {
		
		int[] arr = new int[5]; // [0] ~ [4] (0으로 초기화)
		
		System.out.println(arr); // 주소값
		System.out.println(arr.hashCode()); // 주소값을 십진수로 (해시코드)
		
		/*
		 * arr[0] = 2;
		 * arr[1] = 4;
		 * arr[2] = 6;
		 * ...
		 * arr[4] = 10;
		 * 
		 */
		
		// > 인덱스 규칙: 시작수 0, 끝수 배열의 크기 -1, 1씩 증가
		// > 위의 대입되는 값 규칙: 시작수 2, 끝수 10 포함, 2씩 증가
		
		// 대입을 위한 for문
		// 반복문 규칙?을 보조해줄 변수 세팅
		int value = 2; // 방법2)
		
		for(int i = 0; i < arr.length; i++) {
			
			// 방법1) arr[i] = (i+1) * 2; ( 2 4 6 8 10 ..)
			// 방법2)
			arr[i] = value;
			value += 2;
		}
		
		// 출력을 위한 for문 작성
		for(int i = 0; i < arr.length; i++) {
			
			System.out.println(arr[i] + " ");
		}
		
		// arr 배열은 5칸짜리 배열임!
		// > 즉 [0] ~ [4]번 방까지 존재함!
		
		// arr[5] = 12;
		// arr[6] = 14;
		
		/*
		 * * 배열의 가장 큰 단점
		 * - 맨 처음 할당(new 자료형[크기]) 과정에서 지정한 배열 크기는
		 *   절대 변경이 불가능함
		 * > 배열의 크기를 변경하고자 한다면
		 *   새로운 배열을 다시 만들어서 쓰면 됨(무식하지만 이 방법뿐)
		 *   (즉, new 구문을 통해 새로운 배열을 할당하는 과정을 다시 거쳐야 함)
		 * 
		 */
		
		// 7칸짜리 배열로 다시 만들어보기
		arr = new int[7];
		
		System.out.println("--- arr 변경 후 ---");
		System.out.println(arr); // [I@2f2c9b19
		System.out.println(arr.hashCode()); // 791452441
		
		// > new 구문 (new연산자)
		//   메모리의 heap 영역에 공간을 확보해주는 역할
		//   (매번 임의의 다른 주소지에 공간 확보해줌)
		// > 배열을 새롭게 생성하면 매번 다른 주소값이 나올 수밖에 없다!
		
		/*
		 * * 메모리공간은 항상 섹션마다 고유한 주소값을 가지고 있다.
		 * - 즉, 주소값은 절대 중복되지 않는다.(겹치지 않음
		 * 
		 * * new 구문의 역할 자체가 메모리의 heap 영역의 특정 공간을 쓰기 위해 확보하겠다,
		 *   공간을 할당하겠다는 역할이기 때문에
		 *   배열명은 그대로 쓰되, new 구문만 다시 실행해서 그 결과(주소값)를 다시 대입하게 되면
		 *   new 구문 재실행 전 후로 서로 다른 주소값이 나올 수밖에 없음!!
		 *   
		 * * 기존에 연결되어있던 기존의 배열은
		 *   메모리의 heap 영역에 동동 떠다니다가(자리를 계속 차지하다가),
		 *   일정 시간이 지나면 가비지 컬렉터(GC, garbage collector)에 의해 자동으로 삭제된다!
		 *   > 자동 메모리 관리, 가비지 컬렉션
		 * 
		 */
		
		// 현재 연결되어있는 고리를 그냥 끊어보고자 한다면?
		arr = null;
		// > null: 주소값이 "없음"을 나타내는 값
		
		// 이 때 7칸짜리 배열 또한 heap 영역에 자리를 차지하다가 어느순간 삭제됨(가비지컬렉션)
		
		System.err.println(arr); // null 출력

		//arr[0] = 10;
		// > NullPointerException 오류 발생
		//   현재 arr이 가리키고 있는 주소가 아예 없는데(null)
		//   어떻게 접근할래?라고 오류로써 알려주는 것
		
		// System.out.println(arr[0]);
		// 마찬가지로 NullPointerException 오류 발생 
		
		// System.out.println(arr.hashCode());
		// 마찬가지로 NullPointerException 오류 발생
		
		// System.out.println(arr.length);
		// 마찬가지로 NullPointerException 오류 발생
		
		// 항상 주의해야할 점은
		// 참조자료형의 경우 항상 주소값이 제대로 들어있는지 확인해가면서
		// 코드를 작성하는 것이 가장 좋다!
		
		
	} // 메소드 6 영역 끝
	
	public void method7() {
		
		// 3명의 사용자에게 각 키의 정보를 입력받아 배열에 담아두고
		// 3명의 키 정보를 각각 출력하기
		// 또한 3명 키의 총 합계와 평균도 같이 출력
		
		// > 3명 키 정보를 담아둬야함: 배열 3칸 (double)
		
		Scanner sc = new Scanner(System.in);
		
		double[] heights = new double[3]; // [0] [1] [2]
		
		for(int i = 0; i < heights.length; i++) {
			
			System.out.print("키 입력(cm): ");
			heights[i] = sc.nextDouble();
			sc.nextLine();
			
		}
		
		double sum = 0.0;
		
		//출력 및 키의 총 합계를 구할 for문 한번에 작성
		for(int i = 0; i < heights.length; i++) {
			
			System.out.println(heights[i]);
			// 뿐만 아니라
			sum += heights[i];
			
		}
	
		//총합 및 평균 출력
		System.out.println("3명 키의 총합계: " + sum);
		System.out.printf("3명 키의 평균: %.1f \n", (sum / heights.length));
		
	} // 메소드 7 영역 끝
	
	public void method8() {
		
		// 사용자에게 문자열 1개 입력받고
		// 그 문자열의 각각 문자를 char 배열로 옮겨담기
		// char 배열에 잘 옮겨담아졌는지 출력
		
		// hint) 문자열의 문자들을 char 배열로 옮기기
		// 즉, 문자열의 길이만큼 char 배열의 크기를 할당해야함!
		
		Scanner sc = new Scanner(System.in);
		
		// 1. 사용자에게 문자열 먼저 입력받기
		System.out.print("문자열 하나 입력하세요: ");
		String str = sc.nextLine();
		
		// "apple" 문자열 입력받았다고 생각해보자
		//  01234
		// [0] [1] [2] [3] [4]
		// 'a' 'p' 'p' 'l' 'e'
		
		// 2. 문자를 쪼개서 넣어둘 char 배열 만들기
		// > 문자열의 길이(str.length()) == 배열의 크기(arr.length())
		char[] arr = new char[str.length()];
		
		// 3. 문자를 각 인덱스로 넣어보기
		for(int i = 0; i < str.length(); i++) {
			
			arr[i] = str.charAt(i);
			
		}
		
		// 4. char 배열 출력
		for(int i = 0; i < str.length(); i++) {
			
			System.out.println(arr[i]);
		}
	}
	
	public void method9() {
		
		// 사용자에게 좋아하는 과일 개수를 입력받아
		// 그 개수만큼 과일명을 입력받아 String 배열에 대입
		// 그 String 배열을 출력
		
		// hint) 입력받은 과일 개수만큼 배열 할당
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("과일 개수 입력: ");
		int size = sc.nextInt();
		sc.nextLine();
		
		// 내가 좋아하는 과일명들을 담을 String 배열 만들기(좋아하는 과일 개수만큼 들어가게)
		String[] fruits = new String[size];
		
		//for(int i = 0; i < fruits.length; i++)
		for(int i = 0; i < size; i++) {
			
			System.out.print("과일 명: ");
			fruits[i] = sc.nextLine();
			
		}
		
		// 출력용 for문
		for(int i = 0; i < size; i++) {
			
			System.out.println(fruits[i]);
		}
		
	} // 메소드 9 영역 끝
	
	public void method10() {
		
		int[] arr = new int[4];
		// [0] [1] [2] [3]
		//  0   0   0   0
		
		// 각 인덱스에 내가 원하는 값으로 초기화하고싶다면?
		// > 직접 대입할 수밖에 없음
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		
		
		// - 배열 선언 및 할당과 동시에 내가 원하는 값으로 초기화(대입) 가능
		//   여기까지 한번에 끝내주는 표현법이 있음
		// 방법1)
		int[] arr1 = new int[] {1, 2, 3, 4};
		
		//방법2)
		int[] arr2 = {1, 2, 3, 4}; // 사이에 new int[] 생략
		
		// arr, arr1, arr2 모두
		// [0] [1] [2] [3]
		//  1   2   3   4
		// 형태의 배열이 만들어짐!
		
		// 이 때, {1, 2, 3, 4}를 "초기화 블록"이라고 함
		
		// 이 시점 기준으롤 arr, arr1, arr2에는 다 같은 값들이 들어있음
		// (배열의 크기도 같고, 들어있는 내용물 또한 완전히 일치함)
		System.out.println(arr == arr1); // false
		System.out.println(arr == arr2); // false
		System.out.println(arr1 == arr2); // false
		
		/*
		 * * arr, arr1, arr2 크기, 내용물 같은데
		 * ==로 동등비교하면 모두 false가 나옴
		 * 
		 * 이유: 참조자료형 변수 간에 == 연산은 "주소값" 기준으로 동등비교 발생
		 * 		arr 할당시 new 구문 한 번,
		 * 		arr1 할당시 new 구문 한 번 더,
		 * 		arr2 할당시 new 구문 한 번 더 실행되면서
		 * 		각각 고유한(중복되지않은) 주소지에 배열이 만들어지기 때문
		 * 
		 * 참고)
		 * String 타입도 ==으로 비교하면 주소값 기준으로 비교됨!
		 * String 내용물 기준으로 하고싶다면
		 * 문자열.equals(비교할문자열); 일치하면 true 아니면 false
		 * 
		 */
		
	} // 메소드 10 영역 끝
	
	public void method11() {
		
		// 로또번호 자동발생 프로그램 만들기
		// [2, 4, 10, 15, 33, 40] 이런 형식으로 출력해보세요
		
		// - 1 ~ 45 사이 랜덤값 6개
		// >> 6개 저장해야하므로 6칸짜리 int 배열 만들기
		
		// - 중복되면 안됨
		// 1. 해당 i번째 회차의 랜덤수를 뽑아서 배열의 i번째 인덱스에 대입
		// 2. i번째 회차의 랜덤수를 0 ~ (i - 1)의 값과 일일이 대조해서
		//    중복이 있는지 모두 검사하면 됨
		// 3. 중복이 없다면 다음 반복회차로 넘어가면 됨(증감식으로 올라가자 continue;)
		// 4. 중복이 있다면 i번째 회차를 다시 진행하면됨
		
		// - 작은 값 --> 큰 값 순서
		// 정렬(sort) 개념: 값들을 일정한 기준에 따라 줄세워주는 개념
		// 작은 > 큰: 오름차순 정렬
		// [표현법]
		// Arrays.sort(배열명);
		
		// 1. 6개 랜덤한 정수 담아둘 배열 만들기
		int[] lotto = new int[6]; // [0] ~ [5]
		
		// 2. lotto 배열의 각 인덱스에 순차적으로 접근해서
		//    매번 다른 랜덤값을 담을 수 있게끔 반복문 돌리기
		for(int i = 0; i < lotto.length; i++) {
			
			// 3. i번째 인덱스에 랜덤수 일단 담아보기
			lotto[i] = (int)(Math.random()*45 + 1);
			
			// 4. i번째 랜덤수와 0 ~ (i-1) 포함까지의 직전 랜덤수들이 동일한지 중복 체크하기
			//    시작수: 0, 끝수: (i-1)
			for(int j = 0; j < i; j++) {
				
				if(lotto[i] == lotto[j]) {
					// 중복이 일어난 경우: i번째 로또번호를 다시 뽑아야됨
					
					i--;
					// 다음 바깥쪽 for문의 증감식에 의해 1 증가될 것을 감안하여
					// i번째 반복 회차를 다시 진행해야 하기 때문에 미리 1 빼준 것
					
					break;
					// 예)
					// lotto[1] == lotto[4]
					// 4번방 랜덤수를 다시 뽑아야됨
					// 단, 이미 1번 인덱스의 값과 4번 인덱스의 값이 중복되어서
					// 굳이 2번, 3번 인덱스의 값을 중복체크할 필요 없음
					// 따라서 위의 해당 중복체크 for문을 더 진행할 필요 없음	
				}	
			}	
		}
		// 이 시점 기준으로 잘 됐음
		// 출력
		//	Arrays.sort(lotto);
		//	System.out.printf("[%d, %d, %d, %d, %d, %d] \n", lotto[0], lotto[1], lotto[2], lotto[3], lotto[4], lotto[5]);
		
		System.out.println(Arrays.toString(lotto));
		// > Arrays.toString(배열명);
		//   배열의 데이터들을 0번 인덱스에서부터 순차적으로
		//   "[값, 값, 값, ...]" 형식의 문자열로 만들어주는 메소드
	}

}
