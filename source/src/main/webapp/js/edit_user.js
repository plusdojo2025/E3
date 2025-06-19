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