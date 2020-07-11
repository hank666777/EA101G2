<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.ono.model.*"%>
<%@ page import="com.mem.model.*"%>

<% 
	//取得所有訂單
	List<ONOVO> onolist = new ONOService().getAll();
	//取得登入者的訂單
	MemVO memvo1 = (MemVO)session.getAttribute("memVO");
	List<ONOVO> myONOlist = onolist.stream().filter(ono -> ono.getmemno().contains(memvo1.getMemno()))
																					.collect(Collectors.toList());
	pageContext.setAttribute("myONOlist", myONOlist);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
/*   table#table-1 { */
/* 	background-color: #CCCCFF; */
/*     border: 2px solid black; */
/*     text-align: center; */
/*   } */
/*   table#table-1 h4 { */
/*     color: red; */
/*     display: block; */
/*     margin-bottom: 1px; */
/*   } */
/*   h4 { */
/*     color: blue; */
/*     display: inline; */
/*   } */
/*   table { */
/* 	width: 600px; */
/* 	background-color: white; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
/*   } */
/*   table, th, td { */
/*     border: 1px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 5px; */
/*     text-align: center; */
/*   } */
</style>
</head>
<body>
<%-- <%@ include file="/back-end/back-end-header.jsp" %> --%>
<div id="container" class="container" style="margin:auto; margin-top:30px;">
<!-- <table id="table-1"> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 		 <h3>現場訂單資料</h3> -->
<!-- 		 <h4> -->
<%-- 			 <a href="<%=request.getContextPath()%>/front-end/ono/select_page.jsp"> --%>
<%-- 			 <img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回首頁</a> --%>
<!-- 		 </h4> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->
<table class="table text-nowrap table-hover info text-center
							shadow-lg p-3 mb-5 bg-white rounded">
	<tr class="text-center">
		<th>線上訂單編號</th>
		<th>會員姓名</th>
		<th>優惠券編號</th>
		<th>訂單時間</th>
		<th>訂單總價</th>
		<th>訂單狀態</th>
		<th>訂單付款狀態</th>
	</tr>
<c:forEach var="onVO" items="${myONOlist}">
	<tr class="text-center">
	
	
		<td class="text-center">
				<!-- Button trigger modal 訂單明細內容-->
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#_${memVO.memno}">
				  詳細
				</button>
				${onVO.onono}
		</td>
		<td class="text-center">${memVO.mName}</td>
		<td class="text-center">${onVO.couponSno}</td>
		<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${onVO.onoTime}"></fmt:formatDate></td>
		<td class="text-center">${onVO.onoTotal}</td> 
		<td class="text-center">
			${(onVO.onoStatus == 0)? '<p class="text-danger">未完成<p>':''}
			${(onVO.onoStatus == 1)? '<p class="text-success">已完成<p>':''}
			${(onVO.onoStatus == 2)? '已取消':''}
		</td>
		<td class="text-center">
			${(onVO.onoPay == 0)? '<p class="text-danger">未結帳<p>':'<p class="text-success">結帳<p>'}
		</td>
	
	</tr>
	
	
	
	<!-- Modal -->
	<div class="modal fade" id="_${memVO.memno}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<!-- 要導入的訂單明細，未完成 -->
	        333333333333...
	      </div>
	    </div>
	  </div>
	</div>
	<!-- modal end -->
	
</c:forEach>
</table>
</div>
<%-- <%@ include file="/back-end/back-end-footer.jsp"%> --%>
</body>
</html>