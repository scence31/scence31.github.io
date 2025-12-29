package com.kh.chap04_assist.part02_object.model.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.chap04_assist.part02_object.model.vo.Phone;

public class ObjectDao {
	
	// [출력] 프로그램(기준점) --> 외부매체(파일)
	
	public void fileSave() {		
		// FileOutputStream + ObjectOutputStream
		// 테스트용 객체 생성
		// 0. 스트림변수 선언 및 null 초기화 1. 통로열기 2. 볼일보기 3. 통로닫기
		
		Phone ph = new Phone("아이폰13", 1300000);
		
		ObjectOutputStream oos = null;
		
		   try {
			
			oos = new ObjectOutputStream(new FileOutputStream("phone.txt"));
			
			oos.writeObject(ph); // UpCasting(다형성) 자동형변환
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				oos.close();
				
			} catch(IOException e) {
				
				e.printStackTrace();
			}			
			
		}
				System.out.println("프로그램 종료");
	}

	public void fileRead() {
	 
		try(ObjectInputStream ois 
				= new ObjectInputStream(new FileInputStream("phone.txt"))) {
			
			Phone p = (Phone)ois.readObject();
			
			System.out.println(p.toString());
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		} catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		}
			System.out.println("프로그램 종료");
	}

	// NotSerializableException 오류 발생
	// phone.txt 파일 열어봤더니 다 깨져서 저장되었음
	// > 해당 VO 클래스 선언부에 직렬화 선언을 해야 잘 출력되지만 좀 깨져보임
	// 근데 객체단위로 기록되어있어서 깨져보이는 것
}
