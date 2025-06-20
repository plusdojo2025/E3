<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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
			<form method="GET" action="SearchResultServlet">
				<input type="hidden" name="same_gender" value="0">
				<input type="checkbox" name="same_gender" value="1">
				<input type="hidden" name="smoking" value="0">
				<input type="checkbox" name="smoking" value="1">
				<input type="hidden" name="talking" value="0">
				<input type="checkbox" name="talking" value="1">
				<input type="submit" name="search" value="検索"> <!-- 勝手に追加してます！！！！ -->
			</form>
		</div>
		
		<c:forEach var="e" items="${StandByUserJoin}">
			<div class="modal_action"> 
				<c:out value="${e.nickname}"/><br> 
				<c:out value="${e.nickname}"/>りょうきん　到着予定時刻 
				<% out.println(1 + 1); %>
			</div>
			<div class="modal" style="visibility: hidden;"> 
				<form method="POST" action="SearchResultServlet" onsubmit="return beforeSubmitReq()">
		            <div class="modal_close"> × </div>
					ニックネーム<br> 
					性別　人数<br> 
					料金　到着予定時刻<br>
					登録時刻<br>
					<img src=""> 
					<input type="submit" name="Request" value="申請" class="request">
				</form>
			</div>
		</c:forEach>
		<div>
			<form method="POST" action="SearchResultServlet" onsubmit="return beforeSubmitSta()">
				<input type="hidden"><!-- hiddenをたくさんつくりましょうね -->
				<input type="submit" name="Stand" value="待機登録">
			</form>
		</div>
	
	<script type="text/javascript" src="/E3/js/search_result.js"></script>

	</body>
</html>