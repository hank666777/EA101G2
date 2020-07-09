<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
MemVO memvo = (MemVO) session.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>退款資訊</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<link rel="stylesheet" href="">

	<style type="text/css" media="screen">
		.container-fluid{
			background-image:url(<%=request.getContextPath()%>/front-end/bok/images/back_img2.jpg);
			background-size: cover;
			background-position:bottom ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
		}
		#Refund_info{
			position: relative;
			margin:7% auto;
			width: 300px;
			height: 350px;
			text-align: center;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			display: none;
		}
		form{
  			font: bold 22px/1.5 "微軟正黑體", Verdana, sans-serif;
		}
		

		li:hover {
			background: #eee;
			cursor: pointer;
		}
		
		#logo{
			width: 70px;
			height: 45px;
		}

		#leave{
			position: relative;
			float: left;
			margin-top: 15px;
			margin-left: 15px;
		}
		#send{
			position: relative;
			float: right;
			margin-top: 15px;
			margin-right: 15px;
		}
		#info_title{
			font: bold 14px/1.5 "微軟正黑體";
			color:red;
		}
		.nav-link{
			font: bold 18px/1.5 "微軟正黑體";
		}
		#member{
			font: bold 16px/1.5 "微軟正黑體";
			margin-top: 8px;
		}
	</style>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.jsp"><img id="logo" src="<%=request.getContextPath()%>/front-end/bok/images/logo.png" title="首頁"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/bok/booking_page_user.jsp">訂位首頁<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/bok/newBookingStep1.jsp">開始訂位</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/bok/listByMemNo.jsp">紀錄查詢</a>
				</li>
			</ul>
		</div>
		<div id="member">
			<p>您好! <%=memvo.getmName()%></p>
		</div>
	</nav>

	<div class="container-fluid">
		<div id="Refund_info">
			<p id="info_title">確認您的退款資訊</p>
			<form id="cancelreq" METHOD="post" ACTION="<%=request.getContextPath()%>/BokServlet">
				銀行代號 : <input type="text" id="bank_id" value="787" readonly="readonly"><br>
				退款帳號 : <input type="text" id="bank_account" value="01210550322574" readonly="readonly"><br>
				退款金額 : <input type="text" id="refund_price" value="<%=request.getParameter("bkprice")%>" readonly="readonly"><br>
				<input type="hidden" name="bkno" value="<%=request.getParameter("bkno")%>">
				<input type="hidden" name="action" value="cancelBooking">
				<input type="button" id="leave" value="離開" onclick="location.href='<%=request.getContextPath()%>/front-end/bok/listByMemNo.jsp'">
				<input type="button" id="send" value="送出" onclick="confirm1()">
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function (){
			 $("#Refund_info").fadeIn("slow");
		});
		
		function confirm1(){	
			if(confirm("確定要取消訂位嗎?")){
				cancelreq.submit();
			}
		}
	</script>
	
</body>
</html>