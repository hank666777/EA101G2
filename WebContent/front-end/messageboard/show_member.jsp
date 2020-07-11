<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>Insert title here</title>
<%@ include file="/back-end/back-end-head.jsp" %>
<%@ include file="/front-end/front-end-head.jsp"%>
</head>
<body>
	<%@ include file="/front-end/front-end-header.jsp"%>
	<%@ include file="/front-end/front-end-header2.jsp"%>
		<div class="row justify-content-center">
			<div class="col-xl-7 text-center">
				<img width=150 height=150 style="border-radius:50%;"
					src="<%=request.getContextPath()%>/front-end/mem/mem.mPic?memno=${param.memno}" />
			</div>
		</div>

		<div class="row justify-content-center align-items-center">
			<div class="col-xl-7">

				<table style="margin-top:10px;"
					class="table table-striped table-bordered table-hover table-sm text-center justify-content-center">
					<thead>
						<tr>
							<th class="text-nowrap align-middle table-primary">員工編號</th>
							
							<c:forEach var="memVO" items="${memlist}">
							 		<c:if test="${memVO.memno == param.memno}">
							 		<td class="align-middle">${memVO.memno}</td>
							 		
							 		
							 		</c:if></c:forEach>
							
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">帳號</th>
							<td class="align-middle">${requestScope.memVO.mAccount}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">姓名</th>
							<td class="align-middle">${requestScope.memVO.mName}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">電話</th>
							<td class="align-middle">${requestScope.memVO.mPhone}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">EMAIL</th>
							<td class="align-middle">${requestScope.memVO.mEmail}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">狀態</th>
							<td class="align-middle">
								${(requestScope.memVO.mStatus == 0) ? '000':'' }
								${(requestScope.memVO.mStatus == 1) ? '111':'' }
								${(requestScope.memVO.mStatus == 2) ? '222':'' }
							</td>
						</tr>
						
					</thead>
				</table>

			</div>
		</div>


</body>
</html>