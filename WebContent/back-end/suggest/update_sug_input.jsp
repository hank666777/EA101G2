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



<style type="text/css">

table {
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	font-family:微軟正黑體;
	width: 60%;
	border: 1px solid #CCCCFF;
}

table, tr, td {
	border: 1px solid #CCCCFF;
	margin:6px;
}

td{
  height: 50px;
  width:100px;
  padding: 15px;  
}

input {
	padding: 6px;
	border: 1px solid #888888;
	border-radius: 15px;
	font-size: 18px;
	font-family: 微軟正黑體;
	margin: 6px;
}

.inp:focus {
	outline: none;
	border: 1px solid #FFE5B5;
	background: #FFE5B5;
}

.icon {
	border-radius: 50%;
	width: 150px;
	height: 150px;
	border: 1px solid black;
	margin: 15px;
}
</style>
</head>

<%@ include file="/back-end/back-end-head.jsp" %>
<center>
<body bgcolor='white'>
<%@ include file="/back-end/back-end-header.jsp" %>
				
      <br><br>


	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/suggest/suggest.do" name="form1">
				
		
		<table>
		<input type="hidden" name="action" value="back_update"> 

            <tr>
				<td>意見反應編號</td>
				<td><%=sugestVO.getSuggestno()%></td>
			</tr>

			<tr>
				<td>反應時間</td>
				<td><input type="hidden" name="suggestDate" size="45"
					value="${sugestVO.suggestDate}"  />${sugestVO.suggestDate}</td>
			</tr>

			<tr>
				<td>反應內容</td>
				<td><input type="hidden" name="suggestDetail" size="45"
					value="<%=sugestVO.getSuggestDetail()%>" required /><%=sugestVO.getSuggestDetail()%></td>
			</tr>
			
			<tr>
				<td>反應狀態</td>
				<td><input type="hidden" name="resStatus" size="45"
					value="<%=sugestVO.getResStatus()%>"  /><%=sugestVO.getResStatus()%></td>
			</tr>
			
			<tr>
				<td>回應內容</td>
				<td><input class="inp" type="text" name="responseDetail" size="45"
					value="<%=sugestVO.getResponseDetail()%>" required /></td>
			</tr>

            <tr>
				<td>會員編號</td>
				<td><input type="hidden" name="memno" size="45"
					value="<%=sugestVO.getMemno()%>" /><%=sugestVO.getMemno()%></td>
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