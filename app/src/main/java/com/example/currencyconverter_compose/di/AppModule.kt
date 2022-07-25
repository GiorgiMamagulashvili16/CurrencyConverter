package com.example.currencyconverter_compose.di

import com.example.currencyconverter_compose.data.repository.CurrencyRepositoryImpl
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.usecase.GetCurrenciesUseCase
import com.example.currencyconverter_compose.presentation.curency_screen.CurrenciesVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CurrencyRepository> {
        CurrencyRepositoryImpl(get())
    }
    single {
        GetCurrenciesUseCase(get())
    }

    viewModel {
        CurrenciesVm(get())
    }
}