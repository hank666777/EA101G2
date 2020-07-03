<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>MISS M會員登入</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<!-- 自定義CSS JS-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front-end-index.css">
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!-- font-awesome CSS-->
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

<style type="text/css">
.inp {
	padding: 15px;
	border: 1px solid #888888;
	border-radius: 15px;
	font-size: 25px;
	font-family: 微軟正黑體;
	margin: 6px;
	width: 55%;
}

.inp:focus {
	outline: none;
	border: 1px solid black;
	background: #F0F0F0;
}

.logo {
	margin: -30px auto 20px auto;
}

.add {
	padding: 6px;
	border: 1px solid #888888;
	font-size: 25px;
	position: relative;
	margin-left: 80%;
	margin-top: 3%;
	background-color: black;
	font-weight: bold;
	border-radius: 10px;
}

form {
	background-color: #FFEEDD;
	width: 40%;
	margin: 3% auto;
	opacity: 0.975;
	border-radius: 15px;
	text-align: center;
	height: 82%;
}

.login {
	padding: 6px;
	border: #888888;
	border-radius: 15px;
	font-size: 23px;
	margin: 6px;
	width: 70%;
	height: 60px;
	background-color: #FFA042;
	opacity: 0.7;
	font-weight: bold;
}

body {
	background-size: cover;
}

.div {
	margin: -2% 0px;
}
</style>
<body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">
	
	<div class="container-fluid fixed-top bg-white" style="height:35px; opacity:.8;">
		<div class="row">

			<div class="col">
				<div class="header-user-info">
					<a id="member" href="${pageContext.request.contextPath}/front-end/mem/member_center.jsp"> 
						<i class="fa fa-user"></i> 會員中心
					</a> 
					<a id="cart" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-shopping-cart"></i> 購物車
					</a> 
					<a id="myfavor" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-heart"></i> 追蹤清單
					</a> 
					<a id="menberlogin" style="display: ${(MemVO == null) ? 'display':'none'};"
					href="${pageContext.request.contextPath}/front-end/mem/memberlogin.jsp"> 
						<i class="fa fa-unlock-alt"></i> 會員登入
					</a>
					${MemVO.memno}
<%-- 					<c:if test="${MemVO == null}"> --%>
					
						<a id="" style="display: ${(MemVO != null) ? 'display':'none'};" 
								href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do"> 
							<i class="fa fa-share-square"></i> 會員登出
						</a>
<%-- 					</c:if> --%>
				</div>
			</div>
		</div>
	</div>
	
	
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do">
		<div>
			<input class="add" type="button" style="color: white;" value="加入會員"
				onclick="self.location.href='${pageContext.request.contextPath}/front-end/mem/addMem.jsp'" />
		</div>
		<div class="logo">
			<img src="${pageContext.request.contextPath}/images/logo.png">
		</div>
				帳號<img src="${pageContext.request.contextPath}/images/front-end/registImg/user.png">
				<input class="inp" type="text" name="mAccount" placeholder="請輸入你的帳號..."  />

		<br>

			密碼 
				<img src="${pageContext.request.contextPath}/images/front-end/registImg/key.png">
			
				<input class="inp" type="password" name="mPw"	placeholder="請輸入密碼.."  /> 
				<input type="hidden" name="action" value="memberLogin">

		<br> 
		<br> 
		<input class="login" type="submit" style="color: white;" value="會員登入" />
		<br>
		<%-- 錯誤表列--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: #F00">
				<c:forEach var="message" items="${errorMsgs}">
					${message}<br>
				</c:forEach>
			</font>
		</c:if>
	</form>
	
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>