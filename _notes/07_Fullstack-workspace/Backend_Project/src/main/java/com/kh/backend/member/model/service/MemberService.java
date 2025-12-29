package com.kh.backend.member.model.service;

import java.util.ArrayList;

import com.kh.backend.member.model.vo.Member;

/*
 * * 인터페이스
 * 모든 필드가 상수필드이고, 모든 메소드가 추상메소드인 일종의 추상클래스
 * 추상메소드는 머리부만 있고 몸통부(실재구현코드)는 없는 미완성된 메소드
 * 반드시 오버라이딩 한 누군가가 재정의해서 완성해야만 호출해서 쓸 수 있음
 * 
 * - 실무에서는 추상메소드와 인터페이스를 협업할 때 많이 사용함
 * 팀의 리더 PM(product manager)이 인터페이스에 추상메소드로만 미리 만들어서 팀원들에 베포.
 * 팀원들은 이 전달받은 인터페이스를 상속받아서 구현 후 쓰면 됨(코드 통일성 부여, 병합 시 더 용이함)
 * 
 */
public interface MemberService {

	// 회원목록조회용 서비스
	public abstract ArrayList<Member> selectMemberList();
	
	// 회원상세조회용 서비스 public abstract 생략
	Member selectMember(String userId);
	
	// 회원정보수정용 서비스
	int updateMember(Member m);
	
	// 회원탈퇴용 서비스
	int deleteMember(String userId);
}
