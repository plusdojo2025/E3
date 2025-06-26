'use strict';
/* エラー処理 */
document.addEventListener('DOMContentLoaded', function () {
	const form = document.getElementById('idpw_form');
	const output1 = document.getElementById('output1');
	const output2 = document.getElementById('output2');
	const output3 = document.getElementById('output3');
	const output4 = document.getElementById('output4');
	  
	form.addEventListener('submit', function (event) {
		event.preventDefault(); // 送信を一旦止める
		
		const email = form.mailaddress.value.trim();
		const password1 = form.loginPW.value.trim();
		const password2 = form.loginPW2.value.trim();
		
		let isValid = true;
		let messages = [];
		
		/* メールアドレスが入力されなかった時の処理 */
		if (email === '') {
		  	output1.textContent = 'メールアドレスを入力してください。';
		  	isValid = false;
		} else {
			output1.textContent = '';
		} 
		/* メールアドレスの文字数が320字以内でない時の処理　*/
		if (email !== '' && email.length > 320) {
			messages.push('メールアドレスは320文字以内で入力してください。');
			isValid = false;
		} 
		/* メールアドレスに'@'が含まれていない時の処理 */
		if (email !== '' && !email.includes('@')) {
			messages.push('有効なメールアドレスを入力してください。');
			isValid = false;
		} 
		
		output2.textContent = messages.join('\n');
		
		/* パスワードが入力されなかった時の処理 */
		if (password1 === '') {
		  	output3.textContent = 'パスワードを入力してください。';
		  	isValid = false;
		} 
		/* パスワードの文字数が8文字以上20文字以内でない時の処理 */
		else if (password1.length < 8 || password1.length > 20) {
			output3.textContent = '8文字以上20文字以内で入力してください。';
			isValid = false;
		} else {
			output3.textContent = '';
		} 
		/* 確認用パスワードが入力されなかった時の処理 */
		if (password2 === '') {
		  	output4.textContent = '確認用パスワードを入力してください。';
		  	isValid = false;
		} 
		/* パスワードと確認用パスワードが一致しなかった時の処理 */
		else if (password1 !== password2) {
			output4.textContent = 'パスワードが一致しません。';
			isValid = false;
		} else {
			output4.textContent = '';
			} 
		
		/* すべてを満たすとき */
		if (isValid) {
			form.submit();
		}
	});
});

//エラー削除
function name_error(){
	document.getElementById("output1").textContent = "";
}

//目のマーク(パスワード)
//  id="view"を取得
let viewicon1 = document.getElementById('view1');

//  id="password"を取得
let inputtype1 = document.getElementById('password1');

//  id="view"クリック時の処理を設定
viewicon1.addEventListener('click', function () {
    if (inputtype1.type === 'password') {
        inputtype1.type = 'text';
        viewicon1.innerHTML = '<i class="far fa-eye"></i>';
    } else {
        inputtype1.type = 'password';
        viewicon1.innerHTML = '<i class="far fa-eye-slash"></i>';
    }
});
//目のマーク(確認用パスワード)
//  id="view"を取得
let viewicon2 = document.getElementById('view2');

//  id="password"を取得
let inputtype2 = document.getElementById('password2');

//  id="view"クリック時の処理を設定
viewicon2.addEventListener('click', function () {
    if (inputtype2.type === 'password') {
        inputtype2.type = 'text';
        viewicon2.innerHTML = '<i class="far fa-eye"></i>';
    } else {
        inputtype2.type = 'password';
        viewicon2.innerHTML = '<i class="far fa-eye-slash"></i>';
    }
});