<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% 
	LiveOrderVO loVO = (LiveOrderVO)request.getAttribute("loVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
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
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<div id="container"  style="width:600px;margin:50px auto;border:2px solid black">

<table id="table-1">
	<tr><td>
		 <h3>現場訂單資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>現場訂單編號</th>
		<th>員工編號</th>
		<th>桌號</th>
		<th>現場訂單時間</th>
		<th>現場訂單總價</th>
		<th>現場訂單付款狀態</th>
		<th>現場訂單狀態</th>
	</tr>
	<tr>
		<td>${loVO.liveOrderno}</td>
		<td>${loVO.empno}</td>
		<td>${loVO.tableno}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${loVO.liveOrderTime}"></fmt:formatDate></td>			
			<td><fmt:formatNumber type="number" value="${loVO.liveOrderTotal}"></fmt:formatNumber></td> 
		<td>${loVO.liveOrderPayment}</td>
		<td>${loVO.liveOrderStatus}</td>
	</tr>
</table>

</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>