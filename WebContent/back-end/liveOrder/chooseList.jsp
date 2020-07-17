<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%@ page import="com.employee.model.*"%>
<!DOCTYPE html>
<html style="height: 100vh">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>MISS M-訂單管理</title>
<%@ include file="/back-end/back-end-head.jsp" %>
</head>
<body style="background-size:cover;" background="${pageContext.request.contextPath}/images/back-end/productImg/backProductBackground.jpg">
	
	<%@ include file="/back-end/back-end-header.jsp" %>
	
	<div class="row"></div>
	
	<div class="row justify-content-center" style="margin-top: 100px;">
			<div class="col-xl-4 chefPic ">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.8em; color:#000; background-color:#EEE; opacity:.9;">現場訂單</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/liveOrder/select_page.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/live.jpg" class="card-img-top" alt="..." style="width:400px;height:500px;">
				</a>
				</figure>
		    </div>
			
			<div class="col-xl-4 chefPic ">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.8em; color:#000; background-color:#EEE; opacity:.9;">線上訂單</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/ono/select_page.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/online.png" class="card-img-top" alt="..." style="width:400px;height:500px;">
				</a>
				</figure>
		    </div>
			
	</div>
	
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>