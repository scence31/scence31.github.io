package com.kh.spring.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Category;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.FileRenamePolicy;
import com.kh.spring.common.template.Pagination;
import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 일반게시판 목록 조회 요청 처리 메소드
	@GetMapping("list")
	public ModelAndView selectBoardList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv) {
		
		// System.out.println(cpage);
		// > 요청 시 전달값으로 현재 내가 보고자 하는 페이지수를 넘김!!
		//   또한, 쿼리스트링이 아예 없을 경우 (cpage 값이 안넘어올 경우)
		//   기본값으로 cpage == 1 이 넘어옴!!
		
		// * listCount : 총 게시글의 갯수
		// > BOARD 테이블로부터 유효한 게시글의 갯수를 COUNT 함수로 조회해오기
		int listCount = boardService.selectListCount();
		
		// * currentPage : 현재 사용자가 요청한 페이지
		// > 이미 위에서 요청 시 전달값으로 쿼리스트링을 통해 cpage 라는 키값으로 전달받음.
		// > 상단 매개변수명을 currentPage 로 바꾸는 대신 
		//   @RequestParam 어노테이션으로 cpage 전달값이 담기게끔 유도함!!
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int pageLimit = 10;
		
		// * boardLimit : 한 페이지에 게시글 몇개씩 보여질건지
		int boardLimit = 10;

		// 이제는 4개의 변수를 대서 Pagination 클래스의 공통코드를 호출하면 9개 가공 후 리턴
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Board> list = boardService.selectBoardList(pi);
		// > 어느 페이지를 요청하든 최대 boardLimit 갯수만큼의 게시글이 조회 ArrayList
		
		// 응답데이터 넘기기
		// 1. 조회된 게시글 리스트가 tbody 안에 보여져야 함 (list)
		// 2. 또한 요청 페이지 하단의 페이징바를 만드려면 pi 도 보내야 함
		// 3. 화면포워딩
		// 위의 구문들을 메소드 체이닝으로 축약
		mv.addObject("list", list)
		  .addObject("pi", pi)
		  .setViewName("board/boardListView");
		
		return mv;
	
	} // selectBoardList 끝
	
	// 일반게시글 검색 요청 처리 메소드
	@GetMapping("search")
	public ModelAndView searchBoardList(@RequestParam(value="cpage", defaultValue="1") int currentPage, 
										String condition, String keyword,
										ModelAndView mv) {
		
		// System.out.println(condition);
		// > 검색 조건 : "writer" / "title" / "content"
		// System.out.println(keyword);
		// > 검색어 : 사용자가 입력한 문자열 텍스트
		//			 "ad", "입니다", "게시판" 등
		
		// 위의 두개의 값을 가지고 검색 기능 구현하기!!
		// > 검색 결과 또한 여러행 조회임!! 이 때 페이징 처리도 들어가야함!!
		
		// condition 과 keyword 를 하나의 객체로 가공해서 넘길 것!!
		// > condition 과 keyword 를 필드로 가지는 VO 를 만드는 방법
		//   condition 과 keyword 를 하나의 HashMap 객체로 가공해서 넘기는 방법
		
		// > HashMap 이용
		HashMap<String, String> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		int searchCount = boardService.selectSearchCount(map); // 검색된 총 게시글 갯수
		// int currentPage; // 요청된 페이지 수 (매개변수로 받음)
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		// 이 pi 를 가지고 실제 DB 로 부터 검색 결과 내용을 조회해오면 됨!!
		ArrayList<Board> list = boardService.searchBoardList(map, pi);
		
		// 위의 list 를 이제 응답페이지에 뿌려 줄 것!!
		// + 또한 pi 를 응답데이터로 같이 넘겨서 페이징바도 만들어 줄 것!!
		// > 검색 조회용 응답페이지를 .jsp 로 별도로 만들 필요가 없다!!
		//   기존에 만들었던 목록 조회용 .jsp 파일을 재활용 할 수 있음!!
		
		// > 응답페이지의 검색창에 검색어가 같이 보여졌으면 함!!
		// > 또한 검색창에 검색 조건도 유지되서 보여졌으면 좋겠음!!
		//   검색조건과 검색어를 응답데이터로 한번 더 넘기면 됨
		
		mv.addObject("list", list)
		  .addObject("pi", pi)
		  .addObject("keyword", keyword)
		  .addObject("condition", condition)
		  .setViewName("board/boardListView");
		
		return mv;
		
	} // searchBoardList 끝
	
	// 일반게시글 작성하기 페이지 요청 처리 메소드
	@GetMapping("enrollForm")
	public String enrollForm(Model model) {
		
		// 이왕이면 카테고리 정보를 조회해와서 작성하기 페이지에서 뿌려주자
		ArrayList<Category> list = boardService.selectCategoryList();
		
		model.addAttribute("list", list);
		
		// 일반게시글 작성하기 페이지만 띄우고 끝
		return "board/boardEnrollForm";
		// /WEB-INF/views/board/boardEnrollForm.jsp
		
	} // enrollForm 끝
	
	// 게시글 작성 요청 처리 메소드
	@PostMapping("insert")
	public String insertBoard(Board b, MultipartFile upfile, HttpSession session, Model model) {
		
		// System.out.println(b);
		// > category (카테고리번호), boardTitle, boardContent, boardWriter
		
		// 일반게시글 작성 + "첨부파일 업로드 (Attachment 테이블에 Insert)"
		// > 요청 시 전달값 중 "첨부파일" 관련 내용도 받아내야함!! (upfile)
		
		// * 요청 시 전달값 중 첨부파일이 있을 경우
		// - 스프링에서 첨부파일은 MultipartFile 타입의 매개변수로 받아준다.
		// - 단, 입력 form 에서 enctype 속성을 적어줘야한다!!
		// - 제대로 동작은 하나, 비정상적으로 큰 파일이 업로드 될 때의 경우를 막고싶음!!
		//   (application.properties 파일에서 설정값으로 막아주면 됨)
		// System.out.println(upfile); // 주소값
		// System.out.println(upfile.getOriginalFilename());
		// 요청 시 전달된 파일의 파일명 (원본파일명)
		// > 넘어온 첨부파일이 있다면 원본파일명, 넘어온 첨부파일이 없다면 "" (빈 문자열)
		
		// 위의 요청 시 전달값들을 다 확인했다면 이제 로직을 짜볼 것!!
		// > 게시글 작성 + 첨부파일 업로드 (등록) 의 경우
		//   각각 BOARD 테이블에 INSERT, ATTACHMENT 테이블에 INSERT
		//   총 2번 해줘야 함!!
		
		// BOARD 테이블에 INSERT 할 정보가 담겨있는 Board 객체는
		// 이미 커맨드 객체 방식으로 전달받았음 (Board b)

		// > 우선 ATTACHMENT 테이블의 컬럼 정보와 유사하게
		//   자바 Attachment 클래스를 작성하자!!
		
		// ATTACHMENT 테이블에 INSERT 할 정보를 담을
		// Attachment 객체는 우선 null 로 초기화 해두자 (Attachment at)
		Attachment at = null;
		
		// 넘어온 첨부파일이 있을 경우
		if(!upfile.getOriginalFilename().equals("")) {
			
			// 파일명 수정 작업 후 서버로 업로드 시키기
			// > 일반게시글 등록 뿐만 아니라 사진게시글 등록, 컨텐츠 등록,
			//   예약정보 등록 등에서 폭넓게 쓰일 수 있음!! (공통코드 작업)
			
			// > 첨부파일에 대한 정보를 at 의 각 필드에다 옮겨 담기!!
			
			// 파일명을 수정하는 이유
			// : 업로드된 파일들은 서버 컴퓨터의 어느 한 폴더에 몰아서 저장할것
			//   김말순 회원이 a.jpg, 홍길동 회원이 a.jpg 를 업로드했다면
			//   한 폴더 내에 동일한 이름의 파일들이 저장될 수 없으므로
			//   그래서 파일명을 수정한 뒤에 업로드를 하는 것!!
			
			/*
			// 예) "bono.jpg" --> "2025102015023012345.jpg"
			//    년월일시분초 + 5자리랜덤수 + 원래확장자명
			//    (최대한 파일명이 중복되지 않게)
			
			// 1. 원본파일명 뽑아오기
			String originName = upfile.getOriginalFilename();
			// "bono.jpg"
			
			// 2. 현재 시간을 형식에 맞게 문자열로 뽑기
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date()); 
			// "20251020150230" (년월일시분초)
			
			// 3. 뒤에 붙을 5자리 랜덤값 뽑기 (10000 ~ 99999)
			int ranNum = (int)(Math.random() * 90000 + 10000);
			// 12345
			
			// 4. 원본파일명으로부터 확장자명만 뽑아오기
			String ext = originName.substring(originName.lastIndexOf("."));
			// ".jpg"
			
			// 5. 모두 이어 붙이기
			String changeName = currentTime + ranNum + ext;
			
			// 6. 업로드 하고자 하는 폴더의 경로 셋팅하기
			// > 단, 우리는 지금 개발 단계이지만 추후 배포 단계를 고려해서 경로를 셋팅!!
			//   우리의 배포 폴더 역할을 하는 webapp 폴더 기준으로 경로를 잡아 줄 것!!
			//   webapp 폴더 내부의 resources 폴더 (외부 자원 파일을 담는 폴더)
			//   내부 어딘가에 첨부파일들을 보관할 수 있게끔 유도해보자
			//   (webapp/resources/board_upfiles 폴더)
			
			// application 객체에서 제공하는 getRealPath 메소드를 통해 알아내기
			// request 객체로부터, session 객체로부터 얻어내는 방법이 있음!!
			String savePath = session.getServletContext()
									.getRealPath("/resources/board_upfiles/");
			// > 앞의 / 는 webapp 폴더를 나타내고,
			//   뒤의 / 는 이 board_upfiles 라는 폴더의 내부를 뜻함!!
			//   업로드된 파일들은 항상 "폴더 안에" 저장되야 하므로!!
			// > savePath 에는 C 드라이브 (루트 디렉토리) 에서 부터 시작되는
			//   board_upfiles 라는 폴더의 절대 경로가 담겨있을 것!!
			
			// 7. 파일 경로와 수정파일명을 합체 후 파일을 업로드 해주기
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			
			// 위의 단계들을 공통코드 호출로 대체 (FileRenamePolicy)
			String changeName = FileRenamePolicy.saveFile(upfile, session, "/resources/board_upfiles/");
			
			// 8. 첨부파일에 대한 정보를 Attachment at 에 담기
			at = new Attachment();
			at.setOriginName(upfile.getOriginalFilename());
			at.setChangeName(changeName);
			at.setFilePath("resources/board_upfiles/");
			
		}
		
		// 이 시점 기준으로 게시글, 첨부파일에 대한 정보가 모두 셋팅됨!!
		// System.out.println(b);
		// > 카테고리번호, 제목, 내용, 작성자의 회원번호
		//   BOARD 테이블에 INSERT 할 내용!!
		// System.out.println(at);
		// > 첨부파일이 없는 경우 null
		// > 첨부파일이 있는 경우 원본파일명, 수정파일명, 경로
		
		// 그 전, XSS 공격 방지 처리 하기!! (공통코드 작업)
		
		// 최종적으로 Service -> DAO 갔다오기
		// > BOARD INSERT 와 ATTACHMENT INSERT 모두 하나의 트랜잭션으로 묶기!!
		int result = boardService.insertBoard(b, at);
		
		// 결과에 따른 응답화면 지정
		if(result > 0) { // 성공
			
			// 세션에 일회성 알람 문구를 담아서 
			// 게시글 목록 조회 페이지 1번 페이지로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 게시글이 등록되었습니다.");
			
			return "redirect:/board/list";
			
		} else { // 실패
			
			// 기능은 잘 돌아가나, 완성도를 높여보자!!
			// > 게시글 작성에 실패했다 == 첨부파일을 굳이 서버에 저장할 필요가 없다
			
			// 첨부파일이 있었을 경우 이미 업로드된 파일을 삭제 처리할것임!!
			// (그대로 내비 두면 용량만 차지하니까)
			if(at != null) {
				
				// 해당 첨부파일을 삭제
				// > 삭제하고자 하는 파일을 찝어서 File 객체로 만든 후
				//   delete 메소드 호출하기
				
				String savePath = session.getServletContext()
										 .getRealPath("/resources/board_upfiles/");
				
				new File(savePath + at.getChangeName()).delete();
				
			}
			
			// 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "게시글 작성에 실패했습니다.");
			
			return "common/errorPage";
			
		} 
		
	} // insertBoard 끝
	
	// 일반게시글 상세조회 요청 처리 메소드
	@GetMapping("detail/{boardNo}")
	public String selectBoard(@PathVariable(value="boardNo") int bno, Model model) {
		
		// System.out.println(bno);
		
		// 게시글 상세조회 로직 구현하기
		// > 조회수 개념이 있기 때문에 무조건 조회해오면 안됨!!
		// 1. 우선 조회수 부터 증가하고 넘어와야함!!
		// 2. 조회수 증가에 성공했다면 조회해오기!!
		
		// 1. 조회수 증가시키기
		int result = boardService.increaseCount(bno);
		
		// 2. 조회수 증가에 성공했다면 해당 게시글 조회
		if(result > 0) { // 조회수 증가에 성공
			
			Board b = boardService.selectBoard(bno);
			
			// 3. 해당 게시글에 딸린 첨부파일 정보 또한 조회
			Attachment at = boardService.selectAttachment(bno);
			// > 글번호 (bno) 를 가지고 첨부파일을 조회해올 것!!
			//   그러면 그 게시글에 딸린 첨부파일 정보가 조회됨
			//   단, 우리는 일반게시판 작성 시
			//   게시글 한 개 당 첨부파일 최대 1 개를 넣었었음!!
			// > 참조 게시글 번호로 첨부파일 정보를 조회 하더라도
			//   "단일행 조회" 가 일어날것임!!
			
			// > 이 때, 첨부파일이 없는 게시글일 경우에는
			//   at == null
			
			// 응답데이터로 b, at 모두 담고, 응답페이지로 포워딩
			model.addAttribute("b", b);
			model.addAttribute("at", at);
			
			return "board/boardDetailView";
			// /WEB-INF/views/board/boardDetailView.jsp
			
		} else { // 조회수 증가에 실패
			
			// 에러 문구를 에러페이지로 포워딩
			model.addAttribute("errorMsg", "일반게시글 상세조회에 실패했습니다.");
			
			return "common/errorPage";
			
		}
	
	} // selectBoard 끝
	
	// 일반게시글 수정 페이지 요청 처리 메소드
	@PostMapping("updateForm")
	public ModelAndView updateForm(int bno, ModelAndView mv) {
		
		// 게시글 수정하기 페이지만 띄워주고 끝
		// > 단, 보통 수정하기 페이지에는 기존의 제목과 내용 등이 우선 보여지고
		//   여기서 수정할거면 수정하라고 페이지에 띄워줌
		
		// 기존 게시글 정보를 불러오기
		Board b = boardService.selectBoard(bno);
		// 글번호, 카테고리명, 제목, 내용, 작성자 아이디, 작성일
		
		// 기존 첨부파일 정보 또한 불러오기
		Attachment at = boardService.selectAttachment(bno);
		// 파일번호, 원본파일명, 수정파일명, 파일경로
		
		// 수정하기페이지 또한
		// 카테고리 정보들을 실제 DB 로부터 조회해서 출력할 것!!
		ArrayList<Category> list = boardService.selectCategoryList();
		
		// 수정하기 페이지 포워딩
		mv.addObject("b", b)
		  .addObject("at", at)
		  .addObject("list", list)
		  .setViewName("board/boardUpdateForm");
		// /WEB-INF/views/board/boardUpdateForm.jsp
		
		return mv;
		
	} // updateForm 끝
	
	// 일반게시글 수정 요청 처리 메소드
	@PostMapping("update")
	public String updateBoard(Board b, MultipartFile reUpfile,
							  @RequestParam(defaultValue="0") int originalFileNo,
							  HttpSession session,
							  Model model,
							  String originalFileChangeName) {
		
		// System.out.println(b);
		// 글번호, 수정할 카테고리 번호, 수정할 제목, 수정할 내용
		
		// System.out.println(reUpfile.getOriginalFilename());
		// > 새로운 첨부파일의 원본파일명
		
		// 만약, 기존 첨부파일이 있던 경우 기존 첨부파일의 번호 또한 필요함!!
		// System.out.println(originalFileNo);
		// > 기존에 첨부파일이 있는 경우 그 첨부파일의 파일 번호
		//   기존에 첨부파일이 없는 경우 0 (defaultValue 속성)
		
		// ATTACHMENT 테이블에 INSERT 또는 UPDATE 할 정보를 담을
		// Attachment 객체는 우선 null 로 초기화
		Attachment at = null;
		
		// 새로 넘어온 첨부파일이 있을 경우
		if(!reUpfile.getOriginalFilename().equals("")) {
			
			// 파일명 수정 후 서버로 업로드
			String changeName = FileRenamePolicy.saveFile(reUpfile, session, "/resources/board_upfiles/");
			
			// 첨부파일에 대한 정보를 at 객체에 이어서 담기
			at = new Attachment();
			at.setOriginName(reUpfile.getOriginalFilename());
			at.setChangeName(changeName);
			
			if(originalFileNo != 0) {
				// 기존 첨부파일이 있었을 경우
				// > Attachment 테이블에 Update 문 실행
				
				// 미리 짜둔 UPDATE 구문에서 누락된 데이터
				// FILE_NO 값을 추가적으로 필드에 넣기!!
				at.setFileNo(originalFileNo);
				
				// 새로 넘어온 첨부파일 O, 기존 첨부파일 O
				// > 새로 넘어온 첨부파일 정보로 덮어씌울건데
				//   굳이 기존 첨부파일을 계속 서버에 저장할 필요가?
				// > 기존 첨부파일을 폴더로부터 삭제하기 (수정파일명 필요)
				String savePath = session.getServletContext()
										 .getRealPath("/resources/board_upfiles/");
				
				new File(savePath + originalFileChangeName).delete();
				
			} else { 
				// 기존 첨부파일이 없었을 경우
				// > Attachment 테이블에 Insert 문 실행
				
				// 미리 짜둔 INSERT 구문에서 누락된 데이터
				// REF_BNO, FILE_PATH 값을 추가적으로 필드에 넣기!!
				at.setRefBno(b.getBoardNo());
				at.setFilePath("resources/board_upfiles/");
				
			}
			
		}
		
		// 이 시점 기준으로 b, at 를 다시 출력해보기!!
		// System.out.println(b);
		// System.out.println(at);
		
		/*
		 * * 상황에 따른 경우의 수 정리
		 * 
		 * case 1 : 애초에 새로운 첨부파일이 안넘어온 경우
		 * 			BOARD 테이블에 UPDATE 
		 * 			(b : 글번호, 변경할 카테고리번호, 변경할 제목, 변경할 내용)
		 * 			(at : null)
		 * case 2 : 요청 시 새로운 첨부파일이 넘어온 경우
		 * case 2_1 : 기존 첨부파일이 있던 경우
		 * 			  BOARD 테이블에 UPDATE
		 * 			  (b : 글번호, 변경할 카테고리번호, 변경할 제목, 변경할 내용)
		 * 			  ATTACHMENT 테이블에 UPDATE
		 * 			  (at : 파일번호, 원본파일명, 수정파일명)
		 * case 2_2 : 기존 첨부파일이 없던 경우
		 * 			  BOARD 테이블에 UPDATE
		 * 			  (b : 글번호, 변경할 카테고리번호, 변경할 제목, 변경할 내용)
		 * 			  ATTACHMENT 테이블에 INSERT
		 * 			  (at : 참조게시글번호, 원본파일명, 수정파일명, 경로)
		 */
		
		// 마찬가지로 XSS 공격 방지 하고 넘어가기!!
		
		// 이 모든 경우를 하나의 트랜잭션으로 묶어야 함!!
		int result = boardService.updateBoard(b, at);
		
		// 결과에 따른 응답 처리
		if(result > 0) { // 성공
			
			// 세션에 일회성 알람 문구를 담아서
			// 해당 게시글의 상세보기 페이지로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 게시글이 수정되었습니다.");
			
			return "redirect:/board/detail/" + b.getBoardNo();
			
		} else { // 실패
			
			// 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "게시글 수정에 실패했습니다.");
			
			return "common/errorPage";
		}
		
	} // updateBoard 끝
	
	// 일반게시글 삭제 요청 처리 메소드
	@PostMapping("delete")
	public String deleteBoard(int bno, Model model, HttpSession session) {
		
		int result = boardService.deleteBoard(bno);
		
		if(result > 0) { // 게시글 삭제 성공
			
			// 세션에 일회성 알람 문구를 담아서
			// 일반게시판 목록 1번 페이지로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 게시글이 삭제되었습니다.");
			
			return "redirect:/board/list";
			
		} else { // 게시글 삭제 실패
			
			// 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "게시글 삭제에 실패했습니다.");
			
			return "common/errorPage";
			
		}
		
	}
	
	// 댓글목록조회요청처리 메소드
	@ResponseBody
	@GetMapping("rlist")
	public ArrayList<Reply> ajaxSelectReplyList(int bno) {
		
		// 글번호 넘기면서 서비스-> dao
		// ArrayList<Reply> list = boardService.ajaxSelectReplyList(bno);
		
		// jackson 라이브러리로 곧바로 응답데이터 넘기기
		// return list;
		
		return boardService.ajaxSelectReplyList(bno);
	}
	
	// 댓글작성요청용 메소드
	@ResponseBody
	@PostMapping("rinsert")
	public String ajaxInsertReply(Reply r, HttpSession session) {
		
		// 댓글작성자의 회원번호도 필요하므로 세션으로부터 얻어내기
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		int replyWriter = loginUser.getUserNo();
		r.setReplyWriter(replyWriter + ""); // == String.valueOf(replyWriter)
		
		// replyContent, refBno, replyWriter 필드값이 담겨있음
		
		int result = boardService.ajaxInsertReply(r);
		
		return (result > 0) ? "success" : "fail";
	}
	
	

}





