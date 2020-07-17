<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<div id="footer" class="container-fluid jumbotron text-center footer-height " style="margin-bottom:0;">
		<div class="row">
		<div class="container">
		<div class="row">
			<div class="col">
				<h1 class="sidplay-4">Miss M</h1>
				<p class="lead">營業時間：00：00~24：00 全年無休</p>
				<p class="lead">連絡電話：03-4567890</p>
				<p class="lead">地址：桃園市中壢區中央路300號</p>
				<p class="lead">聯繫方式：Miss_M@miss.com.tw</p>
			</div>
			
<!-- 			<div class="col-3"> -->
<!-- 				<form> -->
<!-- 				  <div class="form-group"> -->
<!-- 				    <label for="exampleFormControlInput1">Email address</label> -->
<!-- 				    <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com"> -->
<!-- 				  </div> -->
<!-- 				  <div class="form-group"> -->
<!-- 				    <label for="exampleFormControlTextarea1">建議內容</label> -->
<!-- 				    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea> -->
<!-- 				  </div> -->
<!-- 				</form> -->
<!-- 			</div> -->
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
	
	</body>
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
				"receiver" : "kent463",
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
// 		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : "kent463",
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
			updateFriendName("kent463");
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : "kent463",
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