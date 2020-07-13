<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.mem.model.*"%> --%>
<%
// 	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>MISS M會員中心</title>
<%@ include file="/front-end/front-end-head.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/member_center_n.css"> --%>
<!-- 聊天室用CSS JS-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/chatRoom/css/chatStyle.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.5.1.min.js"></script>

<style>
#main{
	background-image: url(${pageContext.request.contextPath}/images/front-end/member_bg.png);
	background-size:contain;
	height:100vh;
	padding:0;
}
#second-main{
	opacity:.9;
}
</style>
</head>
<body onload="connect();" onunload="disconnect();">
	<div class="container-fluid" id="main">
		
		<div class="row" id="second-main" style="height:100vh;">
		
		  <div class="col-2" style="padding:0;">
		    <div class="list-group" id="list-tab" role="tablist">
					<!-- 連結鎖定 -->
		      <a class="list-group-item" onclick='return click(this);' disabled='ture'>
			      <img style='width: 50px; height: 50px; border-radius: 50%;' src='${pageContext.request.contextPath}/front-end/mem/mem.mPic?memno=${memVO.memno}'>
			      ${sessionScope.memVO.mName } ${(sessionScope.memVO ==null) ? '':'您好~' }
		      </a>
		      <a class="list-group-item list-group-item-action active" href="${pageContext.request.contextPath}/front-end/index.jsp">
		      	<i class="fa fa-home fa-2x"></i> 回首頁
		      </a>
		      <a class="list-group-item list-group-item-action " id="listOneMem" data-toggle="list" href="#list-listOneMem" role="tab" aria-controls="home">
		      	<i class="fa fa-address-card fa-2x" aria-hidden="true"></i> 個人資料
		      </a>
		      <a class="list-group-item list-group-item-action" id="coupon" data-toggle="list" href="#list-coupon" role="tab" aria-controls="profile">
		      	<i class="fa fa-tag fa-2x" aria-hidden="true"></i> 優惠券
		      </a>
		      <a class="list-group-item list-group-item-action" id="onLineOrder" data-toggle="list" href="#list-onLineOrder" role="tab" aria-controls="messages">
		      	<i class="fa fa-shopping-bag fa-2x" aria-hidden="true"></i> 訂購清單
		      </a>
		      <a class="list-group-item list-group-item-action" id="booking" data-toggle="list" href="#list-booking" role="tab" aria-controls="settings">
		      	<i class="fas fa-chair fa-2x"></i> 訂位查詢
		      </a>
		      <a class="list-group-item list-group-item-action" id="activity" data-toggle="list" href="#list-activity" role="tab" aria-controls="settings">
		      	<i class="fa fa-list-ul fa-2x" aria-hidden="true"></i> 活動查詢
		      </a>
		      <a class="list-group-item list-group-item-action" id="activity-post" data-toggle="list" href="#list-activity-post" role="tab" aria-controls="settings">
		      	<i class="fas fa-poll-h fa-2x"></i> 活動貼文查詢
		      </a>
		      <a class="list-group-item list-group-item-action" id="responselist" data-toggle="list" href="#list-responselist" role="tab" aria-controls="settings">
		      	<i class="fas fa-paper-plane fa-2x"></i> 意見反映
		      </a>
		      <a class="list-group-item list-group-item-action" id="messagess" data-toggle="list" href="#list-messagess" role="tab" aria-controls="settings">
		      	<i class="fas fa-comment-dots fa-2x"></i> 留言紀錄
		      </a>
		      <a class="list-group-item list-group-item-action" id="messagess-report" data-toggle="list" href="#list-messagess-report" role="tab" aria-controls="settings">
		      	<i class="fas fa-exclamation-triangle fa-2x"></i> 留言檢舉紀錄
		      </a>
		      <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/front-end/mem/memberlogout.do">
		      	<i class="fas fa-sign-out-alt fa-2x"></i> 登出
		      </a>
		    </div>
		  </div>
		  
		  <div class="col-10">
		    <br><br><br>
		    <div class="tab-content" id="nav-tabContent">
		      <div class="tab-pane fade show " id="list-listOneMem" role="tabpanel" aria-labelledby="listOneMem">
						<!-- 會員個人資料 -->
						<%@ include file="/front-end/mem/listOneMem.jsp"%>
		      </div>
		      <div class="tab-pane fade" id="list-coupon" role="tabpanel" aria-labelledby="coupon">
		      	<!-- 會員優惠券 -->
						<%@ include file="/front-end/myCoupon/listMyCoupon.jsp"%>
		    	</div>
		      <div class="tab-pane fade" id="list-onLineOrder" role="tabpanel" aria-labelledby="list-onLineOrder">
		      	<!-- 會員訂單紀錄 -->
		      	<%@ include file="/front-end/ono/listMemONO_membercenter.jsp"%>
		      </div>
		      <div class="tab-pane fade" id="list-booking" role="tabpanel" aria-labelledby="booking-list">
		      	<!-- 會員訂位紀錄 -->
		      	<%@ include file="/front-end/bok/listByMemNoForInclude.jsp"%> 
		      </div>
		      <div class="tab-pane fade" id="list-activity" role="tabpanel" aria-labelledby="activity-list">
		      	活動查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="list-activity-post" role="tabpanel" aria-labelledby="activity-post-list">
		      	活動貼文查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="list-responselist" role="tabpanel" aria-labelledby="">
		      	意見反映查詢(未導入)
		      </div>
		      <div class="tab-pane fade" id="list-messagess" role="tabpanel" aria-labelledby="">
		      	<!-- 會員留言紀錄 -->
		      	<%@ include file="/front-end/messageboard/listMemMessage_memcenter.jsp"%>
		      </div>
		      <div class="tab-pane fade" id="list-messagess-report" role="tabpanel" aria-labelledby="">
		      	留言檢舉紀錄(未導入)
		      </div>
		    </div>
		  </div>
		  
		</div>
		
	</div>
	
		
	<div id="chat-circle" class="btn btn-raised">
		<div id="chat-overlay"></div>
		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
 			<path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
 			<path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
		</svg>
	</div>
		
		
	<div class="chat-box">
		<div class="chat-box-header">
				ChatBox 
				<span class="chat-box-toggle">
					<i class="material-icons">close</i>
				</span>
		</div>
		<div id="row"></div>
			<div class="chat-box-body" id="messagesArea">		
			</div>
		<div class="chat-input">			
			<input type="text" id="message" placeholder="Send a message..." onkeydown="if (event.keyCode == 13) sendMessage();"/>
			<input type="submit" class="chat-submit" id="sendMessage" value="送出" onclick="sendMessage();" />
		</div>
	</div>
		
		
		
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script>

	$(function () {
	  var INDEX = 0;
	  $("#sendMessage").click(function (e) {
	    e.preventDefault();
	    var msg = $("#message").val();
	    if (msg.trim() == "") {
	      return false;
	    }

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
	
	var MyPoint = "/FriendWS/${memVO.mAccount}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${memVO.mAccount}';
	var webSocket;                   

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
				
			/*不須點選取得歷史訊息*/
			var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : "Trump",
				"message" : ""
			};
			webSocket.send(JSON.stringify(jsonObj));
				
				
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
			var inputMessage = document.getElementById("message");
			//var friend = statusOutput.textContent;
			var message = inputMessage.value.trim();
	
			if (message === "") {
				alert("Input a message");
				inputMessage.focus();
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : "E0000003",
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
		        }	       
		}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }                                                     /*註解好友列表*/
			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' style="display:none;"><h2>' + friends[i] + '</h2></div>';
		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			//以下讓多餘的編號畫面隱藏
			var test = document.getElementById("statusOutput");   
			test.style.display="none";
			
			var friend = e.srcElement.textContent;
			updateFriendName("E0000003");
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : "E0000003",
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</body>
</html>