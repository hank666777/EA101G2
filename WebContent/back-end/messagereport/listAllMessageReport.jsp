<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.messagereport.model.*"%>

<%
	MessageReportService mrSvc = new MessageReportService();
	List<MessageReportVO> list = mrSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>檢舉list</title>

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
	width: 100%;
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

tr {
	height: 50px;
}

#title {
	margin: 50px;
}

.data_area{
	background-color:lightblue;
	padding:10px;
	border:5px solid lightblue;
	border-radius:10px;
}
</style>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="container">
		<div class="wrapper">
			<div class="report_main">
				<div class="text-center" id="title">
					<h2>檢舉留言列表</h2>
				</div>
				<div class="data_area">
					<table class="text-center">
						<tr>
							<th>檢舉編號</th>
							<th>檢舉理由</th>
							<th>檢舉時間</th>
							<th>檢舉處理狀態</th>
							<th>留言編號</th>
							<th>會員(檢舉者)編號</th>
							<th>審核</th>

						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="mrVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">

							<tr>
								<td>${mrVO.reportno}</td>
								<td>${mrVO.reportDetail}</td>
								<td><fmt:formatDate value="${mrVO.reportTime}"
										pattern="yyyy-MM-dd hh:mm" /></td>

								<td><c:if test="${mrVO.reportStatus == 0}">
										<p style="color: blue; font-weight: bold">未審核
									</c:if> <c:if test="${mrVO.reportStatus == 1}">已審核通過</c:if> <c:if
										test="${mrVO.reportStatus == 2}">已審核未通過</c:if></td>
								<td>${mrVO.postno}</td>
								<td>${mrVO.memno}</td>

								<td><c:if test="${mrVO.reportStatus == 0}">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/messagereport/messagereport.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="審核"> <input type="hidden"
												name="reportno" value="${mrVO.reportno}"> <input
												type="hidden" name="postno" value="${mrVO.postno}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</c:if> 
									<c:if test="${mrVO.reportStatus != 0}">已審核</c:if></td>

							</tr>
						</c:forEach>
					</table>
					<%@ include file="page2.file"%>
				</div>


			</div>
		</div>
	</div>


	<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>