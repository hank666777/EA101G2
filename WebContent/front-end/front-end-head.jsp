<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="utf-8">
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<!-- 自定義CSS JS-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front-end-index.css">
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
<!-- 聊天室用CSS JS-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/chatRoom/css/chatStyle.css">
 
<!-- font-awesome CSS 5.13-->
<link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.13.1/css/all.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style>

</style>
<body onunload="disconnect();">