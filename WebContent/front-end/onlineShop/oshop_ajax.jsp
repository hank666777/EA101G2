<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	ProductService goodsSvc = new ProductService();
	List<ProductVO> list = goodsSvc.getProductByStatus(1);
	pageContext.setAttribute("list", list);

	MemVO SmemVO = (MemVO) session.getAttribute("memVO");
%>

<html lang="zh-Hant-TW">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>線上購物頁面</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700,900">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<style>
@font-face {
	font-family: 'Source Sans Pro';
	font-style: normal;
	font-weight: 200;
	src: local('Source Sans Pro ExtraLight'),
		local('SourceSansPro-ExtraLight'),
		url(https://fonts.gstatic.com/s/sourcesanspro/v13/6xKydSBYKcSV-LCoeQqfX1RYOo3i94_wlxdr.ttf)
		format('truetype');
}

@font-face {
	font-family: 'Source Sans Pro';
	font-style: normal;
	font-weight: 300;
	src: local('Source Sans Pro Light'), local('SourceSansPro-Light'),
		url(https://fonts.gstatic.com/s/sourcesanspro/v13/6xKydSBYKcSV-LCoeQqfX1RYOo3ik4zwlxdr.ttf)
		format('truetype');
}

@font-face {
	font-family: 'Source Sans Pro';
	font-style: normal;
	font-weight: 400;
	src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'),
		url(https://fonts.gstatic.com/s/sourcesanspro/v13/6xK3dSBYKcSV-LCoeQqfX1RYOo3qOK7g.ttf)
		format('truetype');
}

@font-face {
	font-family: 'Source Sans Pro';
	font-style: normal;
	font-weight: 600;
	src: local('Source Sans Pro SemiBold'), local('SourceSansPro-SemiBold'),
		url(https://fonts.gstatic.com/s/sourcesanspro/v13/6xKydSBYKcSV-LCoeQqfX1RYOo3i54rwlxdr.ttf)
		format('truetype');
}

@font-face {
	font-family: 'Source Sans Pro';
	font-style: normal;
	font-weight: 700;
	src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'),
		url(https://fonts.gstatic.com/s/sourcesanspro/v13/6xKydSBYKcSV-LCoeQqfX1RYOo3ig4vwlxdr.ttf)
		format('truetype');
}

@font-face {
	font-family: 'Source Sans Pro';
	font-style: normal;
	font-weight: 900;
	src: local('Source Sans Pro Black'), local('SourceSansPro-Black'),
		url(https://fonts.gstatic.com/s/sourcesanspro/v13/6xKydSBYKcSV-LCoeQqfX1RYOo3iu4nwlxdr.ttf)
		format('truetype');
}

body {
	background: #dce1df;
	color: #4f585e;
	font-family: 'Noto Sans TC', "Helvetica", "Arial", "LiHei Pro",
		"Microsoft JhengHei", Verdana, sans-serif;
	text-rendering: optimizeLegibility;
}

a.btn {
	background: #0096a0;
	border-radius: 4px;
	box-shadow: 0 2px 0px 0 rgba(0, 0, 0, 0.25);
	color: #ffffff;
	display: inline-block;
	padding: 6px 30px 8px;
	position: relative;
	text-decoration: none;
	-webkit-transition: all 0.1s 0s ease-out;
	transition: all 0.1s 0s ease-out;
}

.no-touch input.btn:hover {
	background: #00a2ad;
	box-shadow: 0px 8px 2px 0 rgba(0, 0, 0, 0.075);
	-webkit-transform: translateY(-2px);
	transform: translateY(-2px);
	-webkit-transition: all 0.25s 0s ease-out;
	transition: all 0.25s 0s ease-out;
}

.no-touch input.btn:active, input.btn:active {
	background: #008a93;
	box-shadow: 0 1px 0px 0 rgba(255, 255, 255, 0.25);
	-webkit-transform: translate3d(0, 1px, 0);
	transform: translate3d(0, 1px, 0);
	-webkit-transition: all 0.025s 0s ease-out;
	transition: all 0.025s 0s ease-out;
}

div.cards {
	margin: 10px auto 10px auto;
	max-width: 100%;
	text-align: center;
}

div.card {
	background: #ffffff;
	display: inline-block;
	margin: 8px;
	max-width: 300px;
	position: relative;
	text-align: left;
	-webkit-transition: all 0.3s 0s ease-in;
	transition: all 0.3s 0s ease-in;
	width: 300px;
	z-index: 1;
}

form {
	display: inline-block;
}

div.card .card__image-holder {
	background: rgba(0, 0, 0, 0.1);
	height: 0;
	padding-bottom: 75%;
}

div.card div.card-title {
	background: #ffffff;
	padding: 6px 15px 10px;
	position: relative;
	z-index: 0;
}

div.card div.card-title a.toggle-info {
	border-radius: 32px;
	height: 32px;
	padding: 0;
	position: absolute;
	right: 15px;
	top: 10px;
	width: 32px;
}

div.card div.card-title a.toggle-info span {
	background: #ffffff;
	display: block;
	height: 2px;
	position: absolute;
	top: 16px;
	-webkit-transition: all 0.15s 0s ease-out;
	transition: all 0.15s 0s ease-out;
	width: 12px;
}

div.card div.card-title a.toggle-info span.left {
	right: 14px;
	-webkit-transform: rotate(45deg);
	transform: rotate(45deg);
}

div.card div.card-title a.toggle-info span.right {
	left: 14px;
	-webkit-transform: rotate(-45deg);
	transform: rotate(-45deg);
}

div.card div.card-title h2 {
	font-size: 24px;
	font-weight: 700;
	letter-spacing: -0.05em;
	margin: 0;
	padding: 0;
}

div.card div.card-title h2 small {
	display: block;
	font-size: 18px;
	font-weight: 600;
	letter-spacing: -0.025em;
}

div.card div.card-description {
	padding: 0 15px 10px;
	position: relative;
	font-size: 14px;
	background-color: white;
	z-index: -1;
}

div.card div.card-actions {
	box-shadow: 0 2px 0px 0 rgba(0, 0, 0, 0.075);
	background: #d9d9d9;
	padding: 10px 15px 20px;
	text-align: center;
}

div.card div.card-flap {
	background: #d9d9d9;
	position: absolute;
	width: 100%;
	-webkit-transform-origin: top;
	transform-origin: top;
	-webkit-transform: rotateX(-90deg);
	transform: rotateX(-90deg);
}

div.card div.flap1 {
	-webkit-transition: all 0.3s 0.3s ease-out;
	transition: all 0.3s 0.3s ease-out;
	z-index: -1;
}

div.card div.flap2 {
	-webkit-transition: all 0.3s 0s ease-out;
	transition: all 0.3s 0s ease-out;
	z-index: -2;
}

div.cards.showing div.card {
	cursor: pointer;
	opacity: 0.6;
	-webkit-transform: scale(0.88);
	transform: scale(0.88);
}

.no-touch div.cards.showing div.card:hover {
	opacity: 0.94;
	-webkit-transform: scale(0.92);
	transform: scale(0.92);
}

div.card.show {
	opacity: 1 !important;
	-webkit-transform: scale(1) !important;
	transform: scale(1) !important;
}

div.card.show div.card-flap {
	background: #ffffff;
	-webkit-transform: rotateX(0deg);
	transform: rotateX(0deg);
}

div.card.show div.flap1 {
	-webkit-transition: all 0.3s 0s ease-out;
	transition: all 0.3s 0s ease-out;
}

div.card.show div.flap2 {
	-webkit-transition: all 0.3s 0.2s ease-out;
	transition: all 0.3s 0.2s ease-out;
}

#hoot {
	width: 300px;
	height: 150px;
	margin: auto;
	margin-bottom: -10px;
}

#hoot img {
	width: 125px;
	height: 125px;
	margin: auto;
	border-radius: 50%;
}

.push {
	display: none;
}
</style>
<%@ include file="/front-end/front-end-head.jsp"%>
</head>
<body>
	<%@ include file="/front-end/front-end-header.jsp"%>
	<%@ include file="/front-end/front-end-header2.jsp"%>
	<div id="hoot">
		<a href="<%=request.getContextPath()%>/front-end/onlineShop/OCart.jsp">
			<img src="${pageContext.request.contextPath}/images/back-end/shopcar.png">
			<font size="+2.5"> 我的購物車 </font>
		</a>
	</div>

	<!-- 以下為測試AJAX非同步查詢，撰寫中 -->
	<div class="container">
		<div class="row">
			<div class="col w-50">

				<input id="textSearchAera" type="text" class="form-control">
			</div>


		</div>
		<div class="row">
			<div id="searchInfo" style="height:100vh;" class="col-3">
				
				<!-- 搜尋結果 -->
<!-- 				<div class="cards"> -->
			
<!-- 					<div class="card"> -->
<!-- 						<div class="card__image-holder"> -->
<!-- 							<img class="card__image" -->
<%-- 								src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${listVO.pno}" alt="wave" --%>
<!-- 								style="width: 298px;height:230px;" > -->
<!-- 						</div> -->
<!-- 						<div class="card-title"> -->
<!-- 							<a href="#" class="toggle-info btn"> <span class="left"></span> -->
<!-- 								<span class="right"></span> -->
<!-- 							</a> -->
<!-- 							<h2> -->
<%-- 								商品名稱:${listVO.pname}<small>商品價格:$${listVO.pP}</small> --%>
<!-- 							</h2> -->
<!-- 						</div> -->
<!-- 						<div class="card-flap flap1"> -->
<%-- 							<div class="card-description">商品明細:${listVO.pDes}</div> --%>
<!-- 							<div class="card-flap flap2"> -->
<!-- 								<div class="card-actions">數量: -->
<!-- 							        <select size="1" name="pDoffer"> -->
<!-- 										<option value="1">1</option> -->
<!-- 										<option value="2">2</option> -->
<!-- 										<option value="3">3</option> -->
<!-- 										<option value="4">4</option> -->
<!-- 										<option value="5">5</option> -->
<!-- 										<option value="6">6</option> -->
<!-- 										<option value="7">7</option> -->
<!-- 										<option value="8">8</option> -->
<!-- 										<option value="9">9</option> -->
<!-- 									</select> -->
<!-- 									<input type="hidden" name="action" value="ADD"> -->
<%-- 									<input type="hidden" name="pno" value="${listVO.pno}"> --%>
<%-- 									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 									<input type="submit" name="Submit" value="加入購物車" class="btn"> -->
<!-- 								</div> -->
<%-- 								<input type="hidden" name="pPic" value="${listVO.pPic}"> --%>
<%-- 								<input type="hidden" name="pname" value="${listVO.pname}"> --%>
<%-- 								<input type="hidden" name="pP" value="${listVO.pP}"> --%>
<%-- 								<input type="hidden" name="pDes" value="${listVO.pDes}"> --%>
								
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 				</div> -->
				
				
				
				
			</div>
		</div>
	</div>



	<script>
		$('#textSearchAera').keypress(function() {
			$('#searchInfo').html('搜尋中....');
		});
		
		$('#textSearchAera').on('change',function(data) {
			var textSearchAera = $('#textSearchAera').val();

			$.ajax({
				url : '${pageContext.request.contextPath}/product/OnlineShopServlet.do',
				type : 'post',
				dataType : 'json',
				cache : false,
				//資料進控制器
				data : {
					action : 'listProduct_ByCompositeQuery_ajax',
					product : textSearchAera
				},

				stop : function(reponse) {
					$('#searchInfo').html('');
				},

				beforeSend : function(reponse) {
					$('#searchInfo').html('等待搜尋結果...');
				},

				//接收資料
				success : function(data) {
					console.log('data:' + data)
					let str = "";
					if(data.length !=0){
						for (let i=0 ; i< data.length ; i++){
							str +=
								"<div class='cards'><div class='card'><div class='card__image-holder'>"+
									"<img class='card__image' src='<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno='" + data[i].pno + " alt='wave'" +
												"style='width: 298px;height:230px;' ></div><div class='card-title'><a href='#' class='toggle-info btn'> <span class='left'></span>"+
												"<span class='right'></span>"+
											"</a><h2>商品名稱:" + data[i].pname + `<small>商品價格:$`+ `data[i].pP` +`</small>`+
											`</h2>`+
										`</div>`+
										`<div class="card-flap flap1">`+
											`<div class="card-description">商品明細:`+ data[i].pDes +`</div>`+
											`<div class="card-flap flap2">`+
												`<div class="card-actions">數量:`+
											        `<select size="1" name="pDoffer">`+
														`<option value="1">1</option>`+
														`<option value="2">2</option>`+
														`<option value="3">3</option>`+
														`<option value="4">4</option>`+
														`<option value="5">5</option>`+
														`<option value="6">6</option>`+
														`<option value="7">7</option>`+
														`<option value="8">8</option>`+
														`<option value="9">9</option>`+
													`</select>`+
													`<input type="hidden" name="action" value="ADD">`+
													`<input type="hidden" name="pno" value="`+ data[i].pno +`">`+
													`<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">`+
													`<input type="submit" name="Submit" value="加入購物車" class="btn">`+
												`</div>`+
												`<input type="hidden" name="pPic" value="`+ data[i].pP  +`">`+
												`<input type="hidden" name="pname" value="`+ data[i].pname + `">`+
												`<input type="hidden" name="pP" value="` + data[i].pP + `">`+
												`<input type="hidden" name="pDes" value="`+ data[i].pDes +`">`+
												
											`</div>`+
										`</div>`+
									`</div>`+
									
								`</div>`
						}
					}
					$('#searchInfo').empty();
					$('#searchInfo').append(str);
// 					$("#searchInfo").html(data);
				}
			});
		});
	</script>
	<!-- 以上為測試AJAX非同步查詢，撰寫中 -->



	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var that;
			$("input.btn").click(function(e) {
				that = this;
				e.preventDefault(); // 阻止form瀏覽器預設行為           
				swal({
					title : "已新增",
					text : "您的商品已加入購物車",
					type : "success",
					showCancelButton : false,
				}).then(function() { // callback function
					$(that).closest('form').submit(); // 表單送出
				});
			});
		});

		$(document).ready(function() {
			var zindex = 10;

			$("div.card").click(function(e) {
				e.preventDefault(); // 阻止form瀏覽器預設行為 

				var isShowing = false;

				if ($(this).hasClass("show")) {
					isShowing = true;
				}

				if ($("div.cards").hasClass("showing")) {

					// a card is already in view
					$("div.card.show").removeClass("show");
					if (isShowing) {

						// this card was showing - reset the grid
						$("div.cards").removeClass("showing");
					} else {

						// this card isn't showing
						$(this).css({
							zIndex : zindex
						}).addClass("show");
					}
					zindex++;
				} else {

					//  no cards in view	      
					$("div.cards").addClass("showing");
					$(this).css({
						zIndex : zindex
					}).addClass("show");
					zindex++;
				}
			});
		});
	</script>
	<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>