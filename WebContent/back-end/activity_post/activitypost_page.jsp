<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>活動推文管理</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row text-center">
			<div class="col">
				<h3>資料查詢:</h3>

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
					<li><a
						href="<%=request.getContextPath()%>/back-end/activity_post/listAllActivitypost.jsp">List</a>
						all actpost. <br>
					<br></li>

					<jsp:useBean id="actPSvc" scope="page"
						class="com.activitypost.model.ActivitypostService" />

					<li>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/activity_post/activitypostServlet.do">
							<b>請選擇活動文章編號</b> <select size="1" name="actPostno">
								<c:forEach var="actPVO" items="${actPSvc.all}">
									<option value="${actPVO.actPostno}">${actPVO.actPostno}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</FORM>
					</li>


				</ul>

				<h3>活動管理</h3>

				<ul>
					<li><a
						href="<%=request.getContextPath()%>/back-end/activity_post/addactivitypost.jsp">ADD</a>
						a new activity</li>
				</ul>

			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>