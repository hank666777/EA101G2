<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<%
	ActivityVO actVO = (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style>

	.innerdetail{
		margin:5px 20px;
		
	}
		
	#showactivitytitle{
		text-align:center;
	}
	
	.tddes{
		text-indent: 32px;
	}
	
	.floating-card {
        float: left;
        margin: 30px;
        max-width: 300px;

    }
    
    .windowpad{
    	padding:10px;
    }

	#actimagebox{
		margin-top:35px;
	}

</style>

<body>

	<h4 id="showactivitytitle">${actVO.actName}</h4>

	<div class="container">
		<div class="row">
 	
 			<div class="floating-card">
		 	
			 	<div id="actimagebox"class="col-xl-4 col-lg-6 innerdetail">
			 		<img src="<%=request.getContextPath()%>/back-end/activity/activitypicServlet.do?actno=${actVO.actno}" style="width:300px; height:200px;">
			 	</div>
		 	
		 	</div>
 	
		 	<div class="floating-card">
		 	
				 <div class="col-xl-4 col-lg-6 innerdetail">
			
					<table class="windowpad" width="300px" height="250px">
					 		<tr class="trspace">
					 			<th nowrap="nowrap">活動描述：</th>
					 		</tr>
					 		<tr class="trspace">
					 			<td class="tddes">${actVO.actDes}</td>
					 		</tr>
			 	
			 				<tr>
								<td><b>活動舉辦日期：</b><fmt:formatDate value="${actVO.actHoDate}" pattern="yyyy年MM月dd日 HH時 mm分"/></td>
			 				</tr>
			 	
			 				<tr>
			 					<td><b>報名開始日期：</b><fmt:formatDate value="${actVO.actStDate}" pattern="yyyy年MM月dd日 HH時 mm分"/></td>
			 				</tr>
							
							<tr>
			 					<td><b>報名截止日期：</b><fmt:formatDate value="${actVO.actEdDate}" pattern="yyyy年MM月dd日 HH時 mm分"/></td>
			 				</tr>
							
<!-- 			 				<tr> -->
<%-- 			 					<td><b>報名人數上限：</b>${actVO.actUpper}人</td> --%>
<!-- 							</tr> -->
							
<!-- 							<tr> -->
<%-- 								<td><b>報名人數下限：</b>${actVO.actLower}人</td> --%>
<!-- 			 				</tr> -->
			 	
					</table>
		
	 			</div>
			</div>
			
		</div>	
	</div>	

</body>
</html>