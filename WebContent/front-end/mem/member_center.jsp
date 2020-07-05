<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
	
<html>
<head>
<meta charset="UTF-8">

	<title>MISS M會員中心</title>
	<%@ include file="/front-end/front-end-head.jsp"%>

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


/* 卡片翻轉效果 */
.card-container {
  -webkit-perspective: 700;
  -moz-perspective: 700;
  perspective: 700;
}
.card {
  margin: 20px; 
  background-color: #0871b0;
  width: 200px;
  height: 120px;
  text-align: center;
  line-height: 200px;
  color: #007bff;
  transition: all 0.6s ease;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  positon: relative;
}
.front , .back {
  background-color: #ffc107;
  position: absolute;
  top: 0;
  left: 0;
  width: 200px;
  height: 120px; 
  line-height:120px;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  text-align:center
}
h1 {
  margin: 0;
} 
.back {
  -webkit-transform: rotateY(180deg);
  -moz-transform: rotateY(180deg);
  transform: rotateY(180deg);
}
.card:hover {
  -webkit-transform: rotateY(180deg);
  -moz-transform: rotateY(180deg);
  transform: rotateY(180deg);
}
</style>

<body class=mem_center_body background="${pageContext.request.contextPath}/images/front-end/registImg/backgound.jpg">

	<%@ include file="/front-end/front-end-header.jsp"%>
	
		<!-- 翻轉區塊 -->
		<div class="row">
		
			<div class="container">
				<div class="row justify-content-center">
				
					<div class="col-12 col-md-3">
						<div class="card-container">
						  <div class="card">
						    <div class="front">
						    	<h1>帳戶管理</h1>
						    </div> 
						    <div class="back">
						      <h1>Goodbye</h1>
						    </div>
						  </div>  
						</div>
					</div>
					
					<div class="col-12 col-md-3">
						<div class="card-container">
						  <div class="card">
						    <div class="front">
						      <h1>Hello</h1>
						    </div> 
						    <div class="back">
						      <h1>Goodbye</h1>
						    </div>
						  </div>  
						</div>
					</div>
			
					<div class="col-12 col-md-3">
						<div class="card-container">
						  <div class="card">
						    <div class="front">
						     	<h1>Hello</h1>
						    </div> 
						    <div class="back">
						      <h1>Goodbye</h1>
						    </div>
						  </div>  
						</div>
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

		<div class="mem_center_div">
			<br>
			<br>
			<br>
			<br>
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

	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>