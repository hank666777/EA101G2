<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.product.model.*" %>
<%@ page import="com.ptype.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<%
	PTypeService ptypeSvc = new PTypeService();
	pageContext.setAttribute("ptypeSvc", ptypeSvc);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
<title>商品資料修改</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>



<style>

	#preview{
		width:200px;
	}
	img{
		max-width:300px;
		height:225px;
	}

	body{
		background-image:url('<%= request.getContextPath() %>/images/back-end/productImg/backProductBackground.jpg');
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}

	.card{
		opacity:0.9;
		width:1200px;
		margin:50px auto;
	}

	#addproducttitle{
		text-align:center;
	}

	#pdetail {
		border: orange 2px solid;
		border-collapse: collapse; 
		margin:auto;
		padding:20px;
	}

	.ptds{
		border: orange 2px solid;
		background-color: yello;
		padding:20px;
	}

	#productdes{
		width:300; 
		height:250;
	}

	#delive{
		margin-top:20px;
		margin-left: 85%;
	}
	
	#backbutton{
		margin:20px;
	}

</style>

</head>

<body>
<%@ include file="/back-end/back-end-head.jsp" %>

<%@ include file="/back-end/back-end-header.jsp" %>

<div class="card">
	
				<h4 id ="backbutton"><a href="<%= request.getContextPath() %>/back-end/product/listAllProduct.jsp">回前頁</a></h4>

	<h1 id="addproducttitle">商品資料修改</h1>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items ="${errorMsgs} ">
			<li style="color:red">${message}</li>
			</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="PTypeSvc" scope="page" class="com.ptype.model.PTypeService" />

<form method="post" action="<%=request.getContextPath()%>/product.do"  enctype="multipart/form-data">
	<table id ="pdetail">
		<tr>
			<td class="ptds">商品編號:</td>
			<td class="ptds" colspan ="3"><%=productVO.getpno() %>
		</tr>
		
		<tr>
			<td class="ptds">商品名稱:</td>
			<td class="ptds">
			<input type="text" name="pname" size="41" value=<%=productVO.getpname() %> placeholder ="請輸入商品名稱">
			</td>
			
			<td class="ptds">商品每日供給量:</td>
			<td class="ptds">
			<input type="text" name="pDoffer" size="41" value=<%=productVO.getpDoffer() %> placeholder = "請輸入商品的每日供給量">
			</td>
			
		</tr>
		
		<tr>
			<td class="ptds">商品類型:</td>
			<td class="ptds">
				<select size="1" style ="width:100px" name="pTno">
					<c:forEach var="ptypeVO" items="${ptypeSvc.all}">
						<option value="${ptypeVO.pTno}" ${(productVO.pTno == ptypeVO.pTno)?'selected':''} >${ptypeVO.pTName} 
					</c:forEach>
				</select>
			</td>
			
			<td class="ptds">庫存狀態:</td>
			<td class="ptds">
			<input type="range" name="invStatus" min="0" max="1" step="1" value="<%=productVO.getINVStatus() %>">
			</td>
			
		</tr>
		
		<tr>
			<td class="ptds">商品單價:</td>
			<td class="ptds">
				<input type="text" name="pP" size="41" value=<%=productVO.getpP() %> placeholder ="請輸入商品單價">	
			</td>
			
			<td class="ptds">上下架狀態:</td>
			<td class="ptds">
			<input type="range" name="pStatus" min="0" max="1" step="1" value=<%=productVO.getpStatus() %>>
			</td>
			
		</tr>

		<tr>
			<td class="ptds">商品描述:</td>
			<td class="ptds">
			<textarea id="productdes" name="pDes" placeholder ="請為商品添加描述" > <%=productVO.getpDes() %></textarea>
			</td>
			
			<td class="ptds">商品圖片:</td>
			<td class="ptds" id="container">
				<input id="myfile" type="file" name="pPic" size="45" accept="image/jpeg, image/png" value="<%=productVO.getpPic()%>" />
				<div id ="preview">			
				</div>		
			</td>
		</tr>

</table>

		<input type ="hidden" name="action" value="update" >
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
		<input type="hidden" name="pno" value="<%=productVO.getpno() %>">
		<input id="delive" type ="submit"  value="送出修改">

</form>
</div>
<script>
		function init(){
			var myfile = document.getElementById('myfile');
			var preview = document.getElementById('preview');
			var img = document.createElement('img');
			preview.append(img);
			myfile.addEventListener('change', function(e){
				var files = myfile.files;
				if(files !== null && files.length >0) {
					for(var i=0; i<files.length; i++) {
						 var file = files[i];
						 if(file.type.indexOf('image') > -1){
							 var reader = new FileReader();
							 reader.addEventListener('load', function(e){
								 var result = e.target.result;
								 img.src = result;
							 });
							 reader.readAsDataURL(file);
						 }
					}
			}
		});			
	}
		
		window.onload = init;
	</script>

<%@ include file="/back-end/back-end-footer.jsp" %>

</body>
</html>