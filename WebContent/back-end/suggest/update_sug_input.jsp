<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.suggest.model.*" %>
<%
  SugestVO sugestVO = (SugestVO) request.getAttribute("sugestVO");
%>
<!DOCTYPE html>
<html>
<head>

<title>會員反應單修改</title>

</head>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

				<h4><a href="listAllSug.jsp">回會員列表</a></h4><br><br>


	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/suggest/suggest.do" name="form1">
				
		
		<table>
		<input type="hidden" name="action" value="back_update"> 

            <tr>
				<td>意見反應編號:<font color=red><b>*</b></font></td>
				<td><%=sugestVO.getSuggestno()%></td>
			</tr>

			<tr>
				<td>意見反應時間:</td>
				<td><input type="hidden" name="suggestDate" size="45"
					value="${sugestVO.suggestDate}"  />${sugestVO.suggestDate}</td>
			</tr>

			<tr>
				<td>反應內容:</td>
				<td><input type="TEXT" name="suggestDetail" size="45"
					value="<%=sugestVO.getSuggestDetail()%>" required /></td>
			</tr>
			
			<tr>
				<td>反應狀態:</td>
				<td><input type="hidden" name="resStatus" size="45"
					value="<%=sugestVO.getResStatus()%>"  /><%=sugestVO.getResStatus()%></td>
			</tr>
			
			<tr>
				<td>意見回應內容:</td>
				<td><input type="TEXT" name="responseDetail" size="45"
					value="<%=sugestVO.getResponseDetail()%>" required /></td>
			</tr>

            <tr>
				<td>會員編號:</td>
				<td><input type="hidden" name="memno" size="45"
					value="" /><%=sugestVO.getMemno()%></td>
			</tr>
		</table>
		
		<input type="hidden" name="suggestno" value="<%=sugestVO.getSuggestno()%>"> 
		<input type="submit" value="送出修改">
		
		 <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
				
	</FORM>
		
<body>

</body>
</html>