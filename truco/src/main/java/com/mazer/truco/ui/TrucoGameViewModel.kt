package com.mazer.truco.ui

import androidx.lifecycle.ViewModel
import com.mazer.truco.domain.GameRules
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TrucoGameViewModel(
    private val gameRules: GameRules
): ViewModel() {

    private val _gameState = MutableStateFlow<GameUIState>(GameUIState.Init(gameRules))
    val gameState: StateFlow<GameUIState> = _gameState.asStateFlow()


    private fun updateState(state: GameUIState) {
        _gameState.update { state }
    }

    fun handleEvent(event: GameUIEvent) {
        when (event) {
            is GameUIEvent.FinishGame -> {

            }
            is GameUIEvent.JogarCarta -> {
                val cardIndex = event.cardIndex
            }
            is GameUIEvent.SendMessageToAll -> {
                val message = event.message
            }
            is GameUIEvent.Trucar -> {

            }
        }
    }

}