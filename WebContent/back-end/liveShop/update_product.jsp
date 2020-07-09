<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<% 
	ProductVO pdVO = (ProductVO)request.getAttribute("pdVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改</title>
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
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body>

  <table id="table-1">
	<tr><td>
		 <h3>商品資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveShop/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/SungProductServlet.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=pdVO.getpno()%></td>
	</tr>
	<tr>
		<td>商品名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="pname" size="45" value="<%=pdVO.getpname()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="pP" size="45" value="<%=pdVO.getpP()%>" /></td>
	</tr>
	<tr>
		<td>商品照片:<font color=red><b>*</b></font></td>
		<td><input type="file" name="pPic" accept="image/jpeg, image/png" value="<%=pdVO.getpPic()%>" /></td>
	</tr>
	<tr>
		<td>商品敘述:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="pDes" size="45" value="<%=pdVO.getpDes()%>" /></td>
	</tr>
	<tr>
		<td>商品庫存量:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="pDoffer" size="45" value="<%=pdVO.getpDoffer()%>" /></td>
	</tr>
	<tr>
		<td>商品庫存狀態:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="invStatus" size="45" value="<%=pdVO.getINVStatus()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="pStatus" size="45" value="<%=pdVO.getpStatus()%>" /></td>
	</tr>
	<jsp:useBean id="pSvc" scope="page" class="com.ptype.model.PTypeService" />
	<tr>
		<td>商品類型:<font color=red><b>*</b></font></td>
		<td><select size="1" name="pTno">
			<c:forEach var="pTypeVO" items="${pSvc.all}">
				<option value="${pTypeVO.pTno}" ${(pdVO.pTno==pTypeVO.pTno)? 'selected':'' } >${pTypeVO.pTName}
			</c:forEach>
		</select></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pno" value="<%=pdVO.getpno()%>">
<input type="submit" value="送出修改"></FORM>


</body>
</html>