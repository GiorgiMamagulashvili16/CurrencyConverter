package com.example.currencyconverter_compose

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

internal fun MockWebServer.enqueueResponse(filename: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-responses/${filename}")
    val source = inputStream?.let { inputStream.source().buffer() }

    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code = code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}