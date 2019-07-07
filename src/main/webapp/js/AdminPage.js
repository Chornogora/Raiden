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

function openPhone(){

    let workplace = document.getElementById('workplace');
    let addition = document.getElementById('addition');
    workplace.src='/Raiden_war/administrator/phone';
    workplace.onload=()=>{

        workplace.contentWindow.addEventListener("updatePhone", (event)=>{
            addition.src="/Raiden_war/pages/Administrator/Updating/PhoneUpdating.jsp?"+
                "id=" + event.detail.id + "&"+
                "name=" + event.detail.name + "&"+
                "minutes=" + event.detail.minutes + "&"+
                "monthPrice=" + event.detail.monthPrice;
        });

        workplace.contentWindow.addEventListener("addPhone", ()=>{
            addition.src="PhoneAdding.html";
        });

        workplace.contentWindow.addEventListener("deletePhone", (event)=>{
            if(confirm("Do you really want to add this phone connection tariff?")){

                let request = new XMLHttpRequest();
                request.open("DELETE", "/Raiden_war/administrator/phone?id=" + event.detail, true);

                request.onload=()=>{
                    workplace.contentWindow.location.reload();
                };
                request.send();
            }
        })
    }
}

function openTelevision(){

    let workplace = document.getElementById('workplace');
    let addition = document.getElementById('addition');
    workplace.src='/Raiden_war/administrator/television';
    workplace.onload=()=>{

        workplace.contentWindow.addEventListener("updateTelevision", (event)=>{
            addition.src="/Raiden_war/pages/Administrator/Updating/TelevisionUpdating.jsp?"+
                "id=" + event.detail.id + "&"+
                "name=" + event.detail.name + "&"+
                "channels=" + event.detail.channels + "&"+
                "format=" + event.detail.format+"&"+
                "price=" + event.detail.monthPrice;
        });

        workplace.contentWindow.addEventListener("addTelevision", ()=>{
            addition.src="TelevisionAdding.html";
        });

        workplace.contentWindow.addEventListener("deleteTelevision", (event)=>{
            if(confirm("Do you really want to add this television tariff?")){

                let request = new XMLHttpRequest();
                request.open("DELETE", "/Raiden_war/administrator/television?id=" + event.detail, true);

                request.onload=()=>{
                    workplace.contentWindow.location.reload();
                };
                request.send();
            }
        })
    }
}

function openService(){

    let workplace = document.getElementById('workplace');
    let addition = document.getElementById('addition');
    workplace.src='/Raiden_war/administrator/service';
    workplace.onload=()=>{

        workplace.contentWindow.addEventListener("updateService", (event)=>{
            addition.src="/Raiden_war/pages/Administrator/Updating/ServiceUpdating.jsp?"+
                "id=" + event.detail.id + "&"+
                "name=" + event.detail.name + "&"+
                "measure=" + event.detail.measure + "&"+
                "price=" + event.detail.price;
        });

        workplace.contentWindow.addEventListener("addService", ()=>{
            addition.src="ServiceAdding.html";
        });

        workplace.contentWindow.addEventListener("deleteService", (event)=>{
            if(confirm("Do you really want to add this television tariff?")){

                let request = new XMLHttpRequest();
                request.open("DELETE", "/Raiden_war/administrator/service?id=" + event.detail, true);

                request.onload=()=>{
                    workplace.contentWindow.location.reload();
                };
                request.send();
            }
        })
    }
}

