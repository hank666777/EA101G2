<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*, javax.servlet.*,java.text.*" %>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.features.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivityVO actVO = (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>



<html>
<head>
<title>活動資料 - listOneactivity.jsp</title>

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
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>
<!-- 	<div class="container"> -->
<!-- 		<div class="row text-center"> -->
<!-- 			<div class="col"> -->
	<div class="card">

						<h4>
							<a href="<%=request.getContextPath()%>/back-end/activity/activity_page.jsp">回活動管理首頁</a>
						</h4>



				<table>
					<tr>
						<th class="thact">活動編號</th>
						<th class="thact">活動類別編號</th>
						<th class="thact">活動名稱</th>
<!-- 						<th class="thact">活動描述</th> -->
<!-- 						<th class="thact">活動照片</th> -->
<!-- 						<th class="thact">活動總人數</th> -->
						<th class="thact">報名費</th>
<!-- 						<th class="thact">活動狀態</th> -->
<!-- 						<th class="thact">活動報名人數上限</th> -->
<!-- 						<th class="thact">活動報名人數下限</th> -->
					</tr>
					<tr>
						<td class="tdact"><%=actVO.getActno()%></td>
						<td class="tdact"><%=actVO.getActTyno()%></td>
						<td class="tdact"><a href="<%= request.getContextPath()%>/back-end/activity/activityServlet.do?actno=${actVO.actno}&action=getwindowActivity2">${actVO.actName}</a></td>
<%-- 						<td class="thact"><%=actVO.getActDes()%></td> --%>
<%-- 						<td><img src="<%=request.getContextPath()%>/back-end/activity/activitypicServlet.do?actno=${actVO.actno}" style="width:300px; height:200px;"></td> --%>
<%-- 						<td class="thact"><%=actVO.getActTalPeo()%></td> --%>
<%-- 						<td class="thact"><%=actVO.getActHoDate()%></td> --%>
<%-- 						<td class="thact"><%=actVO.getActStDate()%></td> --%>
<%-- 						<td class="thact"><%=actVO.getActEdDate()%></td> --%>
						<td class="tdact"><%=actVO.getActFee()%></td>
<%-- 						<td class="thact"><%=actVO.getActMode()%></td> --%>
<%-- 						<td class="thact"><%=actVO.getActUpper()%></td> --%>
<%-- 						<td class="thact"><%=actVO.getActLower()%></td> --%>
					</tr>
				</table>
			</div>
<!-- 		</div> -->
<!-- 	</div> -->

		<c:if test="${openModal!=null}">

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
							
						<div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			            </div>
						
						<div class="modal-body">
		<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
		               <jsp:include page="windowsActivitylistOne.jsp" />
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