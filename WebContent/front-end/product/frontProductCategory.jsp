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
	
// 	Object PT = session.getAttribute("pTno");
// 	ProductService productSvc = new ProductService();
// 	List<ProductVO>list = productSvc.getProductByCategory(PT.toString());
// 	pageContext.setAttribute("list", list);

	List<ProductVO> list = (List<ProductVO> )session.getAttribute("listProduct_ByCompositeQuery2");
%>    


<html>
<head>

<title>所有商品資料</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style type="text/css" media="screen">	
		
		body{
		background-image:url('<%= request.getContextPath() %>/images/front-end/productImg/frontProductBackground.jpg');
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}
		
		.col-xl-4, .col-lg-6{
				width: 340px;
				float:left;
				margin: 0 auto;
				
			}
			
/* 		.card{ */
/* 			width: 300px; */
/* 			margin:10px; */
/* 		}	 */
								
		.productimage{
		
			border: 2px solid #fff;
			margin:20px auto;
	
			box-sizing: border-box;
			
		}
		
		.productInfo{
			margin-left: 25px;
		}

		#searchproducttitle{
			text-align:center;
			margin-bottom:20px;
		}
		
		.olt{
			background-color: #F3EFCA;
			width:1200px;
			margin:0 0 0 10;
			padding:10px;	
		}

		.pagefloat{
			text-align:left;
			margin-right:10px;
		}

		.pagebox{
			margin-bottom:30px;
		}
		
		.clicksure{
			margin-bottom:10px;
		}

</style>

</head>
<body>

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td id ="backbutton"> -->
<%-- 				<h4><a href="<%= request.getContextPath() %>/front-end/product/frontProductSearch.jsp">回前頁</a></h4> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<%@ include file="/front-end/front-end-head.jsp" %>
<%@ include file="/front-end/front-end-header.jsp" %>
<%@ include file="/front-end/front-end-header2.jsp" %>

	<div id="productmark">
			<h1 id="searchproducttitle">商品一覽</h1>
	</div>

	


<div class="container">
	<div class="row">

	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs} ">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



	<jsp:useBean id="PTypeSvc" scope="page" class="com.ptype.model.PTypeService" />
		
	<div class="olt">	
	
	<%@ include file="page1.file" %>	
	
		<c:forEach var="productVO" items="${listProduct_ByCompositeQuery2}" begin="<%=pageIndex %>" end="<%=pageIndex + rowsPerPage-1 %>">

			<div class="col-xl-4 col-lg-6 card" style="width:360px; margin:0px 10px 10px 0px;">		
 			
 				<div class="card-body">
 			
 					<a href="<%= request.getContextPath()%>/product.do?pno=${productVO.pno}&action=getDisplayView">
 						<img class="productimage" src="<%=request.getContextPath()%>/ProductReader?pno=${productVO.pno}" width=292px height=219px> 
					</a>
					<div>
						<p class="productInfo">名稱：${productVO.pname}
						</p>
					</div>
				
					<div class = "row">
							
						<div class="col-7">		
							<p class="productInfo">價格：${productVO.pP}元
							</p>
						</div>	
								
					</div>
		  
		  		</div>
		  
		   </div>
			
		</c:forEach>
	
				
	
	</div>
		
	<div class="olt pagebox">
		<%@ include file="page2.file" %>
	</div>	
			
	
	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
						
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            </div>
					
					<div class="modal-body">
		<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
		               <jsp:include page="listOneProduct.jsp" />
		<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>
					
					<div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		            </div>
				
				</div>
			</div>
		</div>
	
	        <script>
	    		 $("#basicModal").modal({show: true});
	        </script>

	</c:if>

	
		</div>
	</div>

	

<%@ include file="/front-end/front-end-footer.jsp" %>

</body>
</html>

