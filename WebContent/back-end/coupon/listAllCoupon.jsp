<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="java.util.*"%>

<%
    CpService Cpservice = new CpService();
    List<CouponVO> list = Cpservice.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="cps" scope="page" class="com.coupon.model.CpService" />

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
font-family:Microsoft jhengHei;}
.btn{
font-family:Microsoft jhengHei;}
</style>
	<title></title>
</head>
<body>

	
			<table class="table">
			<thead class="bg">
				<tr>
					<th>優惠券編號</th>
					<th>優惠券名稱</th>
					<th>優惠券折扣</th>
					<th>優惠券圖片</th>
					<th></th>
				</tr>
				</thead>
		<%@ include file="/page/page1.file" %>
				<c:forEach var="coupon" items="${cps.all}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
				<tr>
					
					<td>${coupon.couponno}</td>
								
					<td>${coupon.couponName}</td>
					
					<td>${coupon.couponDiscount}</td>
					 
					<td>
					<img src="<%=request.getContextPath()%>/mycoupon/imgs?couponno=${coupon.couponno}" />
					</td>
            		
  					<td>
			  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/coupon.do" style="margin-bottom: 0px;">
			    	 <input class="btn-primary btn-lg" type="submit" value="修改">
			     	 <input type="hidden" name="coupon" value="${coupon.couponno}">
			    	 <input type="hidden" name="action"	value="getOneForUpdate"></FORM>
					</td>
					
				</tr>

			
			</c:forEach>
			</table>
		<%@ include file="/page/page2.file" %>
		
	
	
	<div class="error">
			<c:if test="${not empty errorMsgs}">
			<br>
		
			<c:forEach var="message" items="${errorMsgs}">
			<p class="errors">${message.value}</p>
			</c:forEach>
		
			</c:if>
	</div>





</body>
</html>