function CreateContract() {
    let contract = {};

    let elem = document.getElementById("client");
    let array = elem.getElementsByTagName("option");
    for(let opt of array){
        if(opt.selected){
            contract.userId = opt.id;
        }
    }

    contract.address = document.getElementById("address").value;

    if(document.getElementById("InetIncluded").checked){
        let table = document.getElementById("internet");
        let rows = table.getElementsByTagName("tr");
        for(let i = 1; i < rows.length; ++i){
            let row = rows[i];
            if(row.getElementsByTagName("input")[0].checked){
                contract.internet = row.id;
                break;
            }
        }
    }

    if(document.getElementById("TelevisionIncluded").checked){
        let table = document.getElementById("television");
        let rows = table.getElementsByTagName("tr");
        for(let i = 1; i < rows.length; ++i){
            let row = rows[i];
            if(row.getElementsByTagName("input")[0].checked){
                contract.television = row.id;
                break;
            }
        }
    }

    if(document.getElementById("PhoneIncluded").checked){
        let table = document.getElementById("phone");
        let rows = table.getElementsByTagName("tr");
        for(let i = 1; i < rows.length; ++i){
            let row = rows[i];
            if(row.getElementsByTagName("input")[0].checked){
                contract.phone = row.id;
                break;
            }
        }
    }

    if(document.getElementById("ServiceIncluded").checked){
        let table = document.getElementById("service");
        let rows = table.getElementsByTagName("tr");
        contract.services = [];
        for(let i = 1; i < rows.length; ++i){
            let row = rows[i];
            if(row.getElementsByTagName("input")[0].checked){
                contract.services.push(row.id);
            }
        }
    }

    if(contract.address === undefined || contract.address.length === 0){
        CustomAlert("Address was not entered");
        return;
    }

    if(contract.services === undefined && contract.internet === undefined
        && contract.television === undefined && contract.phone === undefined){
        CustomAlert("Services were not chosen");
        return;
    }
    addContract(contract);

}

function addContract(contract){
    let request = new XMLHttpRequest();
    request.open("PUT", encodeURI("/Raiden_war/administrator/contract/adding?contract="+JSON.stringify(contract)), true);
    request.onload=()=>{
        if(request.status === 200){
            location.href="/Raiden_war/pages/Administrator/Success.html";
        }else if(request.status === 403){
            CustomAlert("Contract were not added. Seems that this account is blocked.");
        }
    };
    request.send();
}