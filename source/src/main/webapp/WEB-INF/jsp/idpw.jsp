<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="css/idpw.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div id=logo><img src="img/logo.jpg" alt="シェアタク"></div>
	<div class="form_flame">
	<h1>新規登録</h1>
		<form id=idpw_form action="/e3/IdPwServlet" method="post">
			<input type="text" name="mailaddress" placeholder="メールアドレス">
				<div id="output1" style="color: red;"></div>
			<div class="password-wrapper1">
				<input type="password" id=password1 name="loginPW" placeholder="パスワード">
				<span id ="view1">
						<!-- 目のアイコン -->
						<i class="far fa-eye-slash"></i>
				</span>
			</div>
				<div id="output3" style="color: red;"></div>
			<div class="password-wrapper2">
				<input type="password" id=password2 name="loginPW2" placeholder="確認用パスワード">
				<span id ="view2">
						<!-- 目のアイコン -->
						<i class="far fa-eye-slash"></i>
				</span>
			</div>
				<div id="output4" style="color: red;"></div>
				<div id="output2" style="color: red;"></div>
			<input type="submit" value="登録">
		</form>
		<a href="<c:url value='/LoginServlet' />">戻る</a>
	</div>
<script src="js/idpw.js"></script>
</body>
</html>