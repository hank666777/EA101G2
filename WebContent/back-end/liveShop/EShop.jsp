<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.tabless.model.*"%>
<%@ page import="com.employee.model.*"%>


<%	
	TableVO tbVO = (TableVO) session.getAttribute("tbVO");

	ProductService goodsSvc = new ProductService();
	List<ProductVO> list = goodsSvc.getProductByStatus(1);
	pageContext.setAttribute("list", list);

	ProductService goodsSvc2 = new ProductService();
	List<ProductVO> list2 = goodsSvc.getProductByStatusAndType(1, "PT001");
	pageContext.setAttribute("list2", list2);

	ProductService goodsSvc3 = new ProductService();
	List<ProductVO> list3 = goodsSvc.getProductByStatusAndType(1, "PT002");
	pageContext.setAttribute("list3", list3);

	ProductService goodsSvc4 = new ProductService();
	List<ProductVO> list4 = goodsSvc.getProductByStatusAndType(1, "PT003");
	pageContext.setAttribute("list4", list4);

	ProductService goodsSvc5 = new ProductService();
	List<ProductVO> list5 = goodsSvc.getProductByStatusAndType(1, "PT004");
	pageContext.setAttribute("list5", list5);

	ProductService goodsSvc6 = new ProductService();
	List<ProductVO> list6 = goodsSvc.getProductByStatusAndType(1, "PT005");
	pageContext.setAttribute("list6", list6);

	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<jsp:useBean id="pdSvc" scope="page"
	class="com.product.model.ProductService" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>現場購物頁面</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/liveShop/css/shop.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
</head>
<body>

	<div id="content">
		<div id="table">
			<p>商品列表</p>

			<div id="sit">
				桌號${tbVO.tableNo}
			</div>
		</div>

		<div id="type">
			<form id="drink"
				action="<%=request.getContextPath()%>/product/ShoppingServlet.do">
				<input type="hidden" name="inCludeVO" value="drink"> <input
					type="hidden" name="action" value="changeValue"> <input
					type="hidden" name="pTno" value="PT001">
			</form>
			<a href=""
				onclick="document.getElementById('drink').submit();return false;"><font
				class="types">飲品</font></a>

			<form id="dessert"
				action="<%=request.getContextPath()%>/product/ShoppingServlet.do">
				<input type="hidden" name="inCludeVO" value="dessert"> <input
					type="hidden" name="action" value="changeValue"> <input
					type="hidden" name="pTno" value="PT002">
			</form>
			<a href=""
				onclick="document.getElementById('dessert').submit();return false;"><font
				class="types">甜點</font></a>

			<form id="lightfood"
				action="<%=request.getContextPath()%>/product/ShoppingServlet.do">
				<input type="hidden" name="inCludeVO" value="lightfood"> <input
					type="hidden" name="action" value="changeValue"> <input
					type="hidden" name="pTno" value="PT003">
			</form>
			<a href=""
				onclick="document.getElementById('lightfood').submit();return false;"><font
				class="types">輕食</font></a>

			<form id="soup"
				action="<%=request.getContextPath()%>/product/ShoppingServlet.do">
				<input type="hidden" name="inCludeVO" value="soup"> <input
					type="hidden" name="action" value="changeValue"> <input
					type="hidden" name="pTno" value="PT004">
			</form>
			<a href=""
				onclick="document.getElementById('soup').submit();return false;"><font
				class="types">湯品</font></a>

			<form id="jam"
				action="<%=request.getContextPath()%>/product/ShoppingServlet.do">
				<input type="hidden" name="inCludeVO" value="jam"> <input
					type="hidden" name="action" value="changeValue"> <input
					type="hidden" name="pTno" value="PT005">
			</form>
			<a href=""
				onclick="document.getElementById('jam').submit();return false;"><font
				class="types">果醬</font></a>

		</div>

		<c:choose>
			<c:when test="${inCludeVO=='drink'}">
				<c:forEach var="prdVO" items="${list2}">
					<div class=form>
						<Form name="shoppingForm"
							action="<%=request.getContextPath()%>/product/ShoppingServlet.do"
							method="post">
							<div class="block">
								<img
									src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}"
									class='picture'>
							</div>
							<div class="block1">商品名稱:${prdVO.pname}</div>
							<div class="block2">商品價格:$${prdVO.pP}</div>
							<div class="block3">商品明細:${prdVO.pDes}</div>
							<div class="block4">
								數量: <select size="1" name="pDoffer">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select> <input type="hidden" name="action" value="ADD"> 
								<input type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="inCludeVO" value="${inCludeVO}">
								<!--送出當前是哪種分類給Controller-->
								<input type="submit" name="Submit" value="加入購物車" class="button">
							</div>
							<input type="hidden" name="pPic" value="${prdVO.pPic}"> <input
								type="hidden" name="pname" value="${prdVO.pname}"> <input
								type="hidden" name="pP" value="${prdVO.pP}"> <input
								type="hidden" name="pDes" value="${prdVO.pDes}">
								 <input type="hidden" name="pTno" value="${prdVO.pTno}">
						</Form>
					</div>
				</c:forEach>
			</c:when>
			<c:when test="${inCludeVO=='dessert'}">
				<c:forEach var="prdVO" items="${list3}">
					<div class=form>
						<Form name="shoppingForm"
							action="<%=request.getContextPath()%>/product/ShoppingServlet.do"
							method="post">
							<div class="block">
								<img
									src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}"
									class='picture'>
							</div>
							<div class="block1">商品名稱:${prdVO.pname}</div>
							<div class="block2">商品價格:$${prdVO.pP}</div>
							<div class="block3">商品明細:${prdVO.pDes}</div>
							<div class="block4">
								數量: <select size="1" name="pDoffer">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select> <input type="hidden" name="action" value="ADD">
								<!-- 							<button id="add">加入購物車</button> -->
								<input type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="inCludeVO" value="${inCludeVO}">
								<!--送出當前是哪種分類給Controller-->
								<input type="submit" name="Submit" value="加入購物車" class="button">
							</div>
							<input type="hidden" name="pPic" value="${prdVO.pPic}" id="pPic">
							<input type="hidden" name="pname" value="${prdVO.pname}"
								id="pname"> <input type="hidden" name="pP"
								value="${prdVO.pP}" id="pP"> <input type="hidden"
								name="pDes" value="${prdVO.pDes}" id="pDes"> <input
								type="hidden" name="pno" value="${prdVO.pno}" id="pno">
								 <input type="hidden" name="pTno" value="${prdVO.pTno}">
						</Form>
					</div>
				</c:forEach>
			</c:when>
			<c:when test="${inCludeVO=='lightfood'}">
				<c:forEach var="prdVO" items="${list4}">
					<div class=form>
						<Form name="shoppingForm"
							action="<%=request.getContextPath()%>/product/ShoppingServlet.do"
							method="post">
							<div class="block">
								<img
									src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}"
									class='picture'>
							</div>
							<div class="block1">商品名稱:${prdVO.pname}</div>
							<div class="block2">商品價格:$${prdVO.pP}</div>
							<div class="block3">商品明細:${prdVO.pDes}</div>
							<div class="block4">
								數量: <select size="1" name="pDoffer">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select> <input type="hidden" name="action" value="ADD"> 
								<input type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="inCludeVO" value="${inCludeVO}">
								<!--送出當前是哪種分類給Controller--><input
									type="submit" name="Submit" value="加入購物車" class="button">
							</div>
							<input type="hidden" name="pPic" value="${prdVO.pPic}"> <input
								type="hidden" name="pname" value="${prdVO.pname}"> <input
								type="hidden" name="pP" value="${prdVO.pP}"> <input
								type="hidden" name="pDes" value="${prdVO.pDes}"> <input
								type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="pTno" value="${prdVO.pTno}">
						</Form>
					</div>
				</c:forEach>
			</c:when>
			<c:when test="${inCludeVO=='soup'}">
				<c:forEach var="prdVO" items="${list5}">
					<div class=form>
						<Form name="shoppingForm"
							action="<%=request.getContextPath()%>/product/ShoppingServlet.do"
							method="post">
							<div class="block">
								<img
									src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}"
									class='picture'>
							</div>
							<div class="block1">商品名稱:${prdVO.pname}</div>
							<div class="block2">商品價格:$${prdVO.pP}</div>
							<div class="block3">商品明細:${prdVO.pDes}</div>
							<div class="block4">
								數量: <select size="1" name="pDoffer">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select> <input type="hidden" name="action" value="ADD">
								<input type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="inCludeVO" value="${inCludeVO}">
								<!--送出當前是哪種分類給Controller--> <input
									type="submit" name="Submit" value="加入購物車" class="button">
							</div>
							<input type="hidden" name="pPic" value="${prdVO.pPic}"> <input
								type="hidden" name="pname" value="${prdVO.pname}"> <input
								type="hidden" name="pP" value="${prdVO.pP}"> <input
								type="hidden" name="pDes" value="${prdVO.pDes}"> <input
								type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="pTno" value="${prdVO.pTno}">
						</Form>
					</div>
				</c:forEach>
			</c:when>
			<c:when test="${inCludeVO=='jam'}">
				<c:forEach var="prdVO" items="${list6}">
					<div class=form>
						<Form name="shoppingForm"
							action="<%=request.getContextPath()%>/product/ShoppingServlet.do"
							method="post">
							<div class="block">
								<img
									src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}"
									class='picture'>
							</div>
							<div class="block1">商品名稱:${prdVO.pname}</div>
							<div class="block2">商品價格:$${prdVO.pP}</div>
							<div class="block3">商品明細:${prdVO.pDes}</div>
							<div class="block4">
								數量: <select size="1" name="pDoffer">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select> <input type="hidden" name="action" value="ADD"> 
								<input type="hidden" name="pno" value="${prdVO.pno}">
								 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="inCludeVO" value="${inCludeVO}">
								<!--送出當前是哪種分類給Controller--><input
									type="submit" name="Submit" value="加入購物車" class="button">
							</div>
							<input type="hidden" name="pPic" value="${prdVO.pPic}"> <input
								type="hidden" name="pname" value="${prdVO.pname}"> <input
								type="hidden" name="pP" value="${prdVO.pP}"> <input
								type="hidden" name="pDes" value="${prdVO.pDes}"> <input
								type="hidden" name="pno" value="${prdVO.pno}">
								<input
								type="hidden" name="pTno" value="${prdVO.pTno}">
						</Form>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="prdVO" items="${list}">
					<div class=form>
						<Form name="shoppingForm"
							action="<%=request.getContextPath()%>/product/ShoppingServlet.do"
							method="post">
							<div class="block">
								<img
									src="<%=request.getContextPath()%>/product/DBGifReaderProduct.do?pno=${prdVO.pno}"
									class='picture'>
							</div>
							<div class="block1">商品名稱:${prdVO.pname}</div>
							<div class="block2">商品價格:$${prdVO.pP}</div>
							<div class="block3">商品明細:${prdVO.pDes}</div>
							<div class="block4">
								數量: <select size="1" name="pDoffer">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								</select> <input type="hidden" name="action" value="ADD" id="addProduct">
								<input type="button" name="Submit" value="加入購物車" class="button">
							</div>
							<input type="hidden" name="pPic" value="${prdVO.pPic}"> <input
								type="hidden" name="pname" value="${prdVO.pname}"> <input
								type="hidden" name="pP" value="${prdVO.pP}"> <input
								type="hidden" name="pDes" value="${prdVO.pDes}"> <input
								type="hidden" name="pno" value="${prdVO.pno}">
						</Form>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
	<p>


		<jsp:include page="/back-end/liveShop/Cart.jsp" flush="true" />

		<!--引用jQuery-->
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			type="text/javascript"></script>
		<!--引用SweetAlert2.js-->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
			type="text/javascript"></script>
		<script type="text/javascript">
		
				
// 		$("#add").click(function() {
                  
//                 $.ajax({             
<%--                 	url:"<%=request.getContextPath()%>/product/ShoppingServlet.do", --%>
//                 	type:"post",
//                 	contentType : 'application/json',
// 					data:{
// 						action:"ADD",
// 						pname :$('#pname').val(),
// 						pP:$('#pP').val(),
// 						pDes:$('#pDes').val(),
// 						pDoffer:$('#pDoffer').val(),
// 						pno:$('#pno').val(),
						
// 					},
//                     success : function() {
//                         	$('#show').html('');
//                         	$('#show').load('/back-end/liveShop/Cart.jsp');    
                             
//                     }
//                     })
//             });
		
		
       	 
			$(function() {
				var that;
				$(".button").click(function(e) {
					that = this;
					e.preventDefault(); // 阻止form瀏覽器預設行為           
					swal({
						title : "已新增",
						text : "您的商品已加入購物車",
						type : "success",
						showCancelButton : false,
					}).then(function() { // callback function
						$(that).closest('form').submit(); // 表單送出
					});
				});
			});
			
			
		</script>
</body>
</html>