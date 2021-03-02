package com.itcen.readbook.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itcen.readbook.service.UserService;

@Controller
public class UserController {

	// 회원가입 처리
	@RequestMapping("/user/2")
	public String createuser(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		UserService userservice=UserService.getUserInstance();
		userservice.createUser(model);
		return "redirect:/main";
	}
	
	// ID 중복체크 페이지
	@RequestMapping("/user/id/1")
	public String checkid() {
		return "check_id";
	}
		
	// 로그인 처리 페이지
	@RequestMapping("/login")
	public String loginpage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		UserService userservice=UserService.getUserInstance();
		userservice.login(model);
		return "redirect:/main";
	}
		
	// 로그아웃 페이지
	@RequestMapping("/logout")
	public String logoutpage() {
		return "logout";
	}
	
	// 개인 정보 수정
	@RequestMapping("/mypage/user/4")
	public String updateuser(HttpServletRequest request, Model model) {
		String id=request.getParameter("id");
		model.addAttribute("request", request);
		UserService userservice=UserService.getUserInstance();
		userservice.updateUser(model);
		return "redirect:/mypage?id="+id;
	}
}
