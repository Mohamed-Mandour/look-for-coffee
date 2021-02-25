package com.example.costaapp.networkcall

import com.example.costaapp.model.MetaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoursquareApi {

    @GET("explore")
    fun getVenue(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("v") v: String,
        @Query("limit") limit: Int,
        @Query("ll", encoded = true) ll: String?,
        @Query("query") search_venue: String
    ): Call<MetaResponse>
}
