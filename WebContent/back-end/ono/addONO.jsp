<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ono.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%
	ONOVO onVO = (ONOVO)request.getAttribute("onVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>線上訂單新增</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
   .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<div id="container" style="margin:auto; margin-top:30px;">
<table id="table-1">
	<tr><td>
		 <h3>線上訂單資料新增</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/ono/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ONOServlet/ONOServlet.do" name="form1">

<table>
		
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />	
	<tr>
		<td>會員姓名:<font color=red><b>*</b></font></td>
		<td><select size="1" name="memno">
			<c:forEach var="memVO" items="${memSvc.all}">
				<option value="${memVO.memno}" ${(onVO.memno==memVO.memno)? 'selected':'' } >${memVO.mName}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>優惠卷編號:</td>
		<td><input type="TEXT" name="couponSno" size="45"
			 value="<%= (onVO==null)? "null" : onVO.getcouponSno()%>" /></td>
	</tr>
	<tr>
		<td>線上訂單時間:</td>
		<td><input type="text" name="onoTime" id="f_date1"></td>
	</tr>
	<tr>
		<td>線上訂單總價:</td>
		<td><input type="TEXT" name="onoTotal" size="45"
			 value="<%= (onVO==null)? "???" : onVO.getonoTotal()%>" /></td>
	</tr>
	<tr>
		<td>線上訂單狀態:</td>
		<td><input type="TEXT" name="onoStatus" size="45"
			 value="<%= (onVO==null)? "0" : onVO.getonoStatus()%>" /></td>
	</tr>
	<tr>
		<td>線上訂單付款狀態:</td>
		<td><input type="TEXT" name="onoPay" size="45"
			 value="<%= (onVO==null)? "0" : onVO.getonoPay()%>" /></td>
	</tr>
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>

<% 
  java.sql.Timestamp onoTime = null;
  try {
	  onoTime = onVO.getonoTime();
   } catch (Exception e) {
	   onoTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/ono/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
	
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
   	theme: 'dark',              //theme: 'dark',
  	timepicker:false,       //timepicker:true,
   	step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   	format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   	value: '<%=onoTime%>', // value:   new Date(),
   	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   	//startDate:	            '2017/07/10',  // 起始日
   	//minDate:               '-1970-01-01', // 去除今日(不含)之前
   	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});


</script>


</html>