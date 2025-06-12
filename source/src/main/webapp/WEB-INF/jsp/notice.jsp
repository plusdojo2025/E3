<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通知</title>
</head>
<body>

    <div class="logo"></div>

    <button name="homeButton">✖</button>

    <div>
        <!-- モーダルを開く -->
        <div>申請が届きました</div>
        <div>時間</div>
        <div name="nickname">ニックネーム</div>
    </div>

    <!-- モーダル -->
    <div>
        <form>
            <div name="nickname">ニックネーム</div>
            <div name="gender">性別</div>
            <div name="headcount">人数</div>
            <div name="price">￥</div>
            <div name="time">着</div>
            <div name="registrationDate">登録日時</div>
            <div name="route">経路</div>
            <input type="submit" value="承認" name="approval">
            <input type="submit" value="却下" name="rejected">
        </form>
        <button name="close">✖</button>
    </div>
</body>
</html>