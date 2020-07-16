<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.suggest.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   // List<SugestVO> suggestlist = (List<SugestVO>) request.getAttribute("suggestlist");   
%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<%
  SugService sugSvc = new SugService();
  List<SugestVO> list = sugSvc.getAll();
  pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>意見反應查詢</title>

<style type="text/css">
.suggest_tittle {
	background-color: #F0F0F0;
	font-size: 45px;
	border-radius: 10px;
	width: 200px;
	padding: 15px;
}

body {
	background-size: cover;
}

.list {

	text-align: center;

}

form {
	border-style: solid 5px;
	width: 45%;
	border-radius: 40px;
	background-color: white;
	opacity: 0.9;
	margin: 60px auto;
}

.back_addSuggest{
  border-radius: 15px;
  font-size: 20px;
  font-family: 微軟正黑體;
}
</style>

</head>

<center>

<body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">
        <br>
		<span class="suggest_tittle">意見反應單</span>
<form>
        <br><br>
		<table class="list" border="1">
		
			<tr>	
			    <td>反應單號</td>		
				<td>反應日期</td>
				<td>反應內容</td>
				<td>反應狀態</td>
				<td>回應內容</td>
				<td>會員編號</td>
				
			</tr>
		<c:forEach var="sugestVO" items="${list}">
		
		<c:if test="${memVO.memno == sugestVO.memno }" >
			
			<tr>
			    <td>${sugestVO.suggestno }</td>               
                <td>${sugestVO.suggestDate }</td>                             
				<td>${sugestVO.suggestDetail }</td>
				<td>${sugestVO.resStatus }</td>
                <td>${sugestVO.responseDetail }</td>
				<td>${sugestVO.memno }</td>
	             
			</tr>
			</c:if>
		</c:forEach>
		</table>
		<br>
			   <h4><a href="${pageContext.request.contextPath}/front-end/suggest/addSuggest.jsp"><u>回表單</u></a></h4>

		<br><br>	
</form>
     
	</body>
</html>