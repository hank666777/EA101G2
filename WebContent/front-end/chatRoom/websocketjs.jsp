<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<h3 id="statusOutput" class="statusOutput" style="display:none;"></h3>
	
	<div id="messagesArea" class="panel message-area" style="display:none;"></div>
	<div id="row"></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();"/> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
		<input type="button" id="disconnect" class="button" value="Discont" onclick="disconnect();" />
	</div>
	<script>
			var MyPoint = "/FriendWS/${memNO}";
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			var statusOutput = document.getElementById("statusOutput");
			var messagesArea = document.getElementById("messagesArea");
			var self = '${memNO}';
			var webSocket;
			
			function connect() {
				// create a websocket
				webSocket = new WebSocket(endPointURL);
			
				var messagesArea = document.getElementById("messagesArea");   
				messagesArea.style.display="";	
				
				webSocket.onopen = function(event) {
					console.log("Connect Success!");
				};	
				
				webSocket.onmessage = function(event) {
					var jsonObj = JSON.parse(event.data);
					if ("open" === jsonObj.type) {
						refreshFriendList(jsonObj);
						/*這一段是我加上去取得歷史訊息與聊天訊息的*/
						var jsonObj = {
								"type" : "history",
								"sender" : self,
								"receiver" :"M0002",
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
				var friend = statusOutput.textContent;
				var message = inputMessage.value.trim();
	
				
				
				if (message === "") {
					alert("Input a message");
					inputMessage.focus();
				} else {
					var jsonObj = {
						"type" : "chat",
						"sender" : self,
						"receiver" :"M0002",
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
					updateFriendName("M0002");
					var jsonObj = {
							"type" : "history",
							"sender" : self,
							"receiver" :"M0002",
							"message" : ""
						};
					webSocket.send(JSON.stringify(jsonObj));
				});
			}
			
			function disconnect() {
				webSocket.close();
				document.getElementById('sendMessage').disabled = true;
				document.getElementById('connect').disabled = false;
				document.getElementById('disconnect').disabled = true;
			}
			
			function updateFriendName(name) {
				statusOutput.innerHTML = name;
			}
	</script>