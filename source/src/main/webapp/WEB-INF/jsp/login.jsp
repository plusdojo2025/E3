<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div id=logo><img src="img/logo.jpg" alt="シェアタク"></div>
	<div class=form_flame>
		<h1>ログイン</h1>
		<form id="login_form" action="/E3/LoginServlet" method="post">
			<input type="text" name="mailaddress" placeholder="メールアドレス"><br>
			<input type="password" id= "password" name="loginPW" placeholder="パスワード">
			<span id ="view">
				<!-- 目のアイコン -->
				<i class="far fa-eye-slash"></i>
			</span><br>
			<div id="output" style="color: red; margin-top: 10px;"></div>
			<input type="submit" name="login" value="ログイン">
		</form>
		 <a>新規登録は</a><a href="/E3/IdPwServlet">こちら</a>
	</div> 
<script src="js/login.js"></script>
</body>
</html>