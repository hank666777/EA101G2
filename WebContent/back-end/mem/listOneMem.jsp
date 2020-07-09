<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Controller), 存入req的memVO物件
%>

<html>
<head>
<title>會員資料修改</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

		 <h4><a href="listAllMem.jsp">回會員列表</a></h4>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>大頭照</th>
		<th>會員姓名</th>
		<th>性別</th>
		<th>會員電話</th>
		<th>Email</th>
		<th>註冊日期</th>
		<th>會員狀態</th>
	</tr>
	<tr>
		<td><%=memVO.getMemno()%></td>
		<td><%=memVO.getmAccount()%></td>
		<td><%=memVO.getmPw()%></td>
		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/mem/mem.mPic" >
			<td>
			<img alt="" src="<%=request.getContextPath()%>/back-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
			</td>
			</FORM>
		<td><%=memVO.getmName()%></td>
		<td><%=memVO.getmGender()%></td>
		<td><%=memVO.getmPhone()%></td>
		<td><%=memVO.getmEmail()%></td>
		<td><%=memVO.getmRegDate()%></td>
		<td><%=memVO.getmStatus()%></td>	
	</tr>
</table>

</body>
</html>