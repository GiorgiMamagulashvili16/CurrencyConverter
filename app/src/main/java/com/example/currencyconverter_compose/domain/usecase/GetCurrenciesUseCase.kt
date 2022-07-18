package com.example.currencyconverter_compose.domain.usecase

import android.util.Log
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetCurrenciesUseCase(
    private val currenciesRepository: CurrencyRepository
) {

    suspend fun execute() = withContext(Dispatchers.IO) {

    }
}