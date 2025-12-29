package com.kh.chap03_char.run;

import com.kh.chap03_char.model.dao.FileCharDao;

public class CharRun {
	
	/*
	 * * 문자기반스트림
	 * - 문자스트림: 2byte 단위로 입출력할 수 있는 넓은통로 ( XXXReader / XXXWriter)
	 * - 기반스트림: 외부매체와 직접적으로 연결되는 메인통로
	 * 
	 * > 외부매체를 지정하고 그 외부매체와 직접적으로 연결되는 2byte 단위의 통로
	 * 
	 * XXXReader: XXX라는 외부매체로부터 데이터를 "입력받는" 통로
	 * XXXWriter: XXX라는~ "출력하는" 통로
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		FileCharDao fcd = new FileCharDao();
		
		//fcd.fileSave();
		fcd.fileRead();
		
		


	}

}
