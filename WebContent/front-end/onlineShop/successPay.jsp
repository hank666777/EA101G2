<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>success to pay</title>
<style type="text/css">
	.shop-container{
			background-image:url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/402979/plmojilhdju-sebastian-lp.jpg");
			background-size: cover;
			background-position:bottom ;
			position: relative;
			width:100%;
			height:900px;	
			border:0px solid ;		
			overflow:hidden;
		}
	#successView{
			position: relative;
			margin:15% auto;
			width: 300px;
			height: 300px;
			text-align: center;
			background-color: #F8F8F8;
			border-radius:5px;
			box-shadow:3px 3px 9px black;
			padding-top:20px;
			display: none;
		}
</style>
</head>
<%@ include file="/front-end/front-end-head.jsp"%>
<body>
	<%@ include file="/front-end/front-end-header.jsp"%>
	<%@ include file="/front-end/front-end-header2.jsp"%>

	<div class="container-fluid shop-container">
		<div id="successView">
			<img src="<%=request.getContextPath()%>/images/front-end/pay.jpg" style="height:250px;width:250px;"><br>
			於<span id="time">4</span>秒後跳轉至首頁
		</div>
	</div>

	<%@ include file="/front-end/front-end-footer.jsp"%>
	
	<script type="text/javascript">

		delayURL();   
		function delayURL() {
    		var delay = document.getElementById("time").innerHTML;
			var t = setTimeout("delayURL()", 1000);
    	if (delay > 0) {
	        delay--;
	        document.getElementById("time").innerHTML = delay;
   			 } else {
	 		clearTimeout(t); 
	        window.location.href = '<%=request.getContextPath()%>/front-end/index.jsp';
    		}       
		}



		$(document).ready(function (){
			 $("#successView").fadeIn("slow");
		});
		
		
		
	</script>
</body>
</html>