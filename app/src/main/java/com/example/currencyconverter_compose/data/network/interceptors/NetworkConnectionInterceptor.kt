package com.example.currencyconverter_compose.data.network.interceptors

import com.example.currencyconverter_compose.data.network.exceptions.NetworkConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        if (!isConnected())
//            throw NetworkConnectionException()
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        return try {
            Runtime.getRuntime().exec(COMMAND).waitFor() == PING_TIME
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        private const val COMMAND = "ping -c 1 google.com"
        private const val PING_TIME = 0
    }
}