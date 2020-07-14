<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>
<%
    MemService memSvc = new MemService();

    List<MemVO> list = memSvc.getAll();
    
    pageContext.setAttribute("list",list);

%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

<title>所有會員資料</title>

<style>

  table {
	width: 1210x;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
	margin:auto;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
    
  }
  th {
    padding: 10px;
    width:120 px;
    background-color:#D2E9FF;
  }
  
  td{
    padding:0 px;
  }
  

</style>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<center>
<body bgcolor='white'>
<%@ include file="/back-end/back-end-header.jsp" %>

<!-- <br><br> -->
<%-- <h1><a href="${pageContext.request.contextPath}/back-end/mem/member_manage.jsp"><u>回員工管理</u></a></h1> --%>

<br><br><br>
<table >

	<tr >
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
	</tr>
<%@ include file="page1.file" %> 


	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		     
			<td>${memVO.memno}</td>
			<td>${memVO.mAccount}</td>
			<td>${memVO.mPw}</td>
			
			<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/mem/mem.mPic" >
			<td>
			<img class="icon" id="demo" src="<%=request.getContextPath()%>/back-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 100px; height: 80px">
			</td>
			</FORM>

			<td>${memVO.mName}</td>
			<td>${memVO.mGender}</td>
			<td>${memVO.mPhone}</td>
			<td>${memVO.mEmail}</td>
            <td><fmt:formatDate value="${memVO.mRegDate}" pattern="yyyy-MM-dd"/></td>
			<td>${memVO.mStatus}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memno"  value="${memVO.memno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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

<%@ include file="/back-end/back-end-footer.jsp"%>
</body>
</html>