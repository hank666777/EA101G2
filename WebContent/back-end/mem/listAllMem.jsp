<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemService memSvc = new MemService();

    List<MemVO> list = memSvc.getAll();
    
    pageContext.setAttribute("list",list);

%>


<html>
<head>
<title>所有會員資料 - listAllMem.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		<th>修改</th>
		<th>刪除</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>


	<c:forEach var="memVO" items="${list}" >
		
		<tr>
		     
			<td>${memVO.memno}</td>
			<td>${memVO.mAccount}</td>
			<td>${memVO.mPw}</td>
			
			<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/mem/mem.mPic" >
			<td>
			<img alt="" src="<%=request.getContextPath()%>/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
			</td>
			</FORM>

			<td>${memVO.mName}</td>
			<td>${memVO.mGender}</td>
			<td>${memVO.mPhone}</td>
			<td>${memVO.mEmail}</td>
            <td><fmt:formatDate value="${memVO.mRegDate}" pattern="yyyy-MM-dd"/></td>
			<td>${memVO.mStatus}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memno"  value="${memVO.memno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memno"  value="${memVO.memno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>