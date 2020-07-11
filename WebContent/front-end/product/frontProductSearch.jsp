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

<style>

	h1{
		margin-top: 50px;
		text-align:center;
	}

	ul.wrap{
		display: table;
 		margin: 0 auto;
		border: orange 2px solid;
	}
	
	li{
		margin:30px;
	}
	
</style>


</head>
<body>

		<h1>商品資料查詢</h1>
	
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
		<ul class="wrap">
			<li>
				<form method="post" action="<%= request.getContextPath() %>/product.do">
					<b>輸入商品編號：</b> 
					 <input type="text" name="pno">
					 <input type="hidden" name="action" value="getOne_For_Display">
					 <input type="submit" value="送出">
				</form>
			</li>
	
	  <jsp:useBean id="PTypeSvc" scope="page" class="com.ptype.model.PTypeService" />
	
			<li>  
				<form method="post" action="<%= request.getContextPath() %>/product.do">
					<b>商品類型查詢：</b>
					<select size="1" style ="width:100px" name="pTno">
						<c:forEach var="ptypeVO" items="${PTypeSvc.all}">
							<option value="${ptypeVO.pTno}"> ${ptypeVO.pTName}
						</c:forEach>
					</select>
					<input type = "hidden" name="action" value="get_Category_front">
					<input type = "submit" value ="送出">
				</form>
			</li>
		</ul>

</body>
</html>