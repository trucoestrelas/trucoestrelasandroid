package com.mazer.truco.domain

import com.mazer.common_tools.entities.Player

data class GameRules (
    val gameId: String,
    val numPlayers: Int,
    val maxPointsToWin: Int = 12,
    val playerList: List<Player>
)