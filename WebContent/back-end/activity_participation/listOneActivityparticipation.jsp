<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.actparticipation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ParticipationVO PVO = (ParticipationVO) request.getAttribute("PVO");
%>

<html>
<head>
<title>活動報名紀錄資料</title>
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
				<h3>活動報名紀錄資料-listOneactivityparticipation.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/activity_participation/activityparticipation_page.jsp">回首頁</a>
				</h4>

				<table>
					<tr>
						<th>活動報名編號</th>
						<th>會員編號</th>
						<th>活動編號</th>
						<th>活動報名時間</th>
						<th>活動報名人數</th>
						<th>報名總費用</th>
					</tr>
					<tr>
						<td><%=PVO.getAvPartno()%></td>
						<td><%=PVO.getMemno()%></td>
						<td><%=PVO.getActno()%></td>
						<td><%=PVO.getActPatTime()%></td>
						<td><%=PVO.getActParEnr()%></td>
						<td><%=PVO.getActTalFee()%></td>
					</tr>
				</table>

			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>