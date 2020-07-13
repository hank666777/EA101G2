<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivityVO actVO = (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>活動資料 - listOneactivity.jsp</title>

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


						<h3>活動資料 </h3>
						<h4>
							<a href="<%=request.getContextPath()%>/back-end/activity/activity_page.jsp">回活動管理首頁</a>
						</h4>



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
					</tr>
					<tr>
						<td><%=actVO.getActno()%></td>
						<td><%=actVO.getActTyno()%></td>
						<td><%=actVO.getActName()%></td>
						<td><%=actVO.getActDes()%></td>
						<td><%=actVO.getActPic()%></td>
						<td><%=actVO.getActTalPeo()%></td>
						<td><%=actVO.getActHoDate()%></td>
						<td><%=actVO.getActStDate()%></td>
						<td><%=actVO.getActEdDate()%></td>
						<td><%=actVO.getActFee()%></td>
						<td><%=actVO.getActMode()%></td>
						<td><%=actVO.getActUpper()%></td>
						<td><%=actVO.getActLower()%></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>