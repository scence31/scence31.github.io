package com.kh.shoes.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.shoes.common.ShoesTemplate;
import com.kh.shoes.model.dao.ShoesDao;
import com.kh.shoes.model.vo.Shoes;

public class ShoesService {
	
	public int insertShoes(Shoes s) {
		
		SqlSession sqlSession = ShoesTemplate.getSqlSession();
		
		int result = new ShoesDao().insertShoes(sqlSession, s);
		
		if(result > 0) {
			
			sqlSession.commit();
			
		} else {
			
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
		
	} // 추가 메소드 끝.
	
	public ArrayList<Shoes> selectAll() {
		
		SqlSession sqlSession = ShoesTemplate.getSqlSession();
		
		ArrayList<Shoes> list = new ShoesDao().selectAll(sqlSession);
		
		sqlSession.close();
		
		return list;
		
	} // 조회 메소드 끝
	
	public int updateShoes(Shoes s) {
		
		SqlSession sqlSession = ShoesTemplate.getSqlSession();
		
		int result = new ShoesDao().updateShoes(sqlSession, s);

		if(result > 0) {
			
			sqlSession.commit();
			
		} else {
			
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
		
	} // 업데이트 메소드 끝.
	
	public ArrayList<Shoes> selectByShoesName(String keyword) {

		SqlSession sqlSession = ShoesTemplate.getSqlSession();
		
		ArrayList<Shoes> list = new ShoesDao().selectByShoesName(sqlSession, keyword);
		
		sqlSession.close();
		
		return list;
		
	} // 품명검색 메소드 끝.
	
	public Shoes selectByShoesCode(String shoesCode) {

		SqlSession sqlSession = ShoesTemplate.getSqlSession();
		
		Shoes s = new ShoesDao().selectByShoesCode(sqlSession, shoesCode);
		
		sqlSession.close();
		return s;
	} // 코드검색 메소드 끝.
	
	public int deleteShoes(String shoesCode) {

		SqlSession sqlSession = ShoesTemplate.getSqlSession();
		
		int result = new ShoesDao().deleteShoes(sqlSession, shoesCode);
		
		if(result > 0) {	
			sqlSession.commit();
			
		} else {	
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;	
	} // 삭제 메소드 끝.

}
