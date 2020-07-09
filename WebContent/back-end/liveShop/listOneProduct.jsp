<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>

<% 
	ProductVO pdVO = (ProductVO)request.getAttribute("pdVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  img{
  	height:100px;
  	width:100px;
  }
</style>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>商品資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveShop/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>價格</th>
		<th>商品照片</th>
		<th>商品敘述</th>
		<th>庫存量</th>
		<th>商品庫存狀態</th>
		<th>商品狀態</th>
		<th>商品類別編號</th>
	</tr>
	<tr>
			<td>${pdVO.pno}</td>
			<td>${pdVO.pname}</td>
			<td>${pdVO.pP}</td>
			<td><img src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${pdVO.pno}" class="picture"></td>
			<td>${pdVO.pDes}</td>
			<td>${pdVO.pDoffer}</td> 
			<td>${pdVO.INVStatus}</td>
			<td>${pdVO.pStatus}</td>
			<td>${pdVO.pTno}</td>
	</tr>
</table>

</body>
</html>