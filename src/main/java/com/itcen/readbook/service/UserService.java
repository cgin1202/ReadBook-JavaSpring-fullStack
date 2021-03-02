package com.itcen.readbook.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.itcen.readbook.dao.BookDao;
import com.itcen.readbook.dao.UserDao;
import com.itcen.readbook.vo.BookVO;
import com.itcen.readbook.vo.UserVO;

public class UserService {

	private static UserService userservice;
	
	public static UserService getUserInstance() {
		if(userservice==null)
			userservice=new UserService();
		return userservice;
	}
	
	// 고객 수정 service
	public void updateUser(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String user_id=request.getParameter("id");
		String user_pw=request.getParameter("pw");
		String user_name=request.getParameter("name");
		String user_addr=request.getParameter("addr");
		String user_subaddr=request.getParameter("subaddr");
		String user_number=request.getParameter("number");
		UserDao userdao=UserDao.getInstance();
		userdao.update(user_id, user_pw, user_name, user_addr+" "+user_subaddr, user_number);
	}
	
	// 로그인 service
	public void login(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		UserDao userdao=UserDao.getInstance();
		int userCheck=userdao.loginCheck(id, pw);
		if(userCheck==1){
			model.addAttribute("sessionID", id);
			model.addAttribute("msg", 1);
		}
		else if(userCheck==0){
			model.addAttribute("msg", 0);
		}
		else{
			model.addAttribute("msg", -1);
		}
	}
	
	// 고객 정보 출력 Service
	public void getUserInfo(Model model) {

		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String id=request.getParameter("id");
		BookDao bookdao=new BookDao();
		UserDao userdao=new UserDao();	
		ArrayList<BookVO> userBookInfo=bookdao.userBorrowInfo(id);
		ArrayList<UserVO> userInfo=userdao.getUserInfo(id);
		request.setAttribute("user_book_info", userBookInfo);
		request.setAttribute("user_info", userInfo);
	}
	
	// 회원가입 Service
	public void createUser(Model model) {
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String user_id=request.getParameter("id");
		String user_pw=request.getParameter("pw");
		String user_name=request.getParameter("name");
		String user_addr=request.getParameter("addr");
		String user_subaddr=request.getParameter("subaddr");
		String user_number=request.getParameter("number");
		UserDao userdao=UserDao.getInstance();
		userdao.write(user_id, user_pw, user_name, user_addr+" "+user_subaddr, user_number);
	}
}
