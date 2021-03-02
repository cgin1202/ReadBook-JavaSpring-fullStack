<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html>
    <head>
        <meta content="text/html" charset="utf-8">
        <title>Read Book</title>
        <c:set var="path" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/index.css">
        <script language="Javascript" src="${path}/resources/js/userInfo.js" charset="UTF-8"></script>
		<script language="Javascript" src="${path}/resources/js/searchInfo.js" charset="UTF-8"></script>
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
    	
    	
    	<%@ page isELIgnored="false" %>
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
		
		
		<!-- 로그인이 되지 않았는데 책 빌리려고 할 경우 -->
		
		<c:if test="${param.msg==-3}">
			<script>
				swal("Notice","로그인이 필요합니다.");
			</script>
		</c:if>
		
		<!-- 로그인 성공 시 id를 session에 등록 -->
		 
		
		<c:if test="${param.msg==1}">
			<%
				session.setAttribute("sessionID",request.getParameter("sessionID"));
			%>
		</c:if>
		
		<!-- 로그인 실패 시 알람 시작  -->
		
		<c:if test="${param.msg==0}">
			<script>
				swal("Notice","패스워드가 틀렸습니다.");
			</script>
		</c:if>
		
		<c:if test="${param.msg==-1}">
			<script>
				swal("Notice","아이디가 틀렸습니다.");
			</script>
		</c:if>
		
		<c:if test="${param.borrow_msg==2}">
			<script>
				swal("Notice","도서 대출이 처리되었습니다.");
			</script>
		</c:if>
		
		<c:if test="${param.borrow_msg==-2}">
			<script>
				swal("Notice","빌린 도서가 3권이 넘습니다.");
			</script>
		</c:if>
		
		<!-- 로그인 실패 시 알람 끝  -->

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
	            <form action="<%=request.getContextPath() %>/login" method="post" name="loginuser">
	                <div class="container-fluid">
	                    <div class="row">
	                        <div class="col-5 user">
	                            <input id="userinput" type="text" style="width:100%" name="id" placeholder="ID를 입력하세요.">
	                            <input id="userinput" type="password" style="width:100%; margin-top:10px;" name="pw" placeholder="PW를 입력하세요."> 
	                        </div>
	
	
	                        <div class="col-3 user">
	                            <input id="usercheck" type="button" style="width:130%; "onclick="userLoginConfirm()" value="로그인">
	                            <input id="usercheck" type="button" style="margin-top:10px;" onclick="javascript:window.location='<%=request.getContextPath() %>/user'" value="회원가입" ">
	                        </div>
	                    </div>
	                </div>
	            </form>
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
            
            <form action="<%=request.getContextPath() %>/book/1" method="post" name="searchword">
            	<div id="search">
	            <span class='green_window'>
	                <input type='text' name="keyword" class='input_text' style="text-align:left; height:21px; width:350px; margin:6px 0 0 9px; line-height:21px; font-size:16px; border:none;"/>
	            	<br>
	            	<input type="hidden" name="pagenum" value='0'/>
	            </span>
	            <button type='button' onclick="searchKeywordCheck()" class='sch_smit'>도서 검색</button>
	            </div>
            </form>
		
            <div class="container-fluid" style="margin-top:100px;">
                <div class="row">
                    <div class="col-5">
                        <h3>최다 조회된 도서 TOP 5</h3>
                        <br>
                        <table class="type09" style="margin:0 auto; text-align:center;">
                            <thead>
	                            <tr style="font-size:15px;">
	                              <th scope="cols">도서 이름</th>
	                              <th scope="cols">저자</th>
	                              <th scope="cols">출판사</th>
	                              <th scope="cols">조회 수</th>
	                              <th scope="cols"></th>
	                            </tr>
                            </thead>
                            <tbody style="font-size:12px;">
                            	<c:forEach items="${searchTopList}" var="searchList">
	                            <tr style="vertical-align:center;">
	                            	<td>${searchList.book_name}</td>
	                            	<td style="width:15%;">${searchList.book_author}</td>
	                            	<td style="width:15%;">${searchList.book_publisher}</td>
	                            	<td style="width:12%;">${searchList.book_searchCnt}</td>
	                            	<td style="width:12%;">
	                            		<c:if test="${searchList.book_isAvailable==true}">
	                            			<div id="index" style="font-size:12px; boder: 2px solid black;">
												<button type="button" class="btn">대출</button>
											</div>
										</c:if>
	                            	</td>
	
	                            </tr>
	                            </c:forEach>
                            </tbody>
                          </table>
                    </div>
					<div class="col-2"></div>
                    <div class="col-5">
                        <h3>최다 대출된 도서 TOP 5</h3>
                        <br>
                        <table class="type09" style="margin:0 auto; text-align:center;">
                            <thead>
                            <tr style="font-size:15px;">
                              <th scope="cols">도서 이름</th>
                              <th scope="cols">저자</th>
                              <th scope="cols">출판사</th>
                              <th scope="cols">대출 수</th>
                              <th scope="cols"></th>
                            </tr>
                            </thead>
                            <tbody style="font-size:12px;">
                            	<c:forEach items="${borrowTopList}" var="borrowList">
	                            <tr style="vertical-align:center;">
	                            	<td>${borrowList.book_name}</td>
	                            	<td style="width:15%;">${borrowList.book_author}</td>
	                            	<td style="width:15%;">${borrowList.book_publisher}</td>
	                            	<td style="width:12%;">${borrowList.book_borrowCnt}</td>
	                            	<td style="width:12%;">
	                            		<c:if test="${borrowList.book_isAvailable==true}">
	                            			<div id="index" style="font-size:12px; boder: 2px solid black;">
												<button type="button" class="btn">대출</button>
											</div>
										</c:if>
	                            	</td>
	
	                            </tr>
	                            </c:forEach>
                            </tbody>
                          </table>
                    </div>
                </div>
            </div>
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
        
        <script>
		    // 버튼 클릭시 이벤트 처리 (Row 값 가져오기)
		        
	        $(".btn").click(function(){
	        	
	        	var checkBtn=$(this);
	        	var tr=checkBtn.parent().parent().parent();
	        	var td=tr.children();
	        	var bookname=td.eq(0).text();
	        	var author=td.eq(1).text();
	        	var publisher=td.eq(2).text();
	        	location.href="<%=request.getContextPath() %>/book/2?id=${id}&bookname="+bookname+"&author="+author+"&publisher="+publisher;
	        })
        </script>
    </body>
</html>