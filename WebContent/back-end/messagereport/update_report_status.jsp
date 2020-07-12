<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.messagereport.model.*"%>
<%@ page import="com.messageboard.model.*"%>

<%
	MessageReportVO mrVO = (MessageReportVO) request.getAttribute("mrVO");
	MessageBoardVO mbVO = (MessageBoardVO) request.getAttribute("mbVO");
%>
<!-- <%=mrVO == null%>--${mrVO.memno}--//line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉留言審核 - update_message_input.jsp</title>




<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messagereport/update_report.css" type="text/css" />

<%@ include file="/back-end/back-end-head.jsp" %>

<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>

</head>
<body bgcolor='white'>


<%@ include file="/back-end/back-end-header.jsp" %>

	

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container">
		<div class="wrapper">
			
			<h2 class="text-center">檢舉留言審核:</h2>
			<div class="data_area">
				<FORM METHOD="post" ACTION="messagereport.do" name="form1">
					
					<table  id="post_info">
						<tr>
							<th>遭檢舉留言:${mbVO.postno}</th>
						</tr>
						<tr>
							<th >留言標題:</th>
							<td >${mbVO.postTitle == 'null' ? '': mbVO.postTitle}</td>
						</tr>
						<tr>
							<th >留言分類:</th>
							<td >
								<c:if test="${mbVO.postSort == 1}">閒聊 </c:if>
								<c:if test="${mbVO.postSort == 2}">心得 </c:if>
								<c:if test="${mbVO.postSort == 3}">問題 </c:if>
							</td>
						</tr>
						<tr>
							<th>留言內容:</th>
							<td>${mbVO.postDetail}</td>
						</tr>
						<tr>
							<th>留言時間:</th>
							<td>${mbVO.postTime}</td>
						</tr>
						<tr>
							<th>(留言者)會員編號:</th>
							<td>${mbVO.memno}</td>
						</tr>
						
					</table>
					<div><br></div>
					
					
					<table id="report_info">
						<tr>
							<th class="">檢舉資料</th>
						</tr>
						<tr>
							<th>檢舉編號:</th>
							<td><a />${mrVO.reportno}</td>
						</tr>
						<tr>
							<th>檢舉理由:</th>
							<td>${mrVO.reportDetail}</td>
						</tr>
						<tr>
							<th>留言顯示狀態:<font color=red><b>*</b></font></th>
							<td><input type="radio" name="poststatus" value="0"
								${(mbVO.postStatus==0)?'checked':''}>隱藏 <input
								type="radio" name="poststatus" value="1"
								${(mbVO.postStatus==1)?'checked':''}>顯示</td>
						</tr>
						<tr>
							<th>檢舉處理狀態:</th>
							<td><input type="radio" name="reportstatus" value="0"
								${(mrVO.reportStatus==0)?'checked':''}>未處理 <input
								type="radio" name="reportstatus" value="1"
								${(mrVO.reportStatus==1)?'checked':''}>已處理通過 <input
								type="radio" name="reportstatus" value="2"
								${(mrVO.reportStatus==2)?'checked':''}>已處理未通過</td>
						</tr>

					</table>
					<br> <input type="hidden" name="action" value="update">
					<input type="hidden" name="reportno"
						value="<%=mrVO.getReportno()%>"> <input type="hidden"
						name="reportdetail" value="<%=mrVO.getReportDetail()%>"> <input
						type="hidden" name="reporttime" value="<%=mrVO.getReportTime()%>">



					<input type="hidden" name="postno" value="<%=mbVO.getPostno()%>">
					<input type="hidden" name="posttitle"
						value="<%=mbVO.getPostTitle()%>"> <input type="hidden"
						name="postsort" value="<%=mbVO.getPostSort()%>"> <input
						type="hidden" name="postdetail" value="<%=mbVO.getPostDetail()%>">
					<input type="hidden" name="posttime"
						value="<%=mbVO.getPostTime()%>"> <input type="hidden"
						name="memno" value="<%=mbVO.getMemno()%>"> <input
						type="hidden" name="parentno" value="<%=mbVO.getParentno()%>">

					<input type="submit" value="送出修改">
					<input type="button" value="返回審核列表" 
							onclick="location.href='<%=request.getContextPath()%>/back-end/messagereport/listAllMessageReport.jsp'">
					
				</FORM>

			</div>
		</div>
	</div>

<%@ include file="/back-end/back-end-footer.jsp"%>
	<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>



</html>