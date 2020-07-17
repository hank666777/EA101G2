<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<html lang="zh-Hant-TW">
<head>
<!-- Required meta tags -->
<style>
#main1{
	background-color: #FFF; 
}
.introduce-bg2#p{
	font-size:1.2em;
}
</style>
<title>Miss M</title>

<%@ include file="/front-end/front-end-head.jsp"%>
</head>

<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>

<div id="main1" class="container-fluid">
	<div class="row">
	

	<div id="main" class="container">
		<div class="row">
			<!-- 輪播 data-interval控制速度-->
			<div id="carousel" class="carousel slide" data-ride="carousel"
				data-interval="2500">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
				</ol>
	
				<div class="carousel-inner" style="width: 100%; height: auto;">
					<div class="carousel-item active">
						<img src="${pageContext.request.contextPath}/images/front-end/indexImg/0003.jpg"
							class="w-100 img-responsive d-sm-flex" alt="甜點1">
						<div class="carousel-caption d-none d-md-block banner-block">
							<h1 class="display-3">休閒氣氛</h1>
							<p class="h3">讓你捨不得回家的愉悅心情</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/images/front-end/indexImg/0005.jpg"
							class="w-100 img-responsive d-sm-flex" alt="甜點2">
						<div class="carousel-caption d-none d-md-block banner-block">
							<h1 class="display-3">美味餐點</h1>
							<p class="h3">家人、朋友分享的美食饗宴</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/images/front-end/indexImg/0006.jpg"
							class="w-100 img-responsive d-sm-flex" alt="甜點3">
						<div class="carousel-caption d-none d-md-block banner-block">
							<h1 class="display-3">門市資訊</h1>
							<p class="h3">中央大學-中央店</p>
						</div>
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<!-- memu info -->
		<div class="row my-4">
			<div class="col text-center">
				<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-title2.jpg"
					class="img-fluid" alt="">
			</div>
		</div>
		<div class="row my-4">
			<div class="col text-center">
				<%@ include file="/front-end/product/frontProduct3pic.jsp" %>
			</div>
		</div>


		<!-- restaurant introduction -->

		<div class="row my-4">
			<div class="col text-center mx-auto d-block">
				<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-title1.jpg" alt=""
					class="img-fluid">
			</div>
		</div>
		<div class="introduce-bg2">
			<div class="row">
				<div class="col-md" style="width: auto;">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-2.jpg"
						class="img-fluid img-thumbnail">
				</div>
				<div class="col-md Caption">
					<p>精湛廚藝，一味入魂，食的藝術</p>
				</div>
				<div class="col-md" style="width: auto;">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-3.jpg"
						class="img-fluid img-thumbnail">
				</div>
				<div class="col-md Caption">
					<p>動物友善，歡迎毛小孩與其他寵物</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md" style="width: auto;">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-4.jpg"
						class="img-fluid img-thumbnail">
				</div>
				<div class="col-md Caption">
					<p>健康取向，好的食材、合理的價格</p>
				</div>
				<div class="col-md" style="width: auto;">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-5.jpg"
						class="img-fluid img-thumbnail">
				</div>
				<div class="col-md Caption">
					<p>主廚當家，美味把關，道道精彩</p>
				</div>
			</div>
		</div>


		<!-- chef info -->
		<div class="row my-4">
			<div class="col text-center">
				<img src="${pageContext.request.contextPath}/images/front-end/indexImg/introduce-title3.jpg"
					class="img-fluid" alt="">
			</div>
		</div>
		
		<div class="row w-80 chefinfo mx-auto">
			<div class="col-sm col-md">
				<figure class="figure">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/chef-2.jpg"
						class="img-fluid" alt="">
					<figcaption style="font-size: 1.5em;" class="figure-caption text-right">行政主廚 -
						Peter Wu</figcaption>
				</figure>
			</div>
			<div class="col-sm col-md">
				<figure class="figure">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/chef-3.jpg"
						class="img-fluid" alt="">
					<figcaption style="font-size: 1.5em;" class="figure-caption text-right h3">第一主廚 -
						MARY JANE</figcaption>
				</figure>
			</div>
			<div class="col-sm col-md">
				<figure class="figure">
					<img src="${pageContext.request.contextPath}/images/front-end/indexImg/chef-1.jpg"
						class="img-fluid" alt="">
					<figcaption style="font-size: 1.5em;" class="figure-caption text-right h3">首席主廚 -
						Jeff Lee</figcaption>
				</figure>
			</div>
		</div>

	</div>
	
	</div>
</div>
<%@ include file="front-end-footer.jsp"%>
<script>
//優惠卷及商品推播


window.onload = function connect2() {
	var MyPoint = "/PushSocket/${memVO.mAccount}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket = new WebSocket(endPointURL);
	function cloze() {
		webSocket.close();
		console.log("colse");
	}
	webSocket.onopen = function(event) {
		console.log("Connect Success77777!");
	}
	webSocket.onmessage = function(event) {
		if ((event.data).endsWith("券")) {
			Swal.fire({
				title : '註冊會員即送優惠券!!',
				text : event.data,
				imageWidth : 400,
				imageHeight : 200,
				footer : '<a href=mem/member_center.jsp>立即查看</a>'
			})
		} else {
			Swal.fire({
				title : '目前有新商品上架囉!!',
				text : event.data,
				imageWidth : 400,
				imageHeight : 200,
				footer : '<a href=onlineShop/OShop.jsp>立即查看</a>'
			})
		}
	}
}
</script>
</body>
</html>