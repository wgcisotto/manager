function bloqCaracteres(){   
	var tecla=window.event.keyCode;  
	var ctrl=window.event.ctrlKey;   
	if (ctrl && tecla==74){
		event.keyCode=0;  
		event.returnValue=false;  
    }
	if((tecla > 47 && tecla < 58)) {
		return true;
	} else {
		if (tecla != 8) {
			return false; 
		}else{
			return true;
		}
    }
}

function changeImage(id_img,src) {  
    document.getElementById(id_img).src=src;  
}

/* Funcao para acionar o evento click do button a partir do seu ID*/
function btnClick(id) {
	document.getElementById(id).click();
}
