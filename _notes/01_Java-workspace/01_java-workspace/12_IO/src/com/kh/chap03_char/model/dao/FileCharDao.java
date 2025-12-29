package com.kh.chap03_char.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCharDao {
	
	// 1. 통로열기
	// 2. 볼일보기
	// 3. 통로닫기
	
	// [출력] 프로그램 --> 외부매체(파일): (파일로 데이터를 내보내겠다.)
	
	public void fileSave() {
		
		// FileWriter: 2바이트 단위 데이터를 파일로 출력하는 스트림
		// 0. 스트림변수 선언 및 null로 초기화 1. 통로열기 2. 볼일보기 3. 통로닫기
		
		FileWriter fw = null;
		
		try {
		
			
			fw = new FileWriter("b_char.txt" /*, false */);
			// 존재하지 앟는 출력통로 파일명 제시하면? 해당 파일 만들고나서 연결통로 만들어짐
			
			fw.write("와! IO 재밌다..ㅎ\n");
			//> 해당 문자열 통으로 넘어가는 개념이 아니라, 문자 하나하나씩 쪼개서 전송되는 개념
			fw.write('A');
			fw.write(' ');
			fw.write('\n');
			
			char[] cArr = {'k', 'i', 'w', 'i'};
			fw.write(cArr);
			// 다양하게 오버로딩된 형태 존재
			
		
		} catch(FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch(IOException e)  {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				fw.close();
				
			} catch(IOException e) {
				
				e.printStackTrace();
			}
			
		}
		System.out.println("프로그램 종료");
	}
	
	
	// [입력] 프로그램 <-- 외부매체(파일): 파일로부터 데이터를 가져오겠다. 읽어들이겠다)
	public void fileRead() {
		
		// FileReader
		// 파일로부터 데이터를 2바이트 단위로 입력받는 스트림
		// 1. 통로열기 2. 볼일보기 3. 통로닫기
		
		FileReader fr = null;
		
		try {
			
			fr = new FileReader("b_char.txt");
			
			// System.out.println(fr.read()); // 50752 --> 와~ 유니코드: 숫자로 가져와짐
			// System.out.println(fr.read()); // 33 -----> A
			
			// * 반복문 활용, 마찬가지로 더이상 읽을 수 없으면 -1 리턴
			while(true) {
				
				int value = fr.read();
				
				if(value == -1) {
					
					break;
				}
				System.out.println(value);
				
			}
			
			// 방법2)
			// int value = 0;
			// while((value = fr.read()) != -1) {
			//		sysout(value);
			//}
			
			
		} catch(FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				fr.close();
				
			} catch(IOException e) {
				
				e.printStackTrace();
			}
			
		}
		System.out.println("프로그램 종료");
		
	}
	
	/*
	 * * 스트림의 종류가 많다고 해서 다 익힐 필요는 없음.
	 * > 결국 모든 스트림 클래스의 부모는 동일하기 때문에,
	 *   코드 구조가 다 비슷비슷함
	 * 
	 * 
	 * 
	 * 
	 */
	


}






















