<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/css/appointment.css' />">
		<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
		   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
		   crossorigin=""/>
        <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
		   integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
		   crossorigin="" defer></script>
		<link rel="stylesheet"
			href="https://unpkg.com/leaflet-routing-machine/dist/leaflet-routing-machine.css"/>
		<script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.min.js" defer></script>
		<script src="<c:url value='/js/appointment.js' />" defer></script>
		<title>予約確認 | シェアタク</title>
	</head>
	<body>
		<header>
			<div id="header">
				<div id=logo>
					<img src="<c:url value='/img/logo.jpg' />" alt="シェアタク">
				</div>
				<div id="homeButton">
					<button name="homeButton">×</button>
				</div>
			</div>
		</header>
		
		<main>
			<!-- マッチング相手のニックネームと待ち合わせ時間表示　現在地の緯度経度を保持 -->
			<div class="info" data-my-lat="${reqj.current_latitude }" data-my-lng="${reqj.current_longitude }" data-p-lat="${reqj.prtnr_current_latitude}" data-p-lng="${reqj.prtnr_current_longitude}">
				<a name="nickname"><c:out value="${reqj.nickname}" />さん</a>
				<a name="meetingTime" id="meetingTime"></a>
			</div>
			
			<!-- 現在地から待ち合わせ場所までの経路表示 -->
			<div name="meetingPoint" id="meeting_point"></div>
			
			<!-- タクシー会社の情報 -->
			<div class="taxi">
			<c:forEach var="taxi" items="${taxiList}">
				<div>
					<p name="taxiCompany"><c:out value="${taxi.company}" />：</p>
					<p name="callTaxi"><a href="tel:<c:out value="${taxi.phone}" />"><c:out value="${taxi.phone}" /></a></p>
					<br>
				</div>
			</c:forEach>
			</div>
			
			<!-- チャット欄　ルームid, セッションスコープのユーザーid, チャットログ取得用サーブレットのurl保持 -->
			<div name="chatLog" id="chatLog" data-room-id="${roomId }" data-session-id="${sessionScope.id}" data-get-chat-url="<c:url value='/GetChatServlet' />"></div>
			<!-- チャット送信用フォーム -->
			<form method="post" action="<c:url value='/SendChatServlet' />" id="chat_form">
				<select name="selectPhrase" id="selectPhrase">
					<option value="メッセージを選択">メッセージを選択</option>
					<option value="よろしくお願いします">よろしくお願いします</option>
					<option value="ありがとうございました">ありがとうございました</option>
					<option value="到着しました">到着しました</option>
					<option value="タクシーを呼びました">タクシーを呼びました</option>
					<option value="5分ほど遅れます">5分ほど遅れます</option>
					<option value="10分ほど遅れます">10分ほど遅れます</option>
					<option value="お先にどうぞ">お先にどうぞ</option>
					<option value="少しならお待ちできます">少しならお待ちできます</option>
					<option value="ご到着まだでしょうか？">ご到着まだでしょうか？</option>
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