 //通知ボタンから通知一覧へ画面遷移
document.getElementById('notification').addEventListener('click', () => {
    location.href = '/E3/NoticeServlet';  
  });

//メニューボタン
document.addEventListener('DOMContentLoaded', () => {
	const menu = document.getElementById('menu');
	const modal = document.getElementById('menuModal');
	const modalContent = modal.querySelector('.modal-content');

	menu.addEventListener('click', () => {
		const isOpen = modal.classList.toggle('show');
		menu.classList.toggle('open', isOpen);
	});
		
	// モーダルの外側をクリックしたらモーダルを閉じる
	window.addEventListener('click', (event) => {
		if (modal.classList.contains('show') && !modalContent.contains(event.target) && !menu.contains(event.target)) {
			modal.classList.remove('show');
			menu.classList.remove('open');
		}
	});
});


//必須項目
document.addEventListener('DOMContentLoaded', () => {
	const form = document.getElementById('searchForm');
	const date = document.getElementById('desired_date');
	const current = document.getElementById('departure_location');
	const drop = document.getElementById('destination');
	const headcount = document.getElementById('number_people');

	const errorDate = document.getElementById('errorDate');
	const errorCurrent = document.getElementById('errorCurrent');
	const errorDrop = document.getElementById('errorDrop');
	const errorHeadcount = document.getElementById('errorHeadcount');

	form.addEventListener('submit', (e) => {
		event.preventDefault(); // フォーム送信を一旦止める
		const departure_location = document.getElementById("departure_location").value.trim(); // ← ★追加！
		const destination = document.getElementById("destination").value.trim(); // ← ★追加！
		const resultDiv = document.getElementById("result"); // ← ★追加！
		let isValid = true;

		// 希望日時
		if (!date.value) {
			errorDate.textContent = '希望日時は必須です。';
			isValid = false;
		} else {
			errorDate.textContent = '';
		}

		// 出発地
		if (!current.value.trim()) {
			errorCurrent.textContent = '出発地は必須です。';
			isValid = false;
		} else {
			errorCurrent.textContent = '';
		}

		// 目的地
		if (!drop.value.trim()) {
			errorDrop.textContent = '目的地は必須です。';
			isValid = false;
		} else {
			errorDrop.textContent = '';
		}

		// 人数
		if (!headcount.value || headcount.value < 1 || headcount.value > 2) {
			errorHeadcount.textContent = '人数は1～2の範囲で入力してください。';
			isValid = false;
		} else {
			errorHeadcount.textContent = '';
		}

		if (departure_location === '') {
			alert("出発地を入力してください。");
			isValid = false;
		}

		if (destination === '') {
			alert("目的地を入力してください。");
			isValid = false;
		}

		if (!isValid) {
			return;
		}

		const url1 = 'Geocode?address=' + encodeURIComponent(departure_location);
		fetch(url1)
			.then(response => response.json())
			.then(data => {
				if (data.error) {
					resultDiv.textContent = "エラー: " + data.error;
				} else {
					form.current_latitude.value = String(data.latitude);
					form.current_longitude.value = String(data.longitude);

					const url2 = 'Geocode?address=' + encodeURIComponent(destination);
					fetch(url2)
						.then(response => response.json())
						.then(data2 => {
							if (data2.error) {
								resultDiv.textContent = "エラー: " + data2.error;
							} else {
								form.drop_off_latitude.value = String(data2.latitude);
								form.drop_off_longitude.value = String(data2.longitude);
								const hiddenSubmit = document.createElement("input");
								hiddenSubmit.type = "hidden";
								hiddenSubmit.name = "search";
								hiddenSubmit.value = "検索";
								form.appendChild(hiddenSubmit);
								form.submit(); // ★ここでページ遷移（送信）！
							}
						})
						.catch(error => {
							resultDiv.textContent = "通信エラーが発生しました";
							console.error(error);
						});
							}
						})
			.catch(error => {
				resultDiv.textContent = "通信エラーが発生しました。";
				console.error(error);
			});
	});
});