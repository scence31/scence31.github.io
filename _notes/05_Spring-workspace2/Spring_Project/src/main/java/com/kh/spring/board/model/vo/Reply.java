package com.kh.spring.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
// @AllArgsConstructor로 매개변수생성자 만들지 않은 이유
// 스프링에서는 직접 VO 객체를 만들 필요 없어서
public class Reply {
	
	private int replyNo;
	private String replyContent;
	private int refBno;
	private String replyWriter; // "1" / "admin"
	private String createDate; // 작성일을 TO_CHAR 함수로 조회해서
	private String status;

}
