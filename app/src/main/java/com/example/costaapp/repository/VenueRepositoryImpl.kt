package com.example.costaapp.repository

import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.costaapp.database.VenueDao
import com.example.costaapp.db
import com.example.costaapp.model.Item
import com.example.costaapp.model.BaseResponse
import com.example.costaapp.model.Venue
import com.example.costaapp.networkcall.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

private const val TAG = "VenueRepositoryImpl"

class VenueRepositoryImpl : VenueRepository {

    private val retrofitClient = RetrofitClient()
    private val venueDao: VenueDao = db.venueDao()
    private val allVenue: LiveData<List<Venue>>

    init {
        allVenue = venueDao.getAll()
    }

    override fun getVenue(): MutableLiveData<List<Item>> {
        val venueRequest = retrofitClient.getVenue()
        val data = MutableLiveData<List<Item>>()
        Log.d(TAG, "foursquare url: ${venueRequest.request().url}")
            venueRequest.enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d(TAG, "onResponse: ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        data.value =  (response.body()?.response?.groups?.get(0)?.items)
                        GlobalScope.launch(Dispatchers.Default) {
                            saveVenue(data.value)
                        }
                    }
                }
                override fun onFailure(call: Call<BaseResponse>, error: Throwable) {
                    Log.d("onFailure:", "t ${error.fillInStackTrace()}")
                }
            })
        return data
    }

    private suspend fun saveVenue(items: List<Item>?) {
            if (items != null) {
                for (item in items) {
                    item.venue?.let { venueDao.insert(it) }
                }
        }
    }

    override fun getSavedVenue() = allVenue

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