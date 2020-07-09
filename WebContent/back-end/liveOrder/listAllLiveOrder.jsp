<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.liveOrder.model.*"%>

<% 
	LiveOrderService loSvc = new LiveOrderService();
	List<LiveOrderVO> list = loSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
  body{
  	margin:0;
  	padding:0;
  }
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
  .picture{
  	width:100px;
  	height:100px;
  }
  #contain{
  width:1040px;
  height:800px;
  	margin-left:350px;
  }
</style>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<div id="container"  style="width:600px;margin:50px auto;border:2px solid black">

<table id="table-1">
	<tr><td>
		 <h3>所有現場訂單資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>現場訂單編號</th>
		<th>員工編號</th>
		<th>桌號</th>
		<th>現場訂單時間</th>
		<th>現場訂單總價</th>
		<th>現場訂單付款狀態</th>
		<th>現場訂單狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/back-end/liveShop/page1.file" %>
	<c:forEach var="loVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
			<td>${loVO.liveOrderno}</td>
			<td>${loVO.empno}</td>
			<td>${loVO.tableno}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${loVO.liveOrderTime}"></fmt:formatDate></td>
			<td><fmt:formatNumber type="number" value="${loVO.liveOrderTotal}"></fmt:formatNumber></td> 
			<td>${loVO.liveOrderPayment}</td>
			<td>${loVO.liveOrderStatus}</td>
			<td>	
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="liveOrderno"  value="${loVO.liveOrderno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="liveOrderno"  value="${loVO.liveOrderno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/liveShop/page2.file" %>

</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>