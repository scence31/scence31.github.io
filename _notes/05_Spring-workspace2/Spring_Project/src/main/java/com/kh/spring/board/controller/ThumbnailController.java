package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.template.FileRenamePolicy;

import jakarta.servlet.http.HttpSession;

// 우린 사진게시판도 BOARD 테이블을 이용할 것임!!
// 단, 요청 시 구분을 위해 URL 주소 상에 thumbnail 이라는 공용 주소로 요청할 것!!
// Controller 만 board.controller 패키지에 따로 만들어 줌!!
// 또한 나머지 Service, Dao, mapper 는 기존 Board 것으로 쓸 것임!!
// (결국 BOARD 테이블에 CRUD 를 해야하니깐)

@Controller
@RequestMapping("thumbnail")
public class ThumbnailController {
	
	@Autowired
	private BoardService boardService;
	
	// 사진게시판 목록 조회 요청 처리 메소드
	@GetMapping("list")
	public String selectThumbnailList(Model model) {
		
		// 그 목록 조회 화면에서 보여져야 하는 게시글들의 정보를 조회해오기
		// > 우리는 아직 사진게시글 더미데이터가 하나도 없기 때문에!!
		//   오늘은 하드코딩된 화면만 띄워줄것!! (작성 기능 먼저 구현)
		
		// 사진게시판 리스트페이지에서 필요한 데이터를 조회해올 것
		// > 글번호, 제목, 조회수, 썸네일이미지의경로 및 수정파일명
		ArrayList<Board> list = boardService.selectThumbnailList();
		
		model.addAttribute("list", list);
		
		// 목록 조회 화면만 띄워주면 끝!!
		return "board/thumbnailListView";
		// /WEB-INF/views/board/thumbnailListView.jsp
		
	}
	
	// 사진게시글 작성 페이지 요청 처리 메소드
	@GetMapping("enrollForm")
	public String enrollForm() {
		
		// 사진게시글 작성 페이지만 띄워주고 끝
		return "board/thumbnailEnrollForm";
		// /WEB-INF/views/board/thumbnailEnrollForm.jsp
		
	}
	
	// 사진게시글 작성 요청 처리 메소드
	@PostMapping("insert")
	public String insertThumbnail(Board b, MultipartFile[] files, 
								  HttpSession session, Model model) {
		
		// Board b : 제목, 내용, 작성자의 회원번호
		// > 이따가 BOARD 테이블에 INSERT 해올 것!!
		
		// MultipartFile[] files : files 키값으로 넘겨진 첨부파일"들"
		// > 일반적인 텍스트 상자나 입력 요소 (체크박스 제외) 에도
		//   name 속성값을 동일하게 줘서 한번에 여러 데이터를 넘길 수 있음!!
		//   이 경우, request.getParameterValues("키값") : []
		//   로 뽑듯이 한번에 그 객체 타입의 배열로 받아낼 수 있다!!
		// > 이 전달된 파일 정보들을 Attachment 객체로 옮겨담고,
		//   (최소1개 ~ 최대4개) ArrayList 에 차곡차곡 담아두기
		// > 이따가 ATTACHMENT 테이블에 INSERT 해올 것!!
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		for(int i = 0; i < files.length; i++) {
			
			// files[i].getOriginalFilename();
			// > i 번째 파일의 "원본파일명" 
			
			if(!files[i].getOriginalFilename().equals("")) {
				// i 번째 첨부파일이 확실하게 잘 넘어온 경우
				// > 파일명 수정 후 서버로 업로드 작업 (공통코드)
				String changeName = FileRenamePolicy.saveFile(files[i], 
															  session, 
															  "/resources/thumbnail_upfiles/");
				
				// > Attachment 객체로 생성해서 list 에 add
				Attachment at = new Attachment();
				
				at.setOriginName(files[i].getOriginalFilename());
				at.setChangeName(changeName);
				at.setFilePath("resources/thumbnail_upfiles/");
				
				// 파일레벨 셋팅 (썸네일 : 1, 일반상세이미지 : 2)
				// > 썸네일 이미지는 무조건 0 번 방에 들은 파일임!!
				if(i == 0) {
					// 썸네일 이미지일 경우
					
					at.setFileLevel(1);
					
				} else {
					// 일반 상세 이미지일 경우
					
					at.setFileLevel(2);
				}
				
				list.add(at);
			}
			
		}
		
		// b 와 list 를 같이 넘기면서 insert 요청 하기!!
		// > 단, 이 경우에도 모든 insert 구문들을 하나의 트랜잭션으로 묶어야함
		int result = boardService.insertThumbnail(b, list);
		
		// 처리 결과에 따른 응답화면 지정
		if(result > 0) { // 성공
			
			// 세션에 일회성 알람 문구를 담아 사진게시글 목록 조회 화면으로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 사진게시글이 업로드 되었습니다.");
			
			return "redirect:/thumbnail/list";
			
		} else { // 실패
			
			// 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "사진게시글 업로드에 실패했습니다.");
			
			return "common/errorPage";
			
		}
		
	}

	// 사진게시판 상세조회 요청 처리 메소드
	@GetMapping("detail/{boardNo}")
	public String selectThumbnail(@PathVariable(value="boardNo") int bno, 
								  Model model) {
		
		// System.out.println(bno);
		
		// 우선 마찬가지로 조회수 증가 먼저 하고 올 것!!
		// > 일반게시판에서 조회수 증가 메소드와 쿼리문을 이미 만들어 놨음!!
		//   (보니까 재활용이 가능함)
		int result = boardService.increaseCount(bno);
		
		// 조회수 증가에 성공하면 그제서야 단일행 조회 해오기
		if(result > 0) { // 조회수 증가 성공
			
			// 해당 게시글 정보 하나만 조회해오기
			// > 쿼리문을 잘 살펴보니까 일반게시글 조회용 쿼리문을
			//   살짝만 수정하면 재활용 가능함!!
			// > 일반게시판 상세조회용 selectBoard 서비스 재활용
			Board b = boardService.selectBoard(bno);
			
			// 해당 게시글에 딸린 첨부파일"들"도 조회해오기 (여러행 조회)
			// > 기존 일반게시글 상세조회때 만든 첨부파일 상세조회 서비스는
			//   단일행 조회 기준으로 만들어져서 Attachment 객체만 리턴하기 때문에
			//   재활용 불가!!
			ArrayList<Attachment> list = boardService.selectAttachmentList(bno);
			
			// 응답데이터를 담아서 응답페이지로 포워딩
			model.addAttribute("b", b);
			model.addAttribute("list", list);
			
			return "board/thumbnailDetailView";
			// /WEB-INF/views/board/thumbnailDetailView.jsp
			
		} else { // 조회수 증가 실패
			
			// 에러문구 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "사진게시글 상세조회에 실패했습니다.");
			
			return "common/errorPage";
		}
		
	}
	
}






