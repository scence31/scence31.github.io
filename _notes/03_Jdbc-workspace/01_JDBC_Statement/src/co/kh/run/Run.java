package co.kh.run;

import co.kh.view.MemberView;

public class Run {

	/*
	 * * MVC 패턴
	 * 
	 * - 역할에 따라 데이터관련(Model), 화면(View), 요청처리(Controller) 이렇 게코드로 분류, 개발 및 유지보수(웹개발분야 많이 사용)
	 * 
	 * 
	 * M: Model. 데이터 관련 코드를 담는 부분, VO클래스 그리고 외부매체 직접 연결해서 입출력이 일어나는 DAO(model.dao 패키지에 저장)
	 * 
	 * 
	 * V: View: CLI(Command Line Interface) 환경 - 입력문, 출력문으로 구현(화면에 콘솔 띄우기)
	 * 		    GUI(Craphic User INterface) 환경 - html, css 등으로 구현(화면을 브라우처창에 띄우기)
	 * 
	 * 
	 * C: Controller: 실제 요청을 받아 처리하는 코드를 담는 부분. 사용자로부터 전달받은 입력값을 VO객체로 가공하여 DAO로 넘겨줌
	 * 				  DAO로부터 받은 결과값에 따라 응답화면 VIEW를 지정하는 역할
	 * 
	 * * 코드 흐름
	 * RUN --> MemberView --> memberController --> MemberDao --> DB
	 * 					  <--				   <--			 <--
	 */
	
	public static void main(String[] args) {
		
		// 메인화면 담당 메소드 호출
		// MemberView mv = new MemberView();
		// mv.mainMenu();
		new MemberView().mainMenu();
		
		
		
		
	}

}














