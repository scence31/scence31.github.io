package com.kh.chap02_bye.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// * 데이터접근객체 DAO(Data Access Object) 클래스
// 데이터가 보관되어있는 외부매체와 직접접근해서 데이터를 입출력하는 용도의 클래스
// 기능위주의 클래스인데 데이터 입출력용 기능만 정의되어있는 클래스
// 주로 model.dao 패키지 내에 정의한다.
public class FileByteDao {
	
	/*
	 * * 입출력 과정(3 steps)
	 * 1. 통로열기
	 * 2. 볼일보기 (입력이든 출력이든 간에)
	 * 3. 통로닫기
	 */
	
	// 출력: 프로그램(기준점) --> 외부매체(파일), 프로그램 내 데이터 파일로 내보내기(파일로 기록)
	public void fileSave() {
		
		// FileOutputStream 클래스 활용
		// > 파일로 데이터를 1바이트 단위로 출력해주는 스트림
		
		
		// 0. FileOutputStram 변수선언 및 null로 초기화
		FileOutputStream fout = null;
		
		
		// 1. 통로열기: 통로를 열겠다 == 스트림 객체 생성하겠다.
		
		try { // 1번
			
			fout = new FileOutputStream("a_byte.txt"); // 1번
			// 객체생성 시 연결하고자 하는 파일명을 제시함(문자열로)
			// 해당 파일이 실제로 존재하면 그냥 통로 연결됨(단 파일명만 제시하면 상대경로에 의해 곧바로 파일 생성됨)
			// 존재하지 않으면 그 파일을 먼저 생성하고 그 후에 통로가 연결됨
			
			fout.write('a'); // 2번 --> a 기록
			fout.write(98); // 2번 --> b 기록
			
			// fout.write('김'); --> 한글은 2바이트라서 깨져서 저장됨, 바이트스트림에서 제한
			byte[] bArr = {99, 100, 101};
			fout.write(bArr); //  cde 기록
			fout.write(bArr, 1, 2); // de 기록, 오버로딩된 형태, 1번 인덱스부터 2개만 기록하겟다.
			
		} catch(FileNotFoundException e) { // 1번
			
			e.printStackTrace();
			
		} catch(IOException e) { // 2번
			
			e.printStackTrace();
			
		} finally { // 3번
			
		
			try {
			fout.close();
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
		}	
		System.out.println("프로그램 종료");
	}
		// 2. 볼일보기: 1바이트(-128 ~ 127이지만, 아스키코드표로 인해 음수는 취급x -->0 ~ 127) 단위로 내보내는 작업
		// FileOutputStream 객체에서 제공하는 write() 메소드 사용
		// >> 통로열기 1번 영역 안으로
		
		// 3. 통로닫기: 다 쓴 스트림 객체 자원을 반납하겠다.
		// FileOutputStream 객체에서 제공하는 close() 메소드 이용
		// >> 통로열기 1번 영역 안으로
		// 통로 무조건 닫혀야함(finally 블록)
		// finally 블록: try 내부에서 예외가 발생하든 말든 마지막으로 무조건 실행할 구문이 있으면 써야함
		
		// 1번 fout = new FileOutputStream("a_byte.txt",true);
		// --> true 작성시 기존 파일이 있을경우 내용 이어짐, 미작성하면 내용 덮어씌어짐
		// false면 true 미작성과 같음
	
	public void fileRead() {
		
		// FileInputStream
		
		// 파일로부터 데이터를 1바이트 단위로 입력받는 스트팀
		// 0. FileinputStream 변수 선언 및 null로 초기화
		// 1. 통로열기: 스트림객체 생성
		// 2. 볼일보기: 해당 파일로부터 1바이트씩 읽어들이는 작업.
		//   > FileInputStream 객체에서 제공하는 read() 메소드 이용
		//    파일에 문자로 기록되어있어도 아스키코드로 인해 숫자로 읽어들임
		// 3. 통로닫기: 반드시 마지막에 일어나야해서 finally 볼록에서 작업. 다쓴 자원 반밥
		
		FileInputStream fin = null;
		
		try {
			
			fin = new FileInputStream("a_byte.txt");
			// 인풋스트림 객체생성시 존재하는 파일 a_byte.txt가 아닌 파일명 제시하면
			// FileNotFoundException 오류 발생
			
			
			
			/*
			System.out.println(fin.read()); // 97 a
			System.out.println(fin.read()); // 98 b
			System.out.println(fin.read()); // 99 c
			System.out.println(fin.read()); // 100 d
			System.out.println(fin.read()); // 101 e
			System.out.println(fin.read()); // 100 d
			System.out.println(fin.read()); // 101 e
			
			System.out.println(fin.read()); // -1
			System.out.println(fin.read()); // -1
			*/
			
			// > 더이상 읽어들일 데이터가 없다면 계속 -1 리턴
			// 해결방법 1)
			// * 반복문 활용하기
			// > 파일에서 더이상 읽어들일 문자가 없을 때까지만 반복 돌리기
			// 반복횟수가 명확하지 않으므로 while문 이용
			
			/*
			while(true) {
				
				int value = fin.read();
				
				if(value == -1) {
					
					break;
				}
				System.out.println(value);
				
			}
			*/
			
			// > 퐁당퐁당 출력됨
			//   반복 1회차당 read 메소드가 조건검사용 1회, 출력용 1회 총 2번 호출되어서
			
			// 해결방법2)
			// 조건식 내부에 대입구문 활용하기
			int value = 0;
			while((value = fin.read()) != -1) {
				
				System.out.print((char)value);
			}
			
			System.out.println(); // 줄바꿈
			
			
		} catch(FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				fin.close();
				
			} catch(IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		System.out.println("프로그램 종료");
		
		
		
	}

}
