function getField(name){
    return document.getElementsByName(name)[0].value;
}

function register(){
    let client={
        password: getField("password"),
        fullName: getField("LastName") + " " + getField("FirstName"),
        passportSeries: getField("Series"),
        passportNumber: getField("Number"),
        phoneNumber: getField("Phone"),
        email: getField("Email"),
    };

    if(checkInput(client)){
        sendQuery(client);
    }else{
        alert("Invalid input");
    }

}

function checkInput(client){
    let array = [
        /[A-Za-z0-9]{8,20}/.test(client.password),
        /\w{2,} \w{2,}/.test(client.fullName),
        /[A-Z]{2}/.test(client.passportSeries) || client.passportSeries.length === 0,
        client.passportNumber.toString().length > 5 && client.passportNumber.toString().length < 10,
        client.phoneNumber.toString().length > 10 && client.phoneNumber.toString().length < 14,
        /\w*@\w[\.\w]*/.test(client.email)
    ];

    let result = true;
    for(let condition of array){
        if(!condition){
            result = false;
            break;
        }
    }
    return result;
}

function sendQuery(client){
    let request = new XMLHttpRequest();
    let clientString = JSON.stringify(client);
    request.open("POST", encodeURI("/Raiden_war/administrator/client/adding?client=" + clientString), true);

    request.onload = ()=>{
        if(request.status === 200) {
            location.href = "../Success.html";
        }
    };

    request.send();
}

function update(){
    let client={
        id: getField("client_id"),
        password: "defaultPassword",
        fullName: getField("LastName") + " " + getField("FirstName"),
        passportSeries: getField("Series"),
        passportNumber: getField("Number"),
        phoneNumber: getField("Phone"),
        email: getField("Email"),
    };

    if(checkInput(client)){
        let request = new XMLHttpRequest();
        let clientString = JSON.stringify(client);
        request.open("POST", encodeURI("/Raiden_war/administrator/client/updating?client=" + clientString), true);

        request.onload = ()=>{
            if(request.status === 200) {
                location.href = "../Success.html";
            }
        };

        request.send();
    }else{
        alert("Invalid input");
    }
}