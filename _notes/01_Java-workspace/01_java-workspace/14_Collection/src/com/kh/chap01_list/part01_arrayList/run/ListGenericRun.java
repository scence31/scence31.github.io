package com.kh.chap01_list.part01_arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.chap01_list.part01_arrayList.model.vo.Music;

public class ListGenericRun {
	
	/*
	 * * 제네릭(Generic, <E>): < > 안에 특정 타입을 선언해 Collection 안에 담을 데이터의 타입을 배열처럼 픽스하는 역할
	 * 
	 * - 별도의 제네릭 표시 없이 컬렉션 객체를 생성하면 E == Object, 다형성 적용
	 * - 제네릭 설정하면 E == 해당타입(String, Music 등..) 값만 담을 수 있음 <String> 등
	 *   
	 * * 제네릭 설정하는 이유
	 * 1. 명시한 타입의 데이터만 저장 가능하도록 타입에 제한을 두기 위해
	 * 2. 컬렉션에 저장된 객체를 매번 꺼내서 사용할 때 다형성 생략 가능
	 * 
	 * * 제네릭 설정 주의사항
	 * 1. < > 안의 타입은 참조자료형만 가능(int, double 안됨 근데 Integer, Double 가능 - Wrapper 타입 사용)
	 *   (기본자료형 int 등은 불가능)
	 */
	
	// 제네릭 설정한 ArrayList 사용법
	public static void main(String[] args) {
		
		ArrayList<Music> list = new ArrayList<>();
		
		System.out.println(list);
		
		list.add(new Music("Golden", "헌트릭스")); 		// [0] 1. add
		list.add(new Music("Soda Pop", "사자보이즈")); 		// [1]
		list.add(new Music("뛰어", "블랙핑크")); 			// [2]
		System.out.println(list); // Music 타입만 사용 가능
		
		list.add(1, new Music("FAMOUS", "올데이 프로젝트")); // 2. add
		System.out.println(list);
		
		list.set(0,  new Music("Doirty Work", "에스파")); // 3.set
		System.out.println(list);
		
		list.remove(1);
		System.out.println(list);
		
		System.out.println("리스트에 담긴 데이터 수: " + list.size());
		System.out.println("마지막 리스트 인덱스: " + (list.size() -1));
		
		Music m = list.get(0);
		System.out.println(list.get(0).getTitle()); // 제목만
		System.out.println(list.get(0));
		
		System.out.println("============================");
		
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i).toString());
			
		}
		
		System.out.println("============================");
		
		for(Music music : list) {
			
			System.out.println(music);
			System.out.println(music.getTitle());
			
		}
		
		System.out.println("============================");
		
		List<Music> sub = list.subList(0, 2);
		System.out.println(sub);
		
		System.out.println("============================");
		
		list.addAll(sub);
		System.out.println(list);
		
		ArrayList<Integer> intList = new ArrayList<>();
		
		intList.add(1);
		intList.add(20); // 일종의 AutoBoxing
		System.out.println(intList);
		
		
	}
	
	// ArrayList 객체 생성시 제네릭 설정. 제네릭 생략하면 사실 <Object> 있던 것.
	// 매개변수 지정하면 그 수만큼 배열 만들어짐 ArrayList<Music>(3);
	// JDK 7버전 부터는 뒤의 제네릭 타입은 생략해도 됨
	
	// 1. add(E e) --> add(Music e)
	
	// 2. add(int index, E e)
	
	// 3. set(int index, Music e) - 기존꺼에 덮어씀
	
	// 4. remove(int index)
	
	// 5. .size()
	
	// 6. get(int index): E
	// 형변환(DownCasting) 안해도 됨
	
	// 7. subList(int beginIndex, int endIndex)
	// List --> java.util.List
	
	// 8. addAll(): 같은 타입만

	// 9. isEmpty()
	// 10. clear()
	
	
	
}


















