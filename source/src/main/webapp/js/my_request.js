'use strict';

// 項目選択時にモーダル表示処理
document.querySelectorAll('#info').forEach(function(detail) {
	detail.addEventListener('click', function() {
		// 選択された項目のstand_by_id取得
		var id = detail.getAttribute('data-id');
		// モーダルを非表示から表示に
		document.getElementById(id).style.display = "block";
		
		// テスト用
		console.log(id);
	})
})

// 解除ボタン押下時に確認ダイアログ表示処理
document.querySelectorAll('#cancel').forEach(function(btn) {
	btn.addEventListener('click', function(event) {
	    // confirm表示　キャンセルの場合はダイアログ消す
	    if (!window.confirm('待機情報を削除します。よろしいですか？')) {
	        event.preventDefault();
	    }
	})
})

// ×ボタン押下時にモーダル閉じる処理
document.querySelectorAll('#close').forEach(function(btn) {
	btn.addEventListener('click', function() {
		var modal = btn.closest('.modal');
	    modal.style.display = "none";
	})
})

// モーダル画面外押下時にモーダル閉じる処理
window.onclick = function(event) {
    if (event.target.className === "modal") {
    	event.target.style.display = "none";
  	}
}