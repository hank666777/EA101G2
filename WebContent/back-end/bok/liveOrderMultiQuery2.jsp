<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="com.tabless.model.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%@ page import="com.employee.model.*" %>
<%
	List<BokdtVO> list = (List<BokdtVO>)request.getAttribute("BokDetailList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liveOrderMultiQuery2</title>
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_end_header.css"/>
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/app.js"></script>
	<!-- font-awesome CSS 5.13-->
	<link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.13.1/css/all.css">
	<style type="text/css">

  #table-1{
 	position: relative;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    border-collapse: collapse;
    margin:auto;
    width:500px;
  }
  #table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }

  #listAll {
  			position: relative;
			margin-top: 10px;
			border-collapse: collapse;
			width: 100%;
  }
  #listAll, th, td {
    border: 1px solid #CCCCFF;
    padding: 8px;
    text-align: center;
    border-bottom: 1px solid #ddd;
  }


  #booking-container{

			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
  }
  .data:hover {
			background-color:#CADCF9;
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
 			padding: 5px; 
 			overflow-y: auto; 
 			display: none;
 		} 
</style>
</head>

<body style="background-size:cover;" background="${pageContext.request.contextPath}/images/back-end/productImg/backProductBackground.jpg">

<%@ include file="/back-end/back-end-header.jsp" %>

	<table id="table-1">
		<tr><td>
			<h3>查詢結果</h3>
		</td></tr>
	</table>
	
	
	<div class="container-fluid" id="booking-container">
	<div id="list_container">
	<table id="listAll">
		<tr>
			<th>日期</th>
			<th>時段</th>		
			<th>桌號</th>
			<th></th>
		</tr>	
		<% for (BokdtVO bokdtVO : list) {%>
		<tr class="data">
			<td><%=bokdtVO.getBkDate()%></td>
			<td><%=bokdtVO.getBkPeriod()%></td>
			<td><%=bokdtVO.getTableno()%></td>
			<td>
				
					<input type="hidden" name="bkdate" value="<%=bokdtVO.getBkDate()%>">
					<input type="hidden" name="bkperiod" value="<%=bokdtVO.getBkPeriod()%>">
				<form action="<%=request.getContextPath()%>/product/TablenoServlet.do">
					<input type="hidden" name="tableno" value="<%=bokdtVO.getTableno()%>">
					<input type="hidden" name="action" value="confirm">
					<input type="submit" name="Submit" value="點餐">
				</form>
			</td>
		</tr>
		<%}%>
	</table>
	</div>

</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
	<script src="${pageContext.request.contextPath}/back-end/bok/jquery-ui-1.12.1/jquery-ui.js"></script>
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