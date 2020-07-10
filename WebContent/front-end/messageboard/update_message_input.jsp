<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.messageboard.model.*"%>



<%
	MessageBoardVO mbVO = (MessageBoardVO) request.getAttribute("mbVO"); //MessageBoardServlet.java (Concroller) 存入req的mbVO物件 (包括幫忙取出的mbVO, 也包括輸入資料錯誤時的mbVO物件)
%>
<%-- <%= mbVO==null %>--${mbVO.memno}--//line 100 --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>留言資料修改</title>
<%@ include file="/front-end/front-end-head.jsp"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/add_message.css" type="text/css" />

<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
</head>
<body>
	<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>	

	<FORM METHOD="post" ACTION="messageboard.do" name="form1">

		<div>
			<section id="main" class="main">
				<div class="wrapper">
					<h2>留言板</h2>
					<div class="post_page_form">
						<h3>請輸入留言</h3>

						<%-- 錯誤表列 --%>				
						<div class="errormsgs" style="padding-left:2%;text-align:center">
							<c:if test="${not empty errorMsgs}">
							
							
								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red">${message}</p>
								</c:forEach>
							
							</c:if>
						</div>
<%-- 留言標題(依是否為主留言而顯示或隱藏) --%>		
						<c:if test="${mbVO.parentno == '0' }">
							<div class="formlist">
								<p>留言標題:</p>
								<input type="TEXT" name="posttitle" size="45"
									value="${mbVO.postTitle}" />
							</div>
						</c:if>
						<c:if test="${mbVO.parentno != '0' }">
						<input  type="hidden" name="posttitle" size="45" 
								value="${mbVO.postTitle}" /></c:if>
<%-- 留言種類(依是否為主留言而顯示或隱藏) --%>		
						<c:if test="${mbVO.parentno == '0' }">
							<div class="formlist">
								<p>留言分類:</p>
								<select size="1" name="postsort" id="inputtext">
									<option value=1 ${(mbVO.postSort==1)?'selected':'' }>閒聊			
									<option value=2 ${(mbVO.postSort==2)?'selected':'' }>心得									
									<option value=3 ${(mbVO.postSort==3)?'selected':'' }>問題
								</select>
							</div>
						</c:if>
						<c:if test="${mbVO.parentno != '0' }">
						<input  type="hidden" name="postsort" size="45" 
								value="${mbVO.postSort}" /></c:if>
						
						<div class="formlist">
							<div class="postdetail"><p>留言內容:</p>
								<textarea name="postdetail" id="postdetail" rows="10" cols="50">${mbVO.postDetail}					
								</textarea>
							</div></div>

						<div class="send_btn">
							<input type="hidden" name="action" value="update"> 
							<input type="hidden" name="postno" value="<%=mbVO.getPostno()%>">
							<input type="hidden" name="posttime" value="<%=mbVO.getPostTime()%>"> 
							<input type="hidden" name="memno" value="<%=mbVO.getMemno()%>">
							<input type="hidden" name="parentno" value="<%=mbVO.getParentno()%>">
							<input id="submit" type="submit" value="送出修改">
						</div>
					</div>
				</div>
			</section>
		</div>
</body>

<script>
	CKEDITOR.replace('postdetail');
</script>


</html>