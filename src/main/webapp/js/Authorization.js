function directToPassword(){
    document.getElementsByName("password")[0].focus();
}

function directToSubmit(){
    document.getElementById("submit").focus();
}

function authorize(){
    let login = document.getElementsByName("login")[0].value;
    let password = document.getElementsByName("password")[0].value;

    let request = new XMLHttpRequest();
    request.open("POST", "/Raiden_war/administrator/authorization?login="+login+"&password="+password, true);
   // request.setParameter();
    request.onload = ()=>{
        switch(request.status){
            case 200:
                location.href="pages/AdminPage.jsp";
                break;
            default: alert(request.status);
        }
    };

    request.send();
}