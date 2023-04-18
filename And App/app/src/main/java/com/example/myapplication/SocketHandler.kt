package com.example.myapplication

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket

    fun setSocket() {
        try {
            mSocket = IO.socket("http://192.168.0.103:3000/")
            Log.v("working","It's working")
        } catch (e: URISyntaxException) {
            Log.v("working","It's not working")
        }
    }

    fun getSocket(): Socket {
        return mSocket
    }

    fun establishConnection() {
        mSocket.connect()
    }

    fun closeConnection() {
        mSocket.disconnect()
    }

}