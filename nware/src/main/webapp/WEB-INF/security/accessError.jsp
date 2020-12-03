<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Error</title>
</head>
<body>
	<header id="header">
		<%@ include file = "../layout/header.jsp" %> 	
	</header>
	<h3><c:out value = "${msg }"/></h3>

</body>
</html>