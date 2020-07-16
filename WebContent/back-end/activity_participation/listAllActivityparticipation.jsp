<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actparticipation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>

<%
	ParticipationService PVS = new ParticipationService();
	List<ParticipationVO> list = PVS.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有活動報名紀錄</title>
<style>
	
	body{
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}
	
	
	.thact{
		text-align: center;
		padding: 5px;
		border: orange 2px solid;
	}
	
	.tdact{
		text-align: center;
		padding: 5px;
		border: orange 2px solid;
	}

	.card{
		opacity:0.9;
		width:1200px;
		margin:50px auto;
		padding:50px;
	}

	.clicksure{
		text-align:left;
		margin-top:10px;
	}

</style>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row" text-center">
			<div class="card" style="padding-right: 170;">
							<h3>所有活動報名紀錄資料</h3>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back-end/activity_participation/activityparticipation_page.jsp">回首頁</a>
							</h4>

				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<table>
					<tr>
						<th class="thact">活動報名編號</th>
						<th class="thact">會員編號</th>
						<th class="thact">活動編號</th>
						<th class="thact">活動報名時間</th>
						<th class="thact">活動報名人數</th>
						<th class="thact">報名總費用</th>
<!-- 						<th class="thact">修改</th> -->
<!-- 						<th class="thact">刪除</th>					 -->
						<th class="thact" colspan ="2"></th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="PVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1 %>">
						<tr>
							<td class="tdact">${PVO.avPartno}</td>
							<td class="tdact">${PVO.memno}</td>
							<td class="tdact">${PVO.actno}</td>
							<td class="tdact">${PVO.actPatTime}</td>
							<td class="tdact">${PVO.actParEnr}</td>
							<td class="tdact">${PVO.actTalFee}</td>

							<td class="tdact">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_participation/actparticipationServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="avPartno" value="${PVO.avPartno}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td class="thact">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_participation/actparticipationServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="avPartno" value="${PVO.avPartno}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>