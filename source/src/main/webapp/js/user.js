'use strict';
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('user_form');
    const output1 = document.getElementById('output1');
    const output2 = document.getElementById('output2');
    const output3 = document.getElementById('output3');

    if (!form || !output1 || !output2 || !output3) {
        console.error('Required elements not found');
        return;
    }

    form.addEventListener('submit', function (event) {
        // イベントオブジェクトの保証
        event = event || window.event;
        event.preventDefault();

        const name = form.name ? form.name.value.trim() : '';
        const nickname = form.nickname ? form.nickname.value.trim() : '';
        const gender = form.gender ? form.gender.value.trim() : '';
        const addressElement = document.getElementById("address");
        const address = addressElement ? addressElement.value.trim() : '';
        const resultDiv = document.getElementById("result");

        if (!resultDiv) {
            console.error('Result div not found');
            return;
        }

        let isValid = true;

        if (name === '') {
            output1.textContent = '氏名を入力してください。';
            isValid = false;
        } else {
            output1.textContent = '';
        }

        if (nickname === '') {
            output2.textContent = 'ニックネームを入力してください。';
            isValid = false;
        } else {
            output2.textContent = '';
        }

        if (gender === '') {
            output3.textContent = '性別を選択してください。';
            isValid = false;
        } else {
            output3.textContent = '';
        }

        if (address === '') {
            alert("住所を入力してください。");
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        const url = 'Geocode?address=' + encodeURIComponent(address);
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data.error) {
                    resultDiv.textContent = "エラー: " + data.error;
                } else {
                    if (form.address_latitude && form.address_longitude) {
                        form.address_latitude.value = String(data.latitude);
                        form.address_longitude.value = String(data.longitude);
                        form.submit();
                    } else {
                        throw new Error('Latitude/Longitude fields not found');
                    }
                }
            })
            .catch(error => {
                resultDiv.textContent = "通信エラーが発生しました。";
                console.error('Error:', error);
            });
    });
});