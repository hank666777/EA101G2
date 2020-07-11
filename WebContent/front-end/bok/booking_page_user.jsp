<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%
 	MemVO memvo = (MemVO)session.getAttribute("memVO"); 
// System.out.println("BBB: " + memvo.getmName());
%>
<html>
<head>
	<meta charset="utf-8">
	<title>MISS M訂位主頁</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<link rel="stylesheet" href="">
<%@ include file="/front-end/front-end-head.jsp"%>
	<style type="text/css" media="screen">
		.bok-container{
			background-image:url(<%=request.getContextPath()%>/front-end/bok/images/back_img2.jpg);
			background-size: cover;
			background-position:bottom ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
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
  			font: bold 26px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
		}
		#list-ul li {
			padding: 10px;
			overflow: auto;
		}

		li:hover {
			background: #eee;
			cursor: pointer;
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
			font: 250 20px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
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
		#logo{
			width: 70px;
			height: 45px;
		}
		.nav-link{
			font: bold 18px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
		}
		#member{
			font: bold 16px/1.5 'Noto Sans TC', "Helvetica", "Arial","LiHei Pro","Microsoft JhengHei", Verdana, sans-serif;
			margin-top: 8px;
		}
	</style>

</head>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light ">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.jsp">
			<img id="logo" src="${pageContext.request.contextPath}/images/logo.png" title="首頁">
		</a>
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
<%-- 			<p>您好! <%=memvo.getmName()%></p> --%>
		</div>
	</nav>

	<div class="container-fluid bok-container">
		<div id="list">
			<ul id="list-ul">
				<li><a href="<%=request.getContextPath()%>/front-end/bok/newBookingStep1.jsp">
					<img src="<%=request.getContextPath()%>/front-end/bok/images/reservation.png" >
					<div>
					<h3>開始訂位</h3>
					<p>選擇日期、時段、人數以及您想要的座位。</p>
					</div>
					</a>
				</li>
				<li><a href="<%=request.getContextPath()%>/front-end/bok/listByMemNo.jsp">
					<img src="<%=request.getContextPath()%>/front-end/bok/images/search.png" >
					<div>
					<h3>查詢訂位紀錄</h3>
					<p>可以查詢您的訂位紀錄或取消您的訂位。</p>
					</div>
					</a>
				</li>
				<li><a href="<%=request.getContextPath()%>/front-end/index.jsp">
					<img src="<%=request.getContextPath()%>/front-end/bok/images/home.png" >
					<div>
					<h3>返回首頁</h3>
					<p>離開訂位頁面並且返回首頁。</p>
					</div>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<%@ include file="/front-end/front-end-footer-type2.jsp"%>
	<script type="text/javascript">
		$(document).ready(function (){
			 $("#list").fadeIn("slow");
		});
	</script>
	
</body>
</html>