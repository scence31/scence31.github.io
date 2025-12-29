package com.kh.backend.notice.model.service;

import java.util.ArrayList;

import com.kh.backend.notice.model.vo.Notice;

// 서비스단을 나타내는 설계도면
// 서비스단 안에는 이런 메소드들이 반드시 있어야 한다고 설계도면을 잡아주는 역할
// 추상메소드 형식으로 설계하면 됨(매개변수, 리턴타입, 메소드명, ..)
public interface NoticeService {
	
	// 앞에 public abstract 생략
	
	// 공지사항 목록조회
	ArrayList<Notice> selectNoticeList();
	
	// 공지사항 상세조회
	Notice selectNotice(int noticeNo);
	
	// 공지사항 작성
	int insertNotice(Notice n);
	
	// 공지사항 수정
	int updateNotice(Notice n);
	
	// 공지사항 삭제
	int deleteNotice(int noticeNo);

}
