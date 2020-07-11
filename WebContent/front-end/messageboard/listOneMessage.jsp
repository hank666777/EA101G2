<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.messageboard.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.List"%>

<%@ include file="/front-end/front-end-head.jsp"%>
<%

	MessageBoardVO mbVO = (MessageBoardVO) request.getAttribute("mbVO");
	List<MessageBoardVO> list = (List<MessageBoardVO>)request.getAttribute("list");
	 
	MemService mSvc= new MemService();
	List<MemVO> memlist = mSvc.getAll();
	pageContext.setAttribute("memlist",memlist);
%>


<html>
<head>
<title>Miss M MessageBoard</title>

<style type="text/css">
body {
	/* font-family: 'DFKai-SB';*/
	background-color: #fab5b6;
	background-image: url('<%=request.getContextPath()%>/images/front-end/messageboard/background_candy.jpg');
	background-repeat: repeat;
	background-size: contain;
	}
</style>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageboard/listonemessage.css" type="text/css" />

</head>

<body>
<%@ include file="/front-end/front-end-header.jsp"%>
<%@ include file="/front-end/front-end-header2.jsp"%>		
	<div class ="container rounded" id="main_message">
		<div id="post_header">
			<div class ="h3" id="title_info">
					<c:if test="${mbVO.postSort == '1'}">
                        	<div>【閒聊】&nbsp${mbVO.postTitle}</div></c:if>
                    <c:if test="${mbVO.postSort == '2'}">
                        	<div>【心得】&nbsp${mbVO.postTitle}</div></c:if>
                    <c:if test="${mbVO.postSort == '3'}">
                        	<div>【問題】&nbsp${mbVO.postTitle}</div></c:if>
					
			</div>
			<div class="row blockquote text-left">
				
				
				<div class="img_block">
					<c:forEach var="memVO" items="${memlist}">
						<c:if test="${memVO.memno == mbVO.memno}">
						<img id="post_img" style="height:64px;object-fit: cover;" src="${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}" alt="會員圖片">
						</c:if><!-- 按下後連結留言者資訊-->
					</c:forEach>
				</div>	
				
				<div class="col-sm align-self-end" id="member_info">
					
					<div class ="col-sm " id="post_member">
						<c:forEach var="memVO" items="${memlist}">
							<c:if test="${memVO.memno == mbVO.memno}">
							
							
							 		<a style="text-decoration:none;" href=""
							 		data-toggle="modal" data-target="#_${memVO.memno }">${memVO.mName}</a>&nbsp${memVO.mAccount}
							 		
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
					
					<div class ="col-sm" id="post_time">
						<fmt:formatDate value="${mbVO.postTime}" 
									pattern="yyyy-MM-dd HH:mm" />
					</div>
					
					
				</div>
				<c:if test="${mbVO.parentno == '0' }">
						<div class ="col-sm text-right">#1樓</div>
				</c:if>
			</div>
		</div>
		<div>
			<div class="h4">${mbVO.postDetail}</div>
			<br>
			<br>

			<div class="row blockquote text-right" >
				<div class="col-sm-9"></div>
				
 					
						<c:if test="${mbVO.memno == sessionScope.memVO.memno}" >
							<div class="">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/messageboard/messageboard.do">
					     	<input class="btn btn-danger" type="submit" value="修改">
				    	 	<input type="hidden" name="postno"  value="${mbVO.postno}">
				     		<input type="hidden" name="action"	value="getOne_For_Update"></FORM>				
						</div>
						</c:if>
				
						<div class=" ">
							<input class="btn btn-danger" type="submit" value="回覆" data-toggle="modal" data-target="#replymessage" >			
						</div>
						<div class=" ">							
			    	 		<input class="btn btn-secondary" type="submit" value="檢舉" data-toggle="modal" data-target="#reportmessage">				     							
						</div>
					
				
			</div>
		</div>
	</div>
		<!-- 底下為回覆留言 -->
	

<c:forEach var="mbVO" items="${list}">
		<!-- 以下為Side Message -->
		<!-- foreach VO-->
	<p id="floor_count"></p>
	<div class ="container rounded" id="side_message">
		<div id="post_header">
			<div class="row blockquote text-left" >
				<div class="img_block" >
				<c:forEach var="memVO" items="${memlist}">
					<c:if test="${memVO.memno == mbVO.memno}">
					<img id="post_img" style="height:64px;object-fit: cover; " 
						src="${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}"
						alt="會員圖片">
					</c:if><!-- 按下後連結留言者資訊-->
				</c:forEach>
				</div>	
				<div class="col-sm align-self-end" id="member_info">
					
					<div class ="col-sm" id="post_member">
						<c:forEach var="memVO" items="${memlist}">
							 	<c:if test="${memVO.memno == mbVO.memno}">
							 	<a style="text-decoration:none;" href="">${memVO.mName}</a>&nbsp${memVO.mAccount}														 
								</c:if><!-- 按下後連結留言者資訊-->
						</c:forEach>
						
					</div>
					<div class ="col-sm" id="post_time">
						<fmt:formatDate value="${mbVO.postTime}" 
									pattern="yyyy-MM-dd HH:mm" />
					</div>
					
				</div>
				<div class ="col-sm text-right floor">#</div>
			</div>	
		</div>
		<div>
			<div class="h4">${mbVO.postDetail}</div>
			<br>
			<br>
		</div>
		<div class="row blockquote text-right">
			<div class="col-sm-9"></div>	
					<c:if test="${mbVO.memno == sessionScope.memVO.memno}" >
					<div class="">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/messageboard/messageboard.do">
		    	 		<input class="btn btn-danger" type="submit" value="修改">
		     			<input type="hidden" name="postno"  value="${mbVO.postno}">
		     			<input type="hidden" name="action"	value="getOne_For_Update"></FORM>				
					</div>
					</c:if>
				<!-- 占格數用 -->
					<c:if test="${mbVO.memno != sessionScope.memVO.memno}" ><input class="btn btn-danger invisible" type="submit" value="修改"></c:if>
			
					<div class="">
							<input class="btn btn-danger" type="submit" value="回覆" data-toggle="modal" data-target="#replymessage" >			
						</div>
					<c:if test="${mbVO.memno != sessionScope.memVO.memno}" >
						<div class="">
							<input class="btn btn-secondary" type="submit" value="檢舉" data-toggle="modal" data-target="#reportmessage">
		    		 	</div>
					</c:if>	
					<c:if test="${mbVO.memno != sessionScope.memVO.memno}" ><input class="btn btn-danger invisible" type="submit" value="檢舉"></c:if>
			
			
			
		</div>
	</div>
</c:forEach>

<!-- bootstrap reply message -->
<div class="modal fade" id="replymessage" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Reply message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">     
        <%@ include file="replyMessage.jsp"%>       
      </div>
     
    </div>
  </div>
</div>

<!-- bootstrap report message -->
<div class="modal fade" id="reportmessage" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Report message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">    
        <%@ include file="/front-end/messagereport/addReport.jsp"%>        
      </div>
     
    </div>
  </div>
</div>


<script>

	var fc = document.getElementsByClassName("floor");	
	window.onload = function(){
		for(var i=0; i<fc.length ;i++){
			fc[i].innerText += (i+2)+'樓';
		}
	}
</script>

<%@ include file="/front-end/front-end-footer.jsp"%>
