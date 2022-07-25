package com.example.currencyconverter_compose.domain.repository

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import com.example.currencyconverter_compose.data.model.convert.ConvertResponse

interface CurrencyRepository {

    suspend fun getCurrencyList(): CurrenciesDto
    suspend fun convertAmount(to: String, from: String, amount: String): ConvertResponse
}