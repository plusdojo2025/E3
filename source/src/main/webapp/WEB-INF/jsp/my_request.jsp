<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/webapp/css/appointment.css">
        <script src="/webapp/js/appointment.js" defer></script>
		<title>待機情報確認 | シェアタク</title>
	</head>
	<body>
		<header>
			<div>
				<img src=""> <!-- ロゴ -->
			</div>
			<button>×</button>
		</header>
		
		<main>
			<!-- 待機情報一覧 -->
			<div>
				<p>待機登録</p>
				<p>2025/06/09/ 23:00　登録</p>
			</div>
			
			<!-- 待機情報詳細モーダル -->
			<div>
				<button>×</button>
				<form>
		            <p>2025/06/09 23:20</p>
		            <p>渋谷区道玄坂2</p>
		            <p>自宅付近</p>
		            <p>1名</p>
		            <p>2025/06/09 23:00　登録</p>
		            <input type="submit" value="解除">
		        </form>
			</div>
		</main>
		
		<footer>
			
		</footer>
	</body>
</html>