<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<!-- first navbar -->
	<div class="container-fluid fixed-top bg-white" style="height:35px; opacity:.8;">
		<div class="row">

			<div class="col">
				<div class="header-user-info">
					<a id="member" href="${pageContext.request.contextPath}/front-end/mem/member_center.jsp"> 
						<i class="fa fa-user"></i> 會員中心
					</a> 
					<a id="cart" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-shopping-cart"></i> 購物車
					</a> 
					<a id="myfavor" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-heart"></i> 追蹤清單
					</a> 
					<a id="menberlogin" style="display: ${(MemVO == null) ? 'display':'none'};"
					href="${pageContext.request.contextPath}/front-end/mem/memberlogin.jsp"> 
						<i class="fa fa-unlock-alt"></i> 會員登入
					</a>
					<c:if test="${MemVO == null}">
						<a id="" style="display: ${(MemVO != null) ? 'display':'none'};" 
								href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do"> 
							<i class="fa fa-share-square"></i> 會員登出
						</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>


	<!-- second navbar -->
	<div class="header-bottom container mt-4" style="height:10vh;">

		<nav class="secondnavbar navbar navbar-expand-xl navbar-light">
			<a class="navbar-brand" href="#">
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
					<li class="nav-item active font-weight-bold">
						<a class="nav-link" href="${pageContext.request.contextPath}/front-end/index.jsp">首頁</a>
					</li>

					<li class="nav-item font-weight-bold">
						<a class="nav-link" href="#">線上訂餐</a>
					</li>

					<li class="nav-item font-weight-bold">
						<a class="nav-link"	href="#">線上訂位</a>
					</li>

					<li class="nav-item font-weight-bold">
						<a class="nav-link" href="#">活動快訊</a>
					</li>

					<li id="livestream font-weight-bold" class="nav-item">
						<a class="nav-link" href="#">直播間</a>
					</li>

					<li class="nav-item font-weight-bold dropdown">
						<a class="nav-link dropdown-toggle" href="" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">FAQ 
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">關於我們</a> 
							<a class="dropdown-item" href="#">留言板</a>
							<a class="dropdown-item" href="#">意見反映</a>
						</div>
					</li>
				</ul>
				
				<form class="form-inline" action="" method="">
					<div>
						<input class="form-control mr-sm-2" type="text"
							placeholder="請輸入商品名稱..." aria-label="Search">
					</div>
					<div>
<!-- 						<input type="hidden" name="action" value=""> -->
<!-- 						<input type="hidden" name="pno" value=""> -->
						<input type="submit" class="btn btn-outline-success my-2 my-sm-0" value="搜尋商品">
					</div>
				</form>
			</div>
		</nav>

	</div>