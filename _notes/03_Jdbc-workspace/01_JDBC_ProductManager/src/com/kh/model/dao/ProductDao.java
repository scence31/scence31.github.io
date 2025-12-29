package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Product;

public class ProductDao {
	
	public ArrayList<Product> selectAll() {
		
		ArrayList<Product> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM PRODUCT";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				Product p = new Product();
				
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));
				
				list.add(p);
				
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	} // selectAll 메소드 끝.
	
	public int insertProduct(Product p) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO PRODUCT VALUES" + "('" + p.getProductId() + "', '"
					+ p.getProductName() + "', "
					+ p.getPrice() + ", '"
					+ p.getDescription() + "', "
					+ p.getStock() +")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				
				conn.commit();
			} else {
				
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;

	} // insertProdct 메소드 끝.
	
	public ArrayList<Product> selectByProductName(String keyword) {
		
		ArrayList<Product> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM " + "PRODUCT " + "WHERE PRODUCT_NAME LIKE '%" + keyword + "%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				list.add(new Product(rset.getString("PRODUCT_ID"),
								 	 rset.getString("PRODUCT_NAME"),
									 rset.getInt("PRODUCT_PRICE"),
									 rset.getString("DESCRIPTION"),
									 rset.getInt("STOCK")));
			
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
		
	} // selectByProductName 메소드 끝.
	
	public int updateProduct(Product p) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		/*		UPDATE PRODUCT
				   SET PRODUCT_NAME = 'newProductName'
				     , PRODUCT_PRICE = newPrice
				     , DESCRIPTION = 'newDescription'
				     , STOCK = newStock
				 WHERE PRODUCT_ID = 'productId';
		 */
		
		
		String sql = "UPDATE PRODUCT " + "SET PRODUCT_NAME = " + "'" + p.getProductName() + "'"
					 + ", PRODUCT_PRICE = " + p.getPrice() + ", DESCRIPTION = " + "'" + p.getDescription()
					 + "', STOCK = " + p.getStock() + " WHERE PRODUCT_ID = " + "'" + p.getProductId() + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				
				conn.commit();
				
			} else {
				
				conn.rollback();
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;			 
		
	} // updateProduct 메소드 끝.
	
	public int deleteProduct(String productId) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE " + "FROM PRODUCT " +"WHERE PRODUCT_ID = '" + productId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				
				conn.commit();
				
			} else {
				
				conn.rollback();
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;			
	}

}
