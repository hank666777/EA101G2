<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html>
<head>
	<title>MISS M員工資料新增 </title>
	<%@ include file="/back-end/back-end-head.jsp" %>

</head>
<body>
	<%@ include file="/back-end/back-end-header.jsp" %>
<!-- 	<div class="container"> -->
	
		<div class="row justify-content-center">
			<div class="col-xl-7 text-center">
				<div class="alert alert-primary text-center" role="alert">
					<h2>Miss M員工資料新增</h2>
				</div>
			</div>
		</div>
						
		<div class="row justify-content-center">
			<div class="col-xl-7 text-center" style="color:#F00;">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
							${message}<br>
						</c:forEach>
				</c:if>
			</div>
		</div>
					
		<div class="row align-items-center justify-content-center" >
			<div class="col-6 text-center" id="epicPreview">
				<img style="height:150px;" src="${pageContext.request.contextPath}/images/user-circle.png">
			</div>
		</div>	
			
		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/back-end/employee/employee.do" name="form1" enctype="multipart/form-data">
		<div class="row justify-content-center  ">
			<div id="col-7 text-center ">
				<table class="table table-striped table-bordered table-hover  text-center">
					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">帳號</th>
						<td>隨機生成</td>
					</tr>
					
					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">姓名</th>
						<td><input type="TEXT" name="eName" id="eName" size="20" class="form-control" style="background-color:LemonChiffon;"
							value="<%=(employeeVO==null)? "" : employeeVO.geteName()%>" /></td>
					</tr>
					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">電話</th>
						<td><input type="TEXT" name="ePhone" id="ePhone" size="10" class="form-control" style="background-color:LemonChiffon;"
							value="<%= (employeeVO == null)? "" : employeeVO.getePhone()%>"/></td>
					</tr>

					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">信箱</th>
						<td><input type="email" name="eEmail" id="eEmail" size="50" class="form-control" style="background-color:LemonChiffon;"
							value="<%= (employeeVO == null) ? "" : employeeVO.geteEmail()%>"/></td>
					</tr>
					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">圖片</th>
						<td><input type="file" name="ePic" id="ePic"
							class="btn btn-secondary text-center"/></td>
					</tr>
					
					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">職稱</th>
						<td class="align-items-center">
					    <select name="eTitle" id="eTitle" class="custom-select d-block w-100"
					    				style="background-color:LemonChiffon;">
					      <option value="店長">店長</option>
					      <option value="領班">領班</option>
					      <option value="大廚">大廚</option>
					      <option value="二廚">二廚</option>
					      <option value="服務生" selected>服務生</option>
					    </select>
						</td>
					</tr>

					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">狀態</th>
						<td class="align-items-center">
							<select name="eStatus" class="custom-select d-block w-100"
											style="background-color:LemonChiffon;">
								<option value="0">離職</option>
								<option value="1" selected>在職</option>
							</select> 
						</td>
					</tr>
					
<%-- <jsp:useBean id="feaSvc" scope="page" class="com.features.model.FeaturesService"/> --%>
<%-- <jsp:useBean id="perSvc" scope="page" class="com.permission.model.PermissionService"/> --%>
					<tr>
						<th class="text-nowrap align-middle table-primary" scope="col">權限</th>
						<td class="align-items-center">
							<div class="container">
								<div class="row justify-content-md-center">
									<c:forEach var="feaVO" items="<%=feaSvc.getAll() %>">
										<div class="" style="width:50%;">
											<label><input type="checkbox" class="form-check-input" name="feanos" 
											value="${feaVO.feano}">${feaVO.feaName}</label>
											<br/>
										</div>
									</c:forEach>
								</div>
							</div>
						</td>
					</tr>
						
				</table>
			</div>
		</div>
		
		<div class="row justify-content-center">
			<input type="hidden" name="action" value="insert"> 
			<input class="btn btn-outline-success" type="submit" id="sendbtn" value="送出新增" >
			<span id="magicspan" class="badge badge-success">.</span>
		</div>
		</FORM>
<!-- 	</div> -->
	<%@ include file="/back-end/back-end-footer.jsp"%>
	<script>
		//神奇小按鈕	
		$('#magicspan').on('click',function(){
			$('#eName').val('羊駝');
			$('#ePhone').val('0987654321');
			$('#eEmail').val('fengptt47@gmail.com');
		}); 
	
		//按鈕鎖定，避免重複送出
		$('#sendbtn').click(function(){
			var btn = this;
			setTimeout(function(){
				btn.disabled = true;
			},50);
		});
	
		//預覽圖片
		$('#ePic').on('change',function(){
			if(typeof(FileReader) !== "underfined"){
				var epicPreview = $('#epicPreview');
				epicPreview.empty();
				var reader = new FileReader();
				
				reader.onload = function(e){
					$("<img />",{
						"src": e.target.result,
						"class":"thumb-image" ,
						"style": "border-radius:50%; width:150px; height:150px; text-align: center; margin-bottom:15px;"
					}).appendTo(epicPreview);
				}
				epicPreview.show();
				reader.readAsDataURL($(this)[0].files[0]);
			  } else {
			    alert("This browser does not support FileReader.");
			  }
		});
 	</script>

</body>
</html>