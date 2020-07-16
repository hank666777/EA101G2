<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activitypost.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%
	ActivitypostService actPSvc = new ActivitypostService();
	List<ActivitypostVO> list = actPSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有活動推文資料 - listAllActpost.jsp</title>
<style>

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
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
	<div class="container">
		<div class="row">
			<div class="card">
				<h3>所有活動推文資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/activity_post/activitypost_page.jsp">回首頁</a>
					
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
						<th class="thact">活動文章編號</th>
						<th class="thact">活動編號</th>
						<th class="thact">會員編號</th>
						<th class="thact">活動推文日期</th>
<!-- 						<th class="thact">活動推文內容</th> -->
<!-- 						<th class="thact">活動推文照片</th> -->
						<th class="thact">修改</th>
						<th class="thact">刪除</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="ACPVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td class="tdact">
								
								<a href="<%= request.getContextPath()%>/back-end/activity_post/activitypostServlet.do?actPostno=${ACPVO.actPostno}&action=getwindowActivity">${ACPVO.actPostno}</a>
								
							</td>
								
							<td class="tdact">${ACPVO.actno}</td>
							<td class="tdact">${ACPVO.memno}</td>
							<td class="tdact">${ACPVO.actPostDate}</td>
<%-- 							<td class="tdact">${ACPVO.actPostCon}</td> --%>
<%-- 							<td class="tdact"><img src="<%=request.getContextPath()%>/back-end/activity_post/activitypostpicServlet.do?actPostno=${ACPVO.actPostno}" width="300px" height="250px"></td> --%>


							<td class="tdact">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_post/activitypostServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="actPostno" value="${ACPVO.actPostno}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td class="tdact">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/activity_post/activitypostServlet.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="actPostno" value="${ACPVO.actPostno}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>

				<%@ include file="page2.file"%>
			</div>
		</div>
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
		               <jsp:include page="windowsActivitylistPost.jsp" />
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
</body>
</html>