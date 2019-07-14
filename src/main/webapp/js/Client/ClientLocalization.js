function changeLocale(locale){
    let request = new XMLHttpRequest();
    request.open("POST", "/Raiden_war/client/locale?locale=" + locale, true);
    request.onload=()=>{
        location.reload();
    };
    request.send();
}