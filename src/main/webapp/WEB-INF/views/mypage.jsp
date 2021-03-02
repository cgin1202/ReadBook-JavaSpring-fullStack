<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
        <title></title>
        <meta content="text/html" charset="utf-8">
        <title>Read Book</title>
        <c:set var="path" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/index.css">

        <!--
			부트스트랩 Import
		-->

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        
        <!--
            SweetAlert Import
        -->

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        
    </head>
    
    
    <body>
        
        <div id="wapper">
        <!--
            header 구간 시작
            구성요소 : 사이트 이름(로고)
        -->
        
        <header>
            <br/>
            <a href="<%=request.getContextPath() %>/main">
                <img src="${path}/resources/img/logo.png" alt="system logo">
            </a>
        </header>
         <!--
            header 구간 끝
        -->


        <!--
            nav 구간 시작
            구성요소 : 로그인 창, 로그인 버튼, 회원가입 버튼
        -->
        
        <%
        
        	// session ID 정보가 없을 경우 로그인 창
        	// 정보가 있을 경우 로그인창을 없애기
        	
        	if(session.getAttribute("sessionID")==null){	
       	%>
       		<nav>
	            <script>
	            	swal("Notice","로그인이 필요합니다.");
	            	location.href="<%=request.getContextPath() %>/main";
	            </script>
	        </nav>
       	<%
        	}else{
        %>
        	<nav>
        		<%
        			request.setAttribute("id", session.getAttribute("sessionID"));
        		%>
        	
        		<p style="font-size:18px;"> 접속 ID &nbsp&nbsp&nbsp : &nbsp&nbsp&nbsp <%= session.getAttribute("sessionID") %> </p>
        		<br>
       			<div class="container-fluid">
                    <div class="row">
                        <div class="col-4 user">
							<a href="<%=request.getContextPath() %>/mypage?id=${id}"><img src="${path}/resources/img/mypage.png" alt="mypage" width="70px" height="70px"></a>
						</div>				
						<div class="col-2 user">
						</div>			
                        <div class="col-4 user">
                            <a href="<%=request.getContextPath() %>/logout"><img src="${path}/resources/img/logout.png" alt="logout" width="70px" height="70px"></a>
                        </div>
                    </div>
                </div>
     
        	</nav>
        <%
        	}
        %>
        
		<c:if test="${param.cancel_msg==2}">
			<script>
				swal("Notice","도서 반납 처리되었습니다.");
			</script>
		</c:if>
         <!--
            nav 구간 끝
        -->

        <br><br><br>
        <hr align="center" style="margin-top:5%; width:80%; border:solid 2px #e0e7c0;">
        <br><br><br>
        
        
        <!--
            section 구간 시작
            구성요소 : 이번달 가장 클릭많이한 도서, 이번달 가장 많이 빌린 도서
        -->
        
        <section>
       	
            <div class="container-fluid" style="margin-top:100px;">
                <div class="row">
                    <div class="col-6">
                        <h3> 개인 정보 </h3>
                        <br>
                        <table class="type09" style="margin:0 auto; text-align:center;">
                            <thead>
	                            <tr>
	                              <th style="width:30%;" scope="cols">분류</th>
	                              <th style="width:70%;" scope="cols">내용</th>
	                            </tr>
                            </thead>
                            <tbody>
	                            <tr style="vertical-align:center;">
	                            	<td>ID</td>
	                            	<td>${user_info[0].user_id}</td>
	                            </tr>
	                            
	                            <tr style="vertical-align:center;">
	                            	<td>PW</td>
	                            	<td>${user_info[0].user_pw}</td>
	                            </tr>
	                            
	                            <tr style="vertical-align:center;">
	                            	<td>Address</td>
	                            	<td>${user_info[0].user_addr}</td>
	                            </tr>
	                            
	                            <tr style="vertical-align:center;">
	                            	<td>PhoneNumber</td>
	                            	<td>${user_info[0].user_phoneNum}</td>
	                            </tr>
	                            
	                            
                            </tbody>
                          </table>
                    </div>

                    <div class="col-6">
                        <h3>대여한 도서 목록</h3>
                        <br>
                        <table class="type09" style="margin:0 auto; text-align:center;">
                        	<c:set var="infoList" value="${fn:length(user_book_info)}"/>
                        	
                        	
                        	
                        	<c:if test="${infoList == 0}">
                        		<br><br>
                        		<p>대여한 도서가 없습니다.</p>
                        		<br><br>
                        	</c:if>
                        	
                        	<c:if test="${infoList != 0}">
                        		<thead>
	                            <tr>
	                              <th style="width:50%;" scope="cols">도서명</th>
	                              <th style="width:30%;" scope="cols">저자</th>
	                              <th  style="width:20%;" scope="cols">출판사</th>
	                            </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach items="${user_book_info}" var="userBookList">
			                            <tr style="vertical-align:center;">
			                            	<td>${userBookList.book_name}</td>
			                            	<td>${userBookList.book_author}</td>
			                            	<td>${userBookList.book_publisher}</td>
					                   </tr>
		                            </c:forEach>
	                            </tbody>
                        	</c:if>
                        </table>
                    </div>
                </div>
            </div>

            <br><br><br><br>
            
            <div class="container-fluid" style="margin-top:10px; width:40%">
                <div class="row" style=" margin: 0 auto;">
                    <div class="col-6">
                        <div id="mypage">
                        <button type="button" onclick="javascript:window.location='<%=request.getContextPath() %>/mypage/user?id=${id}'  " class="btn">개인정보수정</button>
                        </div>
                    </div>

                    <div class="col-6">
                        <div id="mypage">
                        <button type="button" onclick="javascript:window.location='<%=request.getContextPath() %>/mypage/book?id=${id}'  " class="btn">도서대출취소</button>
                        </div>
                    </div>
                </div>
            </div>
            <br><br>
        </section>


         <!--
            section 구간 끝
        -->
        

        <!---
            footer 구간 시작
            구성요소 : 회사로고, 개발자 이메일 주소, 저작권
        -->
        <br><br>
        <hr align="center" style="margin-top:5%; width:80%; border:solid 2px #e0e7c0;">
        <br>

        <div class="footer_container">
        <footer>
            <img src="${path}/resources/img/itcen.png" alt="itcen logo">
            
            <br><br>

            <p>이메일 주소 : chltmdwls911@gmail.com </p>
            <p>Copyright © 2021 Choi Seungjin. All rights reserved</p>
        </footer>
        </div>
        <!---
            footer 구간 끝
        -->
        </div>
    </body>
</html>