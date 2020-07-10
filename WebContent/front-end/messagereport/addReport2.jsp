<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.messageboard.model.*"%>
<%@ page import="com.messagereport.model.*"%>

<%
	MessageBoardVO mbVO = (MessageBoardVO) request.getAttribute("mbVO");//顯示要檢舉的留言用
%>
<%
	MessageReportVO mrVO = (MessageReportVO) request.getAttribute("mrVO");
%>

<%=mrVO == null%>--${mrVO.memno}--//line 100

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉資料新增</title>

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
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
textarea#detail{
width="800px" height="800px;"
}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
</head>
<body bgcolor='white'>

	
	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="messagereport.do" name="form1">
		<table>
			<c:if test="${not empty mbVO.postTitle}">
				<td>留言標題:</td>
				<td>${mbVO.postTitle} /></td>
			</c:if>
			
			<tr>
				<td>留言分類:</td>
				<td><%=mbVO.getPostSort() %></td>
			</tr>
			<tr>
				<td>留言內容:</td>
				<td><%=mbVO.getPostDetail() %></td>
			</tr>
				<td>留言時間:</td>
			<td><fmt:formatDate value="${mbVO.postTime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>

			<tr>
				<td>會員編號:</td>
				<td><%=mbVO.getMemno() %></td>
			</tr>
			<tr>
				<td>檢舉原因:</td>
				<td><input type="TEXT" name="reportdetail" size="45"
					placeholder="請填入檢舉原因"
					value="<%=(mrVO == null) ? "" : mrVO.getReportDetail() %>" /></td>
			</tr>
			<tr>
				<td>會員編號(暫先):</td>
				<td><input type="TEXT" name="memno" size="45"
					placeholder="請填入會員編號"
					value="<%=(mrVO == null) ? "" : mrVO.getMemno() %>" /></td>
			</tr>			
		</table>
		<br>
		<input type="hidden" name="postno" value="${mbVO.postno}">
		<input type="hidden" name="action" value="addReport">
		<input type="submit" value="送出檢舉">	</FORM>
		
		

<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>		
</body>
</html>