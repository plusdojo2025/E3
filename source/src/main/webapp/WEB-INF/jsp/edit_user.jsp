<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報編集</title>
<link rel="stylesheet" type="text/css" href="css/edit_user.css">
</head>
<body>
	<div id=logo><img src="img/logo.jpg" alt="シェアタク"></div>
	<div class="form_flame">
		<h1>ユーザー情報編集</h1>
		<form id="user_form" action="/E3/EditUserServlet" method="post">
			<input type="text" name="name" placeholder="氏名">
				<div id="output1" style="color: red;"></div>
			<input type="text" name="nickname" placeholder="ニックネーム">
				<div id="output2" style="color: red;"></div>
			<input type="radio" name="gender">男性
			<input type="radio" name="gender">女性
			<input type="radio" name="gender">その他
				<div id="output3" style="color: red;"></div>
			<input type="text" name="address" placeholder="自宅住所">
			<input type="checkbox" name="noSmoking">非喫煙<br>
			<input type="checkbox" name="noTalking">会話を遠慮する<br>
			<input type="checkbox" name="sameGender">同性希望<br>
			<input type="submit" value="更新">
		</form>
	</div>
	<script src="js/edit_user.js"></script>
</body>
</html>