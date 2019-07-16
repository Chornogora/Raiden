let isStyle = false;

function CustomAlert(text){
    if(!isStyle){
        appendStyle();
    }
    appendBackground();
    let panel = createAlertPanel(text);
    document.getElementsByTagName("body")[0].appendChild(panel);
}

function CustomConfirm(text){
    if(!isStyle){
        appendStyle();
    }
    appendBackground();
    let panel = createCustomPanel(text);
    document.getElementsByTagName("body")[0].appendChild(panel);
}

function appendStyle(){
    let link = document.createElement("link");
    link.rel="stylesheet";
    link.href="/Raiden_war/css/Util.css";
    document.getElementsByTagName("head")[0].appendChild(link);
    isStyle = true;
}

function appendBackground(){
    let back = document.createElement("div");
    back.className = "back";
    document.getElementsByTagName("body")[0].appendChild(back);
}

function createAlertPanel(message){
    let panel = document.createElement("div");
    panel.className = "panel";

    let text = document.createElement("span");
    text.innerText = message;
    text.className = "message";
    panel.appendChild(text);

    let button = document.createElement("button");
    button.innerText = "Ok";
    button.className = "button";
    button.onclick = ()=>{
        hidePanel();
        let event = new CustomEvent('finished', {
            bubbles: true,
            cancelable: true,
        });
        this.dispatchEvent(event);
    };
    panel.appendChild(button);
    return panel;
}

function createCustomPanel(message){
    let panel = document.createElement("div");
    panel.className = "panel";

    let text = document.createElement("span");
    text.innerText = message;
    text.className = "message";
    panel.appendChild(text);

    let button = document.createElement("button");
    button.innerText = "Ok";
    button.className = "button";
    button.style.background = "linear-gradient(blue, mediumblue, darkblue)";
    button.style.left = "10%";
    button.onclick = ()=>{
        hidePanel();
        let event = new CustomEvent('ordered', {
            bubbles: true,
            cancelable: true,
            detail: true
        });
        this.dispatchEvent(event);
    };
    panel.appendChild(button);

    let button2 = document.createElement("button");
    button2.innerText = "Cancel";
    button2.className = "button";
    button2.onclick = ()=>{
        hidePanel();
        let event = new CustomEvent('ordered', {
            bubbles: true,
            cancelable: true,
            detail: false
        });
        this.dispatchEvent(event);
    };
    panel.appendChild(button2);

    return panel;
}

function hidePanel(){
    let elem = document.getElementsByClassName("panel")[0];
    document.getElementsByTagName("body")[0].removeChild(elem);
    elem = document.getElementsByClassName("back")[0];
    document.getElementsByTagName("body")[0].removeChild(elem);
}