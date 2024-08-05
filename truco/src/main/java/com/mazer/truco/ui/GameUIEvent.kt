package com.mazer.truco.ui

import com.mazer.common_tools.entities.Card

sealed class GameUIEvent {
    data class JogarCarta(val cardIndex: Int): GameUIEvent()
    data class SendMessageToAll(val message: String): GameUIEvent()
    data object Trucar: GameUIEvent()
    data object FinishGame: GameUIEvent()
}