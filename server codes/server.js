'use strict';

// do npm i express
const express = require('express');
//do npm i path, this is optional. It helps to generate SVG paths. 
const path = require('path');

//I am using http here, u can require https too. 
const { createServer } = require('http');

// do npm i ws. Also note that this doesn't work in the browser.
// The client must use WebSocket object. It's provided by the javascript.
const { WebSocketServer } = require('ws');

// I am creating the app here
const app = express();
app.use(express.static(path.join(__dirname, '/public')));

// The server is based on http here. You can use https too which is much more secure
const server = createServer(app);

//wss here is the object for the websocket for our server.
// Don't get confused with wss here. It's not WebSocket Secure protocol, it's just a variable name.
const wss = new WebSocketServer({ server });

//we are listening to any client side connection, in order to successfully have the handshake maek.
wss.on('connection', function (ws) {
  const id = setInterval(function () {

    //on connection we just send a process memoryUsage here. You can modify it accordingly for your needs
    ws.send(JSON.stringify(process.memoryUsage()), function () {
      //
      // Ignore errors.
      //
    });
  }, 100);

  //this console just let's us know that the client side handshake is successful
  console.log('started client interval');

  //if we get any error, we console the error
  ws.on('error', console.error);

  //on close or intruption in the handshake. We'll close the connection
  ws.on('close', function () {
    console.log('stopping client interval');
    clearInterval(id);
  });
});

// the server is going to listen to the port 8080
server.listen(8080, function () {
  console.log('Listening on http://localhost:8080');
});
