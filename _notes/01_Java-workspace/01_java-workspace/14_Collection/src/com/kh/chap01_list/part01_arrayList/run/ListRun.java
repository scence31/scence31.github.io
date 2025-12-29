package com.kh.chap01_list.part01_arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.chap01_list.part01_arrayList.model.vo.Music;

/*
 * * 컬렉션: 데이터가 새로 추가, 삭제 혹은 수정되는 기능들이 이미 코드로 정의된 틀이 있음
 * > 자바에서 제공하는 자료구조를(내장되어있는 클래스) 담당하는 프레임워크
 * 
 * - 자료구조: 방대한 데이터를 효율적(구조적)으로 다룰 때 필요한 개념
 * - 프레임워크: 효율적인 기능들이 이미 정의되어있는 틀(코드들이 정의되어 있으니 갖다 쓰자. 라이브러리, API와 비슷)
 * 
 * - 컬렉션 장점(배열의 단점 보완)
 * 1. 크기에 제약 없음(크기 지정할 필요 없고, 지정해도 맞춰서 변동함)
 * 2. 중간에 값 추가하거나 삭제해도 일일이 밀고 땡겨주는 코드가 메소드로 정의되어 있음
 * 3. 기본적으로 여러 타입의 데이터들을 저장할 수 있음(제네릭 설정을 통해서 한 타입만 담기게 유도도 가능 like 배열)
 * 
 * - 배열 vs 컬렉션 어떤 것을 사용할지(컬렉션 더 많이 씀)
 * > 배열: 방대한 데이터들을 단순히 조회만 할 때, 데이터 개수를 알 때
 * > 컬렉션: 방대한 데이터들을 빈번하게 수정될 때, 데이터 개수를 모를 때
 * 
 * * 컬렉션의 3가지 분류
 * - 크게 2가지 계열로 나눌 수 있음
 * 1. Collection 계열(List, Set 계열)
 * 2. Map 계열
 * 
 * - List 계열(선형구조): 담을 값만(value) 저장, 순서유지 됨(index 개념, 배열의 연장선), 값 저장 시 중복값 허용
 *   자식클래스들: ArrayList(실무 사용 多), Vector, LinkedList, ...
 *   
 * - Set 계열(비선형구조): 담을 값만(value) 저장, 순서유지 안됨(index 없음), 중복값 안됨
 *   자식클래스들: HashSet(실무 多), TreeSet, ...
 * 
 * - Map 계열: key + value 세트로 저장, 순서유지 안됨, Key 값만 중복 불가(value 값은 가능)
 *   자식클래스들: HashMap(실무 多), HashTable, Properties(실무 多), ...
 */

// ArrayList 사용법
public class ListRun {

	public static void main(String[] args) {
		
		ArrayList list = new ArrayList();
		
		System.out.println(list.toString()); // []
		
		list.add(new Music("Golden", "헌트릭스")); 	// [0] 1. add
		list.add(new Music("Soda Pop", "사자보이즈")); // [1]
		list.add(new Music("뛰어", "블랙핑크")); 		// [2]
		list.add("끝");								// [3]
		System.out.println(list);
		
		list.add(1, new Music("FAMOUS", "올데이 프로젝트")); // 2. add
		System.out.println(list);
		
		list.set(0,  new Music("Dirty Work", "에스파")); // 3. set
		System.out.println(list);
		
		list.remove(1); // 4. remove
		System.out.println(list);
		
		System.out.println("리스트에 담긴 데이터 수: " + list.size()); // 5. size
		System.out.println("리스트의 마지막 인덱스: " + (list.size() - 1));
		
		System.out.println(list.get(0).toString()); // 오버라이딩된 Music의 toString이 호출됨
		
		System.out.println(((Music)list.get(0)).getTitle());
		
		System.out.println("========================");
		
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i));
		}
		
		System.out.println("========================");
		
		for(Object o : list) {
			
			System.out.println(o);
		}
		
		System.out.println("========================");
		
		List sub = list.subList(0, 2);
		System.out.println(sub);
		
		System.out.println("========================");
		
		list.addAll(sub);
		System.out.println(list);
		
		System.out.println("========================");
		
		System.out.println("리스트가 비었나요?: " + list.isEmpty());
		
		list.clear(); // 10. clear
		System.out.println("리스트가 비었나요?: " + list.isEmpty());
		System.out.println(list);
		System.out.println("리스트의 크기: " + list.size());
		
		
		
		
	}
	
	// ArrayList list = new ArrayList(); -> 기본생성자로 생성시 10칸짜리 배열 생성됨
	// ArrayList list = new ArrayList(3); -> 매개변수생성자 생성시 원하는 사이즈 배열 생성
	// sysout(list) 하면 -> 안에 아무것도 없는 상태, 비어있음
	
	// * ArrayList 유용한 메소드들
	// E(Element, 제네릭) == Object 타입
	
	// 1. add(E e) == add(Object e): 해당 리스트의 끝에 전달된 e를 추가하는 메소드
	// sysout(lists) -> 순서 유지, 크게제약 없음, 다양한 타입 값 추가 가능
	
	// 2. add(int index, E e): 오버로딩, 리스트에 전달되는 index 번째 자리에 e를 추가하는 메소드
	// sysout 중간에 값 추가시 알아서 기존 값들을 뒤로 땡겨주는 작업 내부적으로 진행됨
	
	// 3. set(int index, E e): 리스트에 해당 index 자리 값을, 전달되는 e로 변경해주는 메소드 (덮어씌움)
	
	// 4. remove(int index): 리스트에 해당 index 자리 값을 삭제해줌, 삭제 후 공백은 땡겨지면서 채워짐 
	
	// 5. size(): 현재 리스트에 담긴 데이터 개수를 반환해주는 메소드
	// (마지막 인덱스 == 리스트 크기(size) -1)
	
	// 6. get(int index): E, 리스트로부터 해당 index 번째 자리의 데이터를 반환해주는 메소드
	// Music m = (Music)list.get(0); // 다형성(DownCasting 강제형변환)
	// System.out.println(m);
	// 제목만 보고싶은 경우: 강제형변환(DownCasting) 후 해당 필드의 getter 호출
	
	// for문: 0 ~ 마지막인덱스까지 모두 호출
	
	// 향상된 for문(for each): 배열 뿐만 아니라 List에서도 사용 가능
	// for(변수선언문: 배열명) {}
	// for(변수선언문: 리스트객체명) {}
	// > 리스트의 어느 데이터를 뽑더라도 다형성에 의해 Object 형의 변수로 받을 수 있음
	
	// 7. subList(int beginIndex, int endIndex): List
	// > 해당 리스트로부터, beginIndex에서 endIndex -1까지의 데이터 값들을 추출 후 새로운 List 객체를 만들어 반환
	// endIndex 값 포함 아님.
	
	// 8. addAll(Collection c): 해당 리스트에 다른 컬렉션에 있는 데이터들을 통째로 추가해주는 메소드
	// String concat메소드의 List 버전
	
	// 9. isEmpty(): boolean 비슷, 비어있으면 true 아니면 false 반환
	// list.size() == 0이면 isEmpty() == true
	
	// 10. clear(): 해당 리스트를 싹 비워줌
	
}





























