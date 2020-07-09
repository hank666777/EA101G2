<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<% 
	ProductService pdSvc = new ProductService();
	List<ProductVO> list = pdSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>所有商品資料</title>
</head>
<style>
  body{
  	margin:0;
  	padding:0;
  }
  table#table-1 {
    width: 800px;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    margin:auto;
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
  /*table {
	width: 800px;
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
  }*/
  .picture{
  	width:100px;
  	height:100px;
  }
  #contain{
  width:1040px;
  height:800px;
  	margin:auto;
  }
  table.TB_COLLAPSE {
  border-collapse:collapse;
}
  table.TB_COLLAPSE thead th {
  padding:15px 7px ;
  color:#fff;
  background-color:orange;
}
table.TB_COLLAPSE td {
  padding:5px 0px;
  color:#555;
  text-align:center;
  background-color:#fff;
  border-bottom:1px solid #915999;
}
table.TB_COLLAPSE td {
  padding:5px 0px;
  text-align:center;
}
</style>
<body>

<div id="contain">
<table id="table-1">
	<tr><td>
		 <h3>所有商品資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveShop/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="TB_COLLAPSE">
<thead>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
</thead>
	<%@ include file="/back-end/liveShop/page1.file" %> 
	<c:forEach var="pdVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr>
			<td>${pdVO.pno}</td>
			<td>${pdVO.pname}</td>
			<td>$${pdVO.pP}</td>
			<td><img src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${pdVO.pno}" class="picture"></td>
			<td>${pdVO.pDes}</td>
			<td>${pdVO.pDoffer}</td> 
			<td>${pdVO.INVStatus}</td>
			<td>${pdVO.pStatus}</td>
			<td>${pdVO.pTno}</td>
			<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/SungProductServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="pno"  value="${pdVO.pno}">
			     <input type="hidden" name="action"	value="get_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/SungProductServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="pno"  value="${pdVO.pno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
    </c:forEach>
</table>
<%@ include file="/back-end/liveShop/page2.file" %>
</div>


</body>
</html>