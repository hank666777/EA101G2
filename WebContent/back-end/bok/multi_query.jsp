<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>複合查詢</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	


	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<style type="text/css">
		#booking-container{
			background-image:url(<%=request.getContextPath()%>/back-end/bok/backImages/background.jpg);
			background-size: cover;
			background-position:top ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
		}
		.nav-item{
			position: relative;
			font: bold 24px/1.5 "微軟正黑體";
		}
		li:hover{
			background: #eee;
			cursor: pointer;
		}		
		h3{
  			font: bold 28px/1.5 "微軟正黑體", Verdana, sans-serif;
		}		
		.nav-link{
			font: bold 18px/1.5 "微軟正黑體";
		}
		#Multi_Queries{
			position: relative;
			margin:7% auto;
			width: 400px;
			height: 500px;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			display: none;
			font: bold 18px/1.5 "微軟正黑體";
			padding:1px;
		}
		#form1{
			position: relative;
		}
		#bkperiod{
			height: 33px;
			width: 205px;
		}
		#memno{
			height: 33px;
			width: 205px;
		}
		#date_block{
			position: relative;
			top: 40px;
			left: 60px;
		}
		#period_block{
			position: relative;
			top: 80px;
			left: 60px;
		}
		#memno_block{
			position: relative;
			top: 120px;
			left: 60px;
		}
		#send{
			position: relative;
			top: 160px;
			left: 160px;
		}
		#list_all{
			position: relative;
			top: 120px;
			left: 50px;
		}
		#emp{
			position: relative;
			margin-right: 8px; 
			font: bold 16px/1.5 "微軟正黑體";
			margin-top: 8px;
		}
		#bkstatus{
			position: relative;
			top: 150px;
			left: 60px;
		}
		label:hover{
			cursor: pointer;
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
		<div id="Multi_Queries">
			<br>
			<h3 style="text-align: center;">複合查詢</h3>
			<form method="post" action="<%=request.getContextPath()%>/BokServlet_admin" name="form1" id="form1">
				<div id="date_block">
					日期: 
					<input autocomplete="off" type="text" id="datepicker" name="bkdate" required="required" readonly="readonly" >
				</div>

				<div id="period_block">
					時段: 
					<select name="bkperiod" id="bkperiod">
						<option value=""></option>
						<option value="1200-1300">1200-1300</option>
						<option value="1300-1400">1300-1400</option>
						<option value="1400-1500">1400-1500</option>
						<option value="1500-1600">1500-1600</option>
						<option value="1600-1700">1600-1700</option>
						<option value="1700-1800">1700-1800</option>
						<option value="1800-1900">1800-1900</option>
						<option value="1900-2000">1900-2000</option>
					</select>
				</div>
				
				<div id="memno_block">
					<%
						MemService memSvc = new MemService();
						List<MemVO> memlist = memSvc.getAll();
					%>
					會員: 
					<select name="memno" id="memno">
						<option value=""></option>
						<%for(MemVO memvo : memlist) {%>
						<option value="<%=memvo.getMemno()%>"><%=memvo.getMemno()%>(<%=memvo.getmName()%>)</option>
						<%}%>
					</select>					
				</div>
				
				<fieldset id="bkstatus">
					<input type="checkbox" name="bkstatus" value="0" id="checkbox-1">
					<label for="checkbox-1">未報到</label>
					&emsp;
					<input type="checkbox" name="bkstatus" value="1" id="checkbox-2">
					<label for="checkbox-2">已報到</label>
					&emsp;
					<input type="checkbox" name="bkstatus" value="2" id="checkbox-3">
					<label for="checkbox-3">已取消</label>
				</fieldset>
				
				<input type="submit" id="send" value="送出查詢">
       			<input type="hidden" name="action" value="listByCompositeQuery">
			</form>
			<br><br><br>
			<a id="list_all" href="<%=request.getContextPath()%>/back-end/bok/listAllBooking.jsp"><h3><img src="<%=request.getContextPath()%>/back-end/bok/backImages/list.png" style="height:40px;width:40px;">列出所有訂單...</h3></a>
		</div>
	</div>
	
	<%@ include file="/back-end/back-end-footer.jsp"%>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
			$("#Multi_Queries").fadeIn("slow");
		});

		$( function() {
			$("#datepicker").datepicker({ 
				minDate: 0, 
				maxDate: "+1M +10D",
				beforeShowDay: noMondays,
				dateFormat : "yy-mm-dd" 
			});	
		});

		function noMondays(a) {
			a=a.getDay();
			return[(a>1&&a<7)||a==0,""];
		}

	</script>
</body>
</html>