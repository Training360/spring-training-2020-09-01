window.onload = function() {

    let socket = new SockJS('/websocket-endpoint');

    let stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/employees', function (message) {
            let content = JSON.parse(message.body).content;
            console.log(content);
            print(content);
        });
    });

    let button = document.getElementById("message-button");
    button.onclick  = function () {
        let input = document.getElementById("message-input");
        let content = input.value;
        console.log("content = " + content);
        stompClient.send("/app/messages", {}, JSON.stringify({"content": content}));
    };


}

function print(content) {
    let p = document.getElementById("messages-p");
    p.innerHTML = p.innerHTML  + content;
}