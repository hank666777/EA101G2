<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script type="text/javascript">
    $(function(){
    	 $(".cname").on("blur",function(){
    	    if(this.value.length<2 || this.value.length>10){
    	    	$(".message").removeClass("right");
    	    	$(".message").addClass("message wrong");
    	    	$(".message").text("請輸入正確格式(中文2-10字)");
    	    }else{
    	    	$(".message").removeClass("wrong");
    	    	$(".message").addClass("message right");
    	    	$(".message").text("　");
    	   		}	
    	    })
    	  $(".cdiscount").on("blur",function(){
    	    if(this.value.length===0 || this.value.length>1){
    	    	$(".discount").removeClass("right");
    	    	$(".discount").addClass("discount wrong");
    	    	$(".discount").text("請輸入1個數字");
    	    }else{
    	    	$(".discount").removeClass("wrong");
    	    	$(".discount").addClass("discount right");
    	    	$(".discount").text("　");
    	    	}	
    	    })
    	    
    	   $("#btn").on("click",function () {
	       	var pattern= /^[(\u4e00-\u9fa5)(0-9)]{2,10}$/ ;
    	    var pattern2= /[0-9]{1}/ ;
    	  	
	       	if(!pattern.test($(".cname").val())||!pattern2.test($(".cdiscount").val())||$(".cpic").val()===""){
	       	 $(".cname").blur();
   	       	 $(".cdiscount").blur();
	       	 return false;
	        }
	       	else{
	      	$("#form").submit();
	        }
    	  }) 	
    });
    
    	
	
    </script>
<style type="text/css">
.bg {
            position: fixed;
            top: 0px;
            left: 0px;
            bottom: 10px;
            right: 0;
            z-index: -999;
            opacity:0.85;
        }
        .bg img {
        	background-size:cover;
            height: 1000px;
            width: 100%;
        }

h3 {
	margin: 5px;
	display: inline-block;
	font-size: 14px;
	font-weight: 400;
	color:white;
}
.img {
	visibility: hidden;
	width: 150px;
	height: 100px;
}
ul{
margin:0px;
padding-left:0px;
}
li{
font-size: 16px;}
body{
font-family:Microsoft jhengHei;
background-color: rgb(255, 255, 255);
font-size: 12px;

}

.errors{
color:red;
font-family:Microsoft jhengHei;
margin-bottom: 0px;
height: 34px;
line-height: 34px;

}
p{
display:inline-block;
color:#999;
padding-left:18px;
margin-left:5px;

}
.wrong{
color:red;
background:url(<%=request.getContextPath()%>/back-end/coupon/images/xx.png) no-repeat left center;
}
.right{
background:url(<%=request.getContextPath()%>/back-end/coupon/images/oo.png) no-repeat left center;
}

.form{
position:absolute;
top:10%;
left:25%;
border:1px solid ;
padding:20px;
background-color: rgb(15,15,15,0.3);
}

</style>

<title>Insert title here</title>
</head>
<body >
<div class="bg">
		<img src="<%= request.getContextPath() %>/back-end/coupon/images/bgc.jpg" style="height:100%">
</div>
	<div class="row">
	<div class="form col-md-6">
	
		<form id="form" action="<%=request.getContextPath()%>/coupon/coupon.do" method=post
			enctype="multipart/form-data" >
			<h3>優惠券名稱</h3>
			<input class="cname" type="text" name="cname" value="${param.cname}"><p class="message">　</p> <br>
			
			<h3>優惠券折扣</h3>
			<input class="cdiscount" type="text" name="count" value="${param.count}"><p class="discount">　</p>  <br>
			
			<h3>優惠券圖片</h3>
			<input class="cpic" type="file" name="upfile"
				onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0]);
     					  var img = document.querySelector('#blah');
     					  img.style.visibility = 'visible';">
     					  <p class="pic">　</p> 
			<br> <img class="img" id="blah" /> <br>
			 
			<input id="btn" class="btn btn-primary btn-lg" type="submit" value="新增">
			<input type="hidden" name="action"	value="insert">
		</form>
	</div >
		
	<div class="error col-md-6">
			<c:if test="${not empty errorMsgs}">
			<br>
		
			<c:forEach var="message" items="${errorMsgs}">
			<p class="errors">${message}</p>
			</c:forEach>
		
			</c:if>
	</div>
</div>
</body>
</html>