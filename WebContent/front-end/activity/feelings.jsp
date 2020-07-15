<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.activitypost.model.*"%>
<%
	ActivitypostService actPSvc = new ActivitypostService();
	List<ActivitypostVO> list = actPSvc.getAll();
	pageContext.setAttribute("list", list);
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<html>
<head>
<title>活動心得紀錄資料</title>
<style>
	table.TB_COLLAPSE {
  width:100%;
  border-collapse:collapse;
}
table.TB_COLLAPSE caption {
  padding:10px;
  font-size:24px;
  background-color:#f3f6f9;
}
table.TB_COLLAPSE thead th {
  padding:5px 0px;
  color:#fff;
  background-color:#915957;
  text-align:center;/** 设置水平方向居中 */
  vertical-align:middle/** 设置垂直方向居中 */
}
table.TB_COLLAPSE tbody td {
  padding:5px 0px;
  color:#555;
  text-align:center;
  background-color:#fff;
  border-bottom:1px solid #915957;
}
table.TB_COLLAPSE tfoot td {
  padding:5px 0px;
  text-align:center;
  background-color:#d6d6a5;
}

table.TB_SEPARATE {
  width:100%;
  border-collapse:separate; /*邊框沒有合併*/
}
</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
	<div class="container">
		<div class="row text-center">
			<div class="col">
		<h4>
			<a href="<%=request.getContextPath()%>/front-end/activity/front_page.jsp">回首頁</a>
		</h4>

			<h3>活動心得紀錄</h3>
				<table class="TB_COLLAPSE">
				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<caption></caption>

				<thead>
					<tr>
						<th style="width:150px;">活動文章編號</th>
						<th style="width:150px;">活動編號</th>
						<th style="width:150px;">會員編號</th>
						<th style="width:200px;">活動推文日期</th>
						<th style="width:400px;">活動推文內容</th>
						<th style="width:400px;">活動推文照片</th>

					</tr>
				</thead>
					<jsp:useBean id="actPaSvc" scope="page" class="com.activitypost.model.ActivitypostService"/>
					
					<c:forEach var="activitypostVO" items="${actPaSvc.all}" >
<%-- 						<c:if test = "${memVO.memno==activitypostVO.memno}"> --%>
						<tr>
							<td align="center">${activitypostVO.actPostno}</td>
							<td align="center">${activitypostVO.actno}</td>
							<td align="center">${activitypostVO.memno}</td>
							<td align="center"><fmt:formatDate value="${activitypostVO.actPostDate}"  type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							<td align="center">${activitypostVO.actPostCon}</td>
							<td align="center"><img
								src="<%=request.getContextPath()%>/back-end/activity_post/activitypostpicServlet.do?actPostno=${activitypostVO.actPostno}"></td>
						</tr>
<%-- 						</c:if> --%>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>
	<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>