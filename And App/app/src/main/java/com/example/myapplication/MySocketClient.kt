package com.example.myapplication

import android.util.Log
import okhttp3.*
import java.util.concurrent.TimeUnit

class WebSocketClient {
    private val client = OkHttpClient.Builder()
        .build()

    fun connectWebSocket() {
        val request:Request = Request.Builder()
            .url("ws://10.0.2.2:8080")
            .build()

        val listener = EchoWebSocketListener()
        client.newWebSocket(request, listener)
    }

    private inner class EchoWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d("WebSocket", "WebSocket connection opened")
            webSocket.send("Hello, world!")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d("WebSocket", "Message received: $text")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("WebSocket", "WebSocket connection closing")
            webSocket.close(1000, null)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.e("WebSocket", "WebSocket connection failed: ${t.message}")
        }
    }
}
