'use strict';

function approvalMessage() {
    if (!window.confirm('承認しますか？')) {
        alert('キャンセルしました。');
        event.preventDefault();
    } else {
        alert('承認しました。');
    }
}

function rejectedMessage() {
    if (!window.confirm('却下しますか？')) {
        alert('キャンセルしました。');
        event.preventDefault();
    } else {
        alert('却下しました。');
    }
}

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

// データ取得と描画
function fetchData() {
    fetch('AjaxServlet')
    .then(response => response.json())
    .then(data => {
        const container = document.getElementById("listContainer");
        container.innerHTML = "";

        if (Array.isArray(data.reqList)) {
        	container.innerHTML = "<p>データがありません。</p>";
            data.reqList.forEach(item => {
        		container.innerHTML = "";
                const row = document.createElement("div");
                row.className = "request-item modalBtn";
                row.dataset.id = item.id;
                row.dataset.nickname = item.nickname;
                row.dataset.gender = item.gender;
                row.dataset.headcount = item.headcount;
                row.dataset.registrationDate = item.registration_date;
				
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
}, 3000);

window.onload = fetchData;