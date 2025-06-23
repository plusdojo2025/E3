'use strict';
// チャットフォーム
let chatForm = document.getElementById('chat_form');
let chatLog = document.getElementById('chatLog');

// 地図描画
const map = L.map('meeting_point').setView([36.643238, 138.185428], 20); 
// OpenStreetMap から地図画像を読み込む
var tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
});
tileLayer.addTo(map);

const pointA = L.latLng(36.643238, 138.185428); // A地点
const pointB = L.latLng(36.642931, 138.187182); // B地点
let time = 0;

const control = L.Routing.control({
	waypoints: [pointA, pointB],
	router: L.Routing.osrmv1({
		serviceUrl: 'https://router.project-osrm.org/route/v1',
		profile: 'foot'
	}),
	createMarker: () => null,
	addWaypoints: false,
	routeWhileDragging: false,
	show: false,
	fitSelectedRoutes: false,
	lineOptions: {
    	styles: [] // ← スタイルなしにすると線は描画されない
	}
}).addTo(map);

// 待ち合わせ場所までの所要時間計算メソッド
function calcTime(distance) {
  const averageSpeed = 1.33; // m/s
  return distance / averageSpeed;
}

control.on('routesfound', function(e) {
	const coords = e.routes[0].coordinates;
	const mid = coords[Math.floor(coords.length / 2)];    
	const midpoint = L.latLng(mid.lat, mid.lng);
	L.marker(midpoint).addTo(map).bindPopup("待ち合わせ場所").openPopup();
	
	// ここで pointA → midpoint の所要時間を取得
	L.Routing.control({
	    waypoints: [pointA, midpoint],
	    router: L.Routing.osrmv1({
	      serviceUrl: 'https://router.project-osrm.org/route/v1',
	      profile: 'foot'
	    }),
	    createMarker: () => null,
	    addWaypoints: false,
	    fitSelectedRoutes: false,
	    routeWhileDragging: false,
	    show: false,
	    lineOptions: {
			styles: [{ color: 'blue', opacity: 1, weight: 5 }]
		}
	}).on('routesfound', function(ev) {
		const route = ev.routes[0];
		let distance = route.summary.totalDistance;
		
		// 現在日時取得
		var today = new Date();
		time = new Date(today.getTime() + calcTime(distance) * 1000);
		const hours = time.getHours().toString().padStart(2, '0');
 		const minutes = time.getMinutes().toString().padStart(2, '0');
 		document.getElementById('meetingTime').textContent = hours + ":" + minutes;
		
	}).addTo(map);
});

// チャットテーブルからレコード取得メソッド
function getChat() {
	// GetChatServletのurl取得
	let getChatUrl = chatLog.getAttribute('data-get-chat-url');
	// ルームid取得
	let roomId = chatLog.getAttribute('data-room-id');
	
	// 非同期通信でチャットテーブルからレコード取得
	fetch (getChatUrl + '?roomId=' + encodeURIComponent(roomId))	// GetChatServletのurlにルームid付与
		.then(function(response) {
			return response.json();		// レコードをjson形式で取得
		})
		.then(function(data) {
			chatLog.innerHTML = "";
			
			// json内のデータを1つずつ取り出す
			for (var d of data) {
				// 吹き出し用divタグ作成
				let div = document.createElement('div');
				div.classList.add('fukidashi');
				// 送信日時とメッセージ表示用pタグ作成
				let date = document.createElement('p');
				date.textContent = d.chatDate;
				let chat = document.createElement('p');
				chat.textContent = d.chatText;
				
				// 吹き出し用divタグ内にメッセージ表示用pタグ配置
				div.appendChild(chat);
				
				// セッションスコープのユーザーidとチャット送信者id比較
				if (chatLog.getAttribute('data-session-id') == d.senderId) {	// ログインユーザーが送信したメッセージの場合
					// 作成したpタグにclass属性追加
					//date.setAttribute('class', 'my_chat');
					div.classList.add('my_fukidashi');
					
					let myChat = document.createElement('div');
					myChat.classList.add('my_chat');
					
					myChat.appendChild(date);
					myChat.appendChild(div);
					
					chatLog.appendChild(myChat);
				} else {
					div.classList.add('partner_fukidashi');
					
					// チャット欄用divタグ内に送信日時, 吹き出しを配置
					chatLog.appendChild(date);
					chatLog.appendChild(div);
				}
			}
			
		})
		.catch(function(e) {
			console.error(e);
		})
}

// 予約確認画面表示時にチャットテーブルからレコード取得メソッド実行
window.onload = getChat();

// 5秒毎にチャットテーブルからレコード取得メソッド実行
setInterval(getChat, 5000);


// 送信ボタン押下時のチャット送信処理
document.getElementById('sendButton').addEventListener('click', function(event) {
	// フォーム送信不可
	event.preventDefault();
	
	// セレクトボックスからメッセージ取得
	let chatText = document.getElementById('selectPhrase').value;
	if (chatText != 'メッセージを選択') {		// メッセージが選択されていた場合
		// 送信日時取得
		var today = new Date();
		var yyyy = today.getFullYear();
		var MM = ("0" + (today.getMonth()+1)).slice(-2);
		var dd = ("0" + today.getDate()).slice(-2);
		var hh = ("0" + today.getHours()).slice(-2);
		var mm = ("0" + today.getMinutes()).slice(-2);
		// inputタグ作成して送信日時をフォームに格納
		let chatDate = document.createElement('input');
		chatDate.setAttribute('type', 'hidden');
	    chatDate.setAttribute('name', 'chat_date');
	    chatDate.setAttribute('value', `${yyyy}/${MM}/${dd} ${hh}:${mm}`);
	    chatForm.appendChild(chatDate);
	    
	    // フォーム入力内容格納
	    let formData = new FormData(chatForm);
	    let params = new URLSearchParams(formData);
	    
	    // 非同期通信のチャット登録処理
	    fetch((chatForm.action), {		// SendChatServletのurlにpost形式でフォーム入力内容送信
			method: 'POST',
			body: params
		});
		
		// チャットテーブルからレコード取得メソッド実行
		getChat();
	} else {									// デフォルトメッセージが選択されていた場合
		// テスト用
		console.log('選択して下さい');
	}
})

