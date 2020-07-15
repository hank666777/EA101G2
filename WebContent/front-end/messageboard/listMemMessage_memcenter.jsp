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
	pageContext.setAttribute("mblistsize",mblist.size()-5);
	
	
	MemService mSvc= new MemService();
	List<MemVO> memlist = mSvc.getAll();
	pageContext.setAttribute("memlist",memlist);
	 
%>


<html>
<head>
<title>Miss M MessageBoard</title>



<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/listmessageboard2.css" type="text/css" />

<!-- bootstrap -->

</head>

<body>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- 	<div id='main'> -->
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<p class="h2">最後五筆留言資料</p>
				</div>
			</div>
		</div>
		<div id='wrapper'>			
			<ul class="list-group">
			
				
				<c:forEach var="mbVO" items="${mblist}" end ="4" >
                    <li class="board_info">
<%--                     	<a href="messageboard.do?search=getOne_For_Display&postno=${mbVO.postno}" class="btn2"> --%>
                            
                        <div class="" style="display: inline-block;padding: 0 0 10px 35px;width: 50%;">
                            <div class="board_title">${mbVO.postDetail}</div>
                        </div>
<!--                      </a>     -->
                        <div class="board_sorttime">
                            <div id="M_NAME" title="PO文者" class="d-none d-xl-block">
                            	<c:forEach var="memVO" items="${memlist}">
							 		<c:if test="${memVO.memno == mbVO.memno}">
							 		 <img style="width:24px; "src="<%=request.getContextPath()%>/images/front-end/messageboard/mb_member.png">&nbsp
							 		 <p class="alert-link" style="color:blue">${memVO.mName}</p>
							 		
									</c:if>
								</c:forEach>
                            </div>
                            
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
            	
                  
            
			</ul>
		</div>
	

	
<!-- 	</div>	 -->

</body>
</html>

