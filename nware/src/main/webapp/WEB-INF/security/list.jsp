<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserList</title>
</head>
<body>
	<header id="header">
		<%@ include file="../layout/header.jsp"%>
	</header>
	<h2>List</h2>
	<table border="1">
		<tr>
			<th align="center" width="80">user_id</th>
			<th align="center" width="320">username</th>
			<th align="center" width="100">password</th>
			<th align="center" width="180">enabled</th>
		</tr>
		<c:choose>
			<c:when test="${empty userList }">
				<tr>
					<td colspan="4">List is empty</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${userList }" var="userList">
					<tr>
						<td align="center">${userList.user_id }</td>
						<td align="left">${userList.username }</td>
						<td align="right">${userList.password }</td>
						<td align="center">${userList.enabled }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<br>
	<br>
	<table border="1">
		<tr>
			<th align="center" width="80">user_id</th>
			<th align="center" width="320">username</th>
			<th align="center" width="100">authority</th>
		</tr>
		<c:choose>
			<c:when test="${empty authList }">
				<tr>
					<td colspan="4">List is empty</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${authList }" var="authList">
					<tr>
						<td align="center">${authList.user_id }</td>
						<td align="left">${authList.username }</td>
						<td align="right">${authList.authority}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>


</body>
</html>