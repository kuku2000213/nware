<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
</head>
<body>
	
	<div>
		<h3>채팅방 만들기</h3>
		<input type = "text" placeholder = "채팅방 이름" name = "nMessRoomName" id = "chatname12">
		<input type = "button" id = "addChatBtn" value = "생성">
		<sec:csrfInput/>
	</div>
	<!-- <table border = "1">
		<thead>
			<tr>
				<!-- <th>채팅방 번호</th> -->
			<!-- 	<th width = "200">채팅방 만든이</th>
				<th width = "200">채팅방 이름</th> -->
	 			<!-- <th>채팅방 만든이</th> -->
	<!--		</tr>
		</thead>
	 -->
		<!-- <tbody id = "showMessList">
			<c:forEach items = "${nmessroomnamelist }" var = "nmessroomnamelist"> 
				<tr>
					<!-- <td><c:out value = "${nmessroomnamelist.NMessRoom}"/></td> -->
		<!--		 	<td><c:out value = "${nmessroomnamelist.NMessRoomName}"/></td>
				 	<td><c:out value = "${nmessroomnamelist.username}"/></td>
					<!-- 아니 씨발 대문자로 처 박아놓은적이없는데 ㅈ같네 setter때문인가 -->
			<!--	</tr>
			</c:forEach>
		</tbody>
	</table>
	 -->
	 <!-- 
	 	<tbody id = "showMessList">
	 	</tbody>
	</table>-->
	<div id = "showMessList" style = "background-color : lightgray; width : 500px; height : 850px;margin-top : 20px;">
	
	</div>
	<input type = "text" id = "wakeup" value = "wakeup" style = "display:none;">
	<script>
	var stompClient = null;

	function connect(){
		var socket = new SockJS('/gs-guide-websocket');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			console.log('Connected : ' + frame);
			stompClient.subscribe('/nmess/list', function(showNMessList){
				//showGreeting(JSON.parse(showNMessList.body).content);
				var parsevalue = JSON.parse(showNMessList.body);
				var parsevaluelength = Object.keys(parsevalue).length;
				$("#showMessList").empty();
				for(var i = 0; i < parsevaluelength; i++ ){
					
//					$("#showMessList").append("<tr><td><div style = 'background-color : red'></div>" + parsevalue[i].username + '</td><td><a href = "/nMess/chatRoom">'+parsevalue[i].nmessRoomName+
							 //'</a></td></tr><input type = "text" style = "display : none;" name = "chatroomno" value = "' 
//							 + parsevalue[i].nmessRoom + '">');
//					}
					$("#showMessList").append(
							'<a style = "text-decoration: none; color : black;" href = "/nMess/chatRoom?chatroomno='
							+ parsevalue[i].nmessRoom
							+'">'
							+'<div style = "border : 2px solid gray; "><div style = "font-weight : 600;">' 
							+ parsevalue[i].username  
							+ '</div><div>'
							+ parsevalue[i].nmessRoomName
							+ '</div>'
							+ '</div></a>');
					}
//					$("#showMessList").append(
//							'<a href = "/nMess/chatRoom">'
//							+'<div style = "border : 2px solid gray"><div>' 
//							+ parsevalue[i].username + 
//							'</div><div>'+parsevalue[i].nmessRoomName+
//							 '</div><input type = "text" style = "display : none;" name = "chatroomno" value = "' 
//							 + parsevalue[i].nmessRoom + '"></div></a>');
//					}
				

				//console.log("뭐지?뭐지?뭐지?뭐지?뭐지?뭐지?뭐지?뭐지?" +  parseusername);
				
				//showGreeting(parseusername, parsenmessroomname);
				
				});	
			});
		}

	function sendValue(){
		stompClient.send("/messlist", {}, JSON.stringify({'roomName': $("#chatname12").val()}));
		//stompClient.send("/nmessroom/shownmesslist", {}, JSON.stringify({'roomName': $("#chatname12").val()}));
		}

	function wakeUp(){
		console.log("wakeup!");
		stompClient.send("/messlist", {}, JSON.stringify({'wakeup': $("#wakeup").val()}));
		}

	// 이 메소드를 최상단 메소드에 탑재할 것임
//	function showGreeting(parseusername , parsenmessroomname) {
//	    //$("#greetings").append("<tr><td>" + message + "</td></tr>");
//	    console.log("뭐지2" + username);
//	    console.log("뭐지3" + nmessRoomName);
//	    $("#showMessList").append("<tr><td>" + username + "</td><td>"+nmessRoomName+ "</td></tr>");
//	}
	
//	function showNMessList(nMessRoomName , username){
	//	$("#showMessList").append("<tr><td>" + nMessRoomName + "</td> + <tr>"+username+ "</tr></tr>");
		//}
	$(function(){
		connect();
		setTimeout(function(){
			wakeUp();
		}, 300)
		
//		sendValue();
		$("#addChatBtn").on("click", function(){
			console.log("chatmane12 value ======>", $("#chatname12").val());
			sendValue(); 
			})
		$(document).on("keydown", function(e){
				if(e.keyCode === 13){
					sendValue();  
				}
			})
		})
	</script>
</body>
</html>