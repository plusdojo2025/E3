<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="css/idpw.css">
</head>
<body>
    <div id=logo><img></div>
    <a>新規登録</a>
    <form action="/webapp/IdPwServlet" method="post">
        <input type="text" name="mailaddress" placeholder="メールアドレス"><br>
        <input type="password" name="loginPW" placeholder="パスワード"><br>
        <input type="password" name="loginPW2" placeholder="パスワード再入力"><br>
        <input type="submit" value="登録">
    </form>
    <a href="login.jsp">戻る</a>
</body>
</html>