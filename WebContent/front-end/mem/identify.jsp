<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>
<head>

<!-- sweetbox -->
<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>

<title>驗證</title>
</head>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>
<style>
form {
	background-color: #FFEEDD;
	width: 40%;
	margin: 1% auto;
	font-size: 30px;
	opacity: 0.975;
	border-radius: 15px;
}

.inp {
	padding: 15px;
	border: #888888;
	border-radius: 15px;
	font-size: 25px;
	font-family: 微軟正黑體;
	margin: 6px;
	width: 55%;
}

.img {
	height: 10px;
	width: 10px;
}

.confirm {
	padding: 6px;
	border: #888888;
	border-radius: 15px;
	font-size: 23px;
	margin: 6px;
	width: 150px;
	height: 50px;
	background-color: #D0D0D0;
	opacity: 0.7;
	font-weight: bold;
}

body {
	background-size: cover;
	text-align: center;
	position: relative;
	margin-top: 20%;
}
</style>



<body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">


	<form METHOD="post"
		ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do"
		name="form1">
		<input type="hidden" name="action" value="identify">

		<tr>
			<td>
				<div>
					驗證 <i class="fa fa-envelope-o" aria-hidden="true"></i>
					   <input class="inp" type="text" name="formCode" value="" placeholder=" 請輸入你的驗證碼" required /> 
					   <input id="demo3" class="confirm" type="submit" name="" value="確認"/>

				</div>
			</td>
		</tr>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>


	</form>

	<script>
	
// 	document.getElementById("demo3").onclick = function() {
// 	    swal("干得好", "你点击了按钮!", "success")
// 	};
   
	</script>


</body>
</html>