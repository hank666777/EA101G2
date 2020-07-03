<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
	Object obj = session.getAttribute("location");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Gentlmen X</title>
<link	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/back-end-index.css"	rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<div id="page">

		<div id="header">
			<div id="logo">
				<img width=100 height=100
							src="${pageContext.request.contextPath}/back-end/employee/epicshow.do?empno=${sessionScope.employeeVO.empno}" />
				<h1>${sessionScope.employeeVO.eName} 您好</h1>
				<span><a href="" rel="" id="changeName" onclick="show()"></a></span>
			</div>
			<div id="menu">
				<ul>
					<li class="current_page_item"><a
						href="${pageContext.request.contextPath}/back-end/employee/select_page_employee.jsp" accesskey="1"
						title="">員工管理</a></li>
					<li class="current_page_item"><a href="" accesskey="2"
						title="">會員管理</a></li>
					<li class="current_page_item"><a href="" accesskey="3"
						title="">出餐管理</a></li>
					<li class="current_page_item"><a href="" accesskey="4"
						title="">訂位與桌位管理</a></li>
					<li class="current_page_item"><a href="" accesskey="5"
						title="">活動管理</a></li>
					<li class="current_page_item"><a href="" accesskey="6"
						title="">直播管理</a></li>
					<li class="current_page_item"><a href="" accesskey="7"
						title="">餐點管理</a></li>
					<li class="current_page_item"><a href="" accesskey="8"
						title="">FAQ管理</a></li>
					<li><a href="" accesskey="9" title=""></a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="${pageContext.request.contextPath}/images/back-end/indexImg/cakeroll.png"
					alt="">
				<p>Hello~ Miss M</p>
			</div>
			<hr>
			<div id="search">
				<input type="text" name="" placeholder="搜尋..."><img
					src="${pageContext.request.contextPath}/images/back-end/indexImg/Magnifying-Glass-icon-1.png" alt="">
			</div>
			<hr>
			<div id="welcome">展示功能地區</div>
		</div>
	</div>
	
	
	<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>