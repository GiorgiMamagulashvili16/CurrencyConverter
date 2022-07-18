package com.example.currencyconverter_compose.data.repository

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import com.example.currencyconverter_compose.data.network.api.CurrencyService
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.util.Resource
import com.example.currencyconverter_compose.domain.util.parseErrorBody
import java.io.IOException

class CurrencyRepositoryImpl(
    private val api: CurrencyService
) : CurrencyRepository {

    override suspend fun getCurrencyList(): Resource<CurrenciesDto> {
        return try {
            val response = api.getCurrencyList()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody()?.parseErrorBody() ?: "unKnown error")
            }
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage!!)
        }
    }
}
