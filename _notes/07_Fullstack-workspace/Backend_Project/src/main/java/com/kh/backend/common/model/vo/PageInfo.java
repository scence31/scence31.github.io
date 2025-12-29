package com.kh.backend.common.model.vo;

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
public class PageInfo {

	private int listCount; // 총 게시글 개수
	private int currentPage; // 현재 페이지 수
	private int pageLimit; // 페이징바에서 최대 페이지 개수
	private int boardLimit; // 한 페이지에 보여야 하는 최대 게시글 개수
	
	private int maxPage; // 페이지 총 개수
	private int startPage; // 페이징바 시작수
	private int endPage; // 페이징바 끝수
	
    private int startRow;	// 현재 페이지에서 조회할 시작 ROWNUM
    private int endRow;	// 현재 페이지에서 조회할 끝 ROWNUM
}
