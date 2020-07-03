<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html style="height: 100vh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>back-end-index</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"	rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back-emp_select_page_css.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
	*{
		font-family:微軟正黑體;
	}
</style>
</head>
<body>

	<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">

		<div class="row"></div>

		<div class="accordion" id="accordionExample">
			<div class="card">
			<div class="card-header" id="headingOne">
				<h2 class="mb-0">
				<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					多條件查詢，一樣符合即是
				</button>
				</h2>
			</div>

			<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
				<div class="card-body">
					<form mathod="post" action="${pageContext.request.contextPath}/back-end/employee/employee.do">
						請輸入條件
						<div class="row">
							<div class="col">

								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-1">員工編號</span>
									<input class="form-control" type="text" name="keyempno" aria-describedby="inputGroup-sizing-1">
								</div>

								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-2">員工姓名</span>
									<input class="form-control" type="text" name="keyeName" aria-describedby="inputGroup-sizing-2">
								</div>

								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-3">員工職稱</span>
									<select name="keyeTitle" id="keyeTitle" class="custom-select d-block w-100">
										<option value=""></option>
										<option value="店長">店長</option>
										<option value="領班">領班</option>
										<option value="大廚">大廚</option>
										<option value="二廚">二廚</option>
										<option value="服務生">服務生</option>
									</select>
								</div>

								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-4">員工狀態</span>
									<select name="keyeStatus" class="custom-select d-block w-100">
										<option value=""></option>
										<option value="0">離職</option>
										<option value="1">在職</option>
										<option value="2">留職停薪</option>
									</select>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="col">
								<input type="hidden" name="action" value="list_search_employee" >
								<input type="submit" id="searchbtn" value="查 詢" class="btn btn-outline-warning sendbtn" >
							</div>
						</div>

					</form>
				</div>
			</div>
			</div>

			<div class="card">
			<div class="card-header" id="headingTwo">
				<h2 class="mb-0">
				<button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
					依編號查詢
				</button>
				</h2>
			</div>
			<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
				<div class="card-body">
				2
				</div>
			</div>
			</div>

		</div>
		
		
		
		
		<div class="row">
			<div class="col-xl">
				<div style="height: 30px; background-color: #666;"></div>
			</div>
		</div>
		<br/>
		
		<jsp:useBean id="empSvc" class="com.employee.model.EmployeeService"/>
		<div class="row justify-content-center ">
			<div class="col-xl-2 text-center">
				<form method="post" action="${pageContext.request.contextPath}/back-end/employee/employee.do">
					<b>選擇員工姓名:</b> 
					<select class="custom-select d-block w-100" size="" name="empno" id="">
						<c:forEach var="employeeVO" items="${empSvc.all}">
							<option value="${employeeVO.empno}">${employeeVO.eName}
						</c:forEach>
					</select> 
					<input type="hidden" name="action" value="getOne_For_Display">
					<input class="btn btn-outline-warning sendbtn" type="submit" value="送 出">
				</form>
			</div>
				
			<div class="col-xl-3">
				<form method="post" action="${pageContext.request.contextPath}/back-end/employee/employee.do">
					<div class="row text-center">
						<div class="col">
<!-- 請輸入查詢字：員工編號或姓名或職稱或狀態 -->
							<b>輸入員工編號(如E0000001):</b> 
							<input type="text" name="empno"	class="form-control"> 
<!-- 準備改寫，送請求致控制器list_search_employee -->
							<input type="hidden" name="action" value="getOne_For_Display"> 
<!-- 								<input type="hidden" name="action" value="list_search_employee">  -->
							<input class="btn btn-outline-warning sendbtn" type="submit" name="送出">
							
						</div>
					</div>
				</form>
			</div>
				
		</div>
		
		<div class="row">
			<div class="col text-center" style="color:#F00;">
				<div id="errorPage">
					<c:if test="${! empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
						${message}<br>
						</c:forEach>
					<br>
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="row justify-content-center">
			<div class="col-xl-3 chefPic">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.3em;">新增員工</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/employee/addEmployee.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/employee/selectPage03.png" class="card-img-top" alt="...">
				</a>
				</figure>
			</div>
			
			<div class="col-xl-3 chefPic">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.3em;">查詢所有員工</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/employee/listAllEmployee.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/employee/selectPage02.png" class="card-img-top" alt="...">
				</a>
				</figure> 
			</div>
			
			<div class="col-xl-3 chefPic">
				<figure class="figure">
				<figcaption class="figure-caption" style="font-size:1.3em;">個人資料修改</figcaption>
				<br/><br/>
				<a href="${pageContext.request.contextPath}/back-end/employee/update_employee_input.jsp">
				<img src="${pageContext.request.contextPath}/images/back-end/employee/selectPage01.png" class="card-img-top" alt="...">
				</a>
				</figure> 
			</div>
		</div>
		
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
	<script>
      $('.sendbtn').click(function(){
          var btn = this;
          setTimeout(function(){
              btn.disabled = true;
          },50);
      });

	</script>
</body>
</html>