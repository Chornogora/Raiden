let webSocket;

window.onload=()=>Connect();

function Connect(){
    let request = new XMLHttpRequest();
    request.open("GET", "/Raiden_war/chat", true);

    request.onload = function(){
        let resp = request.response;
        let port = parseInt(resp);
        if(port === -1){
            CustomAlert("No clients");
            addEventListener("finished", ()=> {
                location.href = "/Raiden_war/pages/Administrator/AdminPage.jsp";
            });
        }

        //TODO get localhost dynamic
        webSocket = new WebSocket("ws://localhost:" + resp + "/point");
        webSocket.onmessage = function(evt) {
            if(evt.data === "SYSTEM: client left"){
                let element = document.createElement("span");
                element.className = "ForeignMessage";
                element.innerText="(Client left)";
                document.getElementById("ChatBox").appendChild(element);
                let br = document.createElement("br");
                document.getElementById("ChatBox").appendChild(br);

                document.getElementById("input").style.visibility = "hidden";
                document.getElementById("Send").style.visibility = "hidden";
            }else {
                let element = document.createElement("span");
                element.className = "ForeignMessage";
                element.innerText = evt.data;
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