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

<style type="text/css">
#main {
	/* font-family: 'DFKai-SB';*/
	background-color: #fab5b6;
	background-image: url('<%=request.getContextPath()%>/images/front-end/messageboard/background_candy.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	}

</style>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/listmessageboard.css" type="text/css" />

<!-- bootstrap -->

</head>

<body>
	
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>	


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<div id='main'>
		<div id='wrapper'>
			
			<ul class="list-group">
				
				<div class='page'><%@ include file="page1.file" %></div> 
				<c:forEach var="mbVO" items="${mblist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
				
                    <li class="board_info">
                    	<a href="messageboard.do?search=getOne_For_Display&postno=${mbVO.postno}" class="btn2">      
                        	<div class="" style="display: inline-block;padding: 0 0 10px 35px;width: 50%;" >
                            	<div class="board_title">${mbVO.postDetail}
                            </div>
                      		</div>
                        </a>    
                        <div class="board_sorttime">
                            <div id="M_NAME" title="PO文者" class="d-none d-xl-block">
                            	<c:forEach var="memVO" items="${memlist}">
							 		<c:if test="${memVO.memno == mbVO.memno}">
							 		 <img style="width:24px; "src="<%=request.getContextPath()%>/images/front-end/messageboard/mb_member.png">&nbsp
							 		 <a class="alert-link" href="" style="color:blue">${memVO.mName}</a>
							 		
									</c:if><!-- 按下後連結留言者資訊-->
								</c:forEach>
                            </div><!-- 按下後連結留言者資訊-->
                        </div>
						<div class="board_sorttime">
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
                        
                    </li>
                </c:forEach> 
            	<div class='page'><%@ include file="page2.file" %></div>  
            
			</ul>
		</div>
	

	
	</div>	
	
<%@ include file="/front-end/front-end-footer.jsp"%>


