<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "sec" uri = "http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
	<h1>logout</h1>
	<form action = "/logout" method = "post">
		<sec:csrfInput/>
		<input type = "submit" value = "logout">
	</form>
</body>
</html>