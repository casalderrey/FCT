package com.example.gestiondemusica.di

import android.app.Application
import com.example.gestiondemusica.di.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(playerModule)
        }

    }
}