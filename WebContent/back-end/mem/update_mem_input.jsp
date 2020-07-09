<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

				<h4><a href="listAllMem.jsp">回會員列表</a></h4><br><br>


	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mem/mem.do" name="form1"
		enctype="multipart/form-data">
		
		    <img class="icon" id="demo" src="<%=request.getContextPath()%>/back-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
		
		
		<table>
		<input type="hidden" name="action" value="back_update"> 
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=memVO.getMemno()%></td>
			</tr>
			<tr>
				<td>會員帳號:</td>
				<td><input type="hidden" name="mAccount" size="45"
					value="<%=memVO.getmAccount()%>" required /><%=memVO.getmAccount()%></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="TEXT" name="mPw" size="45"
					value="<%=memVO.getmPw()%>" required /></td>
			</tr>
			<tr>
				<td>大頭照:</td>
				<td><input type="file" name="mPic" value="<%=memVO.getmPic()%>" /></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="mName" size="45"
					value="<%=memVO.getmName()%>"required /></td>
			</tr>
			<tr>
				<td>會員性別:</td>
				<td>男<input type="radio" name="mGender" value="男" checked="true"> 
						女<input type="radio" name="mGender" value="女"></td>
			</tr>
			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="mPhone" size="45"
					value="<%=memVO.getmPhone()%>" required /></td>
			</tr>
			<tr>
				<td>會員mail:</td>
				<td><input type="TEXT" name="mEmail" size="45"
					value="<%=memVO.getmEmail()%>"required /></td>
			</tr>
			<tr>
				<td>註冊日期:</td>
				<td><input type="hidden" name="mRegDate" size="45"
					value="<%=memVO.getmRegDate()%>" /><%=memVO.getmRegDate()%></td>
			</tr>
			<tr>
			    <td>會員狀態:</td>			    
				<td><input type="TEXT" name="mStatus" size="1"
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