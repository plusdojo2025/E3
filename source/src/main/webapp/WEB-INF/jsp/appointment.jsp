<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/css/appointment.css' />">
        <script src="<c:url value='/js/appointment.js' />" defer></script>
		<title>予約確認 | シェアタク</title>
	</head>
	<body>
		<header>
			<div name="logo">
				<img src=""> <!-- ロゴ -->
			</div>
			<button name="homeButton">×</button>
		</header>
		
		<main>
			<!-- マッチング相手のニックネームと待ち合わせ時間表示 -->
			<div>
				<a name="nickname">ニックネームさん</a>
				<a name="meetingTime">23:00</a>
			</div>
			
			<!-- 現在地から待ち合わせ場所までの経路表示 -->
			<div name="meetingPoint">
				<img alt="地図" src="">
			</div>
			
			<!-- タクシー会社の情報 -->
			<c:forEach var="taxi" items="${taxiList}">
				<div>
					<p name="taxiCompany"><c:out value="${taxi.company}" />：</p>
					<p name="callTaxi"><a href="tel:<c:out value="${taxi.phone}" />"><c:out value="${taxi.phone}" /></a></p>
					<br>
				</div>
			</c:forEach>
			
			<!-- チャット欄　ルームid, セッションスコープのユーザーid, チャットログ取得用サーブレットのurl保持 -->
			<div name="chatLog" id="chatLog" data-room-id="${roomId }" data-session-id="${sessionScope.id}" data-get-chat-url="<c:url value='/GetChatServlet' />"></div>
			<!-- チャット送信用フォーム -->
			<form method="post" action="<c:url value='/SendChatServlet' />" id="chat_form">
				<select name="selectPhrase" id="selectPhrase">
					<option value="メッセージを選択">メッセージを選択</option>
					<option value="5分ほど遅れます">5分ほど遅れます</option>
					<option value="到着しました">到着しました</option>
				</select>
				<!-- ルームid付与 -->
				<input type="hidden" name="room_id" value="${roomId }">
				<input type="submit" name="sendButton" id="sendButton" value="送信">
			</form>
		</main>
		
		<footer>
			
		</footer>
	</body>
</html>