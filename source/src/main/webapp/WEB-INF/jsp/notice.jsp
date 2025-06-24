<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>通知 | シェアタク</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/notice.css' />">
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
<script src="https://unpkg.com/leaflet-routing-machine/dist/leaflet-routing-machine.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine/dist/leaflet-routing-machine.css" />

</head>

<body>
    <header>
		<div id="header">
			<div id=logo>
				<img src="<c:url value='/img/logo.jpg' />" alt="シェアタク">
			</div>
			<div id="homeButton">
				<a href="<c:url value='/HomeSearchServlet' />">✖</a>
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
	        <form method="POST" action="<c:url value='/NoticeServlet' />">
	            <input type="hidden" name="id" id="modal-id">
	            <input type="hidden" name="current_latitude" id="modal-cur_lat">
	            <input type="hidden" name="current_longitude" id="modal-cur_long">
	            <input type="hidden" name="drop_off_latitude" id="modal-drop_lat">
	            <input type="hidden" name="drop_off_longitude" id="modal-drop_long">
	            <input type="hidden" name="my_current_latitude" id="modal-my_cur_lat">
	            <input type="hidden" name="my_current_longitude" id="modal-my_cur_long">
	            <input type="hidden" name="my_drop_off_latitude" id="modal-my_drop_lat">
	            <input type="hidden" name="my_drop_off_longitude" id="modal-my_drop_long">
	            <div id="modal-nickname"></div>
	            <div id="modal-gender"></div>
	            <div id="modal-headcount"></div>
	            <div id="modal-registrationDate"></div>
	            <!-- 初乗り￥420 + 255mごとに￥100で計算 -->
	            <div id="modal-price"></div>
	            <!-- 自足40km/hで計算 -->
	            <div id="modal-time"></div>
	            <div id="modal-route">ルート</div>
	            <input type="submit" value="承認" name="submit" onclick="approvalMessage()">
	            <input type="submit" value="却下" name="submit" onclick="rejectedMessage()">
	        </form>
	    </div>
	</div>

	<script src="<c:url value='/js/notice.js' />"></script>
	<!-- jQuery読み込み -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
	
</body>
</html>
