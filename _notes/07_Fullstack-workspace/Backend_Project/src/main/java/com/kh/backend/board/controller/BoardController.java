package com.kh.backend.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.backend.board.model.service.BoardService;
import com.kh.backend.board.model.vo.Board;
import com.kh.backend.common.model.vo.PageInfo;
import com.kh.backend.common.template.FileRenamePolicy;
import com.kh.backend.common.template.Pagination;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService; // 부모자식 다형성 적용 -> 의존성주입
	
	// 일반 게시글 목록 조회용
	@Operation(summary="게시글 목록 조회", description="요청 페이지 수를 요쳥변수로 전달받아 해당 페이지의 게시글 목록 조회 후 반환")
	@ApiResponse(responseCode="200", description="OK",
				content=@Content(mediaType="application/json", schema=@Schema(example="""
																						{
																							"pi : {"listCount" : 107,
																									"currentPage" : 1,
																									"pageLimit" : 5,
																									"boardLimit" : 5,
																									"maxPage" : 22,
																									"startPage" : 1,
																									"endPage" : 5,
																									"startRow" : 1,
																									"endRow" : 10
																									},
																							"list" : [
																									{ "boardNo" : 1,
																									"boardTitle" : 제목,
																									"boardWriter" : 작성자,
																									"boardContent" : 내용,
																								}
																							
																							
																									]
																						}
																					""")))
	@GetMapping("list")
	public HashMap<String, Object> selectBoardList(@RequestParam(value="cpage") int currentPage) {
		
		// System.out.println(currentPage);
		
		// 목록조회 + 페이징처리
		// PageInfo 변수 4개 세팅하기
		int listCount = boardService.selectListCount();
		
		// System.out.println(listCount); // 17(DB 참고)
		
		// currentPage는 이미 위에서 요청시 전달값으로 받아냄.
		
		// 한 페이지당 페이징바 숫자 및 게시글 5개씩 지정
		int pageLimit = 5;
		int boardLimit = 5;
		
		// 위 4개 변수로 나머지 변수 계산 후 한 번에 PageInfo로 담기
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		// System.out.println(pi);
		ArrayList<Board> list = boardService.selectBoardList(pi);
		
		//	for(Board b : list) {
		//		System.out.println(b);
		//	}
		
		// list, pi 두 개의 값을 하나로 뭉쳐서 리턴해야 함
		// => HashMap (자바스크립트의 객체와 유사한 구조 - JSONObject)
		HashMap<String, Object> hm = new HashMap<>(); // 밸류값으로 PageInfo, ArrayList 다 넘길 수 있도록 다형성 적용
		
		hm.put("pi", pi);
		hm.put("list", list);
		
		return hm;
	}
	
	// 일반게시글 검색용
	@GetMapping("search")
	public HashMap<String, Object> searchBoardList(String keyword, @RequestParam(value="cpage") int currentPage) {
		
		System.out.println(keyword);
		System.out.println(currentPage);
		
		int searchCount = boardService.selectSearchCount(keyword);
		
		int pageLimit = 5;
		int boardLimit = 5;
		
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Board> list = boardService.selectSearchList(pi, keyword);
		
		HashMap<String, Object> hm = new HashMap<>();
		
		hm.put("pi", pi);
		hm.put("keyword", keyword);
		hm.put("list",  list);
		
		return hm;
	}
	
	// 게시글 작성
	@PostMapping("insert")
	public String insertBoard(/*@ModelAttribute*/ Board b, MultipartFile upfile, HttpSession session) {
		
		// 요청이 같은 Post 방식이라도 multipart/form-data 형식이라서
		// @RequestBody 어노테이션으로 요청시전달값 받을 수 없음
		// @ModelAttribute 어노테이션 사용해야 함 => 생략해도 됨
		
		// System.out.println(b); // boardTitle, boardContent, boardWriter
		// System.out.println(upfile); // 파일 있으면 주소값 or null
		
		// 첨부파일이 있을 경우
		// 파일명 수정작업 후 서버로 업로드, originName, changeName 필드값 세팅
		if(upfile != null) {
			
			// upfile로부터 원본파일명 먼저 뽑기			
			// 파일명 수정 후 업로드
			// ex) bono.jpg --> 2025120214194213.jag
			// 겹치지 않게 수정 후 resources 폴더 안 쪽에 저장
			// 공통코드로 처리
			String originName = upfile.getOriginalFilename();
			String changeName = FileRenamePolicy.saveFile(upfile, session, "/resources/board_upfiles/");
			
			// b의 originName, changeName 필드값 세팅
			b.setOriginName(originName);
			b.setChangeName(changeName);
		}
		
		// 이 시점 기준으로
		// System.out.println(b);
		// 넘어온 첨부파일 없으면 오리진/체인지네임 = null or 문자열
		
		int result = boardService.insertBoard(b);
		
		return (result > 0) ? "게시글 작성 완료" : "작성 오류";
	}
	
	// 게시글 조회수 증가
	@GetMapping("increaseCount/{boardNo}")
	public int increaseCount(@PathVariable/*(value="boardNo")*/ int boardNo) {
		
		// System.out.println(boardNo);
		
		int result = boardService.increaseCount(boardNo);
		
		return result;
	}
	
	// 게시글 상세조회
	@GetMapping("detail/{boardNo}")
	public Board selectBoard(@PathVariable int boardNo) {
		
		Board b = boardService.selectBoard(boardNo);
		
		return b;
		
	}
	
	// 파일다운로드용
	@GetMapping("download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpSession session) throws IOException {
		
		// 1. 실제 백엔드서버 어딘가에 저장된 fileName 찾기
		// > 파일이 저장된 물리적인 경로 알아내기
		String savePath = session.getServletContext().getRealPath("/resources/board_upfiles/");
		
		// System.out.println(savePath);
		
		// 다운로드해야 하는 파일의 풀 경로(절대경로)
		// savePath + fileName
		
		// 2. 실제 파일 정보를 자바의 파일객체로 옮겨담기
		File file = new File(savePath + fileName);
		// 아래 entity 객체에 응답데이터로 넘겨줄 수 있도록 변형
		Resource resource = new FileSystemResource(file);
		
		
		// 3. 사용자가 다운로드하면 원본파일명으로 확인할 수 있게
		// DB에서 조회하기
		String originName = boardService.selectOriginFileName(fileName);
		// 수정파일명을 넘겨서 원본파일명을 문자열로 조회할 것
		
		// 4. 파일명에 한글이 있을경우 깨짐 방지
		String encoding = URLEncoder.encode(originName, "UTF-8").replaceAll("\\+", "%20");
		
		// 5. 세팅한 변수값들을 가지고 파일을 응답데이터로 넘기면 됨
		// 응답데이터가 파일형식이라 Jackson으로 JSON 못 함
		// >> 응답정보에 대한 형식을 지정해서 리턴해야 함
		// ResponseEntity 객체 == response 리액트
		ResponseEntity<Resource> response = ResponseEntity.ok().header("Content-Disposition", "attachment; filename*=UTF-8''" + encoding)
															  .body(resource);
		
		return response;
		
	}
	
	// 게시글 수정
	@PostMapping("update")
	public String updateBoard(Board b, MultipartFile reupfile, HttpSession session) {
		
		if(reupfile != null) { // 새로 넘어온 첨부파일이 있을 경우 -> 파일명 수정 후 업로드
			
			String originName = reupfile.getOriginalFilename();
			String changeName = FileRenamePolicy.saveFile(reupfile, session, "/resources/board_upfiles/");
			
			b.setOriginName(originName);
			b.setChangeName(changeName);
		}
		
		int result = boardService.updateBoard(b);
		
		return (result > 0) ? "성공" : "실패";
	}
	
	@PostMapping("delete")
	public String deleteBoard(@RequestBody Board b) {
		
		int result = boardService.deleteBoard(b.getBoardNo());
		
		return (result > 0) ? "삭제 성공" : "실패";
	}

}

























