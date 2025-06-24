<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報登録</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/user.css' />"></head>
<body>
	<div id=logo><img src="<c:url value='/img/logo.jpg' />" alt="シェアタク"></div>
		<div class="form_flame">
		<h1>ユーザー情報登録</h1>
		<form id="user_form" action="<c:url value='/UserServlet' />" method="post">
			<input type="text" name="name" placeholder="氏名">
				<div id="output1" style="color: red;"></div>
			<input type="text" name="nickname" placeholder="ニックネーム">
				<div id="output2" style="color: red;"></div>
			<input type="radio" name="gender" value = 0>男性
			<input type="radio" name="gender" value = 1>女性
			<input type="radio" name="gender" value = 2>その他<br>
				<div id="output3" style="color: red;"></div>
			<input type="text" name="address"  id="address" placeholder="自宅住所">
			<input type="hidden" name="address_latitude" id="address_latitude">
        	<input type="hidden" name="address_longitude" id="address_longitude">
        	<div id="result"></div>
			<input type="checkbox" name="noSmoking" value="1">非喫煙<br>
			<input type="hidden" name="noSmoking" value="0">
			<input type="checkbox" name="noTalking" value="1">会話を遠慮する<br>
			<input type="hidden" name="noTalking" value="0">
			<input type="checkbox" name="sameGender" value="1">同性希望<br>
			<input type="hidden" name="sameGender" value="0">
			<input type="submit" value="登録" id="submitBtn">
		</form>
	</div>
<script src="<c:url value='/js/user.js' />"></script>
</body>
</html>