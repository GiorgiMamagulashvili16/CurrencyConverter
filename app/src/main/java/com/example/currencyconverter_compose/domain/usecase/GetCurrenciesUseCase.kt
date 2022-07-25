package com.example.currencyconverter_compose.domain.usecase

import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.util.Resource
import com.example.currencyconverter_compose.presentation.curency_screen.states.CurrencyScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class GetCurrenciesUseCase(
    private val currenciesRepository: CurrencyRepository
) {

    fun execute(): Flow<Resource<HashMap<String,String>>> = flow {
        emit(Resource.Loading<HashMap<String,String>>())
        try {
            val response = currenciesRepository.getCurrencyList()
            emit(Resource.Success(response.currencies))
        }catch (e:Exception){
            emit(Resource.Error<HashMap<String,String>>(e.localizedMessage ?: "unknown error"))
        }
    }
}