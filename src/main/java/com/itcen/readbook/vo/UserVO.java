package com.itcen.readbook.vo;

public class UserVO {
	String user_id;
	String user_pw;
	String user_name;
	String user_addr;
	String user_phoneNum;
	
	public UserVO() {
		
	}
	
	public UserVO(String id, String pw, String name, String addr, String phoneNum) {
		this.user_id=id;
		this.user_pw=pw;
		this.user_name=name;
		this.user_addr=addr;
		this.user_phoneNum=phoneNum;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	public String getUser_phoneNum() {
		return user_phoneNum;
	}

	public void setUser_phoneNum(String user_phoneNum) {
		this.user_phoneNum = user_phoneNum;
	}
	
	
}
