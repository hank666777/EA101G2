<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Controller), 存入req的memVO物件
  MemVO SmemVO = (MemVO)session.getAttribute("memVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

<style type="text/css">

body {
	text-align: center;
	font-size: 20px;
	font-family: 微軟正黑體;
	background-size: cover;
	margin:auto;
}

form {
	border-style: solid 5px;
	width: 40%;
	border-radius: 40px;
	background-color: white;
	opacity: 0.9;
	margin:60px auto;
	
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
td{
    font-size: 25px;
	font-family: 微軟正黑體;
}

.icon{
    border-radius: 50%; 
    width: 150px;
    height: 150px;
    border:1px solid black;
    margin:15px;
}

.mem_tittle{
    background-color: #F0F0F0;
	font-size:45px;
	border-radius:10px;
	width:200px;
}
</style>

</head>
<center>
<body background="front-end/mem/images/front-end/registImg/backgound.jpg">


<form>
<table>
    <img class="icon" id="demo" src="<%=request.getContextPath()%>/front-end/mem/mem.mPic?memno=${memVO.memno}" style="width: 150px; height: 150px">
	<tr>
		<td>會員帳號:</td> 
		<td><input type="hidden" name="mAccount"  value="<%=SmemVO.getmAccount()%>" /><%=SmemVO.getmAccount()%></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input  type="hidden" name="mPw"  value="<%=SmemVO.getmPw()%>" required  /><%=SmemVO.getmPw()%></td>
	</tr>
	<tr>
		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/mem/mem.mPic" >
			<td>
			
			</td>
			</FORM>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input class="inp" type="hidden" name="mName"  value="<%=SmemVO.getmName()%>" required  /><%=SmemVO.getmName()%></td>
	</tr>
	<tr>
		<td>會員性別:</td>
		<td><input type="hidden" name="mGender" value="<%=SmemVO.getmGender()%>" /><%=SmemVO.getmGender()%></td>

	   </tr>
	<tr>
		<td>會員電話:</td>
		<td><input class="inp" type="hidden" name="mPhone"   value="<%=SmemVO.getmPhone()%>" required /><%=SmemVO.getmPhone()%></td>
	</tr>
	<tr>
		<td>會員mail:</td>
		<td><input class="inp" type="hidden" name="mEmail"  value="<%=SmemVO.getmEmail()%>"  required /><%=SmemVO.getmEmail()%></td>
	</tr>
	<tr>
		<td>註冊日期:</td>
		<td><input class="inp" type="hidden" name="mRegDate"  value="<%=SmemVO.getmRegDate()%>" /><%=SmemVO.getmRegDate()%></td>
	</tr>
		
</table>
       <input type="button"  value="回會員中心"  style="width:110px;height:50px;"onclick="self.location.href='${pageContext.request.contextPath}/front-end/mem/member_center.jsp'"/><br>
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

</body>
</html>