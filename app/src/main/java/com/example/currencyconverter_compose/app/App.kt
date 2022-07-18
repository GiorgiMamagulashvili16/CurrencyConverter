package com.example.currencyconverter_compose.app

import android.app.Application
import com.example.currencyconverter_compose.di.appModule
import com.example.currencyconverter_compose.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application (){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, appModule))
        }
    }
}