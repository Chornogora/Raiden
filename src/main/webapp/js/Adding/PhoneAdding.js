function checkValue(){
    let number = document.getElementById("minutes");
    if(number.value < 0){
        number.value = 0;
    }else if(number.value > 99999){
        number.value = 99999;
    }

    number = document.getElementById("price");
    if(number.value < 0){
        number.value = 0;
    }else if(number.value > 99999){
        number.value = 99999;
    }
}