package com.kh.shoes.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ShoesTemplate {
	
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		String resource = "shoes-config.xml";
		
		try {
			
			InputStream inputStream = Resources.getResourceAsStream(resource);
		
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			sqlSession = sqlSessionFactory.openSession(false);
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
		
	}


}
