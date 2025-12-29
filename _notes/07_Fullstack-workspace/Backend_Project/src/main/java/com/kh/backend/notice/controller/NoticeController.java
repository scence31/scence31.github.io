package com.kh.backend.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.backend.notice.model.service.NoticeService;
import com.kh.backend.notice.model.vo.Notice;

@RestController
@RequestMapping("notice")
@CrossOrigin(origins="http://localhost:5173")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService; // 의존성 주입으로 구현체 자동추적
	
	// 공지사항 목록조회
	@GetMapping("list")
	public ArrayList<Notice> selectNoticeList() {
		
		return noticeService.selectNoticeList();
		
		
	}
	
	// 공지사항 상세조회
	@GetMapping("detail/{noticeNo}")
	public Notice selectNotice(@PathVariable int noticeNo) {
		
		return noticeService.selectNotice(noticeNo);
		
	}
	
	// 공지사항 작성용
	@PostMapping("insert")
	public String insertNotice(@RequestBody Notice n) {
		
		// System.out.println(n);
		
		// XSS 공격 방지 참고하기
		
		int result = noticeService.insertNotice(n);
		
		return (result > 0) ? "작성성공" : "작성실패";
	}
	
	// 공지사항 수정용
	@PostMapping("update")
	public String updateNotice(@RequestBody Notice n) {
		
		int result = noticeService.updateNotice(n);
		
		return (result > 0) ? "수정완료" : "수정실패~";
	}
	
	// 공지사항 삭제
	@PostMapping("delete")
	public String deleNotice(@RequestBody Notice n) {
		
		int result = noticeService.deleteNotice(n.getNoticeNo());
		
		return (result > 0) ? "삭제성공" : "삭제실패";
	}
	

}
