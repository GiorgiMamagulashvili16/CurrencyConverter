package com.example.currencyconverter_compose.data.model

data class CurrenciesDto(
    val success: Boolean,
    val currencies: HashMap<String,String> = HashMap(),
)