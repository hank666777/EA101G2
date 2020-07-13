<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.*" %>
<%@ page import="com.ptype.model.*"%>

<%
	PTypeService ptypeSvc = new PTypeService();
	pageContext.setAttribute("ptypeSvc", ptypeSvc);
%>

<%	
	
	//將pTno的物件強轉型成String才能使用下面的方法
	//String pTno=(String)request.getAttribute("pTno");
	
	Object PT = session.getAttribute("pTno");
	ProductService productSvc = new ProductService();
	List<ProductVO>list = productSvc.getProductByCategory(PT.toString());
	pageContext.setAttribute("list", list);
%>    

<html>
<head>
<title>所有商品資料</title>

<style>

	#addproducttitle{
		text-align:center;
		margin:20px;
	}

	#listall {
		border: orange 2px solid;
		border-collapse: collapse; 
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
	
	#backbutton{
		margin:20px;
	}
	
	#selectednum{
		margin-bottom:10px;
	}
	
</style>


</head>

<body>
<%@ include file="/back-end/back-end-head.jsp" %>

<%@ include file="/back-end/back-end-header.jsp" %>

	<table>
		<tr>
			<td id ="backbutton">
				<h4><a href="<%= request.getContextPath() %>/back-end/product/addProduct.jsp">回前頁</a></h4>
			</td>
		</tr>
	</table>

	<h1 id="addproducttitle">商品資料查詢</h1>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs} ">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
			<th class="ths" colspan ="2"></th>
			
		</tr>
	<%@ include file="page1.file" %> 
	<jsp:useBean id="PTypeSvc" scope="page" class="com.ptype.model.PTypeService" />
			
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex %>" end="<%=pageIndex + rowsPerPage-1 %>">
		
		
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
			<td class="tds">
				<form method="post" action="<%=request.getContextPath()%>/product.do" >
					<input type="submit" value="修改"> 
					<input type="hidden" name="pno" value="${productVO.pno}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
			<td class="tds">
				<form method="post" action="<%=request.getContextPath()%>/product.do" >
					<input type="submit" value="刪除"> 
					<input type="hidden" name="pno" value="${productVO.pno}">
					<input type="hidden" name="action" value="delete">	
				</form>
			</td>	
		</tr>

		</c:forEach>
</table>
<%@ include file="page2.file" %>

<%@ include file="/back-end/back-end-footer.jsp" %>

</body>
</html>