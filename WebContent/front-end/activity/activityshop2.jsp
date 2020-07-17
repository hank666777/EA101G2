<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.activity.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.actparticipation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
	ActivityVO acttVO = (ActivityVO) request.getAttribute("acttVO");
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>活動瀏覽</title>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
</head>
<body>
	<div class="container">
			<div class= "text-center ">
				<Form action="<%=request.getContextPath()%>/front-end/activity/activityshop.do" method="post" >
				<p>活動詳情</p>

				<div>活動編號：<%=actVO.getActno()%></div>
				<div>活動名稱：<%=actVO.getActName()%></div>
				<div>活動描述：<%=actVO.getActDes()%></div>
				<div style="width: auto; height: auto;">
					<img src="<%=request.getContextPath()%>/back-end/activity/activitypicServlet.do?actno=<%=actVO.getActno()%>">
				</div>
				<c:if test="${acttVO != null}">
				<div>現在報名人數：${acttVO.actTalPeo}</div>
				</c:if>
				<c:if test="${acttVO == null}">
				<div>現在報名人數：${actVO.actTalPeo}</div>
				</c:if>
				<div>舉辦日期：<fmt:formatDate value="${actVO.actHoDate}"  type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>
				
				<div>開始報名時間：<fmt:formatDate value="${actVO.actStDate}"  type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>

				<div>截止報名時間：<fmt:formatDate value="${actVO.actEdDate}"  type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>

				<div>報名費用：<%=actVO.getActFee()%></div>

<%-- 				<div>活動狀態：<%=actVO.getActMode()%></div> --%>

				<div>活動最多人數：<%=actVO.getActUpper()%></div>

				<div>活動最少人數：<%=actVO.getActLower()%></div>
				<br>
				<input type="hidden" name="actno" value="<%=actVO.getActno()%>"> 
				<input type="hidden" name="memno" value="${memVO.memno}">
				<input type="hidden" name="actFee" value="<%=actVO.getActFee()%>">
				<input type="hidden" name="action" value="add"> 
				<input type="submit" value="參加活動" class="btn btn-success">
		<div class="row text-center"></div>
</Form>
</div>
</div>
	<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>