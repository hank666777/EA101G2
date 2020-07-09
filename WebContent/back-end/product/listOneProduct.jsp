<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.ptype.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	PTypeVO ptypeVO = (PTypeVO) request.getAttribute("ptypeVO");
%>

<%
	PTypeService ptypeSvc = new PTypeService();
	pageContext.setAttribute("ptypeSvc", ptypeSvc);
%>

<html>
<head>
<title>商品資料 - listOneEmp.jsp</title>

</head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style>

	body{
		background-image:url('<%= request.getContextPath() %>/front-end/product/image/productShoppingBackground.jpg');
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}

	#addproducttitle{
		text-align:center;
		margin:20px;
	}

	#addproducttitle{
		text-align:center;
		margin:20px;
	}

	.card{
		opacity:0.9;
		width:1200px;
		margin:20px auto;
	}
	
	.ths{
		text-align: center;
		padding: 5px;
		border: orange 2px solid;
	}

	.tds{
		text-align: center;
		padding: 5px;
		border: orange 2px solid;
	}
	
	.tddes{
		text-indent: 32px;
		padding: 5px;
		border: orange 2px solid;
	}
	
	#productlistoutline{
		margin:20px auto;
	}
	
</style>

<body>

<div class="card">

				<h4 id ="backbutton"><a href="<%= request.getContextPath() %>/back-end/product/selectProductPage.jsp">回首頁</a></h4>

	<h1 id="addproducttitle">商品資料查詢</h1>
	
	<div id="productlistoutline">
	<table id="listall">
		<tr>
			<th class="ths" nowrap="nowrap">商品編號</th>
			<th class="ths" nowrap="nowrap">商品名稱</th>
			<th class="ths" nowrap="nowrap">商品類型</th>
			<th class="ths" nowrap="nowrap">商品圖片</th>
			<th class="ths" nowrap="nowrap">商品單價</th>
			<th class="ths" nowrap="nowrap">商品每日供給量</th>
			<th class="ths" nowrap="nowrap">商品庫存狀態</th>
			<th class="ths" nowrap="nowrap">商品狀態</th>
			<th class="ths" nowrap="nowrap">商品描述</th>	
		</tr>
		
	<jsp:useBean id="PTypeSvc" scope="page" class="com.ptype.model.PTypeService" />
 	
		<tr>
			<td class="tds">${productVO.pno}</td>
			<td class="tds">${productVO.pname}</td>
			<td class="tds">
				<c:forEach var="ptypeVO" items="${PTypeSvc.all}"> 
					<c:if test="${ptypeVO.pTno == productVO.pTno}">
							${ptypeVO.pTName}
					</c:if>
				</c:forEach>
			</td>
			<td class="tds"><img src="<%=request.getContextPath()%>/ProductReader?pno=${productVO.pno}" width=100px height=75px></td>
			<td class="tds">${productVO.pP}</td>
			<td class="tds">${productVO.pDoffer}</td>
			<td class="tds">${productVO.invStatus}</td>
			<td class="tds">${productVO.pStatus}</td>
			<td class="tddes">${productVO.pDes}</td>
		</tr>
		
	</table>
	</div>
</div>

</body>
</html>