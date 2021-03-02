package com.itcen.readbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.itcen.readbook.vo.BookVO;

public class BookDao {

	private DataSource dataSource;

	public BookDao() {
		
		try {
			Context initContext=new InitialContext();
			Context envContext=(Context)initContext.lookup("java:comp/env");
			dataSource=(DataSource)envContext.lookup("jdbc/orcl");
			
			/*요약
			Context initContext=new InitialContext();
			// context의 lookup 메소드를 이용해서 "java:comp/env"에 해당하는 객체를 찾아서 context에 삽입
			Context envContext=(Context)initContext.lookup("java:comp/env");
			// envContext의 lookup 메소드를 이용해서 "jdbc/orcl"에 해당하는 객체를 찾아서 ds 삽입
			dataSource=(DataSource)envContext.lookup("jdbc/orcl");
			dataSource=(DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// INDEX 페이지 (TOP 10 Search 도서 출력)
	
	public ArrayList<BookVO> topSearchList(){
		Connection conn = null;
		ArrayList<BookVO> bdtos=new ArrayList<BookVO>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String query="select book_id, REGEXP_REPLACE(book_author, '[[:punct:]]'), REGEXP_REPLACE(book_publisher, '[[:punct:]]'), book_subject, book_searchcnt, book_borrowcnt, book_isavailable, book_user_id, REGEXP_REPLACE(book_name, '[[:punct:]]') from (select * from bookinfo order by book_searchcnt desc) where rownum<=5 ";
			
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int book_id=rs.getInt(1);
				String book_author=rs.getString(2);
				String book_publisher=rs.getString(3);
				int book_subject=rs.getInt(4);
				int book_searchcnt=rs.getInt(5);
				int book_borrowcnt=rs.getInt(6);
				boolean book_isavailable=rs.getBoolean(7);
				String book_user_id = rs.getString(8);
				String book_name=rs.getString(9);
				
				BookVO bdto=new BookVO(book_id, book_author, book_publisher, book_subject, book_searchcnt, book_borrowcnt, book_isavailable, book_user_id, book_name);
				bdtos.add(bdto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return bdtos;
	}
	
	
	// 도서 예약 ( index 페이지, 검색 결과 페이지)
	
	public void insert(String id, String bookname, String author, String publisher) {
		PreparedStatement preparedStatement=null;
		Connection conn = null;
		try {
			
			conn=dataSource.getConnection();
			String query="update bookinfo set book_user_id=?, book_borrowcnt=book_borrowcnt+1, book_isavailable='0' where (book_name=? and book_isavailable='1' and book_author=? and book_publisher=?) and rownum=1";
			
			preparedStatement=conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, bookname);
			preparedStatement.setString(3, author);
			preparedStatement.setString(4, publisher);
			
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		
		
	
	// 도서 예약 취소 (도서 예약 취소 페이지)
	
	public void update(String id, String bookname, String author, String publisher) {
		PreparedStatement preparedStatement=null;
		Connection conn = null;
		try {
			
			conn=dataSource.getConnection();
			String query="update bookinfo set book_user_id='0', book_isavailable='1' where (book_name=? and book_user_id=? and book_author=? and book_publisher=?) and rownum=1";
			
			preparedStatement=conn.prepareStatement(query);
			preparedStatement.setString(1, bookname);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, author);
			preparedStatement.setString(4, publisher);
			
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	// 도서 조회 시 조회수 + 1 (searchResult 페이지)
	
	public void addSearchCnt(String keyword, int searchsize, int pagenum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection conn = null;
		
		int postnum=7;
		int page_div=searchsize/postnum;
		int page_mod=searchsize%postnum;
		int start=pagenum*postnum;
		int end=0;
		if(pagenum==page_div)
			end=start+page_mod-1;
		else
			end=(pagenum+1)*postnum-1;
		
		
		try {
			conn=dataSource.getConnection();
			String query="update bookinfo set book_searchCnt=book_searchCnt+1 where book_id in ( select book_id from (select book_id, rownum as num from bookinfo where book_name like '%' || ? || '%') sub_bookinfo where num>=? and num <= ?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	// INDEX 페이지 (TOP 10 Borrow 도서 출력)
	
	public ArrayList<BookVO> topBorrowList(){
		Connection conn = null;
		ArrayList<BookVO> bdtos=new ArrayList<BookVO>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String query="select * from (select * from bookinfo order by book_borrowcnt desc) where rownum<=5 ";
			
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int book_id=rs.getInt("book_id");
				String book_author=rs.getString("book_author");
				String book_publisher=rs.getString("book_publisher");
				int book_subject=rs.getInt("book_subject");
				int book_searchcnt=rs.getInt("book_searchcnt");
				int book_borrowcnt=rs.getInt("book_borrowcnt");
				boolean book_isavailable=rs.getBoolean("book_isavailable");
				String book_user_id = rs.getString("book_user_id");
				String book_name=rs.getString("book_name");
				
				BookVO bdto=new BookVO(book_id, book_author, book_publisher, book_subject, book_searchcnt, book_borrowcnt, book_isavailable, book_user_id, book_name);
				bdtos.add(bdto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return bdtos;
	}
	
	
	
	
	// searchResult 페이지 (검색 키워드에 해당되는 도서 목록)
	
	public ArrayList<BookVO> searchResultList(String findKeyword, int searchsize, int pagenum){
		Connection conn = null;
		ArrayList<BookVO> bdtos=new ArrayList<BookVO>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		int postnum=7;
		int page_div=searchsize/postnum;
		int page_mod=searchsize%postnum;
		int start=pagenum*postnum+1;
		int end=0;
		
		
		if(pagenum<0)
			pagenum=0;
		
		if(pagenum>page_div)
			pagenum=page_div;
		
		if(pagenum==page_div)
			end=start+page_mod;
		else
			end=(pagenum+1)*postnum;
		
		
		
		try {
			conn=dataSource.getConnection();
			String query="select * from (select book_id, book_author, book_publisher, book_subject, book_searchcnt, book_borrowcnt, book_isavailable, book_user_id, book_name, rownum as num from bookinfo where book_name like '%' || ? || '%') sub_bookinfo where num>=? and num <= ?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, findKeyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int book_id=rs.getInt("book_id");
				String book_author=rs.getString("book_author");
				String book_publisher=rs.getString("book_publisher");
				int book_subject=rs.getInt("book_subject");
				int book_searchcnt=rs.getInt("book_searchcnt");
				int book_borrowcnt=rs.getInt("book_borrowcnt");
				boolean book_isavailable=rs.getBoolean("book_isavailable");
				String book_user_id = rs.getString("book_user_id");
				String book_name=rs.getString("book_name");
				
				BookVO bdto=new BookVO(book_id, book_author, book_publisher, book_subject, book_searchcnt, book_borrowcnt, book_isavailable, book_user_id, book_name);
				bdtos.add(bdto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return bdtos;
	}
	
	
	
	
	// searchResult 페이지 (검색 키워드에 해당되는 도서 목록)
	
	public int getSearchResultSize(String findKeyword){
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int answer=0;
		
		try {
			
			conn=dataSource.getConnection();
			String query="select * from bookinfo where book_name like '%' || ? || '%' ";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, findKeyword);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				answer++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return answer;
	}
		
		
	
	// 사용자가 빌린 도서 출력 (mypage 페이지)
	
	public ArrayList<BookVO> userBorrowInfo(String user_id){
		Connection conn = null;
		ArrayList<BookVO> bdtos=new ArrayList<BookVO>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String query="select * from bookinfo where book_user_id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int book_id=rs.getInt("book_id");
				String book_author=rs.getString("book_author");
				String book_publisher=rs.getString("book_publisher");
				int book_subject=rs.getInt("book_subject");
				int book_searchcnt=rs.getInt("book_searchcnt");
				int book_borrowcnt=rs.getInt("book_borrowcnt");
				boolean book_isavailable=rs.getBoolean("book_isavailable");
				String book_user_id = rs.getString("book_user_id");
				String book_name=rs.getString("book_name");
				
				BookVO bdto=new BookVO(book_id, book_author, book_publisher, book_subject, book_searchcnt, book_borrowcnt, book_isavailable, book_user_id, book_name);
				bdtos.add(bdto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn !=null)
					conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return bdtos;
	}
}
