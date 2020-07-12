<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.ono.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.onodetail.model.*"%>
<%@ page import="com.product.model.*"%>
<% 
	//取得所有訂單
	List<ONOVO> onolist = new ONOService().getAll();
	//取得登入者的訂單
	MemVO memvo1 = (MemVO)session.getAttribute("memVO");
	List<ONOVO> myONOlist = onolist.stream().filter(ono -> ono.getmemno().contains(memvo1.getMemno()))
																					.collect(Collectors.toList());
	pageContext.setAttribute("myONOlist", myONOlist);	
	
	ONODetailService ondSvc = new ONODetailService();
	List<ONODetailVO> list = ondSvc.getAll();
	pageContext.setAttribute("list",list);
	
	ProductService pdSvc = new ProductService();
	List<ProductVO> pdlist = pdSvc.getAll();
	pageContext.setAttribute("pdlist",pdlist);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

	<table class="table text-nowrap table-hover info text-center
							shadow-lg p-3 mb-5 bg-white rounded">
							
		<tr class="text-center">
			<th>商品編號</th>
			<th>購買數量</th>
			<th>單品價格</th>
	    </tr>
	    
	    <c:forEach var="ondVO" items="${list}">
	    	<c:forEach var="onVO" items="${myONOlist}">
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
		</c:forEach>
		
	</table>

	
</body>
</html>