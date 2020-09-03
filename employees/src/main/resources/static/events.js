window.onload = function() {
    let evtSource = new EventSource("api/events");
    evtSource.addEventListener("message",
        function(event) {
            let content = JSON.parse(event.data).content;
            let p = document.getElementById("event-p");
            p.innerHTML += content;
        });
};