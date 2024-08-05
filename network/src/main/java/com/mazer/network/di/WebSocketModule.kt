package com.mazer.network.di

import com.mazer.network.WebSocketManager
import com.mazer.network.WebSocketRepository
import org.koin.dsl.module

val webSocketModule = module {
    single { WebSocketManager() }
    single { WebSocketRepository(get()) }
}