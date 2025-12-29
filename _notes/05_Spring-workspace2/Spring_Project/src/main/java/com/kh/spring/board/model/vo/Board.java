package com.kh.spring.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Board {
	
	private int boardNo;
	private int boardType;
	private String category; // CATEGORY_NO NUMBER
	// > "10" 게시글 작성 시에는 카테고리 번호, "공통" 조회 시에는 카테고리명
	private String boardTitle;
	private String boardContent;
	private String boardWriter; // BOARD_WRITER NUMBER
	// > "2" 게시글 작성 시에는 회원 번호, "user01" 조회 시에는 작성자의 아이디
	private int count;
	private Date createDate;
	private String status;

	// 사진게시글 목록 조회 시 필요한 썸네일 이미지의 경로와 파일명을 담을 필드 추가
	private String titleImg;
	
}
