<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style>
	
	body{
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
	}
	
	
	.thact{
		text-align: center;
		padding: 5px;
		border: orange 2px solid;
	}
	
	.tdact{
		text-align: center;
		padding: 5px;
		border: orange 2px solid;
	}

	.card{
		opacity:0.9;
		width:1200px;
		margin:50px auto;
		padding:50px;
	}

	.clicksure{
		text-align:left;
		margin-top:10px;
	}

</style>
</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>
<%@ include file="/back-end/back-end-header.jsp" %>
<!-- 	<div class="container"> -->
<!-- 		<div class="row text-center"> -->
<!-- 			<div class="col"> -->
		
	<div class="card">
		
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/activity/activity_page.jsp">回前頁</a>
				</h4>

				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
	
	
				<table>
					<tr>
						<th class="thact">活動編號</th>
						<th class="thact">活動類別編號</th>
						<th class="thact">活動名稱</th>
<!-- 						<th class="thact">活動總人數</th> -->
						<th class="thact">報名費</th>
<!-- 						<th class="thact">活動狀態</th> -->
						<th class="thact" colspan ="2"></th>
					</tr>
					
					<%@ include file="page1.file"%>
					<c:forEach var="ACVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						
						<tr>
							<td class="tdact">${ACVO.actno}</td>
							<td class="tdact">${ACVO.actTyno}</td>
							<td class="tdact">
								
								<a href="<%= request.getContextPath()%>/back-end/activity/activityServlet.do?actno=${ACVO.actno}&action=getwindowActivity">${ACVO.actName}</a></td>
							
<%-- 							<td class="tdact">${ACVO.actTalPeo}</td> --%>
							<td class="tdact">${ACVO.actFee}</td>
<%-- 							<td class="tdact">${ACVO.actMode  ==1 ? "活動成團" : "活動未成團"}</td> --%>

							<td class="tdact">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity/activityServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="actno" value="${ACVO.actno}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td class="tdact">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity/activityServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="actno" value="${ACVO.actno}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						
						</tr>
						
					</c:forEach>
				</table>
				
					<%@ include file="page2.file"%>
		</div>	
		
				
		<c:if test="${openModal!=null}">

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
							
						<div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			            </div>
						
						<div class="modal-body">
		<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
		               <jsp:include page="windowActivity.jsp" />
		<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
						</div>
						
						<div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			            </div>
					
					</div>
				</div>
			</div>

	        <script>
	    		 $("#basicModal").modal({show: true});
	        </script>
 	
 		</c:if>
		
				<%@ include file="/back-end/back-end-footer.jsp"%>
				
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
</body>
</html>