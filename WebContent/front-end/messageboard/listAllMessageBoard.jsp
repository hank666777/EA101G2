<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.messageboard.model.*"%>
<%@ page import="com.mem.model.*"%>



<%
    MessageBoardService mbSvc = new MessageBoardService();
    List<MessageBoardVO> list = mbSvc.getAll();
    pageContext.setAttribute("list",list);
    
    MemService mSvc= new MemService();
    List<MemVO> memlist = mSvc.getAll();
    pageContext.setAttribute("memlist",memlist);
%>

<html>
<head>
<title>Miss M MessageBoard</title>
<%@ include file="/front-end/front-end-head.jsp"%>

<style type="text/css">
#main {
	/* font-family: 'DFKai-SB';*/
	background-color: #fab5b6;
	background-image: url('<%=request.getContextPath()%>/images/front-end/messageboard/background_candy.jpg');
	background-repeat: repeat-y;
	background-size: cover;
}
</style>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/listmessageboard.css" type="text/css" />

<!--include css,js -->

</head>

<body id="">
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>	

	<div id='main'>
		<div class="container" id='wrapper'>
			<div class="row post_header">
				<h2 class="title text-center">留言板	</h2>
				
				<div class="">
					<a href='addNew.jsp' class="indexbtn">
                    <img src="<%=request.getContextPath()%>/images/front-end/messageboard/penicon.png" alt="">&nbsp發表新留言
                	</a>						
				</div>
				
			</div>
			<div class="errormsgs" style="padding-left:2%;text-align:center">
							<c:if test="${not empty errorMsgs}">						
								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red">${message}</p>
								</c:forEach>
							
							</c:if>
						</div>
			<div class="row text-right" id="searchbar" style="font-size:16px">
				<div class="col-3 d-inline-block" >搜尋:&nbsp&nbsp<input id="barcontent" type="text" placeholder="請輸入欲搜尋之關鍵字"/></div>
				<div class="col-9 d-inline-block">
					<div class="row text-right">
						<div class="col">
							<form method="post" action="<%=request.getContextPath()%>/front-end/messageboard/messageboard.do">					
								<select size="1" name="postsort" id="inputtext">
									<option value=1>閒聊</option>
									<option value=2>心得</option>
									<option value=3>問題</option>								
								</select>														
								<input class="btn btn-info" type="submit" value="搜尋">
								<input type="hidden" name="action" value="getMessageByPostSort">
							</form>	
						</div>
						<div class="col-2">
							<form method="post" action="<%=request.getContextPath()%>/front-end/messageboard/messageboard.do">					
								<input class="btn btn-info" type="submit" value="會員留言">
								<input type="hidden" name="memno" value="${sessionScope.memVO.memno }">
								<input type="hidden" name="action" value="getMessageFromMemno_For_Display">
							</form>	
						</div>	
					</div>
					
				</div>
				
			</div>
					<!--  往下為留言列表	 -->
			<ul class="list-group">
				<div class='page'><%@ include file="page1.file" %></div> 
				<c:forEach var="mbVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
				<!-- foreach list-->
								 
                    <li class="board_info">
                    	<a href="messageboard.do?search=getOne_For_Display&postno=${mbVO.postno}" class="btn2">
						<c:if test="${not empty mbVO.postDetail}">											
                        	<div class="board_pic d-inline-block"   
                        	style="background-image:url('http://${mbVO.postDetail}	)">                        
                        </div></c:if>                       
                        <c:if test="${mbVO.postDetail == null && mbVO.postSort == '1'}">
                        	<div class="board_pic"  
                        	style="background-image:url('<%=request.getContextPath()%>/images/front-end/messageboard/chat.jpg')" ></div></c:if>
                        <c:if test="${mbVO.postDetail == null && mbVO.postSort == '2'}">
 							<div class="board_pic"  
                        	style="background-image:url('<%=request.getContextPath()%>/images/front-end/messageboard/heart.jpg')" ></div></c:if>
                        <c:if test="${mbVO.postDetail == null && mbVO.postSort == '3'}">
                        	<div class="board_pic"  
                        	style="background-image:url('<%=request.getContextPath()%>/images/front-end/messageboard/question.jpg')" ></div>
                        </c:if>  
				<!--       以上為圖片判斷     -->
                        <div class="main_info" >
                            <div class="board_title">${mbVO.postTitle}</div>
                        </div>
                        </a> 
                      
                        
                       	<div class="board_sorttime">
                            <div id="M_NAME" class="d-none d-xl-block">
                            	<c:forEach var="memVO" items="${memlist}">
							 		<c:if test="${memVO.memno == mbVO.memno}">
								 		<img style="width:24px; "src="<%=request.getContextPath()%>/images/front-end/messageboard/mb_member.png">
								 		<a class="alert-link" href="" style="color:blue"
								 		data-toggle="modal" data-target="#_${memVO.memno }">${memVO.mName}</a>
									
										<!--  modal -->
								 		<div class="modal fade" id="_${memVO.memno }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
										  <div class="modal-dialog modal-dialog-centered">
										    <div class="modal-content">
										      <div class="modal-header">
										        <h5 class="modal-title" id="exampleModal">${memVO.mName}</h5>
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
										          <span aria-hidden="true">&times;</span>
										        </button>
										      </div>
										      <div class="modal-body">      
										     	 <%@ include file="/front-end/messageboard/show_member.jsp"%>        
										      </div>
										     
										    </div>
										  </div>
										</div>
		

									</c:if><!-- 按下後連結留言者資訊-->
								</c:forEach>

                            </div>
                            <div class="d-none d-xl-block">分類:
                            <c:if test="${mbVO.postSort eq ''}">無</c:if>
                            <c:if test="${mbVO.postSort == '1'}">閒聊</c:if>
                            <c:if test="${mbVO.postSort == '2'}">心得</c:if>
                            <c:if test="${mbVO.postSort == '3'}">問題</c:if>                             	
                            </div>
                            <div class="d-none d-xl-block">
                            <fmt:formatDate value="${mbVO.postTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </div>
                        </div>
                        <!--未完工-->                                                      
                       	<div class="board_popular">
                            <div class="" title="回覆數"><img src="<%=request.getContextPath()%>/images/front-end/messageboard/repeatcount.png">&nbsp12</div>
                            <div class="" title="瀏覽數"><img src="<%=request.getContextPath()%>/images/front-end/messageboard/viewcount.png">&nbsp123456</div>
                        </div>    
                    </li>
                </c:forEach> 
                <div class='page'><%@ include file="page2.file" %></div>                  
			</ul>
		</div>
	</div>	
	

  	
  </div>
</div>
	
<%@ include file="/front-end/front-end-footer.jsp"%>

</body>
</html>
