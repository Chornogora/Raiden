function deleteContract(id){
    addEventListener("ordered", (event2)=>{
        if(event2.detail) {

            let request = new XMLHttpRequest();
            request.open("DELETE", "/Raiden_war/administrator/contract?id=" + id, true);

            request.onload = () => {
                location.reload();
            };
            request.send();
        }
    });
    CustomConfirm("Do you really want to delete this contract?");
}