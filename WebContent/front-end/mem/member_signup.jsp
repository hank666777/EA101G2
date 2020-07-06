<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>
<head>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>MISS M Sign up</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<!-- 自定義CSS JS-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front-end-index.css">
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!-- font-awesome CSS-->
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

</head>

<style type="text/css">
body {
	text-align: center;
	font-size: 20px;
	font-family: 微軟正黑體;
	background-size: cover;
	margin: auto;
}

form {
	border-style: solid 5px;
	width: 40%;
	border-radius: 40px;
	background-color: white;
	opacity: 0.9;
	margin: 60px auto;
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

td {
	font-size: 30px;
	font-family: 微軟正黑體;
}

.icon {
	border-radius: 50%;
	width: 150px;
	height: 150px;
	border: 1px solid black;
	margin: 15px;
}

.mem_tittle {
	background-color: #F0F0F0;
	font-size: 45px;
	border-radius: 10px;
	width: 200px;
	padding: 15px;
}
</style>

<center>

	<body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">
		<br>
		<span class="mem_tittle"> 加入會員 </span>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do"
			name="form1" enctype="multipart/form-data">

			<input type="hidden" name="action" value="insert"> <img
				class="icon" id="demo" />

			<table>
				<br>
				<tr>
					<td>帳號<img src="${pageContext.request.contextPath}/images/front-end/registImg/user.png"></td>
					<td><input class="inp" type="text" name="mAccount"
						value="<%=(memVO == null) ? "" : memVO.getmAccount()%>" /></td>
				</tr>

				<tr>
					<td>密碼<img src="${pageContext.request.contextPath}/images/front-end/registImg/key.png">
					</td>
					<td><input class="inp" type="password" name="mPw"
						value="<%=(memVO == null) ? "" : memVO.getmPw()%>" /></td>
				</tr>

				<tr>
					<td>頭像<img src="${pageContext.request.contextPath}/images/front-end/registImg/images.png"></td>
					<td><input id="file" type="file" name="mPic"
						value="<%=(memVO == null) ? "" : memVO.getmPic()%>" /></td>
				</tr>

				<tr>
					<td>名字<img src="${pageContext.request.contextPath}/images/front-end/registImg/profile.png">
					</td>
					<td><input class="inp" type="text" name="mName"
						value="<%=(memVO == null) ? "" : memVO.getmName()%>" /></td>
				</tr>

				<tr>
					<td>性別</td>
					<td><img src="${pageContext.request.contextPath}/images/front-end/registImg/man.png">
					         男<input type="radio" name="mGender" value="男" checked>
					            
					    <img src="${pageContext.request.contextPath}/images/front-end/registImg/woman.png">
					         女<input type="radio" name="mGender" value="女">
					</td>
				</tr>

				<tr>
					<td>電話<img src="${pageContext.request.contextPath}/images/front-end/registImg/phone.png">
					</td>
					<td><input class="inp" type="text" name="mPhone"
						value="<%=(memVO == null) ? "" : memVO.getmPhone()%>" /></td>
				</tr>

				<tr>
					<td>信箱<img src="${pageContext.request.contextPath}/images/front-end/registImg/mail4.png">
					</td>
					<td><input class="inp" type="email" name="mEmail"
						value="<%=(memVO == null) ? "" : memVO.getmEmail()%>"></td>
				</tr>

			</table>


			<input type="submit" value="送出" style="width: 90px; height: 40px;">
			<input type="button" value="取消" style="width: 90px; height: 40px;"
				onclick="self.location.href='${pageContext.request.contextPath}/front-end/mem/member_center.jsp'" /><br>


			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color:#F00" class="my-0">${message}</p>
				</c:forEach>
			</c:if>

			<br>

		</FORM>
		</fiedset>

		<script>
			$('#file').change(function() {
				var file = $('#file')[0].files[0];
				var reader = new FileReader;
				reader.onload = function(e) {
					$('#demo').attr('src', e.target.result);
				};
				reader.readAsDataURL(file);
			});
		</script>
		
		
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	</body>
</html>
