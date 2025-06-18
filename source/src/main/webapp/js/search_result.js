// モーダル
const modal = document.getElementsByClassName('modal');
const action = document.getElementsByClassName('modal_action');
const close = document.getElementsByClassName('modal_close');

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