<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.activity.model.*"%>
<%@ page import="com.activitypost.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>確認送出</title>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
	<div class="container">
		<div class="row text-center">
			<div class="col">
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/activity/front_page.jsp">回活動首頁</a>
				</h4>
				<h1>感謝您的參與，期待活動上相見</h1>
			</div>
		</div>
	</div>
	<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>