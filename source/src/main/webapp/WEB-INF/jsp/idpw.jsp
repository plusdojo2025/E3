<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="css/idpw.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div id=logo><img></div>
	<a>新規登録</a>
	<form id=idpw_form action="/E3/IdPwServlet" method="post">
		<input type="text" name="mailaddress" placeholder="メールアドレス"><br>
		<input type="password" id=password1 name="loginPW" placeholder="パスワード">
		<span id ="view1">
				<!-- 目のアイコン -->
				<i class="far fa-eye-slash"></i>
		</span><br>
		<input type="password" id=password2 name="loginPW2" placeholder="確認用パスワード">
		<span id ="view2">
				<!-- 目のアイコン -->
				<i class="far fa-eye-slash"></i>
		</span><br>
		<div id="output1" style="color: red;"></div>
		<div id="output2" style="color: red;"></div>
		<div id="output3" style="color: red;"></div>
		<div id="output4" style="color: red;"></div>
		<div id="output5" style="color: red;"></div>
		<input type="submit" value="登録">
	</form>
	<a href="login.jsp">戻る</a>
<script src="js/idpw.js"></script>
</body>
</html>