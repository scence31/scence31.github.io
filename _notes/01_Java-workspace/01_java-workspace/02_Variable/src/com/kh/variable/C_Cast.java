package com.kh.variable;

//형변환 개념
public class C_Cast { // C_Cast 클래스 영역 시작

	/*
	 * * 형변환
	 * - 값의 자료형을 바꾸는 개념
	 *   (byte를 int로, int를 double로, ...)
	 * 
	 * * 컴퓨터 상에서의 값 처리 규칙
	 * 1. 대입연산자(=)를 기준으로 왼쪽과 오른쪽은 항상 같은 자료형이어야만 함!!
	 * > 즉, 변수에는 같은 자료형에 해당하는 값만 대입 가능함
	 *   (다른 자료형의 값을 변수에 대입하고자 한다면 "형변환" 필수)
	 * [표현법]
	 * 자료형 변수명 = (바꿀자료형)값;
	 * 
	 * 2. 같은 자료형끼리만 연산이 가능함!!(int는 int끼리, double은 double끼리)
	 * > 즉, 다른 자료형끼리 연산을 하고싶다면 적어도 한놈은 다른쪽에 타입을 맞춰준 다음 연산해야함!!
	 *   ("형변환" 필수)
	 * [표현법]
	 * 값+(바꿀자료형)값;
	 * 
	 * * 형변환의 종류
	 * 1. 자동형변환
	 * - 컴퓨터에 의해 자동으로 형변환이 진행되는 경우
	 * - 개발자인 내가 직접 형변환 코드를 쓸 필요 없음!!
	 * > 작은 바이트의 자료형 값을 큰 바이트의 자료형 값으로 바꿀 때 대부분 발생
	 * 
	 * 2. 강제형변환(명시적형변환)
	 * - 반대
	 * - 개발자인 내가 직접 형변환 꼭 해야됨!!
	 * > 큰 바이트의 자료형 값을 작은 바이트의 자료형 값으로 바꿀 때 대부분 발생
	 * 
	 * [표현법]
	 * (바꿀자료형)값
	 * (바꿀자료형): 형변환 연산자(cast 연산자)
	 */
	
	// 자동형변환 개념
	// 작은바이트 --> 큰바이트
	public void autoCasting() { // autoCasting 메소드 영역 시작
		
		//1. int(4byte) --> double(8byte)
		int i1 = 10;
		double d1 = i1;
		// 원칙: double d1 = (double)i1;
		
		System.out.println("i1: " + i1); // 10
		System.out.println("d1: " + d1); // 10.0
		// > 자동으로 형변환이 되었다.
		//   10 --> 10.0
		
		int i2 = 12;
		double d2 = 3.3;
		
		double result2 = /*(double)*/i2+d2;
		// 원칙: double result2 = (double)i2+d2;
		
		System.out.println("i2: " + i2); //12
		System.out.println("d2: " + d2); //3.3
		System.out.println("result2: " + result2); // 15.3
		// > 자동으로 형변환이 되었다.
		//   12 --> 12.0
		//   12.0 + 3.3
		
		// 2. int(4byte) --> long(8byte)
		int i3 = 1000;
		long l3 = /*(long)*/i3;
		
		long l4 = /*(long)*/2000;
		// > L을 붙이지 않아도(2000L) 문제가 없는 이유
		//   내부적으로 자동형변환이 되기 때문!
		
		// 3.(특이케이스) long(8byte) --> float(4byte)
		long l5 = /*(long)*/1000000;
		float f5 = /*(float)*/l5;
		
		System.out.println("f5: " + f5);
		// > 자동형변환이 되었다.
		//   정수가 실수로 담길 경우에는 오히려 큰 사이즈의 정수가 작은 사이즈의 실수 변수에 대입될 수 있다.
		//   사실 4byte짜리 float는 8byte짜리 long형보다 사실상 표현 범위가 더 넓어서
		//   (32개의 0과 1로 표현할 수 있는 실수 범위가 long보다 float이 더 큼 - 부동소수점)
		
		//4.(특이케이스) char(2byte) <--> int(4byte)
		// > 쌍방향으로 형변환 가능!!
		
		char ch = /*(char)*/65;
		System.out.println("ch: " + ch); // 'A'
		
		int num = /*(int)*/'A';
		System.out.println("num: " + num); //65
		// > 자동형변환이 되었다.
		// char의 범위: 0 ~ 65xxx
		//            컴퓨터에서는 각 문자마다 고유의 정수값이 정해져 있다.
		//			  단, 음수값은 안됨!!(오류발생)
		
		// 참고)
		// 아스키코드표
		// 0 ~ 127까지의 총 128개의 정수값으로, 128개의 문자를 표현할 수 있는 표
		// 영문자(대소문자), 숫자, 특수문자를 1:1로 매칭시킨 표(국제표준)
		
		// 총 128개의 숫자만으로 전세계의 모든 문자를 표현하기에는 제한적임!!
		// > 그래서 이 아스키코드를 확장시켜서 나온 개념: "유니코드"
		
		// 유니코드표
		// 0 ~ 65535까지의 총 65536개의 정수값으로 65536개의 문자를 표현할 수 있는 표
		// 영문자(대소문자), 숫자, 특수문자 + a (한글, 한문, 일본어 등 다른 문자 포함)
		
		System.out.println('김');
		System.out.println((int)'김'); //44608
		
		// 연습문제: "HelloEveryone"을 대문자와 소문자를 서로 변경해서 출력해라
		// 대문자 --> 소문자: 대문자 + 32
		// 소문자 --> 대문자: 소문자 - 32
		
		int sum = /*(int)*/'A'+32;
		System.out.println("sum: " + sum); //97
		// 'A' --> 65
		// 65 + 32 = 97
		
		System.out.println("sum: " + (char)sum); // 'a'
		// > 문자와 정수 사이에 산술 연산 가능!!
		//   영문자 대소문자는 서로 32 차이남!!
		
		//5.(특이케이스) byte(1byte), short(2byte) 간의 연산
		byte b1 = 1;
		byte b2 = 10;
		
		byte result = (byte)(b1+b2);
		// 오류 byte result = b1+b2;
		// 오류 Type mismatch: cannot convert from int to byte
		// int형을 byte형으로 자동으로 변환할 수 없다는 뜻!
		// > byte끼리 연산시 무조건 결과는 int로 나오게 되어있음
		// 강제로 형변환을 하면 byte 변수에 결과 저장 가능
		// (short도 마찬가지)
		
	} // autoCasting 메소드 영역 끝
	
	// 강제형변환(명시적형변환) 개념
	// 큰 바이트 --> 작은 바이트
	public void forceCasting() { // forceCasting 메소드 영역 시작
		
		// 1. double(8byte) --> float(4byte)
		float f1 = 4.0f;
		// > 반드시 f를 붙여야 함!!(형변환 때문임)
		
		double d2 = 8.0;
		float f2 = (float)d2;
		
		// 2. double(8byte) --> int(4byte)
		// **중요**
		// 데이터의 손실이 발생하기 때문에 신중히 형변환 해야하는 케이스임!!
		double d3 = 10.89;
		int i3 = (int)d3; // 10.89 --> 10
		System.out.println("i3: " +i3); // 10
		// > 10 소수점 아래 내용이 절삭됨(즉, 데이터 손실)
		
		int iNum = 10;
		double dNum = 5.89;
		
		// int iSum = /*(double)*/iNum+dNum;
		// > iNum이 double형으로 자동형변환이 되고
		//   덧셈연산이 그 이후에 이루어짐(결과도 double)
		
		// 해결방법1) 구림
		// 최종연산결과를 int형에 맞춰서 강제형변환
		int iSum1 = (int)(/*(double)*/iNum+dNum);
		System.out.println("iStm1: " + iSum1);
		// 10 --> 10.0
		// 10.0 + 5.89 = 15.89
		// 15.89 --> 15
		
		// 해결방법2) 구림
		// 연산이 발생하기 전에 double을 int로 강제형변환
		int iSum2 = iNum+(int)dNum;
		System.out.println("iSum2: " + iSum2);
		// 5.89 --> 5
		// 5 + 10 = 15
		// > 덧셈연산 전에 애초에 dNum을 int형으로 강제형변환
		//   자동형변환은 iNum이 double로 변환되서 연산이 되는 것인데
		//   그 전에 dNUm을 내가 먼저 선수쳐서 강제로 변환시킨 것!!
		
		// 해결방법3) 좋음
		// 애초에 결과값을 double형 변수로 받기!(권장사항)
		double dSum = iNum+dNum;
		System.out.println("dSum: " + dSum);
		// 10 --> 10.0
		// 10.0 + 5.89 = 15.89
		
	} // forceCasting 메소드 영역 끝
	
	
} // C_Cast 클래스 영역 끝
