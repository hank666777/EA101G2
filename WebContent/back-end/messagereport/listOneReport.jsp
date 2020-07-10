<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.messagereport.model.*"%>
<%@ page import="com.messageboard.model.*"%>


<%
	MessageReportVO mrVO = (MessageReportVO) request.getAttribute("mrVO");
	MessageBoardVO mbVO = (MessageBoardVO) request.getAttribute("mbVO");
%>

<html>
<head>
<title>檢舉資料顯示</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 80%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	width: 200px;
	padding: 5px;
	text-align: center;
}
</style>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/messagereport/update_report.css"
	type="text/css" />


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
</head>
<body bgcolor='white'>

	<div class="container">
		<div class="wrapper">
			<h2 class="text-center">審核相關資料如下:</h2>
			<div class="data_area">
				<table id="report_info">
					<tr>
						<th>檢舉資料</th>
					</tr>
					<tr>
						<th >檢舉編號:</th>
						<td>${mrVO.reportno}</td>
					</tr>
					<tr>
						<th>檢舉理由:</th>
						<td>${mrVO.reportDetail}</td>
					</tr>
					<tr>
						<th>檢舉時間:</th>
						<td>
							<fmt:formatDate value="${mrVO.reportTime}"
								pattern="yyyy-MM-dd hh:mm" />
						</td>
					</tr>
					<tr>
						<th>
							留言顯示狀態:<font color=red><b>*</b></font>
						</th>
						<td>
							<c:if test="${mbVO.postStatus==0}">已隱藏 </c:if>
							<c:if test="${mbVO.postStatus==1}">顯示 </c:if>
						</td>
					</tr>
					<tr>
						<th>
							檢舉處理狀態:<font color=red><b>*</b></font>
						</th>
						<td>
							<c:if test="${mrVO.reportStatus==0}">未處理 </c:if>
							<c:if test="${mrVO.reportStatus==1}">已處理通過 </c:if>
							<c:if test="${mrVO.reportStatus==2}">已處理未通過</c:if>
						</td>
					</tr>
					<input type="submit" value="返回審核表單"
							onclick="location.href='<%=request.getContextPath()%>/back-end/messagereport/listAllMessageReport.jsp'">
				</table>
			</div>

		</div>
	</div>


	<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>