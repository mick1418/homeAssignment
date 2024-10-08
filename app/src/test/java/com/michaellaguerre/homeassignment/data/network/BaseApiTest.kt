package com.michaellaguerre.homeassignment.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mockWebServer: MockWebServer
    lateinit var retrofit: Retrofit


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    @Before
    fun createServer() {

        // These dependencies should be provided by the Dagger Test modules
        mockWebServer = MockWebServer()
        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().apply {
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }


    /**
     * Enqueues a predefined response to the MockWebServer.
     * This response will be served the next time the server must serve a url.
     *
     * The response will be retrieved from a json file in the "resources/api-response" directory.
     *
     * @param mockWebServer the [MockWebServer]
     * @param fileName the filename of the json file to be served
     * @param headers a list of headers to be served with the response (default empty list)
     */
    fun enqueueResponse(
        mockWebServer: MockWebServer,
        fileName: String,
        headers: Map<String, String> = emptyMap()
    ) {

        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(inputStream?.bufferedReader()?.readText() ?: "")
        )
    }
}
