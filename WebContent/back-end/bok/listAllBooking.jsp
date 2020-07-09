<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ page import="com.mem.model.*"%>
<%
	BokService bokSvc = new BokService();
	List<BokVO> list = bokSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有訂單</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	

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
		h3{
  			font: bold 28px/1.5 "微軟正黑體", Verdana, sans-serif;
		}		
		.nav-link{
			font: bold 18px/1.5 "微軟正黑體";
		}
		#list_container{
			position: relative;
			margin:3% auto;
			width: 1000px;
			height: 800px;
			text-align: center;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			display: none;
			padding: 5px;
			overflow-y: auto;
		}
		table {
			position: relative;
			margin-top: 10px;
			border-collapse: collapse;
			width: 100%;
		}

		th, td {
			padding: 8px;
			text-align: left;
			border-bottom: 1px solid #ddd;
		}

		.data:hover {
			background-color:#CADCF9;
		}
		article > h5,
		article > p {
			font: 1rem '微軟正黑體', sans-serif;
			margin: .2rem;
			font-size: 1rem;
			line-height:15px;
			text-align:left;
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
		<div id="list_container">
			<table id="listAll">
				<tr>
					<th>訂位編號</th>
					<th>預約日期</th>
					<th>預約時段</th>
					<th>人數</th>
					<th>金額</th>
					<th>訂位狀態</th>
					<th>預約人</th>
					<th></th>
					<th></th>
				</tr>
				<%for(BokVO bok : list) {%>
				<tr class="data">
					<td><%=bok.getBkno()%></td>
					<td><%=bok.getBkDate()%></td>
					<td><%=bok.getBkPeriod()%></td>
					<td><%=bok.getNumOfPeople()%></td>
					<td><%=bok.getBkPrice()%></td>
					
					<%if(bok.getBkStatus()==0) {%>
						<td style="color:orange;">未報到</td>
					<%} %>
					<%if(bok.getBkStatus()==1) {%>
						<td style="color:green;">已報到</td>
					<%} %>
					<%if(bok.getBkStatus()==2) {%>
						<td style="color:red;">已取消</td>
					<%} %>
					
					<%
						MemService memSvc = new MemService();
						String memno = bok.getMemno();
					%>
					<%if("liveOrder".equals(memno)){ %>
					<td style="color:green;">現場劃位</td>
					<%}else{%>
					<td><%=memSvc.getOneMem(memno).getmName()%></td>
					<%}%>
					
					<% 
						BokdtService bokdtSvc = new BokdtService();
						List<BokdtVO> dtlist = bokdtSvc.getBokdtListByBkNo(bok.getBkno());
					%>
					
					<%if(bok.getBkStatus()!=2){ %>
					<td>
						<button type="button" class="btn btn-secondary" data-container="body" 
						data-toggle="popover" data-placement="bottom" title="已選桌號" data-html="true"
						data-content="<%for(BokdtVO bokdt : dtlist){%> <%=bokdt.getTableno()%> &nbsp;<%}%>">
						詳細資訊</button>
					</td>
					<%}else if (bok.getBkStatus()==2){%>
					<td>
						<button type="button" class="btn btn-secondary" data-container="body" 
						data-toggle="popover" data-placement="bottom" title="已選位置" data-html="true"
						data-content="" disabled>
						詳細資訊</button>
					</td>
					<%}%>
					
					<%
						long time = System.currentTimeMillis();	
						long bktime = bok.getBkDate().getTime();	
						long t = bktime - time ;
						t=t+86400000;
						System.out.println(t);
					%>
					
					<%if(t<=0 || bok.getBkStatus()==2 || bok.getBkStatus()==1) {%>
					<td>
						<form>
							<input type="button" value="check in" onclick="" disabled="disabled">
						</form>
					</td>
					<%}else{%>
					<td>
						<form action="<%=request.getContextPath()%>/BokServlet_admin">
							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							<input type="hidden" name="bkno" value="<%=bok.getBkno()%>">
							<input type="hidden" name="action" value="change">
							<input type="submit" value="check in">
						</form>
					</td>
					<%}%>
				</tr>
				<%}%>
			</table>
			<article>
				<h5 style="color:red;">注意事項:</h5>
				<p>已超過預約日期就無法進行報到。</p>
			</article>
		</div>
	</div>
	
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