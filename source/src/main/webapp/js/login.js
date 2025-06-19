'use strict';
/* ユーザー名とパスワードが入力されなかった時の処理 */
document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('login_form');
  const output1 = document.getElementById('output1');
  const output2 = document.getElementById('output2');

  form.addEventListener('submit', function (event) {
    event.preventDefault(); // 送信を一旦止める

    const email = form.mailaddress.value.trim();
    const password = form.loginPW.value.trim();
    
    let isValid = true;
    
    if (email === '') {
      	output1.textContent = 'メールアドレスを入力してください。';
      	isValid = false;
    } else {
		output1.textContent = '';
    }
    
    if (password === '') {
      	output2.textContent = 'パスワードを入力してください。';
      	isValid = false;
    } else {
      	output2.textContent = '';
    }
    
    if (isValid) {
			form.submit();
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