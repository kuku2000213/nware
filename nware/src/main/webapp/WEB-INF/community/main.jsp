<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello WebSocket</title>
<link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="/main.css" rel="stylesheet"> -->
<!-- <script src="/webjars/jquery/jquery.min.js"></script> -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<!-- <script src="<%=request.getContextPath()%>/resources/js/main.js?ver=14"></script> -->
</head>
<body>
	<noscript>
		<h2 style="color: #ff0000">Seems your browser doesn't support
			Javascript! Websocket relies on Javascript being enabled. Please
			enable Javascript and reload this page!</h2>
	</noscript>
	<div id="main-content" class="container">
		<div class="row">
			<div class="col-md-6">
				<form class="form-inline">
					<div class="form-group">
						<label for="connect">1. Connect 눌러주세요!</label>
						<button id="connect" class="btn btn-default" type="submit">Connect</button>
						<button id="disconnect" class="btn btn-default" type="submit"
							disabled="disabled">Disconnect</button>
					</div>
				</form>
			</div>
			<div class="col-md-6">
				<form class="form-inline">
					<div class="form-group">
						<label for="name">2. 이름을 입력하고 Send를 눌러주세요!</label> <input type="text"
							id="name" class="form-control" placeholder="Your name here...">
					</div>
					<button id="send" class="btn btn-default" type="submit">Send</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table id="conversation" class="table table-striped">
					<thead>
						<tr>
							<th>채팅창</th>
						</tr>
					</thead>
					<tbody id="greetings">
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="message">3. 메시지를 입력하고 Chat Send를 눌러주세요!</label> <input type="text"
			id="chatMessage" class="form-control" placeholder="message.." />
	</div>
	<button id="chatSend" class="btn btn-default sendChat1" type="button">
		Chat Send
	</button>
	
	<script>
	var stompClient = null;

	function setConnected(connected) {
	    $("#connect").prop("disabled", connected);
	    $("#disconnect").prop("disabled", !connected);
	    if (connected) {
	        $("#conversation").show();
	    }
	    else {
	        $("#conversation").hide();
	    }
	    $("#greetings").html("");
	}

	function connect() {
	    var socket = new SockJS('/gs-guide-websocket');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        setConnected(true);
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/greetings', function (greeting) {
	            showGreeting(JSON.parse(greeting.body).content);    
	        });
	        
	        stompClient.subscribe('/topic/chat', function (chat) {
	    	showChat(JSON.parse(chat.body));
	    });
	        
	    });
	}

	function disconnect() {
	    if (stompClient !== null) {
	        stompClient.disconnect();
	    }
	    setConnected(false);
	    console.log("Disconnected");
	}

	function sendName() {
	    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
	}

	function showGreeting(message) {
	    $("#greetings").append("<tr><td>" + message + "</td></tr>");
	}

	function sendChat() {
		stompClient.send("/app/chat", {}, JSON.stringify({'name': $("#name").val(), 'message': $("#chatMessage").val()}));
		$("#chatMessage").val('');
		
	}
	function showChat(chat) {
	  $("#greetings").append("<tr><td>" + chat.name + " : " + chat.message + "</td></tr>");
	}

	$(function () {
	    $("form").on('submit', function (e) {
	        e.preventDefault();
	    });
	    $( "#connect" ).click(function() { connect(); });
	    $( "#disconnect" ).click(function() { disconnect(); });
	    $( "#send" ).click(function() { sendName(); });
	    $( "#chatSend" ).click(function(){ sendChat(); });
	  	$("#chatMessage").keydown(function (key) {
	 
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	           sendChat();
	        }
	 
	    });
	     	
	});
	</script>
	
</body>
</html>