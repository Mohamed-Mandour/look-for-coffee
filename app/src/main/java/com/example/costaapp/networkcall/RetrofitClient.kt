package com.example.costaapp.networkcall

import com.example.costaapp.BuildConfig
import com.example.costaapp.location.DeviceLocation
import com.example.costaapp.model.BaseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient(private val location: DeviceLocation?) {

    private var foursquareApi: FoursquareApi

    companion object {
        private const val CLIENT_ID = "ULEEKFKJW1NOK3B4SRCHKSJI5D2DL1EUHNGW3OHFWI4OOXXD"
        private const val CLIENT_SECRET = "LZE1IHQO05BX05OSYZMONQFMJR4PWSGLWTREIK4SGCA4EE5R"
        private const val BASE_URL = "https://api.foursquare.com/v2/"
        private const val V = "20180323"
        private const val VENUE_PARAM = "coffee"
        private const val LIMIT = 50
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

    fun getVenue(): Call<BaseResponse> {

        return foursquareApi.getVenue(
            CLIENT_ID,
            CLIENT_SECRET,
            V,
            LIMIT,
            locationFormat(),
            VENUE_PARAM
        )
    }

    private fun locationFormat(): String {
        return "${location?.latitude},${location?.longitude}"
    }
}