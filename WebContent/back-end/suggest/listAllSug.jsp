<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.suggest.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.sql.Timestamp" %>
<%
	SugService sugSvc = new SugService();
	List<SugestVO> list = sugSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>

<title>意見反應清單</title>

<style>

  table {
	width: 1510x;
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
    width:150 px;
    background-color:#D2E9FF;
  }
  
  td{
    padding:0 px;
  }
  

</style>

</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<center>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<br><br><br>
	<table >
		<tr>
			<th>意見反應單號</th>
			<th>反應日期</th>
			<th>反應內容</th>
			<th>反應狀態</th>
			<th>回應內容</th>
			<th>會員編號</th>
			<th>修改</th>
		</tr>
  <%@ include file="page1.file" %>
		<c:forEach var="sugestVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${sugestVO.suggestno}</td>
				<td>${sugestVO.suggestDate} </td>
				<td>${sugestVO.suggestDetail}</td>
				<td>${sugestVO.resStatus}</td>
				<td>${sugestVO.responseDetail}</td>
				<td>${sugestVO.memno}</td>
				
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/suggest/suggest.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="suggestno"  value="${sugestVO.suggestno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file" %>
<%@ include file="/back-end/back-end-footer.jsp"%>	
</body>
</html>