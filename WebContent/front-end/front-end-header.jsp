<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<!-- first navbar -->
	<div class="container-fluid bg-white fixed-top" style="height:45px; opacity:.8;">
		<div class="row ">

			<div class="col ">
				<div class="header-user-info ">
<!-- 				<i class=""> -->
					
					<c:if test="${sessionScope.memVO != null}">
						<i class=""><img style='width:25px; height:25px; border-radius:50%;'
														 src='${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}'>
						</i>
					</c:if>
					<i class="ml-auto"></i> ${sessionScope.memVO.mName } ${(sessionScope.memVO ==null) ? '':'您好~' }
				
					<a id="member" href="${pageContext.request.contextPath}/front-end/index.jsp"> 
						<i class="fa fa-home" aria-hidden="true"></i> 回首頁
					</a>
					<a id="member" href="${pageContext.request.contextPath}/front-end/mem/member_center.jsp"> 
						<i class="fa fa-user"></i> 會員中心
					</a> 
					<a id="cart" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-shopping-cart"></i> 購物車
					</a> 
					<a id="myfavor" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-heart"></i> 追蹤清單
					</a> 
					<a id="menberlogin" style="display: ${(sessionScope.memVO == null) ? 'display':'none'};"
					href="${pageContext.request.contextPath}/front-end/mem/memberlogin.jsp"> 
						<i class="fa fa-unlock-alt"></i> 會員登入
					</a>
					<a id="" style="display: ${(sessionScope.memVO == null) ? 'none':'display'};" 
							href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do"> 
						<i class="fa fa-share-square"></i> 會員登出
					</a>
				</div>
			</div>
		</div>
	</div>