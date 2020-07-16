<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.activitypost.model.*"%>
<%@ page import="com.actparticipation.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<html style="height: 100vh">
<head>
	<title>MISS M-活動管理</title>
	<%@ include file="/back-end/back-end-head.jsp" %>
</head>
<body>

	<%@ include file="/back-end/back-end-header.jsp" %>

		<div class="row"></div>

		<div class="row justify-content-center">
			<div class="col-xl-3 chefPic ">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.3em;margin-top:200px;">活動新增查詢</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/activity/activity_page.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/start.jpeg" class="card-img-top" alt="...">
				</a>
				</figure>
			</div>
			
			<div class="col-xl-3 chefPic">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.3em;margin-top:200px;">活動心得新增查詢</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/activity_post/activitypost_page.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/elephant.jpeg" class="card-img-top" alt="...">
				</a>
				</figure> 
			</div>
			
			<div class="col-xl-3 chefPic">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.3em;margin-top:200px;">活動報名新增查詢</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/activity_participation/activityparticipation_page.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/catengl.jpeg" class="card-img-top" alt="...">
				</a>
				</figure> 
			</div>
		</div>
		
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>

</body>
</html>