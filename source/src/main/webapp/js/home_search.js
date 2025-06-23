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

		if (!isValid) {
			e.preventDefault();
		}
	});
});