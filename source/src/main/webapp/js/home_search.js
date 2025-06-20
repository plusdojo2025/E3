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
	const error = document.getElementById('formError');

	form.addEventListener('submit', (e) => {
		const errors = [];

		if (!date.value) errors.push("希望日時は必須です。");
		if (!current.value.trim()) errors.push("出発地は必須です。");
		if (!drop.value.trim()) errors.push("目的地は必須です。");
		if (!headcount.value || headcount.value < 1 || headcount.value > 2) {
			errors.push("人数は1～2の範囲で入力してください。");
		}

		if (errors.length > 0) {
			e.preventDefault();
			error.innerHTML = errors.join("<br>");
		} else {
			error.innerHTML = "";
		}
	});
});