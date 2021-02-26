package com.example.costaapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.costaapp.model.Item
import com.example.costaapp.model.BaseResponse
import com.example.costaapp.networkcall.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenueRepositoryImpl() : VenueRepository {

    private val TAG = "VenueRepositoryImpl"
    private val retrofitClient = RetrofitClient()

    override fun getVenue(): MutableLiveData<List<Item>> {
        val venueRequest = retrofitClient.getVenue()
        val data = MutableLiveData<List<Item>>()
        Log.d(TAG, "foursquare url: ${venueRequest.request().url}")
            venueRequest.enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d(TAG, "onResponse: ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        data.value =  (response.body()?.response?.groups?.get(0)?.items)
                    }
                }
                override fun onFailure(call: Call<BaseResponse>, error: Throwable) {
                    Log.d("onFailure:", "t ${error.fillInStackTrace()}")
                }
            })
        return data
    }

    override fun getVenueLocation(): LiveData<String> {
        val venueRequest = retrofitClient.getVenue()
        val data = MutableLiveData<String>()
        Log.d(TAG, "foursquare url: ${venueRequest.request().url}")
        venueRequest.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d(TAG, "onResponse: ${response.isSuccessful}")
                if (response.isSuccessful) {
                    data.value =  (response.body()?.response?.location)
                }
            }
            override fun onFailure(call: Call<BaseResponse>, error: Throwable) {
                Log.d("onFailure:", "t ${error.fillInStackTrace()}")
            }
        })
        return data
    }


    override fun searchVenue(query: String): LiveData<List<Item>> {
        val venueRequest = retrofitClient.getVenue()
        val data = MutableLiveData<List<Item>>()
        Log.d(TAG, "foursquare url: ${venueRequest.request().url}")
            venueRequest.enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d(TAG, "onResponse: ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        data.value =  (response.body()?.response?.groups?.get(0)?.items)
                    }
                }
                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("onFailure:", "t ${t.fillInStackTrace()}")
                }
            })
        return data
    }
}