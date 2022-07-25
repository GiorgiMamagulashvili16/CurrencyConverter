package com.example.currencyconverter_compose.di

import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest: KoinTest {

    @Test
    fun `Test Koin Module`(){
        startKoin {
            modules(listOf(networkModule, appModule))
        }.checkModules()
        stopKoin()
    }
}