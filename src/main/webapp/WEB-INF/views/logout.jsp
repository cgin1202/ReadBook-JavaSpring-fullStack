<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	session.invalidate();
	//System.out.println("path : "+request.getContextPath());
	response.sendRedirect(request.getContextPath()+"/main");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>

</body>
</html>