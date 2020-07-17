<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.activity.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.actparticipation.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%	
	String actno = (String) request.getAttribute("actno");
	ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	ParticipationVO PV = new ParticipationVO();
	
	Calendar rightNow = Calendar.getInstance();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String sysdate = format.format(rightNow.getTime());
%>


<html>
<head>
<link rel="stylesheet" type="text/css" href="">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>活動報名</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700,900" >
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<style type="text/css">

</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<c:forEach var="message" items="${errorMsgs}">
				<font style="color:red">${message}</font>
			</c:forEach>
		</c:if>
		<Form action="<%=request.getContextPath()%>/front-end/activity/activityshop.do "method="post">
			<div class="container">
				<div class="row justify-content-center">
					<div>
						<table style="border:8px #FFD382 groove;" cellpadding="10" border='2'; >
							<thead>
							    <tr>
											<th></th>
							      <th scope="col"><p class="h2">報名列表</p></th>
							    </tr>
							</thead>
							
							<tbody>
							   <tr>
							     <th style="width:150px;"><p class="h3">會員編號</p></th>
							     <td><p class="h3">${memVO.memno}</p></td>
							   </tr>
							   <tr>
							     <th><p class="h3">活動編號</p></th>
							     <td><p class="h3">${actno}</p></td>
							   </tr>
							   <tr>
							     <th><p class="h3">報名時間</p></th>
							     <td>
							     	<input id="f_date1" type="hidden" name="actPatDate"/><%=sysdate %>
							     </td>
							   </tr>
							   <tr>
							     <th><p class="h3">報名人數</p></th>
							     <td>
							     	<select name="actParEnr" id="actParEnr">
										<option>請選擇人數</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>
							     </td>
							   </tr>
							   <tr>
							     <th><p class="h3">報名費用</p></th>
							     <td>
							     	<p class="h3"><span id="actTalFee">${param.actFee}</span></p>
							     </td>
							   </tr>
						</tbody>
<!-- 						</table> -->
					</div>
					
					<div>
<!-- 						<table class="table table-sm text-nowarp text-center"> -->
							<thead>
							    <tr>
							    		<th></th>
							      <th scope="col"><p class="h2">活動說明</p></th>
							    </tr>
							</thead>
							
							<tbody>
							   <tr>
							     <th scope="row"><p class="h3">活動地點</p></th>
							     <td>資策會烘培館(工程二館旁)</td>
							   </tr>
							   <tr>
							     <th scope="row"><p class="h3">活動對象</p></th>
							     <td>無限制，最多四人合作一份蛋糕</td>
							   </tr>
							   <tr>
							     <th scope="row"><p class="h3">交通方式</p></th>
							     	<td>搭公車、自行開車前往、騎車(附近都有停車場)</td>
							   </tr>
							   <tr>
							   	<th scope="row"><p class="h3">費用包含</p></th>
							   	<td>食材/場地設備使用/成品包裝/飲料暢飲/專人教學</td>
							   </tr>
							   <tr>
							   	<th scope="row"><p class="h3">活動時間</p></th>
							   	<td>2~4小時，視製作蛋糕而定</td>
							   </tr>
							   <tr>
							   	<th scope="row"><p class="h3">注意事項</p></th>
							   	<td>
							   	一、 活動最多四人合做一份，多餘的人數需加收費用，現場將核對人數，若是到場人數與報名人數不同，多人則須補差價，少人亦不退費用。
							   	<br>
							   	二、活動成行，最低需求人數為二十人，成團最多人數為六十人，未達成成團標準將會退費並取消活動。
							   	<br>
							   	本店採用優質天然食材，以及傭有專業技術的烘培道具、設備、精美包裝，有詳細專人講解製作步驟，並有寬敞舒適的大空間。
							   	<br>
								取消活動需再開始前七天告知取消報名，將會退回款項，如因天候等不可抗拒因素，本店將主動聯繫延期或退款							   	
							   	</td>
								</tr>						   
							</tbody>
						</table>
					</div>
					
				</div>
				
				
				<div class="row justify-content-center">
					<div class="col text-center">
						<input type="hidden" name="memno" value="${memVO.memno}">
						<input type="hidden" name="actno" value="${actno}">
						<input type="hidden" name="actTalFee" value="${param.actFee}">
						<input type="hidden" name="action" value="in">
						<input class="btn btn-success"type="submit" value="參加活動" class="button">
					</div>
				</div>
			</div>
		</Form>
	<script>
		
		$('#actParEnr').change(function(){
			var actParEnr = $(this).val();
			console.log($(this).val());
			$('#actTalFee').text(${param.actFee} * actParEnr);
			$("#actTalFee").val(${param.actFee} * actParEnr);
		});
	</script>
	
	
<br>
<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp actPostDate = null;
  try {
	  actPostDate = PV.getActPatTime();
   } catch (Exception e) {
	   actPostDate = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/activity/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/activity/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/activity/datetimepicker/jquery.datetimepicker.full.js"></script>

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