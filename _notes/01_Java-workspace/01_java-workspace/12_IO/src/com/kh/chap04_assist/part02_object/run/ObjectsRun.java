package com.kh.chap04_assist.part02_object.run;

import java.util.Scanner;

import com.kh.chap04_assist.part02_object.model.dao.ObjectsDao;

public class ObjectsRun {

	public static void main(String[] args) {
		
		ObjectsDao ods = new ObjectsDao();
		
		//Scanner sc = new Scanner(System.in);
		
		//System.out.print("저장할 파일명 입력: ");
		//String fileName = sc.nextLine();
		
		//ods.fileSave(fileName);
		
		ods.fileRead();
		
	}

}
