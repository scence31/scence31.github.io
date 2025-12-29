package com.kh.chap01_beforeVSafter.before.run;

import com.kh.chap01_beforeVSafter.before.model.vo.Desktop;
import com.kh.chap01_beforeVSafter.before.model.vo.SmartPhone;
import com.kh.chap01_beforeVSafter.before.model.vo.Tv;

public class BeforeRun {

	public static void main(String[] args) {
		
		// Desktop 객체
		Desktop d = new Desktop();
		
		d.setBrand("삼성");
		d.setpCode("d-01");
		d.setpName("게이밍데스크탑");
		d.setPrice(2300000);
		d.setAllInOne(true);
		
		// Tv 객체
		
		Tv t = new Tv("엘지", "t-01", "고오급벽걸이티비", 3500000, 100);
		
		// SmartPhone 객체
		SmartPhone s = new SmartPhone("애플", "s-01", "아이폰", 1200000, "SKT");
		
		System.out.println(d);
		System.out.println(t);
		System.out.println(s);
		// 주소값 그대로 출력
		
		System.out.println(d.information());
		System.out.println(t.information());
		System.out.println(s.information());
		
		/*
		 * * VO 클래스 작성시 매 클래스마다 중복된 코드를 일일이 기술하고 있음!
		 * - Desktop, Tv, SmartPhone 클래스 모두 brand pCode pName price 필드 갖고있음!
		 * - 필드가 겹치다 보니 필드에 딸린 나머지 코드들도 모두 겹침! 중복
		 * 
		 * > 매 클래스마다 중복된 코드들을 일일이 기술해두면
		 *   코드를 작성하는 과정이 귀찮고 실수할 가능성도 있을 뿐만 아니라
		 *   추후 수정과 같은 유지보수시 매번 해당 코드를 일일이 찾아서
		 *   전부 수정해야하는 번거로움이 생김!
		 *   
		 * * "상속(inheritance)" 개념을 적용해서
		 *    매 클래스마다 중복된 필드, 메소드들을 단 한 번만 또 하나의 클래스(부모클래스)로 정의한 후
		 *    해당 클래스의 코드를 갖다 쓰는 형식으로 진행
		 *    (상속 == 코드를 물려받겠다)
		 * 
		 */
		

	}

}














