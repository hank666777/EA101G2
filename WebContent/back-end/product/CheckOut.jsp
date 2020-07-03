<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/product/css/ShoppingCart.css">
</head>
<body>
	<font size="+3">結帳明細:</font>
	
	<table id="table-1" style="margin: auto;">
		<tr>
		<th width="200">商品名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"><h3>總價</h3></th>
	</tr>
	</table>
	<table style="margin: auto;">
	
	<% 	
		@SuppressWarnings("unchecked")
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String amount = (String) request.getAttribute("amount");
	%>
	
	<% 
		for(int i = 0 ; i < buylist.size(); i++){
			ProductVO order = buylist.get(i);
			String pname = order.getpname();
			Integer pP = order.getpP();
			Integer pDoffer = order.getpDoffer();		
	%>
	<tr>
		<td width="200"><%=pname%>     </td>
		<td width="100"><%=pP%>   </td>
		<td width="100"><%=pDoffer%></td>
		<td width="120"></td>
	</tr>
	<%
		}
	%>
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font>
	    </td>
	</tr>
	</table>
	<p><a href="<%=request.getContextPath()%>/back-end/product/EShop.jsp"><font size="+1"> 是 否 繼 續 購 物</font></a>
</body>
</html>