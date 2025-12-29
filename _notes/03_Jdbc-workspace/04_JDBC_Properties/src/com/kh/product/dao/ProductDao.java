package com.kh.product.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.product.vo.Product;

public class ProductDao {
	

	private Properties prop = new Properties();
	// SQL문을 key=value 형태로 Properties의 prop 객체(Map계열)
	// .xml 외부파일 읽어올 것임.

	public ProductDao() {
		// 기본생성자로로 한 이유: .xml 파일 실행에 대한 코드작성 간편화
		
		try {
			
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
			// resources 파일의 query xml파일 읽어서 prop에 로딩
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // ProductDao 기본생성자 메소드 끝.
	
	public ArrayList<Product> selectAll(Connection conn) {
		
		ArrayList<Product> list = new ArrayList<>();
		
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		
		String sql = prop.getProperty("selectAll");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				
				p.setPrCode(rset.getString("PR_CODE"));
				p.setPrBrand(rset.getString("PR_BRAND"));
				p.setPrName(rset.getString("PR_NAME"));
				p.setPrPrice(rset.getInt("PR_PRICE"));
				p.setPrStock(rset.getInt("PR_STOCK"));
				
				list.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);

		}
		
		return list;
	}
	
	public int insertProduct(Connection conn, Product p) {
		
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");
		
		try {
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, p.getPrCode());
			pstmt.setString(2, p.getPrBrand());
			pstmt.setString(3, p.getPrName());
			pstmt.setInt(4, p.getPrPrice());
			pstmt.setInt(5, p.getPrStock());
						
			result = pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public int updateProduct(Connection conn, Product p) {
	
		int result = 0;
		PreparedStatement pstmt = null;
	
		String sql = prop.getProperty("updateProduct");
	
		try {
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getPrBrand());
			pstmt.setString(2, p.getPrName());
			pstmt.setInt(3, p.getPrPrice());
			pstmt.setInt(4, p.getPrStock());
			pstmt.setString(5, p.getPrCode());
			
			result = pstmt.executeUpdate();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
}
	
	/**
	 * 제품을 DB에서 삭제하는 메소드
	 * @param conn => DB 연결하는 Connection 객체. Service에서 전달
	 * @param prCode => 삭제할 제품 코드
	 * @return => 처리된 행의 개수
	 */
	public int deleteProduct(Connection conn, String prCode) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProduct");
		// query xml파일에 저장해둔 SQL문을 불러오는 구문
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, prCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);

		}

		return result;
		
	} // deleteProduct 메소드 끝.
	
	/**
	 * 검색한 제품을 DB에서 조회하는 메소드
	 * @param conn => DB 연결하는 Connection 객체. Service에서 전달
	 * @param nameKeyword => 품명 검색 키워드
	 * @return
	 */
	public ArrayList<Product> selectByName(Connection conn, String nameKeyword) {
		
		ArrayList<Product> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + nameKeyword + "%");
			// LIKE로 인해 %% 처리, 자동 문자열로 변환되기 때문에 +로 연결
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// rset은 while구문으로 작성
				
				list.add(new Product(rset.getString("PR_CODE"),
									 rset.getString("PR_BRAND"),
									 rset.getString("PR_NAME"),
									 rset.getInt("PR_PRICE"),
									 rset.getInt("PR_STOCK")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	} // selectByName 메소드 끝.
	
	/**
	 * 검색한 제품을 DB에서 조회하는 메소드
	 * @param conn => DB 연결하는 Connection 객체. Service에서 전달
	 * @param brandKeyword => 브랜드명 검색 키워드
	 * @return
	 */
	public ArrayList<Product> selectByBrand(Connection conn, String brandKeyword) {
		
		ArrayList<Product> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByBrand");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + brandKeyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Product(rset.getString("PR_CODE"),
						 rset.getString("PR_BRAND"),
						 rset.getString("PR_NAME"),
						 rset.getInt("PR_PRICE"),
						 rset.getInt("PR_STOCK")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	} // selectByBrand 메소드 끝.
}



