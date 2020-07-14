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

<!-- 驗證code -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/jquery.validate.js"></script> 


<!-- 自訂CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/messageboard/replyMessage.css">

</head>
<body>
	<form  name="replyform" id="reply_form"
		action="messageboard.do" method="post" accept-charset="utf-8" >
		<div class ="container" id="main">
			<section id ="row" >
				<div class="wrapper">
					<div class="post_page_form rounded">										
						<div class="form-row">
						  	<div class="col formlist text-center">
						   		<p>請於下方輸入留言內容:</p>
						   		<%-- 錯誤表列 --%>
									<div class="errormsgs" style="padding-left:2%;text-align:center">
										<c:if test="${not empty errorMsgs}">
										
										
											<c:forEach var="message" items="${errorMsgs}">
												<p style="color: red ;font-weight:bold; background-color:white; width:40%">${message}</p>
											</c:forEach>
										
										</c:if>
									</div>
						   		<div id="invalid-feedback" style="color:red;">
							   		<div class="postdetail">
							      		<textarea name="postdetail" id="postdetail" type="text" 						      			
								      			required></textarea>								     							       	
									</div>
							    </div>
						    </div>   
						 </div>	
						
						
						<div class="text-center" >
							<input type="hidden" name="parentno" value="${mbVO.postno}">						
							<!-- 於頁尾被inlude:故mbVO.postno一定是主留言 -->
							<input type="hidden" name="memno" value="${sessionScope.memVO.memno}">
							<input type="hidden" name="action" value="replyMessage">
							<input class="btn btn-info" id="submit" type="submit" value="送出">
							
						</div>
					</div>
				</div>
			</section>
		</div>			
	</form>
	
<!-- 	按鈕鎖定 -->
<script>
		$('#submit').click(function(){
			var btn = this;
			setTimeout(function(){
				btn.disabled = true;
			},50);
		});
</script>
<%-- 						<c:if test="${mbVO.memno == sessionScope.memVO.memno}" > --%>
<!-- 							<div class=""> -->
<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/messageboard/replyMessage.jsp"> --%>
<!-- 					     	<input class="btn btn-danger" type="submit" value="回覆"> -->
<%-- 				    	 	<input type="hidden" name="postno"  value="${mbVO.postno}">				     					 --%>
<!-- 						</div> -->
<%-- 						</c:if> --%>




<!-- ckeditor  -->

<script type="text/javascript"> CKEDITOR.replace( 'postdetail' );</script>
</body>
</html>