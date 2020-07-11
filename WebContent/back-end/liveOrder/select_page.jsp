<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
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
		height: 900px;	
		margin:20px auto;
		box-shadow: 3px 3px 9px gray;
		padding-top:20px;
	}
	ul.wrap{
		display: table;
 		margin: 0px auto;
	}
  table#table-1 {
	width: 450px;
	background-color: orange;
	margin:auto;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
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
  	width:200px;
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
  	width:200px;
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

<div id="container">
<div id="selectproductonline" class="carding">

<table id="table-1">
   <tr><td><h1 id="productselecttitle">現場訂單管理</h1></td></tr>
</table>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul class="wrap">
  <li><a href="<%=request.getContextPath()%>/back-end/liveOrder/listAllLiveOrder.jsp"> 查詢</a> 全部現場訂單 <br><br></li>

<!--   <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" > --%>
<!--         <b>輸入現場訂單編號 (如20200401-L00001):</b> -->
<!--         <input type="text" name="liveOrderno" class="input"> -->
<!--         <label for="input"></label> -->
<!--         <div class="bottom-line"></div> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

<%--   <jsp:useBean id="loSvc" scope="page" class="com.liveOrder.model.LiveOrderService" /> --%>

<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" > --%>
<!--        <b>選擇桌位編號:</b> -->
<!--        <select size="1" name="liveOrderno"> -->
<%--          <c:forEach var="loVO" items="${loSvc.all}" >  --%>
<%--           <option value="${loVO.liveOrderno}">${loVO.tableno} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  


<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>

  <li>
  	<form method="post" action="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do">
  		<b><font color=blue>萬用複合查詢:</font></b> <br>
  		<b>輸入現場訂單編號:(如20200629-L00002)</b>
  		<div class="input-container">
  		<input type="text" name="liveOrderno" class="input"><br>		
  		<label for="input"></label>
  		<div class="bottom-line"></div>
  		</div>
  		
  		<b>輸入員工編號:(如E0000001)</b>
  		<div class="input-container">
  		<input type="text" name="empno" class="input"><br>
  		<label for="input"></label>
  		<div class="bottom-line"></div>
  		</div>

  		<b>輸入桌號:(如T0001)</b>
  		<div class="input-container">
        <input type="text" name="tableno" class="input"><br>
        <label for="input"></label>
        <div class="bottom-line"></div>
        </div>
        
        
        <b>現場訂單日期時間:(查詢從輸入之後的訂單)</b>
        <div class="input-container">
	    <input name="liveOrderTime" class="f_date1" type="text" class="input"><br>	
	    <label for="input"></label>          
	    <div class="bottom-line"></div>
	    </div>
	    
	    <b>消費總價:</b>
	    <div class="input-container">
        <input type="text" name="liveOrderTotal" value="" class="input"><br>
        <label for="input"></label>
        <div class="bottom-line"></div>
        </div>
        
        <b>現場訂單付款狀態:</b>
        <div class="input-container">
        <input type="text" name="liveOrderPayment" value="" class="input"><br>
        <label for="input"></label>
        <div class="bottom-line"></div>
        </div>
        
        <b>現場訂單狀態:</b> 
        <div class="input-container">       
        <input type="text" name="liveOrderStatus" value="" class="input"><br>
        <label for="input"></label>
        <div class="bottom-line"></div>
        </div>
        
        <input type="submit" value="送出">
  		<input type="hidden" name="action" value="listLiveOrder_ByCompositeQuery">
  	</form> 
  </li>
</ul>


<!-- <ul> -->
<%--   <li><a href="<%=request.getContextPath()%>/back-end/liveOrder/addLiveOrder.jsp">Add</a> a new LiveOrder</li> --%>
<!-- </ul> -->
</div>
</div>

<%@ include file="/back-end/back-end-footer.jsp"%>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
        $.datetimepicker.setLocale('zh');
        $('.f_date1').datetimepicker({
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