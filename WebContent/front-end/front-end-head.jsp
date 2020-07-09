<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="utf-8">
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

<!-- 自定義CSS JS-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front-end-index.css">

<!-- 聊天室用CSS JS-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/chatRoom/css/chatStyle.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>

<!-- font-awesome CSS 5.13-->
<!-- <script src="https://use.fontawesome.com/0114a256f7.js"></script> -->
<link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.13.1/css/all.css">

<style>

</style>
<body onload="connect();" onunload="disconnect();">