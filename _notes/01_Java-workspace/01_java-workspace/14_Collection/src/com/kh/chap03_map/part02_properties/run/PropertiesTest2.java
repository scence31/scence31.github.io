package com.kh.chap03_map.part02_properties.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest2 {

	public static void main(String[] args) {
		
		Properties prop = new Properties(); // 비어있는 상태
		
		System.out.println(prop); // {}
		
		// .properties 형태 파일로부터 키+밸류 형태로 읽어들여 Properties 객체에 차곡 담아주는 메소드(입력)
		// > load(InputStream is)
		
		try {
			//prop.load(new FileInputStream("test.properties"));
			
			// .xml 형태로 똑같이 읽어들이는..
			// loadFromXML(InputStream is)
			prop.loadFromXML(new FileInputStream("test.xml"));
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		//System.out.println(prop);
		
		// getProperty(String key): String value
		// : Properties 객체에 저장된 데이터 중 전달된 String key에 해당하는
		// String value 값을 찾아서 리턴해주는 메소드
		System.out.println(prop.getProperty("List"));
		System.out.println(prop.getProperty("Set"));
		System.out.println(prop.getProperty("Map"));
		System.out.println(prop.getProperty("Collection")); // null(없는 key값)
	}
	
	/*
	 * * .properties 파일
	 * - 해당 프로그램이 기본적으로 가져야하는 정보들을(환경설정과 관련된 값들) 저장. 문자열
	 * - 환경설정과 관련된 값을 .properties 파일로 빼 두면 프로그램 관리자 입장에서 다루기 편함
	 * 
	 * 
	 * * .xml 파일
	 * - 방대한 데이터를 구조화된 형식으로(<태그>로 감싼 형식) 저장하기 위한 파일
	 * - 다양한 프로그래밍 언어 간 호환 쉬움
	 * - .properties 파일과의 차이점: pro는 한 줄 밸류값만 표현 가능(xml은 여러줄 가능)
	 * 
	 * 둘 다 Spring Framework, MyBatis Framework, 기타 프레임워크 환경설정 용도로 많이 씀
	 * xml은 Spring Legacy(구), properties는 Spring Boot(신)에서 주로 사용
	 * 
	 */

}







