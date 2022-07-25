package com.example.currencyconverter_compose.data.network

import com.example.currencyconverter_compose.data.model.CurrenciesDto
import com.example.currencyconverter_compose.di.appModule
import com.example.currencyconverter_compose.di.networkModule
import com.example.currencyconverter_compose.domain.repository.CurrencyRepository
import com.example.currencyconverter_compose.enqueueResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

class TestNetworkCall : KoinTest {

    private val mockWebServer by lazy { MockWebServer() }
    private val repository: CurrencyRepository by inject()

    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule, appModule))
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch currencies correctly given 200 response`() {
        mockWebServer.enqueueResponse("get_currencies_response_200.json", 200)

        runBlocking {
            val actualResponse = repository.getCurrencyList()
            val exceptedResponse = CurrenciesDto(
                success = true,
                currencies = currencyModel
            )
            assertEquals(exceptedResponse, actualResponse)
        }
    }

}

private val currencyModel = hashMapOf(
    "AED" to "United Arab Emirates Dirham",
    "AFN" to "Afghan Afghani",
    "ALL" to "Albanian Lek",
    "AMD" to "Armenian Dram",
    "ANG" to "Netherlands Antillean Guilder",
    "AOA" to "Angolan Kwanza",
    "ARS" to "Argentine Peso",
    "AUD" to "Australian Dollar",
    "AWG" to "Aruban Florin",
    "AZN" to "Azerbaijani Manat",
    "BAM" to "Bosnia-Herzegovina Convertible Mark",
    "BBD" to "Barbadian Dollar",
    "BDT" to "Bangladeshi Taka",
    "BGN" to "Bulgarian Lev",
    "BHD" to "Bahraini Dinar",
    "BIF" to "Burundian Franc",
    "BMD" to "Bermudan Dollar",
    "BND" to "Brunei Dollar",
    "BOB" to "Bolivian Boliviano",
    "BRL" to "Brazilian Real",
    "BSD" to "Bahamian Dollar",
    "BTC" to "Bitcoin",
    "BTN" to "Bhutanese Ngultrum",
    "BWP" to "Botswanan Pula",
    "BYN" to "New Belarusian Ruble",
    "BYR" to "Belarusian Ruble",
    "BZD" to "Belize Dollar",
    "CAD" to "Canadian Dollar",
    "CDF" to "Congolese Franc",
    "CHF" to "Swiss Franc",
    "CLF" to "Chilean Unit of Account (UF)",
    "CLP" to "Chilean Peso",
    "CNY" to "Chinese Yuan",
    "COP" to "Colombian Peso",
    "CRC" to "Costa Rican Colón",
    "CUC" to "Cuban Convertible Peso",
    "CUP" to "Cuban Peso",
    "CVE" to "Cape Verdean Escudo",
    "CZK" to "Czech Republic Koruna",
    "DJF" to "Djiboutian Franc",
    "DKK" to "Danish Krone",
    "DOP" to "Dominican Peso",
    "DZD" to "Algerian Dinar",
    "EGP" to "Egyptian Pound",
    "ERN" to "Eritrean Nakfa",
    "ETB" to "Ethiopian Birr",
    "EUR" to "Euro",
    "FJD" to "Fijian Dollar",
    "FKP" to "Falkland Islands Pound",
    "GBP" to "British Pound Sterling",
    "GEL" to "Georgian Lari",
    "GGP" to "Guernsey Pound",
    "GHS" to "Ghanaian Cedi",
    "GIP" to "Gibraltar Pound",
    "GMD" to "Gambian Dalasi",
    "GNF" to "Guinean Franc",
    "GTQ" to "Guatemalan Quetzal",
    "GYD" to "Guyanaese Dollar",
    "HKD" to "Hong Kong Dollar",
    "HNL" to "Honduran Lempira",
    "HRK" to "Croatian Kuna",
    "HTG" to "Haitian Gourde",
    "HUF" to "Hungarian Forint",
    "IDR" to "Indonesian Rupiah",
    "ILS" to "Israeli New Sheqel",
    "IMP" to "Manx pound",
    "INR" to "Indian Rupee",
    "IQD" to "Iraqi Dinar",
    "IRR" to "Iranian Rial",
    "ISK" to "Icelandic Króna",
    "JEP" to "Jersey Pound",
    "JMD" to "Jamaican Dollar",
    "JOD" to "Jordanian Dinar",
    "JPY" to "Japanese Yen",
    "KES" to "Kenyan Shilling",
    "KGS" to "Kyrgystani Som",
    "KHR" to "Cambodian Riel",
    "KMF" to "Comorian Franc",
    "KPW" to "North Korean Won",
    "KRW" to "South Korean Won",
    "KWD" to "Kuwaiti Dinar",
    "KYD" to "Cayman Islands Dollar",
    "KZT" to "Kazakhstani Tenge",
    "LAK" to "Laotian Kip",
    "LBP" to "Lebanese Pound",
    "LKR" to "Sri Lankan Rupee",
    "LRD" to "Liberian Dollar",
    "LSL" to "Lesotho Loti",
    "LTL" to "Lithuanian Litas",
    "LVL" to "Latvian Lats",
    "LYD" to "Libyan Dinar",
    "MAD" to "Moroccan Dirham",
    "MDL" to "Moldovan Leu",
    "MGA" to "Malagasy Ariary",
    "MKD" to "Macedonian Denar",
    "MMK" to "Myanma Kyat",
    "MNT" to "Mongolian Tugrik",
    "MOP" to "Macanese Pataca",
    "MRO" to "Mauritanian Ouguiya",
    "MUR" to "Mauritian Rupee",
    "MVR" to "Maldivian Rufiyaa",
    "MWK" to "Malawian Kwacha",
    "MXN" to "Mexican Peso",
    "MYR" to "Malaysian Ringgit",
    "MZN" to "Mozambican Metical",
    "NAD" to "Namibian Dollar",
    "NGN" to "Nigerian Naira",
    "NIO" to "Nicaraguan Córdoba",
    "NOK" to "Norwegian Krone",
    "NPR" to "Nepalese Rupee",
    "NZD" to "New Zealand Dollar",
    "OMR" to "Omani Rial",
    "PAB" to "Panamanian Balboa",
    "PEN" to "Peruvian Nuevo Sol",
    "PGK" to "Papua New Guinean Kina",
    "PHP" to "Philippine Peso",
    "PKR" to "Pakistani Rupee",
    "PLN" to "Polish Zloty",
    "PYG" to "Paraguayan Guarani",
    "QAR" to "Qatari Rial",
    "RON" to "Romanian Leu",
    "RSD" to "Serbian Dinar",
    "RUB" to "Russian Ruble",
    "RWF" to "Rwandan Franc",
    "SAR" to "Saudi Riyal",
    "SBD" to "Solomon Islands Dollar",
    "SCR" to "Seychellois Rupee",
    "SDG" to "Sudanese Pound",
    "SEK" to "Swedish Krona",
    "SGD" to "Singapore Dollar",
    "SHP" to "Saint Helena Pound",
    "SLL" to "Sierra Leonean Leone",
    "SOS" to "Somali Shilling",
    "SRD" to "Surinamese Dollar",
    "STD" to "São Tomé and Príncipe Dobra",
    "SVC" to "Salvadoran Colón",
    "SYP" to "Syrian Pound",
    "SZL" to "Swazi Lilangeni",
    "THB" to "Thai Baht",
    "TJS" to "Tajikistani Somoni",
    "TMT" to "Turkmenistani Manat",
    "TND" to "Tunisian Dinar",
    "TOP" to "Tongan Paʻanga",
    "TRY" to "Turkish Lira",
    "TTD" to "Trinidad and Tobago Dollar",
    "TWD" to "New Taiwan Dollar",
    "TZS" to "Tanzanian Shilling",
    "UAH" to "Ukrainian Hryvnia",
    "UGX" to "Ugandan Shilling",
    "USD" to "United States Dollar",
    "UYU" to "Uruguayan Peso",
    "UZS" to "Uzbekistan Som",
    "VEF" to "Venezuelan Bolívar Fuerte",
    "VND" to "Vietnamese Dong",
    "VUV" to "Vanuatu Vatu",
    "WST" to "Samoan Tala",
    "XAF" to "CFA Franc BEAC",
    "XAG" to "Silver (troy ounce)",
    "XAU" to "Gold (troy ounce)",
    "XCD" to "East Caribbean Dollar",
    "XDR" to "Special Drawing Rights",
    "XOF" to "CFA Franc BCEAO",
    "XPF" to "CFP Franc",
    "YER" to "Yemeni Rial",
    "ZAR" to "South African Rand",
    "ZMK" to "Zambian Kwacha (pre-2013)",
    "ZMW" to "Zambian Kwacha",
    "ZWL" to "Zimbabwean Dollar"
)