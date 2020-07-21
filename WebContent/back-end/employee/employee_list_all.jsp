<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<%
	EmployeeService empSvc = new EmployeeService();
	List<EmployeeVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
	
	PermissionService perSvc = new PermissionService();
	List<PermissionVO> perlist = perSvc.getAll();
	pageContext.setAttribute("perlist", perlist);
%>

<html>
<head>
	<title>MISS M員工資料查詢</title>
	<%@ include file="/back-end/back-end-head.jsp" %>
</head>
<body style="background-size:cover;" background="${pageContext.request.contextPath}/images/back-end/back-bg.jpg">

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container-fluid">
	
<!-- 		<div class="row"> -->
<!-- 					<div class="col-12"> -->
<!-- 							請輸入查詢字：員工編號或姓名或職稱或狀態 -->
<!-- 							<p class="h4" style="color:#FFF;">輸入員工姓名:</p>  -->
<!-- 							<input type="text" name="eName"	class="form-control" id="eName">  -->
<!-- 					</div> -->
<!-- 		</div> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-12" id="searchInfo"></div> -->
<!-- 		</div> -->
		
		<div class="row ">
			<div class="col-12">
			<table class="table table-striped text-nowrap table-bordered table-hover table-sm text-center "
							style="margin:0; padding:0px; background-color:#EEE; ocacity:.9;">
		 		<thead>
					<tr class="table-primary">
						<th scope="col" class="">員工編號</th>
						<th scope="col" class="">圖片</th>
						<th scope="col" class="">姓名</th>
						<th scope="col" class="">電話</th>
						<th scope="col" class="" style="width:250px;">EMAIL</th>
						<th scope="col" class="">職稱</th>
						<th scope="col" class="" style="width:250px;">權限</th>
						<th scope="col" class="">狀態</th>
						<th scope="col" class="">修改</th>
					</tr>
				</thead>
				<%@ include file="page1.file"%>
					<tbody>
				<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>"
									 end="<%=pageIndex+rowsPerPage-1%>">
					<tr id="info-tr">
						<td class="align-middle">${employeeVO.empno}</td>
						<td class="align-middle">
							<img width=100 height=100
									 src="<%=request.getContextPath()%>/back-end/employee/epicshow.do?empno=${employeeVO.empno}" /></td>
						<td class="align-middle">${employeeVO.eName}</td>
						<td class="align-middle">${employeeVO.ePhone}</td>
						<td class="align-middle">${employeeVO.eEmail}</td>
						<td class="align-middle">${employeeVO.eTitle}</td>
						<td class="align-middle" style="width:250px;">
										<div class="container">
											<div class="row">

									<c:forEach var="permissionVO" items="${perlist}">
										<c:if test="${employeeVO.empno == permissionVO.empno}">
											 <c:forEach var="featuresVO" items="${feaSvc.all}">
												 <c:if test="${featuresVO.feano == permissionVO.feano}">
												<div class="col-6" >
												 		${featuresVO.feaName}
												</div>
												 </c:if>
											 </c:forEach>
										</c:if>
									</c:forEach>
									
											</div>
										</div>
						</td>
						<td class="align-middle">
							<c:out value="${(employeeVO.eStatus == 0) ? '離職':'' }"/>
							<c:out value="${(employeeVO.eStatus == 1) ? '在職':'' }"/>
							<c:out value="${(employeeVO.eStatus == 2) ? '留職停薪':'' }"/>
						</td>
						<td class="align-middle">
							<FORM METHOD="post"	ACTION="${pageContext.request.contextPath}/back-end/employee/employee.do">
								<input class="btn btn-secondary" type="submit" value="修改">
								<input type="hidden" name="empno" value="${employeeVO.empno}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
					</tbody>
			</table>
			
		</div>
		</div>
		<br>
		<%@ include file="page2.file"%>
	</div>
<script>
		//此為需要事先定義要記錄的定時器
		var keyinTime;
		$('#eName').keypress(function(){
			$('#searchInfo')
				.html('<div class="spinner-grow text-primary text-center" style="width: 3rem; height: 3rem; role="status"></div>');
		});
		
		$('#eName').keyup(function(e){
			var eName = $('#eName').val();
			e.preventDefault();
			//要清掉正在計時的事件，要不然會多重運行
			clearTimeout(keyinTime);
			keyinTime = setTimeout(function(){
				if (eName > 0){
					$.ajax({
						url : '${pageContext.request.contextPath}/back-end/employee/employee.do',
						type : 'post',
						dataType : 'json',
						data : {
							action :'employee_one_search',
							ename : eName
						},
						stop : function(data){
							$('#searchInfo').html('');
						},
						
						success : function(data){
							if(data.length = 0){
								console.log(data)
							}
							if(data.length > 0){
								
							}
							
							console.log('data: ' + data);
						}
						
					});
				
				}
				//秒數就看你要定義多久才運行，一般理想是800~1500的值
			},1000);
			
		});
</script>
	<%@ include file="/back-end/back-end-footer.jsp"%>

	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>