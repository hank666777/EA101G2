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
	//ONODetailService ondSvc = new ONODetailService();
	
%>
<% 
	ONODetailService ondSvc = new ONODetailService();
	List<ONODetailVO> onoDetailList = ondSvc.getAll();
	pageContext.setAttribute("onoDetailList",onoDetailList);
	
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
<style>
/*   table#table-1 { */
/* 	background-color: #CCCCFF; */
/*     border: 2px solid black; */
/*     text-align: center; */
/*   } */
/*   table#table-1 h4 { */
/*     color: red; */
/*     display: block; */
/*     margin-bottom: 1px; */
/*   } */
/*   h4 { */
/*     color: blue; */
/*     display: inline; */
/*   } */
/*   table { */
/* 	width: 600px; */
/* 	background-color: white; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
/*   } */
/*   table, th, td { */
/*     border: 1px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 5px; */
/*     text-align: center; */
/*   } */
</style>
</head>
<body>
<%-- <%@ include file="/back-end/back-end-header.jsp" %> --%>
<div id="container" class="container" style="margin:auto; margin-top:30px;">
	<div class="row">
<!-- <table id="table-1"> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 		 <h3>現場訂單資料</h3> -->
<!-- 		 <h4> -->
<%-- 			 <a href="<%=request.getContextPath()%>/front-end/ono/select_page.jsp"> --%>
<%-- 			 <img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a> --%>
<!-- 		 </h4> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->
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
		<table class="table table-sm text-nowrap">  
			<tr class="text-center">
				<th>商品名稱</th>
				<th>購買數量</th>
				<th>單品價格</th>
			</tr>
	 
<%-- 		    <c:forEach var="ondVO" items="${onoDetailList}"> --%>
<%-- 		    	<c:forEach var="onVO" items="${myONOlist}"> --%>
<%-- 					<c:if test="${(onVO.onono eq ondVO.onono)}"> --%>
	<c:forEach var="ondVO" items="${myONOlist.oneDetails(onVO.onono)}">
	<c:if test="${ondVO.onono eq onVO.onono}">
	
						<tr class="text-center">
		
								<c:forEach var="pdVO" items="${pdlist}">
<%-- 										<c:if test="${(pdVO.pno eq ondVO.pno)}"> --%>
<%-- 											<td class="text-center">${pdVO.pname}</td> --%>
<td class="text-center">${pdVO.pname }</td>
<td class="text-center">${ondVO.onoQty }</td>
<td class="text-center">${ondVO.onoPrice }</td>
<%-- 										</c:if> --%> 
								</c:forEach>
								
<%-- 								<td class="text-center" id="qty">${ondVO.onoQty}</td> --%>
<%-- 								<td class="text-center" id="price">${ondVO.onoPrice}</td> --%>
	
						</tr>
	</c:if>
	</c:forEach>
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>
<%-- 			</c:forEach> --%>
		</table>
			
		
		
		
		
		
		
		
				      	<!-- 要導入的訂單明細，未完成 -->
<%-- 	        <%@ include file="/front-end/ono/listMemONODetail_membercenter.jsp"%> --%>
								<!-- 要導入的訂單明細，未完成 -->
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