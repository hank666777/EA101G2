<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ono.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<% 
	ONOService onSvc = new ONOService();
	List<ONOVO> list = onSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back-end/bok/jquery-ui-1.12.1/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/back-end/bok/jquery-ui-1.12.1/jquery-ui.js"></script>
<style type="text/css">
  #table-1{
 	position: relative;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    border-collapse: collapse;
    margin:auto;
    width:500px;
  }
  #table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }

  #listAll {
  			position: relative;
			margin-top: 10px;
			border-collapse: collapse;
			width: 100%;
  }
  #listAll, th, td {
    border: 1px solid #CCCCFF;
    padding: 8px;
    text-align: center;
    border-bottom: 1px solid #ddd;
  }


  #booking-container{

			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
  }
  .data:hover {
			background-color:#CADCF9;
  }
	#list_container{ 
 			position: relative;  
 			margin:3% auto; 
 			width: 1200px;
			height: 800px; 
 			text-align: center; 
			background-color: #F8F8F8; 
 			border-radius:5px; 
 			box-shadow:3px 3px 9px black; 
 			padding: 5px; 
 			overflow-y: auto; 
 			display: none;
 		}
  }
</style>
</head>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>

<table id="table-1">
	<tr><td>
		 <h3>所有線上訂單資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/ono/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/liveShop/images/logo.png" width="100" height="32" border="0">回線上訂單首頁</a></h4>
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

<div class="container-fluid" id="booking-container">
	<div id="list_container">
	<table id="listAll">
	<tr>
		<th>線上訂單編號</th>
		<th>會員編號</th>
		<th>優惠卷</th>
		<th>線上訂單時間</th>
		<th>線上訂單總價</th>
		<th>線上訂單狀態</th>
		<th>線上訂單付款狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/back-end/liveShop/page1.file" %>	
	<c:forEach var="onVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr class="data">
			<td>${onVO.onono}</td>
			<td>${onVO.memno}</td>
			
			<td>
			<c:if test="${onVO.couponSno == null}">
				<c:out value="未使用"></c:out>
			</c:if>
			<c:if test="${onVO.couponSno != null}">
				<c:out value="${onVO.couponSno}"></c:out>
			</c:if>
			</td>
			
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${onVO.onoTime}"></fmt:formatDate></td>
			<td>${onVO.onoTotal}</td> 
			
			<td>
			<c:if test="${onVO.onoStatus == 0}">
				<c:out value="未完成"></c:out>
			</c:if>
			<c:if test="${onVO.onoStatus == 1}">
				<c:out value="已完成"></c:out>
			</c:if>
			</td>
			
			<td>
			<c:if test="${onVO.onoPay == 0}">
				<c:out value="未付款"></c:out>
			</c:if>
			<c:if test="${onVO.onoPay == 1}">
				<c:out value="已付款"></c:out>
			</c:if>
			</td>
			
			<td>	
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ONOServlet/ONOServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="onono"  value="${onVO.onono}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ONOServlet/ONOServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="onono"  value="${onVO.onono}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
	</c:forEach>

</table>
<%@ include file="/back-end/liveShop/page2.file" %>
</div>

</div>
<%@ include file="/back-end/back-end-footer.jsp"%>
<script src="${pageContext.request.contextPath}/back-end/bok/jquery-ui-1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
			 $("#list_container").fadeIn("slow");
		});

		function detail(){
			alert();
		}
		$(function () {
			$('[data-toggle="popover"]').popover()
		})
	</script>
</body>
</html>