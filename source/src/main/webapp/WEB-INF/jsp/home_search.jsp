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
				<li><a href="my_request.jsp">待機情報確認</a></li>
				<li><a href="user.jsp">ユーザー情報</a></li>
				<li><a href="login.jsp">ログアウト</a></li>
			</ul>
		</div>
	</div>

	<!-- 検索フォーム -->
	<form id="searchForm" action="MatchingServlet" method="post">
		<div>
			希望日時<input type="datetime-local" name="date" id="desired_date">
		</div>
		<div>
			出発地<input type="text" name="current_location" id="departure_location"
				maxlength="100">
		</div>
		<div>
			目的地<input type="text" name="drop_off_location" id="destination"
				maxlength="100">
		</div>
		<div>
			人数<input type="number" name="headcount" id="number_people" min="1"
				max="2">
		</div>
		<div id="formError"></div>

		<div>
			<button type="submit">検索</button>
		</div>
	</form>

	<!-- JSファイル読み込み -->
	<script src="js/home_search.js"></script>

</body>
</html>