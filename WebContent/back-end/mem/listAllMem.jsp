<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
    MemService memSvc = new MemService();

	List<MemVO> list = memSvc.getAll();
    
    pageContext.setAttribute("list",list);

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

<title>所有會員資料</title>

<style>
  h4 {
    color: blue;
    display: inline;
  }

  table {
	width: 990x;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;	
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th {
    padding: 10px;
    width:110 px;
  }
  
  td{
    padding:0 px;
  }
  

</style>

</head>
<center>
<body bgcolor='white'>


	<tr><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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



	<c:forEach var="memVO" items="${list}" >
		
		<tr>
		     
			<td>${memVO.memno}</td>
			<td>${memVO.mAccount}</td>
			<td>${memVO.mPw}</td>
			
			<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/mem/mem.mPic" >
			<td>
			<img class="icon" id="demo" src="<%=request.getContextPath()%>/back-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 75px; height: 75px">
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