<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listLiveOrder_ByCompositeQuery" scope="request" type="java.util.List<LiveOrderVO>" />
<jsp:useBean id="deptSvc" scope="page" class="com.liveOrder.model.LiveOrderService" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>複合查詢</title>
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
	width: 800px;
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
<%@ include file="/back-end/back-end-head.jsp" %>
</head>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<div id="container"  style="width:600px;margin:50px auto;border:2px solid black">

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4>

<table id="table-1">
	<tr><td>
		 <h3>所有現場訂單資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>現場訂單編號</th>
		<th>員工編號</th>
		<th>桌號</th>
		<th>現場訂單日期時間</th>
		<th>消費總價</th>
		<th>現場訂單付款狀態</th>
		<th>現場訂單狀態</th>
	</tr>
	<c:forEach var="loVO" items="${listLiveOrder_ByCompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${loVO.liveOrderno}</td>
			<td>${loVO.empno}</td>
			<td>${loVO.tableno}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${loVO.liveOrderTime}"></fmt:formatDate></td>			
			<td><fmt:formatNumber type="number" value="${loVO.liveOrderTotal}"></fmt:formatNumber></td>
			<td>${loVO.liveOrderPayment}</td>
			<td>${loVO.liveOrderStatus}</td>
		</tr>
	</c:forEach>
</table>

</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>