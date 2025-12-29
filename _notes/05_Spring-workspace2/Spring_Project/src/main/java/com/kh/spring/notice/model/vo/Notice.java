package com.kh.spring.notice.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 매 번 VO 클래스 작성 시 필드부, 생성자부, 메소드부를 직접 기술하기 귀찮음!!
 * > 이클립스의 자동완성 기능이 있다 하더라도 귀찮기 때문에 코드를 자동완성 해주는 라이브러리를 이용해보자!!
 * 
 * * Lombok (롬복)
 * 
 * - VO 클래스의 코드 등을 자동으로 생성해줄 수 있는 라이브러리
 * - 주로, 반복되는 생성자, getter / setter, toString 등의 메소드 작성 구문을 줄여준다. 
 * - 코드를 줄여주는 라이브러리다 라고 해서 "코드 다이어트 라이브러리" 라고도 부름!!
 * 
 * * Lombok 라이브러리 연동 방법
 * 
 * 1. 라이브러리 파일 (.jar) 다운로드 후 프로젝트에 적용 - Maven (pom.xml)
 * 2. 다운로드 된 jar 파일을 찾아서 "실행" 후 STS 에 설치
 * 3. IDE 종료 후 재구동
 * 
 * * Lombok 라이브러리 사용 방법
 * 
 * - 필드부는 직접 작성한 후, 롬복 라이브러리에서 제공하는 어노테이션들을 이용하여 코드를 자동완성 한다.
 * - 그 어노테이션들은 클래스 선언부 상단에 작성한다!!
 * - 실제로 해당 코드들이 없는건 맞는데, STS 한테 코드가 있다고 속여서 작업하는 동작 원리임!!
 * 
 * 주의사항)
 * - 협업 시 롬복을 사용하고 싶다면 모든 팀원이 다 같이 사용해야함!!
 * - 필드명 작성 시 앞글자가 외자인 필드명은 만들지 말 것!!
 *   uId (X) --> userId (O)
 *   uPwd (X) --> userPwd (O)
 *   uId 필드의 setter 는 명명 규칙에 따라 원래는 setuId 로 지어야 하는데
 *   롬복은 해당 필드의 setter 메소드 명을 setUId 로 지어주기 때문에 나중에 스프링이나 EL 구문 등에서 충돌남!!
 */

@NoArgsConstructor // 매개변수가 없는 기본생성자를 자동완성해줌
@AllArgsConstructor // 모든 필드에 대해 매개변수로 갖는 생성자를 자동완성해줌
@Setter // 필드별로 setter 메소드들을 자동완성해줌
@Getter // 필드별로 getter 메소드들을 자동완성해줌
@ToString // toString 메소드를 자동으로 오버라이딩해줌
public class Notice {

	private int noticeNo;		 	 //	NOTICE_NO	NUMBER
	private String noticeTitle; 	 //	NOTICE_TITLE	VARCHAR2(100 BYTE)
	private String noticeContent; 	 //	NOTICE_CONTENT	VARCHAR2(4000 BYTE)
	private String noticeWriter;	 //	NOTICE_WRITER	NUMBER
	// "admin" 조회 시 회원의 아이디로 / "1" 작성 시 회원 번호
	private int count;				 //	COUNT	NUMBER
	private Date createDate;		 //	CREATE_DATE	DATE
	private String status;			 //	STATUS	VARCHAR2(1 BYTE)
	
}
