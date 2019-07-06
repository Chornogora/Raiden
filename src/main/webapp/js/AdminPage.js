function logout(){
    let request = new XMLHttpRequest();
    request.open("POST", "/Raiden_war/administrator/logout", false);
    request.send();
}

function openInternet(){
    let workplace = document.getElementById('workplace');
    let addition = document.getElementById('addition');
    workplace.src='/Raiden_war/administrator/internet';
    workplace.onload=()=>{

        workplace.contentWindow.addEventListener("updateInternet", (event)=>{
            addition.src="/Raiden_war/pages/Administrator/Updating/InternetUpdating.jsp?"+
            "id=" + event.detail.id + "&"+
            "name=" + event.detail.name + "&"+
            "speed=" + event.detail.speed + "&"+
            "monthPrice=" + event.detail.monthPrice;
        });

        workplace.contentWindow.addEventListener("addInternet", ()=>{
           addition.src="InternetAdding.html";
        });

        workplace.contentWindow.addEventListener("deleteInternet", (event)=>{
            if(confirm("Do you really want to add this internet tariff?")){
                //alert(event.detail);
                let request = new XMLHttpRequest();
                request.open("DELETE", "/Raiden_war/administrator/internet?id=" + event.detail, true);

                request.onload=()=>{
                    workplace.contentWindow.location.reload();
                };
                request.send();
            }
        })
    }
}