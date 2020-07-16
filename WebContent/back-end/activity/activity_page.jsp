<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>活動管理</title>

<style>

body{
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}

	.actlabol{
		text-align:center;
		margin-bottom:30px;
	}

	.card{
 		opacity:0.9; 
 		width:475px; 
 		margin:50px auto; 
		padding:50px; 
	}

	.shorthyper{
		display: inline;
		margin-left: 5px;
	}

</style>
</head>
<%@ include file="/back-end/back-end-head.jsp"%>
<body>
		<%@ include file="/back-end/back-end-header.jsp"%>
	
	<div class="container">
		<div class="row" style="margin-top:150px;">
			<div class="card">
				<h4 class="actlabol">資料查詢</h4>

				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				<ul>
					<li class="shorthyper"><a href="<%=request.getContextPath()%>/back-end/activity/addactivity.jsp">新增活動</a></li>
					<li class="shorthyper"><a href="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp">活動查詢</a></li>
				</ul>
				
				<ul class="actlistline">
					
					<jsp:useBean id="actSvc" scope="page"
						class="com.activity.model.ActivityService" />

<!-- 					<li> -->
						
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/back-end/activity/activityServlet.do"> --%>
<!-- 							<b>請選擇活動類別編號</b> <select size="1" name="actno"> -->
<%-- 								<c:forEach var="actVO" items="${actSvc.all}"> --%>
<%-- 									<option value="${actVO.actno}">${actVO.actTyno} --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 							<input type="submit" value="送出"> -->
<!-- 						</FORM> -->
						
<!-- 					</li> -->

					<li>
						
						<FORM METHOD="post"
							
							ACTION="<%=request.getContextPath()%>/back-end/activity/activityServlet.do">
							<b>請選擇活動編號</b> <select size="1" name="actno">
								<c:forEach var="actVO" items="${actSvc.all}">
									<option value="${actVO.actno}">${actVO.actno}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
							
						</FORM>
					
					</li>
					
				</ul>
			</div>	
		</div>
	</div>
	
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>