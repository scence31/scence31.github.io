package com.kh.chap04_assist.part01_buffered.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// BufferedReader / BufferedWriter
public class BufferedDao {
	
	// [출력] 프로그램(기준점) --> 외부매체(파일)
	
	public void fileSave() {
				
		/*
		BufferedWriter bw = null;
		
		try {
		
			bw = new BufferedWriter(new FileWriter("c_buffer.txt"));
		
			bw.write("안녕하세요");
			bw.newLine(); // 개행
			bw.write("반갑습니다\n");
			bw.write("저리가세요");
		
		} catch(IOException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
			bw.close();
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
			
	}
	*/
		
		// * try ~ with ~ resource문
		/*
		 * [표현법]
		 * try(스트림객체생성구문; 스트림객체생성구문; ...스트림객체생성구문) {
		 * 
		 * 		예외가 발생할 법한 구문;
		 * 
		 * } catch(예외클래스명 변수명) {
		 * 
		 * 		예외가 발생했을 때 실행할 코드;
		 * }
		 * 
		 * > 스트림 객체를 try(여기)에서 생성하면, 스트림 객체생성 후 try 블록 구문 완료되면
		 *   알아서 자원 반납됨(finally 블록 생략)
		 */
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("c_buffer.txt"))) {
			
			bw.write("안녕하세요");
			bw.newLine();
			bw.write("반갑습니다\n");
			bw.write("저리가세요.");
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		// 흐름 끝나면 bw객체가 알아서 자원이 반납됨(close)
		
		System.out.println("프로그램 종료");
}
	
	// FileWriter(기반) + BufferedWriter(보조)
	
	// 1. 통로열기
	// 1-1. 기반스트림 객체 먼저 생성(메인통로 먼저 깔기)
	// 1-2. 보조스트림 객체 생성(이미 만들어진 기반스트림 객체를 넘기면서 생성)
	
	// 2. 볼일보기
	// > 보조스트림 객체에서 제공하는 메소드로 볼일을 봐야 함
	// 보조스트림객체명.메소드명();
	// > BufferedWriter 객체에서 제공하는 write() 메소드 이용
	// ★ Read 할 때는 catch 두번(익셉션 두번), sysout, while문
	
	// 3. 통로닫기(finally) == 다 쓴 자원을 반납하겠다
	// 우리가 만든 자원: FileWriter 객체, BufferedWriter 객체
	// > 위에서 만든 객체들 모두 close 해주기(단, 생성된 수순의 역순으로!! 보조->기반)
	
	// 버퍼라는 공간에 글자 하나하나가 쌓여있다가 한꺼번에 파일로 내보내서 속도가 빨라짐
	
	// 코드 줄이기
	// FileWriter fw = null;
	// fw = new FileWriter("c_buffer.txt")
	// fw.close():
	
	
	public void fileRead() {
		
		// FileReader + BufferedReader
		
		BufferedReader br = null;
		
		try {
			
			br = new BufferedReader(new FileReader("c_buffer.txt"));
			
			// System.out.println(br.readLine());
			while(true) {
				String value = br.readLine();
				if(value == null) {
					
					break;
				}
				System.out.println(value);
			}
			
			// 또는
			// String value = null;
			// while((value = br.readLine()) != null) {
			//		sysout(value);
			// }
			
		} catch(FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		}	finally {
		
			
			try {
				
				br.close();
				
			} catch(IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		System.out.println("프로그램 종료");
		
		
	}
	
	

}
