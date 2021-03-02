<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.itcen.readbook.dao.UserDao" %>
<%@ page import="java.util.regex.Pattern" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ID 중복체크</title>
</head>
<body style="text-align:center;">
	<h2>아이디 중복 체크</h2>
	
	<br>
	
	<% 
	
	// 한글처리 & 파라미터 변수화
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("userid");
	
	
	// UserDao 객체 생성
	UserDao userdao=new UserDao();
	int result=-1;
	
	if(id==null || id.equals("")){
		result=-1;
	}else if(Pattern.matches("^[0-9a-zA-Z]*$", id)==true){
		result=userdao.joinIdCheck(id);
	}else{
		result=2;
	}
	
	
	if(result==1){
		out.print("사용가능한 아이디입니다.");
		
		// 아이디 사용하기 버튼 추가 (동기)
		%>
		<input type="button" value="ID 사용하기" onclick="result();">
		
		<%
	}else if(result==0){
		out.print("중복된 아이디입니다.");
	}
	else if(result==2){
		out.print("알파벳과 숫자로 구성된 ID를 입력해주세요.");
	}
	else{
		out.print("아이디를 입력해주세요.");
	}
	%>
	
	<br><br>
	
	<!-- 팝업창구현  -->
	
	<fieldset style="margin-left:auto; margin-right:auto;">
		<!-- action속성에 값이 없으면 기본적으로 자기자신을 불러오지만 중복확인 버튼을 클릭했을때 변경되지않는다.-->	
		<form action="<%= request.getContextPath()%>/user/id/1" method="post" name="idcheck">
			ID : <input type="text" name="userid" value="<%=id%>">
			<p></p>
			<input type="submit" value="중복 확인">
		</form>
	</fieldset>
	
	<!-- 
	선택된 아이디는 중복확인창에서 회원가입 페이지로 정보 전달
	 -->
	<script type="text/javascript">
	   function result(){
	   	//팝업창의 아이디정보를 회원가입창에 아이디정보로 전달
	   	//팝업창은 기존창과 종속관계를 가지고 있으므로 opener를 이용하면 된다.
	   	//alert("팝업창의 id값"+document.wfr.userid.value + ", 회원가입창의 id값 : " +opener.document.fr.id.value)
	   	opener.document.createuser.id.value = document.idcheck.userid.value;
	   	
	   	//회원가입창 제어 - readonly 속성제어
	   	opener.document.createuser.id.readOnly=true;
	   	
	   	window.close();
	   }
 
	</script>
</body>
</html>