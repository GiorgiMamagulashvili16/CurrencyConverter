package com.example.currencyconverter_compose.data.model.convert

data class Query(
    val amount: Int,
    val from: String,
    val to: String
)