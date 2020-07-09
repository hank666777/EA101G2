<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.liveOrder.model.*"%>

<%
	LiveOrderVO loVO = (LiveOrderVO)request.getAttribute("loVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>現場訂單新增</title>
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
<%@ include file="/back-end/back-end-head.jsp" %>
</head>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<div id="container"  style="width:600px;margin:50px auto;border:2px solid black">

<table id="table-1">
	<tr><td>
		 <h3>現場訂單資料新增</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" name="form1">

<table>
		
	<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />	
	<tr>
		<td>員工姓名:<font color=red><b>*</b></font></td>
		<td><select size="1" name="empno">
			<c:forEach var="empVO" items="${empSvc.all}">
				<option value="${empVO.empno}" ${(loVO.empno==empVO.empno)? 'selected':'' } >${empVO.eName}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>桌位編號:</td>
		<td><input type="TEXT" name="tableno" size="45"
			 value="<%= (loVO==null)? "T0001" : loVO.getTableno()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單時間:</td>
		<td><input type="text" name="liveOrderTime" id="f_date1"></td>
	</tr>
	<tr>
		<td>現場訂單總價:</td>
		<td><input type="TEXT" name="liveOrderTotal" size="45"
			 value="<%= (loVO==null)? "???" : loVO.getLiveOrderTotal()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單付款狀態:</td>
		<td><input type="TEXT" name="liveOrderPayment" size="45"
			 value="<%= (loVO==null)? "0" : loVO.getLiveOrderPayment()%>" /></td>
	</tr>
	<tr>
		<td>現場訂單狀態:</td>
		<td><input type="TEXT" name="liveOrderStatus" size="45"
			 value="<%= (loVO==null)? "0" : loVO.getLiveOrderStatus()%>" /></td>
	</tr>
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
</body>


<% 
  java.sql.Timestamp liveOrderTime = null;
  try {
	  liveOrderTime = loVO.getLiveOrderTime();
   } catch (Exception e) {
	   liveOrderTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/liveOrder/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
	
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
   	theme: 'dark',              //theme: 'dark',
  	timepicker:false,       //timepicker:true,
   	step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   	format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   	value: '<%=liveOrderTime%>', // value:   new Date(),
   	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   	//startDate:	            '2017/07/10',  // 起始日
   	//minDate:               '-1970-01-01', // 去除今日(不含)之前
   	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});


</script>


</html>