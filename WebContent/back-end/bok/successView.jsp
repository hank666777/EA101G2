<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>成功</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Refresh" content="3,url=<%=request.getContextPath()%>/back-end/bok/booking_page_admin.jsp">

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<link rel="stylesheet" href="">

	<style type="text/css" media="screen">
		#booking-container{
			background-image:url(<%=request.getContextPath()%>/back-end/bok/backImages/background.jpg);
			background-size: cover;
			background-position:bottom ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
		}
		
		#successView{
			position: relative;
			margin:7% auto;
			width: 300px;
			height: 300px;
			text-align: center;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			display: none;
		}
		h3{
  			font: bold 26px/1.5 "微軟正黑體", Verdana, sans-serif;
		}
		

		li:hover {
			background: #eee;
			cursor: pointer;
		}
		
		
		.nav-link{
			font: bold 18px/1.5 "微軟正黑體";
		}
		#emp{
			position: relative;
			margin-right: 8px; 
			font: bold 16px/1.5 "微軟正黑體";
			margin-top: 8px;
		}
	</style>

</head>
<body>
	<%@ include file="/back-end/back-end-header.jsp" %>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="<%=request.getContextPath()%>/back-end/bok/booking_page_admin.jsp">桌位管理主頁</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/back-end/bok/newBookingStep1.jsp">安排桌位</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/back-end/bok/multi_query.jsp">查詢&報到</a>
				</li>
			</ul>
		</div>
		
	</nav>

	<div class="container-fluid" id="booking-container">
		<div id="successView">
			<br><br><br>
			<img src="<%=request.getContextPath()%>/back-end/bok/images/ok.png"><br>
			<h3>操作成功! 三秒後跳轉...</h3>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
			 $("#successView").fadeIn("slow");
		});
	</script>
	
</body>
</html>