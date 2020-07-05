<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
						<input type="hidden" value="" name="userName">
						<a class="nav-link" href="${pageContext.request.contextPath}/front-end/chat/member_chat.jsp">直播間</a>
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