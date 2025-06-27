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
					<a href="<c:url value='HomeSearchServlet' />" class="close" id="homeBtn">&times;</a>
				</div>
			</div>
		</header>
		
		<main>
			<div class="list">
			<c:choose>
				<c:when test="${!empty standByInfoList}">
					<!-- 待機情報一覧 -->
					<c:forEach var="info" items="${standByInfoList}" varStatus="status">
						<div id="info" data-id="<c:out value="${info.stand_by_id}" />" data-c-lat="${info.current_latitude}" data-c-lng="${info.current_longitude}" data-d-lat="${info.drop_off_latitude}" data-d-lng="${info.drop_off_longitude}" data-url="<c:url value='ReverseGeocode' />">
							<p>待機登録</p>
							<p name="registrationDate"><c:out value="${info.registration_date}" />　登録</p>
							<p>&gt;</p>
							<c:if test="${!status.last}">
								<hr>
							</c:if>
						</div>
						
						<!-- 待機情報詳細モーダル　待機情報id, 現在地と目的地の緯度経度保持 -->
						<div class="modal" id="<c:out value="${info.stand_by_id}" />">
							<div class="modal-content">
								<span id="closeBtn" class="close">&times;</span>
								<form method="post" action="<c:url value='MyRequestServlet' />">
						            <p name="getTaxiDate"><c:out value="${info.date}" /></p>
						            <p name="departurePoint" id="departurePoint_${info.stand_by_id}">現在地</p>
						            <p name="destination" id="destination_${info.stand_by_id}">目的地</p>
						            <p name="headcount"><c:out value="${info.headcount}" />名</p>
						            <p name="registrationDate"><c:out value="${info.registration_date}" />　登録</p>
						            <input type="hidden" name="stand_by_id" value="<c:out value="${info.stand_by_id}" />">
						            <input type="submit" name="cancel" id="cancel" value="解除">
						        </form>
							</div>
						</div>
					</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<p>待機情報はありません</p>
				</c:otherwise>
			</c:choose>
			
		</main>
		
		<footer>
			
		</footer>
	<script src="<c:url value='/js/my_request.js' />" defer></script>
	</body>
</html>