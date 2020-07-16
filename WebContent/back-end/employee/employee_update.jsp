<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
	List<PermissionVO> perVOlist = (ArrayList<PermissionVO>) request.getAttribute("perVOlist");
%>

<html>
<head>
	<title>Miss M員工個人資料修改</title>
	<%@ include file="/back-end/back-end-head.jsp" %>
<style>
* {
	font-family: Microsoft JhengHei;
}
input{
	background-color: yellow;
}
</style>
</head>

<body style="background-size:cover;" background="${pageContext.request.contextPath}/images/back-end/back-bg.jpg">

	<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		
		<div class="row justify-content-center">
			<div class="col-xl-6 text-center">
				<div class="alert alert-primary text-center" role="alert">
					<h2>Miss M員工個人資料修改</h2>
				</div>
			</div>
		</div>
		
		<div class="row justify-content-center">
			<div class="col-xl-6 text-center"  style="color:#F00;">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
							${message}<br>
						</c:forEach>
				</c:if>
			</div>
		</div>
		
		<div class="row justify-content-center">
			<label for="ePic"><div class="col-xl-6 text-center">
				<img id="epicShow" width=150 height=150 style="border-radius:50%;" 
					src="<%=request.getContextPath()%>/back-end/employee/epicshow.do?empno=${employeeVO.empno}" />
			</div></label>
		</div>

		<form method="post" action="${pageContext.request.contextPath}/back-end/employee/employee.do" name="form1" enctype="multipart/form-data">
		<div class="row justify-content-center">
			<div class="col-xl-6" style="background-color:#FFF; opacity:.9;">
				
				<table style="margin-top:15px;" 
					class="table table-striped table-bordered table-hover table-sm text-center justify-content-center">
					<tbody>
						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">員工編號</th>
							<td scope="col" class=" align-middle">
							<input class="form-control" type="text" name="empno"
								value="${employeeVO.empno}" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">帳號</th>
							<td scope="col" class="align-middle">
							<input class="form-control" type="text" name="eAccount" style="background-color:LemonChiffon;"
								value="${employeeVO.eAccount}"/>
							</td>
						</tr>
						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">密碼</th>
							<td scope="col" class="align-middle">
							<input class="form-control" type="password" name="ePw" style="background-color:LemonChiffon;"
								value="${employeeVO.ePw}" /></td>
						</tr>
						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">姓名</th>
							<td scope="col" class="align-middle">
							<input class="form-control" class="" type="text" style="background-color:LemonChiffon;"
								name="eName" value="${employeeVO.eName}" /></td>
						</tr>
						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">電話</th>
							<td scope="col" class="align-middle">
							<input class="form-control" type="text" style="background-color:LemonChiffon;"
								name="ePhone" value="${employeeVO.ePhone}" /></td>
						</tr>
						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">EMAIL</th>
							<td scope="col" class="align-middle">
							<input class="form-control" type="email" style="background-color:LemonChiffon;"
								name="eEmail" value="${employeeVO.eEmail}" /></td>
						</tr>
<!-- 						<tr class=""> -->
<!-- 							<th scope="row" class="text-nowrap align-middle table-primary" scope="col">圖片</th> -->
<!-- 							<td scope="col" class=""> -->
								<input class="btn btn-secondary" type="file" name="ePic" id="ePic" style="display:none;"/>
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr class="text-center">
							<th scope="col" class="text-nowrap align-middle table-primary">職稱</th>
							<td scope="col" class="">
								<select name="eTitle" class="custom-select d-block w-100" style="background-color:LemonChiffon;"> 
									<option value="店長" ${(employeeVO.eTitle == '店長') ? 'selected':'' }>店長</option>
						      <option value="領班" ${(employeeVO.eTitle == '領班') ? 'selected':'' }>領班</option>
						      <option value="大廚" ${(employeeVO.eTitle == '大廚') ? 'selected':'' }>大廚</option>
						      <option value="二廚" ${(employeeVO.eTitle == '二廚') ? 'selected':'' }>二廚</option>
						      <option value="服務生"  ${(employeeVO.eTitle == '服務生') ? 'selected':'' }>服務生</option>
								</select> 
							</td>
						</tr>
						
						<tr class="text-center justify-content-center center">
							<th scope="col" class="text-nowrap align-middle table-primary">狀態</th>
							<td scope="col" class="align-middle justify-content-center">
								<select name="eStatus" class="custom-select d-block w-100" style="background-color:LemonChiffon;">
									<option value="0" ${(employeeVO.eStatus == 0) ? 'selected':''}>離職</option>
									<option value="1" ${(employeeVO.eStatus == 1) ? 'selected':''}>在職</option> 		
									<option value="2" ${(employeeVO.eStatus == 2) ? 'selected':''}>留職停薪</option>				
								</select> 
							</td>
						</tr>

						<tr>
							<th scope="col" class="text-nowrap align-middle table-primary">權限</th>
							<td scope="col" class="align-middle">
							
<%-- <jsp:useBean id="feaSvc" scope="page" class="com.features.model.FeaturesService"/> --%>
								<div class="container">
									<div class="row justify-content-md-center">
										<c:forEach var="feaVOlist" items="${feaSvc.all}">
											<div class="" style="width:50%;">
												<label><input type="checkbox" class="form-check-input" name="feano" 
													value="${feaVOlist.feano}" 
														<c:forEach var="perVOlist" items="${perVOlist}">
															${(perVOlist.feano == feaVOlist.feano)? 'checked':'' }
														</c:forEach>
													>${feaVOlist.feaName}</label>
												<br/>
											</div>
										</c:forEach>
										
									</div>
								</div>
							</td>
						</tr>
						
					</tbody>
				</table>
					
			</div>
		</div>
			
		<div class="row justify-content-center">
			<div class="col-xl-7 text-center">
				<input type="hidden" name="action" value="update">
				<input class="btn btn-secondary" type="submit" value="送出修改">		
			</div>
		</div>
		</form>

	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
	
	<script>
		
		$(function(){
			$('#ePic').change(function(){
				var reader = new FileReader();
				var ePic = $('#ePic')[0].files[0];
				reader.readAsDataURL(ePic);
				reader.onload = function(){
					var img = $('#epicShow');
					img.attr('src',this.result);
				}
			});
		});

 	</script>
</body>
</html>