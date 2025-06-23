<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>通知 | シェアタク</title>
<link rel="stylesheet" type="text/css" href="css/notice.css">
</head>
<body>
    <header>
			<div id="header">
				<div id=logo>
					<img src="<c:url value='/img/logo.jpg' />" alt="シェアタク">
				</div>
				<div id="homeButton">
					<a href="/E3/HomeSearchServlet"><button name="homeButton">×</button></a>
				</div>
			</div>
		</header>
	    <!-- モーダル外に表示されるもの(クリックするとモーダルON) -->
    <div id="listContainer" class="list">
    	<!-- 初期表示は読み込み中 -->
    	<p>読み込み中...</p>
	</div>
	
	<!-- モーダルの中身 -->
	<div id="sharedModal" class="modal" style="display: none;">
	    <div class="modal-content">
	        <span class="closeBtn">✖</span>
	        <form method="POST" action="/E3/NoticeServlet">
	            <input type="hidden" name="id" id="modal-id">
	            <div id="modal-nickname"></div>
	            <div id="modal-gender"></div>
	            <div id="modal-headcount"></div>
	            <div id="modal-registrationDate"></div>
	            <div id="modal-price">価格：￥</div>
	            <div id="modal-time">時間：</div>
	            <div id="modal-route">経路</div>
	            <input type="submit" value="承認" name="submit" onclick="approvalMessage()">
	            <input type="submit" value="却下" name="submit" onclick="rejectedMessage()">
	        </form>
	    </div>
	</div>

	<script src="/webapp/js/notice.js"></script>
	<!-- jQuery読み込み -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</body>
</html>
