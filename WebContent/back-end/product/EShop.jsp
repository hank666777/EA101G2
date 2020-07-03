<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductService goodsSvc = new ProductService();
	List<ProductVO> list = goodsSvc.getProductByStatus(1);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="pdSvc "  scope="page" class="com.product.model.ProductService" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物頁面</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/product/css/shop.css">
</head>
<body>
	
	<table>
		<tr bgcolor='red'>
			<th>商品列表</th>	
		</tr>
	</table>
	
	
	<div id = content>
		<c:forEach var="prdVO" items="${list}" >
			<div id= form>
				<Form name="shoppingForm" action="<%=request.getContextPath()%>/product/Shopping.do" method="get">
			
					<div class="block"><img src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}" class='picture'></div>
					<div class="block1">商品名稱:${prdVO.pname}</div>
					<div class="block2">商品價格:$${prdVO.pP} </div>
					<div class="block3">商品明細:${prdVO.pDes}</div>
					<div class="block4">數量:<input type="text" name="pDoffer" size="3" value=1><input type="hidden" name="action" value="ADD">
					<input type="submit" name="Submit" value="加入購物車"></div>			
					<input type="hidden" name="pPic" value="${prdVO.pPic}">
					<input type="hidden" name="pname" value="${prdVO.pname}">
					<input type="hidden" name="pP" value="${prdVO.pP}">
					<input type="hidden" name="pDes" value="${prdVO.pDes}">
				
				</Form>
			</div>
		</c:forEach>
	</div>
	<p>
		<jsp:include page="/back-end/product/Cart.jsp" flush="true" />
		<script>
		</script>
</body>
</html>