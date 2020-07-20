<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script type="text/javascript">
    $(function(){
    	$("li").each(function(){
    		$(this).on("click",function(){
    			$("li").each(function(){
    			$(this).removeClass("select");	
    			})
    			$(this).addClass("select");
    		})
    	})	
    })

    </script>
<style type="text/css">
body{
		background-image:url("<%= request.getContextPath() %>/back-end/coupon/images/bgc.jpg"); 
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}

ul{
margin:0px;
padding-left:0px;
}
li{
font-size: 14px;}
body{
font-family:Microsoft jhengHei;

}
.row2{
background-color: rgb(255, 255, 255);
border-collapse:collapse ;


}
.select{
border:1.4px solid #dee2e6;
border-bottom:solid 1px rgb(255, 255, 255);
margin-bottom:-1px;
}
.nav a{
background-color: rgb(255, 255, 255) !important;
}




</style>

<title>Insert title here</title>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">優惠券管理</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/back-end/coupon/addCoupon.jsp" target="coupon">新增優惠券種類<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/back-end/coupon/listAllCoupon.jsp" target="coupon">查詢優惠券種類</a>
      </li>

    </ul>
  </div>
</nav>


	<iframe src="" frameborder="no" scrolling="no" width="100%" height="1000px" name="coupon"></iframe>

<%@ include file="/back-end/back-end-footer.jsp" %> 