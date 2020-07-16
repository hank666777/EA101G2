<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activitypost.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<%
	ActivitypostVO actPVO = (ActivitypostVO) request.getAttribute("actPVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>活動貼文資料新增 - addactpost.jsp</title>

<style>
	.card{
		opacity:0.9;
		width:600px;
		margin:50px auto;
		padding:50px;
	}
	
	.buttonleft{
		text-align:left;
	}
	
	#delive{
		margin-top:20px;
		margin-left: 85%;
	}
	
	
</style>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row">
			<div class="card">
		
				<td>
					<h5>
						<a href="<%=request.getContextPath()%>/back-end/activity_post/activitypost_page.jsp">回首頁</a>
					</h5>
					<h3>資料新增</h3> <%-- 錯誤列表 --%> <c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/activity_post/activitypostServlet.do"
						name="form1" enctype="multipart/form-data">

						<table>
							<jsp:useBean id="actPSvc" scope="page"
								class="com.activitypost.model.ActivitypostService" />

							<tr>
								<td>活動編號:<font color=red><b>*</b></font></td>
								<td><select size="1" name="actno">
										<c:forEach var="actPSVO" items="${actPSvc.all}">
											<option value="${actPSVO.actno}"
												${(actPVO.actno==actPSVO.actno)? 'selected':'' }>${actPSVO.actno}
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>會員編號</td>
								<td><input type="TEXT" name="memno" size="45"
									value="<%=(actPVO == null) ? "M000000002" : actPVO.getMemno()%>" /></td>
							</tr>
							<tr>
								<td>活動推文日期</td>
								<td><input id="f_date1" type="text" name="actPostDate">
							</tr>
							<tr>
								<td>活動推文內容</td>
								<td><input type="TEXT" name="actPostCon" size="45"
									value="<%=(actPVO == null) ? " " : actPVO.getActPostCon()%>" /></td>
							</tr>

							<tr>
								<td>活動推文照片</td>
								<td><input type="file" id="fileElem" multiple
									accept="image/*"
									style="background-image:; width: 200px; height: 25px;"
									name="actPostPic" accept="image/jpeg, image/png"
									value="<%=(actPVO == null) ? "請上傳活動照片" : actPVO.getActPostPic()%>" /></td>
							</tr>

						</table>
						<br> <input type="hidden" name="action" value="add">
						<input  id="delive" type="submit" value="送出新增">
					</FORM>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp actPostDate = null;
	try {
		actPostDate = actPVO.getActPostDate();
	} catch (Exception e) {
		actPostDate = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/activity/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/activity_post/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/activity_post/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=actPostDate%>', // value:   new Date(),
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