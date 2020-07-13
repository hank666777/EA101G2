<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%@ page import="com.employee.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liveOrderMultiQuery</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_end_header.css"/>
	<link rel="stylesheet" type="text/css" href="jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/bok/js/app.js"></script>
	<style type="text/css">
		#booking-container{
			
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
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
		#date_block{
			position: relative;
			top: 20px;
			left: 60px;
		}
		#period_block{
			position: relative;
			top: 80px;
			left: 60px;
		}
		#table_block{
			position: relative;
			left: 60px;			
  			top: 5px;	
		}
		#send{
			position: relative;
			top: 100px;
			left: 160px;
		}
	</style>
</head>

<body>
	
	<%@ include file="/back-end/back-end-header.jsp" %>
	
	<div class="container-fluid" id="booking-container">
		<div id="Multi_Queries">
			<br>
	
	<h3 style="text-align: center;">現場劃位</h3>
	<form method="post" action="<%=request.getContextPath()%>/BokdtServlet" name="form1" id="form1">
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

		<div id="table_block">
			桌號: 
			<select name="tableno" id="tableno">
				<option value=""></option>
				<option value="T0001">T0001</option>
				<option value="T0002">T0002</option>
				<option value="T0003">T0003</option>
				<option value="T0004">T0004</option>
				<option value="T0005">T0005</option>
				<option value="T0006">T0006</option>
				<option value="T0007">T0007</option>
				<option value="T0008">T0008</option>
				<option value="T0009">T0009</option>
				<option value="T0010">T0010</option>
				<option value="T0011">T0011</option>
				<option value="T0012">T0012</option>
				<option value="T0013">T0013</option>
				<option value="T0014">T0014</option>
				<option value="T0015">T0015</option>
			</select>					
		</div>

		<input type="submit" id="send" value="送出查詢">
		<input type="hidden" name="action" value="listBokDetailByCompositeQuery">
	</form>
	</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
		<script type="text/javascript">
		$(document).ready(function (){
			$("#Multi_Queries").fadeIn("slow");
		});
	</script>
</body>
</html>