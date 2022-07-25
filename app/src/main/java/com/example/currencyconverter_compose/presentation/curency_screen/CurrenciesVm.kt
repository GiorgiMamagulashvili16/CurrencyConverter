package com.example.currencyconverter_compose.presentation.curency_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter_compose.domain.usecase.GetCurrenciesUseCase
import com.example.currencyconverter_compose.domain.usecase.convert.ConvertAmountUseCase
import com.example.currencyconverter_compose.domain.util.Resource
import com.example.currencyconverter_compose.presentation.curency_screen.states.ConvertedData
import com.example.currencyconverter_compose.presentation.curency_screen.states.CurrencyItem
import com.example.currencyconverter_compose.presentation.curency_screen.states.CurrencyScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CurrenciesVm(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val convertAmountUseCase: ConvertAmountUseCase
) : ViewModel() {


    private val _screenState = mutableStateOf(CurrencyScreenState())
    val screenState: State<CurrencyScreenState> = _screenState

    val currenciesData = mutableStateOf<List<CurrencyItem>>(emptyList())


    val isFirstCurrencyChecked = mutableStateOf<Boolean?>(null)

    private val _baseCurrency = mutableStateOf(CurrencyItem("USD", "United States Dollar"))
    val baseCurrency: State<CurrencyItem> = _baseCurrency

    private val _secondCurrency = mutableStateOf(CurrencyItem("GEL", "Georgian Lari"))
    val secondCurrency: State<CurrencyItem> = _secondCurrency

    private val _enteredAmount = mutableStateOf<String?>("1")
    val enteredAmount: State<String?> = _enteredAmount

    private val _convertedData = mutableStateOf<ConvertedData?>(null)
    val convertedData: State<ConvertedData?> = _convertedData

    init {
        getCurrencies()
    }

    fun convert() {
        if (enteredAmount.value == null || enteredAmount.value == "0" || enteredAmount.value == "") {
            _screenState.value = CurrencyScreenState(errorMes = "please enter valid amount")
            return
        }
        convertAmountUseCase.execute(
            to = secondCurrency.value.code,
            from = baseCurrency.value.code,
            amount = enteredAmount.value!!
        ).onEach {
            when (it) {
                is Resource.Loading ->
                    _screenState.value = CurrencyScreenState(isLoading = true)
                is Resource.Error ->
                    _screenState.value =
                        CurrencyScreenState(errorMes = it.errorMessage)
                is Resource.Success -> {
                    _screenState.value = CurrencyScreenState(amountIsConverted = true)
                    _convertedData.value = ConvertedData(
                        convertedAmount = it.data?.result.toString(),
                        actualCurrency = it.data?.query?.to!!,
                        ratio = it.data.info.quote.toString()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setAmount(amount: String) {
        _enteredAmount.value = amount
    }

    fun setCurrency(item: CurrencyItem) {
        isFirstCurrencyChecked.value?.let {
            if (it)
                _baseCurrency.value = item
            else
                _secondCurrency.value = item
        }

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
