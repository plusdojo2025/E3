<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/css/search_result.css' />">
		<title>一覧 | シェアタク</title>
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
		
		<div class="form_flame"> <!-- 絞り込み -->
			<form method="GET" action="SearchResultServlet">
				<input type="hidden" name="same_gender" value="0">
				<input type="checkbox" name="same_gender" value="1">同性希望
				<input type="hidden" name="smoking" value="0">
				<input type="checkbox" name="smoking" value="1">非喫煙
				<input type="hidden" name="talking" value="0">
				<input type="checkbox" name="talking" value="1">会話NG
				<input type="submit" name="search" value="絞り込み"> <!-- 勝手に追加してます！！！！ -->
			</form>
		</div>
		<div class="forCalculation">
			<c:out value="${cLat}"/>
			<c:out value="${cLon}"/>
			<c:out value="${dLat}"/>
			<c:out value="${dLon}"/>
		</div>
		<div class="list">
			<c:forEach var="e" items="${StandByUserJoin}">
				<div class="modal_action"> 
					<c:out value="${e.nickname}"/><br> 

					<c:out value="${e.nickname}"/>りょうきん　到着予定時刻 
				</div>
				<div class="modal" style="visibility: hidden;"> 
					<form method="POST" action="SearchResultServlet" onsubmit="return beforeSubmitReq()">
			            <div class="modal_close"> × </div>
						ニックネーム<c:out value="${e.nickname}"/><br> 
						性別<c:out value="${e.gender}"/>　人数<c:out value="${e.headcount}"/><br> 
						料金<c:out value="${e.nickname}"/>　到着予定時刻<c:out value="${e.nickname}"/><br>
						登録時刻<c:out value="${e.registration_date}"/><br>
						<img src=""> 
						<input type="submit" name="Request" value="申請" class="request">
					</form>
				</div>
			</c:forEach>
		</div>
		<div>
			<form method="POST" action="SearchResultServlet" onsubmit="return beforeSubmitSta()">
				<input type="hidden"><!-- hiddenをたくさんつくりましょうね -->
				<input type="submit" name="stand" value="待機登録">
			</form>
		</div>
	
	<script type="text/javascript" src="/E3/js/search_result.js"></script>

	</body>
</html>