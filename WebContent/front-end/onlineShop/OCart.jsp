<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO" %>
<%@ page import="com.mem.model.*"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>MISS M結帳購物車</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/onlineShop/css/ShoppingCart.css">
</head>
<%@ include file="/front-end/front-end-head.jsp"%>

<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
<%	
	@SuppressWarnings("unchecked")
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
%>

<% if(buylist != null && (buylist.size() > 0)) {%>

<div class="container text-center mb-2">
	<div class="row">
		<div class="col text-center">
			<p class="h2">目前已購商品如下</p>
		</div>
	</div>

	<div class="row">
	<table class="table text-nowrap text-center table-hover shadow-lg p-3 mb-3 bg-white rounded" id="Carts">
		<tr>
			<th><p class="h3">選購商品圖片</p></th>
			<th><p class="h3">選購商品名稱</p></th>
			<th><p class="h3">商品單價</p></th>
			<th><p class="h3">選購數量</p></th>
		</tr>
		<%
		for(int index = 0 ; index < buylist.size(); index++){
			ProductVO order = buylist.get(index);
		%>
	
		<tr style="padding:1 auto;">
			<td class="">
				<img class="rounded mx-auto d-block" style="width:83px; height:40px;" 
						 src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=<%= order.getpno()%>">
			</td>
			<td style="width:350px; height:30px;"><div align="center"><p class="h4"><%= order.getpname()%></p></div></td>
			<td style="width:350px; height:30px;"><div align="center"><p class="h4"><%= order.getpP()%></p></div></td>			
			
			<td style="width:350px; height:30px;">
				<div align="center">
				<Form name="deleteForm" action="<%=request.getContextPath()%>/product/OnlineShopServlet.do" method="POST">
				    <input type="hidden" name="action" value="QTY"> 
		    	 	<input type="hidden" name="qty" value="<%=index%>"> 
					<input type="number" name="pDoffer" value="<%= order.getpDoffer()%>" min="1" size="1" />
					<input class="btn btn-sm btn-outline-warning" type="submit" value="修改" class="button2">
				</Form>
				</div>
			</td>	
					
			<td width="100">
				<div align="center">
				<Form name="deleteForm" action="<%=request.getContextPath()%>/product/OnlineShopServlet.do" method="POST">
						<input type="hidden" name="action" value="DELETE">
						<input type="hidden" name="del" value="<%= index%>">
					<input class="btn btn-sm btn-outline-danger" type="submit" value="刪除" class="button2">
				</Form>
				</div>
			</td>
		</tr>
		<%}%>
  	</table>
		</div>
		
		<div class="row">
			<div class="col text-center">
			<form name="checkoutForm" action="<%=request.getContextPath()%>/product/OnlineShopServlet.do" method="POST">
				<input type="hidden" name="action" value="CHECKOUT">
				<input class="btn btn-outline-success" type="submit" value="前往購買明細" class="button3">
			</form>
			</div>	
		</div>
<%}%>
</div>
<%@ include file="/front-end/front-end-footer.jsp"%>

</body>
</html>