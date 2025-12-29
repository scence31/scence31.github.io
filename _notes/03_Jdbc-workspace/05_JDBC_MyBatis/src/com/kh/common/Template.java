package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// SqlSession 객체 생성하기(JDBCTemplate의 conn을 간단하게)
// JDBC => getConnection / MyBatis => getSqlSession
// sqlSession = Connection + PreparedStatement 느낌.
// 이 클래스의 코드는 그냥 외워버리는게 나을듯?
public class Template {
	
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		String resource = "mybatis-config.xml";
		
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
