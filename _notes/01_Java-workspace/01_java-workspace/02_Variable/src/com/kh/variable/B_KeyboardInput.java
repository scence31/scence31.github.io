package com.kh.variable;

import java.util.Scanner;


//사용자가 키보드로 입력한 값을 변수에 기록하는 방법
public class B_KeyboardInput { //B_KeyboardInput 클래스 영역 시작

	public void inputTest1() { // inputTest1 메소드 영역 시작 
		
		/*
		 * * Scanner
		 * - 사용자로부터 정수, 실수, 문자열을 입력받을 수 있게끔 고안된 "클래스"(대문자 시작)
		 * - 자바에서 기본적으로 제공되는 내장클래스(내가 만드는 것이 아님!!)
		 * - java.util 패키지 내부에 존재함
		 *   (풀클래스명: java.util.Scanner)
		 * - Scanner 클래스 내부에 각각 정수, 실수, 문자열 등을 입력받을 수 있는
		 *   메소드들이 모여있다!!(이 메소드들을 호출해서 사용하는 방법을 익히자!!)
		 */
		
		// Scanner 클래스 내에 정의된 키보드 값을 입력받는 메소드 호출하기
		// (메인메소드/일반메소드 끌어다 쓰는거랑 같음!!)
		// Scanner 클래스를 "대변할이름" 생성(new)하기
		// 단, 클래스명(System.in); 추가하기!!
		Scanner sc = new Scanner(System.in);
		// System.in의 뜻은 키보드로 입력받은 값을 바이트 단위로 읽어들이겠다는 의미임
		
		// 이제 대변할 이름으로 메소드 호출하면 됨
		// sc.메소드명();
		// Scanner 클래스 내부의 유용한 메소드들을 쓰임새에 맞게 호출해서 쓰면 됨!!
		
		// * 사용자의 인적사항(이름, 나이, 키)을 입력받아 출력하는 프로그램 연습해볼게
		// - 근데 항상 입력받기 전에는 사용자의 입력을 출력문으로 제대로 유도하자!!
		
		System.out.print("당신의 이름은 무엇입니까: ");
		// (예상답변)이름: "김가현", "고길동", "박말순" ...
		// > 문자열 String 타입임
		
		// - 사용자가 입력한 값을 문자열로 받아오는 메소드
		// next(), nextLine()
		
		//sc.next(); --> X
		// 호출 구만만 작성하면 입력만 받고 그냥 끝나게 되어버림!!
		
		// 변수 선언과 동시에 값을 입력받아서 바로 대입한다(ex. int age = 1;)
		// (변수에 입력받은 값을 보관해 뒀다가 나중에 출력하기 위해)
		
		//String name = sc.next();
		// 빈상자 만들고, 호출, 대입 3단계
		// >사용자가 엔터를 하기 전까지 대기상태임
		
		//System.out.println(name);
		// > next(): 사용자가 입력한 값 중에서 공백(" ") 이전까지만 입력을 받아줌
		// ex) "김 가현"을 입력한 경우 "김"만 입력됨
		
		//sc.nextLine();
		
		String name = sc.nextLine();
		// > 사용자가 엔터를 입력하기 전까지 대기상태
		
		//System.out.println(name);
		// > nextLine(): 사용자가 입력한 값 중 개행(줄바꿈, 엔터키)이 있을 경우
		//               공백과 무관하게 개행 이전까지 한줄 단위로 모두 입력을 받아준다.
		
		System.out.print("당신의 나이는 몇살입니까: ");
		
		//(예상답변)나이: 20, 40, ...
		// > 정수 int 타입
		
		// - 사용자가 입력한 값을 정수 int로 받아오는 메소드
		// nextInt()
		
		//sc.nextInt();
		
		int age = sc.nextInt();
		
		//System.out.println(age);
		
		System.out.print("당신의 키는 몇 cm입니까: ");
		
		//(예상답변)키: 154.3 ...
		// > 실수 double 타입
		
		// - 사용자가 입력한 값을 실수 double로 받아오는 메소드
		// nextDouble();
		
		//sc.nextDouble();
		
		double height = sc.nextDouble();
		
		//System.out.println(height);
		
		//name, age, height를 이용해서 하나의 문자열로 출력
		// "xxx님의 나이는 xx살이고 키는 xx.xxcm 입니다." 형식으로
		
		System.out.println(name + "님의 나이는 " + age + "살이고, 키는" + height + "cm 입니다.");
		
		// 위의 출력메소드를 printf로 변환
		System.out.printf("%s님의 나이는 %d살이고, 키는 %.1fcm 입니다.\n", name, age, height);
		
		
		// > Scanner 클래스에서 제공되는 메소드들은
		// 변수 선언과 동시에 값을 입력받아 곧바로 대입하는 구조로 많이 쓴다.
		
		
	} //inputTest1 메소드 영역 끝
	
	//키보드로 값을 입력받을 때 종종 발생되는 문제
	public void inputTest2() { //inputTest2 메소드 영역 시작
		
		//사용자 인적사항 (이름, 나이, 주소, 키) 입력받아 출력하는 프로그램
		
		// 우선 Scanner 클래스를 대변할 이름을 먼저 생성(new) 하기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름을 입력하세요: ");
		String name = sc.nextLine();
		// > 이 시점 기준으로 버퍼는 깨끗하게 비워짐
		
		System.out.print("나이를 입력하세요: ");
		int age = sc.nextInt();
		// > 이 시점 기준으로는 버퍼에 개행문자(\n) 남아있음!!
		
		//해결방법)
		// 이 시점에서 다음 코드로 흐름이 넘어가기 전에
		// 버퍼 공간을 깨끗하게 비우고 넘어갈 것!!
		// > 버퍼를 비워주는 메소드: nextLine(); -> 그냥 호출 구문만 작성
		sc.nextLine();
		// > 변수선언 및 대입 구문 없이 단독으로 호출하면 됨!!
		
		System.out.print("주소를 입력하세요: ");
		String address = sc.nextLine();
		// > 어? 이미 뭔가 입력하고 엔터키를 친건가..?
		// > 사용자가 키보드로 입력한 것으로 간주해서 이슈가 발생한 것!!
		// > 이 시점 기준으로는 버퍼는 깨끗하게 비워짐!!
		
		System.out.print("키를 입력하세요(cm): ");
		double height = sc.nextDouble();
		// > 이 시점 기준에서 버퍼에 개행문자(\n) 남아있음!!
		
		// > nextLine을 제외한 다른 메소드로 입력을 받을 경우에는
		//   입력 후 버퍼공간을 비워주지 않으니, 
		//   대명.nextLine(); 메소드를 바로 다음에 한번 호출해주자.
		
		//name, age, address, height 변수값을 모두 출력해보기
		System.out.println("입력받은 이름: " + name);
		System.out.println("입력받은 나이: " + age);
		System.out.println("입력받은 주소: " + address);
		System.out.println("입력받은 키(cm): " + height);
		
		
		
		
	}//inputTest2 메소드 영역 끝
	
	// 위의 String, int, double 이외에 타입 값을 입력하는 방법
	public void inputTest3() { // inputTest 메소드 영역 시작
		
		/*
		 * * Scanner 클래스에서 제공하는 값을 입력받는 메소드들(타입별)
		 * - 문자열을 입력받을 때: next(), nextLine()
		 * - 정수값을 입력받을 때: nextInt(), nextByte(), nextShort(), nextLong()
		 * - 실수값을 입력받을 때: nextDouble(), nextFloat()
		 * - 논리값을 입력받을 때: nextBoolean()
		 * 
		 * 그럼 문자값을 입력받을 때는? nextChar()??
		 */
		
		//사용자의 인적사항(이름, 성별, 나이, 키) 입력받아서 출력하는 프로그램
		
		// Scanner 클래스를 대변할이름 먼저 생성
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름을 입력하세요: ");
		String name = sc.nextLine();
		
		System.out.print("성별을 입력하세요(M/F): ");
		// char gender = sc.nextChar();
		//> nextChar 메소드는 존재하지 않음
		
		/*
		 * * 문자열의 Index 개념
		 * - Index 뜻: 색인, 번호, 순번..
		 * 
		 * ex) "apple"이라는 문자열의 인덱스
		 *      01234
		 * > 문자열에서 0부터 시작하는 각 글자 자릿수
		 * 
		 * "hello"라는 문자열에서 2번 인덱스의 문자는? - 'l'
		 * "Male"이라는 ~ 0번 ~? - 'M'
		 * "Female" ~ 0번 ~? - "F"
		 * 
		 * * 문자열로부터 n번째 인덱스의 글자만 추출하는 법
		 * [표현법]
		 * 문자열.charAt(뽑을위치값);
		 * 
		 * -주의할 점
		 * 만약 문자열의 글자수보다 더 범위가 큰 인덱스를 제시한다면?
		 * ex) "apple" 문자열의 10번째 인덱스 글자? 01234 X
		 *     "hi" 문자열의 2번째 인덱스 글자? 01 X
		 * > 오류발생: StringIndexOutOfBoundsException
		 */
		
		char gender = sc.nextLine().charAt(0);
		// > 우선 문자열로 입력받은 후 곧바로 0번째 글자만 추출한 것
		
		//System.out.println(gender);
		
		System.out.print("나이를 입력하세요: ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("키를 입력하세요(cm): ");
		double height = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("이름: " + name);
		System.out.println("성별: " + gender);
		System.out.println("나이: " + age);
		System.out.println("키: " + height +"cm");
		
		
		
		
		
	} // inputTest 메소드 영역 끝
	
	public void inputTest4() { //inputTest4 메소드 영역 시작
		
		// 사용자로부터 세글자 이상의 문자열을 입력받은 후
		// 그 문자열로부터 각각 첫번째, 두번째, 세번째 글자를 뽑아서 출력하기
		
		// 첫번째 글자: x
		// 두번째 글자: x
		// 세번째 글자: x
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("세글자 이상의 문자열을 입력하세요: ");
		String str = sc.nextLine(); // 문자열: "hello" 가정
		
		// 첫번째 글자: h
		// 두번째 글자: e
		// 세번째 글자: l
		
		char first = str.charAt(0); // 'h'
		char second = str.charAt(1); // 'e'
		char third = str.charAt(2); // 'l'
		
		System.out.println("첫번째 글자: " + first);
		System.out.println("두번째 글자: " + second);
		System.out.println("세번째 글자: " + third);
		
		
	}//inputTest4 메소드 영역 끝
	
	// 연습문제
		public void inputTest5() { // inputTest5 메소드 영역 시작
			
			// 두 정수를 키보드로 입력받아 
			// 각각 덧셈, 뺄셈, 곱셈, 나눗셈 결과를 출력하기
			
			// 첫번째 정수 : 10
			// 두번째 정수 : 2
			// ----------------
			// 덧셈결과 : 12
			// 뺄셈결과 : 8
			// 곱셈결과 : 20
			// 나눗셈결과 : 5
			
			Scanner sc = new Scanner(System.in);
			
			// 두 개의 정수 각각 입력받기
			System.out.print("첫번째 정수: ");
			int firstNum = sc.nextInt();
			sc.nextLine();
			
			System.out.print("두번째 정수: ");
			int secondNum = sc.nextInt();
			sc.nextLine();
			
			// 각 연산 결과를 출력
			System.out.println("----------");
			
			int result1= firstNum+secondNum;
			int result2= firstNum-secondNum;
			int result3= firstNum*secondNum;
			int result4= firstNum/secondNum;
			
			System.out.println("덧셈결과: " + result1);
			System.out.println("뻴셈결과: " + result2);
			System.out.println("곱셈결과: " + result3);
			System.out.println("나눗셈결과: " + result4);
			
		} // inputTest5 메소드 영역 끝
	
}//B_KeyboardInput 클래스 영역 끝
