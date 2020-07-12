<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.ono.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.onodetail.model.*"%>
<% 
	ONODetailService ondSvc = new ONODetailService();
	List<ONODetailVO> onoDetailList = ondSvc.getAll();
	pageContext.setAttribute("onoDetailList",onoDetailList);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

	<table class="table text-nowrap table-hover info text-center
							shadow-lg p-3 mb-5 bg-white rounded">
							
		<tr class="text-center">
			<th>商品編號</th>
			<th>購買數量</th>
			<th>單品價格</th>
	    </tr>
	    
		<c:forEach var="ondVO" items="${onoDetailList}">
			<c:forEach var="onVO" items="${myONOlist}">
				<c:if test="${myONOlist.onono} == ${list.onono}">
					<tr class="text-center">
	
							
							<td class="text-center">${ondVO.pno}</td>
							<td class="text-center">${ondVO.onoQty}</td>
							<td class="text-center">${ondVO.onoPrice}</td>
							
							

					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
		
	</table>
	
</body>
</html>