<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.util.*"%>
<html lang="zh-Hant-TW">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Miss M員工登入</title>
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nprogress.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- font-awesome CDN-->

<!-- test -->
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
}

.container{
	margin-top:100px;
	taxt-align:center;
}

div{
	margin:15px;
}
@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<body>

<form class="form-signin" action="${pageContext.request.contextPath}/employeelogin.do" name="form2" method="post">
	<div class="container justify-content-md-center">
		<div class="row justify-content-md-center">
			<div class="col-md-5">
			
				<div class="row my-0">
					<img class="mb-4 rounded mx-auto d-block" src="${pageContext.request.contextPath}/images/logo.png" alt="MISS-M logo"
						width="150" height="150">
				</div>
				
				<div class="row justify-content-center" style="margin-top: 0px; margin-bottom: 10px;">
					<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
				</div>
				
				<div class="row">
					<input class="form-control" type="text" id="eAccount" name="eAccount" 
								 style="background-color:LemonChiffon;" placeholder="請輸入帳號" autofocus> 
				</div>
				
				<div class="row">
					<input class="form-control" type="password" id="ePw" name="ePw" 
								 style="background-color:LemonChiffon;" placeholder="請輸入密碼">
				</div>
				
<!-- 				<div class="row my-0"> -->
<!-- 					<label><input type="checkbox" value="remember-me">記住我</label> -->
<!-- 				</div> -->
				
				<div class="row">
					<img id="imageCheckCode" width=100 height=40 
						src="${pageContext.request.contextPath}/checkcode.do">
					<input class="form-control" type="text" name="checkCode"
								 style="background-color:LemonChiffon;" placeholder="請輸入驗證碼"> 
				</div>
				
				<div class="row my-0 text-danger justify-content-md-center" >
					<c:if test="${! empty errorMsgs}" var="message">
						<c:forEach var="message" items="${errorMsgs}">
							${message}<br>
						</c:forEach>
					</c:if>
				</div>
				
				<div class="row">
					<input class="btn btn-lg btn-primary btn-block" type="submit" value="登 入" id="sendbtn" >
					<input type="hidden" name="action" value="login" >
				</div>
				
				<div class="row justify-content-center">
					<p class="text-muted">&copy; 2020</p>
				</div>
				
			</div>
		</div>
	</div>
</form>


	<script>
	$('#sendbtn').on('click',(function(){
		var btn = this;
		setTimeout(function(){
			btn.disabled = true;
		},50);
	}));
	//click可更換驗證圖片，增加timestamp
		$(function(){
			$('#imageCheckCode').on('click',function(){
				this.src = "${pageContext.request.contextPath}" + "/checkcode.do?time=" + new Date().getTime();
			});
		});
	</script>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>