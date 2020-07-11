<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.messageboard.model.*"%>


<%@ include file="/front-end/front-end-head.jsp"%>
<%
	MessageBoardVO mbVO = (MessageBoardVO) request.getAttribute("mbVO");
	
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>MISS M留言資料新增</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/add_message.css" type="text/css" />

<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<%@ include file="/front-end/front-end-head.jsp"%>
</head>
<body>

<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>
	<header>
		<div id='wrapper'>
			<div id="h1_header">
				<h1 id='title' class="">Miss M 留言討論區</h1>
			</div>
		</div>	
	</header>

		<div id="bodymain">
	<FORM METHOD="post" ACTION="messageboard.do" name="form1" style="margin:0;">
			<section id="main" class="main">
				<div class="wrapper">
					<h2>留言板</h2>
					<div class="post_page_form">
						<h3>請輸入以下內容</h3>
						<%-- 錯誤表列 --%>
						<div class="errormsgs" style="padding-left:2%;text-align:center">
							<c:if test="${not empty errorMsgs}">
							
								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red">${message}</p>
								</c:forEach>
							
							</c:if>
						</div>
						
						<div class="formlist"><p>標題:</p>
							<input type="TEXT" name="posttitle" size="45" placeholder="請填入留言標題..." 
									value="<%=(mbVO == null) ? "" : mbVO.getPostTitle()%>" />
						</div>
						
						<div class="formlist" ><p>分類:</p>
							<select size="1" name="postsort" id="inputtext">
								<option value=1>閒聊</option>
								<option value=2>心得</option>
								<option value=3>問題</option>
							</select>
						</div>
						
						<div class="formlist" >
							<p>內容:</p>
							<div class="postdetail">
								<textarea name="postdetail" id="postdetail" 
									rows="100" cols="80" ><%=(mbVO == null) ? "":mbVO.getPostDetail() %>
								</textarea>
							</div>
						</div>
							
						<div class="send_btn">							
							<input type="hidden" name="memno" value="${sessionScope.memVO.memno}">
							<input type="hidden" name="action" value="addMessage">
							<input id="submit" type="submit" value="送出">
						</div>
						
					</div>
				</div>
			</section>
	</FORM>
		</div>
	<script>
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace( 'postdetail' );
	                
	</script>

<%@ include file="/front-end/front-end-footer.jsp"%>
</body>
</html>