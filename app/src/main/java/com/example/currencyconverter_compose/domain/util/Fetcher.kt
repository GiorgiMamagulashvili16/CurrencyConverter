package com.example.currencyconverter_compose.domain.util

import com.example.currencyconverter_compose.data.model.ErrorModel
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException


inline fun <T> fetchData(call: () -> Response<T>): Resource<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(response.errorBody()?.parseErrorBody() ?: "unKnown error")
        }
    } catch (e: IOException) {
        Resource.Error(e.localizedMessage!!)
    }
}

fun ResponseBody.parseErrorBody(): String {
    return Gson().fromJson(this.string(), ErrorModel::class.java).message
}