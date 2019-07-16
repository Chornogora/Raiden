let nameFlag = "asc";
let numFlag = "asc";

function sendUpdateEvent(id){
    let tr = document.getElementById(id);
    let array = tr.getElementsByTagName("td");
    let event = new CustomEvent('updatePhone', {
        bubbles: true,
        cancelable: true,
        detail: {
            id: id,
            name: array[0].innerText,
            minutes: array[1].innerText,
            monthPrice: array[2].innerText
        }
    });
    this.dispatchEvent(event);
}

function sendAddEvent(){
    let event = new CustomEvent('addPhone', {
        bubbles: true,
        cancelable: true,
        detail: null
    });
    this.dispatchEvent(event);
}

function sendDeleteEvent(id){
    let event = new CustomEvent('deletePhone', {
        bubbles: true,
        cancelable: true,
        detail: id
    });
    this.dispatchEvent(event);
}

function sortByName(){
    let table = document.getElementsByTagName("table")[0];
    let temp = table.getElementsByTagName("tr");
    let rows = [];
    let size = temp.length;
    for(let i = 1; i < size; ++i){
        let row = temp[1];
        rows[i-1] = row;
        table.childNodes[1].removeChild(row);
    }

    let func;
    if(nameFlag === "asc"){
        func = (x, y)=>{
            return (x.getElementsByTagName("td")[0].innerText > y.getElementsByTagName("td")[0].innerText) ? 1 : -1;
        };
        nameFlag = "desc";
    }else{
        func = (x, y)=>{
            return (x.getElementsByTagName("td")[0].innerText < y.getElementsByTagName("td")[0].innerText) ? 1 : -1;
        };
        nameFlag = "asc";
    }
    numFlag = "asc";
    rows.sort(func);

    for(let row of rows){
        table.childNodes[1].appendChild(row);
    }

}

function sortByPrice(){
    let table = document.getElementsByTagName("table")[0];
    let temp = table.getElementsByTagName("tr");
    let rows = [];
    let size = temp.length;
    for(let i = 1; i < size; ++i){
        let row = temp[1];
        rows[i-1] = row;
        table.childNodes[1].removeChild(row);
    }

    let func;
    if(numFlag === "asc"){
        func = (x, y)=>{
            return (parseFloat(x.getElementsByTagName("td")[2].innerText) - parseFloat(y.getElementsByTagName("td")[2].innerText));
        };
        numFlag = "desc";
    }else{
        func = (x, y)=>{
            return (parseFloat(y.getElementsByTagName("td")[2].innerText) - parseFloat(x.getElementsByTagName("td")[2].innerText));
        };
        numFlag = "asc";
    }
    nameFlag = "asc";
    rows.sort(func);

    for(let row of rows){
        table.childNodes[1].appendChild(row);
    }

}