'use strict';

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー承認時確認ダイアログーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

function approvalMessage() {
    if (!window.confirm('承認しますか？')) {
        alert('キャンセルしました。');
        event.preventDefault();
    } else {
        alert('承認しました。');
    }
}

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー却下時確認ダイアログーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

function rejectedMessage() {
    if (!window.confirm('却下しますか？')) {
        alert('キャンセルしました。');
        event.preventDefault();
    } else {
        alert('却下しました。');
    }
}

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーモーダルーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

let modalOpen = false;

// モーダル開閉イベント
document.addEventListener("click", function (e) {
    const modalBtn = e.target.closest(".modalBtn");

    if (modalBtn) {
        const modal = document.getElementById("sharedModal");

        // データ属性から値を取得
        document.getElementById("modal-id").value = modalBtn.dataset.id;
        document.getElementById("modal-nickname").textContent = "ニックネーム: " + modalBtn.dataset.nickname;
        document.getElementById("modal-gender").textContent = "性別: " + modalBtn.dataset.gender;
        document.getElementById("modal-headcount").textContent = "人数: " + modalBtn.dataset.headcount;
        document.getElementById("modal-registrationDate").textContent = "登録日: " + modalBtn.dataset.registrationDate;

        modal.style.display = "block";
        modalOpen = true;
    }

    if (e.target.classList.contains("closeBtn")) {
        document.getElementById("sharedModal").style.display = "none";
        modalOpen = false;
    }

    if (e.target.id === "sharedModal") {
        e.target.style.display = "none";
        modalOpen = false;
    }
});

/* ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー非同期通信ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー */

function fetchData() {
    fetch('AjaxServlet')
    .then(response => response.json())
    .then(data => {
		//下記のid属性以下を書き換える
        const container = document.getElementById("listContainer");
        container.innerHTML = "";

        if (Array.isArray(data.reqList)) {
			//リストの中身がない(検索結果無し)場合に表示されたままになる
        	container.innerHTML = "<p>データがありません。</p>";
            data.reqList.forEach(item => {
        		container.innerHTML = "";
                const row = document.createElement("div");
                row.className = "request-item modalBtn";
                //各id属性にデータセット
                row.dataset.id = item.id;
                row.dataset.nickname = item.nickname;
                row.dataset.gender = item.gender;
                row.dataset.headcount = item.headcount;
                row.dataset.registrationDate = item.registration_date;
				//html描画
                row.innerHTML = `
                    <div>申請が届きました</div>
                    <div>時間</div>
                    <div>${item.nickname}</div>
                `;
                container.appendChild(row);
            });
        }
    })
    .catch(err => {
        console.error("データ取得エラー:", err);
    });
}

// モーダルが開いていない時だけ更新
setInterval(() => {
    if (!modalOpen) fetchData();
}, 3000 /* 3000ms */);

window.onload = fetchData;