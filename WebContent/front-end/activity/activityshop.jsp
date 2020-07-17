<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.activity.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.actparticipation.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActivityService AVS = new ActivityService();
	List<ActivityVO> list =AVS.getAll();
	pageContext.setAttribute("list",list);
	
	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>活動瀏覽</title>
<link
	rel="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="https://fonts.googleapis.com/css?family=Raleway:400, 500">
<style type="text/css">
/* Icon set - http://ionicons.com */
figure.snip1249 {
	font-family: "Raleway", Arial, sans-serif;
	position: relative;
	overflow: hidden;
	margin: 10px;
	min-width: 220px;
	max-width: 330px;
	width: 100%;
	background: #1a1a1a;
	color: #ffffff;
	text-align: left;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
	background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0.3) 100%);
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0.3) 100%);
}

figure.snip1249 * {
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all 0.35s ease-in-out;
	transition: all 0.35s ease-in-out;
}

figure.snip1249 .image {
	position: relative;
}

figure.snip1249 img { /*圖片大小*/
	width: 400px;
	height: 200px;
	/*   max-width: 100%; */
	vertical-align: top;
}

figure.snip1249 i {
	position: absolute;
	top: 7px;
	left: 12px;
	font-size: 32px;
	opacity: 0;
	z-index: 2;
	-webkit-transition-delay: 0;
	transition-delay: 0;
}

figure.snip1249 h5 {
	margin: 0;
	font-weight: 550;
	text-transform: uppercase;
}

figure.snip1249:before, figure.snip1249:after { /*左上角顯示的寬高*/
	width: 120px;
	height: 120px;
	position: absolute;
	top: 0;
	left: 0;
	content: "";
	-webkit-transition: all 0.35s ease;
	transition: all 0.35s ease;
	z-index: 1;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.5); /*左上三小角型陰影*/
	background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0.35) 100%);
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0.35) 100%);
	-webkit-transition-delay: 0.2s;
	transition-delay: 0.2s;
}

figure.snip1249:before {
	background-color: #20638f;
	-webkit-transform: skew(-45deg) translateX(-150%);
	transform: skew(-45deg) translateX(-150%);
	border-right: 1px solid #20638f;
}

figure.snip1249:after {
	background-color: #962d22;
	-webkit-transform: skew(-45deg) translateX(-175%);
	transform: skew(-45deg) translateX(-175%);
	border-right: 1px solid #962d22;
}

figure.snip1249 figcaption { /*內容描述*/
	padding: 25px 10px 25px 25px;
	background-color: #ffffff;
	color: #000000;
	position: relative;
	font-size: 0.9em;
}

figure.snip1249 figcaption p {
	margin-bottom: 15px;
}

figure.snip1249 figcaption:before { /*右下角藍條大小*/
	width: 95px;
	height: 95px;
	position: absolute;
	bottom: 0;
	right: 0;
	content: "";
	z-index: 1;
	background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0.35) 100%);
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0.35) 100%);
	background-color: #20638f;
	-webkit-transform: skew(-45deg) translateX(50%);
	transform: skew(-45deg) translateX(50%);
	border-right: 1px solid #20638f;
}

figure.snip1249 .add-to-cart { /*查看詳情*/
	display: inline-block;
	width: auto;
	border: 2px solid #20638f;
	padding: 0.4em 0.6em;
	color: #20638f;
	text-decoration: none;
	font-weight: 800;
	font-size: 0.9em;
	text-transform: uppercase;
}

figure.snip1249 .add-to-cart:hover { /*不知道還能幹嘛用的*/
	background-color: #20638f;
	color: #ffffff;
}

figure.snip1249 .price { /*右下方三角形 內容*/
	position: absolute;
	right: 0;
	bottom: 0;
	color: #ffffff;
	z-index: 2;
	text-transform: uppercase;
	padding: 5px;
	font-weight: 800;
	font-size: 1em;
	text-align: center;
}

figure.snip1249 .price s {
	display: block;
	font-size: 0.85em;
	font-weight: 400;
	opacity: 0.8;
}

figure.snip1249:hover i, figure.snip1249.hover i { /*不知道還能幹嘛用的*/
	opacity: 0.7;
	-webkit-transition-delay: 0.3s;
	transition-delay: 0.3s;
}

figure.snip1249:hover h5, figure.snip1249.hover h5 { /*移到上面 標題下拉*/
	-webkit-transform: translateY(0);
	transform: translateY(0);
	opacity: 1;
}

figure.snip1249:hover:before, figure.snip1249.hover:before { /*藍色區塊*/
	-webkit-transition-delay: 0s;
	transition-delay: 0s;
	-webkit-transform: skew(-45deg) translateX(-50%);
	transform: skew(-45deg) translateX(-50%);
}

figure.snip1249:hover:after, figure.snip1249.hover:after { /*紅色區塊*/
	-webkit-transition-delay: 0.1s;
	transition-delay: 0.1s;
	-webkit-transform: skew(-45deg) translateX(-75%);
	transform: skew(-45deg) translateX(-75%);
}
</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container">

		<div class="row">
			<div id="table">
				<p>活動列表</p>
			</div>
		</div>
		<div class="row text-center">
			<c:forEach var="AVS" items="${list}">
				<Form
					action="<%=request.getContextPath()%>/front-end/activity/activityshop.do">
					<div class="col-3"></div>

					<figure class="snip1249">
						<div class="image">
							<img src="<%=request.getContextPath()%>/front-end/activity/activitypicServlet.do?actno=${AVS.actno}"class="card-img-top" alt=""><i class="ion-ios-star-outline"></i>
						</div>
						<figcaption>
							<h5 class="card-title">${AVS.actName}</h5>
							<p class="card-text">${AVS.actDes}</p>
							<div class="price">精選活動</div>
							<input type="hidden" name="actno" value="${AVS.actno}">
							<input type="hidden" name="memno" value="${memVO.memno}">
							<input type="hidden" name="action" value="check">
							<input type="submit" value="參加活動" class="add-to-cart">
<!-- 							<a type="submit" href="#" class="add-to-cart">查看詳情</a> -->
						</figcaption>
					</figure>
					<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
					<script
						src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
					<script
						src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
					<script>
  /* Demo purposes only */
  $(".hover").mouseleave(
    function () {
      $(this).removeClass("hover");
    }
  );
					</script>
				</Form>
			</c:forEach>
		</div>
	</div>

	<br>


	<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>