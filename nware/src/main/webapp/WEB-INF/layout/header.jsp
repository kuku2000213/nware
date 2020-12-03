<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "sec" uri = "http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	NarangDesign Corp. GroupWare
	<div style = "height : 40px;"></div>
	<c:if test = "${empty sessionScope.loginUser }">
		<form action = "login" method = "get">
			<input type = "submit" value = "로그인">
		</form>
		<br>
		<form action = "signup" method = "get">
			<input type = "submit" value = "회원가입">
		</form>
	</c:if>
	<c:if test = "${!empty sessionScope.loginUser }">
		<!-- <form action = "community/main" method = "get">
			<input type = "submit" value = "채팅">
		</form>
		 -->
		<form action = "/roomList" method = "get">
			<input type = "submit" value = "nMessenger">
		</form>
		<br>
		<form action = "logout" method = "post">
			<sec:csrfInput/>
			<input type = "submit" value = "로그아웃">
		</form>
	</c:if>
	<br>
</body>
</html>