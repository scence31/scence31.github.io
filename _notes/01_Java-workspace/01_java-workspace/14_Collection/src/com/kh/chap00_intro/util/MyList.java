package com.kh.chap00_intro.util;

import java.util.Arrays;

// Collection 수업을 위한 예제
// 마치 배열처럼 여러 개의 데이터를 타입에 상관없이 저장할 수 있게 구현
// 또한 사이즈 제한 없이 저장하게끔 구현
// > 즉, 기존 배열의 단점을 보완한 배열을 개조해서 쓰고싶다!
public class MyList {
	
	// 필드부
	private Object[] arr; // 자료형에 상관없이 아무 데이터나 다 담을 수 있는 배열(다형성 적용)
	private int size; // arr 배열에 실제 담겨있는 총 개수
	
	// 생성자부
	// 기본생성자 - arr 배열을 10칸짜리로 할당(new), size 값 0으로 세팅
	public MyList() {
		
		arr = new Object[10];
		size = 0;
	}
	
	// 매개변수생성자 - arr배열을 length 칸짜리로 할당(new), size 값 0으로 세팅
	public MyList(int length) {
		
		arr = new Object[length];
		size = 0;
	}
	
	
	// 메소드부
	// add 메소드1 - 전달받은 item을 arr배열의 마지막 인덱스에 저장(뒤에 차곡차곡 데이터 넣으라는 뜻)
	//				단 배열이 다 차있으면 기존 arr배열을 깊은복사 하되,
	//				기존의 2배 사이즈로 깊은복사 후 마지막 인덱스에 저장
	//				당연히 add가 한 번 일어날 때마다 size도 1씩 증가해야함
	public void add(Object item) {
		
		if(size == arr.length) {
			
			arr = Arrays.copyOf(arr, arr.length*2);
			// 기존 arr배열의 2배 크기 깊은복사 후 다시 arr 대입
		}
		
		arr[size] = item;
		size++;
		
	}
	
	// add 메소드2 - 전달받은 item을 arr배열의 index번째 칸에 저장
	//				단, 배열이 이미 다 차있다면 기존 arr배열을 깊은복사하되,
	//				기존의 2배 사이즈로 깊은복사 후 index번째 칸에 저장
	//				당연히 add가 한 번 일어날 때마다 size도 1씩 증가해야함
	// > 해당 인덱스의 값부터 마지막 값까지 뒤로 한 칸씩 밀어주는 작업 후 진행!(반복문)
	public void add(int index, Object item) {
		
		if(size == arr.length) {
			
			arr = Arrays.copyOf(arr, arr.length*2);
			
		}
		
		for(int i = size; i > index; i--) {
			
			arr[i] = arr[i-1];
		}
		
		arr[index] = item;
		size++;
			
	}
		
	
	// set메소드 - 전달받은 index번째 칸의 기존 데이터를 item으로 변경
	//			단, index는 현재 값이 차있는 인덱스로 제시해야함(if문 이용)
	public void set(int index, Object item) {
		
		if(index >= 0 && index < size) {
			
			arr[index] = item;
		
		}
	}
	
	// get메소드 - arr배열로부터 전달받은 index번째 데이터를 리턴
	//			단, 없는 index를 매개변수로 전달했을 경우는 null값을 리턴
	public Object get(int index) {
		
		if(index >= 0 && index < size) { // 현재 값이 담긴 인덱스
			
			return arr[index];
			
		} else {
			
			return null;
		}
		

	}
	
	// remove메소드 - arr배열로부터 전달받은 index번째 데이터를 삭제
	//				당연히 size값 또한 1감소시키기
	// > 해당 데이터 삭제 후 뒤에 데이터들을 앞으로 떙겨오는 작업까지 진행
	public void remove(int index) {
		
		for(int i = index; i < size -1; i++) { // 시작 index, 끝 .... i++...)
			
			arr[i] = arr[i+1];
		}
		// 마지막 데이터 삭제(null)
		arr[size -1] = null;
		size--;
	}
	
	// size메소드 - arr배열에 실제 담겨있는 데이터의 개수를 반환
	public int size() {
		
		return size;
		
		
	}
	
	// isEmpty 메소드 - arr배열에 현재 담겨있는 데이터가 하나도 없다면(0개) true 리턴,
	//				  하나라도 있다면(1개 이상) false 리턴
	public boolean isEmpty() {
		
		if(size == 0) {
			
			return true;
		} else {
			
			return false;
		}
	}
	
	// toString 메소드 오버라이딩 - arr 배열의 내용물을 "[값, 값, 값]" 형식으로 문자열 리턴
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for(int i = 0; i <arr.length; i++) {
			
			sb.append(arr[i]);
			sb.append(", ");
		}
		sb.append(']'); 
		
		
		return sb.toString();
		
	}
	
	// add-1 메소드 부분
	// 배열이 이미 차 있는 경우
	
	// add-2
	// arr[index]에 item을 대입하기 전에 끝에 있는 값부터 뒤로 땡겨야댐 --> 감소하는방향으로 for문
	// false가 되면 빠져나감
	
	// set
	// 현재 값이 담겨있는 index일 경우에만 대입 진행
}








