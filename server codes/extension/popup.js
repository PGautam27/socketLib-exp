
// Here socket is the variable connecting to the websocket server.
let socket = new WebSocket("ws://localhost:8080")

// On Successfull connection establishment it will console socket established.
// You can customise to your requirement
socket.onopen = function(e) {
  console.log("socket established")
};

// This is just a button logic which isn't required
document.addEventListener("DOMContentLoaded", function() {
  document.getElementById("myButton").addEventListener("click", function() {
      alert("Hello, world!");
  });
});
