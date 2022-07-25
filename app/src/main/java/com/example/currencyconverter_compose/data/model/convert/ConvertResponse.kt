package com.example.currencyconverter_compose.data.model.convert

data class ConvertResponse(
    val date: String,
    val historical: Boolean,
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)