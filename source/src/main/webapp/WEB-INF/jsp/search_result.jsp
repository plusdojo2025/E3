<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧</title>
</head>
<body>
	<div>
		<img src=""> <!-- ロゴ -->
	</div>
	<div>
		× <!-- 閉じるボタン -->
	</div>
	<div> <!-- 絞り込み -->
		<form method="GET" action="E3/SearchResultServlet">
			<input type="hidden" name="same_gender" value="0">
			<input type="checkbox" name="same_gender" value="1">
			<input type="hidden" name="smoking" value="0">
			<input type="checkbox" name="smoking" value="1">
			<input type="hidden" name="talking" value="0">
			<input type="checkbox" name="talking" value="1">
			<input type="submit" name="search" value="検索"> <!-- 勝手に追加してます！！！！ -->
		</form>
	</div>
	<div class="modal_action"> <!-- 検索結果 -->
		ニックネーム<br> <!-- データベースから -->
		料金　到着予定時刻 <!-- データベースから -->
	</div>
	<div class="modal" style="visibility: hidden;"> <!-- モーダル -->
		<form method="POST" action="E3/SearchResultServlet" onsubmit="return beforeSubmitReq()">
            <div class="modal_close"> × </div>
			ニックネーム<br> <!-- データベースから -->
			性別　人数<br> <!-- データベースから -->
			料金　到着予定時刻<br> <!-- データベースから -->
			登録時刻<br> <!-- データベースから -->
			<img src=""> <!-- 経路地図 --> <!-- データベースから -->
			<input type="submit" name="reqSta" value="申請" class="request">
		</form>
	</div>
	<div>
		<form method="POST" action="E3/SearchResultServlet" onsubmit="return beforeSubmitSta()">
			<input type="submit" name="reqSta" value="待機登録">
		</form>
	</div>

<script type="text/javascript" src="/E3/js/search_result.js"></script>

</body>
</html>