package com.kh.chap02_inherit.run;

import com.kh.chap02_inherit.model.vo.Airplane;
import com.kh.chap02_inherit.model.vo.Car;
import com.kh.chap02_inherit.model.vo.Ship;

public class VehicleRun {

	public static void main(String[] args) {
		
		Airplane a = new Airplane("보잉", 400.4, "여객기", 4, 2);
		
		System.out.println(a.information());
		
		Ship s = new Ship("독도호", 500.2, "유람선", 0);
		
		System.out.println(s.information());
		
		Car c = new Car("그랜져", 800.53, "승용차", 4);
		
		System.out.println(c.information());
		
		

	}

}
