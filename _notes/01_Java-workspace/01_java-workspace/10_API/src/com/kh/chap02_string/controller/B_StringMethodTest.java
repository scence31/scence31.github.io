package com.kh.chap02_string.controller;

import java.util.Arrays;
import java.util.Scanner;

// 문자열과 관련된 유용한 메소드들
public class B_StringMethodTest {
	
	public void method1() {
		
		// 메소드명(매개변수): 반환형
		
		String str1 = "Hell World";
		
		// 1. 문자열.charAt(int index): char
		// > 문자열에서 전달받은 index 위치의 문자 하나만을 뽑아서 리턴
		char ch = str1.charAt(3); // 'l'
		System.out.println("ch: " + ch);
		
		// 2. 문자열.equls(String str): boolean
		// > 문자열과 전달받은 str의 내용물이 같은지 동등비교 후 결과 리턴
		System.out.println("equals: " + str1.equals("Hello World")); // false
		
		// 3. 문자열.length(): int
		// > 해당 문자열의 길이 반환(몇 개의 문자로 이루어져있는지 문자 개수 세기)
		System.out.println("str1의 길이: " + str1.length()); // 10 (공백 포함!!)
		
		// 4. 문자열.concat(String str): String
		// > 문자열과 전달된 str을 하나로 연이어서 리턴
		String str2 = str1.concat("!!!");
		System.out.println("str2: " + str2); // Hell World!!!
		
		// 5-1. 문자열.substring(int beginIndex): String
		// > 문자열의 beginIndex 위치에서부터 끝까지의 문자열을 추출해서 리턴
		System.out.println(str1.substring(6)); // orld
		
		// 5-2. 문자열.substring(int beginIndex, int endIndex): String - 오버로딩된 메솓
		// > 문자열의 beginIndex 위치부터 endIndex - 1까지 문자열 추출해서 리턴
		System.out.println(str1.substring(0, 6)); // Hell W (endIndex 포함 안함)
		
		// 6. 문자열.replace(char old, char new): String
		// > 문자열에서 old문자를 new문자로 변환한 문자열을 리턴(치환)
		String str3 = str1.replace('l', 'c');
		System.out.println("str3: " + str3); // Hecc Worcd ('l'이 'c'로 치환됨)
		
		// ex) 민감정보를 마스킹해보자
		// > 마스킹: 민감정보를 * 같은 것으로 가려서 출력
		String personId = "960316-1234567"; // 주민등록번호
		String cover = personId.substring(0, 8).concat("******");
		System.out.println(cover); // 960316-1******
		
		// 7. 문자열.trim(): String
		// > 문자열의 앞과 뒤 공백을 제거한 문자열을 리턴
		String str4 = "       JA   VA    ";
		System.out.println(str4.trim()); // JA   VA (중간은 공백 안없어짐)
		// 모든 공백 제거하고싶다면?
		System.out.println(str4.trim().replace(" ", "")); // JAVA
		// > replace 메소드 또한 오버로딩된 메소드 (char 뿐만 아니라 String도 됏음)
		
		// 8. 문자열.contains(String str): boolean
		// > 문자열로부터 전달받은 str이 포함되어있는지 검사 후 리턴
		System.out.println("contains: " + str4.contains("JA")); // true (대소문자 구별해야됨)
		// 문자열 리터럴은 대소문자 구분함
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 도서 제목: ");
		String searchTitle = sc.nextLine();
		*/
		
		// 이전 객체배열 단원에서는 검색어와 도서제목이 완전히 일치할 경우 (equals 메소드)
		// 해당하는 검색 결과를 출력했었음
		// > 원래 검색기능이라고 한다면 포함관계도 출력하는 것이 정석임
		
		// 9-1. 문자열.toUpperCase(): String
		// > 문자열을 모두 다 대문자로 변경 후 변경된 문자열 리턴
		System.out.println("upper: " + str1.toUpperCase()); // HELL WORLD
		
		// 9-2. 문자열.toLowerCase(): String
		// > 소문자로 변경 후 리턴
		System.out.println("upper: " + str1.toLowerCase()); // hell world
		
		// ex)
		/*
		 * Scanner sc = new Scanner(System.in);
		
		System.out.print("종료할래? (y/n): ");
		// char answer = sc.nextLine().charAt(0); // Y y N n
		// if(answer == 'Y' || answer == 'y') {}
		
		char answer = sc.nextLine().toUpperCase().charAt(0); // Y N
		// 메소드 체이닝: 메소드를 연이어 계속 호출하는 개념
		// 항상 반환형과 호출순서를 신경써야됨.
		if(answer == 'Y') {	}
		*/
		// 문자열 --> char[]
		/*
		char[] arr = new char[str1.length()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = str1.charAt(i);
		}
		*/
		
		// 10. 문자열.toCharArray(): char[]
		// > 문자열의 각 문자들을 char[] 배열에 옮겨담은 후 그 배열을 리턴
		
		char[] arr = str1.toCharArray();
		System.out.println(Arrays.toString(arr));
		
		// char[] --> 문자열
		char[] arr2 = {'a', 'p', 'p', 'l', 'e'};
		/*
		String result = "";
		for(int i = 0; i < arr2.length; i++) {
			
			result += arr2[i]; // sb.append(arr2[i]);
		}
		*/
		
		// 11. static valueOf(char[] data): String
		// 전달된 char[]에 담긴 문자들을 하나의 문자열로 연이어서 리턴
		String result = String.valueOf(arr2);
		System.out.println(arr2);
		
		
	}
	
	public void method2() {
		
		// 욕설 필터링 프로그램
		// 사용자로부터 채팅메시지를 입력받은 후
		// 욕설부분이 있다면 필터링해서 결과 출력하세요.
		
		// 욕설목록
		// "신발끈", 개나리, 수박씨, 호루라기, 시베리아, 십장생, 조카, 주옥, 쌍쌍바, 십자수
		
		// 입력: 이런 신발끝같은 개나리 호루라기야!!!
		// 결과: 이런 ***같은 *** ****야!!!
		
		String[] filter = {"신발끈", "개나리", "호루라기", "조카"};
		
		Scanner sc = new Scanner(System.in);
		System.out.print("메시지 입력: ");
		String chat = sc.nextLine();
		
		for(int i = 0; i < filter.length; i++) {
			
			// 해당 욕설의 길이 알아내기
			int size = filter[i].length();
			
			
			// 욕설의 길이만큼 * 찍기
			String str = "";
			
			for(int j = 0; j < size; j++) {
				
				str += "*";
			}
			
			chat = chat.replace(filter[i], str);
			
			
			
		}
		System.out.println(chat);
		
		
		
	}
	

}










