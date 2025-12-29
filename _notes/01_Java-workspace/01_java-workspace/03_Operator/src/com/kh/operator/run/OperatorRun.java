package com.kh.operator.run;

/*
import com.kh.operator.A_Arithmetic;
import com.kh.operator.B_InDecrease;
import com.kh.operator.C_Compound;
import com.kh.operator.D_LogicalNegation;
import com.kh.operator.E_Comparison;
import com.kh.operator.F_Logical;
import com.kh.operator.G_Triple;
*/

// > 마침 이 클래스에서 가져다 쓸 다른 크래스들은 모두
//   com.kh.operator 패키지에 들어가있음!!
// > 이 경우에는 위와 같이 import 구문을 일일이 써도 되지만
//   아래와 같이 한 줄로 줄여서 쓰기도 가능하다!!
import com.kh.operator.*;
// > com.kh.operator 패키지의 "모든" 클래스를 가져다 쓰겠다는 의미
// > 해당 패키지의 모든 클래스를 가져다 쓸 경우 .*을 쓰는 것을 권장함!!
//   단, 해당 패키지의 클래스 소수(한 두개) 쓸 경우에는 그냥 일일이 쓰는 것 권장!!
//   (정말 필요한 경우에만 사용하자)
// 이유: 필요없는 클래스를 갖다 쓰기 때문에 컴퓨터 성능 저하를 일으킴

public class OperatorRun { // OperatorRun 클래스 영역 시작
	
	public static void main(String[] args) { // 메인메소드 영역 시작
		
		//System.out.println();
		
		A_Arithmetic a = new A_Arithmetic();
		
		//a.method1();
		//a.method2();
		
		B_InDecrease b = new B_InDecrease();
		
		//b.method1();
		b.method2();
		//b.method3();
		//b.method4();
		
		C_Compound c = new C_Compound();
		
		//c.method1();
		
		D_LogicalNegation d = new D_LogicalNegation();
		
		//d.method1();
		
		E_Comparison e = new E_Comparison();
		
		//e.method1();
		//e.method2();
		
		F_Logical f = new F_Logical();
		
		//f.method1();
		//f.method2();
		//f.method3();
		//f.method4();
		
		G_Triple g = new G_Triple();
		
		//g.method1();
		//g.method2();
		//g.method3();
		g.method4();
		
		
	} // 메인메소드 영역 끝

} // OperatorRun 클래스 영역 끝
