package com.example.currencyconverter_compose.data.network.api

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import com.example.currencyconverter_compose.data.model.convert.ConvertResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("list")
    suspend fun getCurrencyList(): CurrenciesDto

    @GET("convert?")
    suspend fun convertAmount(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: String
    ): ConvertResponse
}