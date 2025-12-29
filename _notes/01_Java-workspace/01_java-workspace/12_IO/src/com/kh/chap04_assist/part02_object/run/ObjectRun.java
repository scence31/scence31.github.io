package com.kh.chap04_assist.part02_object.run;

import com.kh.chap04_assist.part02_object.model.dao.ObjectDao;

public class ObjectRun {
	
	
	/*
	 * * ObjectXXX
	 * - 객체 단위로 입출력할 수 있게 도와주는 보조스트림
	 * 
	 * ObjectInputStream / ObjectOutputStream - 1byte만 있고, 2byte는 없음!!
	 * 
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {

		ObjectDao od = new ObjectDao();
		
		// od.fileSave();
		od.fileRead();
		

	}

}
