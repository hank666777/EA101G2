<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.activitypost.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivitypostVO actPVO = (ActivitypostVO) request.getAttribute("actPVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>活動推文資料 - listOneactivitypost.jsp</title>

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
	width: 750px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row text-center">
			<div class="col">
				<h3>活動資料 - ListOneActivitypost.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/activity_post/activitypost_page.jsp">回首頁</a>
				</h4>
				<table>
					<tr>
						<th>活動文章編號</th>
						<th>活動編號</th>
						<th>會員編號</th>
						<th>活動推文日期</th>
						<th>活動推文內容</th>
						<th>活動推文照片</th>
					</tr>
					<tr>
						<td><%=actPVO.getActPostno()%></td>
						<td><%=actPVO.getActno()%></td>
						<td><%=actPVO.getMemno()%></td>
						<td><%=actPVO.getActPostDate()%></td>
						<td><%=actPVO.getActPostCon()%></td>
						<td><%=actPVO.getActPostPic()%></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>