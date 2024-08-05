package com.mazer.network


import kotlinx.serialization.decodeFromString
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.ByteString

class WebSocketManager {

    private var client: OkHttpClient? = null
    private var webSocket: WebSocket? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    fun start() {
        client = OkHttpClient()

        val request = Request.Builder().url("ws://10.0.2.2:8000/ws/game/").build()
        webSocket = client?.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "WebSocket opened: ${response.message}")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocket", "Receiving message: $text")
                handleIncomingMessage(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.d("WebSocket", "Receiving bytes: ${bytes.hex()}")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocket", "WebSocket closing: $code / $reason")
                webSocket.close(1000, null)
                webSocket.cancel()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocket", "WebSocket closed: $code / $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.d("WebSocket", "WebSocket error: ${t.message}")
            }
        })
    }

    fun sendMessage(message: String) {
        val jsonMessage = """{"message": $message}""".trimMargin()
        print(jsonMessage)
        scope.launch {
            webSocket?.send(jsonMessage)
        }
    }

    fun close() {
        scope.launch {
            webSocket?.close(1000, "Client closing")
        }
    }

    private fun handleIncomingMessage(text: String) {
        val json = Json { ignoreUnknownKeys = true }
        try {
            when (val response = json.decodeFromString<WebSocketResponse>(text)) {
                is GameState -> handleGameState(response)
                is ChatMessage -> handleChatMessage(response)
                is ErrorResponse -> handleErrorResponse(response)
            }
        } catch (e: Exception) {
            Log.e("teste", "Failed to parse message: ${e.message}")
        }
    }

    private fun handleGameState(gameState: GameState) {
        // Handle the game state message
        Log.d("teste", "Game state: ${gameState.state}, players: ${gameState.players}")
    }

    private fun handleChatMessage(chatMessage: ChatMessage) {
        // Handle the chat message
        Log.d("teste", "Chat message from ${chatMessage.sender}: ${chatMessage.message}")
    }

    private fun handleErrorResponse(errorResponse: ErrorResponse) {
        // Handle the error response
        Log.d("teste", "Error: ${errorResponse.code} - ${errorResponse.message}")
    }
}