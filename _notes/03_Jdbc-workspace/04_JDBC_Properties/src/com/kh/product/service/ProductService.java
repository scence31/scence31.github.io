package com.kh.product.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.product.dao.ProductDao;
import com.kh.product.vo.Product;

public class ProductService {
	
	public ArrayList<Product> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insertProduct(Product p) {
		
		
		  Connection conn = JDBCTemplate.getConnection();
		  
		  int result = new ProductDao().insertProduct(conn, p);
        
		  if (result > 0) {
        	
			  JDBCTemplate.commit(conn);
		  } else {
        	
			  JDBCTemplate.rollback(conn);
		  }
		  
		  JDBCTemplate.close(conn);
		  
		  return result;
	} 
	

	public int updateProduct(Product p) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().updateProduct(conn, p);
		
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	
	/**
	 * 삭제할 상품을 담당하는 메소드(트랜잭션)
	 * @param prCode => 삭제할 상품코드입
	 * @return => 처리된 행의 개수
	 */
	public int deleteProduct(String prCode) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().deleteProduct(conn, prCode);
		
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	} // deleteProduct 메소드 끝.
	
	
	/**
	 * 검색 품명을 담당하는 메소드(트랜잭션)
	 * @param nameKeyword => 조회할 품명
	 * @return => 처리된 행의 개수
	 */
	public ArrayList<Product> selectByName(String nameKeyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectByName(conn, nameKeyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	} // selectByName 메소드 끝.
	
	/**
	 * 검색 품명을 담당하는 메소드(트랜잭션)
	 * @param brandKeyword => 조회할 브랜드명
	 * @return => 처리된 행의 개수
	 */
	public ArrayList<Product> selectByBrand(String brandKeyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectByBrand(conn, brandKeyword);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	}

}
