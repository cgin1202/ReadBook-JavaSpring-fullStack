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
        <script language="Javascript" src="${path}/resources/js/userInfo.js" charset="UTF-8"></script>

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
        
        
        <!--
            Daum 우편번호 서비스
        -->
        <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        
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
                    <div class="col-2"></div>
                    <div class="col-8">
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
	                              <th style="width:45%;" scope="cols">도서명</th>
	                              <th style="width:25%;" scope="cols">저자</th>
	                              <th  style="width:20%;" scope="cols">출판사</th>
	                              <th  style="width:10%;" scope="cols"></th>
	                            </tr>
	                            </thead>
	                            <tbody>
	                            
	                            	<c:forEach items="${user_book_info}" var="userBookList">
				                            <tr style="vertical-align:center;">
				                            						                            
					                            	<td name="bookname">${userBookList.book_name}</td>
					                            	<td name="bookauthor">${userBookList.book_author}</td>
					                            	<td name="bookpublisher">${userBookList.book_publisher}</td>
					                            	<td name="bo"style="width:12%;">
				                            			<div id="index" style="font-size:12px; boder: 2px solid black;">
															<buttom type="button" class="cancelbook btn">대출취소</button>
														</div>
					                            	</td>
				                            	
						                   </tr>					                  
		                            </c:forEach>
	                            </tbody>
                        	</c:if>
                        </table>
                    </div>
                    
                    <div class="col-2"></div>
                </div>
            </div>

            <br><br>
            
            <div class="container-fluid" style="margin-top:10px; width:40%">
                <div class="row" style=" margin: 0 auto;">
                    <div class="col-6">
                        <div id="mypage">
                        <button type="button" onclick="javascript:window.location='<%=request.getContextPath() %>/mypage/user?id=${id}'" class="btn">개인정보수정</button>
                        </div>
                    </div>

                    <div class="col-6">
                        <div id="mypage">
                        <button type="button" onclick="javascript:window.location='<%=request.getContextPath() %>/mypage?id=${id}'" class="btn">취소</button>
                        </div>
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
    </body>

    <script>
    
    	// 새로운 창 (회원가입 아이디 중복 체크)
    	// 로직 : 새창을 열어서 페이지를 오픈 후 회원 아이디 정보를 가지고 중복체크
    	// 1. 아이디가 없으면 알림창과 진행 x
    	// 2. 아이디가 있으면 그것을 다음 페이지에 파라미터값으로 함께 전달
    	
    	function winOpen(){
    		
    		var popupX=(window.screen.width/2)-150;
    		var popupY=(window.screen.height/2)-100;
    		
    		window.open("<%=request.getContextPath() %>/user/id/1?userid="+document.createuser.id.value, "", "width=300, height=200, left="+popupX+", top="+popupY);
    	}
    	
    	
    	// 다음 우편번호 서비스
    	
        function daumPostCode() {
            new daum.Postcode({
                oncomplete: function(data) {
                	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById("addr").value = addr;
                    
                    // 수정 불가
                    document.getElementById("addr").readOnly=true;
                    
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("subaddr").focus();
                }
            }).open();
        }
    	
    	
   	
    // 버튼 클릭시 이벤트 처리 (Row 값 가져오기)
    
    $(".cancelbook").click(function(){
    	var checkBtn=$(this);
    	var tr=checkBtn.parent().parent().parent();
    	var td=tr.children();
    	var bookname=td.eq(0).text();
       	var author=td.eq(1).text();
       	var publisher=td.eq(2).text();
    	location.href="<%=request.getContextPath() %>/mypage/book/4?id=${id}&bookname="+bookname+"&author="+author+"&publisher="+publisher;
    })
    </script>
     
</html>