package com.kh.spring.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.common.template.XssDefencePolicy;
import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("notice")
public class NoticeController {

	// NoticeService 객체를 미리 셋팅
	@Autowired
	private NoticeService noticeService;
	
	// 스프링으로 웹 구현 시 자주 보이는 오류들
	// 404 : 파일, 화면, 컨트롤러 등이 없을 때 발생 (Not Found)
	// 405 : 요청 전송 방식이 안맞을 때 발생
	// 500 : 자바 문법 오류 (대부분 Exception)
	
	// 공지사항 목록 조회 요청 처리 메소드
	@GetMapping("list")
	public String selectNoticeList(Model model) {
		
		// System.out.println("잘 호출 되나?");
		
		// 1. 요청 시 전달값 뽑기
		// 2. 가공 -> Service -> Dao -> DB 상호작용 후 결과 받기
		
		// 공지사항 리스트 조회 페이지에서 필요로 하는 응답데이터를 구해오기
		ArrayList<Notice> list = noticeService.selectNoticeList();
		// > 여러행 조회 이므로 ArrayList 로 받을 것!!
		
		/*
		for(Notice n : list) {
			
			System.out.println(n);
		}
		*/
		
		// 응답페이지에서 위의 list 가 보여지게끔 응답데이터로 담기
		model.addAttribute("list", list);
		
		// 3. 결과에 따른 응답화면 지정
		return "notice/noticeListView";
		// /WEB-INF/views/notice/noticeListView.jsp
		
	} // selectNoticeList 끝
	
	// 공지사항 작성 페이지 요청 처리 메소드
	@GetMapping("enrollForm")
	public String enrollForm() {
		
		// 공지사항 작성 페이지만 띄워주고 끝
		return "notice/noticeEnrollForm";
		// /WEB-INF/views/notice/noticeEnrollForm.jsp
	
	} // enrollForm 끝
	
	// 공지사항 작성 요청 처리 메소드
	@PostMapping("insert")
	public String insertNotice(Notice n, Model model, HttpSession session) {
		
		// System.out.println(n);
		// > n : 제목, 내용, 작성자의 회원번호
		
		/*
		 * * 글 제목이나 내용에 html 태그 형식 또는 자바스크립트 코드 같은 것이 들어가면
		 *   진짜 태그로써 해석되서 화면에 출력되거나, 자바스크립트 코드로써 실행됨!!
		 * > 웹 사이트 보안 상 많이 취약하다!!
		 * 
		 * * XSS (Cross-Site-Scripting) 공격
		 * 
		 * - 웹 사이트 상에 악성 스크립트 코드를 일부러 삽입하는 공격
		 * - 글 제목이나 내용에 html 태그, 자바스크립트 코드 등을 삽입해서 공격한다.
		 * 
		 * * 해결 방법
		 * 
		 * - 요청 시 전달값 중 < 를 &lt; 으로, > 를 &gt; 으로 replace 해서 저장
		 * 
		 * * 시큐어 코딩
		 * 
		 * - 개발 과정에서 보안 취약점을 미리 제거해 최대한 안전한 소프트웨어를 개발하는 기법
		 * 예) SQL Injection 공격 : PreparedStatement 쓰기
		 *     비밀번호 암호화 : BCryptPasswordEncoder 쓰기
		 *     XSS 공격 : html 의 "예약어" replace 하기
		 * 	   ...
		 */
		
		// 매번 replace 메소드로 변환을 해주면 되는데, 괜히 길고,
		// 공통코드로 한번 만들어 두면 두고두고 잘 쓰일 것 같음!!
		// > 공통 코드 (템플릿) 작업 진행
		
		// 만든 공통 코드를 가지고 insert 전 치환 작업 진행하기!!
		String replaceTitle = XssDefencePolicy.defence(n.getNoticeTitle());
		String replaceContent = XssDefencePolicy.defence(n.getNoticeContent());
		
		// 이 치환된 문자열들을 n 의 각 필드에 다시 setter 로 대입하기!!
		n.setNoticeTitle(replaceTitle);
		n.setNoticeContent(replaceContent);
		
		int result = noticeService.insertNotice(n);
		
		// 처리된 결과에 따라 사용자가 보게 될 응답화면을 지정
		if(result > 0) { // 공지사항 작성 성공
			
			// 일회성 알람 문구를 세션에 담아서
			// 공지사항 리스트 페이지로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 공지사항이 등록되었습니다.");
			
			return "redirect:/notice/list";
			
		} else { // 공지사항 작성 실패
			
			// 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
			
			return "common/errorPage";
		}
		
	} // insertNotice 끝
	
	// 공지사항 상세보기 요청 처리 메소드
	// @GetMapping("detail")
	// public String selectNotice(int nno, Model model) {
	// > 쿼리스트링 방식으로 요청을 받아 줄 경우
	
	@GetMapping("detail/{noticeNo}")
	public String selectNotice(@PathVariable(value="noticeNo") int nno, Model model) {
	// > Path Variable 방식으로 요청을 받아 줄 경우
		
		// System.out.println(nno);
		
		// 상세조회 요청 시, 우선적으로 해당 게시글의 조회수를 1 증가시켜야함!!
		// > 마찬가지로 게시글 번호를 넘기면서 요청 할 것
		int result = noticeService.increaseCount(nno);
		
		// 공지사항 게시글 조회수 증가에 성공했다면
		// 해당 게시글 한건만 조회해와서 응답데이터로 넘겨줘야 함!!
		if(result > 0) { // 조회수 증가 성공
			
			// 해당 게시글 하나만 조회해오기 (단일행 조회)
			Notice n = noticeService.selectNotice(nno);
			
			model.addAttribute("n", n);

			// 공지사항 상세조회 페이지 포워딩
			return "notice/noticeDetailView";
			// /WEB-INF/views/notice/noticeDetailView.jsp
			
		} else { // 조회수 증가 실패
			
			// 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "공지사항 상세조회에 실패했습니다.");
			
			return "common/errorPage";
		}
		
	} // selectNotice 끝
	
	// 공지사항 수정 페이지 요청 처리 메소드
	@PostMapping("updateForm")
	public String updateForm(int nno, Model model) {
		
		// System.out.println(nno);
		
		// 공지사항 수정하기 페이지는 기존의 제목과 내용을
		// 출력해주고 난 뒤, 여기서 수정할 거면 수정해라 이컨셉임!!
		// > 이미 nno 로 해당 글번호를 받아뒀음!!
		Notice n = noticeService.selectNotice(nno);
		// > 기존 공지사항 상세조회 서비스를 재활용
		//   (글번호, 제목, 내용, 작성자 아이디, 작성일)
		
		model.addAttribute("n", n);
		
		return "notice/noticeUpdateForm";
		// /WEB-INF/views/notice/noticeUpdateForm.jsp
		
	} // updateForm 끝
	
	// 공지사항 수정 요청 처리 메소드
	@PostMapping("update")
	public String updateNotice(Notice n, Model model, HttpSession session) {
		
		// System.out.println(n);
		// > noticeNo, noticeTitle, noticeContent
		
		// XSS 공격 방지 처리 하기
		String replaceTitle = XssDefencePolicy.defence(n.getNoticeTitle());
		String replaceContent = XssDefencePolicy.defence(n.getNoticeContent());
		
		n.setNoticeTitle(replaceTitle);
		n.setNoticeContent(replaceContent);
		
		// Service 로 요청 후 결과 받기
		int result = noticeService.updateNotice(n);
		
		// 결과에 따른 응답페이지 처리
		if(result > 0) { // 성공
			
			// 세션에 일회성 알람 문구를 담아서 해당 공지사항 게시글의
			// 상세보기 페이지로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 공지사항이 수정되었습니다.");
			
			return "redirect:/notice/detail/" + n.getNoticeNo();
			// > Path Variable 방식일 경우
			
			// 참고)
			// 쿼리스트링 방식으로 상세조회 요청을 처리한 경우
			// return "redirect:/notice/detail?nno=" + n.getNoticeNo();
			
		} else { // 실패
			
			// 에러문구를 담아서 에러 페이지로 포워딩
			model.addAttribute("errorMsg", "공지사항 수정에 실패했습니다.");
			
			return "common/errorPage";
		}
		
	} // updateNotice 끝
	
	// 공지사항 삭제 요청 처리 메소드
	@PostMapping("delete")
	public ModelAndView deleteNotice(int nno, ModelAndView mv, HttpSession session) {
		
		int result = noticeService.deleteNotice(nno);
		
		// 결과에 따른 응답화면 지정
		if(result > 0) { // 성공
			
			// 세션에 일회성 알람 문구를 담아서 공지사항 목록 페이지로 url 재요청
			session.setAttribute("alertMsg", "성공적으로 공지사항이 삭제되었습니다.");
			
			mv.setViewName("redirect:/notice/list");
			
		} else { // 실패
			
			// 에러문구를 담아서 에러페이지로 포워딩
			/*
			mv.addObject("errorMsg", "공지사항 삭제에 실패했습니다.");
			mv.setViewName("common/errorPage");
			*/
			// > ModelAndView 객체의 addObject 메소드는 응답데이터를 담는 메소드임!!
			//   addObject 메소드는 ModelAndView 객체를 리턴해줌!!
			//   이 때, 리턴되는 ModelAndView 객체는 "자기 자신" 을 뜻한다!! (즉, mv 를 뜻함)
			
			mv.addObject("errorMsg", "공지사항 삭제에 실패했습니다.")
			  .setViewName("common/errorPage");
			// > 자기 자신의 주소값을 리턴하기 때문에 그 이후의 메소드 호출 시
			//   매번 각각 호출하는게 아니라 메소드 체이닝으로 한큐에 가능 하다!!
			
		}
		
		return mv;
		
	}
	
	
}





