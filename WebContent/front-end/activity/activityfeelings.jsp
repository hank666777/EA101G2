<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.activity.model.*" %>
<%@ page import="com.activitypost.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	ActivitypostVO actPVO = (ActivitypostVO) request.getAttribute("actPVO");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>活動心得</title>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<div class="container">
		<div class="row text-center">
			<div class="col">

				<FORM METHOD="post"enctype="multipart/form-data"
							ACTION="<%=request.getContextPath()%>/front-end/activity/activityfeelings.do">


					<jsp:useBean id="actPSvc" scope="page" class="com.activitypost.model.ActivitypostService" />
					<jsp:useBean id="actPaSvc" scope="page" class="com.actparticipation.model.ParticipationService"/>
					<div>

						<h3>活動參與心得感想</h3>
						<div>會員編號：${memVO.memno}</div>
						<br>
						<div>
<!-- 							選擇報名活動：  -->
<!-- 							<select size="1" name="actno"> -->
<%-- 								<c:forEach var="actno" items="${actPaSvc.all}"> --%>
<%-- 									<c:forEach var="memno" items="${actPaSvc.all}"> --%>
<%-- 										<c:if test = "${memVO.memno} = memno"> --%>
<%-- 										<option value="${actno}">${actno} --%>
<%-- 										</c:if> --%>
<%-- 									</c:forEach> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
							
							選擇報名活動： 
							<select size="1" name="actno">
									<c:forEach var="participationVO" items="${actPaSvc.all}">
										<c:if test = "${memVO.memno==participationVO.memno}">
										<option value="${participationVO.actno}">${participationVO.actno}
										</c:if>
									</c:forEach>
							</select>
						</div>
						<br>
						<div>
							活動推文日期：<input type="text" id="f_date1" name="actPostDate" readonly>
						</div>
						<br>
						<div>
							活動心得內容<br>
							<textarea type="TEXT" name="actPostCon" rows="20" cols="60"
								style="margin: 0px; width: 775px; height: 217px;"></textarea>
						</div>
						<div>
							活動照片 <br>
							<input type="file" id="fileElem" multiple accept="image/*"
								style="background-image:; width: 200px; height: 25px;"
								name="actPostPic" accept="image/jpeg, image/png" />
						</div>

						<br> <input type="hidden" name="memno" value="${memVO.memno}">
						<input type="hidden" name="actno" value="${actno}"> <input
							type="hidden" name="action" value="add"> <input
							type="submit" value="確認送出" class="button">
					</div>
				</FORM>

			</div>
		</div>
	</div>
<%@ include file="/front-end/front-end-footer.jsp"%>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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