<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session = "false" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>

<script src = "https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		var formObj = $("#board");
		$("#btnRegister").on("click", function(){
			formObj.attr("action", "/board/register");
			formObj.attr("method", "post");
			formObj.submit();
			})
		$("#btnList").on("click", function(){
			self.location = "/board/list";
			console.log("하위");
			})

		});
	
</script>
<body>
	<header id="header">
		<%@ include file = "../layout/header.jsp" %> 	
	</header>
	<h2>Register</h2>
	
	<form:form modelAttribute="board" action = "register">
		<table>
			<tr>
				<td>title</td>
				<td><form:input path = "title"/></td>
				<td><font color = "red"><form:errors path = "title"/></font></td>
			</tr>
			<tr>
				<td>writer</td>
				<td><form:input path = "writer"/></td>
				<td><font color = "red"><form:errors path = "writer"/></font></td>
			</tr>
			<tr>
				<td>content</td>
				<td><form:input path = "content"/></td>
				<td><font color = "red"><form:errors path = "content"/></font></td>
			</tr>
		</table>
	</form:form>
	
	<div>
		<input type = "submit" id = "btnRegister" value = "Register">
		<input type = "submit" id = "btnList" value = "List">		
	</div>

</body>
</html>