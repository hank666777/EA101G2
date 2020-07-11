<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/onlineShop/css/ShoppingCart.css">
 
<style>
	#footer{
		padding:0 auto;
	}
</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>

<div class="container" id="banner">
	<font size="+3">結帳明細:</font>

	<table class="table text-nowrap text-center table-hover" id="table-1" style="margin: auto;">
		<tr>
		<th width="200">商品名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"><h3>總價</h3></th>
	</tr>
	</table>
	
	<% 	
		@SuppressWarnings("unchecked")
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String amount = (String) session.getAttribute("amount");
	%>
	
	<% 
		for(int i = 0 ; i < buylist.size(); i++){
			ProductVO order = buylist.get(i);
			String pname = order.getpname();
			Integer pP = order.getpP();
			Integer pDoffer = order.getpDoffer();		
	%>
	<form action="<%=request.getContextPath()%>/product/OnlineShopServlet.do" method="post">
	<table class="table text-nowrap text-center table-hover" style="margin: auto;">
	<tr>
		<td width="200"><%=pname%>     </td>
		<td width="100"><%=pP%>   </td>
		<td width="100"><%=pDoffer%></td>
		<%Integer count = pP*pDoffer; %>
		<td width="120"><%=count%></td>
	</tr>
	<%
		}
	%>
	
	<tr>
		
		<td colspan="6" style="text-align:right;"> 
		<input type="hidden" name = "liveOrderTotal" value="<%=amount%>">
		   <font size="+2">總金額： <h4>$<fmt:formatNumber type="number" value="<%=amount %>"></fmt:formatNumber></h4> </font>
		   
	    </td>
	</tr>
	<tr>
		<td colspan="1" style="text-align:center;">
			<p>
			<a href="<%=request.getContextPath()%>/front-end/onlineShop/OShop.jsp" style="text-align:center;">
				<font> 是 否 繼 續 購 物</font>
			</a>  
		</td>
		<td colspan="5" style="text-align:right;"> 
			<input type="hidden" name="action" value="payPage">
			<input class="btn btn-outline-success" type="submit" value="付款結帳" class="button3" >
		</td>
	</tr>

	</table>
 	</form>
 	</div>
 	<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>