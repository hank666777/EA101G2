<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.product.model.ProductVO" %>
<%@ page import="com.tabless.model.*"%>
<%@ page import="com.employee.model.*"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>結帳購物車</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/liveShop/css/ShoppingCart.css">
</head>
<body>
<div id="banner">
<%	
	@SuppressWarnings("unchecked")
	Vector<ProductVO> liveBuyList = (Vector<ProductVO>) session.getAttribute("liveShoppingcart");


	TableVO tbVO = (TableVO) request.getAttribute("tbVO");
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<% if(liveBuyList != null && (liveBuyList.size() > 0)) {%>

<jsp:useBean id="tableSvc" scope="page" class="com.tabless.model.TableService" />

<div id="font">
<font size="+3">桌號${tbVO.tableNo}目前已購商品如下</font><p>
</div>
	
	<table>
		<tr>
			<th>選購商品名稱</th>
			<th>商品單價</th>
			<th>選購數量</th>
		</tr>
		<%
		for(int index = 0 ; index < liveBuyList.size(); index++){
			ProductVO order = liveBuyList.get(index);
		%>
	
		<tr>
			<td width="200" height="30"><div align="center"><b><%= order.getpname()%></b></div></td>
			<td width="200" height="30"><div align="center"><b><%= order.getpP()%></b></div></td>			
			
			<td width="200" height="30">
			<div align="center">
			<Form name="deleteForm" action="<%=request.getContextPath()%>/product/ShoppingServlet.do" method="POST">
			    <input type="hidden" name="action" value="QTY"> 
	    	 	<input type="hidden" name="qty" value="<%=index%>"> 
				<input type="number" name="pDoffer" value="<%= order.getpDoffer()%>" min="1" size="1" />
				<input type="submit" value="修改" class="button2">
			</Form>
			</div>
			</td>	
					
			<td width="100"><div align="center">
			<Form name="deleteForm" action="<%=request.getContextPath()%>/product/ShoppingServlet.do" method="POST">
				<input type="hidden" name="action" value="DELETE">
				<input type="hidden" name="del" value="<%= index%>">
				<input type="submit" value="刪除" class="button2">
			</Form>
			</div>
			</td>			
		</tr>
		<%}%>
  	</table>
	<p>
		<form name="checkoutForm" action="<%=request.getContextPath()%>/product/ShoppingServlet.do" method="POST">
			<input type="hidden" name="action" value="CHECKOUT">
			<input type="submit" value="付款結帳" class="button3">
		</form>	
<%}%>
</div>

<Script>
	


</script>
</body>
</html>