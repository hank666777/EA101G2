<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//MemVO memvo = session.getAttribute("MemVO"); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>選擇日期和時段</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/app.js"></script>


	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	

	<style type="text/css" media="screen">
		.container-fluid{
			background-image:url(<%=request.getContextPath()%>/back-end/bok/backImages/background.jpg);
			background-size: cover;
			background-position:bottom ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
		}
		#date_and_period{
			position: relative;
			margin:7% auto;
			width: 400px;
			height: 350px;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			padding: 1px;
		}
		li:hover {
			background: #eee;
			cursor: pointer;
		}		
	
		#bkperiod{
			width: 227px;
			height:36px;
		}
		#date_and_period{
			font: 350 20px/1.5 "微軟正黑體", Times New Roman,sans-serif;
			display: none;
		}
		#date_block{
			width: 250px;
			position: relative;
			margin: 10% auto;
		}
		#period_block{
			width: 250px;
			position: relative;
			margin: 10% auto;
		}
		#next{
			position: relative;
			margin-top: 5% ;
			margin-right: 95px; 
			float: right;
		}
		#giveup{
			position: relative;
			margin: 5% 74px;
			margin-left: 74px; 
			float: left;
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
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/back-end/back-end-index.jsp"><img id="logo" src="<%=request.getContextPath()%>/back-end/bok/backImages/home.png" title="回主控台" style="height: 50px;height: 50px;"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

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
		<div id="emp">
			<p>員工名稱</p>
		</div>
	</nav>

	<div class="container-fluid">
		<div id="date_and_period">
			<FORM name="step1" METHOD="post" ACTION="<%=request.getContextPath()%>/BokServlet_admin">
				<div id="date_block">選擇日期: <br>
				<input autocomplete="off" type="text" id="datepicker" name="bkdate" required="required" readonly="readonly" ></div>
				
				<div id="period_block">
				選擇時段: <br>
				<select name="bkperiod" id="bkperiod">
					<option value="1200-1300">1200-1300</option>
					<option value="1300-1400">1300-1400</option>
					<option value="1400-1500">1400-1500</option>
					<option value="1500-1600">1500-1600</option>
					<option value="1600-1700">1600-1700</option>
					<option value="1700-1800">1700-1800</option>
					<option value="1800-1900">1800-1900</option>
					<option value="1900-2000">1900-2000</option>
				</select></div>
				
				<input type="hidden" name="action" value="getStatusByTime">
				<input type="button" id="next" value="下一步" onclick="checkdate()">	
			</FORM>
			<input type="button" id="giveup" value="放棄" onclick="location.href='<%=request.getContextPath()%>/back-end/bok/booking_page_admin.jsp'">
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function (){
			$("#date_and_period").fadeIn("slow");
		});
	</script>
</body>
</html>