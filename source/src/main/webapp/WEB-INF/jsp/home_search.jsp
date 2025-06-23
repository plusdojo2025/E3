<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- CSSファイルの読み込み -->
<link rel="stylesheet" href="css/	home_search.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="logo"></div>

	<!-- 通知ボタン -->
	<button id=notification>通知</button>


	<!-- ハンバーガーボタン -->
	<button id="menu" class="menuBtn" aria-label="メニューを開く">
		<span></span> <span></span> <span></span>
	</button>

	<!-- モーダルメニュー -->
	<div id="menuModal" class="menuModal" aria-hidden="true">
		<div class="modal-content" role="dialog" aria-modal="true"
			aria-labelledby="modalTitle">
			<h2 id="modalTitle" class="modal-title"></h2>
			<ul class="menu-list">
				<li><a href="MyRequestServlet">待機情報確認</a></li>
				<li><a href="UserServlet">ユーザー情報</a></li>
				<li><a href="LoginServlet">ログアウト</a></li>
			</ul>
		</div>
	</div>

	<!-- 検索フォーム -->
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
    <label for="number_people">人数</label>
    <input type="number" name="headcount" id="number_people" min="1" max="2">
    <div id="errorHeadcount" class="error-message"></div>
  </div>

  <div>
    <button type="submit">検索</button>
  </div>
  </form>
	<!-- JSファイル読み込み -->
	<script src="js/home_search.js"></script>

</body>
</html>