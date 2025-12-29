package com.kh.backend.board.model.service;

import java.util.ArrayList;

import com.kh.backend.board.model.vo.Board;
import com.kh.backend.common.model.vo.PageInfo;

public interface BoardService {
	
	// 1. 게시글 목록 조회 + 페이징
	// 1-1. 게시글 총 개수
	int selectListCount();
	
	// 1-2. 실제 게시글 목록을 조회
	ArrayList<Board> selectBoardList(PageInfo pi);
	
	// 2. 게시글 검색 + 페이징
	// 2-1. 총 검색된 게시글 개수를 구하는 서비스
	int selectSearchCount(String keyword);
	
	// 2-2. 총 검색된 게시글 목록을 조회해주는 서비스
	ArrayList<Board> selectSearchList(PageInfo pi, String keyword);
	
	// 3. 게시글 작성
	int insertBoard(Board b);
	
	// 4. 게시글 상세조회
	// 4-1. 조회수 증가용
	int increaseCount(int boardNo);
	
	// 4-2. 게시글 하나 조회
	Board selectBoard(int boardNo);

	// 5. 게시글 수정
	int updateBoard(Board b);
	
	// 6. 게시글 삭제
	int deleteBoard(int boardNo);

	// 원본파일명 조회
	String selectOriginFileName(String fileName);
	
	// 댓글 목록 조회
	
	// 댓글 작성
}
