<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div id=logo><img src="img/logo.jpg" max-width: "200"; height: auto; alt="シェアタク"></div>
	<div class=form_flame>
		<h1>ログイン</h1>
		<form id="login_form" action="/E3/LoginServlet" method="post">
			<input type="text" name="mailaddress" placeholder="メールアドレス"><br>
			<input type="password" name="loginPW" placeholder="パスワード"><br>
			<input type="submit" name="login" value="ログイン">
		</form>
		 <a>新規登録は</a><a href="/E3/IdPwServlet">こちら</a>
	</div> 
</body>
</html>