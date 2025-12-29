package com.kh.chap02_abstractANDinterface.part01_basic.run;

import com.kh.chap02_abstractANDinterface.part01_basic.model.vo.Basketball;
import com.kh.chap02_abstractANDinterface.part01_basic.model.vo.Football;
import com.kh.chap02_abstractANDinterface.part01_basic.model.vo.Sports;

public class BasicRun {

	public static void main(String[] args) {
		
		// Sports 객체 생성
		// Sports s = new Sports();
		// > 추상클래스를 가지고 객체생성은 절대 불가능함
		//   미완성된 클래스이기 때문!
		
		// Sports s;
		// > 단, 객체생성만 안될 뿐, 참조변수로는 사용가능!
		
		// s = new Football();
		// s = new Basketball();
		// > 즉, 다형성을 적용해서 자식객체를 받아주는 용도로는 사용 가능!!
		
		// 객체배열 또한 사용가능
		Sports[] arr = new Sports[2];
		arr[0] = new Football();
		arr[1] = new Basketball();
		
		// 반복문
		for(int i = 0; i < arr.length; i++) {
			
			arr[i].rule();
		}
		
		/*
		 * * 추상메소드
		 * - 메소드 사용의 통일성을 확보하기 위한 목적으로 사용
		 * > 코드상의 표준화된 틀을 제공할 목적으로 사용한다!
		 * 
		 * * 추상클래스
		 * - 추상메소드가 하나라도 포함되는 순간 추상클래스로 정의함
		 *   (일반필드 + 일반메소드 + 추상메소드(생략가능))
		 * - 단, 추상메소드가 굳이 없더라도 해당 클래스를 추상클래스로 둘 수도 있긴 함!
		 *   (추상클래스에 반드시 추상메소드가 포함될 필요는 없다)
		 * > 객체생성 불가능
		 * > 단, 참조변수로는 사용 가능 (다형성 적용가능)
		 *   
		 * * 추상메소드가 없더라도 abstract라는 키워드만 붙이면
		 *   해당클래스를 추상클래스로 둘 수 있다.
		 * - 코드작업시 클래스가 아직 구체적으로 구현되지 않은 상태인 것 같을 때 (개념적)
		 * - 현재 당장 이 클래스를 객체생성이 불가능하도록 막고싶을 경우 (기술적)
		 * 
		 */

	}

}



















