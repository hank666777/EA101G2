<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- second navbar -->
	<div class="header-bottom container" style="padding:0">

		<nav class="secondnavbar navbar navbar-expand-xl navbar-light my-2">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/front-end/index.jsp">
				<img src="${pageContext.request.contextPath}/images/logo.png" alt="Miss M logo"
				style="width: 70px; height: 70px;">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
<!-- 					<li class="nav-item active font-weight-bold"> -->
<%-- 						<a class="nav-link" href="${pageContext.request.contextPath}/front-end/index.jsp">首頁</a> --%>
<!-- 					</li> -->

					<li class="nav-item font-weight-bold">
						<a class="nav-link" href="${pageContext.request.contextPath}/front-end/onlineShop/OShop.jsp">線上訂餐</a>
					</li>

					<li class="nav-item font-weight-bold">
						<a class="nav-link"	href="${pageContext.request.contextPath}/front-end/bok/booking_page_user.jsp">線上訂位</a>
					</li>

					<li class="nav-item font-weight-bold">
						<a class="nav-link" href="${pageContext.request.contextPath}/front-end/activity/front_page.jsp">活動快訊</a>
					</li>

					<li id="massageboard" class="nav-item font-weight-bold">
						<a class="nav-link" href="${pageContext.request.contextPath}/front-end/messageboard/listAllMessageBoard.jsp">留言板</a>
					</li>

					<li class="nav-item font-weight-bold dropdown">
						<a class="nav-link dropdown-toggle" href="" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">FAQ 
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/front-end/about_us.jsp">關於我們</a> 
							<a class="dropdown-item" href="#">意見反映</a>
						</div>
					</li>
				</ul>
				
				<form class="form-inline my-3" action="<%=request.getContextPath()%>/product/OnlineShopServlet.do" method="post">
					<div>
						<input class="form-control mr-sm-2" type="text"
							placeholder="請輸入商品名稱..." aria-label="Search" name="pname">
					</div>
					<div>
						<input type="hidden" name="action" value="listProduct_ByCompositeQuery">
						<input type="submit" class="btn-outline-success my-2 my-sm-0" value="搜尋商品" style="height: 35px;">
					</div>
				</form>
			</div>
		</nav>

	</div>