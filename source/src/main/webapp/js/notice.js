'use strict';

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー承認時確認ダイアログーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

function approvalMessage() {
    if (!window.confirm('承認しますか？')) {
        alert('キャンセルしました。');
        event.preventDefault();
    } else {
        alert('承認しました。');
    }
}

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー却下時確認ダイアログーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

function rejectedMessage() {
    if (!window.confirm('却下しますか？')) {
        alert('キャンセルしました。');
        event.preventDefault();
    } else {
        alert('却下しました。');
    }
}

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーモーダル&距離・料金・到着時刻計算ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

let modalOpen = false;

// モーダル開閉イベント
document.addEventListener("click", function (e) {
    const modalBtn = e.target.closest(".modalBtn");

    if (modalBtn) {
        const modal = document.getElementById("sharedModal");

        // データ属性から値を取得
        document.getElementById("modal-id").value = modalBtn.dataset.id;
        document.getElementById("modal-nickname").textContent = "ニックネーム: " + modalBtn.dataset.nickname;
        if(modalBtn.dataset.gender == 1){
        	document.getElementById("modal-gender").textContent = "性別: " + "男性";
		}else{
        	document.getElementById("modal-gender").textContent = "性別: " + "女性";
		}
        document.getElementById("modal-headcount").textContent = "人数: " + modalBtn.dataset.headcount + "人";
        
        let date = new Date(modalBtn.dataset.registrationDate);
        document.getElementById("modal-registrationDate").textContent = "登録日: " + date.toLocaleString();
        document.getElementById("modal-my_cur_lat").textContent = "自分現在地緯度: " + modalBtn.dataset.my_current_latitude;
        document.getElementById("modal-my_cur_long").textContent = "自分現在地経度: " + modalBtn.dataset.my_current_longitude;
        document.getElementById("modal-my_drop_lat").textContent = "自分目的地緯度: " + modalBtn.dataset.my_drop_off_latitude;
        document.getElementById("modal-my_drop_long").textContent = "自分目的地経度: " + modalBtn.dataset.my_drop_off_longitude;
        document.getElementById("modal-cur_lat").textContent = "相手現在地緯度: " + modalBtn.dataset.current_latitude;
        document.getElementById("modal-cur_long").textContent = "相手現在地経度: " + modalBtn.dataset.current_longitude;
        document.getElementById("modal-drop_lat").textContent = "相手目的地緯度: " + modalBtn.dataset.drop_off_latitude;
        document.getElementById("modal-drop_long").textContent = "相手目的地経度: " + modalBtn.dataset.drop_off_longitude;
        
        modal.style.display = "block";
        modalOpen = true;
    }

    if (e.target.classList.contains("closeBtn")) {
        document.getElementById("sharedModal").style.display = "none";
        modalOpen = false;
    }

    if (e.target.id === "sharedModal") {
        e.target.style.display = "none";
        modalOpen = false;
    }
});

// グローバルに map 変数を定義
let map = null;
let routingControl = null;

document.addEventListener("click", function (e) {
    const modalBtn = e.target.closest(".modalBtn");

    if (modalBtn) {
	
        // すでにマップがあるなら破棄
        if (map) {
            map.remove();
            map = null;
        }

        // 地図用の div を毎回初期化
        const mapContainer = document.getElementById("modal-route");
        mapContainer.innerHTML = ""; // 内容クリア
        const newMapDiv = document.createElement("div");
        newMapDiv.id = "map-inner"; // 子div
        newMapDiv.style = "width: 100%; height: 300px;";
        mapContainer.appendChild(newMapDiv);
        
        /* ---------------------------------距離&料金-------------------------------------------- */
        //出発地の経緯度を取得(二人の中間地点)
		const lat1 = (parseFloat(modalBtn.dataset.current_latitude) + parseFloat(modalBtn.dataset.my_current_latitude)) / 2;
		const lng1 = (parseFloat(modalBtn.dataset.current_longitude) + parseFloat(modalBtn.dataset.my_current_longitude)) / 2;
		
		//目的地設定用変数
		var lat2;
		var lng2;
		
		//自分と相手の目的地の経緯度を取得
		const drop1Lat = parseFloat(modalBtn.dataset.drop_off_latitude);
		const drop1Lng = parseFloat(modalBtn.dataset.drop_off_longitude);
		const drop2Lat = parseFloat(modalBtn.dataset.my_drop_off_latitude);
		const drop2Lng = parseFloat(modalBtn.dataset.my_drop_off_longitude);
		
		//出発地から目的地までの距離を比較
		const d1 = distance(lat1, lng1, drop1Lat, drop1Lng) + distance(drop1Lat, drop1Lng, drop2Lat, drop2Lng);
		const d2 = distance(lat1, lng1, drop2Lat, drop2Lng) + distance(drop2Lat, drop2Lng, drop1Lat, drop1Lng);
		
		//中継地点(先に降車)の設定用変数
		var point_lat;
		var point_lng;
		
		if (d1 < d2) {
		    // d1 が先、中継地点 → d2 が目的地
		    point_lat = drop1Lat;
		    point_lng = drop1Lng;
		    lat2 = drop2Lat;
		    lng2 = drop2Lng;
		} else {
		    point_lat = drop2Lat;
		    point_lng = drop2Lng;
		    lat2 = drop1Lat;
		    lng2 = drop1Lng;
		}
		
		var div = distance(lat1, lng1, drop2Lat, drop2Lng) / (distance(lat1, lng1, point_lat, point_lng) + distance(point_lat, point_lng, lat2, lng2));
		
		document.getElementById("modal-price").textContent ="￥" + Math.trunc((420 + ((distance(lat1, lng1, lat2, lng2) * 1000 - 1000) / 255 * 100 )) * div);
		
		/* -------------------------------到着時間表示--------------------------------------------- */
		let time = new Date(modalBtn.dataset.desiredDate);
		time.setMinutes(time.getMinutes() + distance(lat1, lng1, lat2, lng2) * 1000 / 40 / 1000 * 60);
		document.getElementById('modal-time').textContent =
		time.getHours().toString().padStart(2, '0') + ":" + time.getMinutes().toString().padStart(2, '0') + "着";

        const start = L.latLng(lat1, lng1);
        const via = L.latLng(point_lat, point_lng);
        const end = L.latLng(lat2, lng2);

        // 新規マップ作成
        map = L.map('modal-route').setView(start, 1);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);
        
        // 乗車地点マーカーを立てる
		L.marker(start).addTo(map).bindPopup("乗車地点").openPopup();
        
        // 中継地点マーカーを立てる
		L.marker(via).addTo(map).bindPopup("降車地点1").openPopup();
		
        // 目的地点マーカーを立てる
		L.marker(end).addTo(map).bindPopup("降車地点2").openPopup();

        // ルート描画
        routingControl = L.Routing.control({
		    waypoints: [start, via, end],
		    routeWhileDragging: false,
		    addWaypoints: false,
		    createMarker: function() { return null; },
		    fitSelectedRoutes: true,
		    show: false
		}).addTo(map);
	
		const approveBtn = document.getElementById("approveBtn");
		const rejectBtn = document.getElementById("rejectBtn");
		const headcount = document.getElementById("modal-headcount");
		
		if(modalBtn.dataset.type === "req") {
		    approveBtn.style.display = "inline-block";  // 表示
		    rejectBtn.style.display = "inline-block";
		    headcount.style.display = "inline-block";
		} else {
		    approveBtn.style.display = "none";  // 非表示
		    rejectBtn.style.display = "none";
		    headcount.style.display = "none";
		}

        // モーダル表示
        document.getElementById("sharedModal").style.display = "block";
        modalOpen = true;
    }
});

function distance(lat1, lng1, lat2, lng2) {
  	lat1 *= Math.PI / 180;
  	lng1 *= Math.PI / 180;
	lat2 *= Math.PI / 180;
  	lng2 *= Math.PI / 180;
  	return 6371 * Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1) + Math.sin(lat1) * Math.sin(lat2));
}

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー非同期通信ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

function fetchData() {
    fetch('AjaxServlet')
    .then(response => response.json())
    .then(data => {
		//下記のid属性以下を書き換える
        const container = document.getElementById("listContainer");
        container.innerHTML = "";
        
        var count1 = 0;
        var count2 = 0;
        var count3 = 0;

        if (Array.isArray(data.reqList)) {
			if(data.reqList.length > 0){
				count1 =data.reqList.length;
	        	container.innerHTML = "";
	            data.reqList.forEach(item => {
	                const row = document.createElement("div");
	                row.className = "request-item modalBtn";
	                // 申請
					row.dataset.type = "req";
	                //各id属性にデータセット
	                row.dataset.id = item.id;
	                row.dataset.current_latitude = item.current_latitude;
	                row.dataset.current_longitude = item.current_longitude;
	                row.dataset.drop_off_latitude = item.drop_off_latitude;
	                row.dataset.drop_off_longitude = item.drop_off_longitude;
	                row.dataset.my_current_latitude = item.prtnr_current_latitude;
	                row.dataset.my_current_longitude = item.prtnr_current_longitude;
	                row.dataset.my_drop_off_latitude = item.prtnr_drop_off_latitude;
	                row.dataset.my_drop_off_longitude = item.prtnr_drop_off_longitude;
	                row.dataset.nickname = item.nickname;
	                row.dataset.gender = item.gender;
	                row.dataset.headcount = item.headcount;
	                row.dataset.registrationDate = item.registration_date;
	                row.dataset.desiredDate = item.desired_date;
        			let date = new Date(item.desired_date);
        			
					//html描画
	                row.innerHTML = `
	                	<div id="list">
		                    <div>申請が届きました</div>
		                    <div>${item.desired_date}</div>
		                    <div>${item.nickname}</div>
	                    </div>
	                `;
	                container.appendChild(row);
        		});
            }
        }

        if (Array.isArray(data.sendAnsList)) {
			if(data.sendAnsList.length > 0){
				count2 = data.sendAnsList.length;
	            data.sendAnsList.forEach(item => {
	                const row = document.createElement("div");
	                row.className = "request-item modalBtn";
					// 承認された
					row.dataset.type = "sendAns";
	                //各id属性にデータセット
	                row.dataset.id = item.id;
	                row.dataset.current_latitude = item.current_latitude;
	                row.dataset.current_longitude = item.current_longitude;
	                row.dataset.drop_off_latitude = item.drop_off_latitude;
	                row.dataset.drop_off_longitude = item.drop_off_longitude;
	                row.dataset.my_current_latitude = item.prtnr_current_latitude;
	                row.dataset.my_current_longitude = item.prtnr_current_longitude;
	                row.dataset.my_drop_off_latitude = item.prtnr_drop_off_latitude;
	                row.dataset.my_drop_off_longitude = item.prtnr_drop_off_longitude;
	                row.dataset.nickname = item.nickname;
	                row.dataset.gender = item.gender;
	                row.dataset.headcount = item.headcount;
	                row.dataset.registrationDate = item.registration_date;
	                row.dataset.desiredDate = item.desired_date;
					//html描画
	                row.innerHTML = `
	                	<div id="list">
		                    <div>承認されました</div>
		                    <div>${item.desired_date}</div>
		                    <div>${item.nickname}</div>
	                    </div>
	                `;
	                container.appendChild(row);
        		});
            }
        }

        if (Array.isArray(data.respAnsList)) {
			if(data.respAnsList.length > 0){
				count3 = data.respAnsList.length;
	            data.respAnsList.forEach(item => {
	                const row = document.createElement("div");
	                row.className = "request-item modalBtn";
	                // 承認した
					row.dataset.type = "respAns";
	                //各id属性にデータセット
	                row.dataset.id = item.id;
	                row.dataset.current_latitude = item.current_latitude;
	                row.dataset.current_longitude = item.current_longitude;
	                row.dataset.drop_off_latitude = item.drop_off_latitude;
	                row.dataset.drop_off_longitude = item.drop_off_longitude;
	                row.dataset.my_current_latitude = item.prtnr_current_latitude;
	                row.dataset.my_current_longitude = item.prtnr_current_longitude;
	                row.dataset.my_drop_off_latitude = item.prtnr_drop_off_latitude;
	                row.dataset.my_drop_off_longitude = item.prtnr_drop_off_longitude;
	                row.dataset.nickname = item.nickname;
	                row.dataset.gender = item.gender;
	                row.dataset.headcount = item.headcount;
	                row.dataset.registrationDate = item.registration_date;
	                row.dataset.desiredDate = item.desired_date;
					//html描画
	                row.innerHTML = `
	                	<div id="list">
		                    <div>承認しました</div>
		                    <div>${item.desired_date}</div>
		                    <div>${item.nickname}</div>
	                    </div>
	                `;
	                container.appendChild(row);
        		});
            }
        }
        
        if((count1 + count2 + count3 <= 0)){
        	container.innerHTML = "";
			//リストの中身がない(検索結果無し)場合に表示されたままになる
	    	container.innerHTML = "<p>通知なし</p>";
		}
    })
    .catch(err => {
        console.error("データ取得エラー:", err);
    });
}

// モーダルが開いていない時だけ更新
setInterval(() => {
    if (!modalOpen) fetchData();
}, 3000 /* 3000ms */);

window.onload = fetchData;