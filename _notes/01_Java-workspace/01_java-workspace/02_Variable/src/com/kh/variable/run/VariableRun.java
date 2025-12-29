package com.kh.variable.run;

// import 풀클래스명;
import com.kh.variable.A_Variable;
import com.kh.variable.B_KeyboardInput;
import com.kh.variable.C_Cast;
import com.kh.variable.D_Printf;

public class VariableRun { // VariableRun 영역 시작

	public static void main(String[] args) { //메인메소드 영역 시작
		
		//System.out.println("아래 사각형의 넓이를 구하시오");
		
		
		A_Variable a = new A_Variable();
		
		//대변할이름.메소드명();
		//a.printSquare();
		//a.declareVariable();
		a.initVariable();
		//a.constant();
		//a.overflow();
		
		B_KeyboardInput b = new B_KeyboardInput();
		
		//b.inputTest1();
		//b.inputTest2();
		//b.inputTest3();
		//b.inputTest4();
		//b.inputTest5();
		
		C_Cast c = new C_Cast();
		
		//c.autoCasting();
		//c.forceCasting();
		
		D_Printf d = new D_Printf();
		
		//d.printfTest();
		
		
		
		
	} //메인메소드 영역 끝
	
} // VariableRun 영역 끝
