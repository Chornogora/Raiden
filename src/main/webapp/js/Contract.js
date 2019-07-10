function deleteContract(id){
    if(confirm("Do you really want to delete this contract?")){

        let request = new XMLHttpRequest();
        request.open("DELETE", "/Raiden_war/administrator/contract?id=" + id, true);

        request.onload=()=>{
            location.reload();
        };
        request.send();
    }
}