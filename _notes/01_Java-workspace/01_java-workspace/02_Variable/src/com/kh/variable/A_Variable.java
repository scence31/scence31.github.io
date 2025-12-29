package com.kh.variable;

//풀클래스명: com.kh.variable.A_Variable
public class A_Variable { // A_VAriable 클래스 영역 시작

	// 변수를 사용하는 이유
	// 사각형의 면적과 둘레를 계산해서 출력하는 예제
	
	public void printSquare() { // printSquare 메소드 영역 시작
		
		
		// 사각형 면적 = 가로길이 x 세로길이
		// 사각형 둘레 = (가로길이 + 세로길이) x 2
		
		// 가로길이 6cm, 세로길이 7cm 가정 하에 계산해보자
		// 자바 곱셈 연산 = *
		// 자바에서 여러 구문을 하나로 연잇고싶다면 + 기호 사용할 수 있음
		
		//변수 사용 전
		/*
		System.out.println("사각형의 면적 = " + 6*7); =42
		System.out.println("사각형의 둘레 = " + (6+7)*2); =26
		*/
		
		//변수 사용 후
		// - 가로길이, 세로길이를 각각 담을 수 있는 변수 만들기
		int width = 6; // 가로길이
		int height = 7; // 세로길이

		System.out.println("사각형의 면적 = " + width*height);
		System.out.println("사각형의 둘레 = " + (width+height)*2);
		
		/*
		 * *변수를 사용하는 이유
		 * 1. 값에 의미를 부여할 목적으로 사용(가독성 좋음)
		 * 2. 단 한 번만 값을 기록하고 필요할 때마다 꺼내서 사용(코드 재사용성 높음, 수정 용이)
		 * 3. 유지보수 쉽게할 수 있음
		 *
		 */
		
	} // printSquare 메소드 영역 끝
	
	// 변수의 선언 및 자료형 개념
	public void declareVariable() { // declareVariable 메소드 영역 시작
		
		/*
		 * * 변수의 선언
		 * - 메모리 공간 상에 값을 기록하려면 변수를 먼저 만들어야 한다.
		 * - 값을 기록해두기 위한 변수를 메모리 공간 상에 확보해 두겠다.
		 * - 즉, 값을 담아둘 상자(박스)를 만들겠다.
		 * 
		 * [표현법]
		 * 자료형 변수명;
		 * - 자료형: 변수(상자)의 크기 및 모양을 지정하는 부분
		 * - 변수명: 변수(상자)의 이름을 정하는 부분(의미부여)
		 * 
		 * *변수 선언시 주의사항
		 * 1. 변수명은 소문자로 시작(단 낙타표기법 지키기)
		 * 2. 같은 영역(중괄호 안쪽) 안에서는 동일한 변수명으로 선언할 수 없음(이름 중복 불가)
		 * 3. 해당 영역(중괄호 안쪽) 내에 선언된 변수는 그 영역 안에서만 사용 가능
		 *    > "지역변수" 개념
		 */
		
		// --- 자료형에 대한 개념 ---
		// 1. 논리형
		// 논리값: 어떤 질문에 대해 예(true) 또는 아니오(false)로 대답이 나뉘는 값
		boolean isTrue; // 1byte짜리 빈 상자가 만들어짐
		
		// 2. 숫자형
		// 2_1. 정수형
		// 정수값: 소수점이 없는 숫자값
		byte bNum; // 1byte짜리 빈 상자가 만들어짐(-128 ~ 127)
		short sNum; // 2byte짜리 빈 상자가 만들어짐(-32xxx ~ 32xxx)
		int iNum; // 4byte짜리 빈 상자가 만들어짐(-21억xxx ~ 21억xxx)
		long lNum; // 8byte짜리 빈 상자가 만들어짐(제일 큰 범위)
		// > 정수 자료형은 int가 기본
		
		// 2_2. 실수형
		// 실수값: 소수점이 있는 숫자값
		float fNum; // 4byte짜리 빈 상자가 만들어짐(소수점 아래 7자리까지 표현 가능)
		double dNum; // 8byte짜리 빈 상자가 만들어짐(소수점 아래 15자리까지 표현 가능)
		// > 실수 자료형은 double이 기본
		
		// 3. 문자형
		// 문자값: 글자 단 한 개만을 나타내는 값(홑따옴표로 감싸서 표현)
		char ch; // 2byte짜리 빈 상자가 만들어짐
		
		// ---- 여기까지 총 8개의 자료형(기본자료형, primitive type) ----
		// 기본자료형: 변수 상자 안에 값이 그대로 들어가는 타입
		
		// 4. 문자열형
		// 문자열값: 글자들이 나열되어있는 형태의 값(쌍따옴표로 감싸서 표현)
		String str; // 4byte짜리 빈 상자가 만들어짐
		
		// ---- 문자열(참조자료형, reference type) ----
		// 참조자료형: 변수 상자 안에 값이 그대로 못 들어가는 타입
		
		// --- 여기까지 총 9개의 빈 상자가 메모리 상에 생성됨 ---
		
		/*
		 * * 변수에 값을 대입
		 * - 변수에 값을 담겠다(기록하겠다/저장하겠다)
		 * 
		 * [표현법]
		 * 변수명 = 값;
		 * "="은 값을 대입하는 용도임(대입연산자)
		 * 기존 "=" 의미의 기호는 "=="
		 * 
		 */
		
		isTrue = true;
		
		bNum = 1;
		sNum = 2;
		iNum = 4;
		lNum = 8L;
		// > long임을 알려주기 위해 소문자 또는 대문자(권장) L을 표기해 주는 편
		
		fNum = 4.0f;
		// > float임을 알려주기 위해 f를 붙여서 씀(필수)
		dNum = 8.0;
		
		ch = 'A';
		// > 반드시 ''(홑따옴표) 안에 기술
		
		str = "ABC";
		// > 반드시 "" (쌍따옴표) 안에 기술
		// 참조자료형은 실제 데이터가 변수 상자에 곧바로 들어가지 않음!!
		
		// 각 변수에 담긴 값들 출력해보기
		System.out.println(isTrue);
		System.out.println(bNum);
		System.out.println(sNum);
		System.out.println(iNum);
		System.out.println(lNum);
		System.out.println(fNum);
		System.out.println(dNum);
		System.out.println(ch);
		System.out.println(str);
		
		
		
		
		
	} // declareVariable 메소드 영역 끝
	
	// 변수 선언과 동시에 값 대입
	public void initVariable() { // initVariable 메소드 영역 시작
		
		/*
		 * * 변순 선언과 동시에 값 대입
		 * - 변수에 처음으로 값을 대입하는 행위를 초기화라고 한다.
		 * 
		 * [표현법]
		 * 자료형 변수명 = 값;
		 */
		
		// 1. 논리형
		boolean isTrue = 3 > 5;
		// 3이 5보다 큽니까? false가 대입된 꼴
		
		// 2. 숫자형
		// 2_1. 정수형
		byte bNum = 1;
		short sNum = 2;
		int iNum = 4;
		long lNum = 8L;
		
		//2_2. 실수형
		float fNum = 4.0f;
		double dNum = 8.0;
		
		// 3. 문자형
		char ch = '김';
		
		// 4. 문자열형(참조자료형)
		String str = "자바 공부중";
		
		// 각 변수에 담긴 값들 출력해보기
		
		System.out.println(isTrue);
		System.out.println(bNum);
		System.out.println(sNum);
		System.out.println(iNum);
		System.out.println(lNum);
		System.out.println(fNum);
		System.out.println(dNum);
		System.out.println(ch);
		System.out.println(str);
		
		
		
		
	} //initVariable 메소드 영역 끝
	
	// 상수 개념
	public void constant() { // constant 메소드 영역 시작
		
		/*
		 * * 상수
		 * - 변하지 않는 고정적인 값을 담을 수 있는 공간
		 * 
		 * [표현법]
		 * - 선언과 초기화 따로작업
		 * final 자료형 상수명; // 빈 상자를 만드는 과정(선언 과정)
		 * 상수명 = 값; // 값 초기화 후 절대 못바꿈(초기화 과정)
		 * 
		 * - 선언과 초기화 동시작업
		 * final 자료형 상수명 = 값; // 마찬가지로 값 절대 못바꿈!!
		 * 
		 * > 상수명은 관례상 모두 대문자로 기술한다.
		 */
		
		// 변수의 경우 - 값 변화 가능
		int age = 20;
		System.out.println("age: " + age);
		
		age = 21;
		System.out.println("변경된 age: " + age);
		
		//상수의 경우- 값 변화 불가능
		final int AGE = 20;
		System.out.println("AGE: " + AGE);
		
		// AGE = 21;
		// > 나이와 같이 자주 변경되는 값들은 상수로 지정하면 불편함!!
		
		
	}// constant 메소드 영역 끝
	
	// 데이터 오버플로우 현상
	public void overflow() { // overflow 메소드 영역 시작
		
		byte bNum = 127;
		System.out.println("before: " + bNum);
		
		bNum++;
		// > 기존 bNum 변수값에 1을 증가시킨 후
		//   그 증가된 결과값을 다시 bNum 변수에 대입하겠다.
		
		System.out.println("before: " + bNum); // 129가 아닌 -128
		// > 128이 아닌 -128이 출력된다!
		
		bNum--;
		// > 기존 bNum 변수값에 1을 감소시킨 후
		//   그 감소시킨 결과값을 다시 bNum 변수에 대입하겠다.
		
		System.out.println("final: " + bNum); // -129가 아닌 127
		
		//변수에 계산된 값을 저장할 경우
		//터무니 없는 값이 저장될 경우 overflow 현상이 발생한 것임
		
		
		
	} // overflow 메소드 영역 끝
	
} // A_VAriable 클래스 영역 끝
