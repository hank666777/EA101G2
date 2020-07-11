<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ptype.model.*"%>

<%
	PTypeService ptypeSvc = new PTypeService();
	pageContext.setAttribute("ptypeSvc", ptypeSvc);
%>

<html>
<head>

<title>Product:Home</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style>

/* 	body{ */
<%-- 		background-image:url('<%= request.getContextPath() %>/front-end/product/image/productShoppingBackground.jpg'); --%>
/* 		background-size: cover; */
/* 		background-repeat: no-repeat; */
/* 		background-attachment: fixed; */
/* 		background-position: center; */
/* 	} */

	#productselecttitle{
		margin:25px auto;
		text-align:center;
	}

	ul.wrap{
		display: table;
 		margin: 0px auto;
		border: orange 2px solid;
	}
	
	li{
		margin:30px;
	}
	
	.card{
		opacity:0.9;
		width:500px;
		height:700px;	
		margin:20px auto;
	}
	
	#productSearchButton{
		text-align:right;
		margin-right:30px;
	}
	
	.shorthyper{
		display: inline;
		margin-left: 5px;
	}
	
	
	
</style>


</head>
<body>
	
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>
	
<!-- 		<ul class="wrap"> -->
<!-- 			<li> -->
<%-- 				<form method="post" action="<%= request.getContextPath() %>/product.do"> --%>
<!-- 					<b>輸入商品編號：</b>  -->
<!-- 					 <input type="text" name="pno"> -->
<!-- 					 <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 					 <input type="submit" value="送出"> -->
<!-- 				</form> -->
<!-- 			</li> -->
	
	
<!-- 			<li>   -->
<%-- 				<form method="post" action="<%= request.getContextPath() %>/product.do"> --%>
<!-- 					<b>商品類型查詢：</b> -->
<!-- 					<select size="1" style ="width:100px" name="pTno"> -->
<%-- 						<c:forEach var="ptypeVO" items="${ptypeSvc.all}"> --%>
<%-- 							<option value="${ptypeVO.pTno}"> ${ptypeVO.pTName} --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 					<input type = "hidden" name="action" value="get_Category"> -->
<!-- 					<input type = "submit" value ="送出"> -->
<!-- 				</form> -->
<!-- 			</li> -->
<!-- 		</ul> -->
		
	
		<div id="selectproductonline" class="card">
		
		<h1 id="productselecttitle">商品資料查詢</h1>
		
		
			<ul>
				<li class="shorthyper"><a href='addProduct.jsp'>新增商品</a></li>
				<li class="shorthyper"><a href='listAllProduct.jsp'>商品列表</a></li>
			</ul>

		<ul class="wrap">
			
				<form method="post" action="<%= request.getContextPath() %>/product.do">
					
					<li>		
						<b>輸入商品編號：</b> 
						<input type="text" name="pno">
					</li>		
			
					<li>		
						<b>輸入商品名稱：</b> 
						<input type="text" name="pname">	
					</li>
			
					<li>		
						<b>選擇商品類型：</b>
						<select size="1" style ="width:100px" name="pTno">
							<option value="">
							<c:forEach var="ptypeVO" items="${ptypeSvc.all}">
								<option value="${ptypeVO.pTno}"> ${ptypeVO.pTName}</option>
							</c:forEach>
						</select>
					</li>
			
					<li>
						<b>輸入最高價格：</b>
						<input type="text" name="pP">
					</li>
			
					<li>
						<b>輸入最高供給：</b>
						<input type="text" name="pDoffer">
					</li>
			
					<li>
						<b>選擇庫存狀態：</b>
						<input type ="checkbox" name="invStatus" value ="1">正常
						<input type ="checkbox" name="invStatus" value ="0">缺貨	
					</li>
			
			
					<li>
						<b>選擇商品狀態：</b>
						<input type ="checkbox" name="pStatus" value ="1">上架
						<input type ="checkbox" name="pStatus" value ="0">下架
					</li>
			
					<div id="productSearchButton">		
					<input type = "hidden" name="action" value="listProduct_ByCompositeQuery">
					<input type = "submit" value ="送出">
					</div>	
				</form>	
			
		</ul>
		</div>
		
</body>
</html>