<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>MISS M會員中心</title>
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<!-- 自定義CSS JS-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front-end-index.css">
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!-- font-awesome CSS-->
<script src="https://use.fontawesome.com/0114a256f7.js"></script>

</head>

<style>

.mem_center_span {
	font-size:30px;
	padding:10px;
	background: red;
	text-align: center;
	background-color:	#FFFF37;
	opacity: 0.9;
	border-radius: 10px;
	margin:50px;
	
}

.mem_center_body{

}

.mem_center_div{
    width:65%;
    margin :5% auto;
	border-radius: 20px;
	background-color:	#D0D0D0;
	opacity:0.9;
	padding:10px;
}

.mem_center_body{
  background-size:cover;
  text-align:center;
  font-family:微軟正黑體;
}
.div_inner{
  
}

.mem_tittle{
    background-color: #F0F0F0;
	font-size:60px;
	border-radius:10px;
	width:200px;
	padding: 10px;
}

.aaa{
	 text-decoration:none;
}

</style>

<body class=mem_center_body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">
	<div class="container-fluid fixed-top bg-white" style="height:35px; opacity:.8;">
		<div class="row">

			<div class="col">
				<div class="header-user-info">
					<a id="member" href="${pageContext.request.contextPath}/front-end/mem/member_center.jsp"> 
						<i class="fa fa-user"></i> 會員中心
					</a> 
					<a id="cart" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-shopping-cart"></i> 購物車
					</a> 
					<a id="myfavor" href="${pageContext.request.contextPath}"> 
						<i class="fa fa-heart"></i> 追蹤清單
					</a> 
					<a id="menberlogin" style="display: ${(MemVO == null) ? 'none':'display'};"
					href="${pageContext.request.contextPath}/front-end/mem/memberlogin.jsp"> 
						<i class="fa fa-unlock-alt"></i> 會員登入
					</a>
					
						<a id="" style="display: ${(MemVO == null) ? 'display':'none'};" 
								href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do"> 
							<i class="fa fa-share-square"></i> 會員登出
						</a>
				</div>
			</div>
		</div>
	</div>
	
	
	<br><br><br>
<div >
<span class="mem_tittle" >
會員中心
</span>

</div>



<div class="mem_center_div"><br><br><br><br>
				<div class="div_inner">
				    
          <span class="mem_center_span"><a class="aaa" href="${pageContext.request.contextPath}/front-end/mem/update_mem_input.jsp">帳戶管理</a></span>
					<span class="mem_center_span"><a class="aaa" href="">追蹤清單</a></span>
					<span class="mem_center_span"><a class="aaa" href="">優惠券</a></span>
					<span class="mem_center_span"><a class="aaa" href="">訂單查詢</a></span>
					
				</div>	
				
				<br><br><br><br><br>
				
				<div class="div_inner">
				
					<span class="mem_center_span"><a class="aaa" href="">訂位查詢</a></span>
					<span class="mem_center_span"><a class="aaa" href="">活動查詢</a></span>
					<span class="mem_center_span"><a class="aaa" href="${pageContext.request.contextPath}/front-end/suggest/addSuggest.jsp">意見反應</a></span>
					<span class="mem_center_span"><a class="aaa" href="">留言紀錄</a></span>
				</div>	
				<br><br><br>
</div>
</form>

	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>