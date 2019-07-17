function authorizeByContract(){
    let contractNumber = document.getElementById("contractNumber").value;
    let password = document.getElementById("password").value;

    let request = new XMLHttpRequest();
    request.open("POST", "/Raiden_war/client?contractNumber="+contractNumber+"&password="+password, true);
    request.onload = ()=>{
        switch(request.status){
            case 200:
                location.href="/Raiden_war/client";
                break;
            case 204:
                CustomAlert("Incorrect password");
                break;
            case 400:
                CustomAlert("Incorrect contract number");
                break;
            default:
                CustomAlert("Server error");
        }
    };

    request.send();
}

function Logout(){
    let request = new XMLHttpRequest();
    request.open("OPTIONS", "/Raiden_war/client", false);
    request.send();
    location.href="/Raiden_war/";
}