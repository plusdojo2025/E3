<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/E3/css/appointment.css">
        <script src="/E3/js/appointment.js" defer></script>
		<title>待機情報確認 | シェアタク</title>
	</head>
	<body>
		<header>
			<div name="logo">
				<img src=""> <!-- ロゴ -->
			</div>
			<button name="homeButton">×</button>
		</header>
		
		<main>
			<!-- 待機情報一覧 -->
			<c:forEach var="info" items="${standByInfoList}">
				<div>
					<p>待機登録</p>
					<p name="registrationDate"><c:out value="${info.registration_date}" />　登録</p>
				</div>
				
				<!-- 待機情報詳細モーダル -->
				<div>
					<button name="close">×</button>
					<form method="post" action="<c:url value='MyRequestServlet' />">
			            <p name="getTaxiDate"><c:out value="${info.date}" /></p>
			            <p name="departurePoint">渋谷区道玄坂2</p>
			            <p name="destination">自宅付近</p>
			            <p name="headcount"><c:out value="${info.headcount}" />名</p>
			            <p name="registrationDate"><c:out value="${info.registration_date}" />　登録</p>
			            <input type="hidden" name="stand_by_id" value="<c:out value="${info.stand_by_id}" />">
			            <input type="submit" name="cancel" value="解除">
			        </form>
				</div>
			</c:forEach>
		</main>
		
		<footer>
			
		</footer>
	</body>
</html>