package com.application.punkapi.helper

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object Helper {


    fun isOnline(): Boolean {
        try {
            val timeoutMs = 5000
            val sock = Socket()
            val sockaddr = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockaddr, timeoutMs)
            sock.close()

            return true
        } catch (e: IOException) {
            return false
        }
    }

}

