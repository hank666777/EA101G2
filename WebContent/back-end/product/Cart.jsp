<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>結帳購物車</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/product/css/ShoppingCart.css">
</head>
<body>

<%Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");%>
<% if(buylist != null && (buylist.size() > 0)) {%>

<font size="+3">目前已購商品如下</font><p>

	<table border="1" width="740" >
		<tr bgcolor="blue">
			<th>結帳清單</th>
		</tr>
		<%
		for(int index = 0 ; index < buylist.size(); index++){
			ProductVO order = buylist.get(index);
		%>
		<tr>
			<td width="200" height="30"><div align="center"><b><%= order.getpname()%></b></div></td>
			<td width="200" height="30"><div align="center"><b><%= order.getpP()%></b></div></td>
			<td width="200" height="30"><div align="center"><b><%= order.getpDes()%></b></div></td>
			<td width="200" height="30"><div align="center"><b><%= order.getpDoffer()%></b></div></td>
			
			<td width="100"><div align="center">
			<form name="deleteForm" action="<%=request.getContextPath()%>/product/Shopping.do" method="POST">
				<input type="hidden" name="action" value="DELETE">
				<input type="hidden" name="del" value="<%= index%>">
				<input type="submit" value="刪除" class="button"></div>
			</td></form>						
		</tr>
		<%}%>
  	</table>
	<p>
		<form name="checkoutForm" action="<%=request.getContextPath()%>/product/Shopping.do" method="POST">
			<input type="hidden" name="action" value="CHECKOUT">
			<input type="submit" value="付款結帳" class="button">
		</form>	
<%}%>
</body>
</html>