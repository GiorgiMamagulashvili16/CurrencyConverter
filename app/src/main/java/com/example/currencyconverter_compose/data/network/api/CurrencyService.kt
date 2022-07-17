package com.example.currencyconverter_compose.data.network.api

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {

    @GET("/list")
    suspend fun getCurrencyList(): Response<CurrenciesDto>

}