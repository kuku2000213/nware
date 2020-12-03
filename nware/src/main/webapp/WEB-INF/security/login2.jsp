<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/login2.css"
	type="text/css">
</head>
<script src="<%=request.getContextPath()%>/resources/js/login2.js?ver=1"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		var formObj1 = $("#choiLoginForm");
		var formObj2 = $("#choiSignUpForm");
		$("#choiLoginBtn").on("click", function() {
			formObj1.attr("action", "/login");
			formObj1.attr("method", "post");
			formObj1.submit();
		})
		$("#choiSignUpBtn").on("click", function() {
			formObj1.attr("action", "/signUp");
			formObj1.attr("method", "post");
			formObj1.submit();
		})
	});
</script>

<body>
	<div class="cotn_principal">
		<div class="cont_centrar">

			<div class="cont_login">
				<div class="cont_info_log_sign_up">
					<div class="col_md_login">
						<div class="cont_ba_opcitiy">

							<h2>LOGIN</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
							<button class="btn_login" onclick="cambiar_login()"
								style="outline: none;">LOGIN</button>
						</div>
					</div>

					<div class="col_md_sign_up">
						<div class="cont_ba_opcitiy">
							<h2>SIGN UP</h2>


							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

							<button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN
								UP</button>
						</div>
					</div>

				</div>


				<div class="cont_back_info">
					<div class="cont_img_back_grey">
						<img
							src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
							alt="" />
					</div>

				</div>
				<div class="cont_forms">
					<div class="cont_img_back_">
						<img
							src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
							alt="" />
					</div>
					<form class="cont_form_login" id="choiLoginForm" action="/login"
						method="post">
						<a href="#" onclick="ocultar_login_sign_up()"><i
							class="material-icons">&#xE5C4;</i></a>
						<h2>LOGIN</h2>
						<input type="text" placeholder="username" name="username" /> <input
							type="password" placeholder="Password" name="password" />
						<button class="btn_login" onclick="cambiar_login()"
							id="choiLoginBtn" style="outline: none;">LOGIN</button>
						<sec:csrfInput />
						<!--  <input type = "submit" class = "btn_login" style = "outline:none;" value = "LOGIN"> -->
					</form>

					<form class="cont_form_sign_up" id="choiSignUpForm">
						<a href="#" onclick="ocultar_login_sign_up()"><i
							class="material-icons">&#xE5C4;</i></a>
						<h2>SIGN UP</h2>
						<input type="text" placeholder="Email" /> <input type="text"
							placeholder="User" /> <input type="password"
							placeholder="Password" /> <input type="password"
							placeholder="Confirm Password" />

						<button class="btn_sign_up" onclick="cambiar_sign_up()"
							id="choiSignUpBtn">SIGN UP</button>

					</form>

				</div>

			</div>
		</div>
	</div>
	<h2>
		<c:out value="${error }" />
	</h2>
	<h2>
		<c:out value="${logout }" />
	</h2>

</body>
</html>