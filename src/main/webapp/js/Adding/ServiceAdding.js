function sendReloadEvent(){
    let event = new CustomEvent('reload', {
        bubbles: true,
        cancelable: true
    });
    this.dispatchEvent(event);
}