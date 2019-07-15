let webSocket;

function StartChat(){
    document.getElementById("Start").style.visibility = "hidden";
    document.getElementById("Waiting").style.visibility = "visible";
    document.getElementById("WaitingText").style.visibility = "visible";


    let request = new XMLHttpRequest();
    request.open("POST", "/Raiden_war/chat", true);

    request.onload = function(){
        let resp = request.response;
        let port = parseInt(resp);
        if(port === -1){
            setTimeout(StartChat, 1000);
        }

        //TODO get localhost dynamic
        webSocket = new WebSocket("ws://localhost:" + resp + "/point");
        webSocket.onmessage = function(evt) {
            if(evt.data === "Admin"){
                document.getElementById("Waiting").style.visibility = "hidden";
                document.getElementById("WaitingText").style.visibility = "hidden";
                document.getElementById("input").style.visibility = "visible";
                document.getElementById("Send").style.visibility = "visible";
            }else if(evt.data === "SYSTEM: admin left"){
                let element = document.createElement("span");
                element.className = "ForeignMessage";
                element.innerText="(Admin left)";
                document.getElementById("ChatBox").appendChild(element);
                let br = document.createElement("br");
                document.getElementById("ChatBox").appendChild(br);

                document.getElementById("input").style.visibility = "hidden";
                document.getElementById("Send").style.visibility = "hidden";
            } else{
                let element = document.createElement("span");
                element.className = "ForeignMessage";
                element.innerText=evt.data;
                document.getElementById("ChatBox").appendChild(element);
                let br = document.createElement("br");
                document.getElementById("ChatBox").appendChild(br);
            }
        };
    };
    request.send();
}

function SendMessage(){

    let text = document.getElementById("input").value;

    if(text.trim() === ""){
        return;
    }

    let element = document.createElement("span");
    element.className = "MyMessage";
    element.innerText=text;
    document.getElementById("ChatBox").appendChild(element);
    let br = document.createElement("br");
    document.getElementById("ChatBox").appendChild(br);
    webSocket.send(text);
}