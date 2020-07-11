<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.messageboard.model.*"%>


<html>
<head>
<title>replyMessage</title>


<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<!-- bootstrap -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/messageboard/replyMessage.css">
</head>
<body>
	<form name="replyform" action="messageboard.do" method="post" accept-charset="utf-8">
		<div class ="container" id="main">
			<section id ="row" >
				<div class="wrapper">
					<div class="post_page_form rounded">
					
						<%-- 錯誤表列 --%>
						<div class="errormsgs" style="padding-left:2%;text-align:center">
							<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							
								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red">${message}</p>
								</c:forEach>
							
							</c:if>
						</div>
					
						<div class="formlist" ><p>請於下方輸入留言內容:</p>
						<div class="postdetail">
							<textarea name="postdetail" id="postdetail" required>	</textarea>
							
						</div></div>		
						<div class="text-center" >
							<input type="hidden" name="parentno" value="${mbVO.postno}">						
							<!-- 被inlude:故mbVO.postno一定是主留言 -->
							<input type="hidden" name="memno" value="${sessionScope.memVO.memno}">
							<input type="hidden" name="action" value="replyMessage">
							<input class="btn btn-info" id="submit"  type="submit" value="送出">
							
						</div>
					</div>
				</div>
			</section>
		</div>			
	</form>


<script type="text/javascript"> CKEDITOR.replace( 'postdetail' );</script>
</body>
</html>