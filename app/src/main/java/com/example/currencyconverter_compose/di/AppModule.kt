package com.example.currencyconverter_compose.di

import com.example.currencyconverter_compose.data.repository.CurrencyRepositoryImpl
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.usecase.GetCurrenciesUseCase
import org.koin.dsl.module

val appModule = module {

    single<CurrencyRepository> {
        CurrencyRepositoryImpl(get())
    }
    single {
        GetCurrenciesUseCase(get())
    }
}