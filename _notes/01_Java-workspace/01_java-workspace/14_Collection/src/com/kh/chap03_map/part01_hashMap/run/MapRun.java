package com.kh.chap03_map.part01_hashMap.run;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.kh.chap03_map.part01_hashMap.model.vo.Snack;

// HashMap 사용법
public class MapRun {
	
	// 명심할 사항: 계층구조를 보면 List나 Set은 Collection을 구현한 클래스들로 사용법 비슷, value만 저장
	// Map은 collection 계열이 아님, key + value 구조로 저장, 메소드사용법도 다름
	
	// K: Key == Object 
	// V: Value == Object (제네릭설정X)
	
	// 같은 키값으로 또 put하면 마지막 put된 밸류로 덮어씌워짐, 항상 key + value 세트로 저장
	// 제네릭 안하면 마찬가지로, 아무타입 key value 허용 가능
	// 저장순서 유지 X => index개념 없음

	public static void main(String[] args) {
		
		// 제네릭 X
		HashMap hm1 = new HashMap(); // 0.
		
		hm1.put("123", "첫번째 밸류"); // 1. / "123" + "첫번째 밸류"
		hm1.put(9, 1.234);
		hm1.put('a', "첫번째 밸류"); // (value값만 중복 가능)
		hm1.put("123", "두번째 밸류");
		
		System.out.println(hm1.toString());
		
		System.out.println("===================================================");
		
		// 제네릭 O
		HashMap<String, Snack> hm2 = new HashMap<>(); // 2.
		
		hm2.put("빅파이", new Snack("초코딸기맛", 200)); // 3. / 빅파이 + Sncak(초코딸기맛, 200)
		hm2.put("새우깡", new Snack("짠맛", 500));
		hm2.put("마이쮸", new Snack("포도맛", 350));
		hm2.put("칸초", new Snack("초코맛", 670));
		hm2.put("칙촉", new Snack("초코맛", 670)); // 4.
		
		System.out.println(hm2.toString());
		
		hm2.put("새우깡", new Snack("매운맛", 700)); // 4.
		System.out.println(hm2);

		System.out.println(hm2.get("빅파이"));
		
		
		Snack s = hm2.get("마이쮸");
		System.out.println(s);
		System.out.println(hm2.get("초코파이")); // 6.
		
		System.out.println("size: " + hm2.size());
		
		
		hm2.replace("새우깡", new Snack("겁나짠맛", 1000));
		System.out.println(hm2);
		
		hm2.remove("새우깡");
		System.out.println(hm2);
		
		System.out.println("======================================================");
		
		Set<String> keySet = hm2.keySet(); // 9. ~
		
		System.out.println(keySet.toString());
		
		Iterator<String> itKey = keySet.iterator();
		
		while(itKey.hasNext()) {
			
			String key = itKey.next();
			Snack value = hm2.get(key); // 그 key에 따른 value
			
			System.out.println("key: " + key + ", value: " + value);
			
		}
		
		System.out.println("==========================================");
		
		Set<Entry<String, Snack>> entrySet = hm2.entrySet(); // 10. ~
		
		Iterator<Entry<String, Snack>> itEntry = entrySet.iterator();
		
		while(itEntry.hasNext()) {
			
			Entry<String, Snack> entry = itEntry.next();
			
			String key = entry.getKey();
			Snack value = entry.getValue();
			
			System.out.println("key: " + key + ", value: " + value);
		}
	
	
	}
	
	// 0. HashMap 객체 생성
	// 1. put(Object key, Object value) -> 제네릭X
	// : Map 공간에 key + value 세트로 데이터를 추가해주는 메소드
	
	// 제네릭 O
	// 2. 제네릭 설정시 Key와 Value 타입 각각 지정해야함, K == String, V == Snack
	// 3. put(K key, V value)
	// 저장순서유지 X, 제네릭 설정시 key value 모두 맞는 타입의 값만 들어감
	// 4. value값이 동일해도 key값만 다르면 잘 들어감
	
	// 5. get(Objckt lry): V
	// a==
	// 6. 없는 key값 -> null
	// 7. replace(K key, V value): Map공간에 key 값 추가해서 새로 전달된 value로 변경시켜줌
	// >> 단순히 데이터 수정(put은 데이터 추가) 서로 다름.
	// 8. remove(Object key): Map 공간으로부터 전달된 key값을 찾아서 key+value 세트로 지워주는 메소드
	
	// Map에 담겨있는 데이터들에 순차적으로 접근하고자 할 때(반복)?
	// > Map 또한 일반적인 반복문 사용 불가능(index 개념 없어서)
	// > 향상된 for문도 불가능: key + value 세트라서.. String? Snack?
	// > ArrayList 옮긴 후 반복문 사용도 불가능
	// > Iterator 반복자도 안됨
	//
	// >>> Map을 Set으로 바꿔서 하면 됨 2가지 방법 있음.
	// 9. keySet() 메소드 사용: Map에 있는 key값만 뽑아서 Set으로 옮겨주기
	//    keySet의 Set 복주머니가 생김(key값만 있음) >> value도 가져오기
	//
	// 10. entrySet() 메소드 사용: Map에 담긴 key+value를 Entry 형태로 묶어서 Set으로 옮겨줌
	// Entry: 집합형태(key + value 묶여있는 형태)
	
	
}






