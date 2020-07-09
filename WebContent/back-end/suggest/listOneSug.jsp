<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.suggest.model.*"%>
<%
	SugestVO sugestVO = (SugestVO) request.getAttribute("sugestVO");
%>
<!DOCTYPE html>
<html>
<head>

<title>會員反應清單</title>

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
	width: 600px;
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

<body bgcolor='white'>

	<h4>
		<a href="listAllSug.jsp">回會員列表</a>
	</h4>

	<table>
		<tr>
			<td>意見反應單編號</td>
			<td>反應日期</td>
			<td>反應內容</td>
			<td>反應狀態</td>
			<td>回應內容</td>
			<td>會員編號</td>
		</tr>
		<tr>
			<td><%=sugestVO.getSuggestno()%></td>
			<td><%=sugestVO.getSuggestDate()%></td>
			<td><%=sugestVO.getSuggestDetail()%></td>
			<td><%=sugestVO.getResStatus()%></td>
			<td><%=sugestVO.getResponseDetail()%></td>
			<td><%=sugestVO.getMemno()%></td>
		</tr>
	</table>
<body>

</body>
</html>