
let socket = new WebSocket("ws://localhost:8080")
        socket.onopen = function(e) {
            console.log("socket established")
};

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("myButton").addEventListener("click", function() {
      alert("Hello, world!");
    });
  });