package com.example.currencyconverter_compose.presentation.curency_screen.states

data class CurrencyScreenState(
    val isLoading: Boolean = false,
    val fetchedCurrencies: HashMap<String, String>? = null,
    val errorMes: String? = null,
    val amountIsConverted: Boolean = false
)
