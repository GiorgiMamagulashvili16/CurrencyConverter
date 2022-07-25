package com.example.currencyconverter_compose.di

import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.usecase.GetCurrenciesUseCase
import com.example.currencyconverter_compose.presentation.curency_screen.CurrenciesVm
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import kotlin.test.assertNotNull

class AppModuleTest : KoinTest {

    private val currencyRepo: CurrencyRepository by inject()
    private val currenciesUseCase: GetCurrenciesUseCase by inject()
    private val currenciesVm: CurrenciesVm = get()

    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule,appModule))
        }
    }
    @After
    fun shutDown() {
        stopKoin()
    }

    @Test
    fun `Test repo is not null`() {
        assertNotNull(currencyRepo)
    }

    @Test
    fun `Test use case is not null`() {
        assertNotNull(currenciesUseCase)
    }

    @Test
    fun `Test vm is not null`() {
        assertNotNull(currenciesVm)
    }
}