package com.itcen.readbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.itcen.readbook.vo.UserVO;

public class UserDao {

	private DataSource dataSource;
	private static UserDao instance;
	
	public UserDao() {
		try {
			
			Context initContext=new InitialContext();
			Context envContext=(Context)initContext.lookup("java:comp/env");
			dataSource=(DataSource)envContext.lookup("jdbc/orcl");
			
			/*
			Context initContext=new InitialContext();
			// context의 lookup 메소드를 이용해서 "java:comp/env"에 해당하는 객체를 찾아서 context에 삽입
			Context envContext=(Context)initContext.lookup("java:comp/env");
			// envContext의 lookup 메소드를 이용해서 "jdbc/orcl"에 해당하는 객체를 찾아서 ds 삽입
			dataSource=(DataSource)envContext.lookup("jdbc/orcl");
			*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 싱글톤 패턴
	public static UserDao getInstance() {
		if(instance==null)
			instance=new UserDao();
		return instance;
	}
	
	
	// 개인 정보 수정 페이지의 default value, id에 해당하는 user 정보 출력
	public UserVO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String query="select * from members where id=?";
		UserVO udto=null;
		
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			set=pstmt.executeQuery();
			
			if(set.next()) {
				//System.out.println("여기오는지?? check");
				udto=new UserVO();
				udto.setUser_id(set.getString("user_id"));
				udto.setUser_pw(set.getString("user_pw"));
				udto.setUser_name(set.getString("user_name"));
				udto.setUser_addr(set.getString("user_addr"));
				udto.setUser_phoneNum(set.getString("user_phonenum"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				set.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return udto;
	}
	
	
	// 회원가입 (USER INSERT) 메소드
	public void write(String id, String pw, String name, String addr, String phoneNum) {
		PreparedStatement preparedStatement=null;
		Connection conn = null;
		try {
			String query="insert into userinfo(user_id, user_pw, user_name, user_address, user_phonenum)"
					+ "values(?,?,?,?,?)";
			conn=dataSource.getConnection();
			preparedStatement=conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, addr);
			preparedStatement.setString(5, phoneNum);
			
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
	
	
	
	// 개인정보 수정 (USER UPDATE) 메소드
		public void update(String id, String pw, String name, String addr, String phoneNum) {
			PreparedStatement preparedStatement=null;
			Connection conn = null;
			try {
				String query="update userinfo set user_pw=?, user_name=?, user_address=?, user_phonenum=? where user_id=?";
				conn=dataSource.getConnection();
				preparedStatement=conn.prepareStatement(query);
				preparedStatement.setString(1, pw);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, addr);
				preparedStatement.setString(4, phoneNum);
				preparedStatement.setString(5, id);
				
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
		
		
	// 회원가입 (아이디 중복체크 메소드)
	public int joinIdCheck(String id) {
		int result=-1;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		Connection conn = null;
		try {
			String query="select user_id from userinfo where user_id=?";
			conn=dataSource.getConnection();
			preparedStatement=conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			rs=preparedStatement.executeQuery();
			
			if(rs.next())
				result=0;
			else
				result=1;
			
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
		
		
		return result;
	}

	
	// 로그인 (아이디, 비밀번호 일치 여부 체크 메소드)
	public int loginCheck(String id, String pw) {
		
		int result=-1;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		Connection conn = null;
		try {
			String query="select * from userinfo where user_id=?";
			conn=dataSource.getConnection();
			preparedStatement=conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			rs=preparedStatement.executeQuery();
			
			if(rs.next()) {
				String dbPw=rs.getString("user_pw");
				if(dbPw.equals(pw))
					result=1;
				else
					result=0;
			}else
				result=-1;
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
		return result;
	}
	
// Userinfo 정보 출력 메소드
public ArrayList<UserVO> getUserInfo(String id){
		Connection conn = null;
		ArrayList<UserVO> udtos=new ArrayList<UserVO>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String query="select * from userinfo where user_id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {

				String user_id=rs.getString("user_id");
				String user_pw=rs.getString("user_pw");
				String user_name=rs.getString("user_name");
				String user_address=rs.getString("user_address");
				String user_phonenum=rs.getString("user_phonenum");
				
				UserVO udto=new UserVO(user_id, user_pw, user_name, user_address, user_phonenum);
				udtos.add(udto);
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
		return udtos;
	}
}
