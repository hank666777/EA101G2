<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	MemVO SmemVO = (MemVO) session.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

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
	<body background="../../images/front-end/registImg/backgound.jpg">

		<br>
		<span class="mem_tittle"> 會員資料修改 </span>	

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do"
			name="form1" enctype="multipart/form-data">
             
            <input type="hidden" name="action" value="update"/>
  
    <img class="icon" id="demo" src="<%=request.getContextPath()%>/front-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
			
			<table>

				<br>
				<tr>
					<td>會員帳號:</td>
					<td><input type="hidden" name="mAccount"
						value="<%=SmemVO.getmAccount()%>" /><%=SmemVO.getmAccount()%></td>
				</tr>
				<tr>
					<td>會員密碼:</td>
					<td><input class="inp" type="password" name="mPw"
						value="<%=SmemVO.getmPw()%>" required /></td>
				</tr>
				<tr>
					<td>大頭照:</td>
					<td><input id="file" type="file" name="mPic"
						value="<%=SmemVO.getmPic()%>" /></td>				
				</tr>
				<tr>
					<td>會員姓名:</td>
					<td><input class="inp" type="TEXT" name="mName"
						value="<%=SmemVO.getmName()%>" required /></td>
				</tr>
				<tr>
					<td>會員性別:</td>
					<td>男<input type="radio" name="mGender" value="男" checked="true"> 
						女<input type="radio" name="mGender" value="女"></td>
				</tr>
				<tr>
					<td>會員電話:</td>
					<td><input class="inp" type="TEXT" name="mPhone"
						value="<%=SmemVO.getmPhone()%>" required /></td>
				</tr>
				<tr>
					<td>會員mail:</td>
					<td><input class="inp" type="email" name="mEmail"
						value="<%=SmemVO.getmEmail()%>" required /></td>
				</tr>
				<tr>
					<td>註冊日期:</td>
					<td><input class="inp" type="hidden" name="mRegDate"
						value="<%=SmemVO.getmRegDate()%>" /><%=SmemVO.getmRegDate()%></td>
				</tr>
				<tr>
					<!-- 		<td>會員狀態:</td> 隱藏會員狀態欄位 -->
					<td><input type="hidden" name="mStatus"
						value="<%=SmemVO.getmStatus()%>" /></td>
				</tr>


			</table>
		        <input type="hidden" name="memno" value="<%=SmemVO.getMemno()%>"> 
				<input type="submit" value="送出修改"> 
				<input type="button" value="取消" style="width: 90px; height: 40px;"
				       onclick="self.location.href='member_center.jsp'" /><br>
				       
				 <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<br>      		       

		</FORM>

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

	</body>



	</script>
</html>