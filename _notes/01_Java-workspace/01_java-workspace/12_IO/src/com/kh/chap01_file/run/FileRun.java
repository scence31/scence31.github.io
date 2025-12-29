package com.kh.chap01_file.run;

import java.io.File;
import java.io.IOException;

// 자바 구문을 통해 간다
public class FileRun {
	
	/*
	 * * File 클래스(java.io.File)
	 * - 자바 구문을 통해 파일 또는 폴더를 만들거나, 부가적인 기능을 제공하는 자바클래스
	 * - java.io 패키지 내에 존재함
	 * 
	 * > File 객체가 생성됐다고 해서 파일이 실제로 만들어진 것은 아님.
	 */
	
	/*
	 * * 절대경로와 상대경로
	 * - 절대경로: C 또는 D드라이브(루트 디렉토리)를 포함하여 모든 경로를 경유하여 전부 기입하는 방식
	 * - 상대경로: 루트 디렉토리로 시작하지 않는 방식, 현재 내가 작업중인 폴더를 기준점으로 경로 시작
	 */
	
	public static void main(String[] args)  {
		
		try {
			// * 파일 만들어보기
			// 1. 별도의 경로 지정을 하지 않고 파일명(test.txt)만 제시해서 생성			
			File file1 = new File("test.txt");
			file1.createNewFile();
			// > 현재 내가 작업중인 12_IO프로젝트 폴더 내에 바로 파일이 생성됨
			//   이클립스에서는 파일의 변동 내역을 알아서 반영 후 보여주지는 않는다
			//   그래서 직접 refresh 해서 직접 반영하여 확인해야함
		
			
			
			// 2. 경로지정을 한 파일명을(C:\\aaa\\test.txt) 제시해서 생성
			File file2 = new File("C:\\aaa\\test.txt");
			file2.createNewFile();
			// > Windows 컴퓨터 폴더의 포함관계: \ (\\로 표현 - 이스케이프 문자)
			// 슬래시(/)로도 가능함 리눅스 컴퓨터 방식
			// File file2 = new File("C:/aaa/test.txt");
			
			
			
			// 3. 폴더를 생성하고나서 그 안에 파일을 생성하는 방법
			// 1) 우선 폴더부터 만들기
			File bbbFolder = new File("C:/bbb"); // 절대경로방식
			bbbFolder.mkdir(); // mkdir == make directory 약자 (폴더를 만들겠다.)
			// 2) 만든 bbb 폴더에 파일 생성
			File file3 = new File("C:/bbb/test.txt");
			file3.createNewFile();
			
			
			
			
			// 4.폴더 생성하고나서 그 안에 파일 생성하기(별도 경로지정 없이) --> 내가 작업중인 프로젝트에 생김 1번처럼
			// 1) 폴더명만 제시해서 폴더 생성
			File folder = new File("test");
			folder.mkdir();
			// 폴더 또한 상대경로방식일 경우 현재 내가 작업중인 이 프로젝트 폴더 내에 폴더가 만들어진다
			
			// 2) 그 만들어진 폴더에 파일만들기
			File file = new File("test/person.txt"); // 상대경로
			file.createNewFile();
			
			
			
			
			
			// * File 클래스에서 제공하는 유용한 메소드들
			
			// folder.mkdir(); : 폴더를 만들어주는 메소드
			// file.createNewFiler(); : 파일을 만들어주는 메소드
			
			System.out.println(folder.isFile()); // False 폴더이므로
			System.out.println(file.isFile()); // True 파일이므로
			// isFile() : 파일인지 검사 후 true or false
			
			System.out.println("파일명: " + file.getName()); // persom.txt
			// > file 객체가 가리키는 파일: C:/01_Java-workspace/12_IO/test/person.txt
			// getName() : 실제 파일명만 문자열로 추출해서 리턴
			
			System.out.println("상위폴더: " + file.getParent()); // test
			// > persom.txt 파일은 test 폴더로 감싸져있음(부모폴더)
			// getParent() : 실제 파일을 감싸고 있는 부모 폴더명을 문자열로 리턴
			
			System.out.println("절대경로: " + file.getAbsolutePath());
			// getAbsolutePath() : 해당 파일의 절대경로를 문자열로 리턴
			
			System.out.println("파일용량: " + file.length()); // 0 (byte 단위)
			
			file.delete(); // 파일삭제
			
			
			
		} catch(IOException e) {
		
			e.printStackTrace();
			// 반드시 존재하는 폴더 경로를 제시해야함
			// 즉, C:\\aaa 폴더가 이미 존재해야 파일이 제대로 생성됨

		}
		System.out.println("프로그램 종료");

	}
	

}

/*
 * * IO(Input Output, 입출력)
 * - 프로그램 상의 데이터를 외부매체로 내보내거나, 외부매체로부터 읽어들이는 과정
 * - 항상 기준점은 내가 작성하는 이 프로그램!!! 명심!
 * 
 * - 프로그램 기준으로
 * > 내보내는 방향: 출력 Output
 * > 읽는 방향: 입력 Input
 * 
 * * 프로그램 상의 데이터를 외부매체로(모니터, 스피커, 파일 등) 출력하거나 입력장치로부터(키보드, 마우스, 파일 등) 
 *   입력받는 과정을 거치려면반드시 프로그램/외부매체 사이 연결통로를(Stream) 만들어야함
 * 
 * * 스트림 특징
 * 1. 단방향성(일방통행): 입력이면 입력, 출력이면~, 즉 스트림이 따로 존재함, 입출력 동시에 하려면 스트림 두 개 필요함.
 * 2. 선입선출(FIFO: 1st in, out): 먼저 통로에 진입한 값이 먼저 나감
 * 3. 시간지연 문제가 발생할 수 있음(delay)
 * 참고) 선입후출(FILO): 값이 나중에 나감(스트림과는 상관없음)
 * 
 * * 스트림의 종류 구분
 * > 자바에서는 이 스트림들을 클래스로 정의함.
 *   이 스트림 클래스를 통해 객체를 생성한다 == 통로가 열렸다, 만들어졌다.
 *   
 * 1. 통로의 사이즈(통로의 너비)
 * - 바이트스트림: 1byte짜리가 이동할 수 있을 정도의 사이즈(좁은통로)
 * 				입력(XXXInputStream) / 출력(XXXOutputStream)
 * - 문자스트림: 2byte짜리~ (넓은통로)
 * 				입력(XXXReader) / 출력(XXXWriter)
 * 
 * 2. 외부매체와의 직접적인 연결 여부
 * - 기반스트림: 외부매체와 직접적으로 연결되는 메인통로
 * - 보조스트림: 직접연결 X, 기반스트림 만으로 부족한 성능을 향상시켜주는 용도의 스트림(기반스트림을 보조해주는 역할)
 * 			  절대 단독으로 사용 불가능(메인통로로 못씀) 반드시 기반스트림과 결합해야함
 * 			  ex) 속도 향샹, 객체단위로 입출력 도와줌, 자료형에 데이터를 맞춰 변환 등등..
 * 
 */






















