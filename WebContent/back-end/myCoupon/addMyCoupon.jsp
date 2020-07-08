<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="java.util.*"%>


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
}

body{
font-family:Microsoft jhengHei;
background-color: rgb(255, 255, 255);
font-size: 12px;
}
</style>
	<title></title>
</head>
<body>
		
			<form action="<%=request.getContextPath()%>/mycoupon/mycoupon.do" method="post">		
			<select name="memno">
			<option value="M000000001">
			M000000001
			</option >
			<option value="M000000002">
			M000000002
			</option>		
			</select>	
			<select name="couponName">
			<c:forEach var="coupon" items="${cps.all}" >
  			<option value="${coupon.couponno}"> ${coupon.couponName} </option>
  			</c:forEach>
			</select>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="發送優惠券">
			</form>
			

	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




</body>
</html>