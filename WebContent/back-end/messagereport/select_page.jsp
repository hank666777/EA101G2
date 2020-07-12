<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Message Report: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>MessageReport: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for MessageReport: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMessageReport.jsp'>List</a> all MessageReport.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="messagereport.do" >
        <b>輸入檢舉編號 (如MR001):</b>
        <input type="text" name="reportno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  

  <jsp:useBean id="mrSvc" scope="page" class="com.messagereport.model.MessageReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="messagereport.do" >
       <b>選擇檢舉編號:</b> <select size="1" name="reportno">
					<c:forEach var="mrVO" items="${mrSvc.all}">
						<option value="${mrVO.reportno}">${mrVO.reportno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
<%-- 
  <li>
     <FORM METHOD="post" ACTION="messagereport.do" >
       <b>輸入會員編號:</b>
       <select size="1" name="reportno">
         <c:forEach var="mrVO" items="${mrSvc.all}" > 
          <option value="${mrVO.memno}">${mrVO.memno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getMemReport_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>--%>


<h3><a href='<%=request.getContextPath() %>/back-end/messageboard/listAllMessageBoard.jsp'>新增檢舉</a></h3>
<%-- 
<ul>
  <li><a href='addReport.jsp'>Add</a> a new Report.</li>
  
</ul>    
--%>
</body>
</html>