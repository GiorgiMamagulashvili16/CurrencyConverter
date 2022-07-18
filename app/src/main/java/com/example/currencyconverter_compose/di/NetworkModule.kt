package com.example.currencyconverter_compose.di

import com.example.currencyconverter_compose.data.network.api.CurrencyService
import com.example.currencyconverter_compose.data.network.interceptors.HeaderInterceptor
import com.example.currencyconverter_compose.data.network.interceptors.NetworkConnectionInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single(named("BASE_URL")) {
        "https://api.apilayer.com/currency_data/"
    }

    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    single {
        HeaderInterceptor()
    }
    single {
        NetworkConnectionInterceptor()
    }
    single {
        provideClient(get(), get())
    }
    single { provideRetrofit(get(named("BASE_URL")), get(),get()) }

    single { provideCurrencyApi(get()) }
}

fun provideRetrofit(baseUrl: String, moshi: Moshi, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideCurrencyApi(retrofit: Retrofit): CurrencyService =
    retrofit.create(CurrencyService::class.java)

fun provideClient(
    headerInterceptor: HeaderInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor
) = OkHttpClient.Builder().addInterceptor(headerInterceptor)
    .addInterceptor(networkConnectionInterceptor).build()