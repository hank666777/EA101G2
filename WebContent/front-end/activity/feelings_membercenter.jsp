<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.activitypost.model.*"%>
<%
	ActivitypostService actSvc = new ActivitypostService();
	List<ActivitypostVO> actlist = actSvc.getAll();
	pageContext.setAttribute("actlist", actlist);
	MemVO mVO = (MemVO) session.getAttribute("memVO");
%>
<html>
<head>
<title>活動心得紀錄資料</title>
<style>
	table.TB_COLLAPSE {
  width:100%;
  border-collapse:white;
}
table.TB_COLLAPSE caption {
  padding:10px;
  font-size:24px;
  background-color:white;
}
table.TB_COLLAPSE thead th {
  padding:5px 0px;
  color:black;
  background-color:white;
  text-align:center;/** 设置水平方向居中 */
  vertical-align:middle/** 设置垂直方向居中 */
}
table.TB_COLLAPSE tbody td {
font-size:15px;
  padding:5px 0px;
  color:#555;
  text-align:center;
  background-color:white;
  border-bottom:1px solid #915957;
}
table.TB_COLLAPSE tfoot td {
/* font-size:15px; */
  padding:5px 0px;
  text-align:center;
  background-color:white;
}

table.TB_SEPARATE {
  width:100%;
  border-collapse:separate; /*邊框沒有合併*/
}
img{
	width:100px;
	height:50px;
}
</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%-- <%@ include file="/front-end/front-end-header.jsp"%> --%>
<%-- <%@ include file="/front-end/front-end-header2.jsp"%> --%>
	<div class="container">
		<div class="row text-center">
			<div class="col">
			<h3>活動心得紀錄</h3>
				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
<div style="overflow:auto;height:600px;">
				<table class="TB_COLLAPSE">
				<caption></caption>

				<thead>
					<tr>
						<th style="width:150px">活動文章編號</th>
						<th style="width:150px">活動編號</th>
<!-- 						<th style="width:150px;">會員編號</th> -->
						<th style="width:200px">活動推文日期</th>
						<th style="width:400px">活動推文內容</th>
						<th style="width:200px">活動推文照片</th>

					</tr>
				</thead>
					<jsp:useBean id="actPSvc" scope="page" class="com.activitypost.model.ActivitypostService"/>
					
					<c:forEach var="activitypostVO" items="${actPSvc.all}" >
						<c:if test = "${memVO.memno==activitypostVO.memno}">
						<tr>
							<td align="center"><b>${activitypostVO.actPostno}</b></td>
							<td align="center"><b>${activitypostVO.actno}</b></td>
<%-- 							<td align="center">${activitypostVO.memno}</td> --%>
							<td align="center"><b><fmt:formatDate value="${activitypostVO.actPostDate}"  type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></b></td>
							<td align="center"><b>${activitypostVO.actPostCon}</b></td>
							<td align="center"><img src="<%=request.getContextPath()%>/front-end/activity_post/activitypostpicServlet.do?actPostno=${activitypostVO.actPostno}"></td>
						</tr>
						</c:if>
					</c:forEach>
				</table>



				</div>
			</div>
		</div>
	</div>
<%-- 	<%@ include file="/front-end/front-end-footer.jsp"%> --%>
</body>
</html>