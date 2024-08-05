package com.mazer.network

class WebSocketRepository(private val webSocketManager: WebSocketManager) {

    fun startWebSocket() {
        webSocketManager.start()
    }

    fun sendMessage(message: String) {
        webSocketManager.sendMessage(message)
    }

    fun closeWebSocket() {
        webSocketManager.close()
    }
}