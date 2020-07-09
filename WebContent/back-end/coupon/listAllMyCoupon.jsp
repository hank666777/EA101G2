<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mycoupon.model.*"%>
<%@ page import="java.util.*"%>

<%
    MyCpService MyCpservice = new MyCpService();
    List<MyCouponVO> list = MyCpservice.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="cps" scope="page" class="com.mycoupon.model.MyCpService" />

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<style type="text/css">
		
.row .link a{
width: 150px;
height: 40px;
background-color: rgb(40, 41, 35);	
color: white;
line-height: 40px;
text-align: center;
font-family:Microsoft jhengHei;
font-weight:400;
}
.row .link a:hover{
	background-color: #11a6c5;
	text-decoration: none;
}
img{
width:100px;
padding:0px;
}
.head{
font-size:60px;
font-family:Microsoft jhengHei;
text-align:center;}
td{
height:50px;
line-height:50px;
}
body{
font-family:Microsoft jhengHei;
background-color: rgb(255, 255, 255);
font-size: 12px;
}
.bg{
background-color: rgb(245, 245, 245);
font-family:Microsoft jhengHei !important;}
</style>
	<title></title>
</head>
<body>

	
			<table class="table table-hover">
			<thead class="bg">
				<tr>
					<th>優惠券編號</th>
					<th>優惠券使用狀態</th>
					<th>優惠券種類</th>
					<th>會員編號</th>
				</tr>
				</thead>
		<%@ include file="/page/page1.file" %> 
				<c:forEach var="mycoupon" items="${cps.all}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
				<tr>
					
					<td>${mycoupon.couponsno}</td>
					
					<td>${mycoupon.couponStatus}</td>
					
					<td>${mycoupon.couponno}</td>
					 
					<td>${mycoupon.memno}</td>
            					
				</tr>

			
			</c:forEach>
			</table>
		<%@ include file="/page/page2.file" %>

	
<div class="error">
			<c:if test="${not empty errorMsgs}">
			<br>
		
			<c:forEach var="message" items="${errorMsgs}">
			<p class="errors">${message}</p>
			</c:forEach>
		
			</c:if>
	</div>




</body>
</html>