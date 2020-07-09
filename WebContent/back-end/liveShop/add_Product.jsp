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
<title>商品資料新增</title>
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
	width: 500px;
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
  #container{
  	width:800px;
  	height:500px;
  	margin:auto;
  	padding-left:350px;
  }
  table.TB_COLLAPSE {
  width: 500px;
  border-collapse:collapse;
  }
table.TB_COLLAPSE thead th {
	width: 450px;
  padding:5px 0px;
  color:#fff;
  background-color:orange;
}
table.TB_COLLAPSE th {
  padding:5px 0px;
  color:#555;
  text-align:center;
  background-color:orange;
  border-bottom:1px solid #915957;
}
table.TB_COLLAPSE th {
  padding:5px 0px;
  text-align:center;
}
</style>
</head>
<body>
<div id="container">
<table id="table-1">
	<tr><td>
		 <h3>商品資料新增</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/liveShop/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/SungProductServlet.do" name="form1" enctype="multipart/form-data">
<table class="TB_COLLAPSE">
	<thead>
	<tr>
		<th>資料新增:</th>
		<td>請輸入下列資訊☛☛☛☛☛</td>
	</tr>
	</thead>
	<tr>
		<th>商品名稱:</th>
		<td><input type="TEXT" name="pname" size="45" 
			 value="<%= (pdVO==null)? "大魚" : pdVO.getpname()%>" /></td>
	</tr>
	<tr>
		<th>商品價格:</th>
		<td><input type="TEXT" name="pP" size="45"
			 value="<%= (pdVO==null)? "$$$" : pdVO.getpP()%>" /></td>
	</tr>
	<tr>
		<th>商品圖片:</th>
		<td><input type="file" name="pPic" accept="image/jpeg, image/png"  id="file_input"
			 value="<%= (pdVO==null)? "null" : pdVO.getpPic()%>" /><div id="result"></div></td>
	</tr>
	<tr>
		<th>商品敘述:</th>
		<td><textarea type="TEXT" name="pDes" size="30" row="3" cols="30"
			 value="<%= (pdVO==null)? "來點東西吧" : pdVO.getpDes()%>" /></textarea></td>
	</tr>
	<tr>
		<th>商品庫存:</th>
		<td><input type="TEXT" name="pDoffer" size="45"
			 value="<%= (pdVO==null)? "???" : pdVO.getpDoffer()%>" /></td>
	</tr>
	<tr>
		<th>商品庫存狀態:</th>
		<td><input type="TEXT" name="invStatus" size="45"
			 value="<%= (pdVO==null)? "0" : pdVO.getINVStatus()%>" /></td>
	</tr>
	<tr>
		<th>商品狀態:</th>
		<td><input type="TEXT" name="pStatus" size="45"
			 value="<%= (pdVO==null)? "0" : pdVO.getpStatus()%>" /></td>
	</tr>
	
	<jsp:useBean id="pSvc" scope="page" class="com.ptype.model.PTypeService" />
	<tr>
		<th>商品類型:<font color=red><b>*</b></font></th>
		<td><select size="1" name="pTno">
			<c:forEach var="pTypeVO" items="${pSvc.all}">
				<option value="${pTypeVO.pTno}" ${(pdVO.pTno==pTypeVO.pTno)? 'selected':'' } >${pTypeVO.pTName}
			</c:forEach>
		</select></td>
	</tr>
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</div>
</body>
<script>

	var result = document.getElementById("result");
	var input = document.getElementById("file_input");

	if (typeof FileReader === 'undefined') {
		result.innerHTML = "Sorry, 瀏覽器不支持 FileReader";
		input.setAttribute('disabled', 'disabled');
	} else {
		input.addEventListener('change', readFile, false);
	}

	function readFile() {
		var file = this.files[0];
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			result.innerHTML = '<img src="'+this.result+'" alt="" height="100" />'
		}
	}
</script>
</html>