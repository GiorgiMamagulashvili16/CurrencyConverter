package com.example.currencyconverter_compose.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("apikey","H2tMZOSKLJNuHvRiCjLemrG9j00VIEv").build()
        return chain.proceed(request = request)
    }
}