<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/css/appointment.css' />">
        <script src="<c:url value='/css/appointment.js' />" defer></script>
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
				<p name="nickname">ニックネームさん</p>
				<p name="meetingTime">23:00</p>
			</div>
			
			<!-- 現在地から待ち合わせ場所までの経路表示 -->
			<div name="meetingPoint">
				<img alt="地図" src="https://www.google.com/maps/dir/%E3%83%95%E3%82%A1%E3%83%9F%E3%83%AA%E3%83%BC%E3%83%9E%E3%83%BC%E3%83%88+%E4%B8%B8%E3%81%AE%E5%86%85%E3%82%AA%E3%82%A2%E3%82%BE%E5%BA%97/%E3%80%92100-0005+%E6%9D%B1%E4%BA%AC%E9%83%BD%E5%8D%83%E4%BB%A3%E7%94%B0%E5%8C%BA%E4%B8%B8%E3%81%AE%E5%86%85%EF%BC%91%E4%B8%81%E7%9B%AE%EF%BC%99+%E6%9D%B1%E4%BA%AC%E9%A7%85%E4%B8%B8%E3%81%AE%E5%86%85%E5%8C%97%E5%8F%A3%E3%82%BF%E3%82%AF%E3%82%B7%E3%83%BC%E4%B9%97%E3%82%8A%E5%A0%B4/@35.6824514,139.7651272,18z/data=!3m1!5s0x60188bfbd91993eb:0xc9da05c5e162fa6a!4m13!4m12!1m5!1m1!1s0x60188bf90fd8184b:0xe57d333e5e25b316!2m2!1d139.765668!2d35.683042!1m5!1m1!1s0x60188bff1c4fce0b:0x1eb15921fe67cb95!2m2!1d139.7657935!2d35.6819705?entry=ttu&g_ep=EgoyMDI1MDYwOS4wIKXMDSoASAFQAw%3D%3D">
			</div>
			
			<!-- タクシー会社の情報 -->
			<c:forEach var="taxi" items="${taxiList}">
				<div>
					<p name="taxiCompany"><c:out value="${taxi.company}" />：</p>
					<p name="callTaxi"><a href="tel:<c:out value="${taxi.phone}" />"><c:out value="${taxi.phone}" /></a></p>
					<br>
				</div>
			</c:forEach>
			
			<!-- チャット欄 -->
			<div name="chatLog">
				<p>ここにチャット内容を表示</p>
				<p>ここにチャット内容を表示</p>
				<p>ここにチャット内容を表示</p>
			</div>
			<form method="post" action="<c:url value='/AppointmentServlet' />">
				<select name="selectPhrase">
					<option value="メッセージを選択">メッセージを選択</option>
					<option value="5分ほど遅れます">5分ほど遅れます</option>
					<option value="到着しました">到着しました</option>
				</select>
				<input type="hidden" name="room_id" value="">
				<input type="submit" name="sendButton" value="送信">
			</form>
		</main>
		
		<footer>
			
		</footer>
	</body>
</html>