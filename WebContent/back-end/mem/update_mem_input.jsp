<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

<style type="text/css">

table {
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	font-family:微軟正黑體;
	width: 40%;
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mem/mem.do" name="form1"
		enctype="multipart/form-data">
		
    <img class="icon" id="demo" src="<%=request.getContextPath()%>/back-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
		
		<br><br>
		<table>
		<input type="hidden" name="action" value="back_update"> 
			<tr>
				<td>會員編號</td>
				<td><%=memVO.getMemno()%></td>
			</tr>
			<tr>
				<td>會員帳號</td>
				<td><input type="hidden" name="mAccount" size="25"
					value="<%=memVO.getmAccount()%>" required /><%=memVO.getmAccount()%></td>
			</tr>
			<tr>
				<td>會員密碼</td>
				<td><input class="inp" type="TEXT" name="mPw" size="25"
					value="<%=memVO.getmPw()%>" required /></td>
			</tr>
			<tr>
				<td>大頭照</td>
				<td><input id="file" type="file" name="mPic"  value="<%=memVO.getmPic()%>" /></td>
			</tr>
			<tr>
				<td>會員姓名</td>
				<td><input class="inp" type="TEXT" name="mName" size="25"
					value="<%=memVO.getmName()%>"required /></td>
			</tr>
			<tr>
				<td>會員性別</td>
				<td>男<input type="radio" name="mGender" value="男" checked="true"> 
						女<input type="radio" name="mGender" value="女"></td>
			</tr>
			<tr>
				<td>會員電話</td>
				<td><input class="inp" type="TEXT" name="mPhone" size="25"
					value="<%=memVO.getmPhone()%>" required /></td>
			</tr>
			<tr>
				<td>會員mail</td>
				<td><input class="inp" type="TEXT" name="mEmail" size="25"
					value="<%=memVO.getmEmail()%>"required /></td>
			</tr>
			<tr>
				<td>註冊日期</td>
				<td><input type="hidden" name="mRegDate" size="45"
					value="<%=memVO.getmRegDate()%>" /><%=memVO.getmRegDate()%></td>
			</tr>
			<tr>
			    <td>會員狀態</td>			    
				<td><input class="inp" type="TEXT" name="mStatus" size="1"
					value="<%=memVO.getmStatus()%>" /></td>
			</tr>


		</table>
		
		<br> 
		<input type="hidden" name="memno" value="<%=memVO.getMemno()%>"> 
		<input type="submit" value="送出修改">
		<br>
		<br>
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

</html>