<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>MISS M會員中心</title>
<%@ include file="/front-end/front-end-head.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/member_center_n.css"> --%>

<style>
#main{
	background-image: url(${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg);
	height:100vh;
}
#second-main{
	opacity:.9;
}
</style>
</head>
<body>
	<div class="container-fluid" id="main">
		
		<div class="row" id="second-main">
		
		  <div class="col-2" style="padding:0;">
		    <div class="list-group" id="list-tab" role="tablist">
		      <a class="list-group-item" onclick='return click(this);' disabled='ture'>
			      <img style='width: 50px; height: 50px; border-radius: 50%;' src='${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}'>
			      ${sessionScope.memVO.mName } ${(sessionScope.memVO ==null) ? '':'您好~' }
		      </a>
		      <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/front-end/index.jsp">
		      	<i class="fa fa-home fa-2x"></i> 回首頁
		      </a>
		      <a class="list-group-item list-group-item-action " id="listOneMem" data-toggle="list" href="#list-listOneMem" role="tab" aria-controls="home">
		      	<i class="fa fa-address-card fa-2x" aria-hidden="true"></i> 個人資料
		      </a>
		      <a class="list-group-item list-group-item-action" id="coupon" data-toggle="list" href="#list-coupon" role="tab" aria-controls="profile">
		      	<i class="fa fa-ticket fa-2x" aria-hidden="true"></i> 優惠券
		      </a>
		      <a class="list-group-item list-group-item-action" id="onLineOrder" data-toggle="list" href="#list-onLineOrder" role="tab" aria-controls="messages">
		      	<i class="fa fa-shopping-bag fa-2x" aria-hidden="true"></i> 訂購清單
		      </a>
		      <a class="list-group-item list-group-item-action" id="booking" data-toggle="list" href="#list-booking" role="tab" aria-controls="settings">
		      	<i class="fas fa-chair fa-2x"></i> 訂位查詢
		      </a>
		      <a class="list-group-item list-group-item-action" id="activity" data-toggle="list" href="#list-activity" role="tab" aria-controls="settings">
		      	<i class=""></i> 活動查詢
		      </a>
		    </div>
		  </div>
		  
		  <div class="col-10">
		    <div class="tab-content" id="nav-tabContent">
		      <div class="tab-pane fade show active" id="list-listOneMem" role="tabpanel" aria-labelledby="listOneMem">
<!-- 						這是會員個人資料 -->
						<%@ include file="/front-end/mem/listOneMem.jsp"%>
		      </div>
		      <div class="tab-pane fade" id="list-coupon" role="tabpanel" aria-labelledby="coupon">
		    		優惠券(未導入)
		      </div>
		      <div class="tab-pane fade" id="list-onLineOrder" role="tabpanel" aria-labelledby="list-onLineOrder">
		      	訂單查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="list-booking" role="tabpanel" aria-labelledby="booking-list">
		      	訂位查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="list-activity" role="tabpanel" aria-labelledby="activity-list">
		      	活動查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="booking" role="tabpanel" aria-labelledby="booking-list">
		      	意見反映查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="booking" role="tabpanel" aria-labelledby="booking-list">
		      	留言紀錄(未導入)
		      </div>
		    </div>
		  </div>
		  
		</div>
		
		
		
	</div>
	


<!-- 	<div class="area"> -->
<%-- 		<img alt="" style=" width:100%; height:100%;  --%>
<%-- 		background-image: url(${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg);"> --%>
<!-- 	</div> -->
	
<!-- 	<nav class="main-menu"> -->
<!-- 		<ul> -->
<%-- 			<li style="padding:0 0 0 10; color:#FFF; display: ${(sessionScope.memVO == null) ? 'none':'display'}; height:50px;"> --%>
<%-- 				<img style='width: 50px; height: 50px; border-radius: 50%;' src='${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}'> --%>
<%-- 				${sessionScope.memVO.mName } ${(sessionScope.memVO ==null) ? '':'您好~' } --%>
<!-- 			</li> -->
			
<!-- 			<li> -->
<%-- 				<a href="${pageContext.request.contextPath}/front-end/index.jsp"> --%>
<!-- 					<i class="fa fa-home fa-2x"></i> -->
<!-- 					<span class="nav-text">回首頁</span> -->
<!-- 				</a> -->
<!-- 			</li> -->
			
<%-- 			<c:if test="${sessionScope.memVO.mStatus == 1 }"> --%>
<!-- 			<li> -->
<%-- 				<a href="${pageContext.request.contextPath}/front-end/mem/listOneMem.jsp"> --%>
<!-- 					<i class="fa fa-address-card" aria-hidden="true"></i> -->
<!-- 					<span class="nav-text">個人資料</span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<%-- 			<li style="display:${(sessionScope.memVO.mStatus == 1) ? 'none':'display'}"> --%>
			
<%-- 				<a href="${pageContext.request.contextPath}/front-end/mem/identify.jsp"> --%>
<!-- 					<i class="fa fa-key" aria-hidden="true"></i> -->
<!-- 					<span class="nav-text">重新驗證</span> -->
<!-- 				</a> -->
			
<!-- 			</li> -->
			
<!-- 			<li class="has-subnav"> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-laptop fa-2x"></i> -->
<!-- 					<span class="nav-text"> Stars Components </span> -->
<!-- 				</a> -->

<!-- 			</li> -->
<!-- 			<li class="has-subnav"> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-list fa-2x"></i> -->
<!-- 					<span class="nav-text"> Forms </span> -->
<!-- 				</a> -->

<!-- 			</li> -->
<!-- 			<li class="has-subnav"> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-folder-open fa-2x"></i> -->
<!-- 					<span class="nav-text"> Pages </span> -->
<!-- 				</a> -->

<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-bar-chart-o fa-2x"></i> -->
<!-- 					<span class="nav-text"> Graphs and Statistics </span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-font fa-2x"></i> -->
<!-- 					<span class="nav-text"> Quotes </span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-table fa-2x"></i> -->
<!-- 					<span class="nav-text"> Tables </span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-map-marker fa-2x"></i> -->
<!-- 					<span class="nav-text"> Maps </span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#"> -->
<!-- 					<i class="fa fa-info fa-2x"></i> -->
<!-- 					<span class="nav-text"> Documentation </span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<%-- 			</c:if> --%>
<!-- 		</ul> -->
		
<!-- 		<ul class="logout"> -->
<!-- 			<li> -->
<%-- 				<a href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do"> --%>
<!-- 					<i class="fa fa-power-off fa-2x"></i> -->
<!-- 					<span class="nav-text">Logout</span> -->
<!-- 				</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 	</nav> -->

	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>