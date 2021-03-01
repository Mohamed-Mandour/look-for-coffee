package com.example.costaapp.networkcall

import com.example.costaapp.model.Response
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val meta = """{ "code": 200, "requestId": "6039a1b14253cd5b56fa8054" }"""
private val response = Response("London", null)
private val testJson = """{ "meta": $meta, "response": "$response" }"""

class RetrofitClientTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val foursquareApi by lazy {
        retrofit.create(FoursquareApi::class.java)
    }

    @Test
    fun getVenueWhenRequestingVenue() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(testJson)
                .setResponseCode(200)
        )

        val testObserver = foursquareApi.getVenue("", "", "", 1, "", "")
        assertThat(testObserver.isExecuted)
    }

    @Test
    fun getVenueGetsVenueJson() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(testJson)
                .setResponseCode(200)
        )

        val testObserver = foursquareApi.getVenue("", "", "", 1, "", "")
        TestCase.assertEquals(
            "venues/explore",
            mockWebServer.takeRequest().path
        )
    }
}