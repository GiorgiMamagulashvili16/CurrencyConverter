package com.example.currencyconverter_compose.data.repository

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import com.example.currencyconverter_compose.data.model.convert.ConvertResponse
import com.example.currencyconverter_compose.data.network.api.CurrencyService
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.util.Resource
import com.example.currencyconverter_compose.domain.util.parseErrorBody
import java.io.IOException

class CurrencyRepositoryImpl(
    private val api: CurrencyService
) : CurrencyRepository {

    override suspend fun getCurrencyList(): CurrenciesDto  = api.getCurrencyList()

    override suspend fun convertAmount(to: String, from: String, amount: String): ConvertResponse {
        return api.convertAmount(to, from, amount)
    }
}
