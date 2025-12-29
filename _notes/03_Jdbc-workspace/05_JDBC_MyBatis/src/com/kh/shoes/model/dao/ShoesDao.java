package com.kh.shoes.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.shoes.model.vo.Shoes;

public class ShoesDao {
	
	public int insertShoes(SqlSession sqlSession, Shoes s) {
		
		return sqlSession.insert("shoesMapper.insertShoes", s);
	}
	
	public ArrayList<Shoes> selectAll(SqlSession sqlSession) {
		
		return (ArrayList)sqlSession.selectList("shoesMapper.selectAll");
	}
	
	public int updateShoes(SqlSession sqlSession, Shoes s) {
		
		return sqlSession.update("shoesMapper.updateShoes", s);
	}
	
	public ArrayList<Shoes> selectByShoesName(SqlSession sqlSession, String keyword) {
		
		return (ArrayList)sqlSession.selectList("shoesMapper.selectByShoesName", keyword);
	}
	
	public Shoes selectByShoesCode(SqlSession sqlSession, String shoesCode) {
		
		return sqlSession.selectOne("shoesMapper.selectByShoesCode", shoesCode);
	}
	
	public int deleteShoes(SqlSession sqlSession, String shoesCode) {
		
		return sqlSession.delete("shoesMapper.deleteShoes", shoesCode);
	}
	
	

}
