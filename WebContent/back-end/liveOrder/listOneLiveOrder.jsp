<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
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
	position: relative;
	background-color: orange;
    border: 2px solid black;
    text-align: center;
    border-collapse: collapse;
    margin:auto;
    width:500px;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
   #listAll {
  			position: relative;
			margin-top: 10px;
			border-collapse: collapse;
			width: 100%;
  }
  #listAll, th, td {
    border: 1px solid #CCCCFF;
    padding: 8px;
    text-align: center;
    border-bottom: 1px solid #ddd;
  }
  #table-1 h4 {
    color: blue;
    display: inline;
  }
  #booking-container{

			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
  }
  .data:hover {
			background-color:#CADCF9;
  }
	#list_container{ 
 			position: relative;  
 			margin:3% auto; 
 			width: 1000px;
			height: 300px; 
 			text-align: center; 
			background-color: #F8F8F8; 
 			border-radius:5px; 
 			box-shadow:3px 3px 9px black; 
 			padding: 5px; 
 			overflow-y: auto; 
 			display: none;
 		} 

</style>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>

<table id="table-1">
	<tr><td>
		 <h3>現場訂單資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<div class="container-fluid" id="booking-container">
	<div id="list_container">
<table id="listAll">
	
	<h5 style="font-size:40px;margin-bottom:40px;">資料修改如下</h5>
	
	<tr>
		<th>現場訂單編號</th>
		<th>員工編號</th>
		<th>桌號</th>
		<th>現場訂單時間</th>
		<th>現場訂單總價</th>
		<th>現場訂單付款狀態</th>
		<th>現場訂單狀態</th>
	</tr>
	<tr class="data">
		<td>${loVO.liveOrderno}</td>
		<td>${loVO.empno}</td>
		<td>${loVO.tableno}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${loVO.liveOrderTime}"></fmt:formatDate></td>			
			<td><fmt:formatNumber type="number" value="${loVO.liveOrderTotal}"></fmt:formatNumber></td> 
			
		<td>
			<c:if test="${loVO.liveOrderPayment == 0}">
				<c:out value="未付款"></c:out>
			</c:if>
			<c:if test="${loVO.liveOrderPayment == 1}">
				<c:out value="已付款"></c:out>
			</c:if>
		</td>
		
		<td>
			<c:if test="${loVO.liveOrderStatus == 0}">
				<c:out value="未完成"></c:out>
			</c:if>
			<c:if test="${loVO.liveOrderStatus == 1}">
				<c:out value="已完成"></c:out>
			</c:if>
		</td>
	</tr>
</table>

</div>
</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
<script src="${pageContext.request.contextPath}/back-end/bok/jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
			 $("#list_container").fadeIn("slow");
		});

		function detail(){
			alert();
		}
		$(function () {
			$('[data-toggle="popover"]').popover()
		})
	</script>
</body>
</html>