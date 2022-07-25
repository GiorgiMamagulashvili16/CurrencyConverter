package com.example.currencyconverter_compose.presentation.curency_screen.states

data class ErrorDialogState(
    val message: String? = null,
    val showDialog: Boolean = false
)
