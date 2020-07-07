<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>MISS M會員中心</title>
<%@ include file="/front-end/front-end-head.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/member_center_n.css">

</head>
<body>
	<div class="area">
		<img alt="" style=" width:100%; height:100%; 
		background-image: url(${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg);">
	</div>
	
	<nav class="main-menu">
		<ul>
			<li style="padding:0 0 0 10; color:#FFF; display: ${(sessionScope.memVO == null) ? 'none':'display'}; height:50px;">
				<img style='width: 50px; height: 50px; border-radius: 50%;' src='${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}'>
				${sessionScope.memVO.mName } ${(sessionScope.memVO ==null) ? '':'您好~' }
			</li>
			
			<li>
				<a href="${pageContext.request.contextPath}/front-end/index.jsp">
					<i class="fa fa-home fa-2x"></i>
					<span class="nav-text">回首頁</span>
				</a>
			</li>
			
			<li>
				<a href="${pageContext.request.contextPath}/front-end/mem/listOneMem.jsp">
					<i class="fa fa-address-card" aria-hidden="true"></i>
					<span class="nav-text">個人資料</span>
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/front-end/mem/identify.jsp">
					<i class="fa fa-key" aria-hidden="true"></i>
					<span class="nav-text">重新驗證</span>
				</a>
			</li>
			
			<li class="has-subnav">
				<a href="#">
					<i class="fa fa-laptop fa-2x"></i>
					<span class="nav-text"> Stars Components </span>
				</a>

			</li>
			<li class="has-subnav">
				<a href="#">
					<i class="fa fa-list fa-2x"></i>
					<span class="nav-text"> Forms </span>
				</a>

			</li>
			<li class="has-subnav">
				<a href="#">
					<i class="fa fa-folder-open fa-2x"></i>
					<span class="nav-text"> Pages </span>
				</a>

			</li>
			<li>
				<a href="#">
					<i class="fa fa-bar-chart-o fa-2x"></i>
					<span class="nav-text"> Graphs and Statistics </span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class="fa fa-font fa-2x"></i>
					<span class="nav-text"> Quotes </span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class="fa fa-table fa-2x"></i>
					<span class="nav-text"> Tables </span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class="fa fa-map-marker fa-2x"></i>
					<span class="nav-text"> Maps </span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class="fa fa-info fa-2x"></i>
					<span class="nav-text"> Documentation </span>
				</a>
			</li>
		</ul>

		<ul class="logout">
			<li>
				<a href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do">
					<i class="fa fa-power-off fa-2x"></i>
					<span class="nav-text">Logout</span>
				</a>
			</li>
		</ul>
	</nav>



</body>
</html>