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

<title>所有意見反應單</title>
</head>
<body>
	<table>
		<tr>
			<td>意見反應單號</td>
			<td>反應日期</td>
			<td>反應內容</td>
			<td>反應狀態</td>
			<td>回應內容</td>
			<td>會員編號</td>
			
		</tr>

		<c:forEach var="sugestVO" items="${list}">

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
</body>
</html>