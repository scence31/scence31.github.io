package com.kh.backend.member.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.backend.member.model.service.MemberService;
import com.kh.backend.member.model.vo.Member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="MemberController", description="회원 관리 Controller API") // 클래스 설명
@RestController // == Controller + ResponseBody
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	// 내부적으로 객체 생성해서 주입할 때 인터페이스 타입이면 알아서 구현체 찾고, 자식타입 자동형변환(UpCasting), 다형성
	private MemberService memberService;
	
	// 회원목록 조회용 컨트롤러
	@Operation(summary="회원목록조회 메소드", description="요청변수 없이 전체 회원목록을 조회해서 반환") // 메소드에 대한 설명
	@GetMapping("list")
	public ArrayList<Member> selectMemberList() {
		
		// System.out.println("회원목록조회요청 도달");
		
		ArrayList<Member> list = memberService.selectMemberList();
		
		log.debug("?");
		
		return list;
		
	}
	
	// 회원상세조회용 컨트롤러
	@Operation(summary="회원상세조회 메소드", description="ㅋ")
	@GetMapping("detail/{userId}")
	public Member selectMember(@PathVariable String userId) {
		
		// System.out.println(userId);
		// System.out.println("상세조회 도달");
		
		Member m = memberService.selectMember(userId);
		
		// System.out.println(m);
		
		return m;
	}
	
	// 회원정보수정용 컨트롤러
	@Operation(summary="회원정보수정 메소드", description="수정할 회원 정보를 요청변수로 받아 해당 회원 정보 수정 후 반환")
	@ApiResponse(responseCode="200", description="OK", 
				content=@Content(mediaType="text/plain", schema=@Schema(type="string", example="회원정보수정 성공"))) // 응답에 대한 설명, 기본값- 200 / OK
	@PostMapping("update")
	public String updatMember(@RequestBody Member m) {
		
		// System.out.println(m);
		
		int result = memberService.updateMember(m);
		
		return (result > 0) ? "회원정보수정 성공" : "수정실패";
	}
	
	// 회원탈퇴용 컨트롤러
	@Operation(summary="회원탈퇴 메소드", description="탈퇴한 회원의 아이디를 요청변수로 받아 탈퇴하고 결과메시지를 반환")
	@ApiResponse(responseCode="200", description="OK",
				content=@Content(mediaType="text/plain", schema=@Schema(type="string", example="회원정보탈퇴 성공")))
	@PostMapping("delete")
	public String deleteMember(@RequestBody Member m) {
		
		// System.out.println(userId);
		// {"userId":"user01"}
		// 객체인 척하는 모양으로 전달값이 넘어옴
		
		// 해결방법 - 전달값을 String userId 말고 Member m으로 받기
		// System.out.println(m.getUserId());
		
		int result = memberService.deleteMember(m.getUserId());
		
		return (result > 0) ? "회원탈퇴 성공" : "실패";
	}
	

}












