<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%
	LiveOrderVO loVO = (LiveOrderVO)request.getAttribute("loVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>現場訂單資料修改</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
	#productselecttitle{
		margin:25px auto;
		text-align:center;
	}
	.carding{
		opacity:0.9;
		width:600px;
		height: 800px;	
		margin:20px auto;
		box-shadow: 3px 3px 9px gray;
		padding-top:20px;
	}
	ul.wrap{
		display: table;
 		margin: 0px auto;
 		list-style:none;
	}
  table#table-1 {
	position: relative;
	background-color: orange;
    border: 2px solid black;
    text-align: center;
    border-collapse: collapse;
    margin:auto;
    width:500px;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  #container{
  	width:800px;
  	margin:auto;
  }
  .input-container{
  	width:500px;
  	margin:0 auto;
  	position:relative;
  	display:-webkit-box;
  	display:-ms-flexbox;
  	display:flex;
  	-webkit-box-orient:vretical;
  	-ms-flex-flow:column-reverse;
  	flex-flow:column-reverse;
  	-webkit-box-align:start;
  	align-items:flex-start;
  }
  .input-container input{
  	-webkit-box-ordinal-group:11;
  	order:10;
  	-ms-flex-order:10;
  	outline:none;
  	border:none;
  	font-size:20px;
  	border-bottom:1px solid #d5d5d5;
  	text-indent:10px;
  }
  .input-container input::-moz-placeholder{
  	opacity:0;
  }
  .input-container input::-webkit-input-placeholder{
  	opacity:0;
  }
  .input-container input::-ms-input-placeholder{
  	opacity:0;
  }
  .input-container input, .input-container label{
  	transition:all 0.3s; 
  }
  .input-container label{
  	-webkit-box-ordinal-group:101;
  	-ms-flex-order:100;
  	order:100;
  	color:#3f4f5b;
  	-webkit-transform-origin:left bottom;
  	transform-origin:left bottom;
  	-webkit-transform:translate(10px,40px);
  	transform:translate(0px,40px);
  }
  .input-container .bottom-line{
  	order:2;
  	width:0;
  	height:2px;
  	background:#658db5;
  	transition:all 0.3s;
  }
  .input-container input:focus{
  	border-bottom-color:#fff;
  }
  .input-container input:focus ~ div,
  .input-container input:not(:placeholder-shown) ~ div {
  	width:250px;
  }
  .input-container input:focus + label,
  .input-container input:not(:placeholder-shown) + label{
  	color:#52616c;
  	-webkit-transform:translate(10px) scale(0.9);
  	transform:translate(10px) scale(0.9);
  }
</style>

</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>


<table id="table-1">
	<tr><td>
		 <h3>現場訂單資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回現場訂單首頁</a></h4>
	</td></tr>
</table>

<div id="container">
<div id="selectproductonline" class="carding">

<h3 style="text-align:center;">資料修改中:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
	
	<ul class="wrap">
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" name="form1">


		<b><font color=blue>現場訂單編號:</font></b>
		<div class="input-container">
		<input type="text" name="liveOrderno" class="input" value="<%=loVO.getLiveOrderno()%>" readonly="readonly"><br>
		<label for="input"></label>
  		<div class="bottom-line"></div>
  		</div>
		
		<b>員工編號:</b>
		<select size="1" name="empno">
			<c:forEach var="empVO" items="${empSvc.all}">
				<option value="${empVO.empno}" ${(loVO.empno==empVO.empno)? 'selected':'' } >${empVO.eName}
			</c:forEach>
		</select>
		<br>
		<br>
		
		
		<b>桌位編號:</b>
		<div class="input-container">
        <input type="text" name="tableno" class="input" value="<%=loVO.getTableno()%>"><br>
		<label for="input"></label>
        <div class="bottom-line"></div>
        </div>

		<b>現場訂單時間:</b>
		<div class="input-container">
        <input type="text" name="liveOrderTime" class="input" id="f_date1" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="<%=loVO.getLiveOrderTime()%>"></fmt:formatDate>"><br>
		<label for="input"></label>
        <div class="bottom-line"></div>
        </div>
		
		<b>現場訂單總價:</b>
		<div class="input-container">
        <input type="text" name="liveOrderTotal" class="input" value="<fmt:formatNumber type="number" value="<%=loVO.getLiveOrderTotal()%>"></fmt:formatNumber>"><br>
		<label for="input"></label>
        <div class="bottom-line"></div>
        </div>

		<b>現場訂單付款狀態:(輸入查詢0=未付款,1=已付款)</b>
		<div class="input-container">
        <input type="text" name="liveOrderPayment" class="input" value="<%=loVO.getLiveOrderPayment()%>"><br>
        <label for="input"></label>
        <div class="bottom-line"></div>
        </div>
        
        <b>現場訂單狀態:(輸入查詢0=未完成,1=已完成)</b>
        <div class="input-container">
        <input type="text" name="liveOrderStatus" class="input" value="<%=loVO.getLiveOrderStatus()%>"><br>
        <label for="input"></label>
        <div class="bottom-line"></div>
        </div>

		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="liveOrderno" value="<%=loVO.getLiveOrderno()%>">
		<input type="submit" value="送出修改" class="sendUpdate"></FORM>
		</li>
	</ul>	
</div>
</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',           //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 60,               //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d',
		   value: '',              // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>



</html>