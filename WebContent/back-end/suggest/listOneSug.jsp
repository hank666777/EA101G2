<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ page import="com.liveOrder.model.*"%>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%@ page import="com.suggest.model.*"%>
<%
	SugestVO sugestVO = (SugestVO) request.getAttribute("sugestVO");
%>
<!DOCTYPE html>
<html>
<head>

<title>會員反應清單</title>

<style>

 table {
	width: 1210x;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
	margin:auto;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
    
  }
  th {
    padding: 10px;
    width:120 px;
    background-color:#D2E9FF;
  }
  
  td{
    padding:0 px;
  }
  .button_size{
    height:40px;
    width:120;
  }
</style>

</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<center>
<body bgcolor='white'>
<%@ include file="/back-end/back-end-header.jsp" %>
	
<br><br><br>
	<table>
		<tr>
			<th>意見反應單編號</th>
			<th>反應日期</th>
			<th>反應內容</th>
			<th>反應狀態</th>
			<th>回應內容</th>
			<th>會員編號</th>
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
	<br>
<h4><a href="${pageContext.request.contextPath}/back-end/mem/listAllMem.jsp"><u>回會員列表</u></a></h4>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>