<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/css/my_request.css' />">
		<title>待機情報確認 | シェアタク</title>
	</head>
	<body>
		<header>
			<div id="header">
				<div id=logo>
					<img src="<c:url value='/img/logo.jpg' />" alt="シェアタク">
				</div>
				<div id="homeButton">
					<button name="homeButton">×</button>
				</div>
			</div>
		</header>
		
		<main>
			<div class="list">
			<!-- 待機情報一覧 -->
			<c:forEach var="info" items="${standByInfoList}">
				<div id="info" data-id="<c:out value="${info.stand_by_id}" />">
					<p>待機登録</p>
					<p name="registrationDate"><c:out value="${info.registration_date}" />　登録</p>
					<p>></p>
				</div>
				
				<!-- 待機情報詳細モーダル -->
				<div class="modal" id="<c:out value="${info.stand_by_id}" />">
					<div class="modal-content">
						<button name="close" id="close">×</button>
						<form method="post" action="<c:url value='MyRequestServlet' />">
				            <p name="getTaxiDate"><c:out value="${info.date}" /></p>
				            <p name="departurePoint">渋谷区道玄坂2</p>
				            <p name="destination">自宅付近</p>
				            <p name="headcount"><c:out value="${info.headcount}" />名</p>
				            <p name="registrationDate"><c:out value="${info.registration_date}" />　登録</p>
				            <input type="hidden" name="stand_by_id" value="<c:out value="${info.stand_by_id}" />">
				            <input type="submit" name="cancel" id="cancel" value="解除">
				        </form>
					</div>
				</div>
			</c:forEach>
			</div>
		</main>
		
		<footer>
			
		</footer>
	<script src="<c:url value='/js/my_request.js' />" defer></script>
	</body>
</html>