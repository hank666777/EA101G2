<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activitypost.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%
	ActivitypostService actPSvc = new ActivitypostService();
	List<ActivitypostVO> list = actPSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有活動推文資料 - listAllActpost.jsp</title>
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
	width: 800px;
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
				<h3>所有活動推文資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/activity_participation/activitypost_page.jsp">回活動管理首頁</a>
					
				</h4>
				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<table>
					<tr>
						<th>活動文章編號</th>
						<th>活動編號</th>
						<th>會員編號</th>
						<th>活動推文日期</th>
						<th>活動推文內容</th>
						<th>活動推文照片</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="ACPVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${ACPVO.actPostno}</td>
							<td>${ACPVO.actno}</td>
							<td>${ACPVO.memno}</td>
							<td>${ACPVO.actPostDate}</td>
							<td>${ACPVO.actPostCon}</td>
							<td><img
								src="<%=request.getContextPath()%>/back-end/activity_post/activitypostpicServlet.do?actPostno=${ACPVO.actPostno}"></td>


							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_post/activitypostServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="actPostno" value="${ACPVO.actPostno}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_post/activitypostServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="actPostno" value="${ACPVO.actPostno}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>

				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>