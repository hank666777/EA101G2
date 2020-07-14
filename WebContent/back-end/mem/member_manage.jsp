<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.liveOrder.model.*"%>
<%@ page import="com.permission.model.*" %>
<%@ page import="com.features.model.*" %>

<html>
<head>
<meta>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
<title>會員查詢</title>

<style>


#Img1{ 
      width:250px; height:250px;
      
}
#Img1:hover{ 
     width:300px; height:300px;
}

#Img2{ 
      width:250px; height:250px;
     
}
#Img2:hover{ 
     width:300px; height:300px;
}


</style>

</head>
<%@ include file="/back-end/back-end-head.jsp" %>
<body>

<%@ include file="/back-end/back-end-header.jsp" %>

<center>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
					<figure>
						<figcaption style="margin-top:180px;"><font size=5>所有會員資料</font></figcaption>
						<a
							href="${pageContext.request.contextPath}/back-end/mem/listAllMem.jsp">
							<img id="Img1"
							src="${pageContext.request.contextPath}/images/back-end/member/member.png">
						</a>

					</figure>
			</div>

			<div class="col-md-6">
				<figure>
					<figcaption style="margin-top:180px;"><font size=5>意見反應清單</font></figcaption>
					<a
						href="${pageContext.request.contextPath}/back-end/suggest/listAllSug.jsp">
						<img id="Img2"
						src="${pageContext.request.contextPath}/images/back-end/member/suggest.png">
					</a>

				</figure>
			</div>
		</div>
	</div>

	<%@ include file="/back-end/back-end-footer.jsp"%>



</body>

</html>