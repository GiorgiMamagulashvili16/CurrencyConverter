package com.example.currencyconverter_compose.domain.usecase.convert

import com.example.currencyconverter_compose.data.model.convert.ConvertResponse
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ConvertAmountUseCase(private val currencyRepository: CurrencyRepository) {

    fun execute(to: String, from: String, amount: String): Flow<Resource<ConvertResponse>> = flow {
        emit(Resource.Loading<ConvertResponse>())

        try {
            val response = currencyRepository.convertAmount(to, from, amount)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error<ConvertResponse>(e.localizedMessage ?: "unknown message"))
        }
    }
}