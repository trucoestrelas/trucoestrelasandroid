package com.mazer.common_tools.entities

data class User (
    val email: String,
    val cpf: String,
    val wallet: Wallet,
    val player: Player
)