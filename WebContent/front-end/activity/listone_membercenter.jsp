<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.actparticipation.model.*"%>

<%
// 	ParticipationService PVS = new ParticipationService();
// 	List<ParticipationVO> partlist = PVS.getAll();
// 	pageContext.setAttribute("partlist", partlist);
%>
<html>
<head>
<title>活動報名紀錄</title>
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
  color:block;
  background-color:white;
  text-align:center;/** 设置水平方向居中 */
  vertical-align:middle;/** 设置垂直方向居中 */
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
h4{
type:center;
}
</style>
</head>
<%-- <%@ include file="/front-end/front-end-head.jsp"%> --%>
<body>
<%-- <%@ include file="/front-end/front-end-header.jsp"%> --%>
<%-- <%@ include file="/front-end/front-end-header2.jsp"%> --%>
<div id="container" class="container" style="margin:auto; margin-top:30px;">
	<div class="row">
		<div class="col text-center"><p class="h2">活動報名紀錄<p></div>
	</div>
	<div class="row">
		<table class="table table-sm text-nowrap table-hover info text-center
									shadow-lg p-3 mb-5 bg-white rounded">
				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<font style="color: red">${message}</font><br>
					</c:forEach>
				</c:if>
					<caption></caption>
				<thead>
					<tr class="text-center">
						<th>活動報名編號</th>
<!-- 						<th>會員編號</th> -->
						<th>活動編號</th>
						<th>活動報名時間</th>
						<th>活動報名人數</th>
						<th>報名總費用</th>
					</tr>
				</thead>

					<jsp:useBean id="actPaSvc" scope="page" class="com.actparticipation.model.ParticipationService"/>

					<c:forEach var="participationVO" items="${actPaSvc.all}">
						<c:if test = "${memVO.memno==participationVO.memno}">
						<tr class="text-center">
							<td align="center">${participationVO.avPartno}</td>
<%-- 							<td>${participationVO.memno}</td> --%>
							<td align="center">${participationVO.actno}</td>
							<td align="center"><fmt:formatDate value="${participationVO.actPatTime}"  type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							<td align="center">${participationVO.actParEnr}</td>
							<td align="center">${participationVO.actTalFee}</td>
						</tr>
						</c:if>
					</c:forEach>

				</table>

			</div>
		</div>

<%-- 	<%@ include file="/front-end/front-end-footer.jsp"%> --%>
</body>
</html>