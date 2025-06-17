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
		<form method="GET" action="/E3/SearchResultServlet">
			<input type="checkbox" name="partner_gender" value="man">
			<input type="checkbox" name="partner_gender" value="woman">
			<input type="checkbox" name="smoking" value="no_smoking">
			<input type="checkbox" name="talking" value="no_talking">
			<input type="submit" name="search" value="検索"> <!-- 勝手に追加してます！！！！ -->
		</form>
	</div>
	<div> <!-- 検索結果 -->
		ニックネーム<br> <!-- データベースから -->
		料金　到着予定時刻 <!-- データベースから -->
	</div>
	<div> <!-- モーダル -->
		<form method="POST" action="/E3/SearchResultServlet">
			ニックネーム<br> <!-- データベースから -->
			性別　人数<br> <!-- データベースから -->
			料金　到着予定時刻<br> <!-- データベースから -->
			登録時刻<br> <!-- データベースから -->
			<img src=""> <!-- 経路地図 --> <!-- データベースから -->
			<input type="submit" name="reqSta" value="申請">
		</form>
	</div>
	<div>
		<form method="POST" action="/E3/SearchResultServlet">
			<input type="submit" name="reqSta" value="待機登録">
		</form>
	</div>
</body>
</html>