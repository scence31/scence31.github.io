package com.kh.chap02_byte.run;

import com.kh.chap02_bye.model.dao.FileByteDao;

public class ByteRun {
	
	/* * 바이트기반스트림(바이트 + 기반)
	 * > 외부매체를 지정하고 직접적으로 연결되는 1바이트 단위 통로
	 * 
	 * XXXInputStream: XXX라는 외부매체로부터 데이터를 입력받는 통로
	 * XXXOutputStream: XXX라는 외부매체로 데이터를 출력하는 통로
	 * 
	 */
	public static void main(String[] args) {
		
		FileByteDao fb = new FileByteDao();
		
		//fb.fileSave();
		fb.fileRead();
		
		
	}

}
