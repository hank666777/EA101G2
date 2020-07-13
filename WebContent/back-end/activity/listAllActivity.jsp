<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>
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
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row text-center">
			<div class="col">
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/activity_participation/activity_page.jsp">回活動管理首頁</a>
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
						<th>活動編號</th>
						<th>活動類別編號</th>
						<th>活動名稱</th>
						<th>活動描述</th>
						<th>活動照片</th>
						<th>活動總人數</th>
						<th>活動舉辦日期</th>
						<th>報名開始日期</th>
						<th>報名截止日期</th>
						<th>報名費</th>
						<th>活動狀態</th>
						<th>活動報名人數上限</th>
						<th>活動報名人數下限</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="ACVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${ACVO.actno}</td>
							<td>${ACVO.actTyno}</td>
							<td>${ACVO.actName}</td>
							<td>${ACVO.actDes}</td>
							<td><img
								src="<%=request.getContextPath()%>/back-end/activity/activitypicServlet.do?actno=${ACVO.actno}"></td>
							<td>${ACVO.actTalPeo}</td>
							<td>${ACVO.actHoDate}</td>
							<td>${ACVO.actStDate}</td>
							<td>${ACVO.actEdDate}</td>
							<td>${ACVO.actFee}</td>
							<td>${ACVO.actMode}</td>
							<td>${ACVO.actUpper}</td>
							<td>${ACVO.actLower}</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity/activityServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="actno" value="${ACVO.actno}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity/activityServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="actno" value="${ACVO.actno}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="/back-end/back-end-footer.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="page2.file"%>
</body>
</html>