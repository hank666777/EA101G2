<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>booking_page_admin</title>

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
		.container-fluid{
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
		#list{
			position: relative;
			margin:7% auto;
			width: 400px;
			height: 500px;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			display: none;
		}
		h3{
  			font: bold 26px/1.5 "微軟正黑體", Verdana, sans-serif;
		}
		#list-ul li {
			padding: 10px;
			overflow: auto;
		}
		#list-ul li img {
			float: left;
			margin: 0 15px 0 0;
		}
		#list-ul li div{
			float: left;
			width: 300px;
		}
		#list-ul li div p {
			font: 250 20px/1.5 "微軟正黑體", Times New Roman,sans-serif;
		}	
		#list-ul li {
			list-style-type: none;
			width: 400px;
		}
		#list-ul{
			position: relative;
			padding:0;
			top:50px ;
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
		<div id="list">
			<ul id="list-ul">
				<li><a href="<%=request.getContextPath()%>/back-end/bok/newBookingStep1.jsp">
					<img src="<%=request.getContextPath()%>/back-end/bok/backImages/reservation.png" >
					<div>
						<h3>安排桌位</h3>
						<p>選擇日期、時段、人數以及安排給客人的桌位。</p>
					</div>
					</a>
				</li>
				<li><a href="<%=request.getContextPath()%>/back-end/bok/multi_query.jsp">
					<img src="<%=request.getContextPath()%>/back-end/bok/backImages/check-file.png" >
					<div>
						<h3>查詢&報到</h3>
						<p>綜合查詢以及用於線上預約的報到確認。</p>
					</div>
					</a>
				</li>
				<li><a href="<%=request.getContextPath()%>/back-end/back-end-index.jsp">
					<img src="<%=request.getContextPath()%>/back-end/bok/backImages/control-panel.png" >
					<div>
						<h3>返回主控台</h3>
						<p>離開桌位管理頁面並且返回主控台。</p>
					</div>
					</a>
				</li>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function (){
			 $("#list").fadeIn("slow");
		});
	</script>
</body>
</html>