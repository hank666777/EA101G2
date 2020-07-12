<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="com.tabless.model.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%@ page import="com.employee.model.*" %>
<%
	List<BokdtVO> list = (List<BokdtVO>)request.getAttribute("BokDetailList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liveOrderMultiQuery2</title>
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="app.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_end_header.css"/>
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>

<body>

<%@ include file="/back-end/back-end-header.jsp" %>

	<h3>複合查詢結果</h3>
	<table>
		<tr>
			<th>日期</th>
			<th>時段</th>		
			<th>桌號</th>
			<th></th>
		</tr>	
		<% for (BokdtVO bokdtVO : list) {%>
		<tr>
			<td><%=bokdtVO.getBkDate()%></td>
			<td><%=bokdtVO.getBkPeriod()%></td>
			<td><%=bokdtVO.getTableno()%></td>
			<td>
				
					<input type="hidden" name="bkdate" value="<%=bokdtVO.getBkDate()%>">
					<input type="hidden" name="bkperiod" value="<%=bokdtVO.getBkPeriod()%>">
				<form action="<%=request.getContextPath()%>/product/TablenoServlet.do">
					<input type="hidden" name="tableno" value="<%=bokdtVO.getTableno()%>">
					<input type="hidden" name="action" value="confirm">
					<input type="submit" name="Submit" value="點餐">
				</form>
			</td>
		</tr>
		<%}%>
	</table>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>