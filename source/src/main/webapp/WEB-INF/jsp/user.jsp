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
    <div id=logo><img></div>
    <a>ユーザー情報登録</a>
    <form action="/E3/UserServlet" method="post">
        <input type="text" name="name" placeholder="氏名"><br>
        <input type="text" name="nickname" placeholder="ニックネーム"><br>
        <input type="radio" name="gender" value = 0>男性
        <input type="radio" name="gender" value = 1>女性
        <input type="radio" name="gender" value = 2>その他<br>
        <input type="text" name="address" placeholder="自宅住所"><br>
        <input type="hidden" name="Smoking" value = 0><br>
        <input type="checkbox" name="noSmoking"value = 0>非喫煙<br>
        <input type="hidden" name="Talking"value = 0><br>
        <input type="checkbox" name="noTalking"value = 0>会話を遠慮する<br>
        <input type="hidden" name=""value = 2>
        <input type="checkbox" name="onlyMale"value = 0>男性希望
        <input type="checkbox" name="onlyFemale"value = 1>女性希望<br>
        <input type="submit" value="登録">
    </form>
</body>
</html>