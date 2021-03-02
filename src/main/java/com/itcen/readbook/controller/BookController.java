package com.itcen.readbook.controller;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itcen.readbook.service.BookService;

@Controller
public class BookController {
	
	// 도서 검색 결과 페이지
	@RequestMapping("/book/1")
	public String searchresult(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		BookService bookservice=BookService.getBookInstance();
		bookservice.searchBook(model);
		return "searchBook";
	}
		
	// 도서 대여
	@RequestMapping("/book/2")
	public String borrowbook(HttpServletRequest request, Model model) {
		
		String id=request.getParameter("id");
		// 로그인 하지 않았는데 빌리려고 하는 경우 예외 처리
		boolean flag_id=Pattern.matches("^[a-zA-Z0-9]+$", id);
		if(flag_id==false)
			return "redirect:/main?msg=-3";

		model.addAttribute("request", request);
		BookService bookservice=BookService.getBookInstance();
		bookservice.borrowBook(model);
		return "redirect:/main";
	}
	
	// 도서 예약 취소
	@RequestMapping("/mypage/book/4")
	public String cancelbook(HttpServletRequest request, Model model) {
		String id=request.getParameter("id");
		model.addAttribute("request", request);
		BookService bookservice=BookService.getBookInstance();
		bookservice.cancelBook(model);
		return "redirect:/mypage?id="+id;
	}
}
