<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setStatus(404); %>
<!DOCTYPE html>
<html>
	<head>
        <title></title>
        <meta content="text/html" charset="utf-8">
        <title>Read Book</title>
        <c:set var="path" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${path}/resources/css/error.css">
        <script type="text/javascript" src="${path}/resources/js/error.js"></script>
    	<link rel="stylesheet" type="text/css" href="../../resources/css/error.css">
        <script type="text/javascript" src="../../resources/js/error.js"></script>
    </head>
	<body>
	    <div class="container">
	        <div class="error">
	            <h1>404</h1>
	            <h2>error</h2>
	            <p>요청한 페이지가 존재하지 않습니다.</p>
	            <p>주소를 올바르게 입력했는지 확인부탁드립니다.</p>
	        </div>
	        <div class="stack-container">
	            <div class="card-container">
	                <div class="perspec" style="--spreaddist: 125px; --scaledist: .75; --vertdist: -25px;">
	                    <div class="card">
	                        <div class="writing">
	                            <div class="topbar">
	                                <div class="red"></div>
	                                <div class="yellow"></div>
	                                <div class="green"></div>
	                            </div>
	                            <div class="code">
	                                <ul>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="card-container">
	                <div class="perspec" style="--spreaddist: 100px; --scaledist: .8; --vertdist: -20px;">
	                    <div class="card">
	                        <div class="writing">
	                            <div class="topbar">
	                                <div class="red"></div>
	                                <div class="yellow"></div>
	                                <div class="green"></div>
	                            </div>
	                            <div class="code">
	                                <ul>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="card-container">
	                <div class="perspec" style="--spreaddist:75px; --scaledist: .85; --vertdist: -15px;">
	                    <div class="card">
	                        <div class="writing">
	                            <div class="topbar">
	                                <div class="red"></div>
	                                <div class="yellow"></div>
	                                <div class="green"></div>
	                            </div>
	                            <div class="code">
	                                <ul>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="card-container">
	                <div class="perspec" style="--spreaddist: 50px; --scaledist: .9; --vertdist: -10px;">
	                    <div class="card">
	                        <div class="writing">
	                            <div class="topbar">
	                                <div class="red"></div>
	                                <div class="yellow"></div>
	                                <div class="green"></div>
	                            </div>
	                            <div class="code">
	                                <ul>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="card-container">
	                <div class="perspec" style="--spreaddist: 25px; --scaledist: .95; --vertdist: -5px;">
	                    <div class="card">
	                        <div class="writing">
	                            <div class="topbar">
	                                <div class="red"></div>
	                                <div class="yellow"></div>
	                                <div class="green"></div>
	                            </div>
	                            <div class="code">
	                                <ul>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="card-container">
	                <div class="perspec" style="--spreaddist: 0px; --scaledist: 1; --vertdist: 0px;">
	                    <div class="card">
	                        <div class="writing">
	                            <div class="topbar">
	                                <div class="red"></div>
	                                <div class="yellow"></div>
	                                <div class="green"></div>
	                            </div>
	                            <div class="code">
	                                <ul>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</body>
</html>