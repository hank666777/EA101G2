<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actparticipation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<%
	ParticipationService PVS = new ParticipationService();
	List<ParticipationVO> list = PVS.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有活動報名紀錄</title>
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
							<h3>所有活動報名紀錄資料</h3>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back-end/activity_participation/activityparticipation_page.jsp">回首頁</a>
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
						<th>活動報名編號</th>
						<th>會員編號</th>
						<th>活動編號</th>
						<th>活動報名時間</th>
						<th>活動報名人數</th>
						<th>報名總費用</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="PVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1 %>">
						<tr>
							<td>${PVO.avPartno}</td>
							<td>${PVO.memno}</td>
							<td>${PVO.actno}</td>
							<td>${PVO.actPatTime}</td>
							<td>${PVO.actParEnr}</td>
							<td>${PVO.actTalFee}</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_participation/actparticipationServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="avPartno" value="${PVO.avPartno}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_participation/actparticipationServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="avPartno" value="${PVO.avPartno}"> <input
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