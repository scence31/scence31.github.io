package com.kh.run;

import com.kh.model.view.MemberView;

/*
 * * Framwork 프레임워크
 * 
 * - 복잡한 코드작업을 일정한 틀과 뼈대를 가지고 보다 쉽게 구현하기 위한 가이드라인
 * - 협업 시 개발속도가 빨라지고, 유지보수도 용이해짐(가이드라인이 제공되기 때문)
 * 
 * [종류]
 * 1. 영속성 프레임워크: JDBC 과정을 편리하게 할 수 있도록 도와주는 프레임워크
 * 2. 자바 프레임워크: 자바언어를 가지고 웹개발을 편리하게 할 수 있도록 도와주는 프레임워크
 * 3. 화면구현 프레임워크: 프론트엔드를 보다 쉽게 짤 수 있도록 도와주는 프레임워크
 * 4. 기능지원 프레임워크: 기타업무에 필요한 기능들을 지원해주는 프레임워크
 * 
 *  * MyBatis 프레임워크
 *  - 기존 JDBC 과정의 복잡함을 해결하기 위해 나온 영속성 프레임워크
 *  - 특히 DAO단의 코드를 줄이는데 크게 기여함
 *  
 *  https://mybatis.org/mybatis-3/ko/getting-started.html
 *  사이트 참고
 *  
 *  * MyBatis 연동방법
 *  1단계) mybatis-x.x.x.jar 파일을 프로젝트 내에 연동해주기(ojdbc11.jar 파일도 같이 연동)
 *  
 *  2단계) XML 설정파일 만들기 mybatis-config.xml
 *  -> 이 프로그램에서 필요로하는 외부파일이므로 resources 폴더에 만들 것
 *  개발자문서(가이드라인)에서 제시한 대로 내가 원하는 설정들을 조합해서 작성
 *  
 *  3단계) SQL문을 실행하기 위해 필요한 SqlSession 객체를 만드는 공통코드 작성하기
 *  -> 개발자문서(가이드라인)에서 제시한 대로 구문 작성해야 함
 * 
 * => SqlSession 객체에서 제공하는 SQL문들을 실행해주는 메소드를 통해 JDBV 과정을 대체한다
 * 
 * 
 */

public class Run {

	public static void main(String[] args) {
		
		new MemberView().mainMenu();
		

	}

}
