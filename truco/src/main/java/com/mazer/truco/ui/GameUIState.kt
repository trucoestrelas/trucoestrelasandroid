package com.mazer.truco.ui

import com.mazer.common_tools.entities.Card
import com.mazer.truco.domain.GameRules

sealed interface GameUIState {
    data class Init(val gameRules: GameRules): GameUIState
    data class MatchStarted(val matchId: Int): GameUIState
    data class RoundStarted(val roundNumber: Int, val lastPlayer: Int, val hands: List<Card>): GameUIState
    data class PlayerTurn(val turnNumber: Int, val playerNumber: Int, val isYourTurn: Boolean): GameUIState
    data class EndingTurn(val winnerPlayer: Int, val teamVictory: Int): GameUIState
    data class RoundEnded(val teamVictory: Int, val totalPointsWon: Int): GameUIState
    data class MatchEnded(val teamVictory: Int, val youWin: Boolean): GameUIState
    data class ChatMessageReceived(val message: String, val from: String): GameUIState
}