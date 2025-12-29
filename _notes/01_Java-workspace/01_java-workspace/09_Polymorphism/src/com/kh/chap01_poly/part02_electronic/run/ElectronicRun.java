package com.kh.chap01_poly.part02_electronic.run;

import com.kh.chap01_poly.part02_electronic.controller.ElectronicController2;
import com.kh.chap01_poly.part02_electronic.model.vo.Desktop;
import com.kh.chap01_poly.part02_electronic.model.vo.Electronic;
import com.kh.chap01_poly.part02_electronic.model.vo.NoteBook;
import com.kh.chap01_poly.part02_electronic.model.vo.Tablet;

public class ElectronicRun {

	public static void main(String[] args) {
		
		// 가게 오픈
		/*
		// 1. 다형성을 적용 안 했을 경우 (ElectronicController1)
		ElectronicController1 ec1 = new ElectronicController1();
		// 이 객체를 생성하는 순간 멤버변수로 정의해놨던
		// 각 제품을 담는 상자들(desk, note, tab)이 heap 영역에 생성됨
		
		// 지금은 각 상자에 null이 담겨있음(즉, 아직 제품은 없음)
		// > 제품을 납품받아서 각 상자에 포장해보기
		
		Desktop d = new Desktop("삼성","게이밍",2000000, "Geforce 1070");
		ec1.insert(d);
		
		//NoteBook n = new NoteBook("엘지", "그램", 1500000, 3);
		// ec.insert(n);
		ec1.insert(new NoteBook("엘지", "그램", 1500000, 3));
		
		ec1.insert(new Tablet("애플", "아이패드 프로", 1000000, true));
		
		// > 이 시점 기준으로 상자에 제품들이 잘 담겨있을 것
		
		// 장사 시작
		
		// 손님 입장: 데스크탑, 노트북, 태블릿 제품 모두 상자에서 한번 꺼내서 보여주세요
		// > 각 상자로부터 제품을 꺼내서 보어줘야함
		
		
		Desktop desk = ec1.selectDesktop();
		NoteBook note = ec1.selectNoteBook();
		Tablet tap = ec1.salectTablet();
		
		System.out.println(desk);
		System.out.println(note);
		System.out.println(tab);
		*/
		
		// 2. 다형성을 적용한 경우 (ElectronicController2)
		ElectronicController2 ec2 = new ElectronicController2();
		// > 객체 생성과 동시에 어느 제품이든지 간에 3개를 보관할 수 있는
		//   창고 공간이 생성됨(elec) (위의 elec1과 다름)
		
		// 장사 준비
		// > 제품을 납품받아서 창고에 차곡차곡 쌓을 것
		ec2.insert(new Desktop("삼성", "게이밍데스크탑", 2000000, "Geforce 1070"), 0);
		ec2.insert(new NoteBook("엘지", "그램", 1500000, 3), 1);
		ec2.insert(new Tablet("애플", "아이패드프로", 1000000, true), 2);
		
		// 장사 시작
		/*
		// 손님 입장: 데스크탑, 노트북, 태블릿 제품을 꺼내서 보여주시겠어요?
		Desktop desk = (Desktop)ec2.select(0);
		NoteBook note = (NoteBook)ec2.select(1);
		Tablet tab = (Tablet)ec2.select(2);
		
		System.out.println(desk);
		System.out.println(note);
		System.out.println(tab);
		*/
		
		// 손님 입장: 여기서부터 저기까지 있는 제품 다 보여주세요
		Electronic[] elec = ec2.select();
		
		for(int i = 0; i < elec.length; i++) {
			
			System.out.println(elec[i]/*.toString()*/);
		}
		
		/*
		 * * 다형성을 사용하는 이유
		 * 1. 부모타입의 객체배열로 다양한 자식객체들을 받아줄 수 있음
		 * 2. 메소드의 매개변수나 반환형에 다형성을 적용하게 되면
		 * 	  메소드의 개수를 확 줄여줄 수 있다!
		 * 
		 * * 객체지향프로그래밍(OOP: Object Programming)의 3대 요소 (특징)
		 * 1. 캡슐화
		 * 2. 상속
		 * 3. 다형성
		 * 
		 * + 4대요소(특징): 추상화 포함
		 * 
		 */
		
		
		
	}

}



















