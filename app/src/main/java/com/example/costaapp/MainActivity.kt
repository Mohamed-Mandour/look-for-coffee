package com.example.costaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.costaapp.model.MetaResponse
import com.example.costaapp.networkcall.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private val retrofitClient = RetrofitClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val venue = retrofitClient.getForecast()
        Log.d(TAG, "venue url: ${venue.request().url}")
        venue.enqueue(object : Callback<MetaResponse> {
            override fun onResponse(call: Call<MetaResponse>, response: Response<MetaResponse>) {
                Log.d(TAG, "onResponse: ${response.isSuccessful}")
                if (response.isSuccessful) {
                    response.body()
                }
            }
            override fun onFailure(call: Call<MetaResponse>, t: Throwable) {
                Log.d("onFailure:", "t ${t.fillInStackTrace()}")
            }
        })
    }
}