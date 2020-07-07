<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="java.util.*" %>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
	List<PermissionVO> perVOlist = (ArrayList<PermissionVO>) request.getAttribute("perVOlist");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Miss M員工個人資料</title>
	<%@ include file="/back-end/back-end-head.jsp" %>
<style>
* {
	font-family: Microsoft JhengHei, serif;
}
</style>
</head>
<body>

	<%@ include file="/back-end/back-end-header.jsp" %>
<!-- 	<div class="container"> -->

		<div class="row justify-content-center align-items-center">
			<div class="col-xl-6 text-center">
				<div class="alert alert-primary text-center" role="alert">
					<h2>Miss M員工個人資料</h2>
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-xl-6 text-center">
				<img width=200 height=200 style="border-radius:50%;"
					src="<%=request.getContextPath()%>/back-end/employee/epicshow.do?empno=${employeeVO.empno}" />
			</div>
		</div>

		<div class="row justify-content-center align-items-center">
			<div class="col-xl-6">

				<table style="margin-top:10px;"
					class="table table-striped table-bordered table-hover table-sm text-center justify-content-center">
					<thead>
						<tr>
							<th class="text-nowrap align-middle table-primary">員工編號</th>
							<td class="align-middle">${employeeVO.empno}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">帳號</th>
							<td class="align-middle">${employeeVO.eAccount}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">姓名</th>
							<td class="align-middle">${employeeVO.eName}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">電話</th>
							<td class="align-middle">${employeeVO.ePhone}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">EMAIL</th>
							<td class="align-middle">${employeeVO.eEmail}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">職稱</th>
							<td class="align-middle">${employeeVO.eTitle}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">狀態</th>
							<td class="align-middle">
								${(employeeVO.eStatus == 0) ? '離職':'' }
								${(employeeVO.eStatus == 1) ? '在職':'' }
								${(employeeVO.eStatus == 2) ? '留職停薪':'' }
							</td>
						</tr>
						
<%-- <jsp:useBean id="feaSvc" scope="page" class="com.features.model.FeaturesService"/> --%>
<%-- <jsp:useBean id="perSvc" scope="page" class="com.permission.model.PermissionService"/> --%>
						<tr>
							<th class="text-nowrap align-middle table-primary">權限</th>
							<td class="align-middle">
								<div class="container">
									<div class="row justify-content-md-center">
									
										<c:forEach var="feaVO" items="${feaSvc.all }">
											<c:forEach var="perVO" items="${perVOlist}">
												<c:if test="${perVO.feano == feaVO.feano }">
													<div class="" style="width:50%;">
														<div class="">${feaVO.feaName}</div>
													</div>
												</c:if>
											</c:forEach>
										</c:forEach>
										
									</div>
								</div>
									
							</td>
						</tr>
					</thead>
				</table>

			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-xl-7 text-center">
				<form METHOD="post"	ACTION="${pageContext.request.contextPath}/back-end/employee/employee.do">
					<input type="hidden" name="action" value="getOne_For_Update">
					<input type="hidden" name="empno" value="${employeeVO.empno}">
					<input class="btn btn-secondary" type="submit" value="修改資料">
				</form>
			</div>
			
		</div>
<!-- 	</div> -->
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>