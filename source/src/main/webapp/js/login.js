'use strict';
/* ユーザー名とパスワードが入力されなかった時の処理 */
document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('login_form');
  const output = document.getElementById('output');

  form.addEventListener('submit', function (event) {
    event.preventDefault(); // 送信を一旦止める

    const email = form.mailaddress.value.trim();
    const password = form.loginPW.value.trim();
    
    if (email === '' && password === '') {
		output.textContent = 'メールアドレスとパスワードを入力してください。';
	}else if (email === '') {
      	output.textContent = 'メールアドレスを入力してください。';
    } else if (password === '') {
      	output.textContent = 'パスワードを入力してください。';
    } else {
      	output.textContent = '';
      form.submit(); // 条件を満たしたら送信
    }
  });
});

//目のマーク
//  id="view"を取得
let viewicon = document.getElementById('view');

//  id="password"を取得
let inputtype = document.getElementById('password');

//  id="view"クリック時の処理を設定
viewicon.addEventListener('click', function () {
    if (inputtype.type === 'password') {
        inputtype.type = 'text';
        viewicon.innerHTML = '<i class="far fa-eye"></i>';
    } else {
        inputtype.type = 'password';
        viewicon.innerHTML = '<i class="far fa-eye-slash"></i>';
    }
});