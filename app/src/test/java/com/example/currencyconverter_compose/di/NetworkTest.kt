package com.example.currencyconverter_compose.di

import com.example.currencyconverter_compose.data.network.api.CurrencyService
import com.example.currencyconverter_compose.data.network.interceptors.HeaderInterceptor
import com.example.currencyconverter_compose.data.network.interceptors.NetworkConnectionInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import retrofit2.Retrofit
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NetworkTest : KoinTest {

    private val apiService: CurrencyService by inject()
    private val moshi: Moshi by inject()
    private val retrofit: Retrofit by inject()
    private val okHttpClient: OkHttpClient by inject()
    private val baseUrl: String by lazy { get(named("BASE_URL")) }
    private val networkConnectionInterceptor: NetworkConnectionInterceptor by inject()
    private val headerInterceptor: HeaderInterceptor by inject()

    @Before
    fun setup() {
        startKoin {
            modules(
                listOf(networkModule)
            )
        }
    }

    @After
    fun shutDown() {
        stopKoin()
    }

    @Test
    fun `Test Retrofit Instance`() {
        assertNotNull(retrofit)
        assert(baseUrl == "https://api.apilayer.com/currency_data/")
    }

    @Test
    fun `Test Moshi Instance`() {
        assertNotNull(moshi)
    }

    @Test
    fun `Test OkHttpClient Instance`() {
        assertNotNull(okHttpClient)
        assertNotNull(networkConnectionInterceptor)
        assertNotNull(headerInterceptor)
        assertEquals(
            okHttpClient.interceptors,
            listOf(headerInterceptor,networkConnectionInterceptor)
        )
        assertEquals(okHttpClient.interceptors.size, 2)
    }

    @Test
    fun `Test Api Service Instance`() {
        assertNotNull(apiService)
    }
}