package com.kh.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.kh.view.MemberView;

public class Run {
	
	/*
	 * * Properties 복습
	 * 
	 * - Map 계열의 컬렉션
	 * - key + value 세트로 담는 특징
	 * - 저장순서 유지 X(인덱스 개념 없음)
	 * - value는 중복 허용되지만 key는 중복 불가능
	 * 
	 * * 쓰는 이유
	 * 외부 설정파일 읽어오기 또는 데이터를 파일형태로 내보내는(출력하는) 기능이 부각적으로 제공되기 때문임
	 * > 아무 타입 다 담을 수 있지만
	 * 주로 파일 입출력을 원활하게 하기 위해 모두 String 타입을 씀(key, value)
	 * 
	 */

	public static void main(String[] args) {

		 new MemberView().mainMenu();
		// == MemberView mv = new MemberView()
		// 	  mv.mainMenu();
		// 프로퍼티스때매 잠깐 둠 그냥(DAO)
		

		
		
		
		// 1. properties, xml 파일로 내보내기(출력, output)
		
		Properties prop = new Properties();
		/*
		prop.setProperty("List", "ArrayList");
		prop.setProperty("Set", "HashSet");
		prop.setProperty("Map", "HashMap");
		prop.setProperty("Map", "Properties"); // key값은 중복되서 출력 안되고, 중복되면 마지막 key의 세트 출력 
		
		// System.out.println(prop);
		
		// 현재 내가 작업중인 이 프로젝트 폴더 어딘가에 prop에 담긴 데이터를 파일로 내보내기!
		// 이 프로젝트 폴더 내부에 resources라는 폴더에 파일을 저장하고 싶음.
		// resources 폴더: 내가 만든 프로그램이 구동시 필요로하는 외부 자원파일들을 보관하는 용도로 쓰이는 폴더
		
		try {
			// .properties 형식의 파일로 내보내기
			prop.store(new FileOutputStream("resources/driver.properties"), "test");
			
			// .xml 형식의 파일로 내보내기
			prop.storeToXML(new FileOutputStream("resources/query.xml"), "test");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		
		
		
		
		
		// 2. properties, xml 파일 읽어들이기(입력, input)
		
		
		try {
			// .properties 파일로부터 읽어보기
			// prop.load(new FileInputStream("resources/driver.properties"));
			
			// .xml 파일로부터 읽어보기
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(prop.getProperty("Set"));
		System.out.println(prop.getProperty("List"));
		System.out.println(prop.getProperty("Map"));
		
		// System.out.println(prop);
		
		// System.out.println(prop);
//		System.out.println(prop.getProperty("driver"));
//		System.out.println(prop.getProperty("url"));
//		System.out.println(prop.getProperty("username"));
//		System.out.println(prop.getProperty("password"));
//		
//		System.out.println(prop.getProperty("userName")); // 없는 키값 == null
		
	}

}

























