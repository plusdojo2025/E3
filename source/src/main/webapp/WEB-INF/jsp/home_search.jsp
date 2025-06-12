<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="logo"></div>

	<!-- 通知 -->
	<button onclick="　　　">通知</button>

	<!-- メニュー -->
	<div id="menu" style="display: none;">
		<button onclick="location.href='my_request.jsp'">待機情報確認</button>
		<button onclick="location.href='user.jsp'">ユーザ情報</button>
		<button onclick="location.href='login.jsp'">ログアウト</button>
	</div>

	<!-- 検索フォーム -->
	<form action="MatchingServlet" method="post">
		<div>
			希望日時<input type="datetime-local" name="date">
		</div>
		<div>
			出発地<input type="text" name="current_location" maxlength="100">
		</div>
		<div>
			目的地<input type="text" name="drop_off_location" maxlength="100">
		</div>
		<div>
			人数<input type="number" name="headcount" min="1" max="2">
		</div>
		<div>
			<button type="submit">検索</button>
		</div>
	</form>


</body>
</html>