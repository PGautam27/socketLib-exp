package com.example.myapplication

import android.util.Log
import okhttp3.*

class WebSocketClient {
    private val client = OkHttpClient.Builder()
        .build()

    // Establishing connection
    fun connectWebSocket() {
        // here 10.0.2.2 is your android local host. This will work in your machine too.
        val request:Request = Request.Builder()
            .url("ws://10.0.2.2:8080")
            .build()

        // Here we listen to your connection.
        val listener = EchoWebSocketListener()
        client.newWebSocket(request, listener)
    }

    // the inner class is a nested class here that we use in the parent class.
    private inner class EchoWebSocketListener : WebSocketListener() {

        // on connection we log that the connection is opened. And we send hello world
        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d("WebSocket", "WebSocket connection opened")
            webSocket.send("Hello, world!")
        }

        // We console log the message that we receive from the server.
        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d("WebSocket", "Message received: $text")
        }

        // We console that the socket connection is closing
        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("WebSocket", "WebSocket connection closing")
            webSocket.close(1000, null)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.e("WebSocket", "WebSocket connection failed: ${t.message}")
        }
    }
}
