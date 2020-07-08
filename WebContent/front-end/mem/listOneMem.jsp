<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	MemVO SmemVO = (MemVO) session.getAttribute("memVO");
%>


<html>
<head>
<title>Miss M會員資料</title>

<%@ include file="/front-end/front-end-head.jsp"%>

<style type="text/css">
body {
	/* 	text-align: center; */
	/* 	font-size: 20px; */
	/* 	font-family: 微軟正黑體; */
	/* 	background-size: cover; */
	/* 	margin:auto; */
	
}

form {
	border-style: solid 5px;
	width: 40%;
	border-radius: 40px;
	background-color: white;
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
	font-size: 25px;
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
}
</style>

</head>
<center>
	<body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">
		<%-- 	<%@ include file="/front-end/front-end-header.jsp"%> --%>

		<form>
			<table>
				<img class="icon" id="demo" src="${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
				<tr>
					<td>會員帳號:</td>
					<td>
						<input type="hidden" name="mAccount" value="<%=SmemVO.getmAccount()%>" /><%=SmemVO.getmAccount()%></td>
				</tr>
				<tr>
					<td>會員密碼:</td>
					<td>
						<input type="hidden" name="mPw" value="<%=SmemVO.getmPw()%>" required /><%=SmemVO.getmPw()%></td>
				</tr>
				<tr>
					<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/front-end/mem/mem.mPic">
						<td></td>
					</FORM>
				</tr>
				<tr>
					<td>會員姓名:</td>
					<td>
						<input class="inp" type="hidden" name="mName" value="<%=SmemVO.getmName()%>" required /><%=SmemVO.getmName()%></td>
				</tr>
				<tr>
					<td>會員性別:</td>
					<td>
						<input type="hidden" name="mGender" value="<%=SmemVO.getmGender()%>" /><%=SmemVO.getmGender()%></td>

				</tr>
				<tr>
					<td>會員電話:</td>
					<td>
						<input class="inp" type="hidden" name="mPhone" value="<%=SmemVO.getmPhone()%>" required /><%=SmemVO.getmPhone()%></td>
				</tr>
				<tr>
					<td>會員mail:</td>
					<td>
						<input class="inp" type="hidden" name="mEmail" value="<%=SmemVO.getmEmail()%>" required /><%=SmemVO.getmEmail()%></td>
				</tr>
				<tr>
					<td>註冊日期:</td>
					<td>
						<input class="inp" type="hidden" name="mRegDate" value="<%=SmemVO.getmRegDate()%>" /><%=SmemVO.getmRegDate()%></td>
				</tr>

			</table>
			<a href="${pageContext.request.contextPath}/front-end/mem/update_mem_input.jsp"><h2><span class="badge badge-pill badge-warning">修改資料</span></h2></a>
<!-- 			<input type="button" value="修改資料" style="width: 110px; height: 50px;" -->
<!-- 			 onclick="self.location.href=''" /> -->
			<br>
		</form>

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

		<%-- 	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script> --%>
		<%-- 	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>
	</body>
</html>