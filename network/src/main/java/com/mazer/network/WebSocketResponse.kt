package com.mazer.network

import kotlinx.serialization.Serializable

@Serializable
sealed class WebSocketResponse

@Serializable
data class GameState(val state: String, val players: List<String>) : WebSocketResponse()

@Serializable
data class ChatMessage(val sender: String, val message: String) : WebSocketResponse()

@Serializable
data class ErrorResponse(val code: Int, val message: String) : WebSocketResponse()
