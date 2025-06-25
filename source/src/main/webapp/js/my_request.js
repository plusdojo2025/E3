'use strict';

// 都道府県のコードと県名
const isoToPrefecture = {
  "JP-01": "北海道", "JP-02": "青森県", "JP-03": "岩手県", "JP-04": "宮城県", "JP-05": "秋田県",
  "JP-06": "山形県", "JP-07": "福島県", "JP-08": "茨城県", "JP-09": "栃木県", "JP-10": "群馬県",
  "JP-11": "埼玉県", "JP-12": "千葉県", "JP-13": "東京都", "JP-14": "神奈川県", "JP-15": "新潟県",
  "JP-16": "富山県", "JP-17": "石川県", "JP-18": "福井県", "JP-19": "山梨県", "JP-20": "長野県",
  "JP-21": "岐阜県", "JP-22": "静岡県", "JP-23": "愛知県", "JP-24": "三重県", "JP-25": "滋賀県",
  "JP-26": "京都府", "JP-27": "大阪府", "JP-28": "兵庫県", "JP-29": "奈良県", "JP-30": "和歌山県",
  "JP-31": "鳥取県", "JP-32": "島根県", "JP-33": "岡山県", "JP-34": "広島県", "JP-35": "山口県",
  "JP-36": "徳島県", "JP-37": "香川県", "JP-38": "愛媛県", "JP-39": "高知県", "JP-40": "福岡県",
  "JP-41": "佐賀県", "JP-42": "長崎県", "JP-43": "熊本県", "JP-44": "大分県", "JP-45": "宮崎県",
  "JP-46": "鹿児島県", "JP-47": "沖縄県"
};

// OpenStreetMapの機能で緯度経度から住所を求める処理
function reverseGeocode(lat, lng, url, targetElement) {
	// 緯度経度を取得
	let params = new URLSearchParams();
	params.append('lat', lat);
	params.append('lng', lng);
	
	fetch(url, {			// ReverseGeocodeクラスに飛ばす
		method: 'POST',		// post形式
		body: params		// 取得した緯度経度送信
	})
	.then(function(response) {
		return response.json();		// ReverseGeocodeクラスから取得した住所をjson形式で取得
	})
	.then(function(data) {
		// jsonから都道府県, 市区町村などを取得
		const prefecture = isoToPrefecture[data["ISO3166-2-lvl4"]];
		const city = data.city || data.town || data.village || '';
		const district = data.suburb || data.neighbourhood || '';
		const road = data.road || '';
		const houseNumber = data.house_number || '';
		// 住所の形に整える
		const fullAddress = `${prefecture}${city}${district}${road}${houseNumber}`;
		
		// 整えた住所をモーダルの住所表示欄に表示
		targetElement.textContent = fullAddress;
	})
	.catch(function(e) {
		targetElement.textContent = '取得失敗';
		console.error('リバースジオコーディングエラー:', e);
	});
}

// 項目選択時にモーダル表示処理
document.querySelectorAll('#info').forEach(function(detail) {
	detail.addEventListener('click', function() {
		// 選択された項目のstand_by_id, 現在地と目的地の緯度経度取得
		let id = parseInt(detail.getAttribute('data-id'));
		let cLat = parseFloat(detail.getAttribute('data-c-lat'));	// 現在地　緯度
		let cLng = parseFloat(detail.getAttribute('data-c-lng'));	// 現在地　経度
		let dLat = parseFloat(detail.getAttribute('data-d-lat'));	// 目的地　緯度
		let dLng = parseFloat(detail.getAttribute('data-d-lng'));	// 目的地　経度
		let url = detail.getAttribute('data-url');
		
		// 住所表示用pタグ
		let departurePoint = document.getElementById('departurePoint');		// 現在地
		let destination = document.getElementById('destination');			// 目的地
		
		// OpenStreetMapの機能で緯度経度から住所取得メソッド実行
		Promise.all([
			reverseGeocode(cLat, cLng, url, departurePoint),		// 現在地
			reverseGeocode(dLat, dLng, url, destination)			// 目的地
		]).then(function() {										// 住所取得が完了したらモーダル表示
			// 「.show」クラスを付けてCSS適用
			document.getElementById(id).classList.add("show");
		}).catch(function(e) {
			console.error("住所取得中にエラー:", e);
		})
	});
});

// 解除ボタン押下時に確認ダイアログ表示処理
document.querySelectorAll('#cancel').forEach(function(btn) {
	btn.addEventListener('click', function(event) {
	    // confirm表示　キャンセルの場合はダイアログ消す
	    if (!window.confirm('待機情報を削除します。よろしいですか？')) {
	        event.preventDefault();
	    }
	});
});

// ×ボタン押下時にモーダル閉じる処理
document.querySelectorAll('.close').forEach(function(btn) {
	btn.addEventListener('click', function() {
		const modal = btn.closest('.modal');
		if (modal) {
			modal.classList.remove('show');
		}
	});
});

// モーダル画面外押下時にモーダル閉じる処理
window.addEventListener('click', function(event) {
	if (event.target.classList.contains('modal')) {
    	event.target.classList.remove("show");
  	}
});