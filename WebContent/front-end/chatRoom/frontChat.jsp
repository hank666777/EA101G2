<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/chatRoom/css/chatStyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<div id="center-text">
		<h2>ChatBox UI</h2>
		<p>Message send and scroll to bottom enabled</p>
	</div>
	<div id="body">

		<div id="chat-circle" class="btn btn-raised">
			<div id="chat-overlay"></div>
			<i class="material-icons"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  			<path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
  			<path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
			</svg></i>
		</div>

		<div class="chat-box">
			<div class="chat-box-header">
				ChatBot <span class="chat-box-toggle"><i
					class="material-icons">close</i></span>
			</div>
			<div class="chat-box-body">
				<div class="chat-box-overlay"></div>
				<div class="chat-logs"></div>
				<!--chat-log -->
			</div>
			<div class="chat-input">
				<form>
					<input type="text" id="chat-input" placeholder="Send a message..." />
					<button type="submit" class="chat-submit" id="chat-submit">
						<i class="material-icons">send</i>
					</button>
				</form>
			</div>
		</div>

	</div>
	<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(function () {
	  var INDEX = 0;
	  $("#chat-submit").click(function (e) {
	    e.preventDefault();
	    var msg = $("#chat-input").val();
	    if (msg.trim() == "") {
	      return false;
	    }
	    generate_message(msg, "self");
	    var buttons = [
	      {
	        name: "Existing User",
	        value: "existing"
	      },
	      {
	        name: "New User",
	        value: "new"
	      }
	    ];
	    setTimeout(function () {
	      generate_message(msg, "user");
	    }, 1000);
	  });


	  $("#chat-circle").click(function () {
	    $("#chat-circle").toggle("scale");
	    $(".chat-box").toggle("scale");
	  });

	  $(".chat-box-toggle").click(function () {
	    $("#chat-circle").toggle("scale");
	    $(".chat-box").toggle("scale");
	  });
	});

</script>
</body>
</html>