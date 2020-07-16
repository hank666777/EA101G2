<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.activitypost.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivitypostVO actPVO = (ActivitypostVO) request.getAttribute("actPVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>活動推文資料 - listOneactivitypost.jsp</title>

<style>
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
	
	.aligncenter{
		text-align:left;
	}
	
</style>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row text-center">
			<div class="card">
				<h3>活動資料</h3>
				<h4 class="aligncenter">
					<a href="<%=request.getContextPath()%>/back-end/activity_post/activitypost_page.jsp">回首頁</a>
				</h4>
				<table>
					<tr>
						<th class="thact">活動文章編號</th>
						<th class="thact">活動編號</th>
						<th class="thact">會員編號</th>
						<th class="thact">活動推文日期</th>
						<th class="thact">活動推文內容</th>
						<th class="thact">活動推文照片</th>
					</tr>
					<tr>
						<td class="tdact"><%=actPVO.getActPostno()%></td>
						<td class="tdact"><%=actPVO.getActno()%></td>
						<td class="tdact"><%=actPVO.getMemno()%></td>
						<td class="tdact"><%=actPVO.getActPostDate()%></td>
						<td class="tdact"><%=actPVO.getActPostCon()%></td>
						<td class="tdact"><img src="<%=request.getContextPath()%>/back-end/activity_post/activitypostpicServlet.do?actPostno=${actPVO.actPostno}" width="300px" height="250px"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>