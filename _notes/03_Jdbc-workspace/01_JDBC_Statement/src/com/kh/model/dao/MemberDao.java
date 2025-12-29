package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

/*
	 * * DAO(Data Access Object) 데이터 접근 객체
	 * 
	 * - 데이터가 담겨있는 외부매체와 연결해서 데이터의 입출력을 담당하는 클래스
	 * - Controller로부터 전달받은 값을 가지고 입출력을 진행
	 * 
	 * -> 우린 앞으로 데이터 저장 외부매체로 DB를 쓸 것임
	 * ★ JDBC 과정을 DAO 단에서 수행할 것
	 */

/*
 * * 외부매체와 입출력하기 위해서는??
 * 1. 통로열기
 * 2. 볼일보기
 * 3. 통로닫기
 * 
 * * JDBC용 객체: 통로를 열고 볼일볼 보는 전반적인 과정에서 필요한 객체들
 * - Connection: DB의 연결정보를 담고있는 객체.
 * 				 그 정보를 담아 Connection 객체를 생성하는 순간 DB와 접속됨(연결통로 엶)
 * 
 * - (Prepared)Statement: 해당 접속된 DB에 SQL문을 전달하고
 * 						  실행한 후 결과를 받아내는 객체(볼일을 봄)
 * 
 * - ResultSet: 만약 실행한 SQL문이 SELECT문일 경우 조회된 데이터들이 담겨서 돌아오는 객체
 * 
 * * JDBC 처리순서
 * 1단계) JDBC Driver 등록
 * => 해당 DBMS가 제공하고 있는 클래스를 등록
 * 
 * 2단계) Connection 객체 생성 및 트랜잭션 관련 설정(DML문일 경우에만 함, 자동커밋 해제)
 * => 접속하고자 하는 DB 정보를 담아서 Connection 객체생성(== DB 접속)
 * 
 * 3단계) Statement 객체 생성
 * => Connection 객체를 가지고 생성해야함
 * 
 * 4단계) SQL문을 전달하면서 실행
 * => Statement 객체를 이용해서 SQL문 전달 후 실행
 * -> SELECT문을 실행할 경우: executeQuery 메소드로 실행
 * -> DML문의 경우(INSERT, UPDATE, DELETE): executeUpdatae 메소드로 실행
 * 
 * 5단계) 결과 받기
 * -> SELECT문의 경우: ResultSet객체로(조회된 데이터들이 담겨있음) 받기 ==> 6-1단계로 이동
 * -> DML문의 경우: int로(삽입/수정/삭제된 행의 개수) 받기 ==> 6-2단계로 이동
 * 
 * 6-1단계) ResultSet에 담긴 조회된 데이터들을 하나씩 뽑아서 VO 객체에 담기
 * 
 * 6-2단계) 트랜잭션 처리(성공시 COMMIT, 실패시 ROLLBACK)
 * 
 * 7단계) 다 쓴 JDBC용 자원 생성 역순으로 반납 (close)
 * 
 * 8단계) Controller로 결과 반환
 * -> SELECT문의 경우: 6-1단계에서 만들어진 결과 return;
 * -> DML문의 경우: int(처리된 행의 개수) return;
 * 
 */

/*
 * ** Statement 특징: 완성된 SQL문을 실행시켜야 함!!
 * 
 * 
 */


public class MemberDao {
	
	/**
	 * 사용자가 회원추가 요청시 입력했던 값들을 가지고
	 * MEMBER 테이블에 INSERT문을 실행해주는 메소드
	 * @param m => 사용자가 입력했던 아이디 ~ 주소까지의 값들이 담겨있는 Member 객체
	 * @return => INSERT된 행의 개수(int 값)
	 */
	public int insertMember(Member m) {
		// insert문 => DML문임, int => 처리된 행의 개수 => 트랜잭션 처리
		
		// 0단계)
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL"
									+ ", '" + m.getUserId() + "'"
									+ ", '" + m.getUserPwd() + "'"
									+ ", '" + m.getUserName() + "'"
									+ ", '" + m.getGender() + "'"
									+ ", " + m.getAge()
									+ ", '" + m.getEmail() + "'"
									+ ", '" + m.getPhone() + "'"
									+ ", '" + m.getAddress() + "'"
									+ ", DEFAULT)";
	
		// 1단계)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2-1단계)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 2-2단계)
			conn.setAutoCommit(false);
			
			// ---연결통로 열림---
			
			// 3단계)
			stmt = conn.createStatement();
			
			// 4, 5단계)
			result = stmt.executeUpdate(sql);
			
			// 6-2단계)
			if(result > 0) {
				
				conn.commit();
				
			} else {
				
				conn.rollback();
			}
			
			// -- 볼일 끝 --
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
			// 7단계)
		} finally {
			
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
		
	} // insertMember 메소드 끝
	
	/**
	 * 사용자가 회원전체조회 요청시
	 * MEMBER 테이블에 SELECT * 구문을 실행하는 메소드
	 * @return => 조회된 회원의 정보들이 담겨있는 리스트
	 */
	public ArrayList<Member> selectAll() {
		
		// 0단계)
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		// 1 ~ 5단계)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			// 6-1단계)
			while(rset.next()) { // 여러행 == while문 사용
				
				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				list.add(m);
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
	
	/**
	 * 사용자가 아이디 검색 기능을 요청했을 때
	 * MEMBER 테이블에 SELECT문을 실행해주는 메소드
	 * @param userId => 검색할 회원 아이디(검색어)
	 * @return => 검색된 한 명의 회원정보(단일행 조회)
	 */
	public Member selectByUserId(String userId) {
		
		Member m = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		/*
		SELECT *
		  FROM MEMBER
		 WHERE USERID = 'XXX';
		*/
		String sql = "SELECT * "
					 + "FROM MEMBER "
					 + "WHERE USERID = '" + userId + "'";
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
				
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(sql);
				
				if(rset.next()) { // 아래 참고(단일행 == if문 사용)
					
					m = new Member(rset.getInt("USERNO"),
								   rset.getString("USERID"),
								   rset.getString("USERPWD"),
								   rset.getString("USERNAME"),
								   rset.getString("GENDER"),
								   rset.getInt("AGE"),
								   rset.getString("EMAIL"),
								   rset.getString("PHONE"),
								   rset.getString("ADDRESS"),
								   rset.getDate("ENROLLDATE"));
					
				}
				
				// 이 시점 기준으로 조회된 회원 정보가 없다면, m == null
				
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
			
			return m;

		
	} // selectByUserId 메소드 끝
	
	/**
	 * 사용자가 회원명 키워드 검색요청시 
	 * MEMBER 테이블에 SELECT문 실행해주는 메소드
	 * @param keyword => 검색할 회원명
	 * @return => 검색된 회원들의 정보(목록) 리스트
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		/*
		 SELECT *
  		   FROM MEMBER
 		  WHERE USERNAME LIKE '%XXX%';
		 
		 */
		String sql = "SELECT * FROM MEMBER " + "WHERE USERNAME LIKE '%" + keyword + "%'"; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
			list.add(new Member(rset.getInt("USERNO"),
									rset.getString("USERID"),
									rset.getString("USERPWD"),
									rset.getString("USERNAME"),
									rset.getString("GENDER"),
									rset.getInt("AGE"),
									rset.getString("EMAIL"),
									rset.getString("PHONE"),
									rset.getString("ADDRESS"),
									rset.getDate("ENROLLDATE")));
				
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
	} // selectByUserName 메소드 끝.
	
	/**
	 * MEMBER 테이블에 UPDATE문을 실행해주는 메소드
	 * @param M => 변경할 회원 정보들이 들어있는 객체
	 * @return => 수정된 행의 개수(처리된 행의 개수)
	 */
	public int updateMember(Member m) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		/*UPDATE MEMBER
   			 SET USERPWD = 'XXXX'
     		   , EMAIL = 'XXXXX'
     		   , PHONE = 'XXXXXX"
     		   , ADDRESS = 'XXXXX'
 		   WHERE USERID = 'XXX';
		 */
		
		String sql =
			    "UPDATE MEMBER " +
			    "SET USERPWD = '" + m.getUserPwd() + "', " +
			    "EMAIL = '" + m.getEmail() + "', " +
			    "PHONE = '" + m.getPhone() + "', " +
			    "ADDRESS = '" + m.getAddress() + "' " +
			    "WHERE USERID = '" + m.getUserId() + "'";
		
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
	} // updateMember 메소드 끝.
	
	/**
	 * 사용자가 회원탈퇴 요청시
	 * MEMBER 테이블로부터 한 행을 DELETE 해주는 메소드
	 * @param userId => 탈퇴시킬 회원의 아이디
	 * @return -> 삭제된 행의 개수(처리된 행의 개수)
	 */
	public int deleteMember(String userId) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		/*
		 * DELETE 
  			 FROM MEMBER
 			WHERE USERID = 'XXX';
		*/
		
		String sql = "DELETE " + "FROM MEMBER " + "WHERE USERID = '" + userId + "'"; 
		
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
	} // deleteMember 메소드 끝.

}


// -- insertMember 메소드 --

// 0단계) 필요한 변수들 먼저 세팅
// result: 처리된 행의 개수를 담아줄 변수
// conn: 접속할 DB 정보를 담아 DB에 접속해줄 변수
// stmt: SQL문을 전달 후 실행할 용도의 변수
// sql: 실행할 SQL문 또한 완성된 형태로 변수로 미리 세팅, 세미콜론 있으면 안됨
/*
INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL
        , 'XXX'
        , 'XXXX'
        , 'XXX'
        , 'X'
        , XX
        , 'XXXX'
        , 'XXXXX'
        , 'XXXXX'
        , DAFAULT);
*/

// 1단계) JDBC Driver 등록. try ~ catch 포함, 모두 안에서 생성
// [표현법]
// Class.forNAme("oracle.jdbc.driver.OracleDriver")
// -> ojdbc11.jar 파일에서 제송하는 oracle.jdbc.driver 패키지의
// OracleDriver 클래스를 등록해서 쓰겠다.
// 오타 or 연동 안되면 ClassNotFoundException 오류 발생할 수 있음.

// 2-1단계) Connection 객체 생성
// > Connection 객체 생성시 DB 접속과 관련된 정보들을 넘겨야 함.
// [표현법]
// (Connection) conn = DriverManager.getConnection(url주소, 계정명, 비밀번호);
// Connection 객체가 생성되는 순간, DB 접속이 이루어짐

// 2-2단계) 트랜잭션 설정(DML문을 실행할 경우만 해주면 됨.)
// DNL문 실행 후 개발자인 내가 직접 커밋/롤백 해주겠다. 자동커밋X

// ---연결통로 열림---

// 3단계) Statement 객체 생성(Connection 객체를 통해 생성해야됨)
// [표현법]
// (Statement) stmt = conn.createStatement();

// 4, 5단계) DB에 완성된 SQL문을 전달하면서 실행 후 결과받기
// executeUpdate 메소드 호출(실행할 SQL문 종류가 INSERT / SELECT)
// [표현법]
// INSERT==>  (int) result = stmt.executeUpdate(sql문);
// SELECT==>  (ResultSet) rset = stmt.executeQrery(sql문);
// INSERT 제대로 되었으면 1, 안되었으면 0

// 6-2단계) 트랜잭션 처리
// 성공이면(if result > 0) conn.commit();, 실패면(else) conn.rollback();

// 7단계) 다 쓴 JDBC용 자원들 반납(필수)
// finally 블록에서 진행, 생성된 순서의 역순으로 close();
// conn --> stmt 순서로 생성됨

// 8단계) Controller 결과 반환
// return result; >> 처리된 행의 개수 반환 



// ===============================================================



// -- selectAll 메소드 --
// select문 => ResultSet 객체(여러행 조회) => ArrayList<Member>

// 0단계) 필요한 변수들 먼저 세팅
// 조회된 결과들을 뽑아서 최종적으로 담을 변수(텅 빈 리스트 생성)
// conn: 접속할 DB 정보를 담아 DB에 접속해줄 변수
// stmt: SQL문을 전달 후 실행 후 결과를 받아줄 변수
// rset: SELECT문의 실행 결과가 담겨올 변수
// sql: 실행할 SQL문(완성된 형태로, 세미콜론 X)

// 1단계) 위와 동일
// 2-1단계) 위와 동일
// 2-2단계) SELECT문이라 트랜잭션 설정 스킵
// 3단계) 위와 동일

// 4, 5단계) 위와 동일. 근데 executeUpdate / executeQuery 차이

// 6-1단계) ResultSet의 결과를 하나씩 뽑아서 VO 객체에 옮겨담기
// ResultSet 또한 JDBC용 자원이라서 finally에서 반납해야 함.
// 반납을 통해 데이터가 날아가기 전에 다른 변수에 서둘러 옮기는 개념
//
// * ResultSet 객체에는 내부적으로 커서가 존재함
// 커서: 현재 행의 위치를 나타내는 화살표같은 존재
// SELECT문을 통한 조회 직후 커서는 항상 ResultSet의 0번째 행을 가리킴.
// 매번 커서를 한줄(한 행) 아래로 내려가 그 커서가 가리키는 행에 데이터 유무를 검사 후
// 있으면 각 컬럼의 값을 뽑아서 VO의 필드로 가공해주는 단계
// 더이상 뽑을 값이 없을 때까지 반복수행.
//
// while(rset.next())문 사용
// rset.next(): 커서를 한 행 아래로 움직여주고, 해당 행에 데이터가 존재하면 true or false
// while문 중괄호 안으로 들어왔다는 뜻은, 현재 커서가 가리키는 행의 데이터가 있다는 것.
// --> 컬럼값을 다 뽑아서 VO 객체의 필드로 옮겨담기
//
// Member m = new Member(); --> 한 행의 데이터 == VO 객체 한 개
//
// ResultSet 객체로부터 어떤 컬럼에 해당하는 값을 뽑을건지 매개변수로 제시
// rset.getInt(매개변수) getString, getDate, getDouble... 이렇게 제시하면
// > 컬럼값을 int로, String으로, java.sql.Date로, double형 값으로 뽑아줌
// 각 타입별로 컬럼값들을 뽑아낼 수 있는 메소드들임.
// 매개변수: 컬럼명(대소문자 안가림) 또는 별칭(대소문자 안가림) 또는 컬럼순번(권장x)
// 한 행의 모든 데이터 값을 옮겨담는 과정 m.set(rset.get(""));
//
// 다음 반복회차로 넘어가기 전에 리스트에 Member 객체 담기. list.add(m);
// 이 기준으로 이제 list에 조회된 회원든ㄹ의 정보들이 차곡차곡 담겨있음.

// 7단계) 위와 동일. 역순으로 finally close();
// 8단계) 위와 동일 결과 리턴



// ----- selectByUserId -----
// select문이라서 ResultSet 객체, 단일행이라서 Member 객체

// String sql = 이 부분에서, 공백 구분 잘 해주고, 문자열이라서 작은따음표도 표현해야함.

// selectAll처럼 여러행 조회는 정확히 몇 개 조회인지 유추 불가능해서 while(rset.next()) 사용.
// 근데 이번에는 단일행 조회임. 조회시 행의 개수 유추 가능함(1또는 0) -> if(rset.next()) 사용.
// 조회된 데이터가 있으면 if문 안쪽으로 들어옴.
// 조회된 한 행에 대한 데이터값들을 모두 뽑아서 하나의 Member 객체에 담기
