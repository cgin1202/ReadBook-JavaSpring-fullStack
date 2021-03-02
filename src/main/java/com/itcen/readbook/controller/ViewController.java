package com.itcen.readbook.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.itcen.readbook.service.BookService;
import com.itcen.readbook.service.UserService;

@Controller
public class ViewController {

		
	// main 페이지
	@RequestMapping(value="/main")
	public String main(Model model) {
		BookService bookservice=BookService.getBookInstance();
		bookservice.topSearchBook(model);
		return "index";
	}
		
	// 회원가입 페이지
	@RequestMapping("/user")
	public String createuserpage() {
		return "createUser";
	}
	
	
	// 마이페이지
	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		UserService userservice=UserService.getUserInstance();
		userservice.getUserInfo(model);
		return "mypage";
	}
	
	// 개인 정보 수정 페이지
	@RequestMapping("/mypage/user")
	public String updateuserpage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		UserService userservice=UserService.getUserInstance();
		userservice.getUserInfo(model);
		return "updateUser";
	}
	
	// 도서 예약 취소 페이지
	@RequestMapping("/mypage/book")
	public String updatebookpage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		UserService userservice=UserService.getUserInstance();
		userservice.getUserInfo(model);
		return "cancelBook";
	}
	
	// 404 예외 에러 페이지
	@RequestMapping(value="/error404", method=RequestMethod.GET)
	public String errorpage404(HttpServletResponse request, Model model) {
		// response 객체의 상태 값을 정상으로 바꾸어야 error 404 페이지가 보임
		request.setStatus(HttpServletResponse.SC_OK);
		model.addAttribute("request", request);
		
		return "error/404error";
	}

	// 500 예외 에러 페이지
	@RequestMapping(value="/error500", method=RequestMethod.GET)
	public String errorpage500(HttpServletResponse request, Model model) {
		// response 객체의 상태 값을 정상으로 바꾸어야 error 500 페이지가 보임
		request.setStatus(HttpServletResponse.SC_OK);
		model.addAttribute("request", request);
		
		return "error/500error";
	}
	
	
}
