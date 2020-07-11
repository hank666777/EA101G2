<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.messageboard.model.*"%>
<%@ page import="com.messagereport.model.*"%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉資料新增</title>

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
	width: 80%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
textarea#detail{
width="800px" height="800px;"
}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
</head>
<body bgcolor='white'>

	
	<h3>資料新增:</h3>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/messagereport/messagereport.do" name="form1">
			<%-- 錯誤表列 --%>
						<div class="errormsgs" style="padding-left:2%;text-align:center">
							<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							
								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red">${message}</p>
								</c:forEach>
							
							</c:if>
						</div>
			<tr>
				<td>檢舉原因:</td>
				<td><input type="TEXT" name="reportdetail" size="45"
					placeholder="請填入檢舉原因"
					value="" reqired/></td>
			</tr>
			<tr>
				<td>會員編號(暫先):</td>
				<td><input type="TEXT" name="memno" size="45"
					placeholder="請填入會員編號"
					value="" reqired/></td>
			</tr>			
		</table>
		<br>
		<input type="hidden" name="postno" value="${mbVO.postno}">
		<input type="hidden" name="action" value="addReport">
		<input type="submit" value="送出檢舉">	</FORM>
		
		

<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>		
</body>
</html>