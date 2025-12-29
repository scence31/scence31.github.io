package com.kh.chpa01_math.run;

/*
 * * 라이브러리(Library)
 * - 개발에 자주 사용되는 클래스 및 인터페이스 묶음(코드 묶음)
 * - 배포시 class 파일 형식으로 배포됨(컴파일 된 상태로 코드가 노출되지 않게)
 * 
 * * API(개발자 문서, Application Programming Interface)
 * - 개발에 자주 사용되는 클래스 및 인터페이스 묶음의 사용법을 나타낸 문서(라이브러리 코드 사용법)
 * 
 * * 자바에서 기본적으로 제공하는 API(기본 API, 내장 API)
 * 0. Scanner 클래스
 * 1. Math 클래스
 * 2. String 클래스
 * 3. Wrapper 클래스
 * 4. Date 클래스
 * ...
 * 
 * * 자바 기본 API(문서)
 * - 웹사이트 형식으로 제공해주고 있음 
 */

// 자바의 모든 클래스에는 import java.lang*; --> 생략되어있음(항상 이미 import 되어있음)

public class MathRun {

	public static void main(String[] args) {
		
		/*
		 * * Math 클래스(java.lang.Math)
		 * - 수학과 관련된 필드, 메소드들이 정의되어있는 클래스
		 * - static 형식임(싱글톤 패턴) --> 객체생성 필요 없음 ==>(클래스명.메소드명() or 클래스명.필드명())
		 * - java.lang 패키지 내에 존재함
		 */
		
		// 필드
		// 1. 파이: 원주율, 상수필드로 정의됨
		System.out.println("파이: " + Math.PI);
		
		// 메소드
		// Math m = new Math(); --> import 오류 안뜸(import java.lang)
		// > 모든 메소드들이 static 메소드기 때문에 애초에 객체생성 자체를 막아둔 것(private)
		
		// 메소드명(매개변수): 반환형
		double num1 = 4.349;
		// 1. Math.ceil(double): double [올림]
		System.out.println("올림: " + Math.ceil(num1)); // 5.0
		
		// 2. Math.round(double): long [반올림]
		System.out.println("반올림: " + Math.round(num1)); // 4
		
		// 3. Math.floor(double): double [버림]
		System.out.println("버림: " + Math.floor(num1)); // 4.0
		System.out.println("버림: " + (int)num1); // 4 (인트 형변환)
		
		// 4. Math.abs(int/double/long/float): int/double/long/float [절대값] - 오버로딩된 메소드
		System.out.println("절대값: " + Math.abs(num1)); // 4.349
		
		// 5. Math.min(int, int): int(double 하면 double) [최솟값] - 오버로딩 된 메소드
		System.out.println("최솟값: " + Math.min(5, 10)); // 5 (최댓값은 max로 하면 됨)
		
		// 6. .sqrt(double): double [제곱근(루트)]
		System.out.println("4의 제곱근: " + Math.sqrt(4)); // 2.0
		
		// 7. .pow(double, double): double [제곱]
		System.out.println("2의 10제곱: " + Math.pow(2, 10)); // 1024.0
		
		// 8. .random(): double [랜덤값] 0.0 ~ 0.99999.. 사이
		System.out.println("랜덤수: " + Math.random());
		
	}

}




