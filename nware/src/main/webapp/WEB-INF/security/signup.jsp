<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session = "false" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
</head>
<body>

<h2>회원가입</h2>
	
	<form action = "signUp" method = "post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input name = "username"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type = "password" name = "password"/></td>
			</tr>
		</table>
		<input type = "submit" value = "회원가입">
		<sec:csrfInput/>
	</form>

</body>
</html>