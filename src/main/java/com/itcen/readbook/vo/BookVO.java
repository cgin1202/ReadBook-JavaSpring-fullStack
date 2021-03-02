package com.itcen.readbook.vo;

public class BookVO {
	
	int book_id;
	String book_author;
	String book_publisher;
	int book_subject;
	int book_searchCnt;
	int book_borrowCnt;
	boolean book_isAvailable;
	String book_user_id;
	String book_name;
	
	public boolean isBook_isAvailable() {
		return book_isAvailable;
	}

	public void setBook_isAvailable(boolean book_isAvailable) {
		this.book_isAvailable = book_isAvailable;
	}

	public BookVO() {
		
	}
	
	public BookVO(int book_id, String book_author, String book_publisher, int book_subject, int book_searchCnt, int book_borrowCnt, boolean book_isAvailable, String book_user_id, String book_name) {
		this.book_id=book_id;
		this.book_author=book_author;
		this.book_publisher=book_publisher;
		this.book_subject=book_subject;
		this.book_searchCnt=book_searchCnt;
		this.book_borrowCnt=book_borrowCnt;
		this.book_isAvailable=book_isAvailable;
		this.book_user_id=book_user_id;
		this.book_name=book_name;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public int getBook_subject() {
		return book_subject;
	}

	public void setBook_subject(int book_subject) {
		this.book_subject = book_subject;
	}

	public int getBook_searchCnt() {
		return book_searchCnt;
	}

	public void setBook_searchCnt(int book_searchCnt) {
		this.book_searchCnt = book_searchCnt;
	}

	public int getBook_borrowCnt() {
		return book_borrowCnt;
	}

	public void setBook_borrowCnt(int book_borrowCnt) {
		this.book_borrowCnt = book_borrowCnt;
	}

	public String getBook_user_id() {
		return book_user_id;
	}

	public void setBook_user_id(String book_user_id) {
		this.book_user_id = book_user_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	
	
}