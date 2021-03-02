package com.itcen.readbook.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.itcen.readbook.dao.BookDao;
import com.itcen.readbook.vo.BookVO;

public class BookService {

	private static BookService bookservice;
	public static BookService getBookInstance() {
		if(bookservice==null)
			bookservice=new BookService();
		return bookservice;
	}
	
	// 도서 검색 Service
	public void searchBook(Model model){
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String findKeyword=request.getParameter("keyword");
		int pageNum=0;
		BookDao bookdao=new BookDao();
		if(request.getParameter("pagenum")==null)
			pageNum=0;
		else
			pageNum=Integer.parseInt(request.getParameter("pagenum"));
		int searchSize=bookdao.getSearchResultSize(findKeyword);
		ArrayList<BookVO> findKeyDto=bookdao.searchResultList(findKeyword, searchSize, pageNum);
		// 조회수+1
		if(findKeyDto.size()>0) {
			bookdao.addSearchCnt(findKeyword, searchSize, pageNum);
		}
		
		if(pageNum<0)
			pageNum=0;
		if(pageNum>searchSize/7)
			pageNum=searchSize/7;
		request.setAttribute("pagenum", pageNum);
		request.setAttribute("findWordList", findKeyDto);
	}
	
	// 도서 취소 Service
	public void cancelBook(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String id=request.getParameter("id");
		String bookname=request.getParameter("bookname");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publisher");
		BookDao bookdao=new BookDao();
		bookdao.update(id, bookname, author, publisher);
		model.addAttribute("cancel_msg", 2);
	}
	
	// 도서 에약 Service
	public void borrowBook(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String id=request.getParameter("id");
		String bookname=request.getParameter("bookname");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publisher");
		BookDao bookdao=new BookDao();
		ArrayList<BookVO> checkBookCnt=bookdao.userBorrowInfo(id);
		if(checkBookCnt.size() >= 3) {
			model.addAttribute("borrow_msg", -2);
		}else {
			bookdao.insert(id, bookname, author, publisher);
			model.addAttribute("borrow_msg", 2);
		}
	}
	
	// TOP 도서 Service
	public void topSearchBook(Model model) {
		BookDao bookdao=new BookDao();
		ArrayList<BookVO> searchDto=bookdao.topSearchList();
		ArrayList<BookVO> borrowDto=bookdao.topBorrowList();
		model.addAttribute("searchTopList", searchDto);
		model.addAttribute("borrowTopList", borrowDto);
	}
}
