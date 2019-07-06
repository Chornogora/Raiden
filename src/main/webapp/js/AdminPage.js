function logout(){
    let request = new XMLHttpRequest();
    request.open("POST", "/Raiden_war/administrator/logout", false);
    request.send();
}