package com.example.currencyconverter_compose.presentation.curency_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter_compose.domain.usecase.GetCurrenciesUseCase
import com.example.currencyconverter_compose.domain.util.Resource
import com.example.currencyconverter_compose.presentation.curency_screen.states.CurrencyItem
import com.example.currencyconverter_compose.presentation.curency_screen.states.CurrencyScreenState
import com.example.currencyconverter_compose.presentation.curency_screen.states.ErrorDialogState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CurrenciesVm(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {


    private val _screenState = mutableStateOf<CurrencyScreenState>(CurrencyScreenState())
    val screenState : State<CurrencyScreenState> = _screenState

    val currenciesData = mutableStateOf<List<CurrencyItem>>(emptyList())
    val showErrorDialog = mutableStateOf(ErrorDialogState())

    init {
        getCurrencies()
    }


    fun setCurrenciesData(currencyMap: HashMap<String, String>) = viewModelScope.launch {
        val data = mutableListOf<CurrencyItem>()
        for (i in currencyMap) {
            data.add(CurrencyItem(i.key, i.value))
        }
        currenciesData.value = data
    }

    private fun getCurrencies() {
        getCurrenciesUseCase.execute().onEach {
            when (it) {
                is Resource.Loading -> _screenState.value = CurrencyScreenState(isLoading = true)
                is Resource.Error -> _screenState.value =
                    CurrencyScreenState(errorMes = it.errorMessage)
                is Resource.Success -> _screenState.value =
                    CurrencyScreenState(fetchedCurrencies = it.data)
            }
        }.launchIn(viewModelScope)
    }
}
