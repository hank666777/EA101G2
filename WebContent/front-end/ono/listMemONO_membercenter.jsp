<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.ono.model.*"%>
<%@ page import="com.onodetail.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.product.model.*"%>

<% 
	//取得所有訂單
	List<ONOVO> onolist = new ONOService().getAll();
	//取得登入者的訂單
	MemVO memvo1 = (MemVO)session.getAttribute("memVO");
	List<ONOVO> myONOlist = onolist.stream().filter(ono -> ono.getmemno().contains(memvo1.getMemno()))
																					.collect(Collectors.toList());
	pageContext.setAttribute("myONOlist", myONOlist);
	
	//取得一筆訂單的所有明細
	ONODetailService ondSvc = new ONODetailService();
	List<ONODetailVO> list = ondSvc.getAll();
	pageContext.setAttribute("list",list);
	
	ProductService pdSvc = new ProductService();
	List<ProductVO> pdlist = pdSvc.getAll();
	pageContext.setAttribute("pdlist",pdlist);
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.slim.js"
  integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
  crossorigin="anonymous"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>

<body>
<div id="container" class="container" style="margin:auto; margin-top:30px;">
	<div class="row">
<table id="table-1">
	<tr>
		<td>
		 <h3>線上訂單資料</h3>
		</td>
	</tr>
</table>
<table class="table table-sm text-nowrap table-hover info text-center
							shadow-lg p-3 mb-5 bg-white rounded">
	<tr class="text-center">
		<th>線上訂單編號</th>
		<th>會員姓名</th>
		<th>優惠券編號</th>
		<th>訂單時間</th>
		<th>訂單總價</th>
		<th>訂單狀態</th>
		<th>訂單付款狀態</th>
	</tr>
<c:forEach var="onVO" items="${myONOlist}">
	<tr id="${onVO.onono}" class="text-center parent">
		<td class="text-center">
			 ${onVO.onono}
		</td>
		<td class="text-center">${memVO.mName}</td>
		<td class="text-center">${onVO.couponSno}</td>
		<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${onVO.onoTime}"></fmt:formatDate></td>
		<td class="text-center">${onVO.onoTotal}</td> 
		<td class="text-center">
			${(onVO.onoStatus == 0)? '<p class="text-danger">未完成<p>':''}
			${(onVO.onoStatus == 1)? '<p class="text-success">已完成<p>':''}
			${(onVO.onoStatus == 2)? '已取消':''}
		</td>
		<td class="text-center">
			${(onVO.onoPay == 0)? '<p class="text-danger">未結帳<p>':'<p class="text-success">結帳<p>'}
		</td>
	</tr>
	<tr id="child_${onVO.onono}">
		<td>
		
		<!-- 訂單明細 -->
		<table class="table table-sm text-nowrap table-hover info text-center
									shadow-lg p-3 bg-white rounded my-0">
				<tr class="text-center">
					<th>商品編號</th>
					<th>購買數量</th>
					<th>單品價格</th>
			    </tr>
		
			    <c:forEach var="ondVO" items="${list}">
						<c:if test="${(onVO.onono eq ondVO.onono)}">
		
							<tr class="text-center">
			
									<c:forEach var="pdVO" items="${pdlist}">
											<c:if test="${(pdVO.pno eq ondVO.pno)}">
												<td class="text-center">${pdVO.pname}</td>
											</c:if>
									</c:forEach>
									
									<td class="text-center" id="qty">${ondVO.onoQty}</td>
									<td class="text-center" id="price">${ondVO.onoPrice}</td>
		
							</tr>
						</c:if>
				</c:forEach>
		</table>
								
								
		</td>
	</tr>
	<script type="text/javascript">
  	//表格的展開
		$(function(){
	    $("tr#${onVO.onono}").click(function(){
	        $(this)
	        .toggleClass("selected")
	        .siblings('#child_${onVO.onono}').toggle();
	    }).click();
		});
	</script>
	



</c:forEach>
</table>

		
	
	</div>
</div>
<%-- <%@ include file="/back-end/back-end-footer.jsp"%> --%>

	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	
</body>
</html>