'use strict';
/* エラー処理 */
document.addEventListener('DOMContentLoaded', function () {
	const form = document.getElementById('user_form');
	const output1 = document.getElementById('output1');
	const output2 = document.getElementById('output2');
	const output3 = document.getElementById('output3');
	
	form.addEventListener('submit', function (event) {
		event.preventDefault(); // 送信を一旦止める
		
		const name = form.name.value.trim();
		const nickname = form.nickname.value.trim();
		const gender = form.gender.value.trim();
		
		let isValid = true;
		
		/* 氏名が入力されていない時の処理*/
		if (name === '') {
		  	output1.textContent = '氏名を入力してください。';
		  	isValid = false;
		} else {
			output1.textContent = '';
		}
		/* ニックネームが入力されていない時の処理*/
		if (nickname === '') {
		  	output2.textContent = 'ニックネームを入力してください。';
		  	isValid = false;
		} else {
		  	output2.textContent = '';
		}
		/* 性別にチェックがされていない時の処理*/
		if (gender === '') {
		  	output3.textContent = '性別を選択してください。';
		  	isValid = false;
		} else {
		  	output3.textContent = '';
		}
		
		if (isValid) {
			form.submit();
		}
	});
});

function sendAddress() {
	//入力された住所を取得
  	const address = document.getElementById("address").value;
  	if (!address) {
    	alert("住所を入力してください");
    	return;
  	}

  	// URLエンコードはfetchが自動でやってくれないのでencodeURIComponent使う
  	const url = 'Geocode?address=' + encodeURIComponent(address);

  	fetch(url)
	.then(response => response.json())
	.then(data => {
		if (data.error) {
    		document.getElementById('result').textContent = "エラー: " + data.error;
  		} else {
			//文字に変換
	  		data.latitude = String(data.latitude);
	  		data.longitude = String(data.longitude);
	  		//緯度
	  		document.getElementById('address_latitude').textContent =data.latitude;
	  		//経度
	  		document.getElementById('address_longitude').textContent =data.longitude;  
  		}		
	})
	.catch(error => {
  		document.getElementById('result').textContent = "通信エラーが発生しました。";
  		console.error(error);
	});
}