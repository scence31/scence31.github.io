package com.kh.field.example2.run;

import com.kh.field.example2.model.vo.Protuct;

public class ProductTest {
	
	public static void main(String[] args) {
		
		Protuct p1 = new Protuct("ssgnote9", "갤럭시노트9", "경기도 수원", 960000, 10.0);
		System.out.println(p1.information());
		
		Protuct p2 = new Protuct("lgxnote5", "LG스마트폰5", "경기도 평택", 780000, 0.7);
		System.out.println(p2.information());
		
		Protuct p3 = new Protuct("ktsnote3", "KT스마트폰3", "서울시 강남", 250000, 0.3);
		System.out.println(p3.information());
		
		System.out.println("================================================");
		
		p1.setPrice(1200000);
		p1.setTax(0.05);
		System.out.println(p1.information());
		
		p2.setPrice(1200000);
		p2.setTax(0.05);
		System.out.println(p2.information());
		
		p3.setPrice(1200000);
		p3.setTax(0.05);
		System.out.println(p3.information());
		
		System.out.println("==============================================");
		
		System.out.println("상품명 = " + p1.getProductName());
		System.out.printf("부가세 포함 가격 = %.0f원 \n", (p1.getPrice() + (p1.getPrice()*p1.getTax())));
		
		System.out.println("상품명 = " + p2.getProductName());
		System.out.printf("부가세 포함 가격 = %.0f원 \n", (p2.getPrice() + (p2.getPrice()*p2.getTax())));
		
		System.out.println("상품명 = " + p3.getProductName());
		System.out.printf("부가세 포함 가격 = %.0f원 \n", (p3.getPrice() + (p3.getPrice()*p3.getTax())));
	}

}
