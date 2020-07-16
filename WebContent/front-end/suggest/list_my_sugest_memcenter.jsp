<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.suggest.model.*"%>

<%@ include file="/front-end/front-end-head.jsp"%>

<%  
	MemVO memVO4 = (MemVO)session.getAttribute("memVO");

	//找出自己的反應清單
	List<SugestVO> mySugList = new SugService().getmySug(memVO4.getMemno());
	if(mySugList.size() != 0){
		pageContext.setAttribute("mySugListSize",mySugList.size() - 5);
	}
	pageContext.setAttribute("mySugList", mySugList);
%>


<html>
<head>
<title>Miss M Sug</title>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col text-center">
				<p class="h2">最近五筆意見反映清單</p>
			</div>
		</div>
		
		<div class="row" >
			<div class="col">
			
				<table class="table table-sm text-nowrap table-hover">
					<tbody>
						<tr>
							<th><p class="h4">反應單號</p></th>
							<th><p class="h4">反應日期</p></th>
							<th><p class="h4">反應內容</p></th>
							<th><p class="h4">回覆狀態</p></th>
							<th><p class="h4">回應內容</p></th>
						</tr>
<c:forEach var="mysuglist" items="${mySugList}" begin="${mySugListSize}">
						<tr>
							<td><p class="h5">${mysuglist.suggestno}</p></td>
							<td><p class="h5">${mysuglist.suggestDate}</p></td>
							<td><p class="h5">${mysuglist.suggestDetail}</p></td>
							<td>
								<p class="h5" style="color:#F00;">${(mysuglist.resStatus == 0)? '未回覆':'' }</p>
								<p class="h5" style="color:green;">${(mysuglist.resStatus == 1)? '已回覆':'' }</p>
							</td>
							<td>
								<p class="h5" style="color:#F00;">${mysuglist.responseDetail}</p>
							</td>
						</tr>
</c:forEach>

					</tbody>
				</table>
				
			</div>
		</div>
		
	</div>

</body>
</html>