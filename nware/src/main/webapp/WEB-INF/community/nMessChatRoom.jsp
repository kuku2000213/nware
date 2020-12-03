<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>
*{
	font-family: 'Nanum Gothic', sans-serif;
}
</style>
</head>
<body>
	<div style = "display : none;">
		<input id = "roomno" type = "text" value = "${roomno}" style = "display : none;">
		<input id = "username" type = "text" value ="${username}" style = "display : block;">
	</div>
	<div style = "width : 560px; margin : 0 auto; box-shadow : 0px 0px 30px 4px gray">
		<div style = "width : 560px; height : 800px; margin: 0 auto; background-color : #1d1f20; overflow-y :scroll"
		id = "chatForm">
		
		</div>
		<div style = "width : 560px; height : 120px; margin : 0 auto;">
			<textarea id = "chatvaluesend" style = " outline : none; border-style : none; width : 479.6px; height : 110px; padding:0; margin : 0 auto;resize : none;float : left; font-size : 16pt;"></textarea>
			<input id = "chatsubmit"type = "button" value = "전송" style = "width : 80px; height : 120px; border-style : none; outline:none; background-color : #e2e2e2; margin-left : 0px; margin-top : 0px;float:left; cursor : pointer;">
		</div>
	</div>
	<input type = "text" id = "wakeup" value = "wakeup" style = "display:none;">
	<script>
	var stompClient = null;

	function connect(){
		var socket = new SockJS('/gs-guide-websocket');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame){
			stompClient.subscribe('/nmess/welcome', function(welcome){
				showGreeting(welcome);
				console.log(welcome);
			});
			stompClient.subscribe('/nmess/chatinfo', function(chat){
				var chatlist = JSON.parse(chat.body);
				var chatlistlength = Object.keys(chatlist).length;
				showChat(chatlist, chatlistlength);
				
			});
			stompClient.subscribe('/nmess/chatcon', function(){
				console.log("======> chatcon subscribe");
				});
		});
	}

	function sendUName(){
		stompClient.send("/welcomchat", {}, JSON.stringify({'username':  $("#username").val()}));

		var roomnoval = $("#roomno").val();
		var chatval = "TO JOIN CHAT";

		data = {'roomno': roomnoval, 'chatno' : chatval};
		
		stompClient.send("/chatlist", {}, JSON.stringify(data));
		console.log("username ====>" + $("#username").val());
	}

	function showGreeting(welcome){
		var confirmWC = JSON.parse(welcome.body);
		console.log("showgreeting ======> ", confirmWC);
		$("#chatForm").append("<br><div style = 'color : #fffff4; text-align : center; background-color : #1d1f28; height : 34px; width : 450px; margin: 0 auto;   box-shadow : 0px 0px 30px 4px #000001;'>"
				+ '<div style = "margin-top : 8px;float:left; width : 450px;">' 
				+ confirmWC.username +  " 님이 채팅방에 참가하셨습니다.</div></div><br>");
	}

	function sendChat() {
		//var chatval = document.getElementById("#chatvaluesend");
		var chatval = $("textarea#chatvaluesend").val();
		console.log("sendval() ======>");
		console.log("sendval =======>  chatval : " + chatval);
		var roomnoval = $("#roomno").val();
		data = {'roomnoval' : roomnoval, 'chatval' : chatval };
//		stompClient.send("/chatsend", {}, JSON.stringify({'message': chatval}, {'roomno': roomnoval}));
		
		//stompClient.send("/chatsend", {}, JSON.stringify(data));
		stompClient.send("/chatlist", {}, JSON.stringify(data));

		//stompClient.send("/chatsend", {}, JSON.stringify());
		console.log("sendval() ======> message : " + $("textarea#chatvaluesend").val());
		console.log("sendval() ======> roomno : " + $("#roomno").val());
		//
		//$("textarea#chatvaluesend").val().replace(/\n/g, "");
		$("textarea#chatvaluesend").val('');
		//$("textarea#chatvaluesend").val().replace(/\r?\n|\r/, "");
		$("textarea#chatvaluesend").val().replace(/\n/g, "");
		
		$("textarea#chatvaluesend").focus();
		//$("#chatForm").scrollTop(document.body.scrollHeight);
		$("#chatForm").scrollTop($("#chatForm")[0].scrollHeight);	
		
	}
	function showChat(chatlist, chatlistlength) {
		$("#chatForm").empty();
		console.log("챗 리스트 ======>" + chatlist);
		console.log("챗 리스트 length ======>" + chatlistlength);
		var thisuser = $("#username").val();
		
		for(var i = 0; i < chatlistlength; i++){

			if(chatlist[i].username == thisuser){
				$("#chatForm").append("<div style = 'color : black; margin-left : 354px; margin-top : 6px; '>" 
						+ "<div style = 'font-weight : 600; color : white; text-align : right; margin-right : 6px;'>"
						+ chatlist[i].username 
						+ "</div><div style = 'background : yellow; margin-top : 6px; min-height : 30px;  max-width : 180px;'><div style = 'margin-top : 6px; float : left; margin-left : 4px;'>" 
						+ chatlist[i].nmessChatContent
						+ "</div></div></div><br>");
				
			}else{
				$("#chatForm").append("<div style = 'color : black; margin-left : 10px; margin-top : 6px;'>" 
						+ "<div style = 'font-weight : 600; color : white;'>"
						+ chatlist[i].username 
						+ "</div><div style = 'background : lightgray; margin-top : 6px; min-height : 30px;  max-width : 180px;'><div style = 'margin-top : 6px; float : left; margin-left : 4px;'>" 
						+ chatlist[i].nmessChatContent
						+ "</div></div></div><br>");
			}
			
					
		}
		$("#chatForm").scrollTop($("#chatForm")[0].scrollHeight);
	}

	function wakeUp(){
		console.log("wakeup!");
		stompClient.send("/nmess/shownmesslist", {}, JSON.stringify({'wakeup': $("#wakeup").val()}));
		}
	
	$(function(){
		connect();
		
		setTimeout(function(){
			wakeUp();
		}, 300)
		
		setTimeout(function(){
			sendUName();
			
		}, 500)
		
		setTimeout(function(){
			$("#chatForm").scrollTop($("#chatForm")[0].scrollHeight);	
		}, 600)
		
//		sendValue();
		$("#chatsubmit").on("click", function(){
			sendChat(); 
			})
		})
		
		$(document).on("keydown", function(e){
				if(e.keyCode === 13){
					sendChat(); 
				}
			})
		$(document).on("keyup", function(e){
				if(e.keyCode === 13){
					//sendChat();
					$("textarea#chatvaluesend").val('');
				}
			})
		
		
	</script>
</body>
</html>