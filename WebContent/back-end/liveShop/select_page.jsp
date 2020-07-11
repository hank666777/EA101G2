<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</head>
<body>

<%@include file="/back-end/back-end-header.jsp" %>

<div class="container">
<table id="table-1">
   <tr><td><h3>商品管理</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for product : Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href="<%=request.getContextPath()%>/back-end/liveShop/listAllProduct.jsp">List</a> all product  <br><br></li>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/SungProductServlet.do" >
        <b>輸入商品編號 (如P0001):</b>
        <input type="text" name="pno">
        <input type="hidden" name="action" value="get_For_Product">
        <input type="submit" value="送出">
    </FORM>
  </li>
	
  <jsp:useBean id="pdSvc" scope="page" class="com.product.model.ProductService" />

  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/SungProductServlet.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="pno">
         <c:forEach var="pdVO" items="${pdSvc.all}" > 
          <option value="${pdVO.pno}">${pdVO.pname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="get_For_Product">
       <input type="submit" value="送出">
    </FORM>
  </li>

</ul>

<h3>商品管理8787</h3>

<ul>
  <li><a href="<%=request.getContextPath()%>/back-end/liveShop/add_Product.jsp">Add</a> a new product</li>
</ul>
</div>
<%@include file="/back-end/back-end-footer.jsp" %>

</body>
</html>