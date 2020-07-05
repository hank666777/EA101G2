<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.employee.model.*" %>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>

<%
	PermissionService perSvc = new PermissionService();
	List<PermissionVO> perlist = perSvc.getAll();
	pageContext.setAttribute("perlist", perlist);
%>

<html>
<head>
	<title>MISS M員工查詢</title>
	<%@ include file="/back-end/back-end-head.jsp" %>
</head>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>

<!-- <div class="container-fluid"> -->

	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover table-sm text-center justify-content-center">
			<tr class="table-primary">
				<th scope="row" class="text-nowrap">員工編號</th>
				<th scope="row" class="text-nowrap">圖片</th>
				<th scope="row" class="text-nowrap">姓名</th>
				<th scope="row" class="text-nowrap">電話</th>
				<th scope="row" class="text-nowrap" style="width:250px;">EMAIL</th>
				<th scope="row" class="text-nowrap">職稱</th>
				<th scope="row" class="text-nowrap" style="width:260px;">權限</th>
				<th scope="row" class="text-nowrap">狀態</th>
				<th scope="row" class="text-nowrap">修改</th>
			</tr>
<%--			<%@ include file="page1.file" %>--%>
<%--			<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>"--%>
<%--								 end="<%=pageIndex+rowsPerPage-1%>">--%>
<c:forEach var="employeeVO" items="${emplist}">
				<tr>
					<th scope="row" class="align-middle">${employeeVO.empno}</th>
					<td class="align-middle">
						<img width=100 height=100
								 src="<%=request.getContextPath()%>/back-end/employee/epicshow.do?empno=${employeeVO.empno}"/></td>
					<td class="align-middle">${employeeVO.eName}</td>
					<td class="align-middle">${employeeVO.ePhone}</td>
					<td class="align-middle">${employeeVO.eEmail}</td>
					<td class="align-middle">${employeeVO.eTitle}</td>
					<td class="align-middle" style="width:270px;">
						<div class="container">
							<div class="row">
							<c:forEach var="permissionVO" items="${perlist}">
								<c:if test="${employeeVO.empno == permissionVO.empno}">
									<c:forEach var="featuresVO" items="${feaSvc.all}">
										<c:if test="${featuresVO.feano == permissionVO.feano}">
											<div class="col-6">${featuresVO.feaName}</div>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
							</div>
						</div>
					</td>
					<td class="align-middle">
							${(employeeVO.eStatus == 0) ? '離職':'' }
							${(employeeVO.eStatus == 1) ? '在職':'' }
							${(employeeVO.eStatus == 2) ? '留職停薪':'' }
					</td>
					<td class="align-middle">
						<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/back-end/employee/employee.do">
							<input class="btn btn-secondary" type="submit" value="修改">
							<input type="hidden" name="empno" value="${employeeVO.empno}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
				</tr>
<%--			</c:forEach>--%>
</c:forEach>
		</table>
	</div>
<%--	<%@ include file="page2.file" %>--%>
<!-- </div> -->

<%@ include file="/back-end/back-end-footer.jsp" %>
</body>
</html>