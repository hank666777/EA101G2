<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.messageboard.model.*"%>
<%@ page import="com.messagereport.model.*"%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉資料新增</title>


<style>
table {
	width: 80%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
textarea#detail{
width="800px" height="800px;"
}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
</head>
<body bgcolor='white'>

	<div class="container ">		
		<div class="text-left">
			<FORM class="needs-validation" METHOD="post" 
			ACTION="<%=request.getContextPath()%>/front-end/messagereport/messagereport.do" novalidate>
				 <div class="form-row">
				  	<div class="col-md-6 mb-3">
				   		<label for="validationCustom03">檢舉原因:</label>
				      	<input type="text" class="form-control" name="reportdetail" size="45"
				      			placeholder="請填入檢舉原因"
				      			id="validationCustom03" required>
					     <div class="invalid-feedback">
					       	檢舉原因不可空白
					     </div>
				    </div>   
				 </div>	

				<br>
				<input type="hidden" name="postno" value="${mbVO.postno}">
				<input type="hidden" name="memno" value="${sessionScope.memVO.memno}">
				<input type="hidden" name="action" value="addReport">
				<input id="report_btn"type="submit" value="送出檢舉">
			</FORM>
		</div>
	</div>
		
<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
<!-- 	按鈕鎖定 -->
<script>
// 		$('#report_btn').click(function(){
// 			var btn = this;
// 			setTimeout(function(){
// 				btn.disabled = true;
// 			},50);
// 		});
</script>		

<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>		
</body>
</html>