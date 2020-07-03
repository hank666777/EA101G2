<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html lang="zh-Hant-TW">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<title>Miss M</title>
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<!-- 自定義CSS JS-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front-end-index.css">
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!-- font-awesome CSS-->
<script src="https://use.fontawesome.com/0114a256f7.js"></script>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nprogress.css"> --%>
<%-- <script src="${pageContext.request.contextPath}/js/nprogress.js"></script> --%>
 
</head>
<body>
<%@ include file="front-end-header.jsp"%>


	<div id="main" class="container">
		<div class="row">
			<!-- 輪播 data-interval控制速度-->
			<div id="carousel" class="carousel slide" data-ride="carousel"
				data-interval="2500">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
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
<!-- 					<div class="carousel-item"> -->
<%-- 						<img src="${pageContext.request.contextPath}/images/front-end/indexImg/0003.jpg" --%>
<!-- 							class="w-100 img-responsive d-sm-flex" alt="甜點4"> -->
<!-- 					</div> -->
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
				<form>
				
				</form>
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

<%@ include file="front-end-footer.jsp"%>

</body>
</html>