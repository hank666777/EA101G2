<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.mycoupon.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
	//取得會員物件
	MemVO member = (MemVO) session.getAttribute("memVO");
	
  MyCpService myCpService = new MyCpService();
//   List<MyCouponVO> list = myCpService.getMyCoupon(member.getMemno());
//   pageContext.setAttribute("list", list);
  List<MyCouponVO> myCoupon = myCpService.getMyCoupon(member.getMemno());
  session.setAttribute("myCoupon", myCoupon);
%>
<jsp:useBean id="cps" scope="page" class="com.coupon.model.CpService" />

<html>
<head>
<title>MISS M會員優惠券</title>
<%@ include file="/front-end/front-end-head.jsp"%>
<style type="text/css">
img {
	width: 100px;
	padding: 0px;
}

td {
	height: 50px;
	line-height: 50px;
}

table {
	background-color: rgb(245, 245, 245);
}
</style>

</head>
<body>
	<div class="container">
		<div class="row">
			<a href="<%=request.getContextPath()%>/mycoupon/mycoupon.do?memno=${memVO.memno}">我的優惠券</a>
			<table class="table text-nowrap table-hover info text-center">
				<thead class="bg">
					<tr>
						<th>我的優惠券</th>
						<th>優惠券名稱</th>
						<th>優惠券折扣(折)</th>
						<th>優惠券數量(張)</th>
					</tr>
				</thead>
				<%@ include file="page1.file"%>
				<c:forEach var="myCoupon" items="${myCoupon}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

					<tr class="text-center">
						<td class="text-center" style="height:35px;">
							<img src="<%=request.getContextPath()%>/mycoupon/imgs?couponno=${myCoupon.couponno}" />
						</td>

						<c:forEach var="CouponVO" items="${cps.all}">
							<c:if test="${myCoupon.couponno==CouponVO.couponno}">
								<td class="text-center">${CouponVO.couponName}</td>
								<td class="text-center">${CouponVO.couponDiscount}</td>
							</c:if>
						</c:forEach>

						<td class="text-center">${myCoupon.count}</td>
					</tr>

				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>