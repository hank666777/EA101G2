<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/back-end/chatRoom/css/chatStyle.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<body style="background-size:cover; " onload="connect();" onunload="disconnect();">
	  <div class="container-fluid" style="hegiht:100vh;">
	  	
		<div class="row">
			<div class="col" style="padding:0 0;">
				<ul class="nav justify-content-end align-items-center" 
				style="background-color: #778899; ">
				
					<li class="nav-item mx-3">
						<div class="load ">
						  <hr/><hr/><hr/><hr/>
						</div>
				  </li>
				  <li class="nav-item" style="">
				    <p class="mb-0 h3">${sessionScope.employeeVO.eName} 您好~</p>
				  </li> 
				  <li class="nav-item my-1 mx-2">
				  	<a href="<%=request.getContextPath()%>/back-end/employee/employee_list_one.jsp">
				    	<img style="width:60px; height:60px; border-radius:50%;"class="mx-auto d-block" 
								   src="${pageContext.request.contextPath}/back-end/employee/epicshow.do?empno=${sessionScope.employeeVO.empno}" />
						</a>
				  </li>
				</ul>
			</div>
	  	</div>
	  	
	  	<div class="row" >
	  		<div class="col-2 bg-secondary text-light border border-dark border-right"style="max-height:100%;">
	  			
	  			<div class="row text-light w-100 h-auto">
					<div class="col">
						<a href="${pageContext.request.contextPath}/back-end/back-end-index.jsp">
							<img style="width:150px; height:150px;"class="mx-auto d-block" 
									 src="${pageContext.request.contextPath}/images/logo.png" />
						</a>
					</div>
				</div>
					
<!-- 	  			<div class="row justify-content-center"> -->
<!-- 	  				<blockquote class="blockquote text-center"> -->
<%-- 						  <p class="mb-0 h3">${sessionScope.employeeVO.eName} 您好</p> --%>
<!-- 						</blockquote> -->
<!-- 	  			</div> -->
<jsp:useBean id="feaSvc" class="com.features.model.FeaturesService"/>
<!-- <i class="fa fa-users" aria-hidden="true"></i> -->
					<div class="row shadow bg-secondary h3 justify-content-center">
						<c:forEach var="feaVO" items="${feaSvc.all}">
						<c:forEach var="perVOlist" items="${sessionScope.perVOlist}">
							<c:if test="${(feaVO.feano eq perVOlist.feano)}">
							<a class="btn btn-block btn-secondary" 
								href="${pageContext.request.contextPath}/back-end/
									${(feaVO.feano == 'F0010') ? 'employee/select_page_employee.jsp':''}
										${(feaVO.feano == 'F0020') ? 'mem/member_manage.jsp':''}
									${(feaVO.feano == 'F0030') ? 'product/selectProductPage.jsp':''}
									${(feaVO.feano == 'F0040') ? 'bok/booking_page_admin.jsp':''}
									${(feaVO.feano == 'F0050') ? 'activity/select_page_activity.jsp':''}
									${(feaVO.feano == 'F0060') ? 'bok/liveOrderMultiQuery.jsp':''}
									${(feaVO.feano == 'F0070') ? 'liveOrder/chooseList.jsp':''}
									${(feaVO.feano == 'F0080') ? 'coupon/couponIndex.jsp':''}
									${(feaVO.feano == 'F0090') ? 'messagereport/listAllMessageReport.jsp':''}">
									
<!-- 								塞圖片用，放在引號 -->
								<p class="h2 text-white">
									${(feaVO.feano == 'F0010') ? '<svg class="bi bi-people-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  							<path fill-rule="evenodd" d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5.784 6A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
							</svg>':''}
							
									${(feaVO.feano == 'F0020') ? '<svg class="bi bi-people" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
 							<path fill-rule="evenodd" d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8zm-7.995-.944v-.002.002zM7.022 13h7.956a.274.274 0 0 0 .014-.002l.008-.002c-.002-.264-.167-1.03-.76-1.72C13.688 10.629 12.718 10 11 10c-1.717 0-2.687.63-3.24 1.276-.593.69-.759 1.457-.76 1.72a1.05 1.05 0 0 0 .022.004zm7.973.056v-.002.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816zM4.92 10c-1.668.02-2.615.64-3.16 1.276C1.163 11.97 1 12.739 1 13h3c0-1.045.323-2.086.92-3zM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4z"/>
							</svg>':''}
							
									${(feaVO.feano == 'F0030') ? '<svg class="bi bi-file-post" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  							<path fill-rule="evenodd" d="M4 1h8a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v10a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1H4z"/>
  							<path d="M4 5.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-7z"/>
  							<path fill-rule="evenodd" d="M4 3.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
							</svg>':''}
							
									${(feaVO.feano == 'F0040') ? '<svg class="bi bi-aspect-ratio" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  							<path fill-rule="evenodd" d="M0 3.5A1.5 1.5 0 0 1 1.5 2h13A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 12.5v-9zM1.5 3a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"/>
 							<path fill-rule="evenodd" d="M2 4.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1H3v2.5a.5.5 0 0 1-1 0v-3zm12 7a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H13V8.5a.5.5 0 0 1 1 0v3z"/>
							</svg>':''}
							
									${(feaVO.feano == 'F0050') ? '<svg class="bi bi-life-preserver" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  							<path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
							 <path fill-rule="evenodd" d="M8 11a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8z"/>
							 <path d="M11.642 6.343L15 5v6l-3.358-1.343A3.99 3.99 0 0 0 12 8a3.99 3.99 0 0 0-.358-1.657zM9.657 4.358L11 1H5l1.343 3.358A3.985 3.985 0 0 1 8 4c.59 0 1.152.128 1.657.358zM4.358 6.343L1 5v6l3.358-1.343A3.985 3.985 0 0 1 4 8c0-.59.128-1.152.358-1.657zm1.985 5.299L5 15h6l-1.343-3.358A3.984 3.984 0 0 1 8 12a3.99 3.99 0 0 1-1.657-.358z"/>
							</svg>':''}
							
							
									${(feaVO.feano == 'F0060') ? '<svg class="bi bi-cart-plus" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M8.5 5a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1H8V5.5a.5.5 0 0 1 .5-.5z"/>
							<path fill-rule="evenodd" d="M8 7.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1H9v1.5a.5.5 0 0 1-1 0v-2z"/>
							<path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
							</svg>':''}
							
									${(feaVO.feano == 'F0070') ? '<i class="fa fa-th-list" aria-hidden="true"></i>':''}
							
									${(feaVO.feano == 'F0080') ? '<svg class="bi bi-tag-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M2 1a1 1 0 0 0-1 1v4.586a1 1 0 0 0 .293.707l7 7a1 1 0 0 0 1.414 0l4.586-4.586a1 1 0 0 0 0-1.414l-7-7A1 1 0 0 0 6.586 1H2zm4 3.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
							</svg>':''}
							
									${(feaVO.feano == 'F0090') ? '<svg class="bi bi-chat-quote-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						    <path fill-rule="evenodd" d="M16 8c0 3.866-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7zM7.194 6.766c.087.124.163.26.227.401.428.948.393 2.377-.942 3.706a.446.446 0 0 1-.612.01.405.405 0 0 1-.011-.59c.419-.416.672-.831.809-1.22-.269.165-.588.26-.93.26C4.775 9.333 4 8.587 4 7.667 4 6.747 4.776 6 5.734 6c.271 0 .528.06.756.166l.008.004c.169.07.327.182.469.324.085.083.161.174.227.272zM11 9.073c-.269.165-.588.26-.93.26-.958 0-1.735-.746-1.735-1.666 0-.92.777-1.667 1.734-1.667.271 0 .528.06.756.166l.008.004c.17.07.327.182.469.324.085.083.161.174.227.272.087.124.164.26.228.401.428.948.392 2.377-.942 3.706a.446.446 0 0 1-.613.01.405.405 0 0 1-.011-.59c.42-.416.672-.831.81-1.22z"/>
							</svg>':''}
							
								${feaVO.feaName}</p>
							</a>
							</c:if>
						</c:forEach>
						</c:forEach>
						
						<a class="btn btn-block btn-secondary" id="backLogout"
						 	href="">
								<p class="h2 text-white"><i class="fas fa-sign-out-alt"></i>Logout</p>
						</a>
					</div>

	  		</div>
<script>
//sweet alert2 logout
$('#backLogout').on('click',function(e){
	// stops the default action
	e.preventDefault();
	Swal.fire({
	  title: '確定登出?',
	  text: "",
	  icon: 'question',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  confirmButtonText: '確定',
	  cancelButtonColor: '#d33',
	  cancelButtonText: '取消'
	}).then((result) => {
	  if (result.value) {
	    Swal.fire(
	      '登出成功',
	      '',
	      'info'
	    ).then(function(){
			  window.location.href = "${pageContext.request.contextPath}/employeelogout.do";
		  })
	  }
	});
});

</script>
	  		<div class="col-10 " style="padding:0;">
							
						<!-- 放置區開始 -->
						<div class="row " id="back-end-main-area" >
							<div class="col align-self-center">