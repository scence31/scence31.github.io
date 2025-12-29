package com.kh.cjap02_set.part01_hashSet.run;

import java.util.HashSet;
import java.util.Iterator;

import com.kh.cjap02_set.part01_hashSet.model.vo.Student;

// HashSet 사용방법
public class SetRun {
	
	public static void main(String[] args) {
		
		// 제네릭 X
		HashSet hs1 = new HashSet();
		
		hs1.add("반갑습니다.");
		hs1.add(new String("반갑습니다."));
		hs1.add(new String("여러분"));
		hs1.add(new String("안녕하세요"));
		hs1.add(new String("여러분"));
		hs1.add(50);
		
		System.out.println(hs1.toString());
		
		System.out.println("hs1의 크기: " + hs1.size());
		
		hs1.remove("여러분");
		System.out.println(hs1.toString());
		System.out.println("hs1의 크기: " + hs1.size());
		
		hs1.clear();
		System.out.println(hs1);
		System.out.println("hs1의 크기: " + hs1.size());
		
		System.out.println("hs1 비었나요? " + hs1.isEmpty());
		
		System.out.println("------------------------");
		
		// 제네릭 O
		HashSet<Student> hs2 = new HashSet<>();
		
		hs2.add(new Student("김갑생", 26, 40)); // 2
		hs2.add(new Student("홍길동", 30, 100));
		hs2.add(new Student("고영희", 20, 85));
		hs2.add(new Student("김갑생", 26, 40));
		
		System.out.println(hs2.toString()); // 3
		
		// ~~~강의 다시 보기~~~
		
		Iterator<Student> it = hs2.iterator(); // 4-1
		
		while(it.hasNext()) { // 4-2, 4-3
			
			System.out.println(it.next()); // 4-4
			
		}
		
		// System.out.println(it.next()); 4-5
		
		
	}
	
	
	// 0. HashSet 객체 먼저 생성
	// 1. add(추가할값)
	// 저장되는 데이터 순서유지 및 중복저장 X, 아무 타입 가능(제네릭 안해서)
	// size()
	// remove(삭제할값)
	// clear()
	// isEmpty()
	
	// -제네릭 사용-
	// 2. <Student>로 고정해서 new Student만 사용 가능
	// 3. 출력결과, 저장순서 유지 안됨 / 중복저장 O
	// 중복저장된 이유: 컴퓨터 입장에서 동일한 객체로 판단이 되지 않아서
	
	/*
	 * * HashSet 동작원리
	 * * Set 계열과 List 계열의 메소드 사용법은 비슷함(Collection의 자식이라서)
	 * 
	 * - 값이 add 될 때
	 * equals(), hashCode() 메소드를 통해
	 * 이미 동일한 데이터 있는지 검사 후 add해줌
	 * 
	 * - 객체를 담을 떄
	 * 내부적으로 equals() 비교 + hashCode로 또 비교함 --> 주소값 동등비교
	 * ==> 2차 검증한다고 보면 됨
	 * 
	 * - String은 equals 메소드가 내용물 기준으로 동등비교하게 오버라이딩 됨.
	 * - hashCode는 내용물 기준으로 십진수되게 오버라이딩 됨.
	 * >> String은 내용물 기준으로 중복판별된 것.
	 * 
	 * * 내가 만든 Student 또한 필드값이 일치함을 기준으로 중복판별하고싶음
	 *   String처럼 필드값(내용물) 기준으로 동작하게 오버라이딩하면 됨
	 *   
	 *   
	 * Student 클래스 메소드 오버라이딩
	 * - 원래 equals(): 주소값 동등비교
	 *   오버라이딩된 equals(): 필드값 동등비교
	 * 
	 * - 원래 hashCode(): 주소값을 십진수로 리턴
	 * 	 오버라이딩된 hashCode(): 필드값을 십진수로 리턴
	 */
	
	// * .Iterator() 메소드: Collection 계열의 List, Set 계열 모두 사용 가능(Map 계열은 사용불가!)
	//   >> Collection에 있는 데이터들을 Iterator 공간으로 옮겨주는 역할
	//
	// 4-1. Iterator(반복자): HasySet에 있는 데이터를 Iterator에 옮겨 담기
	// 4-2. Iterator로 반복(더이상 뽑을 데이터가 없을 때까지, while문 사용)
	// 4-3. it.hasNext(): Iterator 객체에서 더 뽑을 데이터가 있나 검사, 있으면 true or false
	// 4-4. it.next(): 객체에서 제공하는 데이터를 뽑아주는 메소드
	// 4-5. 다 뽑았는데 또 it.next() 하면 오류발생 NoSuchElementException
	
	
	
	
	
}


















