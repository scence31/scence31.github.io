package com.kh.backend.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.backend.board.model.vo.Board;
import com.kh.backend.common.model.vo.PageInfo;

@Repository
public class BoardDao {

	public int selectListCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
	}

	public ArrayList<Board> selectBoardList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		// 1. 인라인뷰
		
		// 2. 마이바티스 RowBounds
		// => 앞에서부터 offset 건너뛰고 limit개만 끊어내는 원리
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", pi);
	}

	public int selectSearchCount(SqlSessionTemplate sqlSession, String keyword) {
		return sqlSession.selectOne("boardMapper.selectSearchCount", keyword);
	}

	public ArrayList<Board> selectSearchList(SqlSessionTemplate sqlSession, PageInfo pi, String keyword) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("startRow", pi.getStartRow());
		map.put("endRow", pi.getEndRow());
		map.put("keyword", keyword);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map);
	}

	public int insertBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}

	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}

	public String selectOriginFileName(SqlSessionTemplate sqlSession, String fileName) {
		return sqlSession.selectOne("boardMapper.selectOriginFileName", fileName);
	}

	public int updateBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.update("boardMapper.updateBoard", b);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.deleteBoard", boardNo);
	}

}
