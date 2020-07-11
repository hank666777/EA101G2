<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.messageboard.model.*"%>
<%@ page import="com.mem.model.*"%>

<%@ include file="/front-end/front-end-head.jsp"%>

<%  
	MemVO ssVO = (MemVO)session.getAttribute("memVO");
	String memno= ssVO.getMemno();
	
	MessageBoardService mbSvc = new MessageBoardService();
	List<MessageBoardVO> mblist = mbSvc.getByMemno(memno);
	pageContext.setAttribute("mblist",mblist);
	
	MemService mSvc= new MemService();
	List<MemVO> memlist = mSvc.getAll();
	pageContext.setAttribute("memlist",memlist);
%>

<html>
<head>
<title>Miss M MessageBoard</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/listmessageboard.css" type="text/css" />

<!-- bootstrap -->
</head>

<body>
<%-- ${not empty sessionScope.memVO}	 --%>
<%-- ${sessionScope.memVO.memno } --%>
<%-- ${not empty mblist}	 --%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<c:forEach var="message" items="${errorMsgs}">
		<font style="color:red">${message}</font>
	</c:forEach>
</c:if>

	<div class="container">
		<div class="row">
		<div id='wrapper'>			
			<ul class="list-group">
				
			<c:forEach var="mbVO" items="${mblist}" >
				<li class="board_info">
					<a href="${pageContext.request.contextPath}/messageboard.do?search=getOne_For_Display&postno=${mbVO.postno}" class="btn2">

					<div class="main_info" >
						<div class="board_title">
							${mbVO.postDetail}
						</div>
          </div>
          </a>    
					<div class="board_sorttime">
						<div id="M_NAME" title="PO文者" class="d-none d-xl-block">
							<c:forEach var="memVO" items="${memlist}">
							<c:if test="${memVO.memno == mbVO.memno}">
								<img style="width:24px; "src="<%=request.getContextPath()%>/images/front-end/messageboard/mb_member.png">&nbsp
								<a class="alert-link" style="color:blue">${memVO.mName}</a>
						
							</c:if><!-- 按下後連結留言者資訊-->
							</c:forEach>
						</div><!-- 按下後連結留言者資訊-->
						<div class="d-none d-xl-block">分類:
							<c:if test="${mbVO.postSort eq ''}">無</c:if>
							<c:if test="${mbVO.postSort == 1}">閒聊</c:if>
							<c:if test="${mbVO.postSort == 2}">心得</c:if>
							<c:if test="${mbVO.postSort == 3}">問題</c:if>                             	
						</div>
						<div class="d-none d-xl-block">
							<fmt:formatDate value="${mbVO.postTime}" pattern="yyyy-MM-dd hh:mm:ss" />
						</div>
					</div>
<!-- 						<div class="board_sorttime"> -->
<!-- 							<div class="d-none d-xl-block">分類: -->
<%--                 <c:if test="${mbVO.postSort eq ''}">無</c:if> --%>
<%-- 								<c:if test="${mbVO.postSort == 1}">閒聊</c:if> --%>
<%-- 								<c:if test="${mbVO.postSort == 2}">心得</c:if> --%>
<%-- 								<c:if test="${mbVO.postSort == 3}">問題</c:if>                             	 --%>
<!-- 							</div> -->
<!-- 						<div class="d-none d-xl-block"> -->
<%-- 							<fmt:formatDate value="${mbVO.postTime}" pattern="yyyy-MM-dd hh:mm:ss" /> --%>
<!-- 		        </div> -->
<!-- 			    </div> -->
						    
					</li>
           </c:forEach> 
            
			</ul>
		</div>
		
	</div>
	</div>	

</body>
</html>
