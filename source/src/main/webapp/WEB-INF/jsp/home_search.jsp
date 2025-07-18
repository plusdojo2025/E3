<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- CSSファイルの読み込み -->
<link rel="stylesheet" href="<c:url value='/css/home_search.css' />">
<meta charset="UTF-8">
<title>ホーム| シェアタク</title>
</head>
<body>
	<header>
		<div id="header">
			<div id=logo>
				<img src="<c:url value='/img/logo.jpg' />" alt="シェアタク">
			</div>
			<!-- 通知ボタン -->
			<div id="notification">
				<a href="<c:url value='/NoticeServlet' />" id="notification">
					<img src="img/bell.png" alt="通知">
				</a>
			</div>
			<!-- ハンバーガーボタン -->
			<button id="menu" class="menuBtn" aria-label="メニューを開く">
				<span></span> <span></span> <span></span>
			</button>
		</div>
	</header>

	<!-- モーダルメニュー -->
	<div id="menuModal" class="menuModal" aria-hidden="true">
		<div class="modal-content" role="dialog" aria-modal="true"
			aria-labelledby="modalTitle">
			<h2 id="modalTitle" class="modal-title"></h2>
			<ul class="menu-list">
				<li><a href="<c:url value='/MyRequestServlet' />">▸待機情報確認</a></li>
				<li><a href="<c:url value='/EditUserServlet' />">▸ユーザー情報</a></li>
				<li><a href="<c:url value='/LoginServlet' />">▸ログアウト</a></li>
			</ul>
		</div>
	</div>
	
	<a href="<c:url value='/AppointmentServlet' />" style="text-decoration: none;">
	<div class="appointment">
		<p id="appointment">予約確認はこちら▸</p>
	</div>
</a>

	<!-- 検索フォーム -->
	<div class="form_flame">
	<h1>検索</h1>
	<form id="searchForm" action="<c:url value='/HomeSearchServlet' />" method="post">

		 <div class="form-group">
		   <label for="desired_date">希望日時</label>
		   <input type="datetime-local" name="date" id="desired_date">
		   <div id="errorDate" class="error-message"></div>
		 </div>
		
		 <div class="form-group">
		   <label for="departure_location">出発地</label>
		   <input type="text" name="current_location" id="departure_location" maxlength="100">
		   <input type="hidden" id="current_latitude" name="current_latitude">
		   <input type="hidden" id="current_longitude" name="current_longitude">
		   <div id="errorCurrent" class="error-message"></div>
		   </div>
		
		 <div class="form-group">
		   <label for="destination">目的地</label>
		   <input type="text" name="drop_off_location" id="destination" maxlength="100">
		   <input type="hidden" id="drop_off_latitude" name="drop_off_latitude">
		   <input type="hidden" id="drop_off_longitude" name="drop_off_longitude">
		   <div id="errorDrop" class="error-message"></div>
		 </div>
		 
		 <div id="result"></div>
		
		 <div class="form-group">
		   <label for="number_people">人数</label><br>
		   <input type="number" name="headcount" id="number_people" min="1" max="2">人
		   <div id="errorHeadcount" class="error-message"></div>
		 </div>
		
		 <div>
		   <input type="submit" name="search" value="検索" id="submitBtn">
		 </div>
	 </form>
  </div>
	<!-- JSファイル読み込み -->
<script src="<c:url value='/js/home_search.js' />"></script>

</body>
</html>