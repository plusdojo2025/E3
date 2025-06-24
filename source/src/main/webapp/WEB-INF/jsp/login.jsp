<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css' />">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div id=logo><img src="<c:url value='/img/logo.jpg' />" alt="シェアタク"></div>
	<div class=form_flame>
		<h1>ログイン</h1>
		<form id="login_form" action="<c:url value='/LoginServlet' />" method="post">
			<input type="text" name="mailaddress" placeholder="メールアドレス">
			<div id="output1" style="color: red; margin-top: 10px;"></div>
			<div class="password-wrapper">
				<input type="password" id="password" name="loginPW" placeholder="パスワード">
				<span id ="view">
					<!-- 目のアイコン -->
					<i class="far fa-eye-slash"></i>
				</span>
			</div>
			<div id="output2" style="color: red; margin-top: 10px;"></div>
			<input type="submit" name="login" value="ログイン">
		</form>
		<div class="register-link">
		 <a>新規登録は</a><a href="<c:url value='/IdPwServlet' />">こちら</a>
		</div>
	</div> 
<script src="<c:url value='/js/login.js' />"></script>
</body>
</html>