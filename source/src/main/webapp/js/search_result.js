// モーダル
const modal = document.getElementsByClassName('modal');
const action = document.getElementsByClassName('modal_action');
const close = document.getElementsByClassName('modal_close');
const mycLat = document.getElementById('mycLat').value;
const mycLon = document.getElementById('mycLon').value;
const mydLat = document.getElementById('mydLat').value;
const mydLon = document.getElementById('mydLon').value;
let pncLat = document.getElementsByClassName('pncLat');
let pncLon = document.getElementsByClassName('pncLon');
let pndLat = document.getElementsByClassName('pndLat');
let pndLon = document.getElementsByClassName('pndLon');
let date = document.getElementsByClassName('date');
let gender = document.getElementsByClassName('gender');
let vatogen = document.getElementsByClassName('vatogen');


for(let i = 0; i < action.length; i++){
	let sLat = (parseFloat(mycLat) + parseFloat(pncLat[i].value)) / 2; 
	let sLon = (parseFloat(mycLon) + parseFloat(pncLon[i].value)) / 2; 
console.log(date[i].value);
	//const patern1 = distance(sLat, sLon, mydLat, mydLon) + distance(mydLat, mydLon, pndLat, pndLon);
	//const patern2 = distance(sLat, sLon, pndLat, pndLon) + distance(pndLat, pndLon, mydLat, mydLon);
	if(distance(sLat, sLon, mydLat.value, mydLon.value) <= distance(sLat, sLon, pndLat.value, pndLon.value)){
		document.getElementsByClassName('fee')[i * 2].textContent = culFee(1, sLat, sLon, mydLat, mydLon, pndLat[i].value, pndLon[i].value);
		document.getElementsByClassName('fee')[i * 2 + 1].textContent = culFee(1, sLat, sLon, mydLat, mydLon, pndLat[i].value, pndLon[i].value);
		document.getElementsByClassName('time')[i * 2].textContent = culTime(distance(sLat, sLon, mydLat, mydLon), date[i].value.toString());
		document.getElementsByClassName('time')[i * 2 + 1].textContent = culTime(distance(sLat, sLon, mydLat, mydLon), date[i].value.toString());
	}
	else{
		document.getElementsByClassName('fee')[i * 2].textContent = culFee(2, sLat, sLon, mydLat, mydLon, pndLat[i].value, pndLon[i].value);
		document.getElementsByClassName('fee')[i * 2 + 1].textContent = culFee(2, sLat, sLon, mydLat, mydLon, pndLat[i].value, pndLon[i].value);
		document.getElementsByClassName('time')[i * 2].textContent = culTime(distance(sLat, sLon, pndLat[i].value, pndLon[i].value) + distance(pndLat[i].value, pndLon[i].value, mydLat, mydLon), date[i].value.toString());
		document.getElementsByClassName('time')[i * 2 + 1].textContent = culTime(distance(sLat, sLon, pndLat[i].value, pndLon[i].value) + distance(pndLat[i].value, pndLon[i].value, mydLat, mydLon), date[i].value.toString());
	}
}
function distance(lat1, lng1, lat2, lng2) {
	const R = Math.PI / 180;
	lat1 *= R;
	lng1 *= R;
	lat2 *= R;
	lng2 *= R;
	return 6371 * Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1) + Math.sin(lat1) * Math.sin(lat2));
}
function culFee(paturn, sLat, sLon, mydLat, mydLon, pndLat, pndLon){
	let fee = 420;
	if(paturn === 1){ //自分が先
		fee += Math.floor((distance(sLat, sLon, mydLat, mydLon) + distance(pndLat, pndLon, mydLat, mydLon)) / 0.255) * 100; //255mにつき100円加算
		fee *= distance(sLat, sLon, mydLat, mydLon) / (distance(sLat, sLon, mydLat, mydLon) + distance(pndLat, pndLon, mydLat, mydLon));
	}
	else{ //自分が後
		fee += Math.floor((distance(sLat, sLon, pndLat, pndLon) + distance(pndLat, pndLon, mydLat, mydLon)) / 0.255) * 100;
		fee *= distance(sLat, sLon, pndLat, pndLon) / (distance(sLat, sLon, pndLat, pndLon) + distance(pndLat, pndLon, mydLat, mydLon));
	}
	return parseInt(fee).toLocaleString();
}
function culTime(dis, date){
	let depYear = date.slice(0, 4);
	let depMonth = date.slice(5, 7);
	let depDay = date.slice(8, 10);
	let depHour = date.slice(11, 13);
	let depMinute = date.slice(14, 16);
	let depTime = new Date(depYear + '-' + depMonth + '-' + depDay + 'T' + depHour + ':' + depMinute + ':00');
	depTime.setMinutes(depTime.getMinutes() + Math.floor((dis / 40) * 60));
	console.log(depTime.getHours() + ':' + depTime.getMinutes());
	return depTime.getHours() + ':' + depTime.getMinutes();
}

for(let i = 0; i < action.length; i++){
    action[i].onclick = function(){
			        
		modal[i].style.visibility = "visible";
		
		if(gender[i].value == 0){
			vatogen[i].textContent = "男";
		}
		else if(gender[i].value == 1){
			vatogen[i].textContent = "女";
		}
		else{
			vatogen[i].textContent = "その他";
		}
		let sLat = (parseFloat(mycLat) + parseFloat(pncLat[i].value)) / 2; 
		let sLon = (parseFloat(mycLon) + parseFloat(pncLon[i].value)) / 2; 
		
		var map = L.map('mapid', {
		center: [sLat, sLon],
		zoom: 16,
		}); 
		
		// OpenStreetMap から地図画像を読み込む
		
		var tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
		});
		tileLayer.addTo(map);
		
		// マーカー画像の場所を指定する
		
		L.marker([sLat, sLon]).addTo(map); 

    }
}

for(let i = 0; i < action.length; i++){
    close[i].onclick = function(){
        modal[i].style.visibility = "hidden";
    }
}


// 申請確認
function beforeSubmitReq(){
    if(window.confirm("申請してよろしいですか？")){
        return true;
    }
    else{
        return false;
    }
}
function beforeSubmitSta(){
    if(window.confirm("待機登録してよろしいですか？")){
        return true;
    }
    else{
        return false;
    }
}