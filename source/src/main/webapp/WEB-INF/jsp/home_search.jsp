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
				<a href="NoticeServlet" id="notification">
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
				<li><a href="MyRequestServlet">▸待機情報確認</a></li>
				<li><a href="EditUserServlet">▸ユーザー情報</a></li>
				<li><a href="LoginServlet">▸ログアウト</a></li>
			</ul>
		</div>
	</div>

	<!-- 検索フォーム -->
	<div class="form_flame">
	<h1>検索</h1>
	<form id="searchForm" action="/E3/SearchResultServlet" method="post">

		 <div class="form-group">
		   <label for="desired_date">希望日時</label>
		   <input type="datetime-local" name="date" id="desired_date">
		   <div id="errorDate" class="error-message"></div>
		 </div>
		
		 <div class="form-group">
		   <label for="departure_location">出発地</label>
		   <input type="text" name="current_location" id="departure_location" maxlength="100">
		   <div id="errorCurrent" class="error-message"></div>
		   </div>
		
		 <div class="form-group">
		   <label for="destination">目的地</label>
		   <input type="text" name="drop_off_location" id="destination" maxlength="100">
		   <div id="errorDrop" class="error-message"></div>
		 </div>
		
		 <div class="form-group">
		   <label for="number_people">人数</label><br>
		   <input type="number" name="headcount" id="number_people" min="1" max="2">人
		   <div id="errorHeadcount" class="error-message"></div>
		 </div>
		
		 <div>
		   <input type="submit" value="検索">
		 </div>
	 </form>
  </div>
	<!-- JSファイル読み込み -->
	<script src="js/home_search.js"></script>

</body>
</html>