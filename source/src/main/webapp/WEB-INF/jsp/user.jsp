<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報登録</title>
<link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
	<div id=logo><img src="img/logo.jpg" alt="シェアタク"></div>
		<div class="form_flame">
		<h1>ユーザー情報登録</h1>
		<form id="user_form" action="/E3/UserServlet" method="post">
			<input type="text" name="name" placeholder="氏名">
				<div id="output1" style="color: red;"></div>
			<input type="text" name="nickname" placeholder="ニックネーム">
				<div id="output2" style="color: red;"></div>
			<input type="radio" name="gender" value = 0>男性
			<input type="radio" name="gender" value = 1>女性
			<input type="radio" name="gender" value = 2>その他<br>
				<div id="output3" style="color: red;"></div>
			<input type="text" name="address" placeholder="自宅住所">
			<input type="hidden" name="address_latitude" id="address_latitude">
        	<input type="hidden" name="address_longitude" id="address_longitude">
        	<div id="result"></div>
			<input type="hidden" name="noSmoking" value = 0>
			<input type="checkbox" name="noSmoking"value = 1>非喫煙<br>
			<input type="hidden" name="noTalking"value = 0>
			<input type="checkbox" name="noTalking"value = 1>会話を遠慮する<br>
			<input type="hidden" name="sameGender" value = 0>
			<input type="checkbox" name="sameGender"value = 1>同性希望<br>
			<input type="submit" value="登録">
		</form>
	</div>
	<script src="js/user.js"></script>
</body>
</html>