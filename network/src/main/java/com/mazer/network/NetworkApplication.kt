package com.mazer.network

import android.app.Application
import com.mazer.network.di.webSocketModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NetworkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NetworkApplication)
            modules(listOf(webSocketModule)) // Adicione outros módulos aqui conforme necessário
        }
    }
}