package com.kh.chap03_map.part02_properties.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

// Properties 사용 방법
public class PropertiesTest1 {
	
	/*
	 * * Properties
	 * - Map 계열: key + value 세트로 저장, 저장순서유지X... 똑같음
	 * 
	 * 단, key, value 모두 String으로 고정해서 쓰는 경향이 큼
	 * 애초에 객체생성시 제네릭 설정 못함. 다른 타입 못담는 것은 아님. 관례상 String 고정임
	 * 
	 * 애초에 key, value를 String으로 추가해주는 별도의 메소드 존재
	 * String 타입의 key를 제시하면 String 타입의 value로 리턴해주는 별도 메소드 존재
	 * 
	 * 근데 왜 굳이 String으로 고정해서 담음?
	 * Properties 객체는 내부적으로 Properties 객체에 담긴 데이터들을
	 * 파일입출력해주는 별도의 메소드가 존재하기 때문
	 * 
	 */

	public static void main(String[] args) {
		
		Properties prop = new Properties(); // 0.
		
		/*
		prop.put("초코파이", new Snack("초코맛", 300));
		prop.put("초코파이", new Snack("바나나맛", 400));
		prop.put(123, "123");
		
		System.out.println(prop);
		
		System.out.println(prop.get("초코파이")); // 1.
		System.out.println(prop.get("몽쉘")); // 2.
		*/
		
		prop.setProperty("List", "ArrayList"); // == prop.put(List~); 이것과 동일.
		prop.setProperty("Set", "HashSet"); // ~> 동일
		prop.setProperty("Map", "HashMap");
		prop.setProperty("Map", "Properties");
		
		System.out.println(prop);
		
		try {
		prop.store(new FileOutputStream("test.properties"), "Properties Test");
		
		prop.storeToXML(new FileOutputStream("test.xml"), "Properties Test");
		
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	// 0. Properties 객체 먼저 생성 - 표현법상 제네릭 설정 불가능
	// 마찬가지로 put 메소드를 통해 key + value 형태로 데이터 추가 가능
	// 1. get 메소드를 통해 key값 제시해서 value값만 알 수 있음.
	// 2. 없는 key값 get하면 null로 리턴
	
	// 3. setProperty(String key, String value): key+value 세트를 String 타입으로 추가하는 메소드
	// >> put 메소드의 String 버전으로 보면 됨
	
	// 4. store(OutputStream os, String comments): Properties 객체에 담긴 데이터들을 파일로 내보내기(출력)
	// 5. storeToXML(OutputStream os, String comments): Properties 데이터 xml 파일로 내보내기(출력)
	

}











