package com.example.gestiondemusica.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinApp : Application() {
        override fun onCreate() {
            super.onCreate()

            startKoin { // start Koin!
                androidLogger()
                androidContext(this@KoinApp) // declare used Android context
                modules(reproductorModule) // declare modules
            }

        }
    }
