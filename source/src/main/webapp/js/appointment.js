'use strict';

// マッチング相手のニックネームと到着予定時刻表示欄から自分と相手の現在地取得
let info = document.getElementsByClassName('info')[0];
let myLat = parseFloat(info.getAttribute('data-my-lat'));	// 自分の現在地　緯度
let myLng = parseFloat(info.getAttribute('data-my-lng'));	// 自分の現在地　経度
let pLat = parseFloat(info.getAttribute('data-p-lat'));		// 相手の現在地　緯度
let pLng = parseFloat(info.getAttribute('data-p-lng'));		// 相手の現在地　経度

// 地図描画　中心を自分の現在地に
const map = L.map('meeting_point').setView([myLat, myLng], 16); 
// OpenStreetMapから地図画像を読み込む
var tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
});
tileLayer.addTo(map);

const myPoint = L.latLng(myLat, myLng);		// 自分の現在地　(緯度, 経度)
const pPoint = L.latLng(pLat, pLng);		// 相手の現在地　(緯度, 経度)
let time = 0;								// 所要時間

// 待ち合わせ場所までの所要時間計算メソッド
function calcTime(distance) {
  const averageSpeed = 1.33;		// 徒歩の平均速度　秒速1.33m
  return distance / averageSpeed;
}

// 自分と相手の現在地の緯度経度から中間地点の緯度経度を計算
let midPoint = L.latLng(((myLat + pLat) / 2), ((myLng + pLng) / 2))
// 自分の現在地から中間地点までのルート導出
L.Routing.control({
    waypoints: [myPoint, midPoint],		// 自分の現在地から中間地点
    router: L.Routing.osrmv1({
      serviceUrl: 'https://router.project-osrm.org/route/v1',
      profile: 'foot-walking'					// 徒歩に設定
    }),
    createMarker: () => null,
    addWaypoints: false,
    fitSelectedRoutes: false,
    routeWhileDragging: false,
    show: false,
    lineOptions: {
		styles: [{ color: 'blue', opacity: 1, weight: 5 }]		// ルートを表す線の設定
	}
}).on('routesfound', function(ev) {
	// ルートから距離取得
	const route = ev.routes[0];
	let distance = route.summary.totalDistance;
	
	// 現在時刻取得
	var today = new Date();
	// 距離から所要時間を求め、現在時刻に加える
	time = new Date(today.getTime() + calcTime(distance) * 1000);
	const hours = time.getHours().toString().padStart(2, '0');
	const minutes = time.getMinutes().toString().padStart(2, '0');
	// 到着予定時刻をhh:mm形式にして表示
	document.getElementById('meetingTime').textContent = hours + ":" + minutes;
	
}).addTo(map);
// 自分の現在地, 待ち合わせ場所にピン表示
L.marker(myPoint).addTo(map).bindPopup("現在地", {autoClose:false}).openPopup();
L.marker(midPoint).addTo(map).bindPopup("待ち合わせ場所", {autoClose:false}).openPopup();


// チャットフォーム
let chatForm = document.getElementById('chat_form');
let chatLog = document.getElementById('chatLog');

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
				
				// メッセージの左右振り分け
				// セッションスコープのユーザーidとチャット送信者id比較
				if (chatLog.getAttribute('data-session-id') == d.senderId) {	// ログインユーザーのメッセージ
					// 作成したpタグに自分のメッセージを表すclass属性追加
					div.classList.add('my_fukidashi');
					// 右寄せ用divタグ作成とclass属性追加
					let myChat = document.createElement('div');
					myChat.classList.add('my_chat');
					// 右寄せ用divタグ内に送信日時, 吹き出しを配置
					myChat.appendChild(date);
					myChat.appendChild(div);
					// チャット欄用divタグ内に右寄せ用divタグ配置
					chatLog.appendChild(myChat);
				} else {														// 相手のメッセージ
					// 吹き出し用divタグに相手のメッセージを表すclass属性追加
					div.classList.add('partner_fukidashi');
					// チャット欄用divタグ内に送信日時, 吹き出しを配置
					chatLog.appendChild(date);
					chatLog.appendChild(div);
				}
			}
			// チャット欄の最下部までスクロール
			chatLog.scrollTop = chatLog.scrollHeight;
			
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
		})
		.then(function() {
			// チャットテーブルからレコード取得メソッド実行
			getChat();
		});
	} else {									// デフォルトメッセージが選択されていた場合
		// アラート表示
		alert('メッセージを選択して下さい。')
	}
})

