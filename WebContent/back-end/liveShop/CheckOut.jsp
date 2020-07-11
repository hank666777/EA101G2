<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tabless.model.*"%>
<%@ page import="com.employee.model.*"%>
<% 
	TableVO tbVO = (TableVO)request.getAttribute("tbVO");
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/liveShop/css/ShoppingCart.css">
</head>
<body>
	<font size="+3">桌號${tbVO.tableNo}結帳明細:</font>

	<table id="table-1" style="margin: auto;">
		<tr>
		<th width="200">商品名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"><h3>總價</h3></th>
	</tr>
	</table>
	

	
	<% 	
		@SuppressWarnings("unchecked")
		Vector<ProductVO> liveBuyList = (Vector<ProductVO>) session.getAttribute("liveShoppingcart");
		String amount = (String) request.getAttribute("amount");
	%>
	
	<% 
		for(int i = 0 ; i < liveBuyList.size(); i++){
			ProductVO order = liveBuyList.get(i);
			String pname = order.getpname();
			Integer pP = order.getpP();
			Integer pDoffer = order.getpDoffer();		
	%>
	<form action="<%=request.getContextPath()%>/liveOrder/LiveOrderServlet.do" method="post">
	<table style="margin: auto;">
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
		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font>
		   
	    </td>
	</tr>
	<tr>
		<td colspan="1" style="text-align:center;">
			
		   	<p><a href="<%=request.getContextPath()%>/back-end/liveShop/EShop.jsp" style="text-align:center;"><font> 是 否 繼 續 購 物</font></a>  
		</td>
		<td colspan="5" style="text-align:right;"> 
			
			<input type="hidden" name="action" value="Detail">
      		<input type="submit" value="付款結帳" class="button3" >
		</td>
	</tr>

	</table>
 	</form>
	
</body>
</html>