<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.suggest.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO mem = (MemVO) session.getAttribute("memVO");
	SugestVO sugestVO = (SugestVO) request.getAttribute("sugestVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>意見反應清單</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
			integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>

<style type="text/css">
body {
	text-align: center;
	font-family: 微軟正黑體;
	background-size: cover;
	margin: auto;
}

.form1 {
	border-style: solid 5px;
	width: 40%;
	border-radius: 40px;
	background-color: white;
	opacity: 0.9;
	margin: 60px auto;
}

.site {
	position: relative;
	top: 10px;
	left: 0px;
}

.record {
	font-family: 微軟正黑體;
	font-size: 30px;
	position: absolute;
	top: 15px;
	left: 45px;
	width: 150px;
	height: 70px;
	border-radius: 10px;
}

.suggest_tittle {
	background-color: #F0F0F0;
	font-size: 60px;
	border-radius: 10px;
	width: 200px;
	padding: 10px;
	margin: 10px;
}

.text {
	width: 480px;
	height: 360px;
	margin: 5%;
	font-size: 20px;
	font-family: 微軟正黑體;
	border-radius: 10px;
	border: 2px orange solid;
	padding: 10px;
}

.text:focus {
	outline: none;
	border: 5px orange solid;
}

input {
	padding: 6px;
	border: 1px solid #888888;
	border-radius: 15px;
	font-size: 18px;
	font-family: 微軟正黑體;
	margin: 6px;
}

.name {
	padding: 6px;
	border: 1px solid #888888;
	font-size: 25px;
	position: relative;
	left: -100px;
	margin: auto;
	font-weight: bold;
	border-radius: 10px;
}

.hello {
	font-size: 25px;
	font-family: 微軟正黑體;
	margin: 5%;
}
</style>
<%-- <%@ include file="/front-end/front-end-head.jsp"%> --%>
<body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">
<%-- <%@ include file="/front-end/front-end-header.jsp"%> --%>



	<div class="site">
		<button class="record" onclick="self.location.href='listMySuggest.jsp'">查詢紀錄</button>
	</div>






	<br>
	<br>
	<br>
	<br>
	<span class="suggest_tittle">意見反應單 </span>

	<form class="form1" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/suggest/suggest.do">

		<input type="hidden" name="action" value="insert" />


		<textarea class="text" name="suggestDetail" value="" placeholder="請輸入你的意見內容..." required></textarea>

		<input type="hidden" name="resStatus" value="" />
		<input type="hidden" value=" " name="responseDetail">
		<br>
		<input type="hidden" value="" name="memno">
		<input type="submit" value="送出" class="btn btn-success">
		<input type="button" value="回首頁" class="btn btn-danger" onclick="self.location.href='${pageContext.request.contextPath}/front-end/index.jsp'" />
		<br>
		<br>


	</form>



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->




</html>