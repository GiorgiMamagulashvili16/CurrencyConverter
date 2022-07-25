package com.example.currencyconverter_compose.domain.repository

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import com.example.currencyconverter_compose.domain.util.Resource

interface CurrencyRepository {

    suspend fun getCurrencyList(): CurrenciesDto
}