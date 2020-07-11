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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style>

	.innerdetail{
		margin:5px 20px;
		float:left;
	}
	
	.tdcart{
		text-align:right;
	}
	
	#showproducttitle{
		text-align:center;
	}
	
</style>

</head>
<body>
		
	<jsp:useBean id="PTypeSvc" scope="page" class="com.ptype.model.PTypeService" />
 	
 	<h4 id="showproducttitle">${productVO.pname}</h4>
 	
 	<div class="col-xl-6 card innerdetail">
 		<img src="<%=request.getContextPath()%>/ProductReader?pno=${productVO.pno}" width=360px height=270px>
 	</div>
 	
 	
	 	<div class="col-xl-6 card innerdetail">
	
	<table height=270px>
	 		<tr>
	 			<th nowrap="nowrap">商品描述：</th>
	 		</tr>
	 		<tr>
	 			<td>${productVO.pDes}</td>
	 		</tr>
	 		
	 		<tr>
	 			<td nowrap="nowrap"><b>商品價格：</b>NT${productVO.pP}元</td>
	 		</tr>
	 	
	 		<tr>
	 			<td nowrap="nowrap"><b>每日限量：</b>${productVO.pDoffer}個</td>
	 		</tr>
	 	
	 		<tr>
	 			<td class="tdcart"><input type="button" value="加入購物車"> </td>
	 		</tr>
	 	
	</table>
		
	 	</div>
	 	
	
	
</body>
</html>