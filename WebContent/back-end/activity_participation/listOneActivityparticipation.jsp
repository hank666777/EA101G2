<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.actparticipation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ParticipationVO PVO = (ParticipationVO) request.getAttribute("PVO");
%>

<html>
<head>
<title>活動報名紀錄資料</title>
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
<!-- 	<div class="container"> -->
<%-- 		<div class="row> --%>
			<div class="card">
				
				<h3>活動報名紀錄資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/activity_participation/activityparticipation_page.jsp">回首頁</a>
				</h4>

				<table>
					<tr>
						<th class="thact">活動報名編號</th>
						<th class="thact">會員編號</th>
						<th class="thact">活動編號</th>
						<th class="thact">活動報名時間</th>
						<th class="thact">活動報名人數</th>
						<th class="thact">報名總費用</th>
					</tr>
					<tr>
						<td class="tdact"><%=PVO.getAvPartno()%></td>
						<td class="tdact"><%=PVO.getMemno()%></td>
						<td class="tdact"><%=PVO.getActno()%></td>
						<td class="tdact"><%=PVO.getActPatTime()%></td>
						<td class="tdact"><%=PVO.getActParEnr()%></td>
						<td class="tdact"><%=PVO.getActTalFee()%></td>
					</tr>
				</table>
			</div>
<!-- 		</div> -->
<!-- 	</div> -->

	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>