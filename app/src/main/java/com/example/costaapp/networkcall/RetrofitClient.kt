package com.example.costaapp.networkcall

import com.example.costaapp.BuildConfig
import com.example.costaapp.model.MetaResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    private var foursquareApi: FoursquareApi

    companion object {
        private const val CLIENT_ID = "ULEEKFKJW1NOK3B4SRCHKSJI5D2DL1EUHNGW3OHFWI4OOXXD"
        private const val CLIENT_SECRET = "LZE1IHQO05BX05OSYZMONQFMJR4PWSGLWTREIK4SGCA4EE5R"
        private const val BASE_URL = "https://api.foursquare.com/v2/venues/"
        private const val V = "20180323"
        private const val LL = "51.510288,-0.056160"
    }

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        foursquareApi = retrofit.create(FoursquareApi::class.java)
    }

    fun getForecast(): Call<MetaResponse> {
        return foursquareApi.getVenue(CLIENT_ID, CLIENT_SECRET, V, 10, LL, "costa")
    }
}
