<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='/css/search_result.css' />">
		<title>一覧 | シェアタク</title>
		<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
		   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
		   crossorigin=""/>
		 <!-- Make sure you put this AFTER Leaflet's CSS -->
		 <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
		   integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
		   crossorigin=""></script>
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
			<span id="mycLat"><c:out value="${cLat}"/></span>
			<span id="mycLon"><c:out value="${cLon}"/></span>
			<span id="mydLat"><c:out value="${dLat}"/></span>
			<span id="mydLon"><c:out value="${dLon}"/></span>
		</div>
		<div class="list">
			<c:forEach var="e" items="${StandByUserJoin}">
				<div class="forCalculation">
					<span class="pncLat"><c:out value="${e.current_latitude}"/></span>
					<span class="pncLon"><c:out value="${e.current_longitude}"/></span>
					<span class="pndLat"><c:out value="${e.drop_off_latitude}"/></span>
					<span class="pndLon"><c:out value="${e.drop_off_longitude}"/></span>
					<span class="date"><c:out value="${e.date}"/></span>
				</div>
				<div class="modal_action"> 
					<c:out value="${e.nickname}"/><br> 
					料金：<span class="fee"></span><br>
					到着予定時刻：<span class="time"></span>
				</div>
				<div class="modal" style="visibility: hidden;"> 
					<form method="POST" action="SearchResultServlet" onsubmit="return beforeSubmitReq()">
			            <div class="modal_close"> × </div>
						ニックネーム<c:out value="${e.nickname}"/><br> 
						性別<c:out value="${e.gender}"/>　人数<c:out value="${e.headcount}"/><br> 
						
						料金：<span class="fee"></span>　到着予定時刻:<span class="time"></span><br>
						登録時刻<c:out value="${e.registration_date}"/><br>
						<div id="mapid" style="height:50px"></div>
						<input type="submit" name="Request" value="申請" class="request">
					</form>
				</div>
			</c:forEach>
		</div>
		<div>
			<form method="POST" action="SearchResultServlet" onsubmit="return beforeSubmitSta()">
				<input type="hidden" name="date" value="${date}">
				<input type="hidden" name="current_latitude" value="${cLat}">
				<input type="hidden" name="current_longitude" value="${cLon}">
				<input type="hidden" name="drop_off_latitude" value="${dLat}">
				<input type="hidden" name="drop_off_longitude" value="${dLon}">
				<input type="hidden" name="headcount" value="${headcount}">
				<input type="hidden" name="registration_date" value="rDate">
				<input type="hidden" name="talking" value="${talking}">
				<input type="hidden" name="smoking" value="${smoking}">
				<input type="hidden" name="partner_gender" value="${partner_gender}">
				<input type="submit" name="stand" value="待機登録">
			</form>
		</div>
	
	<script type="text/javascript" src="/E3/js/search_result.js"></script>

	</body>
</html>