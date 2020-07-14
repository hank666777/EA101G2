<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html>
<head>
	
	<%@ include file="/back-end/back-end-head.jsp" %>
<style>
* {
	font-family: Microsoft JhengHei, serif;
}
</style>
</head>
<body>


<!-- 	<div class="container"> -->
	
		<div class="row justify-content-center">
			<div class="col-xl-7 text-center">
				<img width=200 height=200 style="border-radius:50%;"
					src="<%=request.getContextPath()%>/front-end/mem/mem.mPic?memno=${memVO.memno}" />
			</div>
		</div>

		<div class="row justify-content-center align-items-center">
			<div class="col-xl-7">

				<table style="margin-top:10px;"
					class="table table-striped table-bordered table-hover table-sm text-center justify-content-center">
									
						<tr>
							<th class="text-nowrap align-middle table-primary">姓名</th>
							<td class="align-middle">${memVO.mName}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">性別</th>
							<td class="align-middle">${memVO.mGender}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">EMAIL</th>
							<td class="align-middle">${memVO.mEmail}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">註冊日期</th>
							<td class="align-middle">${memVO.mRegDate}</td>
						</tr>
						<tr>
							<th class="text-nowrap align-middle table-primary">狀態</th>
							<td class="align-middle">
								${(memVO.mStatus == 0) ? '未驗證':'' }
								${(memVO.mStatus == 1) ? '已驗證':'' }
								${(memVO.mStatus == 2) ? '停權':'' }
							</td>
						</tr>

				</table>
			</div>
		</div>
	
</body>
</html>