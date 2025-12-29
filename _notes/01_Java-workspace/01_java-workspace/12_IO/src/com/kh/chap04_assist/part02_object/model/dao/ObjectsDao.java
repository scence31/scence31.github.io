package com.kh.chap04_assist.part02_object.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.chap04_assist.part02_object.model.vo.Phone;

public class ObjectsDao {
	
	// [출력] 프로그램(기준점) --> 외부매체(파일)
	// FileOutputStream + ObjectOutputStream
	
	// arr 배열의 모든 인덱스에 있는 휴대폰 객체를
	// fileName쪽으로 출력하기
	
	public void fileSave(String fileName) {
		
		// 휴대폰들의 정보를 객체배열로 나타내기
		
		Phone[] arr = new Phone[3]; // [0] [1] [2], null null null
		
		arr[0] = new Phone("아이폰", 1300000);
		arr[1] = new Phone("갤럭시", 1000000);
		arr[2] = new Phone("플립", 2000000);
		
		try(ObjectOutputStream oos
				= new ObjectOutputStream(new FileOutputStream(fileName))) {
			
			for(int i = 0; i < arr.length; i++) {
				
				oos.writeObject(arr[i]);
				
			}
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("프로그램 종료");
	}
	
	// [입력] 프로그램 <- 외부매체(파일)
	
	public void fileRead() {
		
		ObjectInputStream ois = null;
		
		try {
			
			ois = new ObjectInputStream(new FileInputStream("phoneList.txt"));
			
			while(true) {
				
				System.out.println(ois.readObject().toString());
				//더 이상 읽어드릴 데이터 없으면 EOFException(End Of File) 오류 발생
			}

		} catch(EOFException e) {
			
			System.out.println("파일 다 읽었습니다.");
		} catch(IOException e) {
			
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				
				ois.close();
			} catch(IOException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println("프로그램 종료");
	}

}






















