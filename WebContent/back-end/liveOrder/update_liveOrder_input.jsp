<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.liveOrder.model.*"%>

<%
	LiveOrderVO loVO = (LiveOrderVO)request.getAttribute("loVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>現場訂單資料修改</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<div id="container"  style="width:600px;margin:50px auto;border:2px solid black">

<table id="table-1">
	<tr><td>
		 <h3>現場訂單資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" name="form1">
<table>
	<tr>
		<td>現場訂單編號:<font color=red><b>*</b></font></td>
		<td><%=loVO.getLiveOrderno()%></td>
	</tr>
	<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />	
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="empno">
			<c:forEach var="empVO" items="${empSvc.all}">
				<option value="${empVO.empno}" ${(loVO.empno==empVO.empno)? 'selected':'' } >${empVO.eName}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>桌位編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="tableno" size="45" value="<%=loVO.getTableno()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單時間:<font color=red><b>*</b></font></td>
		<td><input type="text" name="liveOrderTime" size="45" value="<%=loVO.getLiveOrderTime()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單總價:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="liveOrderTotal" size="45" value="<%=loVO.getLiveOrderTotal()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單付款狀態:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="liveOrderPayment" size="45" value="<%=loVO.getLiveOrderPayment()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單狀態:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="liveOrderStatus" size="45" value="<%=loVO.getLiveOrderStatus()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="liveOrderno" value="<%=loVO.getLiveOrderno()%>">
<input type="submit" value="送出修改"></FORM>

</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>