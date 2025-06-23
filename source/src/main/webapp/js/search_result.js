// モーダル
const modal = document.getElementsByClassName('modal');
const action = document.getElementsByClassName('modal_action');
const close = document.getElementsByClassName('modal_close');
const mycLat = document.getElementById('mycLat');
const mycLon = document.getElementById('mycLon');
const mydLat = document.getElementById('mydLat');
const mydLon = document.getElementById('mydLon');
let pncLat = document.getElementsByClassName('pncLat');
let pncLon = document.getElementsByClassName('pncLon');
let pndLat = document.getElementsByClassName('pndLat');
let pndLon = document.getElementsByClassName('pndLon');
let date = document.getElementsByClassName('date');

for(let i = 0; i < action.length; i++){
	let sLat = (mycLat + pncLat[i]) / 2; 
	let sLon = (mycLon + pncLon[i]) / 2; 
	//const patern1 = distance(sLat, sLon, mydLat, mydLon) + distance(mydLat, mydLon, pndLat, pndLon);
	//const patern2 = distance(sLat, sLon, pndLat, pndLon) + distance(pndLat, pndLon, mydLat, mydLon);
	if(distance(sLat, sLon, mydLat, mydLon) <= distance(sLat, sLon, pndLat, pndLon)){
		document.getElementsByClassName('fee')[i * 2].textContent = culFee(1, sLat, sLon, mydLat, mydLon, pndLat, pndLon);
		document.getElementsByClassName('fee')[i * 2 + 1].textContent = culFee(1, sLat, sLon, mydLat, mydLon, pndLat, pndLon);
		document.getElementsByClassName('time')[i * 2].textContent = culTime(distance(sLat, sLon, mydLat, mydLon), date[i]);
		document.getElementsByClassName('time')[i * 2 + 1].textContent = culTime(distance(sLat, sLon, mydLat, mydLon), date[i]);
	}
	else{
		document.getElementsByClassName('fee')[i * 2].textContent = culFee(2, sLat, sLon, mydLat, mydLon, pndLat, pndLon);
		document.getElementsByClassName('fee')[i * 2 + 1].textContent = culFee(2, sLat, sLon, mydLat, mydLon, pndLat, pndLon);
		document.getElementsByClassName('time')[i * 2].textContent = culTime(distance(sLat, sLon, pndLat, pndLon) + distance(pndLat, pndLon, mydLat, mydLon), date[i]);
		document.getElementsByClassName('time')[i * 2 + 1].textContent = culTime(distance(sLat, sLon, pndLat, pndLon) + distance(pndLat, pndLon, mydLat, mydLon), date[i]);
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
	return fee;
}
function culTime(dis, date){
	let depYear = date.substr(0, 4);
	let depMonth = date.substr(5, 2);
	let depDay = date.substr(8, 2);
	let depHour = date.substr(11, 2);
	let depMinute = date.substr(14, 2);
	let depTime = new Date(depYear + '-' + depMonth + '-' + depDay + '-' + depHour + '-' + depMinute + '-00');
	depTime.setMinutes(depTime.getMinutes() + Math.floor((dis / 40) * 60));
	return depTime.getHours() + ':' + depTime.getMinutes();
}

for(let i = 0; i < action.length; i++){
    action[i].onclick = function(){
        modal[i].style.visibility = "visible";
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