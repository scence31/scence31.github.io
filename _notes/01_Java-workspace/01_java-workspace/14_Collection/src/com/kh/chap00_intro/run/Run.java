package com.kh.chap00_intro.run;

import com.kh.chap00_intro.util.MyList;

public class Run {

	public static void main(String[] args) {
		
		MyList list = new MyList();
		
		list.add("ㅎㅇ");
		list.add(9);
		list.add(2, 4);
		list.add(2, 10);
		list.size();
		list.remove(1);
		System.out.println(list.toString());
		
		for(int i = 0; i < 10; i++) {
			
			System.out.println(list);
		}
		
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i));
			
		}

	}
	
	/*
	 * * MyList와 같이 기존 배열의 단점들을 보완했더니
	 * - 다량의 데이터들을 다량으로 담을 수 있게됨
	 * - 자료형 상관없이 아무 타입의 데이터 담기 가능
	 * - 근본은 배열이라 인덱스개념 활용해서 코드를 짤 수 있음(반복문활용)
	 * 	 (다량의 데이터들을 한 줄로 세워서 관리할 수 있음)
	 * 
	 * * 다량의 데이터들을 어떻게하면 효율적으로 관리할 수 있을까?.? --> 자료구조(Data Structure)
	 * 1. 선형자료구조: 한 줄로 세우기 - 아까 만든 MyList에 해당
	 *    ex) 리스트: 배열 기반 줄세워서, 각각 데이터를 비엔나 소시지처럼 관리 등
	 *    	  스택: 선입후출 구조로 데이터 줄세워 관리
	 *    	  큐: 선입선출 구조로 데이터 줄세워 관리
	 *    	  등등..
	 *    
	 * 2. 비선형자료구조: 막 담기(일정 규칙 하에)
	 *    ex) 트리: 부모-자식관계 형식으로 관리
	 *    	  그래프: 그물망 형식으로 관리
	 * 
	 */

}
