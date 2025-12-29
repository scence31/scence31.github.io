package com.kh.backend.member.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.backend.member.model.dao.MemberDao;
import com.kh.backend.member.model.vo.Member;

/*
 * * 구현체 클래스
 * - 미완성된 추상클래스의 코드를 상속받아서 구현(완성)하는 클래스
 * - 주로 구현체클래스명은 기존인터페이스명 뒤에 Impl 접미사를 붙임
 * 
 * 팀원들은 이 MemberServiceImpl 만들고 베포받은 MemberService 인터페이스를 상속받은 후 구현하면 됨
 * 모든 팀원들이 같은 작업환경에서 코드만 채워서 작업하는 모양이 됨.(훨씬 효율적)
 * (인터페이스가 구현의 강제성을 준다는 점을 이용해서 협업에 많이 쓰임)
 * 
 */
@Service // 구현체 클래스 상단에 어노테이션 빈 등록
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Member> selectMemberList() {
		
		return memberDao.selectMemberList(sqlSession);
	}

	@Override
	public Member selectMember(String userId) {
		
		return memberDao.selectMember(sqlSession, userId);
	}

	@Transactional
	@Override
	public int updateMember(Member m) {
		
		return memberDao.updateMember(sqlSession, m);
	}

	@Transactional
	@Override
	public int deleteMember(String userId) {
		return memberDao.deleteMember(sqlSession, userId);
	}

}
