package com.kh.chap04_assist.part01_buffered.run;

import com.kh.chap04_assist.part01_buffered.model.dao.BufferedDao;

public class BufferedRun {
	
	/*
	 * * 보조스트림(단독사용 불가, 기반스트림 객체와 결합 후 사용)
	 * > 기반스트림(메인통로)의 부족한 성능, 기능 확장시켜주는 스트림
	 * 
	 * [표현법]
	 * 보조스트림클래스명 객체명 = new 보조스트림클래스명(기반스트림객체);
	 * > 객체생성시 매개변수로 기반스트림 객체를 넘기면서 결합하여 생성하겠다.
	 * 
	 * * 주의사항
	 * 1. 보조스트림과 기반스트림의 방향이 일치해야한다.(입력이면 입력, 출력~ )
	 * 2. 보조스트림과 기반스트림의 통로사이즈가 일치해야함(문자면 문자, 바이트면~ )
	 * 
	 * * BufferedXXX
	 * - 입출력 속도성능 향상 목적의 보조스트림
	 * - 버퍼공간을 제공해서 입출력할 데이터를 한번에 모아뒀다가
	 * 	 한꺼번에 입출력을 진행해서 속도를 더 빠르게 향상시켜주는 원리
	 * 
	 * BufferedReader / BufferedWriter - 2byte
	 * BufferedInputStream / BufferedOutputStream - 1byte
	 * 
	 */
	

	public static void main(String[] args) {
		
		BufferedDao bd = new BufferedDao();
		
		bd.fileSave();
		//bd.fileRead();
		

	}

}
