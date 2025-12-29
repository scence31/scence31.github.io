package com.kh.spring.common.template;

import com.kh.spring.common.model.vo.PageInfo;

// 페이징 처리 시마다 필요한 7개의 변수를 셋팅하는 공통 코드
public class Pagination {

	// 4개의 기본변수를 매개변수로 받아서 3개의 변수를 계산해서 PageInfo 로 리턴
	public static PageInfo getPageInfo(int listCount,
								  	   int currentPage,
								  	   int pageLimit,
								  	   int boardLimit) {
		
		// 위의 4개의 변수를 가지고 나머지 3개의 변수를 계산할 것!!
		
		// * maxPage : 가장 마지막 페이지가 몇 번 페이지인지
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// * startPage : 페이지 하단에 보여질 페이징바의 시작수
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// * endPage : 페이지 하단에 보여질 페이징바의 끝수
		int endPage = startPage + pageLimit - 1;
		
		// maxPage 가 곧 endPage 로 되어야 하는 경우
		if(endPage > maxPage) {
			
			endPage = maxPage;
		}
		
		// * startRow : SQL 시작 행(DAO에서 묶어서 처리하기 위함)
		int startRow = (currentPage - 1) * boardLimit + 1;
		
		// * endRow : SQL 끝 행(DAO 묶어서 처리)
		int endRow = startRow + boardLimit -1;
		

		// 매개변수로 받은 변수값 4개 + 계산한 3개 + 계산한 2개
		// = 한번에 PageInfo 객체에 다 담을 수 있음..!
		PageInfo pi = new PageInfo(listCount, currentPage, 
								   pageLimit, boardLimit,
								   maxPage, startPage, endPage,
								   startRow, endRow);
		
		return pi;
	}
	
}
