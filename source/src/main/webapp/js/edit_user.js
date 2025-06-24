'use strict';
document.addEventListener('DOMContentLoaded', function () {
	const form = document.getElementById('user_form');
	const output1 = document.getElementById('output1');
	const output2 = document.getElementById('output2');
	const output3 = document.getElementById('output3');

	form.addEventListener('submit', function (event) {
		event.preventDefault(); // フォーム送信を一旦止める

		const name = form.name.value.trim();
		const nickname = form.nickname.value.trim();
		const gender = form.gender.value.trim();
		const address = document.getElementById("address").value.trim(); // ← ★追加！
		const resultDiv = document.getElementById("result"); // ← ★追加！

		let isValid = true;

		if (name === '') {
			output1.textContent = '氏名を入力してください。';
			isValid = false;
		} else {
			output1.textContent = '';
		}

		if (nickname === '') {
			output2.textContent = 'ニックネームを入力してください。';
			isValid = false;
		} else {
			output2.textContent = '';
		}

		if (gender === '') {
			output3.textContent = '性別を選択してください。';
			isValid = false;
		} else {
			output3.textContent = '';
		}

		if (address === '') {
			alert("住所を入力してください。");
			isValid = false;
		}

		if (!isValid) {
			return;
		}

		const url = 'Geocode?address=' + encodeURIComponent(address);
		fetch(url)
			.then(response => response.json())
			.then(data => {
				if (data.error) {
					resultDiv.textContent = "エラー: " + data.error;
				} else {
					form.address_latitude.value = String(data.latitude);
					form.address_longitude.value = String(data.longitude);
					form.submit(); // ★ここでページ遷移（送信）！
				}
			})
			.catch(error => {
				resultDiv.textContent = "通信エラーが発生しました。";
				console.error(error);
			});
	});
});
