<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.messagereport.model.*"%>
<%@ page import="com.mem.model.*"%>



<%  
	MemVO ssVO1 = (MemVO)session.getAttribute("memVO");
	
	MessageReportService mrSvcR = new MessageReportService();
	List<MessageReportVO> mrlistReport = mrSvcR.getByMemno(ssVO1.getMemno());
	pageContext.setAttribute("mrlistReport",mrlistReport);
%>


<html>
<head>
<title>Miss M MessageBoard</title>



<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/listmessageboard.css" type="text/css" /> --%>

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
.report_detail{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	width:150px;
}
</style>

</head>

<body>
<%-- ${not empty sessionScope.memVO}	 --%>
<%-- ${sessionScope.memVO.memno } --%>
<%-- ${not empty mblist}	 --%>

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
					<h2>會員檢舉列表</h2>
				</div>
				<div class="data_area">
					<table class="text-center tablb-sm text-nowrap">
						<tr>
							<th>檢舉編號</th>
							<th>檢舉理由</th>
							<th>檢舉時間</th>
							<th>檢舉處理狀態</th>
							<th>留言編號</th>
							<th>檢舉者</th>
							
						</tr>
<%-- 						<%@ include file="page1.file"%>  --%>
						<c:forEach var="mrVO" items="${mrlistReport}">

							<tr>
								<td>${mrVO.reportno}</td>
								<td><div class="report_detail">${mrVO.reportDetail}</div></td>
								<td><fmt:formatDate value="${mrVO.reportTime}"
										pattern="yyyy-MM-dd HH:mm" /></td>

								<td style="width:10%;"><c:if test="${mrVO.reportStatus == 0}">
										<div style="color: blue; font-weight: bold; vertical-align:middle">尚未審核</div>
									</c:if> 
									<c:if test="${mrVO.reportStatus == 1}">已審核通過</c:if> 
									<c:if test="${mrVO.reportStatus == 2}">已審核未通過</c:if>
								</td>
								<td>${mrVO.postno}</td>
								
								<c:if test="${mrVO.memno == sessionScope.memVO.memno}">
								<td>${memVO.mName}</td>
								</c:if>

							

							</tr>
						</c:forEach>
					</table>
<%-- 					<%@ include file="page2.file"%> --%>
				</div>


			</div>
		</div>
	</div>

</body>
</html>

